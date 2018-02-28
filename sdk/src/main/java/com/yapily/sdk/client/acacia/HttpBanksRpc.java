package com.yapily.sdk.client.acacia;

import java.util.List;

import com.yapily.api.client.model.Bank;
import com.yapily.sdk.client.BaseHttpRpc;
import com.yapily.sdk.client.RequestSerializer;
import com.yapily.sdk.client.ResponseDeserializer;
import com.yapily.sdk.client.SystemPropertiesCredentialsProvider;
import com.yapily.sdk.services.ApiClient;
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
