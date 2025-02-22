package com.barbosa.wolfChat.api.chat.dtos;

import com.barbosa.wolfChat.dto.chat.ChatUserDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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
