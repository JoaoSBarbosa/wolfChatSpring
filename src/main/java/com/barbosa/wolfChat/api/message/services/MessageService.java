package com.barbosa.wolfChat.api.message.services;

import com.barbosa.wolfChat.core.models.entities.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {

    String sendMessage(Message message);
    Page<Message> getAll(Pageable pageable);

}
