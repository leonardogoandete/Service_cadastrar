-- Drop existing tables if they exist
DROP TABLE IF EXISTS cadastro;
DROP TABLE IF EXISTS cadastro_SEQ;

-- Create cadastro table
CREATE TABLE IF NOT EXISTS cadastro (
    id BIGINT NOT NULL AUTO_INCREMENT,
    DTYPE VARCHAR(31) NOT NULL,
    cnpj VARCHAR(255),
    cpf VARCHAR(255),
    email VARCHAR(255),
    endereco VARCHAR(255),
    nome VARCHAR(255),
    role ENUM('ADMIN', 'INSTITUICAO', 'USUARIO'),
    senha VARCHAR(255),
    PRIMARY KEY (id)
);

-- Create cadastro_SEQ table
CREATE TABLE cadastro_SEQ (
    next_val BIGINT
);

-- Insert initial value into cadastro_SEQ
INSERT INTO cadastro_SEQ VALUES (1);