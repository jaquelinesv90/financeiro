package com.financeiro.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lancamento")
public class Lancamento {
		
		@Id
		private Long codigo;
		
		private String descricao;
		
		private LocalDate datavencimento;
		
		private LocalDate datapagamento;
		
		private BigDecimal valor;
		
		private String observacao;
		
		@Enumerated(EnumType.STRING)
		private TipoLancamento tipo;
		
		@ManyToOne
		@JoinColumn(name = "codigo_categoria")
		private Categoria codigo_categoria;
		
		@ManyToOne
		@JoinColumn(name= "codigo_pessoa")
		private Pessoa codigo_pessoa;

		
		public Long getCodigo() {
			return codigo;
		}

		public void setCodigo(Long codigo) {
			this.codigo = codigo;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public LocalDate getDatavencimento() {
			return datavencimento;
		}

		public void setDatavencimento(LocalDate datavencimento) {
			this.datavencimento = datavencimento;
		}

		public LocalDate getDatapagamento() {
			return datapagamento;
		}

		public void setDatapagamento(LocalDate datapagamento) {
			this.datapagamento = datapagamento;
		}

		public BigDecimal getValor() {
			return valor;
		}

		public void setValor(BigDecimal valor) {
			this.valor = valor;
		}

		public String getObservacao() {
			return observacao;
		}

		public void setObservacao(String observacao) {
			this.observacao = observacao;
		}

		public TipoLancamento getTipo() {
			return tipo;
		}

		public void setTipo(TipoLancamento tipo) {
			this.tipo = tipo;
		}

		public Categoria getCodigo_categoria() {
			return codigo_categoria;
		}

		public void setCodigo_categoria(Categoria codigo_categoria) {
			this.codigo_categoria = codigo_categoria;
		}

		public Pessoa getCodigo_pessoa() {
			return codigo_pessoa;
		}

		public void setCodigo_pessoa(Pessoa codigo_pessoa) {
			this.codigo_pessoa = codigo_pessoa;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
			Lancamento other = (Lancamento) obj;
			if (codigo == null) {
				if (other.codigo != null)
					return false;
			} else if (!codigo.equals(other.codigo))
				return false;
			return true;
		}

			
}
