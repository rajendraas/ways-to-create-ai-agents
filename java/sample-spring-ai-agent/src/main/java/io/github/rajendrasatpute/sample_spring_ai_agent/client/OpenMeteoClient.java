package io.github.rajendrasatpute.sample_spring_ai_agent.client;

import io.github.rajendrasatpute.sample_spring_ai_agent.dto.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "open-meteo", url = "${client.openmeteo.domain}")
public interface OpenMeteoClient {
    @GetMapping("/forecast?current=temperature_2m,wind_speed_10m&timezone=IST")
    WeatherResponse getWeather(@RequestParam String latitude, @RequestParam String longitude);
}
