package io.github.rajendrasatpute.sample_mcp_server.tools;

import io.github.rajendrasatpute.sample_mcp_server.client.OpenMeteoClient;
import io.github.rajendrasatpute.sample_mcp_server.dto.WeatherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class WeatherTool {
    private final OpenMeteoClient openMeteoClient;

    @Tool(description = "Get the temperature (in celsius) for a specific location")
    public WeatherResponse weatherForecast(
            @ToolParam(description = "The location latitude") String latitude,
            @ToolParam(description = "The location longitude") String longitude
    ) {
        log.info("weatherForecast: Provided location - " + latitude + ", " + longitude);
        WeatherResponse weatherResponse = openMeteoClient.getWeather(latitude, longitude);

        return weatherResponse;
    }
}
