package com.barbosa.wolfChat.api.chatUser.mappers;

import com.barbosa.wolfChat.api.chatUser.dtos.ChatUserDTO;
import com.barbosa.wolfChat.core.models.entities.ChatUser;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChatUserMappersImpl implements ChatUserMappers {
    @Override
    public ChatUserDTO toChatUserDTO(ChatUser chatUser) {
        if(chatUser == null) return null;

        return ChatUserDTO
                .builder()
                .userId( chatUser.getId() )
                .firstName( chatUser.getUser().getFirstName() )
                .lastName( chatUser.getUser().getLastName() )
                .isAdmin( chatUser.getIsAdmin() )
                .joinedAt( chatUser.getJoinedAt() )
                .build();
    }

    @Override
    public List<ChatUserDTO> toChatUserDTOList(List<ChatUser> chatUsers) {
        if(chatUsers == null) return Collections.emptyList();
        return chatUsers.stream().map(ChatUserDTO::new).collect( Collectors.toList());
    }
}
