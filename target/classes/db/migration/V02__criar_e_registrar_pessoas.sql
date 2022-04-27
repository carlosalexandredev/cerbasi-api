CREATE TABLE pessoa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(80) NOT NULL,
	ativo BOOLEAN,
	logradouro VARCHAR(30),
	numero INT,
	complemento VARCHAR(100),
	bairro VARCHAR(30),
	cep VARCHAR(9),
	cidade VARCHAR(30),
	estado VARCHAR(2)
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) VALUES
	('Júlio Luiz Conceição Carvalho',true,'Rua Três A',12, null,'Flamboyant Residencial Park','38081-802','Uberaba','MG'),
	('Lívia Azevedo Augusto',false,'Quadra G',7,'Casa Verde','Bela Vista','64031-332','Teresina','PI'),
	('Helen Amorin Diniz',true,'Rua Três B',2, null,'Restinga','91790-759','Porto Alegre','RS'),
	('Kevin Guzzo de Carvalho',true,'Avenida Doutor Juvêncio Matos',6,'Proximo ao banco BRB','COHAB Anil IV','65050-700','Porto Alegre','RS'),
	('Stenio Camacho Mata',false,'Quadra 606 Norte Rua 4',8,'Proximo ao Colégio Ipiranga','Plano Diretor Norte','77006-782','Palmas','TO'),
	('Pedro Figueiro Nunes',false, null,0, null, null,'77006-782','Caldas Novas','GO'),
	('Henrique Medeiros', false, 'Rua do Sapo', 20, 'Apto 201', 'Centro', '12.400-12', 'Rio de Janeiro', 'RJ'),
	('Carlos Santana', true, 'Rua da Manga', 33, null, 'Centro', '31.400-12', 'Belo Horizonte', 'MG'),
	('Leonardo Oliveira', false, 'Rua do Músico', 566, null, 'Segismundo Pereira', '38.400-00', 'Uberlândia', 'MG'),
	('Isabela Martins', true, 'Rua da Terra', 13, 'Apto 10', 'Vigilato', '99.400-12', 'Manaus', 'AM');

