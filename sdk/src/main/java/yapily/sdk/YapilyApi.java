package yapily.sdk;

public class YapilyApi {

  public static final String SERVICE_PATH_BANKS = "/banks";
  public static final String SERVICE_PATH_USERS = "/users";

  public static final String SERVICE_PATH_USERS_BANKS_IDENTITY = "/users/{userUuid}/banks/{bankId}/identity";
  public static final String SERVICE_PATH_USERS_BANKS_ACCOUNTS = "/users/{userUuid}/banks/{bankId}/accounts";
  public static final String SERVICE_PATH_USERS_BANKS_ACCOUNTS_TRANSACTIONS = "/users/{userUuid}/banks/{bankId}/accounts/{accountId}/transactions";

  public static final String API_APPLICATION_ID_ENV_NAME = "YAPILY_API_APPLICATION_ACCESS_KEY";
  public static final String API_APPLICATION_SECRET_ENV_NAME = "YAPILY_API_APPLICATION_ACCESS_SECRET";
  public static final String DEFAULT_ROOT_URL_ENV_NAME = "YAPILY_API_URL";

}
