package yapily.sdk.services.banking;

import java.util.List;

import yapily.sdk.YapilyApi;
import yapily.api.client.model.Account;
import yapily.sdk.client.banking.HttpAccountsRpc;
import yapily.sdk.services.ApiClient;

public class Accounts extends ApiClient {

    private HttpAccountsRpc accountsRpc = new HttpAccountsRpc(this);

    public Accounts() {
        super(YapilyApi.SERVICE_PATH_USERS_BANKS_ACCOUNTS);
    }

    public Account getAccount(String userUuid, String bankId, String accountId){
        return accountsRpc.getAccount(userUuid, bankId, accountId);
    }

    public List<Account> listAccounts(String userUuid, String bankId) {
        return accountsRpc.listAccounts(userUuid, bankId);
    }

}
