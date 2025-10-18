package io.github.rajendrasatpute.sample_spring_ai_agent.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

import io.github.rajendrasatpute.sample_spring_ai_agent.tools.DateTimeTools;
import io.github.rajendrasatpute.sample_spring_ai_agent.tools.SunriseSunsetTool;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgentService {

    private final ChatClient chatClient;
    private final ToolCallbackProvider tools;
    private final SunriseSunsetTool sunriseSunsetTool;
    private final DateTimeTools dateTimeTools;

    Map<String, String> specialistRoutes = Map.of(
            "weather",
            """
                    	You are a weather specialist. You deal with temperature for any location. Always start with "Weather details:"
                    	Input: 
                    """,
            "times",
            """
                    	You are a specialist for sunset and sunrise times at any specific location. Always start with "Time details:"
                    	Input: 
                    """
    );

    public String chat(String userPrompt) {
        String routeKey = determineRoute(userPrompt, specialistRoutes.keySet());

        if (null == routeKey) {
            return this.defaultQuery(userPrompt);
        }

        if (routeKey.toLowerCase().contains("weather")) {
            return this.weather(specialistRoutes.get("weather") + userPrompt);
        } else if (routeKey.toLowerCase().contains("times")) {
            return this.sunset(specialistRoutes.get("times") + userPrompt);
        }

        return this.defaultQuery(userPrompt);
    }

    private String determineRoute(String input, Iterable<String> availableRoutes) {
        log.debug("\nAvailable routes: " + availableRoutes);

        String selectorPrompt = String.format("""
                You are an export in query analysis. You will be given a query and based on that return the selection.

                ## Instructions:
                1. A query asking about time at specific location is related to times.
                2. A query about Weather, temperature, rain forcast or wind speed at specific location is related to weather.
                3. First explain your reasoning, then provide your selection in this JSON format:

                "{
                    "reasoning": "Brief explanation of why this query should be routed to a specific term.",
                    "selection": "Make sure you only return one of weather, times or generic as selection."
                }"

                Input: %s""", input);

        log.debug(selectorPrompt);

        RoutingResponse routingResponse = chatClient.prompt(selectorPrompt).call().entity(RoutingResponse.class);

        log.info(String.format(
                "Routing Analysis:%s\nSelected route: %s",
                routingResponse.reasoning(),
                routingResponse.selection()
        ));

        return routingResponse.selection();
    }

    private String defaultQuery(String prompt) {
        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }

    private String sunset(String prompt) {
        return chatClient
                .prompt(prompt)
                .tools(
                        this.dateTimeTools,
                        this.sunriseSunsetTool
                )
                .call()
                .content();
    }

    private String weather(String prompt) {
        return chatClient
                .prompt(prompt)
                .toolCallbacks(tools)
                .call()
                .content();
    }

    record RoutingResponse(String reasoning, String selection) {
    }
}
