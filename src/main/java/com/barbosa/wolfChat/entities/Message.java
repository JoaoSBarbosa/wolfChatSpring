package com.barbosa.wolfChat.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_mensagem")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msg_id")
    private Long msgId;

    @Column(name = "msg_conteudo")
    private String content;

    @Column(name = "msg_chat_id", nullable = false)
    private Long chatId;

    @Column(name = "msg_remetente_id", nullable = false)
    private Long userId;

    @Column(name = "msg_enviado_em", updatable = false)
    @CreationTimestamp
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "msg_remetente_id", insertable = false, updatable = false, referencedColumnName = "usu_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "msg_chat_id", insertable = false, updatable = false, referencedColumnName = "chat_id")
    @JsonBackReference
    private Chat chat;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_mensagem_visualizacao",
            joinColumns = @JoinColumn(name = "mv_mensagem_id"),
            inverseJoinColumns = @JoinColumn(name = "mv_usuario_id")
    )
    private Set<User> viewedBy = new HashSet<>();

    public Message() {
    }


    public Message(Long msgId, String content, User sender, LocalDateTime timestamp, Chat chat, Set<User> viewedBy) {
        this.msgId = msgId;
        this.content = content;
        this.sender = sender;
        this.timestamp = timestamp;
        this.chat = chat;
        this.viewedBy = viewedBy;
    }
}
