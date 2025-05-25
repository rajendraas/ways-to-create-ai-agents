package io.github.rajendrasatpute.sample_spring_ai_agent.tools;

import io.github.rajendrasatpute.sample_spring_ai_agent.client.OpenMeteoClient;
import io.github.rajendrasatpute.sample_spring_ai_agent.dto.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WeatherTool {
    private final OpenMeteoClient openMeteoClient;

    @Tool(description = "Get the temperature (in celsius) for a specific location")
    public WeatherResponse weatherForecast(
            @ToolParam(description = "The location latitude") String latitude,
            @ToolParam(description = "The location longitude") String longitude
    ) {

        WeatherResponse weatherResponse = openMeteoClient.getWeather(latitude, longitude);

        return weatherResponse;
    }
}
