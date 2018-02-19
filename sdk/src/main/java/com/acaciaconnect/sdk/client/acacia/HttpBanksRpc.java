package com.acaciaconnect.sdk.client.acacia;

import java.util.List;

import com.acaciaconnect.api.client.model.acacia.Bank;
import com.acaciaconnect.sdk.client.BaseHttpRpc;
import com.acaciaconnect.sdk.client.RequestSerializer;
import com.acaciaconnect.sdk.client.ResponseDeserializer;
import com.acaciaconnect.sdk.client.SystemPropertiesCredentialsProvider;
import com.acaciaconnect.sdk.services.ApiClient;
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
