package yapily.sdk.client.yapily;

import java.util.List;

import org.apache.http.client.CredentialsProvider;

import com.fasterxml.jackson.core.type.TypeReference;

import yapily.api.client.model.Consent;
import yapily.sdk.client.BaseHttpRpc;
import yapily.sdk.client.HeaderAppender;
import yapily.sdk.client.RequestSerializer;
import yapily.sdk.client.ResponseDeserializer;
import yapily.sdk.client.ValueMap;
import yapily.sdk.services.ApiClient;

public class HttpInstitutionConsentsRpc extends BaseHttpRpc {

    private final ApiClient apiClient;
    private final CredentialsProvider credentialsProvider;

    private RequestSerializer requestSerializer = new RequestSerializer();

    private ResponseDeserializer<List<Consent>> consentResponseDeserializer = new ResponseDeserializer<>(new TypeReference<List<Consent>>() {
    });

    private ResponseDeserializer<String> stringResponseDeserializer = new ResponseDeserializer<>(new TypeReference<String>() {
    });

    public HttpInstitutionConsentsRpc(ApiClient apiClient, CredentialsProvider credentialsProvider) {
        this.apiClient = apiClient;
        this.credentialsProvider = credentialsProvider;
    }

    public List<Consent> list(String userUuid, String institutionId) {

        return requestGet(apiClient.getEndpoint(new ValueMap().withUserUuid(userUuid).withInstitutionId(institutionId)),
                          consentResponseDeserializer,
                          credentialsProvider,
                          new HeaderAppender());
    }

}
