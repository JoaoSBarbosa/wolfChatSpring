package com.barbosa.wolfChat.core.models.entities;

import com.barbosa.wolfChat.core.models.base.Auditable;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_chat")
public class Chat extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long chatId;

    @Column(name = "eh_grupo")
    private Boolean isGroup;

    @Column(name = "nome_chat")
    private String chatName;
    @Column( name = "descricao")
    private String description;


    @Column(name = "criado_por", nullable = false)
    private Long createdBy;


    @ManyToOne
    @JoinColumn(name = "criado_por", insertable = false, updatable = false, referencedColumnName = "id")
    private User user;


    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference // Evita loops de serialização
    private List<ChatUser> chatUsers = new ArrayList<>();


    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Message> messages = new ArrayList<>();
}
