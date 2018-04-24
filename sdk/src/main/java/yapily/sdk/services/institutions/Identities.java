package yapily.sdk.services.institutions;

import yapily.api.client.model.Identity;
import yapily.sdk.YapilyApi;
import yapily.sdk.YapilyCredentials;
import yapily.sdk.client.institutions.HttpIdentitiesRpc;
import yapily.sdk.services.ApiClient;

public class Identities extends ApiClient {

    private final HttpIdentitiesRpc identityRpc;

    public Identities() {
        super(YapilyApi.SERVICE_PATH_USERS_INSTITUTIONS_IDENTITY);
        identityRpc = new HttpIdentitiesRpc(this);
    }

    public Identities(YapilyCredentials yapilyCredentials) {
        super(YapilyApi.SERVICE_PATH_USERS_INSTITUTIONS_IDENTITY);
        identityRpc = new HttpIdentitiesRpc(this, yapilyCredentials.toCredentialsProvider());
    }


    public Identity getIdentity(String userUUID, String institutionId) {
        return identityRpc.getIdentity(userUUID, institutionId);
    }

}
