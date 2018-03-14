package yapily.sdk.services.yapily;


import yapily.sdk.YapilyApi;
import yapily.api.client.model.ApplicationUser;
import yapily.sdk.client.yapily.HttpUsersRpc;
import yapily.sdk.services.ApiClient;

import java.util.List;
import java.util.UUID;

public class Users extends ApiClient {

    private HttpUsersRpc rpc = new HttpUsersRpc(this);

    public Users() {
        super(YapilyApi.SERVICE_PATH_USERS);
    }

    public Users(String rootUrl) {
        super(rootUrl, YapilyApi.SERVICE_PATH_USERS);
    }

    public ApplicationUser getUser(String uuid) {
        return rpc.getUser(uuid);
    }

    public List<ApplicationUser> listUsers() {
        return rpc.listUsers();
    }

    public ApplicationUser createUser(String appUserId) {
        return rpc.postUser(appUserId);
    }

    public void updateUser(UUID applicationUuid, ApplicationUser applicationUser) {
        rpc.putUser(applicationUser.getUuid(), applicationUser);
    }

}
