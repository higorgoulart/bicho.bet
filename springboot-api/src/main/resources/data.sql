INSERT INTO loterica (id, nome, saldo, telefone, cnpj) VALUES (1, 'Bicho.bet', 10000000, '48999999999', '29961469000179');

INSERT INTO jogo(id, dt_fim, dt_inicio, status, loterica_id)
VALUES (1, '2023-11-27 20:00:00', '2023-11-20 20:00:00', 'ABERTO', 1);

INSERT INTO apostador (id, nome, username, email, "password", cpf, saldo, depositado, divida, limite) VALUES
(1, 'Guilherme Savio',  'guilherme.savio',  'guilherme.savio@bichobet.com',  '$2a$10$Ra3TENGkNYICgYSnviOVuO0z6CaMf/gtTcA37ujkcS0fEbkA.QItm', '15365498725', 3000, 300,  0,   100),
(2, 'Higor Goulart',    'higor.goulart',    'higor.goulart@bichobet.com',    '$2a$10$Ra3TENGkNYICgYSnviOVuO0z6CaMf/gtTcA37ujkcS0fEbkA.QItm', '15965487365', 20,   0,  -100,  0),
(3, 'Filipe Milaneze',  'filipe.milaneze',  'filipe.milaneze@bichobet.com',  '$2a$10$Ra3TENGkNYICgYSnviOVuO0z6CaMf/gtTcA37ujkcS0fEbkA.QItm', '45698712302', 300,  200,  0,   100),
(4, 'Gabriel Ferreira', 'gabriel.ferreira', 'gabriel.ferreira@bichobet.com', '$2a$10$Ra3TENGkNYICgYSnviOVuO0z6CaMf/gtTcA37ujkcS0fEbkA.QItm', '95123647895', 200,  100,  0,   100),
(5, 'Max Willian',      'max.willian',      'max.willian@bichobet.com',      '$2a$10$Ra3TENGkNYICgYSnviOVuO0z6CaMf/gtTcA37ujkcS0fEbkA.QItm', '65954715204', 100,  0,   -100, 0);


INSERT INTO aposta(id, data, numeros, tipo, valor, apostador_id, jogo_id, premio) 
VALUES 
(1, '2023-11-24 12:00', '[24]',            'GRUPO',   100, 1, 1, 0),
(2, '2023-11-24 12:00', '[15, 19]',        'DUQUE',   100, 2, 1, 0),
(3, '2023-11-24 12:00', '[1760]',          'MILHAR',  100, 3, 1, 0),
(4, '2023-11-24 12:00', '[273]',           'CENTENA', 100, 4, 1, 0),
(5, '2023-11-24 12:00', '[23, 22, 10, 2]', 'QUADRA',  100, 5, 1, 0);


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