package yapily.sdk.services.banking;

import java.util.List;

import yapily.sdk.AcaciaApi;
import yapily.api.client.model.Account;
import yapily.sdk.client.banking.HttpAccountsRpc;
import yapily.sdk.services.ApiClient;

public class Accounts extends ApiClient {

    HttpAccountsRpc accountsRpc = new HttpAccountsRpc(this);

    public Accounts() {
        super(AcaciaApi.SERVICE_PATH_USERS_BANKS_ACCOUNTS);
    }

    public List<Account> listAccounts(String userUuid, String bankId) {
        return accountsRpc.listAccounts(userUuid, bankId);
    }

}
