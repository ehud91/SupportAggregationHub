package com.supportaggregationapi.utils;

import com.google.gson.Gson;

import java.util.Map;

public class GeneralUtils {

    /**
     * Build json String from attributes
     * @param objectAttr Map<String, String>
     * @return json String
     * @access public
     */
    public static String parseToJson(Map<String, String> objectAttr) {
        Gson gson = new Gson();
        return gson.toJson(objectAttr);
    }
}
