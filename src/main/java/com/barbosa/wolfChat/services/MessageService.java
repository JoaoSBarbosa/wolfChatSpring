package com.barbosa.wolfChat.services;

import com.barbosa.wolfChat.entities.Message;
import com.barbosa.wolfChat.repositories.ChatRepository;
import com.barbosa.wolfChat.repositories.MessageRepository;
import com.barbosa.wolfChat.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository, ChatRepository chatRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
    }

    @Transactional
    public String sendMessage(Message message) {
        Message entity = new Message();
        entity.setChat(message.getChat());
        entity.setSender(message.getSender());
        entity.setContent(message.getContent());
        messageRepository.save(entity);
        return "A mensagem [" + message.getContent() + "] foi enviada com sucesso!";

    }

    @Transactional(readOnly = true)
    public Page<Message> getAll(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }
}
