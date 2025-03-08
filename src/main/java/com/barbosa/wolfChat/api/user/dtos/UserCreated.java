package com.barbosa.wolfChat.api.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreated {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
}
