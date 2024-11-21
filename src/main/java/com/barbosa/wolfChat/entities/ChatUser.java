package com.barbosa.wolfChat.entities;

import com.barbosa.wolfChat.entities.PK.ChatUserId;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_chat_usuario")
public class ChatUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ChatUserId chatUserId;

    @ManyToOne
    @MapsId("chatId")
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "usu_id")
    private User user;

    @Column(name = "is_admin")
    private Boolean isAdmin = false;


    public ChatUser() {}

    public ChatUser(ChatUserId chatUserId, Chat chat, User user, Boolean isAdmin) {
        this.chatUserId = chatUserId;
        this.chat = chat;
        this.user = user;
        this.isAdmin = isAdmin;
    }
}
