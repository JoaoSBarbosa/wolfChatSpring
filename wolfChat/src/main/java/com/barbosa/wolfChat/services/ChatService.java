package com.barbosa.wolfChat.services;

import com.barbosa.wolfChat.dto.chat.CreateChatRequestDTO;
import com.barbosa.wolfChat.entities.Chat;
import com.barbosa.wolfChat.entities.User;
import com.barbosa.wolfChat.repositories.ChatRepository;
import com.barbosa.wolfChat.repositories.UserRepository;
import com.barbosa.wolfChat.services.exception.ServiceNotFoudEntityException;
import com.barbosa.wolfChat.utils.CommonUtil.CommunUtils;
import com.barbosa.wolfChat.utils.model.ResponseUtil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public ChatService(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseUtil creatingChat(CreateChatRequestDTO dto) {

        Chat chat = new Chat();
        Set<User> users = new HashSet<>();

        List<Long> userIds = dto.getUsersIds();
        for (Long userId : userIds) {
            User user = userRepository.findById(userId).orElseThrow(() -> new ServiceNotFoudEntityException("Não foi possivel localizar usuario com o id informado"));
            users.add(user);
        }
        chat.setUsers(users);
        chat = chatRepository.save(chat);

        return ResponseUtil
                .builder()
                .message("Chat criado com sucesso!")
                .status(HttpStatus.CREATED)
                .data(CommunUtils.getDateTime())
                .data(chat)
                .build();

    }

    @Transactional(readOnly = true)
    public Page<Chat> getChats(Pageable pageable) {
        return chatRepository.findAll(pageable);
    }
}