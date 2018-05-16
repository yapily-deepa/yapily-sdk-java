package yapily.sdk.client.institutions;

import org.apache.http.client.CredentialsProvider;

import yapily.api.client.model.Account;
import yapily.sdk.client.BaseHttpRpc;
import yapily.sdk.client.HeaderAppender;
import yapily.sdk.client.ResponseDeserializer;
import yapily.sdk.client.ValueMap;
import yapily.sdk.services.ApiClient;

public class HttpAccountRpc extends BaseHttpRpc {

    private final ApiClient apiClient;
    private final CredentialsProvider credentialsProvider;
    private final HeaderAppender headerAppender;

    private ResponseDeserializer<Account> responseDeserializer = new ResponseDeserializer<>(Account.class);

    public HttpAccountRpc(ApiClient apiClient, CredentialsProvider credentialsProvider, HeaderAppender headerAppender) {
        this.apiClient = apiClient;
        this.credentialsProvider = credentialsProvider;
        this.headerAppender = headerAppender;
    }

    public Account getAccount(String accountId) {
        return requestGet(apiClient.getEndpoint(new ValueMap().withAccountId(accountId)), responseDeserializer, credentialsProvider, headerAppender);
    }

}
