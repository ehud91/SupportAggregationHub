package com.supportaggregationapi.service;

import com.supportaggregationapi.logic.AggregationLogic;
import com.supportaggregationapi.model.AggregationApiContext;
import com.supportaggregationapi.model.AggregationHubInputParameters;
import com.supportaggregationapi.model.AppResponse;
import com.supportaggregationapi.utils.Const;
import com.supportaggregationapi.validations.ServiceValidationApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.UUID;

public class AggregationSrv {

    private final static Logger LOGGER = LoggerFactory.getLogger(AggregationSrv.class);
    private static AggregationApiContext aac = new AggregationApiContext();

    /**
     * Service Get Cases by parameters
     * @param env Environment
     * @param restTemplate RestTemplate
     * @param appInputParameters AggregationHubInputParameters
     * @param appSession HttpSession
     * @return appResponse AppResponse
     * @access public
     */
    public static AppResponse getCasesByParams(
            Environment env, RestTemplate restTemplate, AggregationHubInputParameters appInputParameters, HttpSession appSession) {

        AppResponse appResponse;
        UUID uuid = UUID.randomUUID();
        LOGGER.info(Const.LOG_SEPARATOR);
        LOGGER.info("Start Get CasesByParams service, uuid={}", uuid.toString());
        try {
            AggregationLogic.updateLastRequestTimeFromCRM(appInputParameters.getAppId(), appInputParameters.getAppKey(), env, appSession);
            aac = AggregationLogic.initAppContext(uuid, env, restTemplate, appInputParameters);
            aac = AggregationLogic.runLogic();
            appResponse = new AppResponse(uuid.toString(), Const.HTTP_STATUS_OK, Const.HTTP_STATUS_OK_DESCRIPTION, aac.getCases(), aac.getFoundCasesCount());
        } catch(Exception e) {
            appResponse = new AppResponse(uuid.toString(), Const.HTTP_STATUS_INTERNAL_ERROR, "Error Occured - Could not get the requested cases", null, 0);
        }
        LOGGER.info("End Get CasesByParams Service, uuid={}", uuid.toString());
        LOGGER.info(Const.LOG_SEPARATOR);
        return appResponse;
    }

    /**
     * Collect the given input parameters
     * @param product String
     * @param provider String
     * @param errorCode String
     * @param customer String
     * @param dateRange String
     * @param caseStatus String
     * @param appId String
     * @param appKey String
     * @return aggregationHubInputParameters AggregationHubInputParameters
     * @access public
     */
    public static AggregationHubInputParameters collectInputParams(String product,
                                                                   String provider,
                                                                   String errorCode,
                                                                   String customer,
                                                                   String dateRange,
                                                                   String caseStatus,
                                                                   String appId,
                                                                   String appKey) {
        AggregationHubInputParameters aggregationHubInputParameters = new AggregationHubInputParameters();
        String productInput = (!ServiceValidationApi.isProductParamEmpty(product)) ? product : "";
        int providerInput = (!ServiceValidationApi.isProviderParamEmpty(provider)) ? Integer.parseInt(provider) : 0;
        int customerInput = (!ServiceValidationApi.isCustomerParamEmpty(customer)) ? Integer.parseInt(customer) : 0;
        String dateRangeInput = (!ServiceValidationApi.isDateRangeParamEmpty(dateRange)) ? dateRange : "";
        int errorCodeInput = (!ServiceValidationApi.isErrorCodeParamEmpty(errorCode)) ? Integer.parseInt(errorCode) : 0;
        String caseStatusInput = (!ServiceValidationApi.isStatusParamEmpty(caseStatus)) ? caseStatus : "";
        aggregationHubInputParameters.setProduct(productInput);
        aggregationHubInputParameters.setProvider(providerInput);
        aggregationHubInputParameters.setErrorCode(errorCodeInput);
        aggregationHubInputParameters.setCustomer(customerInput);
        aggregationHubInputParameters.setCaseStatus(caseStatusInput);
        aggregationHubInputParameters.setDateRange(dateRangeInput);
        aggregationHubInputParameters.setAppId(appId);
        aggregationHubInputParameters.setAppKey(appKey);
        return aggregationHubInputParameters;
    }
}
