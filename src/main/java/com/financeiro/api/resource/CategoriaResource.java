package com.financeiro.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.financeiro.api.event.RecursoCriadoEvent;
import com.financeiro.api.model.Categoria;
import com.financeiro.api.repository.CategoriaRepository;

//classe de recurso, é um controlador
@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Categoria> listar(){
		return repository.findAll();
	}
	
	@PostMapping
	//anotação @Valid ativa a validação das propriedades do bean(ex: @Notblank,@Email..)
	//@ResponseStatus(HttpStatus.CREATED) 
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = repository.save(categoria);
		
		publisher.publishEvent(new RecursoCriadoEvent(this,response,categoriaSalva.getCodigo_categoria()));
		
		//a anotação foi removida pois esta linha faz a mesma coisa(depois de salvar retorna o codigo 201- criado)
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	
	@GetMapping("/{codigo}")
	public Optional<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
		return repository.findById(codigo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> remover(@PathVariable Long codigo){
		if(repository.existsById(codigo)) {
			return ResponseEntity.notFound().build();
		}
		
		repository.deleteById(codigo);
		return ResponseEntity.noContent().build();
	}
}
