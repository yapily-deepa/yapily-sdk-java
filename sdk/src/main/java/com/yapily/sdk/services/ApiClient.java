package com.yapily.sdk.services;

import java.util.logging.Logger;

import com.yapily.api.client.AcaciaApi;
import com.google.common.base.Preconditions;

public class ApiClient {

    static final Logger LOGGER = Logger.getLogger(ApiClient.class.getName());

    // Note: Leave this static initializer at the top of the file.
    static {
        Preconditions.checkState(AcaciaApi.MAJOR_VERSION == 0 &&
                                 AcaciaApi.MINOR_VERSION >= 0,
                                 "You are currently running version %s of the Yapily API client. " +
                                 "You need at least version 1.0.0 of the client to run version " +
                                 "0.0.1 of the Acacia SDK library.",
                                 AcaciaApi.VERSION);
    }

    /**
     * The default encoded root URL of the service. This is determined when the library is generated and
     * normally should not be changed.
     */
    static final String DEFAULT_ROOT_URL =
            System.getenv().getOrDefault(AcaciaApi.DEFAULT_ROOT_URL_ENV_NAME,
                                         System.getProperty(AcaciaApi.DEFAULT_ROOT_URL_ENV_NAME, "http://localhost:8081"));

    /**
     * Root URL of the service, for example {@code "https://api.acaciaconnect.com/"}. Must be
     * URL-encoded and must end with a "/".
     */
    private final String rootUrl;

    /**
     * Service path, for example {@code "v1/banking/"}. Must be URL-encoded and must end with a "/".
     */
    private final String servicePath;

    public ApiClient(String rootUrl, String servicePath) {
        this.rootUrl = CommonClientUtil.normalizeRootUrl(rootUrl);
        this.servicePath = CommonClientUtil.normalizeServicePath(servicePath);
    }

    public ApiClient(String servicePath) {
        this(DEFAULT_ROOT_URL, servicePath);
    }

    public final String getBaseUrl() {
        return rootUrl + servicePath;
    }

}
