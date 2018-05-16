package yapily.sdk.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.core.JsonProcessingException;

import yapily.sdk.client.exceptions.ApiException;

public class BaseHttpRpc {

    protected <T> T requestGet(String url, ResponseDeserializer<T> responseDeserializer, CredentialsProvider credentialsProvider, HeaderAppender headerAppender) {
        return executeRequest(new HttpGet(url), responseDeserializer, credentialsProvider, headerAppender);
    }

    protected void requestDelete(String url, CredentialsProvider credentialsProvider, HeaderAppender headerAppender) {
        final HttpDelete httpRequest = new HttpDelete(url);
        executeRequest(httpRequest, null, credentialsProvider, headerAppender);
    }

    protected void requestPut(String url, Object object, RequestSerializer requestSerializer, CredentialsProvider credentialsProvider, HeaderAppender headerAppender) {
        final HttpPut httpRequest = new HttpPut(url);
        executeRequestWithEnclosingEntity(httpRequest, object, requestSerializer, null, credentialsProvider, headerAppender);
    }

    protected <T> T requestPost(String url, Object entity, RequestSerializer requestSerializer, ResponseDeserializer<T> responseDeserializer, CredentialsProvider credentialsProvider,
                                HeaderAppender headerAppender) {
        final HttpPost httpRequest = new HttpPost(url);
        return executeRequestWithEnclosingEntity(httpRequest, entity, requestSerializer, responseDeserializer, credentialsProvider, headerAppender);
    }

    private <T> T executeRequestWithEnclosingEntity(HttpEntityEnclosingRequestBase httpRequest, Object entity, RequestSerializer requestSerializer, ResponseDeserializer<T> responseDeserializer,
                                                    CredentialsProvider credentialsProvider, HeaderAppender headerAppender) {
        if (entity != null && requestSerializer != null) {
            try {
                setHttpRequestEntity(httpRequest, requestSerializer, entity);
            } catch (final IOException e) {
                throw new ApiException(e);
            }
        }
        return executeRequest(httpRequest, responseDeserializer, credentialsProvider, headerAppender);
    }

    private <T> T executeRequest(HttpRequestBase httpRequest, ResponseDeserializer<T> responseDeserializer, CredentialsProvider credentialsProvider, HeaderAppender headerAppender) {
        final CloseableHttpClient httpClient = credentialsProvider == null ? HttpClients.createDefault()
                                                                           : HttpClients.custom()
                                                                                        .setDefaultCredentialsProvider(credentialsProvider)
                                                                                        .build();
        T result = null;
        try {
            if (responseDeserializer != null) {
                httpRequest.setHeader(HttpHeaders.ACCEPT, responseDeserializer.getMimeType());
            }

            headerAppender.append(httpRequest);

            final CloseableHttpResponse httpResponse = httpClient.execute(httpRequest);
            final HttpEntity entity = httpResponse.getEntity();

            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK || httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
                if (responseDeserializer != null) {
                    result = responseDeserializer.getObject(entity.getContent());
                }
            } else {
                throwApiException(httpRequest, httpResponse);
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

    private void throwApiException(HttpRequestBase httpRequest, HttpResponse httpResponse) {
        System.out.println("httpRequest uri - " + httpRequest.getURI().toString());
        System.out.println("httpResponse.getStatusLine().getStatusCode() - " + httpResponse.getStatusLine().getStatusCode());
        throw new ApiException(httpResponse.getStatusLine().getStatusCode(), httpResponse.getStatusLine().getReasonPhrase());
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
