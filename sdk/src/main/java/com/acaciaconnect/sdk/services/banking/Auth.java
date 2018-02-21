package com.acaciaconnect.sdk.services.banking;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.acaciaconnect.auth.client.AcaciaAuth;
import com.acaciaconnect.sdk.client.exceptions.ApiException;
import com.acaciaconnect.sdk.services.AuthClient;

public class Auth extends AuthClient {

    public Auth() {
        super(AcaciaAuth.PATH_DIRECT);
    }

    public Auth(String rootUrl) {
        super(rootUrl, AcaciaAuth.PATH_DIRECT);
    }

    public URI authDirectURL(UUID applicationUuid, UUID userUuid, String bankId, String callbackUrl, String scope) {
        try {
            final URIBuilder uriBuilder = new URIBuilder(getBaseDirectUrl());
            uriBuilder.addParameter(AcaciaAuth.PARAMETER_BANK_ID, bankId);
            uriBuilder.addParameters(uriParameters(applicationUuid, userUuid, callbackUrl, scope));
            return uriBuilder.build();
        } catch (final URISyntaxException e) {
            throw new ApiException(e);
        }
    }

    private List<NameValuePair> uriParameters(UUID applicationUuid, UUID userUuid, String callbackUrl, String scope) {
        List<NameValuePair> uriParameters = new ArrayList<>();
        uriParameters.add(new BasicNameValuePair(AcaciaAuth.PARAMETER_APPLICATION_ID, applicationUuid.toString()));
        uriParameters.add(new BasicNameValuePair(AcaciaAuth.PARAMETER_USER_ID, userUuid.toString()));
        if (callbackUrl != null) uriParameters.add(new BasicNameValuePair(AcaciaAuth.PARAMETER_CALLBACK_URL, callbackUrl));
        if (scope != null) uriParameters.add(new BasicNameValuePair(AcaciaAuth.PARAMETER_SCOPE, scope));
        return uriParameters;
    }

}
