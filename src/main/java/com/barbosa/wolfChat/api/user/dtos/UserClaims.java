package com.barbosa.wolfChat.api.user.dtos;

import com.barbosa.wolfChat.core.models.entities.User;
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


    public UserClaims(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.imageUri = user.getImageUri();
        this.linkDropboxImage = user.getLinkDropboxImage();

    }
}
