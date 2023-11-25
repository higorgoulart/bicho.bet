DELETE FROM refreshtoken;
DELETE FROM bicho WHERE id <= 25;
DELETE FROM aposta WHERE id <= 80;
DELETE FROM apostador WHERE id <= 5;
DELETE FROM jogo WHERE id <= 20;
DELETE FROM loterica WHERE id = 1;

INSERT INTO loterica (id, nome, saldo, telefone, cnpj) VALUES (1, 'Bicho.bet', 10000000, '48999999999', '29961469000179');

INSERT INTO jogo(id, dt_fim, dt_inicio, status, loterica_id)
VALUES
    (1, '2023-12-04 20:00:00', '2023-12-04 20:00:00', 'ABERTO', 1),
    (2, '2023-11-25 18:00:00', '2023-11-18 18:00:00', 'FECHADO', 1),
    (3, '2023-11-26 15:30:00', '2023-11-19 15:30:00', 'FECHADO', 1),
    (4, '2023-11-21 21:45:00', '2023-11-14 21:45:00', 'FECHADO', 1),
    (5, '2023-11-22 12:00:00', '2023-11-15 12:00:00', 'FECHADO', 1),
    (6, '2023-11-23 09:15:00', '2023-11-16 09:15:00', 'FECHADO', 1),
    (7, '2023-11-24 17:30:00', '2023-11-17 17:30:00', 'FECHADO', 1),
    (8, '2023-11-25 20:45:00', '2023-11-18 20:45:00', 'FECHADO', 1),
    (9, '2023-11-20 14:00:00', '2023-11-13 14:00:00', 'FECHADO', 1),
    (10, '2023-11-21 11:30:00', '2023-11-14 11:30:00', 'FECHADO', 1),
    (11, '2023-11-22 19:00:00', '2023-11-15 19:00:00', 'FECHADO', 1),
    (12, '2023-11-23 16:30:00', '2023-11-16 16:30:00', 'FECHADO', 1),
    (13, '2023-11-24 22:45:00', '2023-11-17 22:45:00', 'FECHADO', 1),
    (14, '2023-11-25 14:15:00', '2023-11-18 14:15:00', 'FECHADO', 1),
    (15, '2023-11-20 10:30:00', '2023-11-13 10:30:00', 'FECHADO', 1),
    (16, '2023-11-21 17:00:00', '2023-11-14 17:00:00', 'FECHADO', 1),
    (17, '2023-11-22 23:15:00', '2023-11-15 23:15:00', 'FECHADO', 1),
    (18, '2023-11-23 13:45:00', '2023-11-16 13:45:00', 'FECHADO', 1),
    (19, '2023-11-24 07:00:00', '2023-11-17 07:00:00', 'FECHADO', 1),
    (20, '2023-11-25 09:30:00', '2023-11-18 09:30:00', 'FECHADO', 1);

INSERT INTO apostador (id, nome, username, email, "password", cpf, saldo, depositado, divida, limite)
VALUES
    (1, 'Guilherme Savio',  'guilherme.savio',  'guilherme.savio@bichobet.com',  '$2a$10$Ra3TENGkNYICgYSnviOVuO0z6CaMf/gtTcA37ujkcS0fEbkA.QItm', '15365498725', 3000, 300,  0,   100),
    (2, 'Higor Goulart',    'higor.goulart',    'higor.goulart@bichobet.com',    '$2a$10$Ra3TENGkNYICgYSnviOVuO0z6CaMf/gtTcA37ujkcS0fEbkA.QItm', '15965487365', 20,   0,  -100,  0),
    (3, 'Filipe Milaneze',  'filipe.milaneze',  'filipe.milaneze@bichobet.com',  '$2a$10$Ra3TENGkNYICgYSnviOVuO0z6CaMf/gtTcA37ujkcS0fEbkA.QItm', '45698712302', 300,  200,  0,   100),
    (4, 'Gabriel Ferreira', 'gabriel.ferreira', 'gabriel.ferreira@bichobet.com', '$2a$10$Ra3TENGkNYICgYSnviOVuO0z6CaMf/gtTcA37ujkcS0fEbkA.QItm', '95123647895', 200,  100,  0,   100),
    (5, 'Max Willian',      'max.willian',      'max.willian@bichobet.com',      '$2a$10$Ra3TENGkNYICgYSnviOVuO0z6CaMf/gtTcA37ujkcS0fEbkA.QItm', '65954715204', 100,  0,   -100, 0);

