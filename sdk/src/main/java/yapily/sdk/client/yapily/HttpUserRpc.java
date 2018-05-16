package yapily.sdk.client.yapily;

import org.apache.http.client.CredentialsProvider;

import yapily.api.client.model.ApplicationUser;
import yapily.sdk.client.BaseHttpRpc;
import yapily.sdk.client.HeaderAppender;
import yapily.sdk.client.RequestSerializer;
import yapily.sdk.client.ResponseDeserializer;
import yapily.sdk.client.ValueMap;
import yapily.sdk.services.ApiClient;

public class HttpUserRpc extends BaseHttpRpc {

    private final ApiClient apiClient;
    private final CredentialsProvider credentialsProvider;

    private ResponseDeserializer<ApplicationUser> responseDeserializer = new ResponseDeserializer<>(ApplicationUser.class);

    private RequestSerializer requestSerializer = new RequestSerializer();

    public HttpUserRpc(ApiClient apiClient, CredentialsProvider credentialsProvider) {
        this.apiClient = apiClient;
        this.credentialsProvider = credentialsProvider;
    }

    public void put(String userUuid, ApplicationUser applicationUser) {
        requestPut(apiClient.getEndpoint(new ValueMap().withUserUuid(userUuid)), applicationUser, requestSerializer, credentialsProvider, new HeaderAppender());
    }

    public void delete(String userUuid) {
        requestDelete(apiClient.getEndpoint(new ValueMap().withUserUuid(userUuid)), credentialsProvider, new HeaderAppender());
    }

    public ApplicationUser get(String userUuid) {
        return requestGet(apiClient.getEndpoint(new ValueMap().withUserUuid(userUuid)), responseDeserializer, credentialsProvider, new HeaderAppender());
    }

}
