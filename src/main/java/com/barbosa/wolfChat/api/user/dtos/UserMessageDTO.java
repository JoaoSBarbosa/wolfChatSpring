package com.barbosa.wolfChat.api.user.dtos;

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
