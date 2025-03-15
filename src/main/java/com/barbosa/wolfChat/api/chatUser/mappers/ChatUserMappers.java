package com.barbosa.wolfChat.api.chatUser.mappers;

import com.barbosa.wolfChat.api.chatUser.dtos.ChatUserDTO;
import com.barbosa.wolfChat.core.models.entities.ChatUser;

import java.util.List;

public interface ChatUserMappers {

    ChatUserDTO toChatUserDTO( ChatUser chatUser );
    List<ChatUserDTO> toChatUserDTOList( List<ChatUser> chatUsers );
}
