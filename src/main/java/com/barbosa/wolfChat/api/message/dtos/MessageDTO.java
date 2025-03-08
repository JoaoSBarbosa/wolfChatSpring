package com.barbosa.wolfChat.api.message.dtos;

import com.barbosa.wolfChat.api.user.dtos.UserMessageDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class MessageDTO {

    private Long msgId;
    private String content;
    private LocalDateTime timestamp;
    private UserMessageDTO sender;
    private Set<UserMessageDTO> viewedBy = new HashSet<>();
}
