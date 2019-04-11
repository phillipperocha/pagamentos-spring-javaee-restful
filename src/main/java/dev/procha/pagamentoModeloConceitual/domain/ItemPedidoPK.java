package dev.procha.pagamentoModeloConceitual.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

// Essa classe auxiliar tem algumas exigências.
// 1º Ela deve implementar Serializable
//2º Getters e Setters
//3º E HashCode e Equals (DESSA VEZ COM BASE NOS DOIS ATRIBUTOS!)
// Não precisa de construtor

// Temos que usar a anotação @Embeddable, pra dizer que ela é um subtipo
@Embeddable
public class ItemPedidoPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// Primeiro pegaremos os dois objetos que a compõem
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	// 2º Getters e Setters
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	// 3º E HashCode e Equals (DESSA VEZ COM BASE NOS DOIS ATRIBUTOS!)
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
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
		ItemPedidoPK other = (ItemPedidoPK) obj;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}
	
}
