package yapily.sdk.client.institutions;

import java.util.List;

import org.apache.http.impl.client.BasicCredentialsProvider;

import com.fasterxml.jackson.core.type.TypeReference;

import yapily.api.client.model.Transaction;
import yapily.sdk.client.BaseHttpRpc;
import yapily.sdk.client.ResponseDeserializer;
import yapily.sdk.client.SystemPropertiesCredentialsProvider;
import yapily.sdk.services.ApiClient;

public class HttpTransactionsRpc extends BaseHttpRpc {

    private final ApiClient apiClient;
    private final BasicCredentialsProvider basicCredentialsProvider;

    ResponseDeserializer<List<Transaction>> responseDeserializerList = new ResponseDeserializer<>(new TypeReference<List<Transaction>>() {});

    public HttpTransactionsRpc(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.basicCredentialsProvider = SystemPropertiesCredentialsProvider.credentialsProvider();
    }

    public HttpTransactionsRpc(ApiClient apiClient,BasicCredentialsProvider basicCredentialsProvider) {
        this.apiClient = apiClient;
        this.basicCredentialsProvider = basicCredentialsProvider;
    }

    public List<Transaction> getTransactions(String userUUID, String accountId, String institutionId) {
        return requestGet(getTransactionsUrl(userUUID, accountId, institutionId),
                          responseDeserializerList, basicCredentialsProvider);
    }

    private String getTransactionsUrl(String userUUID, String accountId, String institutionId) {
        return apiClient.getBaseUrl()
                        .replace("{userUuid}", userUUID)
                        .replace("{accountId}", accountId)
                        .replace("{institutionId}", institutionId);
    }

}
