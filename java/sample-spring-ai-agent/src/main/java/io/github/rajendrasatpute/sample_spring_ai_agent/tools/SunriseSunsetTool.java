package io.github.rajendrasatpute.sample_spring_ai_agent.tools;

import io.github.rajendrasatpute.sample_spring_ai_agent.client.SunriseSunsetClient;
import io.github.rajendrasatpute.sample_spring_ai_agent.dto.SunriseSunsetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class SunriseSunsetTool {
    private final SunriseSunsetClient sunriseSunsetClient;

    @Tool(description = "Get the sunrise and sunset times for a specific location")
    public SunriseSunsetResponse sunriseSunset(
            @ToolParam(description = "The location latitude") String latitude,
            @ToolParam(description = "The location longitude") String longitude
    ) {
        log.info("sunriseSunset: Provided location - " + latitude + ", " + longitude);
        SunriseSunsetResponse sunriseSunsetResponse = sunriseSunsetClient.getSunriseSunsetTimes(latitude, longitude);

        return sunriseSunsetResponse;
    }
}
