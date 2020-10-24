package com.supportaggregationapi.model;

import com.supportaggregationapi.utils.GeneralUtils;

import java.util.HashMap;
import java.util.Map;

public class AggregationHubInputParameters {

    private String product;
    private int provider;
    private int errorCode;
    private int customer;
    private String caseStatus;
    private String dateRange;
    private String appId;
    private String appKey;

    public AggregationHubInputParameters() {}

    public AggregationHubInputParameters(String product, int provider, int errorCode, int customer, String caseStatus, String dateRange, String appId, String appKey) {
        this.product = product;
        this.errorCode = errorCode;
        this.customer = customer;
        this.caseStatus = caseStatus;
        this.dateRange = dateRange;
        this.provider = provider;
        this.appId = appId;
        this.appKey = appKey;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public int getProvider() {
        return provider;
    }

    public void setProvider(int provider) {
        this.provider = provider;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @Override
    public String toString() {

        Map<String, String> aggregationHubInputParameters = new HashMap<>();
        aggregationHubInputParameters.put("product", String.valueOf(product));
        aggregationHubInputParameters.put("provider", String.valueOf(provider));
        aggregationHubInputParameters.put("errorCode", String.valueOf(errorCode));
        aggregationHubInputParameters.put("customer", String.valueOf(customer));
        aggregationHubInputParameters.put("caseStatus", String.valueOf(caseStatus));
        aggregationHubInputParameters.put("dateRange", String.valueOf(dateRange));
        aggregationHubInputParameters.put("appId", String.valueOf(appId));
        aggregationHubInputParameters.put("appKey", String.valueOf(appKey));
        return GeneralUtils.parseToJson(aggregationHubInputParameters);
    }
}
