package com.barbosa.wolfChat.core.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_mensagem")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long msgId;

    @Column(name = "conteudo")
    private String content;

    @Column(name = "chat_id", nullable = false)
    private Long chatId;

    @Column(name = "remetente_id", nullable = false)
    private Long userId;

    @Column(name = "enviado_em", updatable = false)
    @CreationTimestamp
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "remetente_id", insertable = false, updatable = false, referencedColumnName = "id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "chat_id", insertable = false, updatable = false, referencedColumnName = "id")
    @JsonBackReference
    private Chat chat;


    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MessageView> viewedBy = new HashSet<>();


}
