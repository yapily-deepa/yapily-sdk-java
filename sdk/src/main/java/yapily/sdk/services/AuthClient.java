package yapily.sdk.services;

import yapily.sdk.YapilyAuth;
import com.google.common.base.Preconditions;

public class AuthClient {

    // Note: Leave this static initializer at the top of the file.
    static {
        Preconditions.checkState(YapilyAuth.MAJOR_VERSION == 0 &&
                                 YapilyAuth.MINOR_VERSION >= 0,
                                 "You are currently running version %s of the yapily auth-client. " +
                                 "You need at least version 0.0 of yapily auth-client to run version " +
                                 "0.0.1 of the Yapily SDK library.",
                                 YapilyAuth.VERSION);
    }

    /**
     * The default encoded root URL of the service. This is determined when the library is generated and
     * normally should not be changed.
     */
    private static final String DEFAULT_ROOT_URL =
            System.getenv().getOrDefault(YapilyAuth.DEFAULT_ROOT_URL_ENV_NAME,
                                         System.getProperty(YapilyAuth.DEFAULT_ROOT_URL_ENV_NAME, "http://localhost:8082/"));

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
