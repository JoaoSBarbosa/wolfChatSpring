package com.barbosa.wolfChat.api.chat.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import com.barbosa.wolfChat.api.chatUser.dtos.ChatUserDTO;

@Data
public class ChatDTO {
    private Long chatId;
    private Boolean isGroup;
    private String chatName;
    private String description;
    private LocalDateTime createdAt;
    private Long createdBy;
    private List<ChatUserDTO> chatUsers;
}
