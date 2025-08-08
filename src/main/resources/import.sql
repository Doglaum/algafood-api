insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Francesa');

insert into estado (id, nome) values (1, 'Santa Catarina');
insert into estado (id, nome) values (2, 'Paraná');

insert into cidade (id, nome, estado_id) values (1, 'Palhoça', 1);
insert into cidade (id, nome, estado_id) values (2, 'Curitiba', 2);

insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao) values ('Bob Lanches', 0.0, 1, "88134-400", "Rua das Flores", "200", "Bela Vista", 1, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao) values ('Point do Gordinho', 20.90, 2, "88134-410", "Rua das Ameixas", "147", "Passa Vinte", 1, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao) values ('Divinissimo', 0.0, 1, "88134-420", "Rua das Laranjas", "987-77", "Guarda", 1, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao) values ('Bistro', 16, 2, "80010-360", "Rua das Vigas", "1344", "Formosa", 2, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao) values ('Chez Gourmet', 25.00, 3, "80010-110", "Rua da Esperança", "12", "Caixa Alta", 2, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao) values ('Sushi Master', 12.50, 3, "80010-350", "Rua sem nome", "1", "Ponta de Baixo", 2, utc_timestamp, utc_timestamp);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de Crédito'), (2, 'Cartão de Débito')

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1),(1,2),(2,1),(2,2),(3,1),(4,1)

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Hambúrguer Clássico", "Pão, carne e queijo", 25.00, true, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Batata Frita", "Batata crocante", 12.00, true, 1);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("X-Tudo", "Sanduíche completo", 28.00, true, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Refrigerante", "Copo 300ml", 6.00, true, 2);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Calabresa", "Calabresa e queijo", 39.90, true, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Portuguesa", "Presunto, ovo e ervilha", 42.00, true, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Risoto de Camarão", "Arroz cremoso com camarão", 55.00, true, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Salada Caesar", "Alface, frango e molho caesar", 22.00, true, 4);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Salmão Grelhado", "Salmão com legumes", 60.00, true, 5);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Sopa de Cebola", "Sopa tradicional francesa", 18.00, true, 5);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Sushi Especial", "Combo de sushis variados", 48.00, true, 6);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Temaki de Salmão", "Temaki recheado de salmão", 22.00, true, 6);
