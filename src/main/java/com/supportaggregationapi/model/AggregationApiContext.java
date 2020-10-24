package com.supportaggregationapi.model;

import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class AggregationApiContext {

    private String uuid;
    private Environment applicationProps;
    private RestTemplate restTemplate;
    private List<Cases> cases;
    private int foundCasesCount;
    private AggregationHubInputParameters aggregationHubInputParameters;

    public AggregationApiContext() {super();}

    public AggregationApiContext(String uuid, Environment applicationProps, RestTemplate restTemplate, List<Cases> cases) {
        this.uuid = uuid;
        this.applicationProps = applicationProps;
        this.restTemplate = restTemplate;
        this.cases = cases;
    }

    public void setAggregationApiContext(String uuid, Environment applicationProps, RestTemplate restTemplate, AggregationHubInputParameters aggregationHubInputParameters) {
        this.uuid = uuid;
        this.applicationProps = applicationProps;
        this.restTemplate = restTemplate;
        this.aggregationHubInputParameters = aggregationHubInputParameters;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Environment getApplicationProps() {
        return applicationProps;
    }

    public void setApplicationProps(Environment applicationProps) {
        this.applicationProps = applicationProps;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Cases> getCases() {
        return cases;
    }

    public void setCases(List<Cases> cases) {
        this.cases = cases;
    }

    public AggregationHubInputParameters getAggregationHubInputParameters() {
        return aggregationHubInputParameters;
    }

    public void setAggregationHubInputParameters(AggregationHubInputParameters aggregationHubInputParameters) {
        this.aggregationHubInputParameters = aggregationHubInputParameters;
    }

    public int getFoundCasesCount() {
        return foundCasesCount;
    }

    public void setFoundCasesCount(int foundCasesCount) {
        this.foundCasesCount = foundCasesCount;
    }
}
