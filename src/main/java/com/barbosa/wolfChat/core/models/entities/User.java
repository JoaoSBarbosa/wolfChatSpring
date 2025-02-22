package com.barbosa.wolfChat.core.models.entities;

import com.barbosa.wolfChat.core.models.base.Auditable;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_usuario")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User  extends Auditable implements Serializable{

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id")
    private Long userId;

    @Column(name = "usu_nome")
    private String firstName;
    @Column(name = "usu_sobrenome")
    private String lastName;
    @Column(name = "usu_usuario", unique = true)
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


}
