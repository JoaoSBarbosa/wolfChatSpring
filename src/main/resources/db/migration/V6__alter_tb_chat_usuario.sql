-- Renomear a coluna 'ct_is_admin' para 'ctu_is_admin'
ALTER TABLE `tb_chat_usuario` CHANGE `ct_is_admin` `ctu_is_admin` BIT(1) DEFAULT NULL;
-- Adicionar a coluna 'ctu_data_entrada'
ALTER TABLE `tb_chat_usuario` ADD COLUMN `ctu_data_entrada` DATETIME(6) DEFAULT NULL;
