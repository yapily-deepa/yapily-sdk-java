package com.yapily.sdk.services.banking;

import com.yapily.api.client.AcaciaApi;
import com.yapily.api.client.models.banking.Identity;
import com.yapily.sdk.client.banking.HttpIdentitiesRpc;
import com.yapily.sdk.services.ApiClient;

import java.util.UUID;

public class Identities extends ApiClient {

    HttpIdentitiesRpc identityRpc = new HttpIdentitiesRpc(this);

    public Identities() {
        super(AcaciaApi.SERVICE_PATH_USERS_BANKS_IDENTITY);
    }

    public Identity getIdentity(UUID userUUID, String bankId) {
        return identityRpc.getIdentity(userUUID, bankId);
    }

}
