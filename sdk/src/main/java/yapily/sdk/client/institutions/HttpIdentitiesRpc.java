package yapily.sdk.client.institutions;

import org.apache.http.client.CredentialsProvider;

import yapily.api.client.model.Identity;
import yapily.sdk.client.BaseHttpRpc;
import yapily.sdk.client.HeaderAppender;
import yapily.sdk.client.ResponseDeserializer;
import yapily.sdk.services.ApiClient;

public class HttpIdentitiesRpc extends BaseHttpRpc {

    private final ApiClient apiClient;
    private final HeaderAppender headerAppender;
    private final CredentialsProvider credentialsProvider;

    private ResponseDeserializer<Identity> responseDeserializer = new ResponseDeserializer<>(Identity.class);

    public HttpIdentitiesRpc(ApiClient apiClient, CredentialsProvider credentialsProvider, HeaderAppender headerAppender) {
        this.apiClient = apiClient;
        this.headerAppender = headerAppender;
        this.credentialsProvider = credentialsProvider;
    }

    public Identity getIdentity() {
        return requestGet(apiClient.getEndpoint(), responseDeserializer, credentialsProvider, headerAppender);
    }

}
