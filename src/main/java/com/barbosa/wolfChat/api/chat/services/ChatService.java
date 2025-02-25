package com.barbosa.wolfChat.api.chat.services;
import com.barbosa.wolfChat.api.chat.dtos.ChatDTO;
import com.barbosa.wolfChat.dto.chat.CreateChatDTO;
import com.barbosa.wolfChat.utils.model.ResponseUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChatService {

    ResponseUtil creatingChat(CreateChatDTO dto);
    Page<ChatDTO> getChats(Pageable pageable);
    void addUserToGroup(Long chatId, Long adminId, Long userId);
    void promoteUserToAdmin(Long chatId, Long adminId ,Long userId);

}
