CREATE TABLE tb_chat_usuario (
                                 id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                 chat_id BIGINT NOT NULL,
                                 usu_id BIGINT NOT NULL,
                                 is_admin BOOLEAN NOT NULL DEFAULT FALSE,
                                 data_entrada TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 FOREIGN KEY (chat_id) REFERENCES tb_chat(id) ON DELETE CASCADE,
                                 FOREIGN KEY (usu_id) REFERENCES tb_usuario(id) ON DELETE CASCADE
);
