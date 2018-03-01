package yapily.sdk.services.banking;

import yapily.sdk.AcaciaApi;
import yapily.api.client.model.Transaction;
import yapily.sdk.client.banking.HttpTransactionsRpc;
import yapily.sdk.services.ApiClient;

import java.util.List;

public class Transactions extends ApiClient {

    HttpTransactionsRpc transactionsRpc = new HttpTransactionsRpc(this);

    public Transactions() {
        super(AcaciaApi.SERVICE_PATH_USERS_BANKS_ACCOUNTS_TRANSACTIONS);
    }

    public List<Transaction> listTransactions(String userUUID, String accountId, String bankId) {
        return transactionsRpc.getTransactions(userUUID, accountId, bankId);
    }

}
