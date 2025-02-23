package com.barbosa.wolfChat.core.models.entities;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_acessos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonNaming( PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Roles {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;
}
