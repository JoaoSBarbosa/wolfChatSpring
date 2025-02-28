package com.barbosa.wolfChat.api.message.services;

import com.barbosa.wolfChat.core.models.entities.Message;
import com.barbosa.wolfChat.repositories.ChatRepository;
import com.barbosa.wolfChat.repositories.MessageRepository;
import com.barbosa.wolfChat.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;


    @Transactional
    public String sendMessage(Message message) {
        Message entity = new Message();
        entity.setChatId( message.getChatId() );
        entity.setUserId(message.getUserId());
        entity.setContent(message.getContent());
        messageRepository.save(entity);
        return "A mensagem [" + message.getContent() + "] foi enviada com sucesso!";

    }

    @Transactional(readOnly = true)
    public Page<Message> getAll(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }
}
