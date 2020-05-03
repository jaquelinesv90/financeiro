package com.financeiro.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Lancamento.class)
public abstract class Lancamento_ {

	public static volatile SingularAttribute<Lancamento, LocalDate> datapagamento;
	public static volatile SingularAttribute<Lancamento, Long> codigo;
	public static volatile SingularAttribute<Lancamento, String> observacao;
	public static volatile SingularAttribute<Lancamento, TipoLancamento> tipo;
	public static volatile SingularAttribute<Lancamento, BigDecimal> valor;
	public static volatile SingularAttribute<Lancamento, Categoria> codigo_categoria;
	public static volatile SingularAttribute<Lancamento, LocalDate> datavencimento;
	public static volatile SingularAttribute<Lancamento, Pessoa> codigo_pessoa;
	public static volatile SingularAttribute<Lancamento, String> descricao;

	public static final String DATAPAGAMENTO = "datapagamento";
	public static final String CODIGO = "codigo";
	public static final String OBSERVACAO = "observacao";
	public static final String TIPO = "tipo";
	public static final String VALOR = "valor";
	public static final String CODIGO_CATEGORIA = "codigo_categoria";
	public static final String DATAVENCIMENTO = "datavencimento";
	public static final String CODIGO_PESSOA = "codigo_pessoa";
	public static final String DESCRICAO = "descricao";

}

