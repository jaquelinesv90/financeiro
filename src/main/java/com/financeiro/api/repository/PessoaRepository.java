package com.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financeiro.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
