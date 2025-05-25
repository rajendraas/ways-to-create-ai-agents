package io.github.rajendrasatpute.sample_mcp_server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherResponse(@JsonProperty("current") Current current) {
    public record Current(
            @JsonProperty("time") LocalDateTime time,
            @JsonProperty("interval") int interval,
            @JsonProperty("temperature_2m") double temperature_2m) {
    }
}
