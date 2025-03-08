package com.barbosa.wolfChat.api.chat.controllers;
import com.barbosa.wolfChat.api.chat.dtos.ChatDTO;
import com.barbosa.wolfChat.api.chat.dtos.CreateChatDTO;
import com.barbosa.wolfChat.api.chat.services.ChatServiceImpl;
import com.barbosa.wolfChat.utils.model.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/chats")
public class ChatController {

    private final ChatServiceImpl chatServiceImpl;

    public ChatController(final ChatServiceImpl chatServiceImpl) {
        this.chatServiceImpl = chatServiceImpl;
    }


    @PostMapping
    public ResponseEntity<ResponseUtil> addChat(@Valid @RequestBody CreateChatDTO userId) {
        ResponseUtil responseUtil = chatServiceImpl.creatingChat(userId);
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }

    @GetMapping
    public ResponseEntity<Page<ChatDTO>> getChats(Pageable pageable) {
        Page<ChatDTO> chats = chatServiceImpl.getChats(pageable);
        return ResponseEntity.ok().body(chats);
    }
}
