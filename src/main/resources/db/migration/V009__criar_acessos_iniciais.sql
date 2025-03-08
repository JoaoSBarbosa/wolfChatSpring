-- pode enviar e receber mensagens, participar de chats, etc.
INSERT INTO acessos(nome) VALUES("ROLE_USER");
-- pode ter permissões para gerenciar conteúdo dentro do chat, como excluir mensagens ou banir usuários, mas não tem controle administrativo total.
INSERT INTO acessos(nome) VALUES("ROLE_MODERATOR");
--  permissões administrativas no sistema, podendo gerenciar usuários, configurações e roles.
INSERT INTO acessos(nome) VALUES("ROLE_ADMIN");
-- podem visualizar algumas partes do chat, mas não têm permissões para enviar mensagens ou interagir ativamente.
INSERT INTO acessos(nome) VALUES("ROLE_GUEST");
-- super administradores que possuem controle completo sobre todo o sistema, incluindo a possibilidade de gerenciar configurações do sistema e de moderadores
INSERT INTO acessos(nome) VALUES("ROLE_SUPER_ADMIN");
