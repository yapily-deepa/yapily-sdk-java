package yapily.sdk;

public class YapilyApi {

    public static final String SERVICE_PATH_INSTITUTIONS = "/institutions";
    public static final String SERVICE_PATH_INSTITUTION = "/institutions/{institutionId}";
    public static final String SERVICE_PATH_USERS = "/users";
    public static final String SERVICE_PATH_USER = "/users/{userUuid}";
    public static final String SERVICE_PATH_USER_CONSENTS = "/users/{userUuid}/consents";
    public static final String SERVICE_PATH_INSTITUTION_USER_CONSENTS = "/users/{userUuid}/consents?institutionId={institutionId}";
    public static final String SERVICE_PATH_USER_CONSENT = "/users/{userUuid}/consents/{consentToken}";


    public static final String SERVICE_PATH_IDENTITY = "/identity";
    public static final String SERVICE_PATH_ACCOUNTS = "/accounts";
    public static final String SERVICE_PATH_ACCOUNT = "/accounts/{accountId}";
    public static final String SERVICE_PATH_ACCOUNT_TRANSACTIONS = "/accounts/{accountId}/transactions";

    public static final String API_APPLICATION_ID_ENV_NAME = "YAPILY_API_APPLICATION_ACCESS_KEY";
    public static final String API_APPLICATION_SECRET_ENV_NAME = "YAPILY_API_APPLICATION_ACCESS_SECRET";
    public static final String DEFAULT_ROOT_URL_ENV_NAME = "YAPILY_API_URL";
    public static final String DEFAULT_ROOT_URL_PROPERTY_NAME = "yapily.api.url";

}
