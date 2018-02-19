package com.acaciaconnect.api.client;

public class AcaciaApi {
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

  public static final String MEDIA_TYPE = "application/json;charset=UTF-8";

  public static final String SERVICE_PATH_BANKS = "/banks";
  public static final String SERVICE_PATH_USERS = "/user";
  public static final String SERVICE_PATH_USER = "/user/{uuid}";

  public static final String SERVICE_PATH_USERS_BANKS_IDENTITY = "/user/{userUuid}/banks/{bankId}/identity";
  public static final String SERVICE_PATH_USERS_BANKS_ACCOUNTS = "/user/{userUuid}/banks/{bankId}/accounts";
  public static final String SERVICE_PATH_USERS_BANKS_ACCOUNT = "/user/{userUuid}/banks/{bankId}/accounts/{accountId}";
  public static final String SERVICE_PATH_USERS_BANKS_ACCOUNTS_TRANSACTIONS = "/user/{userUuid}/banks/{bankId}/accounts/{accountId}/transactions";

  public static final String API_APPLICATION_ID_ENV_NAME = "ACACIA_API_APPLICATION_ACCESS_KEY";
  public static final String API_APPLICATION_SECRET_ENV_NAME = "ACACIA_API_APPLICATION_ACCESS_SECRET";

}
