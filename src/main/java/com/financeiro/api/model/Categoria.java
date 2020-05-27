package com.financeiro.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="categoria")
public class Categoria {
	
	@Id
	@Column
	private Long codigo_categoria;
	
	//anotação do jakarta ee, valida se o valor não é null, nem vazio e 
	// não contém espaços
	@NotBlank
	@Size(min = 3, max = 20)
	private String  nome;
	

	public Long getCodigo_categoria() {
		return codigo_categoria;
	}

	public void setCodigo_categoria(Long codigo_categoria) {
		this.codigo_categoria = codigo_categoria;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo_categoria == null) ? 0 : codigo_categoria.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (codigo_categoria == null) {
			if (other.codigo_categoria != null)
				return false;
		} else if (!codigo_categoria.equals(other.codigo_categoria))
			return false;
		return true;
	}
	
}
