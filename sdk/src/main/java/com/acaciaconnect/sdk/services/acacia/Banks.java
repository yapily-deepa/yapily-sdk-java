package com.acaciaconnect.sdk.services.acacia;

import java.util.List;

import com.acaciaconnect.api.client.AcaciaApi;
import com.acaciaconnect.api.client.model.acacia.Bank;
import com.acaciaconnect.sdk.client.acacia.HttpBanksRpc;
import com.acaciaconnect.sdk.services.ApiClient;

public class Banks extends ApiClient {

    HttpBanksRpc banksRpc = new HttpBanksRpc(this);

    public Banks() {
        super(AcaciaApi.SERVICE_PATH_BANKS);
    }

    public Banks(String rootUrl) {
        super(rootUrl, AcaciaApi.SERVICE_PATH_BANKS);
    }

    public List<Bank> listBanks() {
        return banksRpc.listBanks();
    }

}
