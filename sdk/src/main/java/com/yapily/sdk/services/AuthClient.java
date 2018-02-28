package com.yapily.sdk.services;

import java.util.logging.Logger;

import com.yapily.sdk.AcaciaAuth;
import com.google.common.base.Preconditions;

public class AuthClient {

    static final Logger LOGGER = Logger.getLogger(AuthClient.class.getName());

    // Note: Leave this static initializer at the top of the file.
    static {
        Preconditions.checkState(AcaciaAuth.MAJOR_VERSION == 0 &&
                                 AcaciaAuth.MINOR_VERSION >= 0,
                                 "You are currently running with version %s of acacia-auth-client. " +
                                 "You need at least version 0.0 of acacia-auth-client to run version " +
                                 "0.0.1 of the Acacia SDK library.",
                                 AcaciaAuth.VERSION);
    }

    /**
     * The default encoded root URL of the service. This is determined when the library is generated and
     * normally should not be changed.
     */
    static final String DEFAULT_ROOT_URL =
            System.getenv().getOrDefault(AcaciaAuth.DEFAULT_ROOT_URL_ENV_NAME,
                                         System.getProperty(AcaciaAuth.DEFAULT_ROOT_URL_ENV_NAME, "http://localhost:8082/"));

    private final String rootUrl;

    private final String directPath;

    public AuthClient(String rootUrl, String directPath) {
        this.rootUrl = CommonClientUtil.normalizeRootUrl(rootUrl);
        this.directPath = CommonClientUtil.normalizeServicePath(directPath);
    }

    public AuthClient(String directPath) {
        this(DEFAULT_ROOT_URL, directPath);
    }

    public final String getBaseDirectUrl() {
        return rootUrl + directPath;
    }
}
