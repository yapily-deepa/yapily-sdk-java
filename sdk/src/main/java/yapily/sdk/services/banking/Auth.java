package yapily.sdk.services.banking;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public Auth(String rootUrl) {
        super(rootUrl, YapilyAuth.PATH_DIRECT);
    }

    public URI authDirectURL(UUID applicationUuid, String userUuid, String bankId, String callbackUrl, String scope) {
        try {
            final URIBuilder uriBuilder = new URIBuilder(getBaseDirectUrl());
            uriBuilder.addParameter(YapilyAuth.PARAMETER_BANK_ID, bankId);
            uriBuilder.addParameters(uriParameters(applicationUuid, userUuid, callbackUrl, scope));
            return uriBuilder.build();
        } catch (final URISyntaxException e) {
            throw new ApiException(e);
        }
    }

    private List<NameValuePair> uriParameters(UUID applicationUuid, String userUuid, String callbackUrl, String scope) {
        List<NameValuePair> uriParameters = new ArrayList<>();
        uriParameters.add(new BasicNameValuePair(YapilyAuth.PARAMETER_APPLICATION_ID, applicationUuid.toString()));
        uriParameters.add(new BasicNameValuePair(YapilyAuth.PARAMETER_USER_ID, userUuid));
        if (callbackUrl != null) uriParameters.add(new BasicNameValuePair(YapilyAuth.PARAMETER_CALLBACK_URL, callbackUrl));
        if (scope != null) uriParameters.add(new BasicNameValuePair(YapilyAuth.PARAMETER_SCOPE, scope));
        return uriParameters;
    }

}
