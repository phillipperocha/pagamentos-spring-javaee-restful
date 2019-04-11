package dev.procha.pagamentoModeloConceitual.domain;

import java.util.Date;

import javax.persistence.Entity;

import dev.procha.pagamentoModeloConceitual.domain.enums.EstadoPagamento;

// Nas subclasses é só colocar @Entity
@Entity
public class PagamentoComBoleto extends Pagamento {
	// Na subclasse não precisa por o implements mas precisa por o numero de versão
	private static final long serialVersionUID = 1L;
	
	private Date dataVencimento;
	private Date dataPagamento;
	
	public PagamentoComBoleto() {
		// TODO Auto-generated constructor stub
	}

	// Como o Pagamento com boleto é uma SUBCLASSE vamos gerar um construtor a partir da superclasse
	// e acrescentaremos os novos atributos
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento ) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
}