-- GRUPO
INSERT INTO aposta(id, data, numeros, tipo, valor, apostador_id, jogo_id, premio)
VALUES
    (1, '2023-12-04 12:00', '[24]', 'GRUPO', 100, 1, 1, 0),
    (2, '2023-12-04 12:00', '[15]', 'GRUPO', 100, 2, 1, 0),
    (3, '2023-12-04 12:00', '[42]', 'GRUPO', 100, 3, 1, 0),
    (4, '2023-12-04 12:00', '[68]', 'GRUPO', 100, 4, 1, 0),
    (5, '2023-12-04 12:00', '[11]', 'GRUPO', 100, 5, 1, 0),
    (6, '2023-12-04 12:00', '[33]', 'GRUPO', 100, 1, 1, 0),
    (7, '2023-12-04 12:00', '[75]', 'GRUPO', 100, 2, 1, 0),
    (8, '2023-12-04 12:00', '[88]', 'GRUPO', 100, 3, 1, 0),
    (9, '2023-12-04 12:00', '[51]', 'GRUPO', 100, 4, 1, 0),
    (10, '2023-12-04 12:00', '[99]', 'GRUPO', 100, 5, 1, 0);

-- DUQUE
INSERT INTO aposta(id, data, numeros, tipo, valor, apostador_id, jogo_id, premio)
VALUES
    (11, '2023-12-04 12:00', '[15, 19]', 'DUQUE', 100, 1, 1, 0),
    (12, '2023-12-04 12:00', '[7, 22]', 'DUQUE', 100, 2, 1, 0),
    (13, '2023-12-04 12:00', '[14, 35]', 'DUQUE', 100, 3, 1, 0),
    (14, '2023-12-04 12:00', '[45, 68]', 'DUQUE', 100, 4, 1, 0),
    (15, '2023-12-04 12:00', '[11, 33]', 'DUQUE', 100, 5, 1, 0),
    (16, '2023-12-04 12:00', '[29, 75]', 'DUQUE', 100, 1, 1, 0),
    (17, '2023-12-04 12:00', '[50, 88]', 'DUQUE', 100, 2, 1, 0),
    (18, '2023-12-04 12:00', '[44, 77]', 'DUQUE', 100, 3, 1, 0),
    (19, '2023-12-04 12:00', '[51, 99]', 'DUQUE', 100, 4, 1, 0),
    (20, '2023-12-04 12:00', '[18, 63]', 'DUQUE', 100, 5, 1, 0);

-- TERNO
INSERT INTO aposta(id, data, numeros, tipo, valor, apostador_id, jogo_id, premio)
VALUES
    (21, '2023-12-04 12:00', '[27, 26, 10]', 'TERNO', 100, 1, 1, 0),
    (22, '2023-12-04 12:00', '[3, 15, 19]', 'TERNO', 100, 2, 1, 0),
    (23, '2023-12-04 12:00', '[22, 11, 35]', 'TERNO', 100, 3, 1, 0),
    (24, '2023-12-04 12:00', '[45, 68, 29]', 'TERNO', 100, 4, 1, 0),
    (25, '2023-12-04 12:00', '[11, 33, 75]', 'TERNO', 100, 5, 1, 0),
    (26, '2023-12-04 12:00', '[29, 50, 88]', 'TERNO', 100, 1, 1, 0),
    (27, '2023-12-04 12:00', '[77, 44, 13]', 'TERNO', 100, 2, 1, 0),
    (28, '2023-12-04 12:00', '[88, 66, 22]', 'TERNO', 100, 3, 1, 0),
    (29, '2023-12-04 12:00', '[51, 99, 75]', 'TERNO', 100, 4, 1, 0),
    (30, '2023-12-04 12:00', '[18, 63, 44]', 'TERNO', 100, 5, 1, 0);

