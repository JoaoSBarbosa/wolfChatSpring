package com.barbosa.wolfChat.controllers;

import com.barbosa.wolfChat.entities.Message;
import com.barbosa.wolfChat.repositories.MessageRepository;
import com.barbosa.wolfChat.services.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageRepository messageRepository, MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Message message) {
        String content = messageService.sendMessage(message);
        return ResponseEntity.ok(content);
    }

    @GetMapping
    public ResponseEntity<Page<Message>> getAll(Pageable pageable) {
        Page<Message> messages = messageService.getAll(pageable);
        return ResponseEntity.ok(messages);
    }
}
