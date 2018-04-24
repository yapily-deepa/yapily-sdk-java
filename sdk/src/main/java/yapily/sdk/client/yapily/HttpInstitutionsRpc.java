package yapily.sdk.client.yapily;

import java.util.List;

import org.apache.http.impl.client.BasicCredentialsProvider;

import com.fasterxml.jackson.core.type.TypeReference;

import yapily.api.client.model.Institution;
import yapily.sdk.client.BaseHttpRpc;
import yapily.sdk.client.RequestSerializer;
import yapily.sdk.client.ResponseDeserializer;
import yapily.sdk.client.SystemPropertiesCredentialsProvider;
import yapily.sdk.services.ApiClient;

public class HttpInstitutionsRpc extends BaseHttpRpc {

    private final ApiClient apiClient;
    private final BasicCredentialsProvider basicCredentialsProvider;

    private ResponseDeserializer<List<Institution>> responseDeserializer = new ResponseDeserializer<>(new TypeReference<List<Institution>>() {});

    RequestSerializer requestSerializer = new RequestSerializer();

    public HttpInstitutionsRpc(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.basicCredentialsProvider = SystemPropertiesCredentialsProvider.credentialsProvider();
    }

    public HttpInstitutionsRpc(ApiClient apiClient,BasicCredentialsProvider basicCredentialsProvider) {
        this.apiClient = apiClient;
        this.basicCredentialsProvider = basicCredentialsProvider;
    }

    public List<Institution> listInstitutions() {
        return requestGet(apiClient.getBaseUrl(), responseDeserializer, basicCredentialsProvider);
    }

}
