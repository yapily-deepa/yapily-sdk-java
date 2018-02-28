package com.yapily.sdk.services.banking;

import com.yapily.sdk.AcaciaApi;
import com.yapily.api.client.model.Identity;
import com.yapily.sdk.client.banking.HttpIdentitiesRpc;
import com.yapily.sdk.services.ApiClient;

import java.util.UUID;

public class Identities extends ApiClient {

    HttpIdentitiesRpc identityRpc = new HttpIdentitiesRpc(this);

    public Identities() {
        super(AcaciaApi.SERVICE_PATH_USERS_BANKS_IDENTITY);
    }

    public Identity getIdentity(String userUUID, String bankId) {
        return identityRpc.getIdentity(userUUID, bankId);
    }

}
