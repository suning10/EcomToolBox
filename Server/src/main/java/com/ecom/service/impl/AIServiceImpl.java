package com.ecom.service.impl;


import com.ecom.pojo.entity.AIChatRequest;
import com.ecom.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;
import java.time.Duration;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class AIServiceImpl implements AIService {

    @Autowired
    private WebClient fastApiWebClient;

    @Override
    public Flux<String> sendChat(AIChatRequest aiChatRequest) {
        return fastApiWebClient.post()
                .uri("/admin/ai/chat")
                .bodyValue(aiChatRequest)
                .retrieve()
                .bodyToFlux(String.class)
                .retryWhen(Retry.backoff(3, Duration.ofMillis(500)).filter(this::isRetryable))
                .timeout(Duration.ofSeconds(10));
    }

    private boolean isRetryable(Throwable throwable) {
        if (throwable instanceof WebClientResponseException responseException) {
            return responseException.getStatusCode().is5xxServerError();
        }
        return throwable instanceof WebClientRequestException;
    }
}
