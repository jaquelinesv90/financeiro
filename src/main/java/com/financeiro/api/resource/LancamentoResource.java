package com.financeiro.api.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financeiro.api.event.RecursoCriadoEvent;
import com.financeiro.api.exceptionHandler.FinanceiroExceptionHandler.Erro;
import com.financeiro.api.model.Lancamento;
import com.financeiro.api.repository.LancamentoRepository;
import com.financeiro.api.repository.filter.LancamentoFilter;
import com.financeiro.api.service.LancamentoService;
import com.financeiro.api.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private LancamentoService service;
	
	
	@GetMapping
	public List<Lancamento> pesquisar(LancamentoFilter filter){
		return repository.filtrar(filter);
	}
	
	
	@PostMapping
	public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento,HttpServletResponse response){
		Lancamento lancamentoSalvo = service.salvar(lancamento);
		
		publisher.publishEvent(new RecursoCriadoEvent(this,response,lancamentoSalvo.getCodigo()));
	
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
	}
	
	@GetMapping("/{codigo}")
	public Optional<Lancamento> buscarPeloCodigo(@PathVariable Long codigo){
		return repository.findById(codigo);
	}
	
	@ExceptionHandler({ PessoaInexistenteOuInativaException.class })
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){
		
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inexistente", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario,mensagemDesenvolvedor));
		
		return  ResponseEntity.badRequest().body(erros);
	}

}
