package com.surgee.JaccardAlgorithm.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JaccardAlgorithmCalculator {

    public double calculateJaccardSimilarity(HashSet<String> dataDb, HashSet<String> searchItem) {
        Set<Object> intersection = new HashSet<>(searchItem);
        Set<Object> union = new HashSet<>(searchItem);

        intersection.retainAll(dataDb);
        union.addAll(dataDb);

        return (Double.valueOf(intersection.size() / Double.valueOf(union.size())));
    }

    public String cleanString(String str) {
        String newStr = new String();
        newStr = str.toLowerCase().replaceAll("[,.]|\\b(and|or|but|nor|so|for|yet)\\b", "");
    
        return newStr;
    }

    public String buildComparisonString (HashMap<String, String> map) {
        StringBuilder comparisonString  = new StringBuilder();
                map.forEach((key, val) -> {
                    if(key == "description" || key ==  "uniqueIdentifier" || key == "itemBrand" || key == "title" ) {
                        comparisonString.append(val + " ");
                    }
                });
        return cleanString(comparisonString.toString());
    }

    
}

