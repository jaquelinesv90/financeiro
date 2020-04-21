package com.financeiro.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.financeiro.api.model.Pessoa;
import com.financeiro.api.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository repository;
	
	public Pessoa atualizar(Long codigo,Pessoa pessoa) {
		
		Optional<Pessoa> pessoaSalva = buscarPessoaPeloCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaSalva,"codigo");
		
		return repository.save(pessoaSalva.get());
	}

	public void atualizarAtivo(Long codigo, Boolean ativo) {
		Optional<Pessoa> pessoaSalva = buscarPessoaPeloCodigo(codigo);
		pessoaSalva.get().setAtivo(ativo);
		
		repository.save(pessoaSalva.get());
	}
	
	private Optional<Pessoa> buscarPessoaPeloCodigo(Long codigo) {
		Optional<Pessoa> pessoa = repository.findById(codigo);
		if(pessoa == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoa;
	}
}
