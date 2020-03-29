
CREATE TABLE PESSOA(
	codigo_pessoa bigint NOT NULL,
	nome varchar NOT NULL,
	ativo boolean,
	logradouro varchar,
	numero integer,
	complemento varchar,
	bairro varchar,
	cep integer,
	cidade varchar,
	estado varchar,
	primary key(codigo_pessoa)
);

INSERT INTO PESSOA VALUES(1,'João da silva',true,' Rua xv de novembro',123,'5 andar','centro',43567-788,'Curitiba','Paraná');
INSERT INTO PESSOA VALUES(2,'maria dos santos', false,'Rua dos atiradores', 45,'nenhum','wunderwald',66678-090,'Pomerode','Santa Catarina');
INSERT INTO PESSOA VALUES(3,'jonas silva',true,'Rua Doze',05,'condominio','batel',23455-000,'São Paulo','São Paulo');


