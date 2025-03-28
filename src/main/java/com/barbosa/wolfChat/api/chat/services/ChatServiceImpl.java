package com.barbosa.wolfChat.api.chat.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.barbosa.wolfChat.api.chat.dtos.CreateChatWithMessageDTO;
import com.barbosa.wolfChat.api.message.dtos.MessageDTO;
import com.barbosa.wolfChat.api.message.dtos.MessageForChatCreationDTO;
import com.barbosa.wolfChat.api.user.dtos.UserClaims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barbosa.wolfChat.api.chat.dtos.ChatDTO;
import com.barbosa.wolfChat.api.chat.dtos.CreateChatDTO;
import com.barbosa.wolfChat.api.chatUser.dtos.ChatUserDTO;
import com.barbosa.wolfChat.core.models.entities.Chat;
import com.barbosa.wolfChat.core.models.entities.ChatUser;
import com.barbosa.wolfChat.core.models.entities.Message;
import com.barbosa.wolfChat.core.models.entities.MessageView;
import com.barbosa.wolfChat.core.models.entities.User;
import com.barbosa.wolfChat.core.services.exception.ServiceNotFoudEntityException;
import com.barbosa.wolfChat.repositories.ChatRepository;
import com.barbosa.wolfChat.repositories.ChatUserRepository;
import com.barbosa.wolfChat.repositories.MessageRepository;
import com.barbosa.wolfChat.repositories.UserRepository;
import com.barbosa.wolfChat.utils.CommonUtil.CommunUtils;
import com.barbosa.wolfChat.utils.model.ResponseUtil;

import jakarta.persistence.EntityNotFoundException;

import static com.barbosa.wolfChat.api.chat.utils.ChatUtil.validateChatCreation;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatUserRepository chatUserRepository;
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseUtil creatingChat(CreateChatDTO dto) {

        System.out.println("VALOR DE IS GROUP: " + dto.getIsGroup());
        if (Boolean.FALSE.equals(dto.getIsGroup())) {
            System.out.println("CAIU NO NÃO É GRUPO");
        }

        User creator = userRepository.findById(dto.getAdminId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario criador não encontrado"));

        if (Boolean.FALSE.equals(dto.getIsGroup()) && dto.getUserIds().size() > 2) {
            return ResponseUtil.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .sendDateTime(CommunUtils.getDateTime())
                    .data(null)
                    .message("O chat individual só pode ter dois participantes")
                    .build();
        }

        Chat chat = new Chat();
        if (dto.getChatName() != null && dto.getIsGroup())
            chat.setChatName(dto.getChatName());
        if (dto.getDescription() != null && dto.getIsGroup())
            chat.setDescription(dto.getDescription());
        chat.setIsGroup(dto.getIsGroup());
        chat.setCreatedBy(creator);
        chat.setCreatedAt(LocalDateTime.now());

        chat = chatRepository.saveAndFlush(chat);

        List<ChatUser> chatUsers = new ArrayList<>();
        // // Adicionando usuários ao chat
        for (Long participantId : dto.getUserIds()) {
            User participant = userRepository.findById(participantId)
                    .orElseThrow(() -> new ServiceNotFoudEntityException("Usuário não encontrado: " + participantId));

            ChatUser chatUser = new ChatUser();
            chatUser.setChat(chat);
            chatUser.setUser(participant);
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



    @Override
    @Transactional
    public ChatDTO createChat(CreateChatWithMessageDTO dto) {
        User creator = getUserOrThrow(dto.getAdminId(), "Usuário criador não encontrado");

        validateChatCreation(dto);

        Chat chat = buildChat(dto, creator);

        chat = chatRepository.saveAndFlush(chat);
        final Long chatId = chat.getChatId();

        List<ChatUser> chatUsers = buildChatUsers(dto, chat);
        chatUserRepository.saveAll(chatUsers);

        if (!dto.getMessages().isEmpty()) {
            List<Message> messages = buildMessages(dto.getMessages(), chatId, creator);
            messageRepository.saveAll(messages);
        }

        // Buscar o chat atualizado já com os usuários para popular o DTO
        Chat finalChat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Não existe chat com este id"));

        // Retorna o DTO usando o Builder
        return ChatDTO.builder()
                .chatId(finalChat.getChatId())
                .isGroup(finalChat.getIsGroup())
                .chatName(finalChat.getChatName())
                .description(finalChat.getDescription())
                .createdAt(finalChat.getCreatedAt())
                .createdBy(new UserClaims(finalChat.getCreatedBy()))
                .chatUsers(finalChat.getChatUsers().stream()
                        .map(ChatUserDTO::new)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public ChatDTO findChatById(Long id) {
        if(id == null) throw new IllegalArgumentException("O ID do chat esta vazio");
        return chatRepository.findById(id).map(ChatDTO::fromEntity).orElseThrow(()-> new RuntimeException("Não localizado chat com o id informado: "+id));
    }


    @Transactional(readOnly = true)
    public Page<ChatDTO> getChats(Pageable pageable) {
        Page<Chat> chats = chatRepository.findAll(pageable);

        return chats.map(ChatDTO::fromEntity);
    }

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





    private Chat buildChat(CreateChatWithMessageDTO dto, User creator){
        Chat chat = new Chat();
        chat.setIsGroup(dto.getIsGroup());
        chat.setCreatedBy(creator);
        chat.setCreatedAt(LocalDateTime.now());

        if (Boolean.TRUE.equals(dto.getIsGroup())) {
            chat.setChatName(dto.getChatName());
            chat.setDescription(dto.getDescription());
        }
        return chat;
    }
    private List<ChatUser> buildChatUsers(CreateChatWithMessageDTO dto, Chat chat) {
        List<ChatUser> chatUsers = new ArrayList<>();

        for (Long participantId : dto.getUserIds()) {
            User participant = getUserOrThrow(participantId, "Usuário não encontrado: " + participantId);

            ChatUser chatUser = new ChatUser();
            chatUser.setChat(chat);
            chatUser.setUser(participant);
            chatUser.setIsAdmin(Boolean.TRUE.equals(dto.getIsGroup()) && participant.getUserId().equals(dto.getAdminId()));
            chatUser.setJoinedAt(LocalDateTime.now());

            chatUsers.add(chatUser);
        }

        return chatUsers;
    }


    private List<Message> buildMessages(List<MessageForChatCreationDTO> messageDTOs, Long chatId, User creator) {
        List<Message> messages = new ArrayList<>();

        for (MessageForChatCreationDTO dto : messageDTOs) {
            Message entity = new Message();
            entity.setChatId(chatId);
            entity.setUserId(creator.getUserId());
            entity.setTimestamp(dto.getTimestamp() != null ? dto.getTimestamp() : LocalDateTime.now());
            entity.setContent(dto.getContent());

            messages.add(entity);
        }

        return messages;
    }


    private User getUserOrThrow(Long userId, String errorMessage) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(errorMessage));
    }
}
