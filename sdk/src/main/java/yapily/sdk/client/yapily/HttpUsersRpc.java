package yapily.sdk.client.yapily;


import java.util.List;

import org.apache.http.client.CredentialsProvider;

import com.fasterxml.jackson.core.type.TypeReference;

import yapily.api.client.model.ApplicationUser;
import yapily.sdk.client.BaseHttpRpc;
import yapily.sdk.client.HeaderAppender;
import yapily.sdk.client.RequestSerializer;
import yapily.sdk.client.ResponseDeserializer;
import yapily.sdk.services.ApiClient;

public class HttpUsersRpc extends BaseHttpRpc {

    private final ApiClient apiClient;
    private final CredentialsProvider credentialsProvider;

    private ResponseDeserializer<List<ApplicationUser>> responseDeserializerList = new ResponseDeserializer<>(new TypeReference<List<ApplicationUser>>() {
    });

    private ResponseDeserializer<ApplicationUser> responseDeserializer = new ResponseDeserializer<>(ApplicationUser.class);

    private RequestSerializer requestSerializer = new RequestSerializer();

    public HttpUsersRpc(ApiClient apiClient, CredentialsProvider credentialsProvider) {
        this.apiClient = apiClient;
        this.credentialsProvider = credentialsProvider;
    }

    public ApplicationUser post(ApplicationUser appUser) {
        return requestPost(apiClient.getEndpoint(), appUser, requestSerializer, responseDeserializer, credentialsProvider, new HeaderAppender());
    }

    public List<ApplicationUser> list() {
        return requestGet(apiClient.getEndpoint(), responseDeserializerList, credentialsProvider, new HeaderAppender());
    }

}
