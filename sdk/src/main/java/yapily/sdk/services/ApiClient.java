package yapily.sdk.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.commons.text.StrSubstitutor;

import com.google.common.base.Preconditions;

import yapily.sdk.YapilyApi;
import yapily.sdk.client.ValueMap;

public class ApiClient {

    static final Logger LOGGER = Logger.getLogger(ApiClient.class.getName());
    /**
     * The default encoded root URL of the service. This is determined when the library is generated and
     * normally should not be changed.
     */
    static final String DEFAULT_ROOT_URL = System.getenv().getOrDefault(YapilyApi.DEFAULT_ROOT_URL_ENV_NAME,
                                                                        System.getProperty(YapilyApi.DEFAULT_ROOT_URL_PROPERTY_NAME, "http://localhost:8081"));

    // Note: Leave this static initializer at the top of the file.
    static {
        Properties props = new Properties();
        InputStream is = ApiClient.class.getClassLoader().getResourceAsStream("project.properties");
        try {
            props.load(is);

            final String apiVersion = props.getProperty("yapily.api-client.version");
            String[] versionParts = apiVersion.split("\\.");
            final int apiMajorVersion = Integer.parseInt(versionParts[0]);
            final int apiMinorVersion = Integer.parseInt(versionParts[1]);

            final String sdkVersion = props.getProperty("yapily.sdk.version");

            Preconditions.checkState(apiMajorVersion == 0 &&
                                     apiMinorVersion >= 1,
                                     "You are currently running version %s of the Yapily API client. " +
                                     "You need at least version 0.0.1 of the client to run version " +
                                     "%s of the Yapily SDK library.",
                                     apiVersion, sdkVersion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Root URL of the service, for example {@code "https://api.yapily.com/"}. Must be URL-encoded and
     * must end with a "/".
     */
    private final String rootUrl;

    /**
     * Service path, for example {@code "/institutions/"}. Must be URL-encoded and must end with a "/".
     */
    private final String servicePath;

    public ApiClient(String rootUrl, String servicePath) {
        this.rootUrl = CommonClientUtil.normalizeRootUrl(rootUrl);
        this.servicePath = CommonClientUtil.normalizeServicePath(servicePath);
    }

    public ApiClient(String servicePath) {
        this(DEFAULT_ROOT_URL, servicePath);
    }

    public final String getEndpoint() {
        return rootUrl + servicePath;
    }

    public final String getEndpoint(ValueMap valueMap) {
        return new StrSubstitutor(valueMap.toMap(), "{", "}").replace(getEndpoint());
    }

}
