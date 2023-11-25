-- Drop existing tables if they exist
DROP TABLE IF EXISTS Cadastro;
DROP TABLE IF EXISTS Cadastro_SEQ;

-- Create Cadastro table
CREATE TABLE IF NOT EXISTS Cadastro (
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

-- Create Cadastro_SEQ table
CREATE TABLE Cadastro_SEQ (
    next_val BIGINT
);

-- Insert initial value into Cadastro_SEQ
INSERT INTO Cadastro_SEQ VALUES (1);