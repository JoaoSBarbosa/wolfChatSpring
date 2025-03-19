package com.barbosa.wolfChat.api.chat.dtos;

import com.barbosa.wolfChat.api.message.dtos.MessageDTO;
import com.barbosa.wolfChat.api.user.dtos.UserClaims;
import com.barbosa.wolfChat.core.models.entities.Chat;
import com.barbosa.wolfChat.core.models.entities.ChatUser;
import com.barbosa.wolfChat.core.models.entities.Message;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.barbosa.wolfChat.api.chatUser.dtos.ChatUserDTO;

@Data
@Builder
public class ChatDTO {

    private Long chatId;
    private Boolean isGroup;
    private String chatName;
    private String description;
    private LocalDateTime createdAt;
    private UserClaims createdBy;
    private List<ChatUserDTO> chatUsers = new ArrayList<>();
    private List<MessageDTO> messages = new ArrayList<>();

    public static ChatDTO fromEntity(Chat chat) {

        List<ChatUserDTO> chatUsersDTO = chat.getChatUsers()
                .stream()
                .map(ChatUserDTO::new)
                .collect(Collectors.toList());

        List<MessageDTO> messagesDTO = chat.getMessages()
                .stream()
                .map(MessageDTO::fromEntity) // ✅ usa o método padronizado!
                .collect(Collectors.toList());

        return ChatDTO.builder()
                .chatId(chat.getChatId())
                .isGroup(chat.getIsGroup())
                .chatName(chat.getChatName())
                .description(chat.getDescription())
                .createdAt(chat.getCreatedAt())
                .createdBy(new UserClaims(chat.getCreatedBy()))
                .chatUsers(chatUsersDTO)
                .messages(messagesDTO)
                .build();
    }
}
