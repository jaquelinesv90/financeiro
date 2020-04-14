CREATE TABLE LANCAMENTO(
	codigo bigint  not null,
	descricao varchar, 	
	datavencimento date,
	datapagamento date,
	valor decimal(10,2),
	observacao varchar,
	tipo varchar,
	codigo_categoria bigint,
	codigo_pessoa bigint,
	primary key(codigo),
	foreign key(codigo_categoria) references categoria,
	foreign key(codigo_pessoa) references pessoa
);



INSERT INTO LANCAMENTO VALUES(1,'salário','2020-06-10',null,6500.00,'distribuição de lucros','RECEITA',1,1);
INSERT INTO LANCAMENTO VALUES(2,'Lanche','2020-06-10',null,10.20,null,'DESPESA',1,1);
INSERT INTO LANCAMENTO VALUES(3,'eletrônicos','2020-06-10',null,6500.00,'Geração','RECEITA',2,1);