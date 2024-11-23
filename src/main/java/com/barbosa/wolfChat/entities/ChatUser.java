package com.barbosa.wolfChat.entities;

import com.barbosa.wolfChat.entities.PK.ChatUserId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @CreationTimestamp
    private LocalDateTime joinedAt;

}
