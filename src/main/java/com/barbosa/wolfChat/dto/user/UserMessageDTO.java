package com.barbosa.wolfChat.dto.user;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserMessageDTO {
    private Long userId;
    private String firstName;
    private String lastName;
}
