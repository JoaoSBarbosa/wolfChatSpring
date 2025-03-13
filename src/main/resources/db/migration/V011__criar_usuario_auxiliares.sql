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
    updated_at
) VALUES
      (
          2, 'Maria', 'Fernandes', 'maria_dev', 'maria@email.com', '$2a$12$bAJ2CXrMf9E7XyYrJiFR0.AWh/PVJfObugaCW6jzpY19cJDLrjjC6',
          'https://avatars.githubusercontent.com/u/583231?v=4', 'https://dropbox.com/link-da-imagem-maria',
          NOW(), NOW()
      ),
      (
          3, 'Carlos', 'Oliveira', 'carlos_ol', 'carlos@email.com', '$2a$12$bAJ2CXrMf9E7XyYrJiFR0.AWh/PVJfObugaCW6jzpY19cJDLrjjC6',
          'https://randomuser.me/api/portraits/men/32.jpg', 'https://dropbox.com/link-da-imagem-carlos',
          NOW(), NOW()
      ),
      (
          4, 'Fernanda', 'Souza', 'fernanda_s', 'fernanda@email.com', '$2a$12$bAJ2CXrMf9E7XyYrJiFR0.AWh/PVJfObugaCW6jzpY19cJDLrjjC6',
          'https://randomuser.me/api/portraits/women/44.jpg', 'https://dropbox.com/link-da-imagem-fernanda',
          NOW(), NOW()
      ),
      (
          5, 'Lucas', 'Almeida', 'lucas_dev', 'lucas@email.com', '$2a$12$bAJ2CXrMf9E7XyYrJiFR0.AWh/PVJfObugaCW6jzpY19cJDLrjjC6',
          'https://i.pravatar.cc/150?img=5', 'https://dropbox.com/link-da-imagem-lucas',
          NOW(), NOW()
      ),
      (
          6, 'Ana', 'Pereira', 'ana_p', 'ana@email.com', '$2a$12$bAJ2CXrMf9E7XyYrJiFR0.AWh/PVJfObugaCW6jzpY19cJDLrjjC6',
          'https://i.pravatar.cc/150?img=12', 'https://dropbox.com/link-da-imagem-ana',
          NOW(), NOW()
      );
