package yapily.sdk.services.yapily;

import yapily.sdk.credential.KeyCredentials;
import yapily.sdk.credential.SystemPropertiesCredentialsProvider;
import yapily.sdk.credential.YapilyCredentials;

public abstract class ApplicationServiceBuilder<T> {

    private String key;
    private String secret;

    public ApplicationServiceBuilder<T> standard() {
        return this;
    }

    public ApplicationServiceBuilder<T> withApplicationCredentials(String key, String secret) {
        this.key = key;
        this.secret = secret;
        return this;
    }

    protected YapilyCredentials createCredentials() {

        final YapilyCredentials yapilyCredentials;

        if (key != null && secret != null) {
            yapilyCredentials = new KeyCredentials(key, secret);
        } else {
            yapilyCredentials = SystemPropertiesCredentialsProvider.credentialsProvider();
        }

        return yapilyCredentials;
    }

    public abstract T build();

}
