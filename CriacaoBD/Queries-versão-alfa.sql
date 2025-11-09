1) Listar todos os reportes com o nome do utilizador e tipo de anomalia
SELECT 
    r.rep_id AS ID_Reporte,
    u.uti_nome AS Utilizador,
    ta.tip_nome AS Tipo_Anomalia,
    a.ano_descricao AS Descricao_Anomalia,
    r.rep_estado AS Estado,
    r.rep_data AS Data,
    l.loc_endereco AS Localizacao
FROM reporte r
LEFT JOIN utilizador u ON r.rep_uti_id = u.uti_id
LEFT JOIN anomalia a ON r.rep_ano_id = a.ano_id
LEFT JOIN tipo_anomalia ta ON a.tip_id = ta.tip_id
LEFT JOIN localizacao l ON r.rep_loc_id = l.loc_id
ORDER BY r.rep_data DESC;

2) Contar quantos reportes existem por tipo de anomalia
SELECT 
    ta.tip_nome AS Tipo_Anomalia,
    COUNT(r.rep_id) AS Total_Reportes
FROM reporte r
LEFT JOIN anomalia a ON r.rep_ano_id = a.ano_id
LEFT JOIN tipo_anomalia ta ON a.tip_id = ta.tip_id
GROUP BY ta.tip_nome
ORDER BY Total_Reportes DESC;

3) Listar reportes por estado (pendente, em análise, resolvido)
SELECT 
    rep_estado AS Estado,
    COUNT(*) AS Quantidade
FROM reporte
GROUP BY rep_estado;

4) Obter todos os reportes feitos por um utilizador específico (ex: Daniel)
SELECT 
    r.rep_id,
    r.rep_descricao,
    ta.tip_nome AS Tipo_Anomalia,
    r.rep_estado,
    r.rep_data
FROM reporte r
JOIN anomalia a ON r.rep_ano_id = a.ano_id
JOIN tipo_anomalia ta ON a.tip_id = ta.tip_id
JOIN utilizador u ON r.rep_uti_id = u.uti_id
WHERE u.uti_nome = 'Daniel Alexandre'
ORDER BY r.rep_data DESC;

5) Verificar localizações e coordenadas de todas as anomalias reportadas
SELECT 
    r.rep_id,
    l.loc_endereco,
    l.loc_latitude,
    l.loc_longitude,
    ta.tip_nome AS Tipo_Anomalia,
    r.rep_estado
FROM reporte r
JOIN localizacao l ON r.rep_loc_id = l.loc_id
JOIN anomalia a ON r.rep_ano_id = a.ano_id
JOIN tipo_anomalia ta ON a.tip_id = ta.tip_id
ORDER BY r.rep_data DESC;

6) Obter as fotografias associadas a cada reporte
SELECT 
    r.rep_id AS ID_Reporte,
    u.uti_nome AS Utilizador,
    f.foto_nome AS Nome_Fotografia,
    f.foto_url AS URL,
    f.foto_caminho AS Caminho
FROM fotografia f
JOIN reporte r ON f.foto_rep_id = r.rep_id
JOIN utilizador u ON r.rep_uti_id = u.uti_id
ORDER BY r.rep_id;

7) Estatística: Quantos reportes por utilizador
SELECT 
    u.uti_nome AS Utilizador,
    COUNT(r.rep_id) AS Total_Reportes
FROM utilizador u
LEFT JOIN reporte r ON u.uti_id = r.rep_uti_id
GROUP BY u.uti_nome
ORDER BY Total_Reportes DESC;

8) Filtrar reportes recentes (últimos 7 dias)
SELECT 
    r.rep_id,
    u.uti_nome,
    ta.tip_nome AS Tipo_Anomalia,
    r.rep_data,
    r.rep_estado
FROM reporte r
JOIN utilizador u ON r.rep_uti_id = u.uti_id
JOIN anomalia a ON r.rep_ano_id = a.ano_id
JOIN tipo_anomalia ta ON a.tip_id = ta.tip_id
WHERE r.rep_data >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)
ORDER BY r.rep_data DESC;
