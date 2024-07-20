package com.barbosa.wolfChat.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_mensagem_visualizacao")
public class MessageView implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mv_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mv_mensagem_id")
    private Message message;

    @ManyToOne
    @JoinColumn(name = "mv_usuario_id")
    private User user;

    @Column(name = "mv_visualizado_em")
    private LocalDateTime timestamp;

    public MessageView() {}

    public MessageView(Long id, Message message, User user, LocalDateTime timestamp) {
        this.id = id;
        this.message = message;
        this.user = user;
        this.timestamp = timestamp;
    }
}
