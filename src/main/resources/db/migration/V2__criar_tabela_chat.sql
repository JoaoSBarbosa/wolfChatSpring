CREATE TABLE `tb_chat` (
    `chat_id` bigint NOT NULL AUTO_INCREMENT,
    `chat_nome` varchar(255) DEFAULT NULL,
    `chat_criado_em` datetime(6) DEFAULT NULL,
    `chat_descricao` varchar(255) DEFAULT NULL,
    `chat_atualizado_em` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`chat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;