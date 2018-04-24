package yapily.sdk.client.institutions;

import org.apache.http.impl.client.BasicCredentialsProvider;

import yapily.api.client.model.Identity;
import yapily.sdk.client.BaseHttpRpc;
import yapily.sdk.client.ResponseDeserializer;
import yapily.sdk.client.SystemPropertiesCredentialsProvider;
import yapily.sdk.services.ApiClient;

public class HttpIdentitiesRpc extends BaseHttpRpc {

    private final ApiClient apiClient;
    private final BasicCredentialsProvider basicCredentialsProvider;

    ResponseDeserializer<Identity> responseDeserializer = new ResponseDeserializer<>(Identity.class);

    public HttpIdentitiesRpc(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.basicCredentialsProvider = SystemPropertiesCredentialsProvider.credentialsProvider();
    }

    public HttpIdentitiesRpc(ApiClient apiClient, BasicCredentialsProvider basicCredentialsProvider) {
        this.apiClient = apiClient;
        this.basicCredentialsProvider = basicCredentialsProvider;
    }

    public Identity getIdentity(String userUUID, String institutionId) {
        return requestGet(getIdentityUrl(userUUID, institutionId), responseDeserializer, basicCredentialsProvider);
    }

    private String getIdentityUrl(String userUUID, String institutionId) {
        return apiClient.getBaseUrl()
                        .replace("{userUuid}", userUUID)
                        .replace("{institutionId}", institutionId);
    }

}
