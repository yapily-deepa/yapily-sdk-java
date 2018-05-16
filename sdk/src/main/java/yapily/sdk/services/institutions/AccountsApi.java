package yapily.sdk.services.institutions;

import java.util.List;

import yapily.api.client.model.Account;
import yapily.sdk.YapilyApi;
import yapily.sdk.client.HeaderAppender;
import yapily.sdk.client.institutions.HttpAccountRpc;
import yapily.sdk.client.institutions.HttpAccountsRpc;
import yapily.sdk.credential.YapilyCredentials;
import yapily.sdk.services.ApiClient;

public class AccountsApi {

    private final HttpAccountsRpc accountsRpc;
    private final HttpAccountRpc accountRpc;

    private AccountsApi(YapilyCredentials yapilyCredentials, HeaderAppender headerAppender) {
        accountRpc = new HttpAccountRpc(new ApiClient(YapilyApi.SERVICE_PATH_ACCOUNT), yapilyCredentials.toCredentialsProvider(), headerAppender);
        accountsRpc = new HttpAccountsRpc(new ApiClient(YapilyApi.SERVICE_PATH_ACCOUNTS), yapilyCredentials.toCredentialsProvider(), headerAppender);
    }

    public Account getAccount(String accountId) {
        return accountRpc.getAccount(accountId);
    }

    public List<Account> listAccounts() {
        return accountsRpc.listAccounts();
    }

    public static class Builder extends InstitutionServiceBuilder<AccountsApi> {

        @Override
        public AccountsApi build() {
            final YapilyCredentials yapilyCredentials = createCredentials();
            final HeaderAppender headerAppender = createHeaderAppender();
            return new AccountsApi(yapilyCredentials, headerAppender);
        }
    }

}
