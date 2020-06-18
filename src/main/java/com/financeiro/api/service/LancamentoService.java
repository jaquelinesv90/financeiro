package com.financeiro.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
	
	public Lancamento atualizar(Long codigo,Lancamento lancamento) {
		Lancamento lancamentoSalvo = buscarLancamentoExistente(codigo);
		
		if(!lancamento.getCodigo_pessoa().equals(lancamentoSalvo.getCodigo_pessoa())) {
			validarPessoa(lancamento);
		}
		
		BeanUtils.copyProperties(lancamento, lancamentoSalvo,"codigo");
		
		return repository.save(lancamentoSalvo);
	}
	
	
	private void validarPessoa(Lancamento lancamento) {
		Pessoa pessoa = null;
		if(lancamento.getCodigo_pessoa().getCodigo_pessoa() != null) {
			pessoa = pessoaRepository.findById(lancamento.getCodigo_pessoa().getCodigo_pessoa()).get();
		}
		
		if(pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
	}

	private Lancamento buscarLancamentoExistente(Long codigo) {
		Lancamento lancamentoSalvo = repository.findById(codigo).orElse(new Lancamento());
		
		if(lancamentoSalvo == null) {
			throw new IllegalArgumentException();
		}
		return lancamentoSalvo;
	}
}
