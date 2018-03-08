package yapily.sdk.services.acacia;

import java.util.List;

import yapily.sdk.YapilyApi;
import yapily.api.client.model.Bank;
import yapily.sdk.client.acacia.HttpBanksRpc;
import yapily.sdk.services.ApiClient;

public class Banks extends ApiClient {

    HttpBanksRpc banksRpc = new HttpBanksRpc(this);

    public Banks() {
        super(YapilyApi.SERVICE_PATH_BANKS);
    }

    public Banks(String rootUrl) {
        super(rootUrl, YapilyApi.SERVICE_PATH_BANKS);
    }

    public List<Bank> listBanks() {
        return banksRpc.listBanks();
    }

}
