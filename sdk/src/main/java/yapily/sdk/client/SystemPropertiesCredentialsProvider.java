package yapily.sdk.client;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;

import yapily.sdk.YapilyApi;
import com.google.common.base.Strings;

public class SystemPropertiesCredentialsProvider extends BasicCredentialsProvider {

    private static SystemPropertiesCredentialsProvider INSTANCE;

    SystemPropertiesCredentialsProvider() {
        if (Strings.isNullOrEmpty(System.getProperty(YapilyApi.API_APPLICATION_ID_ENV_NAME)) || Strings.isNullOrEmpty(System.getProperty(YapilyApi.API_APPLICATION_SECRET_ENV_NAME))) {
            System.out.println("Credentials not set with system properties: " + YapilyApi.API_APPLICATION_ID_ENV_NAME + ", " + YapilyApi.API_APPLICATION_SECRET_ENV_NAME);
            System.out.println("checking for environment variables...");
            if(Strings.isNullOrEmpty(System.getenv(YapilyApi.API_APPLICATION_ID_ENV_NAME)) || Strings.isNullOrEmpty(System.getenv(YapilyApi.API_APPLICATION_SECRET_ENV_NAME))) {
                throw new RuntimeException("Credentials not set with environment variables: " + YapilyApi.API_APPLICATION_ID_ENV_NAME + ", " + YapilyApi.API_APPLICATION_SECRET_ENV_NAME);
            }
        }
        final UsernamePasswordCredentials creds = new UsernamePasswordCredentials(System.getProperty(YapilyApi.API_APPLICATION_ID_ENV_NAME),
                                                                                  System.getProperty(YapilyApi.API_APPLICATION_SECRET_ENV_NAME));
        setCredentials(AuthScope.ANY, creds);
    }

    synchronized public static SystemPropertiesCredentialsProvider credentialsProvider() {
        if(INSTANCE==null){
            INSTANCE = new SystemPropertiesCredentialsProvider();
        }
        return INSTANCE;
    }

}
