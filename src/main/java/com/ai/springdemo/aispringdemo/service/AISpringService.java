package com.ai.springdemo.aispringdemo.service;

import org.springframework.ai.client.AiClient;
import org.springframework.ai.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AISpringService {

    private final AiClient aiClient;
    @Value("openai.api-key")
    private String apiKey;

    public AISpringService(AiClient aiClient){
        this.aiClient = aiClient;
    }

    public String getAsk(String question){
        PromptTemplate promptTemplate = new PromptTemplate("");
        promptTemplate.add("question", question);
        return this.aiClient.generate(promptTemplate.create()).getGeneration().getText();
    }

}
