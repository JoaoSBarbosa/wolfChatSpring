CREATE TABLE tb_mensagem_visualizada(
	
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	id_mensagem BIGINT NOT NULL,
	id_usuario_vizualizou BIGINT NOT NULL,
	visualiado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (id_mensagem) REFERENCES tb_mensagem(id),
    FOREIGN KEY (id_usuario_vizualizou) REFERENCES tb_usuario(id)
);