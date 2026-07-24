package com.ecom.service.impl;


import com.ecom.pojo.entity.AIChatRequest;
import com.ecom.pojo.entity.AIChatSessionMessages;
import com.ecom.pojo.entity.AIChatSessionRead;
import com.ecom.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;
import java.time.Duration;
import java.util.List;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
@Service
public class AIServiceImpl implements AIService {

    @Autowired
    private WebClient fastApiWebClient;

    @Override
    public Flux<ServerSentEvent<String>> sendChat(AIChatRequest aiChatRequest) {
        return fastApiWebClient.post()
                .uri("/admin/ai/chat")
                .bodyValue(aiChatRequest)
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<ServerSentEvent<String>>() {})
                .retryWhen(Retry.backoff(3, Duration.ofMillis(500)).filter(this::isRetryable))
                .timeout(Duration.ofSeconds(10));
    }

    @Override
    public List<AIChatSessionRead> getChatSessions() {
        return fastApiWebClient.get()
                .uri("/admin/ai/chat/sessions")
                .retrieve()
                .bodyToFlux(AIChatSessionRead.class)
                .retryWhen(Retry.backoff(3, Duration.ofMillis(500)).filter(this::isRetryable))
                .timeout(Duration.ofSeconds(10))
                .collectList()
                .block();
    }

    @Override
    public AIChatSessionMessages getChatSessionMessages(String sessionId) {
        return fastApiWebClient.get()
                .uri("/admin/ai/chat/sessions/{sessionId}/messages", sessionId)
                .retrieve()
                .bodyToMono(AIChatSessionMessages.class)
                .retryWhen(Retry.backoff(3, Duration.ofMillis(500)).filter(this::isRetryable))
                .timeout(Duration.ofSeconds(10))
                .block();
    }

    private boolean isRetryable(Throwable throwable) {
        if (throwable instanceof WebClientResponseException responseException) {
            return responseException.getStatusCode().is5xxServerError();
        }
        return throwable instanceof WebClientRequestException;
    }
}
