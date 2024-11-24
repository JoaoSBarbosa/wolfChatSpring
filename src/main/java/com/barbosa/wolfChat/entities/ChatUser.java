package com.barbosa.wolfChat.entities;
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
    @Column(name = "ctu_id")
    private Long id;

    @Column(name = "ctu_chat_id", nullable = false)
    private Long chatId;

    @Column(name = "ctu_usu_id",nullable = false)
    private Long userId;

    @Column(name = "ctu_is_admin", nullable = false)
    private Boolean isAdmin = false;

    @CreationTimestamp
    @Column(name = "ctu_data_entrada", updatable = false)
    private LocalDateTime joinedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ctu_chat_id", referencedColumnName = "chat_id", insertable = false, updatable = false)
    @JsonBackReference // Evita loops de serialização
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "ctu_usu_id", referencedColumnName = "usu_id", insertable = false, updatable = false)
    private User user;



}
