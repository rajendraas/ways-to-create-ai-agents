package io.github.rajendrasatpute.sample_spring_ai_agent.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    private final ChatClient chatClient;

    public AgentService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String userPrompt) {
        return chatClient.prompt(userPrompt).call().content();
    }
}
