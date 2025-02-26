CREATE TABLE tb_usuario_acessos (
                                    id_usuario BIGINT NOT NULL,
                                    id_acesso BIGINT NOT NULL,
                                    PRIMARY KEY (id_usuario, id_acesso),
                                    FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id) ON DELETE CASCADE,
                                    FOREIGN KEY (id_acesso) REFERENCES acessos(id) ON DELETE CASCADE
);
