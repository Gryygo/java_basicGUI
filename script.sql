CREATE DATABASE db_projeto;

USE db_projeto;

CREATE TABLE contato ( 
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR (50),
    sobrenome VARCHAR (50),
    telefone VARCHAR (14)
);
