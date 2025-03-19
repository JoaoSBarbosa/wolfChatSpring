package com.barbosa.wolfChat.api.chatUser.dtos;

import com.barbosa.wolfChat.core.models.entities.ChatUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatUserDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private Boolean isAdmin;
    private LocalDateTime joinedAt;

    public ChatUserDTO(ChatUser chatUser) {
        this.userId = chatUser.getId();
        firstName = chatUser.getUser().getFirstName();
        lastName = chatUser.getUser().getLastName();
        isAdmin = chatUser.getIsAdmin();
        joinedAt = chatUser.getJoinedAt();
    }

}
