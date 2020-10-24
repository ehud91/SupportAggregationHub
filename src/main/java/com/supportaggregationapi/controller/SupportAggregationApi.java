package com.supportaggregationapi.controller;

import com.supportaggregationapi.logic.AggregationLogic;
import com.supportaggregationapi.model.AggregationHubInputParameters;
import com.supportaggregationapi.model.AppResponse;
import com.supportaggregationapi.service.AggregationSrv;
import com.supportaggregationapi.validations.ServiceValidationApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

@RestController
@ComponentScan(basePackages = "com.supportaggregationapi.*")
@EntityScan("com.supportaggregationapi.*")
@RequestMapping("/api")
@PropertySource("classpath:application.properties")
@EnableAutoConfiguration
public class SupportAggregationApi {

    private final static Logger LOGGER = LoggerFactory.getLogger(SupportAggregationApi.class);

    @Autowired
    private Environment env;

    @Autowired
    private RestTemplate restTemplate;

    @GET
    @GetMapping("/getaggregationhub")
    @Produces("application/json; charset=UTF-8")
    @Operation(summary = "Get device information")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Got AppResponse response", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AppResponse.class))}),
    @ApiResponse(responseCode = "400", description = "Invalid AppResponse", content = @Content),
    @ApiResponse(responseCode = "404", description = "AppResponse not found", content = @Content)})
    public ResponseEntity<AppResponse> getAggregationHub(
            @RequestParam(name = "product", required = false) String product,
            @RequestParam(name = "provider", required = false) String provider,
            @RequestParam(name = "errorCode", required = false) String errorCode,
            @RequestParam(name = "customer", required = false) String customer,
            @RequestParam(name = "caseStatus", required = false) String caseStatus,
            @RequestParam(name = "dateRange", required = false) String dateRange,
            @RequestHeader(name = "X-App-Id", required = false) String appId,
            @RequestHeader(name = "X-App-Key", required = false) String appKey,
            HttpSession appSession) {

        if(ServiceValidationApi.isRequestFromCRM(appId, appKey) &&
                ServiceValidationApi.isValidRequestFromCRM(appId, appKey, env) &&
                !ServiceValidationApi.isRequestFromCRMPassedFifteenMinutes(appSession)) {

            // CRM Validation - Detect CRM requests by less then 15 minutes
            AppResponse appResponse = AggregationLogic.getCRMErrorResponse();
            return new ResponseEntity<AppResponse>(appResponse, HttpStatus.BAD_REQUEST);
        }

        if(!ServiceValidationApi.isGivenParams(product, provider, errorCode, customer, dateRange, caseStatus) ||
                !ServiceValidationApi.isValidParams(product, provider, errorCode, customer, dateRange, caseStatus)) {

            // Validation on the Service input
            AppResponse appResponse = AggregationLogic.getValidationErrorResponse(product, provider, errorCode, customer, dateRange, caseStatus);
            return new ResponseEntity<AppResponse>(appResponse, HttpStatus.BAD_REQUEST);
        }

        AggregationHubInputParameters aggregationHubInputParameters =
                AggregationSrv.collectInputParams(product, provider, errorCode, customer, dateRange, caseStatus, appId, appKey);

        AppResponse appResponse = AggregationSrv.getCasesByParams(env, restTemplate, aggregationHubInputParameters, appSession);
        return new ResponseEntity<AppResponse>(appResponse, HttpStatus.OK);
    }
}
