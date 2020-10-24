package com.supportaggregationapi.logic;

import com.supportaggregationapi.casesapiwsapi.CasesApiWsApi;
import com.supportaggregationapi.model.AggregationApiContext;
import com.supportaggregationapi.model.AggregationHubInputParameters;
import com.supportaggregationapi.model.AppResponse;
import com.supportaggregationapi.model.Cases;
import com.supportaggregationapi.utils.Const;
import com.supportaggregationapi.validations.ServiceValidationApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AggregationLogic {

    private final static Logger LOGGER = LoggerFactory.getLogger(AggregationLogic.class);
    final private static AggregationApiContext aac = new AggregationApiContext();

    /**
     * Initialize application context
     * @param uuid UUID
     * @param env Environment
     * @param restTemplate RestTemplate
     * @param aggregationHubInputParameters AggregationHubInputParameters
     * @return aac AggregationApiContext
     * @access public
     */
    public static AggregationApiContext initAppContext(UUID uuid,
                                                 Environment env,
                                                 RestTemplate restTemplate,
                                                 AggregationHubInputParameters aggregationHubInputParameters) {

        LOGGER.info("Initialize application context");
        aac.setAggregationApiContext(uuid.toString(), env, restTemplate, aggregationHubInputParameters);
        return aac;
    }

    /**
     * Run the service logic
     * @return acc AggregationApiContext
     * @access public
     */
    public static AggregationApiContext runLogic() {

        List<Cases> cases = CasesApiWsApi.getCasesFromWsApi(aac);
        aac.setCases(cases);
        filterCasesByGivenParameters();
        return aac;
    }

    /**
     * Filter all cases by the Given input parameters
     */
    public static void filterCasesByGivenParameters() {

        List<Cases> cases = aac.getCases();
        if (!aac.getAggregationHubInputParameters().getProduct().isEmpty()) {
            LOGGER.info("Filter by service input parameters, product={}", aac.getAggregationHubInputParameters().getProduct());
            cases = cases.stream()
                    .filter(param -> param.getProductName().contains(aac.getAggregationHubInputParameters().getProduct()))
                    .collect(Collectors.toList());
        }
        if (aac.getAggregationHubInputParameters().getProvider() > 0) {
            LOGGER.info("Filter by service input parameters, provider={}", aac.getAggregationHubInputParameters().getProvider());
            cases = cases.stream()
                    .filter(param -> param.getProvider() == aac.getAggregationHubInputParameters().getProvider())
                    .collect(Collectors.toList());
        }
        if (aac.getAggregationHubInputParameters().getCustomer() > 0) {
            LOGGER.info("Filter by service input parameters, customer={}", aac.getAggregationHubInputParameters().getCustomer());
            cases = cases.stream()
                    .filter(param -> param.getCustomerId() == aac.getAggregationHubInputParameters().getCustomer())
                    .collect(Collectors.toList());
        }
        if (aac.getAggregationHubInputParameters().getErrorCode() > 0) {
            LOGGER.info("Filter by service input parameters, errorCode={}", aac.getAggregationHubInputParameters().getErrorCode());
            cases = cases.stream()
                    .filter(param -> param.getCreateErrorCode() == aac.getAggregationHubInputParameters().getErrorCode())
                    .collect(Collectors.toList());
        }
        if (!aac.getAggregationHubInputParameters().getCaseStatus().isEmpty()) {
            LOGGER.info("Filter by service input parameters, status={}", aac.getAggregationHubInputParameters().getCaseStatus());
            cases = cases.stream()
                    .filter(param -> param.getStatus().contains(aac.getAggregationHubInputParameters().getCaseStatus()))
                    .collect(Collectors.toList());
        }
        if(!aac.getAggregationHubInputParameters().getDateRange().isEmpty()) {
            LOGGER.info("Filter by service input parameters, dateRange={}", aac.getAggregationHubInputParameters().getDateRange());
            cases = cases.stream()
                    .filter(param -> param.getTicketCreatedDate().equals(new Date(aac.getAggregationHubInputParameters().getCaseStatus())) ||
                            param.getLastModifiedDate().equals(new Date(aac.getAggregationHubInputParameters().getCaseStatus())))
                    .collect(Collectors.toList());
        }
        LOGGER.info("Filter all cases from WS response by the given service input parameters, cases={}, casesCount={}",
                cases, cases.size());
        aac.setCases(cases);
        aac.setFoundCasesCount(cases.size());
    }

    /**
     * Update the time of the last request from the CRM system in session
     * @param appId String
     * @param appKey String
     * @param env Environment
     * @param appSession HttpSession
     * @access public
     */
    public static void updateLastRequestTimeFromCRM(String appId, String appKey, Environment env, HttpSession appSession) {

        if(ServiceValidationApi.isRequestFromCRM(appId, appKey) &&
                ServiceValidationApi.isValidRequestFromCRM(appId, appKey, env) &&
                ServiceValidationApi.isRequestFromCRMPassedFifteenMinutes(appSession)) {
            LOGGER.info("Got request from the CRM system - Update the session key - last time request from the CRM system");
            appSession.setAttribute(Const.SESSION_KEY_LAST_TIME_REQUEST_FROM_CRM, System.currentTimeMillis());
        }
    }

    /**
     * Get the validation Response error by the given service parameters
     * @param product String
     * @param provider String
     * @param errorCode String
     * @param customer String
     * @param dateRange String
     * @param status String
     * @return appResponse AppResponse
     * @access public
     */
    public static AppResponse getValidationErrorResponse(String product,
                                                         String provider,
                                                         String errorCode,
                                                         String customer,
                                                         String dateRange,
                                                         String status) {
        UUID uuid = UUID.randomUUID();
        AppResponse appResponse = new AppResponse();
        if(!ServiceValidationApi.isGivenParams(product, provider, errorCode, customer, status, dateRange)) {
            appResponse = new AppResponse(uuid.toString(), Const.HTTP_BAD_REQUEST, "Validation Error - No parameter was given", null, 0);
        } else if(!ServiceValidationApi.isProductParamEmpty(product) && !ServiceValidationApi.isProductParamAlphabetic(product)) {
            appResponse = new AppResponse(uuid.toString(), Const.HTTP_BAD_REQUEST, "Validation Error - parameter product should only contains alphabetic characters", null, 0);
        } else if(!ServiceValidationApi.isProviderParamEmpty(provider) && !ServiceValidationApi.isProviderParamNumeric(provider)) {
            appResponse = new AppResponse(uuid.toString(), Const.HTTP_BAD_REQUEST, "Validation Error - parameter provider should only contains alphabetic characters", null, 0);
        } else if(!ServiceValidationApi.isErrorCodeParamEmpty(errorCode) && !ServiceValidationApi.isErrorCodeParamNumeric(errorCode)) {
            appResponse = new AppResponse(uuid.toString(), Const.HTTP_BAD_REQUEST, "Validation Error - parameter errorCode should only contains numbers", null, 0);
        } else if(!ServiceValidationApi.isCustomerParamEmpty(customer) && !ServiceValidationApi.isCustomerParamNumeric(customer)) {
            appResponse = new AppResponse(uuid.toString(), Const.HTTP_BAD_REQUEST, "Validation Error - parameter customer should only contains numbers", null, 0);
        } else if(!ServiceValidationApi.isDateRangeParamEmpty(dateRange) && !ServiceValidationApi.isDateRangeParamDateFormat(dateRange)) {
            appResponse = new AppResponse(uuid.toString(), Const.HTTP_BAD_REQUEST, "Validation Error - parameter dateRange is not in the valid format", null, 0);
        } else if(!ServiceValidationApi.isStatusParamEmpty(status) && !ServiceValidationApi.isStatusParamAlphabetic(status)) {
            appResponse = new AppResponse(uuid.toString(), Const.HTTP_BAD_REQUEST, "Validation Error - parameter case status should only contains alphabetic characters", null, 0);
        }
        return appResponse;
    }

    /**
     * Get Response error for the CRM system by detecting request less then 15 minutes
     * @return appResponse AppResponse
     * @access public
     */
    public static AppResponse getCRMErrorResponse() {
        UUID uuid = UUID.randomUUID();
        return new AppResponse(uuid.toString(), Const.HTTP_BAD_REQUEST, "CRM System Validation Error - Detected CRM system request less then - 15 minutes" , null, 0);
    }
}
