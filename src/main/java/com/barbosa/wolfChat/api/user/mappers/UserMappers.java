package com.barbosa.wolfChat.api.user.mappers;

import com.barbosa.wolfChat.api.user.dtos.UserClaims;
import com.barbosa.wolfChat.api.user.dtos.UserRequest;
import com.barbosa.wolfChat.core.models.entities.User;

public interface UserMappers {

    UserClaims toUserClaims(User user);
    UserRequest toUserRequest(User user);
}
