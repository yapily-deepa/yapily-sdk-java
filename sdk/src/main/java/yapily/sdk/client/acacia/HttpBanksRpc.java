package yapily.sdk.client.acacia;

import java.util.List;

import yapily.api.client.model.Bank;
import yapily.sdk.client.BaseHttpRpc;
import yapily.sdk.client.RequestSerializer;
import yapily.sdk.client.ResponseDeserializer;
import yapily.sdk.client.SystemPropertiesCredentialsProvider;
import yapily.sdk.services.ApiClient;
import com.fasterxml.jackson.core.type.TypeReference;

public class HttpBanksRpc extends BaseHttpRpc {

    private final ApiClient apiClient;

    ResponseDeserializer<List<Bank>> responseDeserializer = new ResponseDeserializer<>(new TypeReference<List<Bank>>() {
    });
    RequestSerializer requestSerializer = new RequestSerializer();

    public HttpBanksRpc(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<Bank> listBanks() {
        return requestGet(apiClient.getBaseUrl(), responseDeserializer, SystemPropertiesCredentialsProvider.credentialsProvider());
    }

}
