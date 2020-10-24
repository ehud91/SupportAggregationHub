package com.supportaggregationapi.model;

import java.util.List;

public class AppResponse {

    private String requestUuid;
    private int status;
    private String desc;
    private int foundCasesCount;
    private List<Cases> cases;

    public AppResponse() {}

    public AppResponse(String requestUuid, int status, String desc, List<Cases> cases, int foundCasesCount) {
        this.requestUuid = requestUuid;
        this.status = status;
        this.desc = desc;
        this.cases = cases;
        this.foundCasesCount = foundCasesCount;
    }

    public String getRequestUuid() {
        return requestUuid;
    }

    public void setRequestUuid(String requestUuid) {
        this.requestUuid = requestUuid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Cases> getCases() {
        return cases;
    }

    public void setCases(List<Cases> cases) {
        this.cases = cases;
    }

    public int getFoundCasesCount() {
        return foundCasesCount;
    }

    public void setFoundCasesCount(int foundCasesCount) {
        this.foundCasesCount = foundCasesCount;
    }
}
