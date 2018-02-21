package com.yapily.sdk.services.acacia;

import java.util.List;

import com.yapily.api.client.AcaciaApi;
import com.yapily.api.client.models.acacia.Bank;
import com.yapily.sdk.client.acacia.HttpBanksRpc;
import com.yapily.sdk.services.ApiClient;

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
