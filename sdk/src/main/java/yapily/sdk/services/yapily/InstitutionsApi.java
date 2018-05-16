package yapily.sdk.services.yapily;

import java.util.List;

import yapily.api.client.model.Institution;
import yapily.sdk.YapilyApi;
import yapily.sdk.client.yapily.HttpInstitutionRpc;
import yapily.sdk.client.yapily.HttpInstitutionsRpc;
import yapily.sdk.credential.YapilyCredentials;
import yapily.sdk.services.ApiClient;

public class InstitutionsApi {

    private final HttpInstitutionRpc institutionRpc;
    private final HttpInstitutionsRpc institutionsRpc;

    private InstitutionsApi(YapilyCredentials yapilyCredentials) {
        institutionRpc = new HttpInstitutionRpc(new ApiClient(YapilyApi.SERVICE_PATH_INSTITUTION), yapilyCredentials.toCredentialsProvider());
        institutionsRpc = new HttpInstitutionsRpc(new ApiClient(YapilyApi.SERVICE_PATH_INSTITUTIONS), yapilyCredentials.toCredentialsProvider());
    }

    public Institution getInstitution(String institutionId) {
        return institutionRpc.getInstitution(institutionId);
    }

    public List<Institution> listInstitutions() {
        return institutionsRpc.listInstitutions();
    }

    public static class Builder extends ApplicationServiceBuilder<InstitutionsApi> {

        @Override
        public InstitutionsApi build() {
            final YapilyCredentials yapilyCredentials = createCredentials();
            return new InstitutionsApi(yapilyCredentials);
        }
    }

}
