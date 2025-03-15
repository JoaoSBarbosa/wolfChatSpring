package com.barbosa.wolfChat.api.chat.utils;

import com.barbosa.wolfChat.api.chat.dtos.CreateChatWithMessageDTO;
import com.barbosa.wolfChat.core.models.entities.Chat;
import com.barbosa.wolfChat.core.models.entities.ChatUser;
import com.barbosa.wolfChat.core.models.entities.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class ChatUtil {


    public static void validateChatCreation(CreateChatWithMessageDTO dto){
        if(Boolean.FALSE.equals(dto.getIsGroup()) && dto.getUserIds().size() > 2){
            throw new IllegalArgumentException("O chat individual só pode ter dois participantes");
        }
    }


}
