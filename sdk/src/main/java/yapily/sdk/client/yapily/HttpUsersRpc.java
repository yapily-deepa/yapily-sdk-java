package yapily.sdk.client.yapily;


import yapily.api.client.model.ApplicationUser;
import yapily.sdk.client.BaseHttpRpc;
import yapily.sdk.client.RequestSerializer;
import yapily.sdk.client.ResponseDeserializer;
import yapily.sdk.client.SystemPropertiesCredentialsProvider;
import yapily.sdk.services.ApiClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

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

    public void putUser(String uuid, ApplicationUser applicationUser) {
        requestPut(getApplicationUserUrl(uuid), applicationUser, requestSerializer, SystemPropertiesCredentialsProvider.credentialsProvider());
    }

    public void deleteUser(String uuid) {
        requestDelete(getApplicationUserUrl(uuid), SystemPropertiesCredentialsProvider.credentialsProvider());
    }

    public ApplicationUser getUser(String uuid) {
        return requestGet(getApplicationUserUrl(uuid), responseDeserializer, SystemPropertiesCredentialsProvider.credentialsProvider());
    }

    private String getApplicationUserUrl(String userUuid) {
        return apiClient.getBaseUrl() + userUuid;
    }
}
