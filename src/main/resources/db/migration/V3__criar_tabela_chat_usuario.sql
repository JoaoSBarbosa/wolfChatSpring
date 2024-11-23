CREATE TABLE `tb_chat_usuario` (
    `ctu_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `ct_is_admin` bit(1) DEFAULT NULL,
    `ctu_chat_id` BIGINT NOT NULL,
    `ctu_usu_id` BIGINT NOT NULL,
    CONSTRAINT `FKbm4k1gwq1on3kqe9w96kmsstu` FOREIGN KEY (`ctu_chat_id`) REFERENCES `tb_chat` (`chat_id`),
    CONSTRAINT `FK6xj3neghc1md6gcesqtt5t92x` FOREIGN KEY (`ctu_usu_id`) REFERENCES `tb_usuario` (`usu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
