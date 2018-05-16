package yapily.sdk.services.institutions;

import yapily.api.client.model.Identity;
import yapily.sdk.YapilyApi;
import yapily.sdk.client.HeaderAppender;
import yapily.sdk.client.institutions.HttpIdentitiesRpc;
import yapily.sdk.credential.YapilyCredentials;
import yapily.sdk.services.ApiClient;

public class IdentitiesApi {

    private final HttpIdentitiesRpc identityRpc;

    private IdentitiesApi(YapilyCredentials yapilyCredentials, HeaderAppender headerAppender) {
        identityRpc = new HttpIdentitiesRpc(new ApiClient(YapilyApi.SERVICE_PATH_IDENTITY), yapilyCredentials.toCredentialsProvider(), headerAppender);
    }

    public Identity getIdentity() {
        return identityRpc.getIdentity();
    }

    public static class Builder extends InstitutionServiceBuilder<IdentitiesApi> {

        @Override
        public IdentitiesApi build() {
            final YapilyCredentials yapilyCredentials = createCredentials();
            final HeaderAppender headerAppender = createHeaderAppender();
            return new IdentitiesApi(yapilyCredentials, headerAppender);
        }
    }
}
