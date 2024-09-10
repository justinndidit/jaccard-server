package com.surgee.JaccardAlgorithm.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.surgee.JaccardAlgorithm.dto.data.ResponseData;
import com.surgee.JaccardAlgorithm.dto.response.HttpResponseData;
import com.surgee.JaccardAlgorithm.util.JaccardAlgorithmCalculator;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class JaccardAlgorithmService {
    private final JaccardAlgorithmCalculator jaccardAlgorithmCalculator;
    private final WebClient webClient;
    
    @Async
    public void jaccardAlgorithmHandler(List<LinkedHashMap<String, String>> dataDB, 
                                                                                                        HashMap<String, String> searchItem,
                                                                                                        List<ResponseData> responseData  ) {
        try {
                HashSet<String> comparisonStringSearchItem = new HashSet<> (Arrays.asList(jaccardAlgorithmCalculator
                                                                                                                                                                            .buildComparisonString(searchItem).split(" ")));        
                for (HashMap<String, String> element : dataDB ) {
                    String comparisonStringDataDB = jaccardAlgorithmCalculator.buildComparisonString(element);
            
                    element.replace("comparisonString", comparisonStringDataDB);

                    HashSet<String> comparisonStringDataDbElement = new HashSet<>(Arrays.asList(element.get("comparisonString").split(" ")));
                    double jaccardNumber = jaccardAlgorithmCalculator.calculateJaccardSimilarity(comparisonStringDataDbElement, comparisonStringSearchItem);
                    responseData.add(ResponseData.builder().item(element).jaccardNumber(jaccardNumber).build());
                }
                
            } catch (Exception e) {
                throw new IllegalArgumentException("Something went wrong");
            }
        
    }

    public void  display(List<LinkedHashMap<String, String>> dataDB, HashMap<String, String> searchItem, String type) {
        List<ResponseData> responseData = new ArrayList<>();

        try {
            jaccardAlgorithmHandler(dataDB, searchItem, responseData);

            double minJaccardValue = 0.8;

            List<ResponseData> filteredResponseData = responseData.stream().filter((item) -> item.getJaccardNumber() >= minJaccardValue).collect(Collectors.toList());

            HttpResponseData response = HttpResponseData.builder()
                                                                                                                .filteredResponseData(filteredResponseData)
                                                                                                                .searchItem(searchItem)
                                                                                                                .type(type)
                                                                                                                .build();

            webClient.post().uri("api/v1/webhook/jaccard-algorithm")
                                            .body(Mono.just(response), ResponseData.class)
                                            .retrieve()
                                            .bodyToMono(Void.class)
                                            .subscribe(hookResponse -> {
                                                System.out.println("webhook call succeeded");
                                            }, error -> {
                                                System.out.println("Error in web hook");
                                            });


        } catch (Exception e) {
            webClient.post().uri("/api/v1/webhook/jaccard-algorithm")
            .body(Mono.just(e.getMessage()), String.class)
            .retrieve()
            .bodyToMono(Void.class)
            .subscribe(response -> {
                System.out.println("webhook call succeeded");
            }, error -> {
                System.out.println("Error in web hook");
            });
        }       
    }
}
