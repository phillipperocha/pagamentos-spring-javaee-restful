package dev.procha.pagamentoModeloConceitual.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import dev.procha.pagamentoModeloConceitual.domain.enums.EstadoPagamento;

@Entity
// Para mapear herança vamos colocar na superclasse e passamos a estratégia para fazer a herança na tabela do banco	// As estratégias são "o tabelão" que é uma tabela única que vai guardar valores nulos no cartao, caso seja boleto e vice-versa
// E a segunda são três tabelas, uma pra cada um. 
// A primeira estratégia é mais performática, mas a segunda mais organizada. A segunda temos que fazer joins para pegar os valores.
// Quando tem poucos atributos na subclasse fazemos o tabelão
// SINGLE_TABLE == tabelão // JOINED - tRês tabelas

@Inheritance(strategy = InheritanceType.JOINED)
// Será uma classe abstrata pq não pode ser instanciada
public abstract class Pagamento  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	// Por ser um relacionamento um para um, usaremos o mesmo Id para o pagamento e o Pedido

	private Integer id;
	// Mesma coisa pro enum de antes
	private Integer estado;
	
	@OneToOne
	@JoinColumn(name="pedido_id")
	// Para garantir que o Id da classe Pagamento seja o mesmo de Pedido vamos por:
	@MapsId
	private Pedido pedido;
	
	public Pagamento() {
		// TODO Auto-generated constructor stub
	}

	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = estado.getCod();
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
