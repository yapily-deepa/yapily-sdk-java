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

public class HttpConsentRpc extends BaseHttpRpc {

    private final ApiClient apiClient;
    private final CredentialsProvider credentialsProvider;

    private RequestSerializer requestSerializer = new RequestSerializer();

    private ResponseDeserializer<List<Consent>> consentResponseDeserializer = new ResponseDeserializer<>(new TypeReference<List<Consent>>() {
    });

    private ResponseDeserializer<String> stringResponseDeserializer = new ResponseDeserializer<>(new TypeReference<String>() {
    });

    public HttpConsentRpc(ApiClient apiClient, CredentialsProvider credentialsProvider) {
        this.apiClient = apiClient;
        this.credentialsProvider = credentialsProvider;
    }

    public void delete(String userUuid, String consentToken) {
        requestDelete(apiClient.getEndpoint(new ValueMap().withUserUuid(userUuid).withConsentToken(consentToken)), credentialsProvider, new HeaderAppender());
    }

}
