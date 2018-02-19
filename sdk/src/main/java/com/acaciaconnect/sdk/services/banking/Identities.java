package com.acaciaconnect.sdk.services.banking;

import com.acaciaconnect.api.client.AcaciaApi;
import com.acaciaconnect.api.client.model.banking.Identity;
import com.acaciaconnect.sdk.client.banking.HttpIdentitiesRpc;
import com.acaciaconnect.sdk.services.ApiClient;

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
