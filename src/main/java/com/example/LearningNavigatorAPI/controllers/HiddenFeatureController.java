package com.example.LearningNavigatorAPI.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.lmsexamregistration.services.HiddenFeatureService;

@RestController
@RequestMapping("/hidden-feature")
public class HiddenFeatureController {

    private final HiddenFeatureService hiddenFeatureService; 

    public HiddenFeatureController(HiddenFeatureService hiddenFeatureService) {
        this.hiddenFeatureService = hiddenFeatureService;
    }

    @GetMapping("/{number}/trivia")
    public ResponseEntity<String> getNumberTrivia(@PathVariable int number) {
        String trivia = hiddenFeatureService.getNumberTrivia(number);
        return ResponseEntity.ok(trivia);
    }
}