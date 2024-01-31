package com.ai.springdemo.aispringdemo.controller;

import com.ai.springdemo.aispringdemo.service.AISpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AISpringController {

    private final AISpringService aiSpringService;

    @Autowired
    public AISpringController(AISpringService aiSpringService){
        this.aiSpringService = aiSpringService;
    }
    @PostMapping("/question")
    public ResponseEntity<String>getQuestion(@RequestBody String question){
        return ResponseEntity.ok().body(aiSpringService.getAsk(question));
    }
    @GetMapping(value = "/image", produces = "image/jpeg")
    public ResponseEntity<InputStreamResource> getImage(@RequestParam(name = "image") String image) {
        return ResponseEntity.ok().body(aiSpringService.getImage(image));
    }
}
