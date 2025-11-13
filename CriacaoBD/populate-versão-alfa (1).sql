


INSERT INTO utilizador (uti_nome, uti_email, uti_password) VALUES
('Daniel Alexandre', 'daniel@example.com', '1234'),
('Camila Vargas', 'camila.vargas@example.com', 'abc123'),
('Cássia Batista', 'cassia.batista@example.com', 'pass123'),
('José Paim', 'jose@example.com', 'leagueoflegends');


INSERT INTO tipo_anomalia (tip_nome) VALUES
('Rampas Inexistentes'),
('Passeios Danificados'),
('Passadeiras Mal Sinalizadas'),
('Zonas Perigosas'),
('Buraco na Via'),
('Sinalização Danificada'),
('Outro');


INSERT INTO anomalia (tip_id, ano_descricao, ano_grau_perigo) VALUES
((SELECT tip_id FROM tipo_anomalia WHERE tip_nome='Rampas Inexistentes'), 'Ausência de rampa em passeio', 'Médio'),
((SELECT tip_id FROM tipo_anomalia WHERE tip_nome='Passeios Danificados'), 'Passeio com pedras soltas', 'Médio'),
((SELECT tip_id FROM tipo_anomalia WHERE tip_nome='Passadeiras Mal Sinalizadas'), 'Falta de marcação visível', 'Baixo'),
((SELECT tip_id FROM tipo_anomalia WHERE tip_nome='Zonas Perigosas'), 'Zona sem iluminação', 'Alto'),
((SELECT tip_id FROM tipo_anomalia WHERE tip_nome='Buraco na Via'), 'Buraco com ~30cm', 'Alto'),
((SELECT tip_id FROM tipo_anomalia WHERE tip_nome='Sinalização Danificada'), 'Sinal de STOP tombado', 'Médio'),
((SELECT tip_id FROM tipo_anomalia WHERE tip_nome='Outro'), 'Outro (descrito pelo utilizador)', 'Baixo');


INSERT INTO localizacao (loc_latitude, loc_longitude, loc_endereco) VALUES
(38.716, -9.141, 'Praça do Comércio, Lisboa'),
(38.707, -9.135, 'Cais do Sodré, Lisboa'),
(38.736, -9.150, 'Campo Grande, Lisboa'),
(41.1579, -8.6291, 'Avenida dos Aliados, Porto');


INSERT INTO reporte 
(rep_uti_id, rep_ano_id, rep_loc_id, rep_estado, rep_data, rep_descricao, rep_tipo_personalizado) 
VALUES
(1, (SELECT a.ano_id FROM anomalia a JOIN tipo_anomalia t ON t.tip_id=a.tip_id WHERE t.tip_nome='Buraco na Via' LIMIT 1),
 1, 'Pendente', CURRENT_DATE, 'Buraco grande dificulta travessia', NULL),

(2, (SELECT a.ano_id FROM anomalia a JOIN tipo_anomalia t ON t.tip_id=a.tip_id WHERE t.tip_nome='Sinalização Danificada' LIMIT 1),
 2, 'Em análise', CURRENT_DATE, 'Sinal virado ao contrário', NULL),

(3, (SELECT a.ano_id FROM anomalia a JOIN tipo_anomalia t ON t.tip_id=a.tip_id WHERE t.tip_nome='Outro' LIMIT 1),
 3, 'Pendente', CURRENT_DATE, 'Árvore caída a bloquear passeio', 'Árvore caída'),

(4, (SELECT a.ano_id FROM anomalia a JOIN tipo_anomalia t ON t.tip_id=a.tip_id WHERE t.tip_nome='Buraco na Via' LIMIT 1),
 4, 'Em análise', CURRENT_DATE, 'Buraco com lâminas', NULL);


INSERT INTO fotografia 
(foto_nome, foto_caminho, foto_mime, foto_tamanho, foto_url, foto_rep_id) VALUES
('buraco1.jpg', 'uploads/buraco1.jpg', 'image/jpeg', 345678, '/uploads/buraco1.jpg', 1),
('buraco2.jpg', 'uploads/buraco2.jpg', 'image/jpeg', 289000, '/uploads/buraco2.jpg', 1),
('sinal1.jpg', 'uploads/sinal1.jpg', 'image/jpeg', 198765, '/uploads/sinal1.jpg', 2),
('arvore.jpg', 'uploads/arvore.jpg', 'image/jpeg', 250000, '/uploads/arvore.jpg', 3),
('rampa.jpg', 'uploads/rampa.jpg', 'image/jpeg', 190000, '/uploads/rampa.jpg', 4);

