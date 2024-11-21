package com.barbosa.wolfChat.entities.PK;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Embeddable
public class ChatUserId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "chat_id")
    private Long chatId;
    @Column(name = "usu_id")
    private Long userId;

    public ChatUserId() {}
    public ChatUserId(Long chatId, Long chatUser) {}
}
