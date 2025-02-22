package com.barbosa.wolfChat.api.chat.services;

import com.barbosa.wolfChat.api.chat.dtos.ChatDTO;
import com.barbosa.wolfChat.dto.chat.ChatUserDTO;
import com.barbosa.wolfChat.dto.chat.CreateChatDTO;
import com.barbosa.wolfChat.models.entities.*;
import com.barbosa.wolfChat.repositories.ChatRepository;
import com.barbosa.wolfChat.repositories.ChatUserRepository;
import com.barbosa.wolfChat.repositories.MessageRepository;
import com.barbosa.wolfChat.repositories.UserRepository;
import com.barbosa.wolfChat.services.exception.ServiceNotFoudEntityException;
import com.barbosa.wolfChat.utils.CommonUtil.CommunUtils;
import com.barbosa.wolfChat.utils.model.ResponseUtil;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired ChatUserRepository chatUserRepository;
    @Autowired MessageRepository messageRepository;
    @Autowired private ChatRepository chatRepository;
    @Autowired private UserRepository userRepository;

//    public ChatService(ChatRepository chatRepository, UserRepository userRepository) {
//        this.chatRepository = chatRepository;
//        this.userRepository = userRepository;
//    }

    @Transactional
    public ResponseUtil creatingChat(CreateChatDTO dto) {

        System.out.println("VALOR DE IS GROUP: "+ dto.getIsGroup());
        if (Boolean.FALSE.equals(dto.getIsGroup())) {
            System.out.println("CAIU NO NÃO É GRUPO");
        }

        User creator = userRepository.findById(dto.getAdminId()).orElseThrow(() -> new EntityNotFoundException("Usuario criador não encontrado"));

        if (Boolean.FALSE.equals(dto.getIsGroup()) && dto.getUserIds().size() > 2) {
            return ResponseUtil.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .sendDateTime(CommunUtils.getDateTime())
                    .data(null)
                    .message("O chat individual só pode ter dois participantes")
                    .build();
        }

        Chat chat = new Chat();
        if (dto.getChatName() != null && dto.getIsGroup()) chat.setChatName(dto.getChatName());
        if (dto.getDescription() != null && dto.getIsGroup()) chat.setDescription(dto.getDescription());
        chat.setIsGroup(dto.getIsGroup());
        chat.setCreatedBy(creator.getUserId());
        chat.setCreatedAt(LocalDateTime.now());

        chat = chatRepository.saveAndFlush(chat);

        List<ChatUser> chatUsers = new ArrayList<>();
//        // Adicionando usuários ao chat
        for (Long participantId : dto.getUserIds()) {
            User participant = userRepository.findById(participantId).orElseThrow(() -> new ServiceNotFoudEntityException("Usuário não encontrado: " + participantId));

            ChatUser chatUser = new ChatUser();
            chatUser.setChatId( chat.getChatId() );
            chatUser.setUserId(participant.getUserId());
            chatUser.setIsAdmin(dto.getIsGroup() && participant.equals(dto.getAdminId()));
            chatUser.setJoinedAt(LocalDateTime.now());
            chatUsers.add(chatUser);

        }

        chatUserRepository.saveAll(chatUsers);

        return ResponseUtil
                .builder()
                .message("Grupo criado com sucesso!")
                .status(HttpStatus.CREATED)
                .data(CommunUtils.getDateTime())
                .data(chat)
                .build();
    }


    @Transactional(readOnly = true)
    public Page<ChatDTO> getChats(Pageable pageable) {
        Page<Chat> chats = chatRepository.findAll(pageable);

        // Converte entidades para DTOs
        return chats.map(chat -> {
            ChatDTO chatDTO = new ChatDTO();
            chatDTO.setChatId(chat.getChatId());
            chatDTO.setIsGroup(chat.getIsGroup());
            chatDTO.setChatName(chat.getChatName());
            chatDTO.setDescription(chat.getDescription());
            chatDTO.setCreatedAt(chat.getCreatedAt());
            chatDTO.setCreatedBy(chat.getCreatedBy());

            // Mapeia ChatUsers para DTOs
            List<ChatUserDTO> chatUsers = chat.getChatUsers().stream().map(chatUser -> {
                ChatUserDTO chatUserDTO = new ChatUserDTO();
                chatUserDTO.setUserId(chatUser.getUserId());
                chatUserDTO.setIsAdmin(chatUser.getIsAdmin());
                chatUserDTO.setJoinedAt(chatUser.getJoinedAt());
                return chatUserDTO;
            }).collect(Collectors.toList());

            chatDTO.setChatUsers(chatUsers);
            return chatDTO;
        });
    }


