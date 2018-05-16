package yapily.sdk.client.yapily;

import java.util.List;

import org.apache.http.client.CredentialsProvider;

import com.fasterxml.jackson.core.type.TypeReference;

import yapily.api.client.model.Consent;
import yapily.api.client.model.CreateConsentApiKey;
import yapily.sdk.client.BaseHttpRpc;
import yapily.sdk.client.HeaderAppender;
import yapily.sdk.client.RequestSerializer;
import yapily.sdk.client.ResponseDeserializer;
import yapily.sdk.client.ValueMap;
import yapily.sdk.services.ApiClient;

public class HttpConsentsRpc extends BaseHttpRpc {

    private final ApiClient apiClient;
    private final CredentialsProvider credentialsProvider;

    private ResponseDeserializer<List<Consent>> consentResponseDeserializer = new ResponseDeserializer<>(new TypeReference<List<Consent>>() {
    });

    private ResponseDeserializer<Consent> consentCreateResponseDeserializer = new ResponseDeserializer<>(new TypeReference<Consent>() {
    });

    private RequestSerializer requestSerializer = new RequestSerializer();

    public HttpConsentsRpc(ApiClient apiClient, CredentialsProvider credentialsProvider) {
        this.apiClient = apiClient;
        this.credentialsProvider = credentialsProvider;
    }

    public List<Consent> list(String userUuid) {
        return requestGet(apiClient.getEndpoint(new ValueMap().withUserUuid(userUuid)), consentResponseDeserializer, credentialsProvider, new HeaderAppender());
    }

    public List<Consent> list(String userUuid, String institutionId) {
        return requestGet(apiClient.getEndpoint(new ValueMap().withUserUuid(userUuid).withInstitutionId(institutionId)),
                          consentResponseDeserializer,
                          credentialsProvider,
                          new HeaderAppender());
    }

    public Consent create(String userUuid, CreateConsentApiKey createConsentApiKey) {
        return requestPost(apiClient.getEndpoint(new ValueMap().withUserUuid(userUuid)), createConsentApiKey, requestSerializer, consentCreateResponseDeserializer, credentialsProvider,
                           new HeaderAppender());
    }

}
