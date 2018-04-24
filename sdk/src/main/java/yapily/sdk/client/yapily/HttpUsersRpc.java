package yapily.sdk.client.yapily;


import java.util.List;

import org.apache.http.impl.client.BasicCredentialsProvider;

import com.fasterxml.jackson.core.type.TypeReference;

import yapily.api.client.model.ApplicationUser;
import yapily.sdk.client.BaseHttpRpc;
import yapily.sdk.client.RequestSerializer;
import yapily.sdk.client.ResponseDeserializer;
import yapily.sdk.client.SystemPropertiesCredentialsProvider;
import yapily.sdk.services.ApiClient;

public class HttpUsersRpc extends BaseHttpRpc {

    private final ApiClient apiClient;
    private final BasicCredentialsProvider basicCredentialsProvider;


    ResponseDeserializer<List<ApplicationUser>> responseDeserializerList = new ResponseDeserializer<>(new TypeReference<List<ApplicationUser>>() {
    });
    ResponseDeserializer<ApplicationUser> responseDeserializer = new ResponseDeserializer<>(ApplicationUser.class);
    RequestSerializer requestSerializer = new RequestSerializer();

    public HttpUsersRpc(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.basicCredentialsProvider = SystemPropertiesCredentialsProvider.credentialsProvider();
    }

    public HttpUsersRpc(ApiClient apiClient,BasicCredentialsProvider basicCredentialsProvider) {
        this.apiClient = apiClient;
        this.basicCredentialsProvider = basicCredentialsProvider;
    }

    public List<ApplicationUser> listUsers() {
        return requestGet(apiClient.getBaseUrl(), responseDeserializerList, basicCredentialsProvider);
    }

    public ApplicationUser postUser(ApplicationUser appUser) {
        return requestPost(apiClient.getBaseUrl(), appUser, requestSerializer, responseDeserializer, basicCredentialsProvider);
    }

    public void putUser(String uuid, ApplicationUser applicationUser) {
        requestPut(getApplicationUserUrl(uuid), applicationUser, requestSerializer, basicCredentialsProvider);
    }

    public void deleteUser(String uuid) {
        requestDelete(getApplicationUserUrl(uuid), basicCredentialsProvider);
    }

    public ApplicationUser getUser(String uuid) {
        return requestGet(getApplicationUserUrl(uuid), responseDeserializer, basicCredentialsProvider);
    }

    private String getApplicationUserUrl(String userUuid) {
        return apiClient.getBaseUrl() + userUuid;
    }
}
