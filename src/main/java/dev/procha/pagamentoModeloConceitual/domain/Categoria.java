package dev.procha.pagamentoModeloConceitual.domain;

import java.io.Serializable;

// Partindo do checklist vamos realizar as implementações necessárias

public class Categoria  implements Serializable { // 6. Implementar Serializable. Isso diz que a nossa classe pode ser convertida em uma sequencia de bytes.
	private static final long serialVersionUID = 1L;
	
	// 1. Atributos Básicos
	private Integer id;
	private String nome;
	
	// 2. A classe categoria está associada com produto, mas estamos iniciando o teste apenas com a categoria, ignororaremos esse passo agora
	
	// 3. Gerando os construtores
	public Categoria() {
		// TODO Auto-generated constructor stub
	}

	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	// 4. Gerar Getters e Setters
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

	// 5. Gerar HashCode e Equals 
	//(Precisamos desses métodos para não comparar apenas dois objetos pelo seu ponteiro de memória.)
	// E sim pelo conteúdo dele. É padrão na web que comparamos apenas pelo ID
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
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
		
}
