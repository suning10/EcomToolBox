package com.ecom.service;

import com.ecom.pojo.entity.AIChatRequest;
import com.ecom.pojo.entity.AIChatSessionMessages;
import com.ecom.pojo.entity.AIChatSessionRead;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

import java.util.List;

public interface AIService {
    Flux<ServerSentEvent<String>> sendChat(AIChatRequest aiChatRequest);

    List<AIChatSessionRead> getChatSessions();

    AIChatSessionMessages getChatSessionMessages(String sessionId);
}
