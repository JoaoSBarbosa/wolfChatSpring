CREATE TABLE `tb_mensagem_visualizacao` (
    `mv_mensagem_id` bigint NOT NULL,
    `mv_usuario_id` bigint NOT NULL,
    PRIMARY KEY (`mv_mensagem_id`,`mv_usuario_id`),
    KEY `FKneew9u3amfp857yrwxibdortn` (`mv_usuario_id`),
    CONSTRAINT `FK6xpl1lycr3lspn8mecd42584k` FOREIGN KEY (`mv_mensagem_id`) REFERENCES `tb_mensagem` (`msg_id`),
    CONSTRAINT `FKneew9u3amfp857yrwxibdortn` FOREIGN KEY (`mv_usuario_id`) REFERENCES `tb_usuario` (`usu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;