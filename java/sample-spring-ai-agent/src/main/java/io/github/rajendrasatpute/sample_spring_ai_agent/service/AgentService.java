package io.github.rajendrasatpute.sample_spring_ai_agent.service;

import io.github.rajendrasatpute.sample_spring_ai_agent.tools.DateTimeTools;
import io.github.rajendrasatpute.sample_spring_ai_agent.tools.WeatherTool;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgentService {

    private final ChatClient chatClient;
    private final WeatherTool weatherTool;
    private final DateTimeTools dateTimeTools;


    public String chat(String userPrompt) {
        return chatClient
                .prompt(userPrompt)
                .tools(this.dateTimeTools, this.weatherTool)
                .call()
                .content();
    }
}
