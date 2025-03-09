package com.barbosa.wolfChat.api.user.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserClaims {

    private String firstName;
    private String lastName;
    private String imageUri;
    private String linkDropboxImage;
}
