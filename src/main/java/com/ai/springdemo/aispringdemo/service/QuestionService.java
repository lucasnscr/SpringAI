package com.ai.springdemo.aispringdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);

    @Value("classpath:/prompts/system-qa.st")
    private Resource qaSystemPromptResource;

    @Value("classpath:/prompts/system-chatbot.st")
    private Resource chatbotSystemPromptResource;

    private final ChatClient aiClient;

    private final VectorStore vectorStore;

    @Autowired
    public QuestionService(ChatClient aiClient, VectorStore vectorStore) {
        this.aiClient = aiClient;
        this.vectorStore = vectorStore;
    }

    public String question(String message, boolean prompstuff) {
        Message systemMessage = getSystemMessage(message, prompstuff);
        UserMessage userMessage = new UserMessage(message);
        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));

        logger.info("Asking AI model to reply to question.");
        ChatResponse aiResponse = aiClient.call(prompt);
        logger.info("AI responded.");
        return aiResponse.getResult().getMetadata().getFinishReason();
    }

    private Message getSystemMessage(String message, boolean prompstuff) {
        if (prompstuff) {
            logger.info("Retrieving relevant documents");
            List<Document> similarDocuments = vectorStore.similaritySearch(message);
            logger.info(String.format("Found %s relevant documents.", similarDocuments.size()));
            String documents = similarDocuments.stream().map(Document::getContent).collect(Collectors.joining("\n"));
            SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(this.qaSystemPromptResource);
            return systemPromptTemplate.createMessage(Map.of("documents", documents));
        } else {
            logger.info("Not stuffing the prompt, using generic prompt");
            return new SystemPromptTemplate(this.chatbotSystemPromptResource).createMessage();
        }
    }


    public String simpleQuestion(String question) {
            return aiClient.call(question);
    }
}
