package com.surgee.JaccardAlgorithm.dto.data;

import java.util.HashMap;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseData {
    HashMap<String, String> item;
    double jaccardNumber;
}
