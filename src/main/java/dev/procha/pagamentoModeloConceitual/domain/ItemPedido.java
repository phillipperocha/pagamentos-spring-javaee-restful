package dev.procha.pagamentoModeloConceitual.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	// Por ser uma classe de associação, ela não tem ID Integer
	// Quem identifica ela são os dois objetos associados a ela.	
	
	// O seu ID precisa ser instanciado
	@EmbeddedId // (Aqui dizemos que ele é um ID embutido em um tipo auxiliar)
	private ItemPedidoPK id = new ItemPedidoPK();
	
	// Ou seja, esse ID é um atributo composto.
	// Quando fazemos uma entidade do JPA tendo como atributo identificador uma outra classe
	// Temos que colocar na outra classe a anotação @Embeddable, pra dizer que ela é um subtipo
	
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	// Vale acrescentar também que as **associações do ItemPedido, tanto com Pedido quanto com Produto 
	// já foram feitas na classe PedidoPK
	
	// Construtores (ATENÇÃO NO CONSTRUTOR COM FIELDS)
	public ItemPedido() {
		// TODO Auto-generated constructor stub
	}

	// O argumento (ItemPedido id) é uma peculiaridade do JPA, para os programadores que forem usar a nossa classe
	// Esse objeto não faz o menor sentido. Então vamos trocar o ItemPedidoPK por PEdido pedido e Produto produto
	//ANTES: public ItemPedido(ItemPedidoPK id, Double desconto, Integer quantidade, Double preco) {
	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		super();
		// Mudaremos também o set ID
		// ANTES: this.id = id;
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	// Getters e Setters

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	// Além disso, vamos criar os GETS de pedido e produto
	// Para ter acesso direto do item e do produto
	// Muito mais sentido do que acessar o id e dentro do id pegá-los.
	
	public Pedido getPedido() {
		return id.getPedido();
	}
	
	public Produto getProduto() {
		return id.getProduto();
	}
	
	// Hashcode e Equals

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
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