-- QUADRA
INSERT INTO aposta(id, data, numeros, tipo, valor, apostador_id, jogo_id, premio)
VALUES
    (31, '2023-12-04 12:00', '[24, 25, 9, 2]', 'QUADRA', 100, 1, 1, 0),
    (32, '2023-12-04 12:00', '[7, 22, 14, 35]', 'QUADRA', 100, 2, 1, 0),
    (33, '2023-12-04 12:00', '[22, 11, 35, 45]', 'QUADRA', 100, 3, 1, 0),
    (34, '2023-12-04 12:00', '[45, 68, 29, 50]', 'QUADRA', 100, 4, 1, 0),
    (35, '2023-12-04 12:00', '[11, 33, 75, 29]', 'QUADRA', 100, 5, 1, 0),
    (36, '2023-12-04 12:00', '[29, 50, 88, 77]', 'QUADRA', 100, 1, 1, 0),
    (37, '2023-12-04 12:00', '[77, 44, 13, 88]', 'QUADRA', 100, 2, 1, 0),
    (38, '2023-12-04 12:00', '[88, 66, 22, 77]', 'QUADRA', 100, 3, 1, 0),
    (39, '2023-12-04 12:00', '[51, 99, 75, 51]', 'QUADRA', 100, 4, 1, 0),
    (40, '2023-12-04 12:00', '[18, 63, 44, 18]', 'QUADRA', 100, 5, 1, 0);

-- QUINA
INSERT INTO aposta(id, data, numeros, tipo, valor, apostador_id, jogo_id, premio)
VALUES
    (41, '2023-12-04 12:00', '[12, 13, 8, 3, 11]', 'QUINA', 100, 1, 1, 0),
    (42, '2023-12-04 12:00', '[7, 22, 14, 35, 45]', 'QUINA', 100, 2, 1, 0),
    (43, '2023-12-04 12:00', '[22, 11, 35, 45, 68]', 'QUINA', 100, 3, 1, 0),
    (44, '2023-12-04 12:00', '[45, 68, 29, 50, 11]', 'QUINA', 100, 4, 1, 0),
    (45, '2023-12-04 12:00', '[11, 33, 75, 29, 50]', 'QUINA', 100, 5, 1, 0),
    (46, '2023-12-04 12:00', '[29, 50, 88, 77, 44]', 'QUINA', 100, 1, 1, 0),
    (47, '2023-12-04 12:00', '[77, 44, 13, 88, 66]', 'QUINA', 100, 2, 1, 0),
    (48, '2023-12-04 12:00', '[88, 66, 22, 77, 99]', 'QUINA', 100, 3, 1, 0),
    (49, '2023-12-04 12:00', '[51, 99, 75, 51, 18]', 'QUINA', 100, 4, 1, 0),
    (50, '2023-12-04 12:00', '[18, 63, 44, 18, 12]', 'QUINA', 100, 5, 1, 0);

-- MILHAR
INSERT INTO aposta(id, data, numeros, tipo, valor, apostador_id, jogo_id, premio)
VALUES
    (51, '2023-12-04 12:00', '[1760]', 'MILHAR', 100, 1, 1, 0),
    (52, '2023-12-04 12:00', '[2345]', 'MILHAR', 100, 2, 1, 0),
    (53, '2023-12-04 12:00', '[6789]', 'MILHAR', 100, 3, 1, 0),
    (54, '2023-12-04 12:00', '[9876]', 'MILHAR', 100, 4, 1, 0),
    (55, '2023-12-04 12:00', '[5432]', 'MILHAR', 100, 5, 1, 0),
    (56, '2023-12-04 12:00', '[1098]', 'MILHAR', 100, 1, 1, 0),
    (57, '2023-12-04 12:00', '[8765]', 'MILHAR', 100, 2, 1, 0),
    (58, '2023-12-04 12:00', '[4321]', 'MILHAR', 100, 3, 1, 0),
    (59, '2023-12-04 12:00', '[5678]', 'MILHAR', 100, 4, 1, 0),
    (60, '2023-12-04 12:00', '[3210]', 'MILHAR', 100, 5, 1, 0);

