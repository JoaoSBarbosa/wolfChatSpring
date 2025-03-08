package com.barbosa.wolfChat.api.chat.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateChatDTO {
    private String chatName;
    private String description;
    private List<Long> userIds;
    private Long adminId;
    @NotNull(message = "O campo 'isGroup' é obrigatório.")
    private Boolean isGroup;
}
