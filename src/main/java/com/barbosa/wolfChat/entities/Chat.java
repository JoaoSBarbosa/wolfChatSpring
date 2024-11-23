package com.barbosa.wolfChat.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_chat")
public class Chat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long chatId;
    @Column(name = "chat_nome")
    private String chatName;
    @Column( name = "chat_descricao")
    private String description;
    @Column(name = "chat_criado_em", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "chat_atualizado_em")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ChatUser> chatUsers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "tb_chat_usuario",
            joinColumns = @JoinColumn(name = "ctu_chat_id"),
            inverseJoinColumns = @JoinColumn(name = "ctu_usu_id"))
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Message> messages = new ArrayList<>();
}
