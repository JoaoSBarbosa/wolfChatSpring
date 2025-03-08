package com.barbosa.wolfChat.core.models.entities;
import com.barbosa.wolfChat.core.models.base.Auditable;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Table(name = "tb_chat")
public class Chat extends Auditable implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   
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
    @JsonManagedReference
    private List<ChatUser> chatUsers = new ArrayList<>();


    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Message> messages = new ArrayList<>();
}
