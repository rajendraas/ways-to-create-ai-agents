package io.github.rajendrasatpute.sample_spring_ai_agent.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SunriseSunsetResponse(@JsonProperty("result") SunriseSunsetResult result) {
    public record SunriseSunsetResult(
            @JsonProperty("sunrise") String sunrise,
            @JsonProperty("sunset") String sunset,
            @JsonProperty("day_length") String day_length
    ) {
    }
}
