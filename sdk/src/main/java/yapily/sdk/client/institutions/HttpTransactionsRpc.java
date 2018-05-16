package yapily.sdk.client.institutions;

import java.util.List;

import org.apache.http.client.CredentialsProvider;

import com.fasterxml.jackson.core.type.TypeReference;

import yapily.api.client.model.Transaction;
import yapily.sdk.client.BaseHttpRpc;
import yapily.sdk.client.HeaderAppender;
import yapily.sdk.client.ResponseDeserializer;
import yapily.sdk.client.ValueMap;
import yapily.sdk.services.ApiClient;

public class HttpTransactionsRpc extends BaseHttpRpc {

    private final ApiClient apiClient;
    private final HeaderAppender headerAppender;
    private final CredentialsProvider basicCredentialsProvider;

    private ResponseDeserializer<List<Transaction>> responseDeserializerList = new ResponseDeserializer<>(new TypeReference<List<Transaction>>() {
    });

    public HttpTransactionsRpc(ApiClient apiClient, CredentialsProvider credentialsProvider, HeaderAppender headerAppender) {
        this.apiClient = apiClient;
        this.headerAppender = headerAppender;
        this.basicCredentialsProvider = credentialsProvider;
    }

    public List<Transaction> getTransactions(String accountId) {
        return requestGet(apiClient.getEndpoint(new ValueMap().withAccountId(accountId)), responseDeserializerList, basicCredentialsProvider, headerAppender);
    }

}
