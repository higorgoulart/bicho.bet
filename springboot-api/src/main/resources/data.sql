INSERT INTO loterica (id, nome, saldo, telefone, cnpj) VALUES (1, 'Bicho.bet', 10000000, '48999999999', '29961469000179');

INSERT INTO apostador(id, nome, saldo, telefone, cpf, divida, limite)
VALUES
(1, 'Guilherme Savio', 300, '48999999999', '15365498725', 0, 900),
(2, 'Higor Goulart', 500, '48999999999', '15965487365', 0, 1500),
(3, 'Filipe Milaneze', 300, '48999999999', '45698712302', 0, 900),
(4, 'Gabriel Ferreira', 500, '48999999999', '95123647895', 0, 1500),
(5, 'Max Willian', 300, '48999999999', '65954715204', 0, 900);

INSERT INTO jogo(id, dt_fim, dt_inicio, status, loterica_id)
VALUES (1, '2023-11-27 20:00:00', '2023-11-20 20:00:00', 'ABERTO', 1);


INSERT INTO bicho (id, nome, numeros)
VALUES
    (1, 'AVESTRUZ', '{"numeros":[1,2,3,4]}'),
    (2, 'AGUIA', '{"numeros":[5,6,7,8]}'),
    (3, 'BURRO', '{"numeros":[9,10,11,12]}'),
    (4, 'BORBOLETA', '{"numeros":[13,14,15,16]}'),
    (5, 'CACHORRO', '{"numeros":[17,18,19,20]}'),
    (6, 'CABRA', '{"numeros":[21,22,23,24]}'),
    (7, 'CARNEIRO', '{"numeros":[25,26,27,28]}'),
    (8, 'CAMELO', '{"numeros":[29,30,31,32]}'),
    (9, 'COBRA', '{"numeros":[33,34,35,36]}'),
    (10, 'COELHO', '{"numeros":[37,38,39,40]}'),
    (11, 'CAVALO', '{"numeros":[41,42,43,44]}'),
    (12, 'ELEFANTE', '{"numeros":[45,46,47,48]}'),
    (13, 'GALO', '{"numeros":[49,50,51,52]}'),
    (14, 'GATO', '{"numeros":[53,54,55,56]}'),
    (15, 'JACARE', '{"numeros":[57,58,59,60]}'),
    (16, 'LEAO', '{"numeros":[61,62,63,64]}'),
    (17, 'MACACO', '{"numeros":[65,66,67,68]}'),
    (18, 'PORCO', '{"numeros":[69,70,71,72]}'),
    (19, 'PAVAO', '{"numeros":[73,74,75,76]}'),
    (20, 'PERU', '{"numeros":[77,78,79,80]}'),
    (21, 'TOURO', '{"numeros":[81,82,83,84]}'),
    (22, 'TIGRE', '{"numeros":[85,86,87,88]}'),
    (23, 'URSO', '{"numeros":[89,90,91,92]}'),
    (24, 'VEADO', '{"numeros":[93,94,95,96]}'),
    (25, 'VACA', '{"numeros":[97,98,99,0]}');