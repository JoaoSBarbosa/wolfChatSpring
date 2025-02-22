CREATE TABLE `tb_mensagem_visualizada` (
    `mv_id_mensagem` bigint NOT NULL,
    `mv_id_usuario_vizualizou` bigint NOT NULL,
    `mv_visualiado_em` TIMESTAMP DEFAULT  CURRENT_TIMESTAMP,
    PRIMARY KEY (`mv_id_mensagem`,`mv_id_usuario_vizualizou`),
    KEY `FKneew9u3amfp857yrwxibdortn` (`mv_id_usuario_vizualizou`),
    CONSTRAINT `FK6xpl1lycr3lspn8mecd42584k` FOREIGN KEY (`mv_id_mensagem`) REFERENCES `tb_mensagem` (`msg_id`),
    CONSTRAINT `FKneew9u3amfp857yrwxibdortn` FOREIGN KEY (`mv_id_usuario_vizualizou`) REFERENCES `tb_usuario` (`usu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;