package com.board.service;

import com.board.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatService {
    private final SimpMessagingTemplate messagingTemplate;

    public void handleIncomingMessage(ChatMessage message) {
        messagingTemplate.convertAndSend("/sub/messages", message);
    }
}
