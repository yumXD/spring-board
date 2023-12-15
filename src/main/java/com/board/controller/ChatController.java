package com.board.controller;

import com.board.dto.ChatMessage;
import com.board.entity.User;
import com.board.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final UserService userService;

    @MessageMapping("/chat")
    @SendTo("/sub/messages")
    public ChatMessage send(ChatMessage message) {
        return message;
    }

    @GetMapping("/talk")
    public String chatPage(Principal principal, Model model) {
        User user = userService.getUser(principal.getName());
        model.addAttribute("username", user.getActualUsername());
        return "chat";
    }
}
