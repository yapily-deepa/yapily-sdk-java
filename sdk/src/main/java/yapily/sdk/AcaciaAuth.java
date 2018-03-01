package yapily.sdk;

public class AcaciaAuth {
  // NOTE: Integer instead of int so compiler thinks it isn't a constant, so it won't inline it
  /**
   * Major part of the current release version.
   */
  public static final Integer MAJOR_VERSION = 0;

  /**
   * Minor part of the current release version.
   */
  public static final Integer MINOR_VERSION = 0;

  /**
   * Fix part of the current release version.
   */
  public static final Integer FIX_VERSION = 1;

  /** Current release version. */
  // NOTE: toString() so compiler thinks it isn't a constant, so it won't inline it
  public static final String VERSION = (MAJOR_VERSION + "." + MINOR_VERSION + "." + FIX_VERSION).toString();

  public static final String PATH_DIRECT = "/direct";

  public static final String PARAMETER_APPLICATION_ID = "application";
  public static final String PARAMETER_USER_ID = "user";
  public static final String PARAMETER_BANK_ID = "bank";
  public static final String PARAMETER_CALLBACK_URL = "callback";
  public static final String PARAMETER_SCOPE = "scope";

  public static final String DEFAULT_ROOT_URL_ENV_NAME = "ACACIA_AUTH_URL";

}
