package com.acaciaconnect.sdk.client.banking;

import java.util.List;
import java.util.UUID;

import com.acaciaconnect.api.client.model.banking.Account;
import com.acaciaconnect.sdk.client.BaseHttpRpc;
import com.acaciaconnect.sdk.client.ResponseDeserializer;
import com.acaciaconnect.sdk.client.SystemPropertiesCredentialsProvider;
import com.acaciaconnect.sdk.services.ApiClient;
import com.fasterxml.jackson.core.type.TypeReference;

public class HttpAccountsRpc extends BaseHttpRpc {

    private final ApiClient apiClient;

    ResponseDeserializer<List<Account>> responseDeserializerList = new ResponseDeserializer<>(new TypeReference<List<Account>>() {
    });
    ResponseDeserializer<Account> responseDeserializer = new ResponseDeserializer<>(Account.class);


    public HttpAccountsRpc(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<Account> listAccounts(UUID userUuid, String bankId) {
        return requestGet(getAccountsUrl(userUuid.toString(), bankId), responseDeserializerList, SystemPropertiesCredentialsProvider.credentialsProvider());
    }

    private String getAccountsUrl(String userUuid, String bankId) {
        return apiClient.getBaseUrl()
                        .replace("{userUuid}", userUuid)
                        .replace("{bankId}", bankId);
    }

}
