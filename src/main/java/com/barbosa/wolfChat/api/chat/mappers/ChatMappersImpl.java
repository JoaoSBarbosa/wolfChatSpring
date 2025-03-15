package com.barbosa.wolfChat.api.chat.mappers;

import com.barbosa.wolfChat.api.chat.dtos.ChatDTO;
import com.barbosa.wolfChat.api.chatUser.mappers.ChatUserMappers;
import com.barbosa.wolfChat.core.models.entities.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatMappersImpl implements ChatMappers {

    private final ChatUserMappers chatUserMappers;
    @Override
    public ChatDTO toChatDTO(Chat chat) {
        if(chat == null) return null;
        return ChatDTO
                .builder()
                .chatId( chat.getChatId() )
                .isGroup( chat.getIsGroup() )
                .chatName( chat.getChatName() )
                .chatUsers( chatUserMappers.toChatUserDTOList(chat.getChatUsers()) )
                .createdAt( chat.getCreatedAt() )
                .createdBy( chat.getCreatedBy() )
                .description(chat.getDescription() )
                .build();
    }
}
