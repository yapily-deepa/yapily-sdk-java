package com.yapily.sdk.client.banking;

import java.util.List;
import java.util.UUID;

import com.yapily.api.client.model.Account;
import com.yapily.sdk.client.BaseHttpRpc;
import com.yapily.sdk.client.ResponseDeserializer;
import com.yapily.sdk.client.SystemPropertiesCredentialsProvider;
import com.yapily.sdk.services.ApiClient;
import com.fasterxml.jackson.core.type.TypeReference;

public class HttpAccountsRpc extends BaseHttpRpc {

    private final ApiClient apiClient;

    ResponseDeserializer<List<Account>> responseDeserializerList = new ResponseDeserializer<>(new TypeReference<List<Account>>() {
    });
    ResponseDeserializer<Account> responseDeserializer = new ResponseDeserializer<>(Account.class);


    public HttpAccountsRpc(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<Account> listAccounts(String userUuid, String bankId) {
        return requestGet(getAccountsUrl(userUuid, bankId), responseDeserializerList, SystemPropertiesCredentialsProvider.credentialsProvider());
    }

    private String getAccountsUrl(String userUuid, String bankId) {
        return apiClient.getBaseUrl()
                        .replace("{userUuid}", userUuid)
                        .replace("{bankId}", bankId);
    }

}
