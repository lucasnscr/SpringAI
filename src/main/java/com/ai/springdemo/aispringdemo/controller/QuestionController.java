package com.ai.springdemo.aispringdemo.controller;

import com.ai.springdemo.aispringdemo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }

    @PostMapping("/question")
    public ResponseEntity<String> getQuestion(@RequestBody String question){
        return ResponseEntity.ok().body(questionService.simpleQuestion(question));
    }

    @GetMapping
    public Map completion(@RequestParam(value = "question", defaultValue = "What is the largest trend of 2023?") String question,
                          @RequestParam(value = "prompstuff", defaultValue = "true") boolean prompstuff) {
        String answer = this.questionService.question(question, prompstuff);
        Map map = new LinkedHashMap();
        map.put("question", question);
        map.put("answer", answer);
        return map;
    }
}
