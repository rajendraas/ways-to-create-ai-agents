package io.github.rajendrasatpute.sample_spring_ai_agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SampleSpringAiAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleSpringAiAgentApplication.class, args);
    }

}
