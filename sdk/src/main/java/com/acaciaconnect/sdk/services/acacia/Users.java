package com.acaciaconnect.sdk.services.acacia;

import com.acaciaconnect.api.client.AcaciaApi;
import com.acaciaconnect.api.client.model.acacia.ApplicationUser;
import com.acaciaconnect.sdk.client.acacia.HttpUsersRpc;
import com.acaciaconnect.sdk.services.ApiClient;

import java.util.List;
import java.util.UUID;

public class Users extends ApiClient {

    HttpUsersRpc rpc = new HttpUsersRpc(this);

    public Users() {
        super(AcaciaApi.SERVICE_PATH_USERS);
    }

    public Users(String rootUrl) {
        super(rootUrl, AcaciaApi.SERVICE_PATH_USERS);
    }

    public List<ApplicationUser> listUsers(UUID applicationUuid) {
        return rpc.listUsers(applicationUuid);
    }

    public ApplicationUser createUser(String appUserId) {
        return rpc.postUser(appUserId);
    }

    public void updateUser(UUID applicationUuid, ApplicationUser applicationUser) {
        rpc.putUser(applicationUuid, applicationUser.getUuid(), applicationUser);
    }

}
