package com.financeiro.api.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.financeiro.api.model.Lancamento;
import com.financeiro.api.model.Lancamento_;
import com.financeiro.api.repository.filter.LancamentoFilter;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;
	
	//utilizando o criteriaQuery do jpa
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter,Pageable pageable){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		//criar as restrições/filtros
		Predicate[] predicates = criarRestricoes(lancamentoFilter,builder,root);
		criteria.where(predicates);
		
		TypedQuery<Lancamento> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query,pageable);
		
		return new PageImpl<>(query.getResultList(),pageable,total(lancamentoFilter));
		
	}
	
	private Long total(LancamentoFilter lancamentoFilter) {
		
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Lancamento> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}


	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter,CriteriaBuilder builder, Root<Lancamento> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
			predicates.add(builder.like(
					builder.lower(root.get(Lancamento_.descricao)),"%" + lancamentoFilter.getDescricao().toLowerCase() +"%" ));
		}
		
		if(lancamentoFilter.getDataVencimentoDe() !=null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Lancamento_.datavencimento), lancamentoFilter.getDataVencimentoDe()));
		}
		
		if(lancamentoFilter.getDataVencimentoAte() != null){
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Lancamento_.datavencimento),lancamentoFilter.getDataVencimentoAte()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
		
	}

}
