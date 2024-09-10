package com.surgee.JaccardAlgorithm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.surgee.JaccardAlgorithm.dto.request.HttpRequestObject;
import com.surgee.JaccardAlgorithm.services.JaccardAlgorithmService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JaccardAlgorithmController {

    private final JaccardAlgorithmService service;

    @PostMapping("/api/v1/jaccard-algorithm")
    public void runJaccardAlgorithm(@RequestBody HttpRequestObject items ) {
     
        service.display(items.getDataDB(), items.getSearchItem(), items.getType());
        
    }
}
