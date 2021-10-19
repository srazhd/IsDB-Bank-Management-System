package com.citybank.jsondiff;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonDiffUtil {
    public static JsonDiffDto getJsonDiffDto(String oldJson, String newJson){

        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> oldMap = new HashMap<String, String>();
            Map<String, String> newMap = new HashMap<String, String>();
            FlatMapUtil.addKeys("", mapper.readTree(oldJson), oldMap);
            FlatMapUtil.addKeys("", mapper.readTree(newJson), newMap);
            JsonDiffDto JsonDiffDto = new JsonDiffDto();
            List<PresentData> presentDataList = new ArrayList<PresentData>();
            List<PreviousData> previousDataList = new ArrayList<PreviousData>();
            MapDifference<String, String> difference = Maps.difference(oldMap, newMap);
            for (int i = 0; i < difference.entriesDiffering().size(); i++) {
                PresentData presentData = new PresentData();
                PreviousData previousData = new PreviousData();
                String field = String.valueOf(difference.entriesDiffering().keySet().toArray()[i]);
                presentData.setField(field);
                previousData.setField(field);
                presentData.setValue(difference.entriesDiffering().get(field).rightValue());
                previousData.setValue(difference.entriesDiffering().get(field).leftValue());
                presentDataList.add(presentData);
                previousDataList.add(previousData);
            }
            JsonDiffDto.setPresentDataList(presentDataList);
            JsonDiffDto.setPreviousDataList(previousDataList);
            return JsonDiffDto;
        } catch (IOException e) {
            return null;
        }
    }
}
