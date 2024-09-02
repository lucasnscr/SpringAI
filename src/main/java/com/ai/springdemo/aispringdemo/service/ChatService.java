package com.ai.springdemo.aispringdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    @Value("classpath:/prompts/system-qa.st")
    private Resource qaSystemPromptResource;

    @Value("classpath:/prompts/system-chatbot.st")
    private Resource chatbotSystemPromptResource;

    private final ChatClient chatClient;


    private final VectorStore vectorStore;

    @Autowired
    public ChatService(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        this.chatClient = chatClientBuilder.build();
        this.vectorStore = vectorStore;
    }

    public Flux<String> chat(String userMessageText) {
        Message systemMessage = createSystemMessage(userMessageText);
        UserMessage userMessage = new UserMessage(userMessageText);
        Prompt prompt = createPrompt(systemMessage, userMessage);

        logger.info("Sending prompt to AI model.");

        return chatClient.prompt(prompt)
                .stream()
                .chatResponse()
                .flatMap(this::processChatResponse)
                .doOnComplete(() -> logger.info("AI response processing completed."))
                .doOnError(error -> logger.error("Error during AI response processing", error));
    }

    private Message createSystemMessage(String userMessageText) {
        logger.info("Fetching relevant documents for message: {}", userMessageText);
        List<Document> relevantDocuments = vectorStore.similaritySearch(userMessageText);
        logger.info("Found {} relevant documents.", relevantDocuments.size());
        String documentsContent = relevantDocuments.stream()
                .map(Document::getContent)
                .collect(Collectors.joining("\n"));
        return new SystemPromptTemplate(qaSystemPromptResource)
                .createMessage(Map.of("documents", documentsContent));
    }

    private Prompt createPrompt(Message systemMessage, UserMessage userMessage) {
        return new Prompt(List.of(systemMessage, userMessage));
    }

    private Flux<String> processChatResponse(ChatResponse chatResponse) {
        return Optional.ofNullable(chatResponse)
                .filter(response -> response.getResults() != null && !response.getResults().isEmpty())
                .map(response -> response.getResults().getFirst())
                .map(result -> result.getOutput().getContent())
                .filter(content -> content != null)
                .map(Flux::just)
                .orElseGet(() -> {
                    logger.warn("Received an empty or null response.");
                    return Flux.empty();
                });
    }
}
