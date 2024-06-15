INSERT INTO CHEFES(nome, sobrenome) VALUES ('Duda', 'MAFRA');
INSERT INTO CHEFES(nome, sobrenome) VALUES ('Duda', 'SOUZA');
INSERT INTO CHEFES(nome, sobrenome) VALUES ('Rebeca', 'MAFRA');
INSERT INTO CHEFES(nome, sobrenome) VALUES ('Debora', 'Menezes');

INSERT INTO RECEITAS(nome, chefe_id) VALUES ('Brownie da Duda', '1');
INSERT INTO RECEITAS(nome, chefe_id) VALUES ('Petit Gateau', '1');
INSERT INTO RECEITAS(nome, chefe_id) VALUES ('Torta de Morango', '2');
INSERT INTO RECEITAS(nome, chefe_id) VALUES ('Pudim', '4');

INSERT INTO INGREDIENTES(nome, preco) VALUES ('Chocolate Nobre', 58.2);
INSERT INTO INGREDIENTES(nome, preco) VALUES ('Açucar', 4.25);
INSERT INTO INGREDIENTES(nome, preco) VALUES ('Ovo', 0.85);
INSERT INTO INGREDIENTES(nome, preco) VALUES ('Manteiga sem sal', 10.2);
INSERT INTO INGREDIENTES(nome, preco) VALUES ('Leite Condensado', 8.6);

INSERT INTO RECEITAS_INGREDIENTES(receita_id, ingredientes_id) VALUES (1,1);
INSERT INTO RECEITAS_INGREDIENTES(receita_id, ingredientes_id) VALUES (1,2);
INSERT INTO RECEITAS_INGREDIENTES(receita_id, ingredientes_id) VALUES (1,3);
INSERT INTO RECEITAS_INGREDIENTES(receita_id, ingredientes_id) VALUES (1,4);
INSERT INTO RECEITAS_INGREDIENTES(receita_id, ingredientes_id) VALUES (4,5);
INSERT INTO RECEITAS_INGREDIENTES(receita_id, ingredientes_id) VALUES (4,3);
INSERT INTO RECEITAS_INGREDIENTES(receita_id, ingredientes_id) VALUES (2,1);

INSERT INTO ROLE(nome) VALUES ('ADMIN');
INSERT INTO ROLE(nome) VALUES ('USER');
INSERT INTO ROLE(nome) VALUES ('CHEFE');

INSERT INTO USUARIO(nome, email, status, password) VALUES ('Jorge', 'jorge@gmail.com', 1, '124578');
INSERT INTO USUARIO(nome, email, status, password) VALUES ('Fabio', 'fabio@gmail.com', 2, '124578');
INSERT INTO USUARIO(nome, email, status, password) VALUES ('Leticia', 'let@gmail.com', 1, '124578');

INSERT INTO USUARIO_ROLES(usuario_id, roles_id) VALUES (1,1);
INSERT INTO USUARIO_ROLES(usuario_id, roles_id) VALUES (1,2);
INSERT INTO USUARIO_ROLES(usuario_id, roles_id) VALUES (2,2);
INSERT INTO USUARIO_ROLES(usuario_id, roles_id) VALUES (3,3);
