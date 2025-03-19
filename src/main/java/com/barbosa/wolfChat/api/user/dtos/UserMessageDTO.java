package com.barbosa.wolfChat.api.user.dtos;

import com.barbosa.wolfChat.core.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMessageDTO {

    private Long userId;
    private String firstName;
    private String lastName;

    public UserMessageDTO(User user) {
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }
}