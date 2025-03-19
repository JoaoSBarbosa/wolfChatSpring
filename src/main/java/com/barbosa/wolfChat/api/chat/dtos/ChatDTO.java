package com.barbosa.wolfChat.api.chat.dtos;

import com.barbosa.wolfChat.api.user.dtos.UserClaims;
import com.barbosa.wolfChat.core.models.entities.Chat;
import com.barbosa.wolfChat.core.models.entities.ChatUser;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public ChatDTO(
            Long chatId,
            Boolean isGroup,
            String chatName,
            String description,
            LocalDateTime createdAt,
            UserClaims createdBy
    ) {
        this.chatId = chatId;
        this.isGroup = isGroup;
        this.chatName = chatName;
        this.description = description;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }
    public ChatDTO(
            Long chatId,
            Boolean isGroup,
            String chatName,
            String description,
            LocalDateTime createdAt,
            UserClaims createdBy,
            List<ChatUserDTO> chatUsers
    ) {
        this.chatId = chatId;
        this.isGroup = isGroup;
        this.chatName = chatName;
        this.description = description;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.chatUsers = chatUsers;
    }


    public ChatDTO(Chat chat) {

        chatId = chat.getChatId();
        isGroup = chat.getIsGroup();
        chatName = chat.getChatName();
        description = chat.getDescription();
        createdAt = chat.getCreatedAt();
        if(chat.getCreatedBy() != null) {
            createdBy = new UserClaims(chat.getCreatedBy());
        }
    }

    public ChatDTO(Chat chat, List<ChatUser> chatUsers) {
        this(chat);

        // ⚠️ chatUsers é um List<ChatUser> e você está tentando adicionar no List<ChatUserDTO> chatUsers
        chatUsers.forEach(chatUser -> this.chatUsers.add(new ChatUserDTO(chatUser)));
    }

}
