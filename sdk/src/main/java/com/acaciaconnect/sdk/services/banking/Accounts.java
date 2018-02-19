package com.acaciaconnect.sdk.services.banking;

import java.util.List;
import java.util.UUID;

import com.acaciaconnect.api.client.AcaciaApi;
import com.acaciaconnect.api.client.model.banking.Account;
import com.acaciaconnect.sdk.client.banking.HttpAccountsRpc;
import com.acaciaconnect.sdk.services.ApiClient;

public class Accounts extends ApiClient {

    HttpAccountsRpc accountsRpc = new HttpAccountsRpc(this);

    public Accounts() {
        super(AcaciaApi.SERVICE_PATH_USERS_BANKS_ACCOUNTS);
    }

    public List<Account> listAccounts(UUID userUuid, String bankId) {
        return accountsRpc.listAccounts(userUuid, bankId);
    }

}
