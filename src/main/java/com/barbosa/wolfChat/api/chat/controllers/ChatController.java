package com.barbosa.wolfChat.api.chat.controllers;
import com.barbosa.wolfChat.api.chat.dtos.ChatDTO;
import com.barbosa.wolfChat.dto.chat.CreateChatDTO;
import com.barbosa.wolfChat.api.chat.services.ChatService;
import com.barbosa.wolfChat.utils.model.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/chats")
public class ChatController {

    private final ChatService chatService;

    public ChatController(final ChatService chatService) {
        this.chatService = chatService;
    }


    @PostMapping
    public ResponseEntity<ResponseUtil> addChat(@Valid @RequestBody CreateChatDTO userId) {
        ResponseUtil responseUtil = chatService.creatingChat(userId);
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }

    @GetMapping
    public ResponseEntity<Page<ChatDTO>> getChats(Pageable pageable) {
        Page<ChatDTO> chats = chatService.getChats(pageable);
        return ResponseEntity.ok().body(chats);
    }
}
