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

    @Column(name = "chat_grupo")
    private Boolean isGroup;

    @Column(name = "chat_nome")
    private String chatName;
    @Column( name = "chat_descricao")
    private String description;
    @Column(name = "chat_criado_em", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "chat_criado_por", nullable = false)
    private Long createdBy;

    @Column(name = "chat_atualizado_em")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "chat_criado_por", insertable = false, updatable = false, referencedColumnName = "usu_id")
    private User user;

//    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<ChatUser> chatUsers = new HashSet<>();

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference // Evita loops de serialização
    private List<ChatUser> chatUsers = new ArrayList<>();


    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Message> messages = new ArrayList<>();
}
