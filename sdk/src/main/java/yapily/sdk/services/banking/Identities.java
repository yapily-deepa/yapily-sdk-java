package yapily.sdk.services.banking;

import yapily.sdk.YapilyApi;
import yapily.api.client.model.Identity;
import yapily.sdk.client.banking.HttpIdentitiesRpc;
import yapily.sdk.services.ApiClient;

public class Identities extends ApiClient {

    private HttpIdentitiesRpc identityRpc = new HttpIdentitiesRpc(this);

    public Identities() {
        super(YapilyApi.SERVICE_PATH_USERS_BANKS_IDENTITY);
    }

    public Identity getIdentity(String userUUID, String bankId) {
        return identityRpc.getIdentity(userUUID, bankId);
    }

}
