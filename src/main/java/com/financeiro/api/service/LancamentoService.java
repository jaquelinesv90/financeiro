package com.financeiro.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financeiro.api.model.Lancamento;
import com.financeiro.api.model.Pessoa;
import com.financeiro.api.repository.LancamentoRepository;
import com.financeiro.api.repository.PessoaRepository;
import com.financeiro.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository repository;
	
	public Lancamento salvar(Lancamento lancamento) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getCodigo_pessoa().getCodigo_pessoa());
		if(pessoa == null || pessoa.get().isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		return repository.save(lancamento);
	}

}
