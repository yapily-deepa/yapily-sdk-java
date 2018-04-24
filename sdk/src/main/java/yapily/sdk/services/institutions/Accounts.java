package yapily.sdk.services.institutions;

import java.util.List;

import yapily.api.client.model.Account;
import yapily.sdk.YapilyApi;
import yapily.sdk.YapilyCredentials;
import yapily.sdk.client.institutions.HttpAccountsRpc;
import yapily.sdk.services.ApiClient;

public class Accounts extends ApiClient {

    private final HttpAccountsRpc accountsRpc;

    public Accounts() {
        super(YapilyApi.SERVICE_PATH_USERS_INSTITUTIONS_ACCOUNTS);
        accountsRpc =  new HttpAccountsRpc(this);
    }

    public Accounts(YapilyCredentials yapilyCredentials) {
        super(YapilyApi.SERVICE_PATH_USERS_INSTITUTIONS_ACCOUNTS);
        accountsRpc =  new HttpAccountsRpc(this, yapilyCredentials.toCredentialsProvider());
    }

    public Account getAccount(String userUuid, String institutionId, String accountId) {
        return accountsRpc.getAccount(userUuid, institutionId, accountId);
    }

    public List<Account> listAccounts(String userUuid, String institutionId) {
        return accountsRpc.listAccounts(userUuid, institutionId);
    }

}
