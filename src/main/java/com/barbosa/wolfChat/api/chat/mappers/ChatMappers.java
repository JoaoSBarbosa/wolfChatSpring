package com.barbosa.wolfChat.api.chat.mappers;

import com.barbosa.wolfChat.api.chat.dtos.ChatDTO;
import com.barbosa.wolfChat.core.models.entities.Chat;

public interface ChatMappers {

    ChatDTO toChatDTO(Chat chat);
}
