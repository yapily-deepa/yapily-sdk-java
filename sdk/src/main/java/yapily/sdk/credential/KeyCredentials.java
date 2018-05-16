package yapily.sdk.credential;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;

public class KeyCredentials implements YapilyCredentials {

    private final String key;
    private final String secret;

    public KeyCredentials(String key, String secret) {
        this.key = key;
        this.secret = secret;
    }

    @Override
    public CredentialsProvider toCredentialsProvider() {
        BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
        basicCredentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(key, secret));
        return basicCredentialsProvider;
    }
}
