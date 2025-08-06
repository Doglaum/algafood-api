insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Francesa');
insert into cozinha (id, nome) values (4, 'Japonesa');
insert into cozinha (id, nome) values (5, 'Brasileira');
insert into cozinha (id, nome) values (6, 'Mexicana');

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Bob Lanches', 0.0, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Point do Gordinho', 20.90, 2);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Divinissimo', 0.0 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Bistro', 16, 2);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Chez Gourmet', 25.00, 3);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Sushi Master', 12.50, 4);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Sabores do Brasil', 8.00, 5);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('El Mariachi', 18.75, 6);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Le Bistro', 20.00, 3);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Sakura Sushi', 10.00, 4);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Pizza da Vila', 0.0, 5);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Cantina Italiana', 15.75, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Hamburgueria Prime', 12.00, 2);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Taco Mania', 14.50, 6);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Grelhados do Chef', 18.00, 5);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Delícias do Oriente', 11.90, 4);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Sabores Europeus', 22.50, 3);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('La Pasta Mia', 16.80, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Barbecue & Beer', 20.00, 5);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Fusion Gourmet', 25.90, 2);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de Crédito'), (2, 'Cartão de Débito')

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1),(1,2),(2,1),(2,2),(3,1),(4,1)
