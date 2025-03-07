CREATE TABLE tb_mensagem (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             conteudo TEXT NOT NULL,
                             chat_id BIGINT NOT NULL,
                             remetente_id BIGINT NOT NULL,
                             enviado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             FOREIGN KEY (remetente_id) REFERENCES tb_usuario(id),
                             FOREIGN KEY (chat_id) REFERENCES tb_chat(id)
);
