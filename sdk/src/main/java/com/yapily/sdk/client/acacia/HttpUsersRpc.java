package com.yapily.sdk.client.acacia;

import com.yapily.api.client.models.acacia.ApplicationUser;
import com.yapily.sdk.client.BaseHttpRpc;
import com.yapily.sdk.client.RequestSerializer;
import com.yapily.sdk.client.ResponseDeserializer;
import com.yapily.sdk.client.SystemPropertiesCredentialsProvider;
import com.yapily.sdk.services.ApiClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.UUID;

public class HttpUsersRpc extends BaseHttpRpc {

    private final ApiClient apiClient;

    ResponseDeserializer<List<ApplicationUser>> responseDeserializerList = new ResponseDeserializer<>(new TypeReference<List<ApplicationUser>>() {
    });
    ResponseDeserializer<ApplicationUser> responseDeserializer = new ResponseDeserializer<>(ApplicationUser.class);
    RequestSerializer requestSerializer = new RequestSerializer();

    public HttpUsersRpc(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<ApplicationUser> listUsers() {
        return requestGet(apiClient.getBaseUrl(), responseDeserializerList, SystemPropertiesCredentialsProvider.credentialsProvider());
    }

    public ApplicationUser postUser(String appUserId) {
        return requestPost(apiClient.getBaseUrl(), appUserId, requestSerializer, responseDeserializer, SystemPropertiesCredentialsProvider.credentialsProvider());
    }

    public void putUser(UUID uuid, ApplicationUser applicationUser) {
        requestPut(getApplicationUserUrl(uuid), applicationUser, requestSerializer, SystemPropertiesCredentialsProvider.credentialsProvider());
    }

    public void deleteUser(UUID uuid) {
        requestDelete(getApplicationUserUrl(uuid), SystemPropertiesCredentialsProvider.credentialsProvider());
    }

    public ApplicationUser getUser(UUID uuid) {
        return requestGet(getApplicationUserUrl(uuid), responseDeserializer, SystemPropertiesCredentialsProvider.credentialsProvider());
    }

    private String getApplicationUserUrl(UUID userUuid) {
        return apiClient.getBaseUrl() + userUuid.toString();
    }
}
