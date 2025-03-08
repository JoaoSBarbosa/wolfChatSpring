package com.barbosa.wolfChat.api.chatUser.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatUserDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private Boolean isAdmin;
    private LocalDateTime joinedAt;

}
