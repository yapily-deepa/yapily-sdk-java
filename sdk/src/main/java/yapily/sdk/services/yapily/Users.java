package yapily.sdk.services.yapily;


import yapily.sdk.YapilyApi;
import yapily.api.client.model.ApplicationUser;
import yapily.sdk.client.acacia.HttpUsersRpc;
import yapily.sdk.services.ApiClient;

import java.util.List;
import java.util.UUID;

public class Users extends ApiClient {

    HttpUsersRpc rpc = new HttpUsersRpc(this);

    public Users() {
        super(YapilyApi.SERVICE_PATH_USERS);
    }

    public Users(String rootUrl) {
        super(rootUrl, YapilyApi.SERVICE_PATH_USERS);
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
