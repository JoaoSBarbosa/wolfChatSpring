package com.barbosa.wolfChat.api.message.controllers;

import com.barbosa.wolfChat.core.models.entities.Message;
import com.barbosa.wolfChat.repositories.MessageRepository;
import com.barbosa.wolfChat.api.message.services.MessageServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageServiceImpl messageServiceImpl;

    public MessageController(MessageRepository messageRepository, MessageServiceImpl messageServiceImpl) {
        this.messageServiceImpl = messageServiceImpl;
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Message message) {
        String content = messageServiceImpl.sendMessage(message);
        return ResponseEntity.ok(content);
    }

    @GetMapping
    public ResponseEntity<Page<Message>> getAll(Pageable pageable) {
        Page<Message> messages = messageServiceImpl.getAll(pageable);
        return ResponseEntity.ok(messages);
    }
}
