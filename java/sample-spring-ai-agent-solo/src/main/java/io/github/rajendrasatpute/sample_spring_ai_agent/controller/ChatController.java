package io.github.rajendrasatpute.sample_spring_ai_agent.controller;

import io.github.rajendrasatpute.sample_spring_ai_agent.dto.ChatRequest;
import io.github.rajendrasatpute.sample_spring_ai_agent.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final AgentService agentService;

    @PostMapping("/chat")
    public ResponseEntity<Object> chat(@RequestBody ChatRequest chatRequest) {
        return ResponseEntity.ok().body(agentService.chat(chatRequest.getMessage()));
    }
}
