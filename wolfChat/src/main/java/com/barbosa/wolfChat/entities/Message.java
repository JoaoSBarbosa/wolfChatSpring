package com.barbosa.wolfChat.entities;

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

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "msg_id")
    private Long msgId;

    @Column(name = "msg_conteudo")
    private String content;

    @ManyToOne
    @JoinColumn(name = "msg_remetente_id")
    private User sender;

    @Column(name = "msg_enviado_em", updatable = false)
    @CreationTimestamp
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "msg_id_chat")
    private Chat chat;

    @ManyToMany
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
