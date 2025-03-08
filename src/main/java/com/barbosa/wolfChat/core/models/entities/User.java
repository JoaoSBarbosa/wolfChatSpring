package com.barbosa.wolfChat.core.models.entities;

import java.io.Serializable;
import java.util.Set;

import com.barbosa.wolfChat.core.models.base.Auditable;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_usuario")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User  extends Auditable implements Serializable{

    private static final long serialVersionUID = 1L;

	@Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @Column(name = "nome")
    private String firstName;
    @Column(name = "sobrenome")
    private String lastName;
    @Column(name = "usuario", unique = true)
    private String userName;
    @Column(name = "email")
    private String email;
    @Column(name = "senha")
    private String password;
    @Column(name = "url_imagem")
    private String imageUri;

    @Column(name = "link_imagem_dropbox")
    private String linkDropboxImage;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_usuario_acessos", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_acesso"))
    private Set<Roles> roles;


}
