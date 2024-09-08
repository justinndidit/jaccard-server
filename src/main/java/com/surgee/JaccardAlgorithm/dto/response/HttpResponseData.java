package com.surgee.JaccardAlgorithm.dto.response;

import java.util.HashMap;
import java.util.List;

import com.surgee.JaccardAlgorithm.dto.data.ResponseData;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HttpResponseData {
    List<ResponseData> filteredResponseData ;
    HashMap<String, String> searchItem;
    String type;
}
