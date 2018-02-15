package com.yapily.sdk.services;

import java.util.logging.Logger;

import com.yapily.api.client.YapilyApi;
import com.google.common.base.Preconditions;
import com.yapily.api.client.YapilyApi;

public class ApiClient {

  static final Logger LOGGER = Logger.getLogger(ApiClient.class.getName());

  // Note: Leave this static initializer at the top of the file.
  static {
    Preconditions.checkState(YapilyApi.MAJOR_VERSION == 0 &&
                             YapilyApi.MINOR_VERSION >= 0,
                             "You are currently running with version %s of acacia-api-client. " +
                                                           "You need at least version 0.0 of acacia-api-client to run version " +
                                                           "0.0.1 of the Acacia SDK library.",
                             YapilyApi.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated and
   * normally should not be changed.
   */
  public static final String DEFAULT_ROOT_URL = "https://localhost:8443/";

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
