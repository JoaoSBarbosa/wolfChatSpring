package com.barbosa.wolfChat.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_usuario")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id")
    private Long userId;

    @Column(name = "usu_nome")
    private String firstName;
    @Column(name = "usu_sobrenome")
    private String lastName;
    @Column(name = "usu_nome_usuario", unique = true)
    private String userName;
    @Column(name = "usu_email")
    private String email;
    @Column(name = "usu_senha")
    private String password;
    @Column(name = "usu_url_imagem")
    private String imageUri;
    @Column(name = "usu_criado_em")
    @CreationTimestamp
    private LocalDateTime createIn;
    @Column(name = "usu_atualizado_em")
    @UpdateTimestamp
    private LocalDateTime updateIn;

    public User(){}

    public User(Long userId, String firstName, String lastName, String userName, String email, String password, String imageUri, LocalDateTime createIn, LocalDateTime updateIn) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.imageUri = imageUri;
        this.createIn = createIn;
        this.updateIn = updateIn;
    }



}
