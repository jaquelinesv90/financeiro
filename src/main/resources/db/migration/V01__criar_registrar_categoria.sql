CREATE TABLE categoria(
	codigo_categoria bigint NOT NULL,
    nome  varchar NOT NULL,
    primary key(codigo_categoria)
);

INSERT INTO categoria(codigo_categoria,nome) values(1,'Lazer');
INSERT INTO categoria(codigo_categoria,nome) values(2,'Alimentação');
INSERT INTO categoria(codigo_categoria,nome) values(3,'Supermercado');
INSERT INTO categoria(codigo_categoria,nome) values(4,'Farmácia');
INSERT INTO categoria(codigo_categoria,nome) values(5,'Outros');



