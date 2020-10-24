package com.supportaggregationapi.validations;

import com.supportaggregationapi.utils.Const;
import com.supportaggregationapi.utils.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;

public class ServiceValidationApi {

    /**
     * Check is product parameter is empty
     * @param product String
     * @return boolean true/false
     * @access public
     */
    public static boolean isProductParamEmpty(String product) {
        return (null == product || product.isEmpty());
    }

    /**
     * Check is product parameter is alphabetic string
     * @param product String
     * @return boolean true/false
     * @access public
     */
    public static boolean isProductParamAlphabetic(String product) {
        return (StringUtils.isAlpha(product));
    }

    /**
     * Check is product parameter is empty
     * @param provider String
     * @return boolean true/false
     * @access public
     */
    public static boolean isProviderParamEmpty(String provider) {
        return (null == provider || provider.isEmpty());
    }

    /**
     * Check is provider parameter is numeric string
     * @param provider String
     * @return boolean true/false
     * @access public
     */
    public static boolean isProviderParamNumeric(String provider) {
        return (StringUtils.isNumeric(provider));
    }

    /**
     * Check is errorCode parameter is empty
     * @param errorCode String
     * @return boolean true/false
     * @access public
     */
    public static boolean isErrorCodeParamEmpty(String errorCode) {
        return (null == errorCode || errorCode.isEmpty());
    }

    /**
     * Check is status parameter is empty
     * @param status String
     * @return boolean true/false
     * @access public
     */
    public static boolean isStatusParamEmpty(String status) {
        return (null == status || status.isEmpty());
    }

    /**
     * Check is status parameter is alphabetic string
     * @param status String
     * @return boolean true/false
     * @access public
     */
    public static boolean isStatusParamAlphabetic(String status) {
        return (StringUtils.isAlpha(status));
    }

    /**
     * Check is errorCode parameter is numeric string
     * @param errorCode String
     * @return boolean true/false
     * @access public
     */
    public static boolean isErrorCodeParamNumeric(String errorCode) {
        return (StringUtils.isNumeric(errorCode));
    }

    /**
     * Check is customer parameter is empty
     * @param customer String
     * @return boolean true/false
     * @access public
     */
    public static boolean isCustomerParamEmpty(String customer) {
        return (null == customer || customer.isEmpty());
    }

    /**
     * Check is customer parameter is numeric string
     * @param customer String
     * @return boolean true/false
     * @access public
     */
    public static boolean isCustomerParamNumeric(String customer) {
        return (StringUtils.isNumeric(customer));
    }

    /**
     * Check is dateRange parameter is empty
     * @param dateRange String
     * @return boolean true/false
     * @access public
     */
    public static boolean isDateRangeParamEmpty(String dateRange) {
        return (null == dateRange || dateRange.isEmpty());
    }

    /**
     * Check is dateRange parameter is in a date format
     * @param dateRange String
     * @return boolean true/false
     * @access public
     */
    public static boolean isDateRangeParamDateFormat(String dateRange) {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Const.CASES_WS_DATE_FORMAT);
            dateFormatter.parse(dateRange);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * Check if only one of the the given parameters is not empty
     * @param product String
     * @param provider String
     * @param errorCode String
     * @param customer String
     * @param dateRange String
     * @param status String
     * @return boolean true/false
     * @access public
     */
    public static boolean isGivenParams(
                                    String product,
                                    String provider,
                                    String errorCode,
                                    String customer,
                                    String dateRange,
                                    String status
                                    ) {
        if(!isProductParamEmpty(product)) {
            return true;
        }
        if(!isProviderParamEmpty(provider)) {
            return true;
        }
        if(!isErrorCodeParamEmpty(errorCode)) {
            return true;
        }
        if(!isCustomerParamEmpty(customer)) {
            return true;
        }
        if(!isDateRangeParamEmpty(dateRange)) {
            return true;
        }
        if(!isStatusParamEmpty(status)) {
            return true;
        }
        return false;
    }

    /**
     * Validation all input parameters
     * @param product String
     * @param provider String
     * @param errorCode String
     * @param customer String
     * @param dateRange String
     * @param status String
     * @return boolean true/false
     * @access public
     */
    public static boolean isValidParams(String product,
                                        String provider,
                                        String errorCode,
                                        String customer,
                                        String dateRange,
                                        String status) {

         if(!ServiceValidationApi.isProductParamEmpty(product) && !ServiceValidationApi.isProductParamAlphabetic(product)) {
            return false;
         } else if(!ServiceValidationApi.isProviderParamEmpty(provider) && !ServiceValidationApi.isProviderParamNumeric(provider)) {
             return false;
         } else if(!ServiceValidationApi.isErrorCodeParamEmpty(errorCode) && !ServiceValidationApi.isErrorCodeParamNumeric(errorCode)) {
             return false;
         } else if(!ServiceValidationApi.isCustomerParamEmpty(customer) && !ServiceValidationApi.isCustomerParamNumeric(customer)) {
             return false;
         } else if(!ServiceValidationApi.isDateRangeParamEmpty(dateRange) && !ServiceValidationApi.isDateRangeParamDateFormat(dateRange)) {
             return false;
         } else if(!ServiceValidationApi.isStatusParamEmpty(status) && !ServiceValidationApi.isStatusParamAlphabetic(status)) {
             return false;
         }
         return true;
    }

    /**
     * Check if given in header the 2 CRM system parameters
     * @param appId String
     * @param appKey String
     * @return boolean true/false
     * @access public
     */
    public static boolean isRequestFromCRM(String appId, String appKey) {
        return (null != appId && !appId.isEmpty() && null != appKey && !appKey.isEmpty());
    }

    /**
     * Check Validation for the CRM system
     * @param appId String
     * @param appKey String
     * @param env String
     * @return boolean true/false
     * @access public
     */
    public static boolean isValidRequestFromCRM(String appId, String appKey, Environment env) {
        return (appId.equals(env.getProperty(Const.EXTERNAL_CRM_SYSTEM_APP_ID)) && appKey.equals(env.getProperty(Const.EXTERNAL_CRM_SYSTEM_APP_KEY)));
    }

    /**
     * Check if the request from the CRM system is less then 15 minutes
     * @param appSession HttpSession
     * @return boolean true/false
     * @access public
     */
    public static boolean isRequestFromCRMPassedFifteenMinutes(HttpSession appSession) {
        if(null == appSession.getAttribute(Const.SESSION_KEY_LAST_TIME_REQUEST_FROM_CRM)) {
            return true;
        }
        if(TimeUtils.isPassedFifteenMinutes((long) appSession.getAttribute(Const.SESSION_KEY_LAST_TIME_REQUEST_FROM_CRM))) {
            return true;
        }
        return false;
    }
}
