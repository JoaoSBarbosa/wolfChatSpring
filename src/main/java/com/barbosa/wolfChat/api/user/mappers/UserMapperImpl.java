package com.barbosa.wolfChat.api.user.mappers;

import com.barbosa.wolfChat.api.user.dtos.UserClaims;
import com.barbosa.wolfChat.api.user.dtos.UserRequest;
import com.barbosa.wolfChat.core.models.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMappers {

    @Override
    public UserClaims toUserClaims(User user) {
        if (user == null) return null;

        return UserClaims
                .builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .linkDropboxImage(user.getLinkDropboxImage())
                .imageUri(user.getImageUri())
                .build();
    }

    @Override
    public UserRequest toUserRequest(User user) {
        if (user == null) return null;

        return UserRequest
                .builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .linkDropboxImage(user.getLinkDropboxImage())
                .imageUri(user.getImageUri())
                .build();
    }
}
