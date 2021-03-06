package yapily.sdk.services.institutions;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

import yapily.sdk.YapilyAuth;
import yapily.sdk.client.exceptions.ApiException;
import yapily.sdk.services.AuthClient;

public class Auth extends AuthClient {

    public Auth() {
        super(YapilyAuth.PATH_DIRECT);
    }

    public URI authDirectURL(String applicationUuid, String userUuid, String institutionId, String callbackUrl, String scope) {
        try {
            final URIBuilder uriBuilder = new URIBuilder(getBaseDirectUrl());
            uriBuilder.addParameter(YapilyAuth.PARAMETER_INSTITUTION_ID, institutionId);
            uriBuilder.addParameters(uriParameters(applicationUuid, userUuid, callbackUrl, scope));
            return uriBuilder.build();
        } catch (final URISyntaxException e) {
            throw new ApiException(e);
        }
    }

    private List<NameValuePair> uriParameters(String applicationUuid, String userUuid, String callbackUrl, String scope) {
        List<NameValuePair> uriParameters = new ArrayList<>();
        uriParameters.add(new BasicNameValuePair(YapilyAuth.PARAMETER_APPLICATION_ID, applicationUuid));
        uriParameters.add(new BasicNameValuePair(YapilyAuth.PARAMETER_USER_ID, userUuid));
        if (callbackUrl != null) uriParameters.add(new BasicNameValuePair(YapilyAuth.PARAMETER_CALLBACK_URL, callbackUrl));
        if (scope != null) uriParameters.add(new BasicNameValuePair(YapilyAuth.PARAMETER_SCOPE, scope));
        return uriParameters;
    }

}
