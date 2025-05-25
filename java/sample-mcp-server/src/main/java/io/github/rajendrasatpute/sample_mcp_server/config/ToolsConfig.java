package io.github.rajendrasatpute.sample_mcp_server.config;

import io.github.rajendrasatpute.sample_mcp_server.tools.DateTimeTools;
import io.github.rajendrasatpute.sample_mcp_server.tools.WeatherTool;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolsConfig {
    @Bean
    public ToolCallbackProvider tools(WeatherTool weatherTool, DateTimeTools dateTimeTools) {
        return MethodToolCallbackProvider
                .builder()
                .toolObjects(weatherTool, dateTimeTools)
                .build();
    }
}
