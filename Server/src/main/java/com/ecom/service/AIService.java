package com.ecom.service;

import com.ecom.pojo.entity.AIChatRequest;
import reactor.core.publisher.Flux;

public interface AIService {
    Flux<String> sendChat(AIChatRequest aiChatRequest);
}
