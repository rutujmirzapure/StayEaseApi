package com.example.LearningNavigatorAPI.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HiddenFeatureService {

    private final String numbersApiUrl = "http://numbersapi.com/";

    public String getNumberTrivia(int number) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(numbersApiUrl + number + "/trivia", String.class);
    }
}
