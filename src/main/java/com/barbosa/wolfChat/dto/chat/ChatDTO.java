package com.barbosa.wolfChat.dto.chat;

import com.barbosa.wolfChat.dto.message.MessageDTO;
import com.barbosa.wolfChat.dto.user.UserMessageDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ChatDTO {

    private Long chatId;
    private String chatName;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<UserMessageDTO> users = new HashSet<>();
    private List<MessageDTO> messages = new ArrayList<>();
}
