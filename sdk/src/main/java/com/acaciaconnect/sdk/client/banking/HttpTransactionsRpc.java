package com.acaciaconnect.sdk.client.banking;

import com.acaciaconnect.api.client.model.banking.Transaction;
import com.acaciaconnect.sdk.client.BaseHttpRpc;
import com.acaciaconnect.sdk.client.ResponseDeserializer;
import com.acaciaconnect.sdk.client.SystemPropertiesCredentialsProvider;
import com.acaciaconnect.sdk.services.ApiClient;
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

    public List<Transaction> getTransactions(UUID userUUID, String accountId, String bankId) {
        return requestGet(getTransactionsUrl(userUUID, accountId, bankId),
                          responseDeserializerList,
                          SystemPropertiesCredentialsProvider.credentialsProvider());
    }

    private String getTransactionsUrl(UUID userUUID, String accountId, String bankId) {
        return apiClient.getBaseUrl()
                        .replace("{userUuid}", userUUID.toString())
                        .replace("{accountId}", accountId)
                        .replace("{bankId}", bankId);
    }

}
