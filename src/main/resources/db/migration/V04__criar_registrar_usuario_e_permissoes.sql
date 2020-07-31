CREATE TABLE usuario(
	codigo BIGINT primary key,
	nome varchar,
	email varchar,
	senha varchar
);

CREATE TABLE permissao(
	codigo BIGINT primary key,
	descricao varchar
);	

	
CREATE TABLE usuario_permissao(
	codigo_usuario BIGINT NOT NULL,
	codigo_permissao BIGINT NOT NULL,
	PRIMARY KEY(codigo_usuario, codigo_permissao),
	FOREIGN KEY(codigo_usuario) REFERENCES usuario(codigo),
	FOREIGN KEY(codigo_permissao) REFERENCES permissao(codigo)
);

INSERT INTO usuario values(1,'Administrador','admin@email.com','');
INSERT INTO usuario values(2,'joao','maria@email.com','');


INSERT INTO permissao values(1,'ROLE_CADASTRAR_CATEGORIA');
INSERT INTO permissao values(2,'ROLE_PESQUISAR_CATEGORIA');
INSERT INTO permissao values(3,'ROLE_CADASTRAR_PESSOA');
INSERT INTO permissao values(4,'ROLE_REMOVER_PESSOA');
INSERT INTO permissao values(5,'ROLE_PESQUISAR_PESSOA');
INSERT INTO permissao values(6,'ROLE_CADASTRAR_LANCAMENTO');
INSERT INTO permissao values(7,'ROLE_REMOVER_LANCAMENTO');
INSERT INTO permissao values(8,'ROLE_PESQUISAR_LANCAMENTO');

--admin
INSERT INTO usuario_permissao values(1,1);
INSERT INTO usuario_permissao values(1,2);
INSERT INTO usuario_permissao values(1,3);
INSERT INTO usuario_permissao values(1,4);
INSERT INTO usuario_permissao values(1,5);
INSERT INTO usuario_permissao values(1,6);

--joao
INSERT INTO usuario_permissao values(2,2);
INSERT INTO usuario_permissao values(2,5);
INSERT INTO usuario_permissao values(2,8);