-- CENTENA
INSERT INTO aposta(id, data, numeros, tipo, valor, apostador_id, jogo_id, premio)
VALUES
    (61, '2023-12-04 12:00', '[273]', 'CENTENA', 100, 1, 1, 0),
    (62, '2023-12-04 12:00', '[456]', 'CENTENA', 100, 2, 1, 0),
    (63, '2023-12-04 12:00', '[789]', 'CENTENA', 100, 3, 1, 0),
    (64, '2023-12-04 12:00', '[987]', 'CENTENA', 100, 4, 1, 0),
    (65, '2023-12-04 12:00', '[123]', 'CENTENA', 100, 5, 1, 0),
    (66, '2023-12-04 12:00', '[345]', 'CENTENA', 100, 1, 1, 0),
    (67, '2023-12-04 12:00', '[678]', 'CENTENA', 100, 2, 1, 0),
    (68, '2023-12-04 12:00', '[890]', 'CENTENA', 100, 3, 1, 0),
    (69, '2023-12-04 12:00', '[567]', 'CENTENA', 100, 4, 1, 0),
    (70, '2023-12-04 12:00', '[321]', 'CENTENA', 100, 5, 1, 0);

-- DEZENA
INSERT INTO aposta(id, data, numeros, tipo, valor, apostador_id, jogo_id, premio)
VALUES
    (71, '2023-12-04 12:00', '[24]', 'DEZENA', 100, 1, 1, 0),
    (72, '2023-12-04 12:00', '[15]', 'DEZENA', 100, 2, 1, 0),
    (73, '2023-12-04 12:00', '[42]', 'DEZENA', 100, 3, 1, 0),
    (74, '2023-12-04 12:00', '[68]', 'DEZENA', 100, 4, 1, 0),
    (75, '2023-12-04 12:00', '[11]', 'DEZENA', 100, 5, 1, 0),
    (76, '2023-12-04 12:00', '[33]', 'DEZENA', 100, 1, 1, 0),
    (77, '2023-12-04 12:00', '[75]', 'DEZENA', 100, 2, 1, 0),
    (78, '2023-12-04 12:00', '[88]', 'DEZENA', 100, 3, 1, 0),
    (79, '2023-12-04 12:00', '[51]', 'DEZENA', 100, 4, 1, 0),
    (80, '2023-12-04 12:00', '[99]', 'DEZENA', 100, 5, 1, 0);

INSERT INTO bicho (id, nome, numeros)
VALUES
    (1, 'AVESTRUZ', '[1,2,3,4]'),
    (2, 'AGUIA', '[5,6,7,8]'),
    (3, 'BURRO', '[9,10,11,12]'),
    (4, 'BORBOLETA', '[13,14,15,16]'),
    (5, 'CACHORRO', '[17,18,19,20]'),
    (6, 'CABRA', '[21,22,23,24]'),
    (7, 'CARNEIRO', '[25,26,27,28]'),
    (8, 'CAMELO', '[29,30,31,32]'),
    (9, 'COBRA', '[33,34,35,36]'),
    (10, 'COELHO', '[37,38,39,40]'),
    (11, 'CAVALO', '[41,42,43,44]'),
    (12, 'ELEFANTE', '[45,46,47,48]'),
    (13, 'GALO', '[49,50,51,52]'),
    (14, 'GATO', '[53,54,55,56]'),
    (15, 'JACARE', '[57,58,59,60]'),
    (16, 'LEAO', '[61,62,63,64]'),
    (17, 'MACACO', '[65,66,67,68]'),
    (18, 'PORCO', '[69,70,71,72]'),
    (19, 'PAVAO', '[73,74,75,76]'),
    (20, 'PERU', '[77,78,79,80]'),
    (21, 'TOURO', '[81,82,83,84]'),
    (22, 'TIGRE', '[85,86,87,88]'),
    (23, 'URSO', '[89,90,91,92]'),
    (24, 'VEADO', '[93,94,95,96]'),
    (25, 'VACA', '[97,98,99,0]');