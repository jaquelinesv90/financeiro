package com.financeiro.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

//Events é um recurso para desacoplar rotinas do sistema
//Eventos são mensagens publicadas por algumas rotina, sem
//saber explicitament para quem e sem aguardar resposta.
//exemplo eventos: Usuario x logou, Novo cliente cadastrado..
public class RecursoCriadoEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse response;
	private Long codigo;

	public RecursoCriadoEvent(Object source,HttpServletResponse response, Long codigo) {
		super(source);
		this.response = response;
		this.codigo = codigo;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
	
	public Long getCodigo() {
		return codigo;
	}
	
}
