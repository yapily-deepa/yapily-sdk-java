package com.yapily.sdk.services;

import java.util.logging.Logger;

import com.yapily.auth.client.AcaciaAuth;
import com.google.common.base.Preconditions;
import com.yapily.api.client.YapilyApi;

public class AuthClient {

  static final Logger LOGGER = Logger.getLogger(AuthClient.class.getName());

  // Note: Leave this static initializer at the top of the file.
  static {
    Preconditions.checkState(YapilyApi.MAJOR_VERSION == 0 &&
                             YapilyApi.MINOR_VERSION >= 0,
                             "You are currently running with version %s of acacia-auth-client. " +
                                                            "You need at least version 0.0 of acacia-auth-client to run version " +
                                                            "0.0.1 of the Acacia SDK library.",
                             YapilyApi.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated and
   * normally should not be changed.
   */
  public static final String DEFAULT_ROOT_URL = "http://localhost:8082/";

  private final String rootUrl;

  private final String selectPath;
  private final String directPath;

  public AuthClient(String rootUrl, String selectPath, String directPath) {
    this.rootUrl = CommonClientUtil.normalizeRootUrl(rootUrl);
    this.selectPath = CommonClientUtil.normalizeServicePath(selectPath);
    this.directPath = CommonClientUtil.normalizeServicePath(directPath);
  }

  public AuthClient(String selectPath, String directPath) {
    this(DEFAULT_ROOT_URL, selectPath, directPath);
  }

  public final String getBaseSelectUrl() {
    return rootUrl + selectPath;
  }

  public final String getBaseDirectUrl() {
    return rootUrl + directPath;
  }
}
