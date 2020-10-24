package com.supportaggregationapi.model;

import com.supportaggregationapi.utils.GeneralUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Cases {

    private int caseId;
    private int customerId;
    private int provider;
    private int createErrorCode;
    private String status;
    private Date ticketCreatedDate;
    private Date lastModifiedDate;
    private String productName;

    public Cases() {}

    public Cases(int caseId, int customerId, int provider, int createErrorCode, String status, Date ticketCreatedDate, Date lastModifiedDate, String productName) {
        this.caseId = caseId;
        this.customerId = customerId;
        this.provider = provider;
        this.createErrorCode = createErrorCode;
        this.status = status;
        this.ticketCreatedDate = ticketCreatedDate;
        this.lastModifiedDate = lastModifiedDate;
        this.productName = productName;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProvider() {
        return provider;
    }

    public void setProvider(int provider) {
        this.provider = provider;
    }

    public int getCreateErrorCode() {
        return createErrorCode;
    }

    public void setCreateErrorCode(int createErrorCode) {
        this.createErrorCode = createErrorCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTicketCreatedDate() {
        return ticketCreatedDate;
    }

    public void setTicketCreatedDate(Date ticketCreatedDate) {
        this.ticketCreatedDate = ticketCreatedDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {

        Map<String, String> casesAttributes = new HashMap<>();
        casesAttributes.put("caseId", String.valueOf(caseId));
        casesAttributes.put("customerId", String.valueOf(customerId));
        casesAttributes.put("provider", String.valueOf(provider));
        casesAttributes.put("createErrorCode", String.valueOf(createErrorCode));
        casesAttributes.put("status", String.valueOf(status));
        casesAttributes.put("ticketCreatedDate", String.valueOf(ticketCreatedDate));
        casesAttributes.put("lastModifiedDate", String.valueOf(lastModifiedDate));
        casesAttributes.put("productName", String.valueOf(productName));
        return GeneralUtils.parseToJson(casesAttributes);
    }
}
