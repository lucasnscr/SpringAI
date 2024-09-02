package com.ai.springdemo.aispringdemo.controller;

import com.ai.springdemo.aispringdemo.exception.InvalidMessageException;
import com.ai.springdemo.aispringdemo.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final ChatService questionService;

    @Autowired
    public ChatController(ChatService questionService){
        this.questionService = questionService;
    }

    @PostMapping("/chat")
    public Flux<String> getQuestion(@RequestBody String question){

        logger.info("Received question: {}", question);

        if (question == null || question.trim().isEmpty()) {
            logger.error("Invalid question received: empty or null message");
            return Flux.error(new InvalidMessageException("Question cannot be null or empty"));
        }

        return questionService.chat(question)
                .doOnError(error -> logger.error("Error processing question: {}", error.getMessage()));
    }

    @ExceptionHandler(InvalidMessageException.class)
    public ResponseEntity<String> handleInvalidMessageException(InvalidMessageException e) {
        logger.error("Invalid message error: {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
