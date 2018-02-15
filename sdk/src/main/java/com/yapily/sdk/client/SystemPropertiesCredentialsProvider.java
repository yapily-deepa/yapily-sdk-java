package com.yapily.sdk.client;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;

import com.yapily.api.client.YapilyApi;
import com.google.common.base.Strings;
import com.yapily.api.client.YapilyApi;

public class SystemPropertiesCredentialsProvider extends BasicCredentialsProvider {

  private static SystemPropertiesCredentialsProvider INSTANCE;

  SystemPropertiesCredentialsProvider() {
    if (Strings.isNullOrEmpty(System.getProperty(YapilyApi.API_APPLICATION_ID_ENV_NAME)) || Strings.isNullOrEmpty(System.getProperty(YapilyApi.API_APPLICATION_ID_ENV_NAME))) {
      throw new RuntimeException("Credentials not set with system properties: " + YapilyApi.API_APPLICATION_ID_ENV_NAME + ", " + YapilyApi.API_APPLICATION_SECRET_ENV_NAME);
    }
    final UsernamePasswordCredentials creds = new UsernamePasswordCredentials(System.getProperty(YapilyApi.API_APPLICATION_ID_ENV_NAME),
                                                                              System.getProperty(YapilyApi.API_APPLICATION_SECRET_ENV_NAME));
    setCredentials(AuthScope.ANY, creds);
  }

  synchronized public static SystemPropertiesCredentialsProvider credentialsProvider() {
    //TODO: check the admin credentials changing to applicationUuid and make this singleton again
    return new SystemPropertiesCredentialsProvider();
  }

}
