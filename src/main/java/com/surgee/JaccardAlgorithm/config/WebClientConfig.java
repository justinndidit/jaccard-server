package com.surgee.JaccardAlgorithm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
public class WebClientConfig {
    
    @Bean
    public WebClient webClient() {
        return  WebClient.builder()
                                            .baseUrl("https://swep-200-project.onrender.com")
                                            .defaultHeader(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE)
                                            .build();
        }
}
