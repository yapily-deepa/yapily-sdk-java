package yapily.sdk.client.yapily;

import java.util.List;

import org.apache.http.client.CredentialsProvider;

import com.fasterxml.jackson.core.type.TypeReference;

import yapily.api.client.model.Institution;
import yapily.sdk.client.BaseHttpRpc;
import yapily.sdk.client.HeaderAppender;
import yapily.sdk.client.ResponseDeserializer;
import yapily.sdk.services.ApiClient;

public class HttpInstitutionsRpc extends BaseHttpRpc {

    private final ApiClient apiClient;
    private final CredentialsProvider credentialsProvider;

    private ResponseDeserializer<List<Institution>> responseDeserializer = new ResponseDeserializer<>(new TypeReference<List<Institution>>() {
    });

    public HttpInstitutionsRpc(ApiClient apiClient, CredentialsProvider credentialsProvider) {
        this.apiClient = apiClient;
        this.credentialsProvider = credentialsProvider;
    }

    public List<Institution> listInstitutions() {
        return requestGet(apiClient.getEndpoint(), responseDeserializer, credentialsProvider, new HeaderAppender());
    }

}
