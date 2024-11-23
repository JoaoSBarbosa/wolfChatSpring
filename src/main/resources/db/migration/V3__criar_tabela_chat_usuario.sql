CREATE TABLE `tb_chat_usuario` (
    `is_admin` bit(1) DEFAULT NULL,
    `chat_id` bigint NOT NULL,
    `usu_id` bigint NOT NULL,
    PRIMARY KEY (`chat_id`,`usu_id`),
    KEY `FK6xj3neghc1md6gcesqtt5t92x` (`usu_id`),
    CONSTRAINT `FK6xj3neghc1md6gcesqtt5t92x` FOREIGN KEY (`usu_id`) REFERENCES `tb_usuario` (`usu_id`),
    CONSTRAINT `FKbm4k1gwq1on3kqe9w96kmsstu` FOREIGN KEY (`chat_id`) REFERENCES `tb_chat` (`chat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;