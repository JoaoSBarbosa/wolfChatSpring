package com.barbosa.wolfChat.api.chatUser.controllers;

import com.barbosa.wolfChat.api.chatUser.services.ChatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/chat_users")
public class ChatUserController {

    @Autowired
    ChatUserService chatUserService;

//    @DeleteMapping("/{chatId}/{userId}")
//    public ResponseEntity<String> removeUser(@PathVariable Long chatId, @PathVariable Long userId) {
//        chatUserService.removeUserFromChat(chatId, userId);
//        return ResponseEntity.ok("Usuário removido do chat.");
//    }
}
