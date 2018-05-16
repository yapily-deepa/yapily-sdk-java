package yapily.sdk.services.institutions;

import yapily.sdk.client.HeaderAppender;
import yapily.sdk.credential.KeyCredentials;
import yapily.sdk.credential.SystemPropertiesCredentialsProvider;
import yapily.sdk.credential.YapilyCredentials;

public abstract class InstitutionServiceBuilder<T> {

    private static final String CONSENT_HEADER = "CONSENT";
    private String key;
    private String secret;
    private String consentToken;

    public InstitutionServiceBuilder<T> standard() {
        return this;
    }

    public InstitutionServiceBuilder<T> withApplicationCredentials(String key, String secret) {
        this.key = key;
        this.secret = secret;
        return this;
    }

    public InstitutionServiceBuilder<T> withConsentToken(String consentToken) {
        this.consentToken = consentToken;
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

    protected HeaderAppender createHeaderAppender() {
        HeaderAppender headerAppender = new HeaderAppender();
        if (consentToken != null) {
            headerAppender.addHeader(CONSENT_HEADER, consentToken);
        }
        return headerAppender;
    }

    public abstract T build();

}
