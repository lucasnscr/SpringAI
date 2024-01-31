package com.ai.springdemo.aispringdemo.controller;

import com.ai.springdemo.aispringdemo.service.AISpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AISpringController {

    private final AISpringService aiSpringService;

    @Autowired
    public AISpringController(AISpringService aiSpringService){
        this.aiSpringService = aiSpringService;
    }
    public String getQuestion(@RequestParam(name = "question")String question){
        return aiSpringService.getAsk(question);

    }
}
