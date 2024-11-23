package com.barbosa.wolfChat.services;

import com.barbosa.wolfChat.dto.chat.CreateChatDTO;
import com.barbosa.wolfChat.dto.chat.CreateChatRequestDTO;
import com.barbosa.wolfChat.entities.Chat;
import com.barbosa.wolfChat.entities.ChatUser;
import com.barbosa.wolfChat.entities.User;
import com.barbosa.wolfChat.repositories.ChatRepository;
import com.barbosa.wolfChat.repositories.ChatUserRepository;
import com.barbosa.wolfChat.repositories.UserRepository;
import com.barbosa.wolfChat.services.exception.ServiceNotFoudEntityException;
import com.barbosa.wolfChat.utils.CommonUtil.CommunUtils;
import com.barbosa.wolfChat.utils.model.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ChatUserRepository chatUserRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public ChatService(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseUtil creatingChat(CreateChatDTO dto) {

        Chat chat = new Chat();
        if (dto.getChatName() != null) chat.setChatName(dto.getChatName());
        if (dto.getDescription() != null) chat.setDescription(dto.getDescription());

        // Adicionando usuários ao chat
        Set<User> users = new HashSet<>();
        List<Long> userIds = dto.getUserIds();

        for (Long userId : userIds) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ServiceNotFoudEntityException("Usuário não encontrado: " + userId));
            users.add(user);
        }
        chat.setUsers(users);
        // Salvando chat
        chat = chatRepository.save(chat);

        // Adicionando usuários no ChatUser
        for (User user : users) {
            ChatUser chatUser = new ChatUser();
            chatUser.setChat(chat);
            chatUser.setUser(user);
            chatUser.setIsAdmin( user.getUserId().equals( dto.getAdminId()));
            chatUserRepository.save(chatUser);
        }
        return ResponseUtil
                .builder()
                .message("Grupo criado com sucesso!")
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
