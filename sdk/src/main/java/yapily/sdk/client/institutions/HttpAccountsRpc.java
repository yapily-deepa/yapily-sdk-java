package yapily.sdk.client.institutions;

import java.util.List;

import org.apache.http.impl.client.BasicCredentialsProvider;

import com.fasterxml.jackson.core.type.TypeReference;

import yapily.api.client.model.Account;
import yapily.sdk.client.BaseHttpRpc;
import yapily.sdk.client.ResponseDeserializer;
import yapily.sdk.client.SystemPropertiesCredentialsProvider;
import yapily.sdk.services.ApiClient;

public class HttpAccountsRpc extends BaseHttpRpc {

    private final ApiClient apiClient;
    private final BasicCredentialsProvider basicCredentialsProvider;

    ResponseDeserializer<List<Account>> responseDeserializerList = new ResponseDeserializer<>(new TypeReference<List<Account>>() {});
    ResponseDeserializer<Account> responseDeserializer = new ResponseDeserializer<>(Account.class);

    public HttpAccountsRpc(ApiClient apiClient) {
        this.apiClient = apiClient;
        basicCredentialsProvider = SystemPropertiesCredentialsProvider.credentialsProvider();
    }

    public HttpAccountsRpc(ApiClient apiClient, BasicCredentialsProvider basicCredentialsProvider) {
        this.apiClient = apiClient;
        this.basicCredentialsProvider = basicCredentialsProvider;
    }

    public List<Account> listAccounts(String userUuid, String institutionId) {
        return requestGet(getAccountsUrl(userUuid, institutionId), responseDeserializerList, basicCredentialsProvider);
    }

    public Account getAccount(String userUuid, String institutionId, String accountId) {
        return requestGet(getSingleAccountUrl(userUuid, institutionId, accountId), responseDeserializer, basicCredentialsProvider);
    }

    private String getAccountsUrl(String userUuid, String institutionId) {
        return apiClient.getBaseUrl()
                        .replace("{userUuid}", userUuid)
                        .replace("{institutionId}", institutionId);
    }

    private String getSingleAccountUrl(String userUuid, String institutionId, String accountId) {
        return getAccountsUrl(userUuid, institutionId) + accountId;
    }

}
