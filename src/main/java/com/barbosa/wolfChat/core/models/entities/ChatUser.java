package com.barbosa.wolfChat.core.models.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "chat_id", nullable = false)
    private Long chatId;

    @Column(name = "usu_id",nullable = false)
    private Long userId;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin = false;

    @CreationTimestamp
    @Column(name = "data_entrada", updatable = false)
    private LocalDateTime joinedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", insertable = false, updatable = false)
    @JsonBackReference // Evita loops de serialização
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "usu_id", insertable = false, updatable = false)
    private User user;



}
