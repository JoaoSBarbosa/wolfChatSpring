package com.barbosa.wolfChat.controllers;

import com.barbosa.wolfChat.dto.chat.CreateChatRequestDTO;
import com.barbosa.wolfChat.entities.Chat;
import com.barbosa.wolfChat.services.ChatService;
import com.barbosa.wolfChat.utils.model.ResponseUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {

    private final ChatService chatService;

    public ChatController(final ChatService chatService) {
        this.chatService = chatService;
    }


    @PostMapping
    public ResponseEntity<ResponseUtil> addChat(@RequestBody CreateChatRequestDTO userId) {
        ResponseUtil responseUtil = chatService.creatingChat(userId);
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }

    @GetMapping
    public ResponseEntity<Page<Chat>> getChats(Pageable pageable) {
        Page<Chat> chats = chatService.getChats(pageable);
        return ResponseEntity.ok().body(chats);
    }
}
