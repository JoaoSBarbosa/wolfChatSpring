package com.barbosa.wolfChat.mapper;


import com.barbosa.wolfChat.dto.chat.ChatDTO;
import com.barbosa.wolfChat.dto.message.MessageDTO;
import com.barbosa.wolfChat.dto.user.UserMessageDTO;
import com.barbosa.wolfChat.entities.Chat;
import com.barbosa.wolfChat.entities.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ChatMapper {

    // Conversão de Chat para ChatDTO
    public ChatDTO toDTO(Chat chat) {
        ChatDTO chatDTO = new ChatDTO();

        chatDTO.setChatId(chat.getChatId());
        chatDTO.setChatName(chat.getChatName());
        chatDTO.setDescription(chat.getDescription());
        chatDTO.setCreatedAt(chat.getCreatedAt());
        chatDTO.setUpdatedAt(chat.getUpdatedAt());

        // Mapeando usuários
        Set<UserMessageDTO> userDTOs = chat.getUsers().stream()
                .map(user -> new UserMessageDTO(user.getUserId(), user.getFirstName(), user.getLastName()))
                .collect(Collectors.toSet());
        chatDTO.setUsers(userDTOs);

        // Mapeando mensagens
        List<MessageDTO> messageDTOs = chat.getMessages().stream()
                .map(this::toMessageDTO)  // Aqui usamos 'this' pois o método não é estático
                .collect(Collectors.toList());
        chatDTO.setMessages(messageDTOs);

        return chatDTO;
    }

    // Conversão de Message para MessageDTO
    public MessageDTO toMessageDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMsgId(message.getMsgId());
        messageDTO.setContent(message.getContent());
        messageDTO.setTimestamp(message.getTimestamp());

        // Mapeando o remetente
        UserMessageDTO senderDTO = new UserMessageDTO();
        senderDTO.setUserId(message.getSender().getUserId());
        senderDTO.setFirstName(message.getSender().getFirstName());
        senderDTO.setLastName(message.getSender().getLastName());
        messageDTO.setSender(senderDTO);

        // Mapeando os usuários que visualizaram a mensagem
        Set<UserMessageDTO> viewedByDTOs = message.getViewedBy().stream()
                .map(user -> new UserMessageDTO(user.getUserId(), user.getFirstName(), user.getLastName()))
                .collect(Collectors.toSet());
        messageDTO.setViewedBy(viewedByDTOs);

        return messageDTO;
    }

//@Component
//public class ChatMapper {
//
//    public ChatDTO toDTO(Chat chat) {
//        ChatDTO chatDTO = new ChatDTO();
//
//        chatDTO.setChatId(chat.getChatId());
//        chatDTO.setChatName(chat.getChatName());
//        chatDTO.setDescription( chat.getDescription());
//        chatDTO.setCreatedAp( chat.getCreatedAt());
//        chatDTO.setUpdateAt( chat.getUpdatedAt());
//
//        Set<UserMessageDTO> userDTOs = chat.getUsers().stream()
//                .map(user-> new UserMessageDTO(user.getUserId(), user.getFirstName(), user.getLastName()))
//                .collect(Collectors.toSet());
//
//        List<MessageDTO> messageDTOs = chat.getMessages().stream()
//                .map(ChatMapper::toMessageDTO)
//                .collect(Collectors.toList());
//
//        chatDTO.setMessages(messageDTOs);
//
//        return chatDTO;
//
//    }
//
//
//    public MessageDTO toMessageDTO(Message message) {
//        MessageDTO messageDTO = new MessageDTO();
//        messageDTO.setMsgId(message.getMsgId());
//        messageDTO.setContent(message.getContent());
//        messageDTO.setTimestamp(message.getTimestamp());
//
//        // Mapeando o remetente
//        UserMessageDTO senderDTO = new UserMessageDTO();
//        senderDTO.setUserId(message.getSender().getUserId());
//        senderDTO.setFirstName(message.getSender().getFirstName());
//        senderDTO.setLastName(message.getSender().getLastName());
//        messageDTO.setSender(senderDTO);
//
//        // Mapeando os usuários que visualizaram a mensagem
//
//        Set<UserMessageDTO> viewedByDTOs = message.getViewedBy().stream()
//                .map(user -> new UserMessageDTO(user.getUserId(), user.getFirstName(), user.getLastName()))
//                .collect(Collectors.toSet());
//        messageDTO.setViewedBy(viewedByDTOs);
//
//        return messageDTO;
//
//    }
}
