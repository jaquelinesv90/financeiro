package com.financeiro.api.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.financeiro.api.event.RecursoCriadoEvent;

// a anotação @Component diz que é um bean gerenciado pelo Spring,ou seja,
//o Spring instancia, configura,injeta objetos da classe em outros beans.

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent>{

	public RecursoCriadoListener() {
		System.out.println("RecursoCriadoListener, construtor chamado na inicialização do sistema, Spring scaneou as classes"
				+ "do projeto e encontrou a anotação, adicionou dentro do container(ioc container) ");
	}
	
	@Override
	public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {
		HttpServletResponse response = recursoCriadoEvent.getResponse();
		Long codigo = recursoCriadoEvent.getCodigo();
		
		adicionarHeaderLocation(response, codigo);
	}

	private void adicionarHeaderLocation(HttpServletResponse response, Long codigo) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(codigo).toUri();
			response.setHeader("Location", uri.toASCIIString());
	}

}
