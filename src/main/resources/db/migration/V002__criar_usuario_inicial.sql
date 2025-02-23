-- INSERT INTO `tb_usuario` VALUES (1,'2024-11-21 20:28:01.104220','adm@gmail.com','admin','vazio','wolf','123','2024-11-21 20:28:01.104220','wolf');
-- senha123

INSERT INTO tb_usuario (
    nome, sobrenome, usuario, email, senha, url_imagem, link_imagem_dropbox, created_at, update_at
) VALUES (
             'João', 'Silva', 'wolf', 'joao@email.com', '$2a$12$RQ..EJ8jgLznY81guEUfoeWx70RRNt8/0F6dUNEpb9DPlEFPW6CpK',
             'https://meusite.com/imagem.jpg', 'https://dropbox.com/link-da-imagem',
             NOW(), NOW()
         );
