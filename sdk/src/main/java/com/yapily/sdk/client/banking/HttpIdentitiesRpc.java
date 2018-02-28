package com.yapily.sdk.client.banking;

import com.yapily.api.client.model.Identity;
import com.yapily.sdk.client.BaseHttpRpc;
import com.yapily.sdk.client.ResponseDeserializer;
import com.yapily.sdk.client.SystemPropertiesCredentialsProvider;
import com.yapily.sdk.services.ApiClient;

import java.util.UUID;

public class HttpIdentitiesRpc extends BaseHttpRpc {

    private final ApiClient apiClient;

    ResponseDeserializer<Identity> responseDeserializer = new ResponseDeserializer<>(Identity.class);

    public HttpIdentitiesRpc(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Identity getIdentity(String userUUID, String bankId) {
        return requestGet(getIdentityUrl(userUUID, bankId), responseDeserializer, SystemPropertiesCredentialsProvider.credentialsProvider());
    }

    private String getIdentityUrl(String userUUID, String bankId) {
        return apiClient.getBaseUrl()
                        .replace("{userUuid}", userUUID)
                        .replace("{bankId}", bankId);
    }

}
