package com.supportaggregationapi.casesapiwsapi;

import com.supportaggregationapi.model.AggregationApiContext;
import com.supportaggregationapi.model.Cases;
import com.supportaggregationapi.model.CasesWsApiResponse;
import com.supportaggregationapi.utils.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class CasesApiWsApi {

    private final static Logger LOGGER = LoggerFactory.getLogger(CasesApiWsApi.class);

    /**
     * Call for casesAPI in order fetch all cases
     * @param acc AggregationApiContext
     * @return cases List<Cases>
     * @access public
     */
    public static List<Cases> getCasesFromWsApi(AggregationApiContext acc) {

        List<Cases> cases = new ArrayList<>();
        final String uri = acc.getApplicationProps().getProperty(Const.CASES_WS_API);
        try {
            ResponseEntity<CasesWsApiResponse> responseApi = acc.getRestTemplate().getForEntity(uri, CasesWsApiResponse.class);
            if (!responseApi.getStatusCode().equals(HttpStatus.OK)) {
                LOGGER.error("Could not get Cases from WS Api, uri={}", uri);
                return cases;
            }
            cases = responseApi.getBody().getCases();
            LOGGER.info("Got Cases from WS Api, cases={}", cases);
        } catch (Exception e) {
            LOGGER.error("Got Exception from WS Api, uri={}, exception={}",
                    uri, e.getMessage());
        }
        return cases;
    }

}
