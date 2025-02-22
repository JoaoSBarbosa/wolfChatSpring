package com.barbosa.wolfChat.dto.message;

import com.barbosa.wolfChat.dto.user.UserMessageDTO;
import com.barbosa.wolfChat.entities.Chat;
import com.barbosa.wolfChat.entities.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

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
