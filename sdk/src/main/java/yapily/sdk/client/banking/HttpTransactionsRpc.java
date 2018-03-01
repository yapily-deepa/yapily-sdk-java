package yapily.sdk.client.banking;

import yapily.api.client.model.Transaction;
import yapily.sdk.client.BaseHttpRpc;
import yapily.sdk.client.ResponseDeserializer;
import yapily.sdk.client.SystemPropertiesCredentialsProvider;
import yapily.sdk.services.ApiClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class HttpTransactionsRpc extends BaseHttpRpc {

    private final ApiClient apiClient;

    ResponseDeserializer<List<Transaction>> responseDeserializerList = new ResponseDeserializer<>(new TypeReference<List<Transaction>>() {
    });

    public HttpTransactionsRpc(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<Transaction> getTransactions(String userUUID, String accountId, String bankId) {
        return requestGet(getTransactionsUrl(userUUID, accountId, bankId),
                          responseDeserializerList,
                          SystemPropertiesCredentialsProvider.credentialsProvider());
    }

    private String getTransactionsUrl(String userUUID, String accountId, String bankId) {
        return apiClient.getBaseUrl()
                        .replace("{userUuid}", userUUID)
                        .replace("{accountId}", accountId)
                        .replace("{bankId}", bankId);
    }

}
