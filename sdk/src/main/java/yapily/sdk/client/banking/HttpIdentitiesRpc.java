package yapily.sdk.client.banking;

import yapily.api.client.model.Identity;
import yapily.sdk.client.BaseHttpRpc;
import yapily.sdk.client.ResponseDeserializer;
import yapily.sdk.client.SystemPropertiesCredentialsProvider;
import yapily.sdk.services.ApiClient;

public class HttpIdentitiesRpc extends BaseHttpRpc {

    private final ApiClient apiClient;

    ResponseDeserializer<Identity> responseDeserializer = new ResponseDeserializer<>(Identity.class);

    public HttpIdentitiesRpc(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Identity getIdentity(String userUUID, String bankId) {
        return requestGet(getIdentityUrl(userUUID, bankId), responseDeserializer, SystemPropertiesCredentialsProvider.credentialsProvider());
    }

    private String getIdentityUrl(String userUUID, String bankId) {
        return apiClient.getBaseUrl()
                        .replace("{userUuid}", userUUID)
                        .replace("{bankId}", bankId);
    }

}
