INSERT INTO tb_usuario (
    id,
    nome,
    sobrenome,
    usuario,
    email,
    senha,
    url_imagem,
    link_imagem_dropbox,
    created_at,
    updated_at  -- <- Nome correto
) VALUES (
             1,'João', 'Silva', 'wolf', 'joao@email.com', '$2a$12$RQ..EJ8jgLznY81guEUfoeWx70RRNt8/0F6dUNEpb9DPlEFPW6CpK',
             'https://meusite.com/imagem.jpg', 'https://dropbox.com/link-da-imagem',
             NOW(), NOW()
         );
