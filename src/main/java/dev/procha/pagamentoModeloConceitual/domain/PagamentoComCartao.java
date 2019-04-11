package dev.procha.pagamentoModeloConceitual.domain;

import javax.persistence.Entity;

import dev.procha.pagamentoModeloConceitual.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {
	// Na subclasse não precisa por o implements mas precisa por o numero de versão
	private static final long serialVersionUID = 1L;

	private Integer numeroDeParcelas;
	
	public PagamentoComCartao() {
		
	}

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}	
	
}
