CREATE TABLE despesa(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(30) NOT NULL,
	valor DECIMAL(12,2) NOT NULL,
	data_despesa DATE,
	tipo VARCHAR(20) NOT NULL,
	codigo_pessoa BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO despesa (nome, valor, data_despesa, tipo, codigo_pessoa) values 
	('Salário mensal', 450.00,  '2017-02-10', 'FIXAS', 1);