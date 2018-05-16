package yapily.sdk.services.institutions;

import java.util.List;

import yapily.api.client.model.Transaction;
import yapily.sdk.YapilyApi;
import yapily.sdk.client.HeaderAppender;
import yapily.sdk.client.institutions.HttpTransactionsRpc;
import yapily.sdk.credential.YapilyCredentials;
import yapily.sdk.services.ApiClient;

public class TransactionsApi {

    private final HttpTransactionsRpc transactionsRpc;

    private TransactionsApi(YapilyCredentials yapilyCredentials, HeaderAppender headerAppender) {
        transactionsRpc = new HttpTransactionsRpc(new ApiClient(YapilyApi.SERVICE_PATH_ACCOUNT_TRANSACTIONS), yapilyCredentials.toCredentialsProvider(), headerAppender);
    }

    public List<Transaction> listTransactions(String accountId) {
        return transactionsRpc.getTransactions(accountId);
    }

    public static class Builder extends InstitutionServiceBuilder<TransactionsApi> {

        @Override
        public TransactionsApi build() {
            final YapilyCredentials yapilyCredentials = createCredentials();
            final HeaderAppender headerAppender = createHeaderAppender();
            return new TransactionsApi(yapilyCredentials, headerAppender);
        }
    }
}
