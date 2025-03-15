package com.barbosa.wolfChat.api.chat.dtos;

import com.barbosa.wolfChat.api.message.dtos.MessageForChatCreationDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateChatWithMessageDTO {
    private String chatName;
    private String description;
    @NotEmpty(message = "Deve haver pelo menos um participante no chat")
    private List<Long> userIds;
    @NotNull(message = "O administrador do chat é obrigatório")
    private Long adminId;
    @NotNull(message = "O campo 'isGroup' é obrigatório.")
    private Boolean isGroup;
    private List<@Valid MessageForChatCreationDTO> messages = new ArrayList<>();

//    List<MessageForChatCreationDTO> messages = new ArrayList<>();
}
