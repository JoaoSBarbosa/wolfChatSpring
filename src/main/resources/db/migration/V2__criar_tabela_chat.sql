CREATE TABLE `tb_chat` (
    `chat_id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `chat_nome` varchar(255) DEFAULT NULL,
    `chat_grupo` BOOLEAN DEFAULT FALSE,
    `chat_criado_em` datetime(6) DEFAULT NULL,
    `chat_criado_por` BIGINT  NOT NULL,
    `chat_descricao` varchar(255) DEFAULT NULL,
    `chat_atualizado_em` datetime(6) DEFAULT NULL,
     FOREIGN KEY (chat_criado_por) REFERENCES tb_usuario(usu_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;