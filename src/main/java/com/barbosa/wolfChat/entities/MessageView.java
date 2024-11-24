package com.barbosa.wolfChat.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_mensagem_visualizada")
public class MessageView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageViewId;

    @Column(name = "mv_id_mensagem")
    private Long msgId;

    @Column(name = "mv_id_usuario_vizualizou")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "mv_id_mensagem", nullable = false, insertable = false, updatable = false, referencedColumnName = "msg_id")
    private Message message;

    @ManyToOne
    @JoinColumn(name = "mv_id_usuario_vizualizou", nullable = false, referencedColumnName = "usu_id", insertable = false, updatable = false)
    private User viewer;

    @Column(name = "mv_visualiado_em")
    private LocalDateTime viewedAt;
}
