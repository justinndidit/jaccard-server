package com.surgee.JaccardAlgorithm.dto.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import lombok.Data;

@Data
public class HttpRequestObject {
    private List<LinkedHashMap<String, String>> dataDB = new ArrayList<>();
    private HashMap<String, String> searchItem = new LinkedHashMap<>();
    private String type;
}
