package yapily.sdk.services.yapily;


import java.util.List;
import java.util.UUID;

import yapily.api.client.model.ApplicationUser;
import yapily.sdk.YapilyApi;
import yapily.sdk.YapilyCredentials;
import yapily.sdk.client.yapily.HttpUsersRpc;
import yapily.sdk.services.ApiClient;

public class Users extends ApiClient {

    private final HttpUsersRpc rpc;

    public Users() {
        super(YapilyApi.SERVICE_PATH_USERS);
        rpc = new HttpUsersRpc(this);
    }

    public Users(String rootUrl) {
        super(rootUrl, YapilyApi.SERVICE_PATH_USERS);
        rpc = new HttpUsersRpc(this);
    }

    public Users(YapilyCredentials yapilyCredentials) {
        super(YapilyApi.SERVICE_PATH_USERS);
        rpc = new HttpUsersRpc(this, yapilyCredentials.toCredentialsProvider());
    }

    public Users(String rootUrl,YapilyCredentials yapilyCredentials) {
        super(rootUrl, YapilyApi.SERVICE_PATH_USERS);
        rpc = new HttpUsersRpc(this, yapilyCredentials.toCredentialsProvider());
    }

    public ApplicationUser getUser(String uuid) {
        return rpc.getUser(uuid);
    }

    public List<ApplicationUser> listUsers() {
        return rpc.listUsers();
    }

    public ApplicationUser createUser(ApplicationUser appUser) {
        return rpc.postUser(appUser);
    }

    public void updateUser(UUID applicationUuid, ApplicationUser applicationUser) {
        rpc.putUser(applicationUser.getUuid(), applicationUser);
    }

}
