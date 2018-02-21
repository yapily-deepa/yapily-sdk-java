package com.yapily.sdk.services.banking;

import com.yapily.api.client.AcaciaApi;
import com.yapily.api.client.models.banking.Transaction;
import com.yapily.sdk.client.banking.HttpTransactionsRpc;
import com.yapily.sdk.services.ApiClient;

import java.util.List;
import java.util.UUID;

public class Transactions extends ApiClient {

    HttpTransactionsRpc transactionsRpc = new HttpTransactionsRpc(this);

    public Transactions() {
        super(AcaciaApi.SERVICE_PATH_USERS_BANKS_ACCOUNTS_TRANSACTIONS);
    }

    public List<Transaction> listTransactions(UUID userUUID, String accountId, String bankId) {
        return transactionsRpc.getTransactions(userUUID, accountId, bankId);
    }

}
