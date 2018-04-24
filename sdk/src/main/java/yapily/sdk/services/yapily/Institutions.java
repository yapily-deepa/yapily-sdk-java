package yapily.sdk.services.yapily;

import java.util.List;

import yapily.api.client.model.Institution;
import yapily.sdk.YapilyApi;
import yapily.sdk.YapilyCredentials;
import yapily.sdk.client.yapily.HttpInstitutionsRpc;
import yapily.sdk.services.ApiClient;

public class Institutions extends ApiClient {

    private final HttpInstitutionsRpc institutionsRpc;

    public Institutions() {
        super(YapilyApi.SERVICE_PATH_INSTITUTIONS);
        institutionsRpc = new HttpInstitutionsRpc(this);
    }

    public Institutions(YapilyCredentials yapilyCredentials) {
        super(YapilyApi.SERVICE_PATH_INSTITUTIONS);
        institutionsRpc = new HttpInstitutionsRpc(this, yapilyCredentials.toCredentialsProvider());
    }

    public Institutions(String rootUrl) {
        super(rootUrl, YapilyApi.SERVICE_PATH_INSTITUTIONS);
        institutionsRpc = new HttpInstitutionsRpc(this);
    }

    public Institutions(String rootUrl,YapilyCredentials yapilyCredentials) {
        super(rootUrl, YapilyApi.SERVICE_PATH_INSTITUTIONS);
        institutionsRpc = new HttpInstitutionsRpc(this);
    }

    public List<Institution> listInstitutions() {
        return institutionsRpc.listInstitutions();
    }

}
