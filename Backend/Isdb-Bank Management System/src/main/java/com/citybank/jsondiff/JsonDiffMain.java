package com.citybank.jsondiff;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDiffMain {
    public static void main(String[] args) throws JsonProcessingException {
        String jsonOld = "{\"name\":\"zahangir\", \"location\":\"IICT\"}";
        String jsonNew="{\"name\":\"rabbi\", \"location\":\"IICT\"}";
        JsonDiffDto jsonDiffDto = JsonDiffUtil.getJsonDiffDto(jsonOld, jsonNew);
        System.out.println(new ObjectMapper().writeValueAsString(jsonDiffDto));
    }
}
