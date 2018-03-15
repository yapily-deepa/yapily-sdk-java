package yapily.sdk.client.banking;

import java.util.List;

import yapily.api.client.model.Account;
import yapily.sdk.client.BaseHttpRpc;
import yapily.sdk.client.ResponseDeserializer;
import yapily.sdk.client.SystemPropertiesCredentialsProvider;
import yapily.sdk.services.ApiClient;
import com.fasterxml.jackson.core.type.TypeReference;

public class HttpAccountsRpc extends BaseHttpRpc {

    private final ApiClient apiClient;

    ResponseDeserializer<List<Account>> responseDeserializerList = new ResponseDeserializer<>(new TypeReference<List<Account>>() {
    });
    ResponseDeserializer<Account> responseDeserializer = new ResponseDeserializer<>(Account.class);


    public HttpAccountsRpc(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<Account> listAccounts(String userUuid, String bankId) {
        return requestGet(getAccountsUrl(userUuid, bankId), responseDeserializerList, SystemPropertiesCredentialsProvider.credentialsProvider());
    }

    public Account getAccount(String userUuid, String bankId, String accountId){
        return requestGet(getAccountsUrl(userUuid, bankId), responseDeserializer, SystemPropertiesCredentialsProvider.credentialsProvider());
    }

    private String getAccountsUrl(String userUuid, String bankId) {
        return apiClient.getBaseUrl()
                        .replace("{userUuid}", userUuid)
                        .replace("{bankId}", bankId);
    }

    private String getAccountUrl(String userUuid, String bankId, String accountId){
        return getAccountsUrl(userUuid, bankId) + accountId;
    }

}
