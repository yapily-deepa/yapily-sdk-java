package com.acaciaconnect.sdk.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.acaciaconnect.sdk.services.ApiException;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;

public class BaseHttpRpc {

    public <T> T requestGet(String url, ResponseDeserializer<T> responseDeserializer, CredentialsProvider credentialsProvider) {
        return executeRequest(new HttpGet(url), responseDeserializer, credentialsProvider);
    }

    public void requestDelete(String url, CredentialsProvider credentialsProvider) {
        final HttpDelete httpRequest = new HttpDelete(url);
        executeRequest(httpRequest, null, credentialsProvider);
    }

    public void requestPut(String url, Object object, RequestSerializer requestSerializer, CredentialsProvider credentialsProvider) {
        final HttpPut httpRequest = new HttpPut(url);
        executeRequestWithEnclosingEntity(httpRequest, object, requestSerializer, null, credentialsProvider);
    }

    public <T> T requestPost(String url, Object entity, RequestSerializer requestSerializer, ResponseDeserializer<T> responseDeserializer, CredentialsProvider credentialsProvider) {
        final HttpPost httpRequest = new HttpPost(url);
        return executeRequestWithEnclosingEntity(httpRequest, entity, requestSerializer, responseDeserializer, credentialsProvider);
    }

    private <T> T executeRequestWithEnclosingEntity(HttpEntityEnclosingRequestBase httpRequest, Object entity, RequestSerializer requestSerializer, ResponseDeserializer<T> responseDeserializer,
                                                    CredentialsProvider credentialsProvider) {
        if (entity != null && requestSerializer != null) {
            try {
                setHttpRequestEntity(httpRequest, requestSerializer, entity);
            } catch (final IOException e) {
                throw new ApiException(e);
            }
        }
        return executeRequest(httpRequest, responseDeserializer, credentialsProvider);
    }

    private <T> T executeRequest(HttpRequestBase httpRequest, ResponseDeserializer<T> responseDeserializer, CredentialsProvider credentialsProvider) {
        //    final CloseableHttpClient httpClient = credentialsProvider == null ? HttpClients.createDefault()
        //                                                                       : HttpClients.custom()
        //                                                                                    .setDefaultCredentialsProvider(credentialsProvider)
        //                                                                                    .build();

        final SSLConnectionSocketFactory sslsf;
        try {
            sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault(),
                                                   NoopHostnameVerifier.INSTANCE);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        final Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", sslsf)
                .build();

        final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(100);

        final CloseableHttpClient httpClient = credentialsProvider == null ? HttpClients.custom()
                                                                                        .setSSLSocketFactory(sslsf)
                                                                                        .setConnectionManager(cm)
                                                                                        .build()
                                                                           : HttpClients.custom()
                                                                                        .setSSLSocketFactory(sslsf)
                                                                                        .setConnectionManager(cm)
                                                                                        .setDefaultCredentialsProvider(credentialsProvider)
                                                                                        .build();

        T result = null;
        try {
            if (responseDeserializer != null) {
                httpRequest.setHeader(HttpHeaders.ACCEPT, responseDeserializer.getMimeType());
            }

            final CloseableHttpResponse httpResponse = httpClient.execute(httpRequest);
            final HttpEntity entity = httpResponse.getEntity();

            if (responseDeserializer != null) {
                if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    result = responseDeserializer.getObject(entity.getContent());
                } else {
                    System.out.println("httpRequest uri - " + httpRequest.getURI().toString());
                    System.out.println("httpResponse.getStatusLine().getStatusCode() - " + httpResponse.getStatusLine().getStatusCode());
                    throw new ApiException(httpResponse.getStatusLine().getStatusCode(), httpResponse.getStatusLine().getReasonPhrase());
                }
            }
            httpResponse.close();

        } catch (final IOException e) {
            throw new ApiException(e);
        } finally {
            try {
                httpClient.close();
            } catch (final IOException e) {
                throw new ApiException(e);
            }
        }
        return result;
    }

    private void setHttpRequestEntity(HttpEntityEnclosingRequestBase httpRequest, RequestSerializer requestSerializer, Object object) throws IOException {
        StringEntity stringEntity;
        try {
            stringEntity = new StringEntity(requestSerializer.getString(object));
            httpRequest.setEntity(stringEntity);
            httpRequest.setHeader(HttpHeaders.CONTENT_TYPE, requestSerializer.getMimeType());
        } catch (UnsupportedEncodingException | JsonProcessingException e) {
            throw new ApiException(e);
        }

    }

}