//    @Transactional(readOnly = true)
//    public Page<Chat> getChats(Pageable pageable) {
//        return chatRepository.findAll(pageable);
//    }


    @Transactional
    public void addUserToGroup(Long chatId, Long adminId, Long userId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("Chat not found"));

        if (!chat.getIsGroup()) {
            throw new IllegalArgumentException("Cannot add users to an individual chat");
        }

        // Check if the admin is actually an admin in the group
        ChatUser admin = chatUserRepository.findByChatIdAndUserId(chatId, adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found in the group"));

        if (!admin.getIsAdmin()) {
            throw new IllegalArgumentException("Only admins can add users to a group");
        }

        // Check if the user is already in the group
        Optional<ChatUser> existingUser = chatUserRepository.findByChatIdAndUserId(chatId, userId);
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("User is already in the group");
        }

        // Add the new user to the group
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        ChatUser chatUser = new ChatUser();
        chatUser.setChat(chat);
        chatUser.setUser(user);
        chatUser.setIsAdmin(false); // New users are not admins by default
        chatUser.setJoinedAt(LocalDateTime.now());

        chatUserRepository.save(chatUser);
    }

    /**
     * Remove a user from a group chat.
     * Only admins can remove users.
     * If the removed user is the last admin, assign a new admin automatically.
     */
//    @Transactional
//    public void removeUserFromGroup(Long chatId, Long adminId, Long userId) {
//        Chat chat = chatRepository.findById(chatId)
//                .orElseThrow(() -> new IllegalArgumentException("Chat not found"));
//
//        if (!chat.getIsGroup()) {
//            throw new IllegalArgumentException("Cannot remove users from an individual chat");
//        }
//
//        // Check if the admin is actually an admin in the group
//        ChatUser admin = chatUserRepository.findByChatIdAndUserId(chatId, adminId)
//                .orElseThrow(() -> new IllegalArgumentException("Admin not found in the group"));
//
//        if (!admin.getIsAdmin()) {
//            throw new IllegalArgumentException("Only admins can remove users from a group");
//        }
//
//        // Remove the user
//        ChatUser userToRemove = chatUserRepository.findByChatIdAndUserId(chatId, userId)
//                .orElseThrow(() -> new IllegalArgumentException("User not found in the group"));
//
//        chatUserRepository.delete(userToRemove);
//
//        // If the user removed is an admin and was the last admin, assign a new admin
//        if (userToRemove.getIsAdmin()) {
//            List<ChatUser> remainingAdmins = chatUserRepository.findAdminsByChatId(chatId);
//            if (remainingAdmins.isEmpty()) {
//                // Assign the oldest user as the new admin
//                ChatUser oldestUser = chatUserRepository.findOldestUserInChat(chatId)
//                        .orElseThrow(() -> new IllegalStateException("No users remaining in chat"));
//
//                oldestUser.setIsAdmin(true);
//                chatUserRepository.save(oldestUser);
//            }
//        }
//
//        // If the group is empty after removal, delete the group
//        if (chatUserRepository.countByChatId(chatId) == 0) {
//            chatRepository.delete(chat);
//        }
//    }


    @Transactional
    public void promoteUserToAdmin(Long chatId, Long adminId, Long userId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("Chat not found"));

        if (!chat.getIsGroup()) {
            throw new IllegalArgumentException("Cannot promote users in an individual chat");
        }

        // Check if the admin is actually an admin in the group
        ChatUser admin = chatUserRepository.findByChatIdAndUserId(chatId, adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found in the group"));

        if (!admin.getIsAdmin()) {
            throw new IllegalArgumentException("Only admins can promote users in a group");
        }

        // Promote the user
        ChatUser userToPromote = chatUserRepository.findByChatIdAndUserId(chatId, userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found in the group"));

        userToPromote.setIsAdmin(true);
        chatUserRepository.save(userToPromote);
    }

    /**
     * Send a message in a chat.
     */
    @Transactional
    public Message sendMessage(Long chatId, Long senderId, String content) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("Chat not found"));

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("Sender not found"));

        // Create the message
        Message message = new Message();
        message.setChat(chat);
        message.setSender(sender);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());

        return messageRepository.save(message);
    }

    /**
     * Mark a message as viewed by a user.
     */
    @Transactional
    public void markMessageAsViewed(Long messageId, Long viewerId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Message not found"));

        User viewer = userRepository.findById(viewerId)
                .orElseThrow(() -> new IllegalArgumentException("Viewer not found"));

        if (message.getViewedBy().stream().anyMatch(view -> view.getViewer().equals(viewer))) {
            throw new IllegalArgumentException("Message already viewed by this user");
        }

        MessageView messageView = new MessageView();
        messageView.setMessage(message);
        messageView.setViewer(viewer);
        messageView.setViewedAt(LocalDateTime.now());

        message.getViewedBy().add(messageView);
        messageRepository.save(message);
    }
}
