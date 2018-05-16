package yapily.sdk.credential;

import org.apache.http.client.CredentialsProvider;

public interface YapilyCredentials {

    CredentialsProvider toCredentialsProvider();

}
