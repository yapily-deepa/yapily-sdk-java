package yapily.sdk.client.institutions;

import java.util.List;

import org.apache.http.client.CredentialsProvider;

import com.fasterxml.jackson.core.type.TypeReference;

import yapily.api.client.model.Account;
import yapily.sdk.client.BaseHttpRpc;
import yapily.sdk.client.HeaderAppender;
import yapily.sdk.client.ResponseDeserializer;
import yapily.sdk.services.ApiClient;

public class HttpAccountsRpc extends BaseHttpRpc {

    private final ApiClient apiClient;
    private final CredentialsProvider credentialsProvider;
    private final HeaderAppender headerAppender;

    private ResponseDeserializer<List<Account>> responseDeserializerList = new ResponseDeserializer<>(new TypeReference<List<Account>>() {
    });

    public HttpAccountsRpc(ApiClient apiClient, CredentialsProvider credentialsProvider, HeaderAppender headerAppender) {
        this.apiClient = apiClient;
        this.credentialsProvider = credentialsProvider;
        this.headerAppender = headerAppender;
    }

    public List<Account> listAccounts() {
        return requestGet(apiClient.getEndpoint(), responseDeserializerList, credentialsProvider, headerAppender);
    }

}
