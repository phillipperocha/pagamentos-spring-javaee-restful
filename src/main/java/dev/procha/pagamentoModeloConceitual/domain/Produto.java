package dev.procha.pagamentoModeloConceitual.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

// 1. Mapeando a Entidade
@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// 2. Mapeando o ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double preco;
	
	// 3. Agora mapearemos os relacionamentos da classe Produto, no caso ela se relaciona com as categorias no formato Muitos para Muitos
	@ManyToMany
	// Na anotação abaixo, @JoinTable, é que definiremos quem será a tabela que vai fazer o Muitos para Muitos no banco de dados
	
	@JoinTable(name = "PRODUTO_CATEGORIA", // Precisamos nomear a tabela do banco que será utilizada para o relacionamento
		joinColumns = @JoinColumn(name = "produto_id"), // Definir o nome da chave estrangeira da classe produto nessa tabela, ela receberá a PK de produto
		inverseJoinColumns = @JoinColumn(name = "categoria_id") // Aqui definiremos a chave estrangeira da categoria, junto de seu nome na tabela
	)	
	private List<Categoria> categorias = new ArrayList<>();
		
	public Produto() {
	}
	
	public Produto(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	// 4. Getters e Setters
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
