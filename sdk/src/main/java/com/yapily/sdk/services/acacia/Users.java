package com.yapily.sdk.services.acacia;


import com.yapily.sdk.AcaciaApi;
import com.yapily.api.client.model.ApplicationUser;
import com.yapily.sdk.client.acacia.HttpUsersRpc;
import com.yapily.sdk.services.ApiClient;

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
        return rpc.listUsers();
    }

    public ApplicationUser createUser(String appUserId) {
        return rpc.postUser(appUserId);
    }

    public void updateUser(UUID applicationUuid, ApplicationUser applicationUser) {
        rpc.putUser(applicationUser.getUuid(), applicationUser);
    }

}
