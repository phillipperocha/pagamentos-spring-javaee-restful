package dev.procha.pagamentoModeloConceitual.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Para levar essa categoria ao banco precisamos dizer que ela é uma entidade a ser modelada
// Por isso, utilizaremos a anotação @Entity

@Entity
public class Categoria  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Precisamos dizer quem será a chave primária com @Id
	@Id
	// E, no nosso caso, ele será gerado automaticamente então entramos com a anotação @GeneratedValue
	// E também definir a estratégia de geração automática dos IDS
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	public Categoria() {
	}

	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

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
