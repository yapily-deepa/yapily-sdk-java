package yapily.sdk.services.yapily;

import java.util.List;
import java.util.UUID;

import org.apache.http.client.CredentialsProvider;

import yapily.api.client.model.ApplicationUser;
import yapily.api.client.model.Consent;
import yapily.api.client.model.CreateConsentApiKey;
import yapily.sdk.YapilyApi;
import yapily.sdk.client.yapily.HttpConsentRpc;
import yapily.sdk.client.yapily.HttpConsentsRpc;
import yapily.sdk.client.yapily.HttpInstitutionConsentsRpc;
import yapily.sdk.client.yapily.HttpUserRpc;
import yapily.sdk.client.yapily.HttpUsersRpc;
import yapily.sdk.credential.YapilyCredentials;
import yapily.sdk.services.ApiClient;

public class UsersApi {

    private final HttpUserRpc userRpc;
    private final HttpUsersRpc usersRpc;
    private final HttpConsentsRpc consentsRpc;
    private final HttpConsentRpc consentRpc;
    private final HttpInstitutionConsentsRpc institutionConsentsRpc;

    private UsersApi(YapilyCredentials yapilyCredentials) {
        CredentialsProvider credentialsProvider = yapilyCredentials.toCredentialsProvider();
        userRpc = new HttpUserRpc(new ApiClient(YapilyApi.SERVICE_PATH_USER), credentialsProvider);
        usersRpc = new HttpUsersRpc(new ApiClient(YapilyApi.SERVICE_PATH_USERS), credentialsProvider);
        consentRpc = new HttpConsentRpc(new ApiClient(YapilyApi.SERVICE_PATH_USER_CONSENT), credentialsProvider);
        consentsRpc = new HttpConsentsRpc(new ApiClient(YapilyApi.SERVICE_PATH_USER_CONSENTS), credentialsProvider);
        institutionConsentsRpc = new HttpInstitutionConsentsRpc(new ApiClient(YapilyApi.SERVICE_PATH_INSTITUTION_USER_CONSENTS), credentialsProvider);
    }

    public ApplicationUser getUser(String userUuid) {
        return userRpc.get(userUuid);
    }

    public List<Consent> listConsents(String userUuid) {
        return consentsRpc.list(userUuid);
    }

    public List<Consent> listConsentsForInstitution(String userUuid, String institutionId) {
        return institutionConsentsRpc.list(userUuid, institutionId);
    }

    public Consent createConsent(String userUuid, CreateConsentApiKey createConsentApiKey) {
        return consentsRpc.create(userUuid, createConsentApiKey);
    }

    public void deleteConsent(String userUuid, String consentToken) {
        consentRpc.delete(userUuid, consentToken);
    }

    public List<ApplicationUser> listUsers() {
        return usersRpc.list();
    }

    public ApplicationUser createUser(ApplicationUser appUser) {
        return usersRpc.post(appUser);
    }

    public void updateUser(UUID applicationUuid, ApplicationUser applicationUser) {
        userRpc.put(applicationUser.getUuid(), applicationUser);
    }

    public static class Builder extends ApplicationServiceBuilder<UsersApi> {

        @Override
        public UsersApi build() {
            final YapilyCredentials yapilyCredentials = createCredentials();
            return new UsersApi(yapilyCredentials);
        }
    }

}
