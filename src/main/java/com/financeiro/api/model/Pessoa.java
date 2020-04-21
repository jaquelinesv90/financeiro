package com.financeiro.api.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pessoa")
public class Pessoa {

	@Id
	private Long codigo_pessoa;

	private String nome;

	private boolean ativo;

	@Embedded
	private Endereco endereco;

	public Long getCodigo_pessoa() {
		return codigo_pessoa;
	}

	public void setCodigo_pessoa(Long codigo_pessoa) {
		this.codigo_pessoa = codigo_pessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@JsonIgnore
	@Transient
	public boolean isInativo() {
		return !this.ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo_pessoa == null) ? 0 : codigo_pessoa.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (codigo_pessoa == null) {
			if (other.codigo_pessoa != null)
				return false;
		} else if (!codigo_pessoa.equals(other.codigo_pessoa))
			return false;
		return true;
	}
}