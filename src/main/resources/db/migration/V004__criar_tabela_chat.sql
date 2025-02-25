CREATE TABLE `tb_chat`(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  eh_grupo BOOLEAN NOT NULL,
  nome_chat VARCHAR(255),
  descricao TEXT,
  criado_por BIGINT NOT NULL,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  FOREIGN KEY (criado_por) REFERENCES tb_usuario(id) ON DELETE CASCADE
);