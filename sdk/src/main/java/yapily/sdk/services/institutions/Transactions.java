package yapily.sdk.services.institutions;

import java.util.List;

import yapily.api.client.model.Transaction;
import yapily.sdk.YapilyApi;
import yapily.sdk.YapilyCredentials;
import yapily.sdk.client.institutions.HttpTransactionsRpc;
import yapily.sdk.services.ApiClient;

public class Transactions extends ApiClient {

    private final HttpTransactionsRpc transactionsRpc;

    public Transactions() {
        super(YapilyApi.SERVICE_PATH_USERS_INSTITUTIONS_ACCOUNTS_TRANSACTIONS);
        transactionsRpc =  new HttpTransactionsRpc(this);
    }

    public Transactions(YapilyCredentials yapilyCredentials) {
        super(YapilyApi.SERVICE_PATH_USERS_INSTITUTIONS_ACCOUNTS_TRANSACTIONS);
        transactionsRpc =  new HttpTransactionsRpc(this, yapilyCredentials.toCredentialsProvider());
    }

    public List<Transaction> listTransactions(String userUUID, String accountId, String institutionId) {
        return transactionsRpc.getTransactions(userUUID, accountId, institutionId);
    }

}
