package com.financeiro.api.repository.lancamento;

import java.util.List;

import com.financeiro.api.model.Lancamento;
import com.financeiro.api.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);

}
