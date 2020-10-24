package com.supportaggregationapi.utils;

public class Const {

    // Application statuses
    public final static int HTTP_STATUS_OK = 200;
    public final static int HTTP_STATUS_INTERNAL_ERROR = 500;
    public final static int HTTP_BAD_REQUEST = 400;

    // Application statuses descriptions
    public final static String HTTP_STATUS_OK_DESCRIPTION = "Status - 200 Ok";
    public final static String HTTP_BAD_REQUEST_DESCRIPTION = "Status - 400 Bad Request";

    // Determines the timeout in milliseconds until a connection is established.
    public static final int CLIENT_API_CONNECT_TIMEOUT = 30000;

    // The timeout when requesting a connection from the connection manager.
    public static final int CLIENT_API_REQUEST_TIMEOUT = 30000;

    // Do not return results after 15 minutes
    public static final int FIFTEEN_MINUTES = 15 * 60 * 1000;

    // Session key to hold the last request time from the CRM system
    public static final String SESSION_KEY_LAST_TIME_REQUEST_FROM_CRM = "LAST_TIME_REQUEST_FROM_CRM";

    // Service api url property key
    public static final String CASES_WS_API = "service.api.aggregationhub-url";

    // Date format from - cases WS API
    public static final String CASES_WS_DATE_FORMAT = "d/MM/yyyy HH:ss";

    public static final String LOG_SEPARATOR = "******************************************************************************";

    // CRM properties keys
    public static final String EXTERNAL_CRM_SYSTEM_APP_ID = "crm.api.request-appid";
    public static final String EXTERNAL_CRM_SYSTEM_APP_KEY = "crm.api.request-appkey";
}
