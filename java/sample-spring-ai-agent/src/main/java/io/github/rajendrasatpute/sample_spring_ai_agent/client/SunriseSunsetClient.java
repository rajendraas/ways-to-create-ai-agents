package io.github.rajendrasatpute.sample_spring_ai_agent.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "sunset-sunrise", url = "${client.sunsetsunrise.domain}")
public interface SunriseSunsetClient {
    @GetMapping("/json?tzid=Asia/Kolkata")
    Object getSunriseSunsetTimes(@RequestParam String lat, @RequestParam String lng);
}
