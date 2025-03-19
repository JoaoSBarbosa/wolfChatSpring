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
        this.userId = chatUser.getUser().getUserId(); // ⚠️ Antes estava chatUser.getId(), que parece errado
        this.firstName = chatUser.getUser().getFirstName();
        this.lastName = chatUser.getUser().getLastName();
        this.isAdmin = chatUser.getIsAdmin();
        this.joinedAt = chatUser.getJoinedAt();
    }

}
