CREATE TABLE `tb_mensagem` (
    `msg_id` bigint NOT NULL AUTO_INCREMENT,
    `msg_conteudo` varchar(255) DEFAULT NULL,
    `msg_enviado_em` datetime(6) DEFAULT NULL,
    `msg_chat_id` bigint DEFAULT NULL,
    `msg_remetente_id` bigint DEFAULT NULL,
    PRIMARY KEY (`msg_id`),
    KEY `FKbyiykoffqtr6hsergn1762915` (`msg_chat_id`),
    KEY `FKfhp7c8a08gw97nuc1ww2et72n` (`msg_remetente_id`),
    CONSTRAINT `FKbyiykoffqtr6hsergn1762915` FOREIGN KEY (`msg_chat_id`) REFERENCES `tb_chat` (`chat_id`),
    CONSTRAINT `FKfhp7c8a08gw97nuc1ww2et72n` FOREIGN KEY (`msg_remetente_id`) REFERENCES `tb_usuario` (`usu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;