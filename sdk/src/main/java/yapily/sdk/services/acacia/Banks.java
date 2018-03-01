package yapily.sdk.services.acacia;

import java.util.List;

import yapily.sdk.AcaciaApi;
import yapily.api.client.model.Bank;
import yapily.sdk.client.acacia.HttpBanksRpc;
import yapily.sdk.services.ApiClient;

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
