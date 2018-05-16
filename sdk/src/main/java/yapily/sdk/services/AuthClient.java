package yapily.sdk.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.google.common.base.Preconditions;

import yapily.sdk.YapilyAuth;

public class AuthClient {

    /**
     * The default encoded root URL of the service. This is determined when the library is generated and
     * normally should not be changed.
     */
    private static final String DEFAULT_ROOT_URL =
            System.getenv().getOrDefault(YapilyAuth.DEFAULT_ROOT_URL_ENV_NAME,
                                         System.getProperty(YapilyAuth.DEFAULT_ROOT_URL_PROPERTY_NAME, "http://localhost:8082/"));

    // Note: Leave this static initializer at the top of the file.
    static {
        Properties props = new Properties();
        InputStream is = AuthClient.class.getClassLoader().getResourceAsStream("project.properties");
        try {
            props.load(is);

            final String authVersion = props.getProperty("yapily.auth-client.version");
            String[] versionParts = authVersion.split("\\.");
            final int authMajorVersion = Integer.parseInt(versionParts[0]);
            final int authMinorVersion = Integer.parseInt(versionParts[1]);

            final String sdkVersion = props.getProperty("yapily.sdk.version");

            Preconditions.checkState(authMajorVersion == 0 &&
                                     authMinorVersion >= 0,
                                     "You are currently running version %s of the Yapily Auth client. " +
                                     "You need at least version 0.0.1 of the client to run version " +
                                     "%s of the Yapily SDK library.",
                                     authVersion, sdkVersion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final String rootUrl;

    private final String directPath;

    public AuthClient(String rootUrl, String directPath) {
        this.rootUrl = CommonClientUtil.normalizeRootUrl(rootUrl);
        this.directPath = CommonClientUtil.normalizeServicePath(directPath);
    }

    public AuthClient(String directPath) {
        this(DEFAULT_ROOT_URL, directPath);
    }

    protected final String getBaseDirectUrl() {
        return rootUrl + directPath;
    }
}
