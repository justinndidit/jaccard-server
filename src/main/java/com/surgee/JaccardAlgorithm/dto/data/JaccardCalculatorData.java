package com.surgee.JaccardAlgorithm.dto.data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JaccardCalculatorData {
    private List<LinkedHashMap<String, String>> dataDB;
    private HashMap<String, String> searchItem;
}
