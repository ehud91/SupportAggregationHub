package com.supportaggregationapi.model;

import com.supportaggregationapi.utils.GeneralUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CasesWsApiResponse {

    private String requestUuid;
    private String status;
    private String desc;
    private List<Cases> cases;

    public CasesWsApiResponse() {};

    public CasesWsApiResponse(String requestUuid, String status, String desc, List<Cases> cases) {
        this.requestUuid = requestUuid;
        this.status = status;
        this.desc = desc;
        this.cases = cases;
    }

    public String getRequestUuid() {
        return requestUuid;
    }

    public void setRequestUuid(String requestUuid) {
        this.requestUuid = requestUuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    @Override
    public String toString() {

        Map<String, String> casesWsApiResponse = new HashMap<>();
        casesWsApiResponse.put("requestUuid", String.valueOf(requestUuid));
        casesWsApiResponse.put("status", String.valueOf(status));
        casesWsApiResponse.put("desc", String.valueOf(desc));
        casesWsApiResponse.put("cases", String.valueOf(cases));
        return GeneralUtils.parseToJson(casesWsApiResponse);
    }
}
