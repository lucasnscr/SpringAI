package com.ai.springdemo.aispringdemo.service;

import com.ai.springdemo.aispringdemo.models.Image;
import org.springframework.ai.client.AiClient;
import org.springframework.ai.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class AISpringService {

    private final AiClient aiClient;

    @Value("${spring.ai.openai.api-key}")
    private static String apiKey;

    @Value("${spring.ai.openai.imageUrl}")
    private static String openAIImageUrl;

    @Autowired
    public AISpringService(AiClient aiClient) {
        this.aiClient = aiClient;
    }

    public String getAsk(String question){
        return aiClient.generate(question);
    }

    public InputStreamResource getImage(String topic) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                 I am really board from online memes. Can you create me a prompt about {topic}.
                 Elevate the given topic. Make it classy.
                 Make a resolution of 256x256, but ensure that it is presented in json it need to be string.
                 I desire only one creation. Give me as JSON format: prompt, n, size.
                """);
        promptTemplate.add("topic", topic);
        String imagePrompt = this.aiClient.generate(promptTemplate.create()).getGeneration().getText();
        return fetchImageAsStream(imagePrompt);
    }

    private static InputStreamResource fetchImageAsStream(String imagePrompt) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = createHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(imagePrompt, headers);

        String imageUrl = extractImageUrl(restTemplate, httpEntity);
        return downloadImageAsResource(restTemplate, imageUrl);
    }

    private static HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + apiKey);
        headers.add("Content-Type", "application/json");
        return headers;
    }

    private static String extractImageUrl(RestTemplate restTemplate, HttpEntity<String> httpEntity) {
        ResponseEntity<Image> response = restTemplate.exchange(
                openAIImageUrl, HttpMethod.POST, httpEntity, Image.class);
        Image body = response.getBody();
        if (body == null || body.getData().isEmpty()) {
            throw new IllegalStateException("Failed to retrieve image URL");
        }
        return body.getData().get(0);
    }

    private static InputStreamResource downloadImageAsResource(RestTemplate restTemplate, String imageUrl) {
        byte[] imageBytes;
        try {
            imageBytes = restTemplate.getForObject(new URI(imageUrl), byte[].class);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid image URL", e);
        }

        if (imageBytes == null) {
            throw new IllegalStateException("Failed to download image");
        }

        InputStreamResource inputStreamResource;
        inputStreamResource = new InputStreamResource(new ByteArrayInputStream(imageBytes));
        return inputStreamResource;
    }

}
