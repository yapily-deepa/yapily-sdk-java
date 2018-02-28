package com.yapily.sdk.client.banking;

import com.yapily.api.client.model.Transaction;
import com.yapily.sdk.client.BaseHttpRpc;
import com.yapily.sdk.client.ResponseDeserializer;
import com.yapily.sdk.client.SystemPropertiesCredentialsProvider;
import com.yapily.sdk.services.ApiClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.UUID;

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
