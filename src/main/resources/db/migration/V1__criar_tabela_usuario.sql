CREATE TABLE `tb_usuario` (
    `usu_id` bigint NOT NULL AUTO_INCREMENT,
    `usu_criado_em` datetime(6) DEFAULT NULL,
    `usu_email` varchar(255) DEFAULT NULL,
    `usu_nome` varchar(255) DEFAULT NULL,
    `usu_url_imagem` varchar(255) DEFAULT NULL,
    `usu_sobrenome` varchar(255) DEFAULT NULL,
    `usu_senha` varchar(255) DEFAULT NULL,
    `usu_atualizado_em` datetime(6) DEFAULT NULL,
    `usu_usuario` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`usu_id`),
    UNIQUE KEY `UK87npml0jggls53o6d709lgna1` (`usu_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;