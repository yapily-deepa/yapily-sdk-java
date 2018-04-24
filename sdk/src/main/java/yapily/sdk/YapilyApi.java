package yapily.sdk;

public class YapilyApi {

    public static final String SERVICE_PATH_INSTITUTIONS = "/institutions";
    public static final String SERVICE_PATH_USERS = "/users";

    public static final String SERVICE_PATH_USERS_INSTITUTIONS_IDENTITY = "/users/{userUuid}/institutions/{institutionId}/identity";
    public static final String SERVICE_PATH_USERS_INSTITUTIONS_ACCOUNTS = "/users/{userUuid}/institutions/{institutionId}/accounts";
    public static final String SERVICE_PATH_USERS_INSTITUTIONS_ACCOUNTS_TRANSACTIONS = "/users/{userUuid}/institutions/{institutionId}/accounts/{accountId}/transactions";

    public static final String API_APPLICATION_ID_ENV_NAME = "YAPILY_API_APPLICATION_ACCESS_KEY";
    public static final String API_APPLICATION_SECRET_ENV_NAME = "YAPILY_API_APPLICATION_ACCESS_SECRET";
    public static final String DEFAULT_ROOT_URL_ENV_NAME = "YAPILY_API_URL";
    public static final String DEFAULT_ROOT_URL_PROPERTY_NAME = "yapily.api.url";

}
