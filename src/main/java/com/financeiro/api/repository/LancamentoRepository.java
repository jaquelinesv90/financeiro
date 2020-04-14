package com.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financeiro.api.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento,Long>{

}
