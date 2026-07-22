package com.ecom.service.impl;


import com.ecom.pojo.entity.AIChatRequest;
import com.ecom.pojo.entity.AIResponse;
import com.ecom.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;
import org.springframework.http.HttpStatusCode;
import java.time.Duration;
import reactor.util.retry.Retry;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class AIServiceImpl implements AIService {

    @Autowired
    private WebClient fastApiWebClient;
    @Override
    public AIResponse sendChat(AIChatRequest aiChatRequest) {
        /**
        return fastApiWebClient.post()
                .uri("/admin/ai/chat")
                .bodyValue(aiChatRequest)
                .retrieve()
//                .onStatus(HttpStatusCode::is4xxClientError, new IllegalAccessError("no access"))
////                .onStatus(HttpStatusCode::is5xxServerError, new RuntimeException("Internal Error"))
//                .retryWhen(Retry.backoff(3, Duration.ofMillis(500)).filter(this::isRetryable))
//                .timeout(Duration.ofSeconds(10));
        ;
    }
         */
        return null;
    }
}
