package io.github.rajendrasatpute.sample_spring_ai_agent.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgentService {

    private final ChatClient chatClient;
    private final ToolCallbackProvider tools;

    public String chat(String userPrompt) {
        return chatClient
                .prompt(userPrompt)
                .toolCallbacks(tools)
                .call()
                .content();
    }
}
