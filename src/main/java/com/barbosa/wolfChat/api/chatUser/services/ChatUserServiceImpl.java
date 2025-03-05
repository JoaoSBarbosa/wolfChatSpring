package com.barbosa.wolfChat.api.chatUser.services;

import com.barbosa.wolfChat.core.models.entities.ChatUser;
import com.barbosa.wolfChat.repositories.ChatUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatUserServiceImpl implements ChatUserService {

    private final ChatUserRepository chatUserRepository;

    @Override
    public void removeUserFromChat(Long userId, Long chatId) {

        ChatUser chatUser = chatUserRepository.findByChatIdAndUserId(chatId,userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado no chat."));;

        chatUserRepository.delete(chatUser);

        if(chatUser.getIsAdmin()){
            List<ChatUser> admins = chatUserRepository.findAdminsByChatId(chatId);

            if(admins.isEmpty()){
                ChatUser oldestMember = chatUserRepository.findOldMemberByChatId(chatId).orElseThrow(() -> new RuntimeException("Nenhum membro restante no grupo."));

                oldestMember.setIsAdmin(true);

                chatUserRepository.save(oldestMember);
            }

            // Verificar se o grupo está vazio
            if (chatUserRepository.countByChatId(chatId) == 0) {
                // Opcional: Deletar o grupo
                chatUserRepository.deleteById(chatId);
            }

        }

    }
}
