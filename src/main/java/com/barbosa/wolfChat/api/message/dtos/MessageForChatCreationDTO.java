package com.barbosa.wolfChat.api.message.dtos;

import com.barbosa.wolfChat.api.user.dtos.UserMessageDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class MessageForChatCreationDTO {
    //    private String content;
//    private LocalDateTime timestamp;
//    private Long userId;
    @NotBlank(message = "Conteúdo da mensagem não pode ser vazio")
    private String content;

    private LocalDateTime timestamp;

    @NotNull(message = "O ID do usuário que enviou a mensagem é obrigatório")
    private Long userId;
}
