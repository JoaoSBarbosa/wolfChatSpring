-- CREATE TABLE tb_usuario (
--     `id` bigint NOT NULL AUTO_INCREMENT,
--     `email` varchar(255) DEFAULT NULL,
--     `nome` varchar(255) DEFAULT NULL,
--     `usu_url_imagem` varchar(255) DEFAULT NULL,
--     `sobrenome` varchar(255) DEFAULT NULL,
--     `senha` varchar(255) DEFAULT NULL,
--     `usuario` varchar(255) DEFAULT NULL,
--     `url_imagem` varchar(500) DEFAULT NULL,
--     `link_imagem_dropbox` varchar(500) DEFAULT NULL,
--     `created_at` TIMESTAMP,
--     `update_at` TIMESTAMP
--     PRIMARY KEY (`id`),
--     UNIQUE KEY `UK87npml0jggls53o6d709lgna1` (`usuario`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE tb_usuario (
                            id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            email VARCHAR(255),
                            nome VARCHAR(255),
                            sobrenome VARCHAR(255),
                            usuario VARCHAR(255) UNIQUE,
                            senha VARCHAR(255),
                            url_imagem VARCHAR(500),
                            link_imagem_dropbox VARCHAR(500),
                            created_at TIMESTAMP,
                            updated_at TIMESTAMP
);
