package com.ecom.service;

import com.ecom.pojo.entity.AIChatRequest;
import com.ecom.pojo.entity.AIResponse;

public interface AIService {
    AIResponse sendChat(AIChatRequest aiChatRequest);
}
