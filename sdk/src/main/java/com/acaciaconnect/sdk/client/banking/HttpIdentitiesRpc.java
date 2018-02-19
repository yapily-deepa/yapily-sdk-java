package com.acaciaconnect.sdk.client.banking;

import com.acaciaconnect.api.client.model.banking.Identity;
import com.acaciaconnect.sdk.client.BaseHttpRpc;
import com.acaciaconnect.sdk.client.ResponseDeserializer;
import com.acaciaconnect.sdk.client.SystemPropertiesCredentialsProvider;
import com.acaciaconnect.sdk.services.ApiClient;

import java.util.UUID;

public class HttpIdentitiesRpc extends BaseHttpRpc {

    private final ApiClient apiClient;

    ResponseDeserializer<Identity> responseDeserializer = new ResponseDeserializer<>(Identity.class);

    public HttpIdentitiesRpc(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Identity getIdentity(UUID userUUID, String bankId) {
        return requestGet(getIdentityUrl(userUUID, bankId), responseDeserializer, SystemPropertiesCredentialsProvider.credentialsProvider());
    }

    private String getIdentityUrl(UUID userUUID, String bankId) {
        return apiClient.getBaseUrl()
                        .replace("{userUuid}", userUUID.toString())
                        .replace("{bankId}", bankId);
    }

}
