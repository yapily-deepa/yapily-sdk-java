package com.yapily.sdk.services.banking;

import java.util.List;
import java.util.UUID;

import com.yapily.api.client.AcaciaApi;
import com.yapily.api.client.models.banking.Account;
import com.yapily.sdk.client.banking.HttpAccountsRpc;
import com.yapily.sdk.services.ApiClient;

public class Accounts extends ApiClient {

    HttpAccountsRpc accountsRpc = new HttpAccountsRpc(this);

    public Accounts() {
        super(AcaciaApi.SERVICE_PATH_USERS_BANKS_ACCOUNTS);
    }

    public List<Account> listAccounts(UUID userUuid, String bankId) {
        return accountsRpc.listAccounts(userUuid, bankId);
    }

}