BASE DE DADOS WAYCARE (Versão Alfa)

CREATE TABLE utilizador (
    uti_id BIGINT NOT NULL AUTO_INCREMENT,
    uti_nome VARCHAR(255) NOT NULL,
    uti_email VARCHAR(255) NOT NULL UNIQUE,
    uti_password VARCHAR(255) NOT NULL,
    PRIMARY KEY (uti_id)
) ENGINE=InnoDB;

CREATE TABLE tipo_anomalia (
    tip_id BIGINT NOT NULL AUTO_INCREMENT,
    tip_nome VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (tip_id)
) ENGINE=InnoDB;

CREATE TABLE anomalia (
    ano_id BIGINT NOT NULL AUTO_INCREMENT,
    tip_id BIGINT,
    ano_descricao VARCHAR(255),
    ano_grau_perigo VARCHAR(255),
    PRIMARY KEY (ano_id),
    CONSTRAINT fk_anomalia_tipo FOREIGN KEY (tip_id) REFERENCES tipo_anomalia(tip_id)
        ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE localizacao (
    loc_id BIGINT NOT NULL AUTO_INCREMENT,
    loc_latitude DOUBLE,
    loc_longitude DOUBLE,
    loc_endereco VARCHAR(255),
    PRIMARY KEY (loc_id)
) ENGINE=InnoDB;

CREATE TABLE reporte (
    rep_id BIGINT NOT NULL AUTO_INCREMENT,
    rep_uti_id BIGINT,
    rep_ano_id BIGINT,
    rep_loc_id BIGINT,
    rep_data DATE,
    rep_estado VARCHAR(255),
    rep_descricao VARCHAR(255),
    rep_tipo_personalizado VARCHAR(255),
    PRIMARY KEY (rep_id),
    CONSTRAINT fk_reporte_utilizador FOREIGN KEY (rep_uti_id) REFERENCES utilizador(uti_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_reporte_anomalia FOREIGN KEY (rep_ano_id) REFERENCES anomalia(ano_id)
        ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_reporte_localizacao FOREIGN KEY (rep_loc_id) REFERENCES localizacao(loc_id)
        ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB;


CREATE TABLE fotografia (
    foto_id BIGINT NOT NULL AUTO_INCREMENT,
    foto_rep_id BIGINT NOT NULL,
    foto_nome VARCHAR(255) NOT NULL,
    foto_caminho VARCHAR(255) NOT NULL,
    foto_mime VARCHAR(255),
    foto_tamanho BIGINT,
    foto_url VARCHAR(255),
    PRIMARY KEY (foto_id),
    CONSTRAINT fk_foto_reporte FOREIGN KEY (foto_rep_id) REFERENCES reporte(rep_id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;



