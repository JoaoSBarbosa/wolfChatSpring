package com.barbosa.wolfChat.core.models.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_mensagem_visualizada")
public class MessageView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long messageViewId;

    @Column(name = "id_mensagem")
    private Long msgId;

    @Column(name = "id_usuario_vizualizou")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "id_mensagem", nullable = false, insertable = false, updatable = false, referencedColumnName = "id")
    private Message message;

    @ManyToOne
    @JoinColumn(name = "id_usuario_vizualizou", nullable = false, referencedColumnName = "id", insertable = false, updatable = false)
    private User viewer;

    @Column(name = "visualiado_em")
    private LocalDateTime viewedAt;
}
