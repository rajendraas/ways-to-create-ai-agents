package io.github.rajendrasatpute.sample_spring_ai_agent.service;

import io.github.rajendrasatpute.sample_spring_ai_agent.tools.DateTimeTools;
import io.github.rajendrasatpute.sample_spring_ai_agent.tools.SunriseSunsetTool;
import io.github.rajendrasatpute.sample_spring_ai_agent.tools.WeatherTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgentService {

    private final ChatClient chatClient;
    private final WeatherTool weatherTool;
    private final SunriseSunsetTool sunriseSunsetTool;
    private final DateTimeTools dateTimeTools;

    Map<String, String> specialistRoutes = Map.of(
            "weather",
            """
                    	You are a weather specialist. You deal with temperature for any location. Always start with "Weather details:"
                    	Input: 
                    """,
            "sunset",
            """
                    	You are a sunset specialist. You deal with sunset time for any location. Always start with "Sunset details:"
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
        } else if (routeKey.toLowerCase().contains("sunset")) {
            return this.sunset(specialistRoutes.get("sunset") + userPrompt);
        }

        return this.defaultQuery(userPrompt);
    }

    private String determineRoute(String input, Iterable<String> availableRoutes) {
        log.debug("\nAvailable routes: " + availableRoutes);

        String selectorPrompt = String.format("""
                Analyze the input and select the most appropriate specialist only from these options: %s
                First explain your reasoning, then provide your selection in this JSON format:

                \\{
                    "reasoning": "Brief explanation of why this query should be routed to a specific specialist.",
                    "selection": "The chosen specialist name"
                \\}

                Input: %s""", availableRoutes, input);

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

    private String weather(String prompt) {
        return chatClient
                .prompt(prompt)
                .tools(
                        this.dateTimeTools,
                        this.weatherTool
                )
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

    record RoutingResponse(String reasoning, String selection) {
    }
}
