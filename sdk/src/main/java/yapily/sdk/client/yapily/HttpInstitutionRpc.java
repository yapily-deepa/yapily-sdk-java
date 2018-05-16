package yapily.sdk.client.yapily;

import org.apache.http.client.CredentialsProvider;

import yapily.api.client.model.Institution;
import yapily.sdk.client.BaseHttpRpc;
import yapily.sdk.client.HeaderAppender;
import yapily.sdk.client.ResponseDeserializer;
import yapily.sdk.client.ValueMap;
import yapily.sdk.services.ApiClient;

public class HttpInstitutionRpc extends BaseHttpRpc {

    private final ApiClient apiClient;
    private final CredentialsProvider credentialsProvider;

    private ResponseDeserializer<Institution> responseDeserializer = new ResponseDeserializer<>(Institution.class);

    public HttpInstitutionRpc(ApiClient apiClient, CredentialsProvider credentialsProvider) {
        this.apiClient = apiClient;
        this.credentialsProvider = credentialsProvider;
    }

    public Institution getInstitution(String institutionId) {
        return requestGet(apiClient.getEndpoint(new ValueMap().withInstitutionId(institutionId)), responseDeserializer, credentialsProvider, new HeaderAppender());
    }

}
