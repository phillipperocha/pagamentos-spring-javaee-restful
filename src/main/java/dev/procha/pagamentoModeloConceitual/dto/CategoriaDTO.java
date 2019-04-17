package dev.procha.pagamentoModeloConceitual.dto;

import java.io.Serializable;

import dev.procha.pagamentoModeloConceitual.domain.Categoria;

// Ele precisa implementar o serializable
public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Esse objeto será simplesmente para definir os dados que queremos trafegar
	// Quando quisermos exibir os dados da Categoria
	
	// A Categoria tem Id, Nome e Lista de Produtos.
	// Então no DTO colocaremos apenas ID e NOME
	
	private Integer id;
	private String nome;
	
	// Criar um construtor vazio
	public CategoriaDTO() {
		
	}
	
	// ** AQUI CRIAREMOS UM CONSTRUTOR INSTANCIANDO UMA CATEGORIA DTO A PARTIR DE UMA CATEGORIA
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}
	
	// E os getters e setters
	
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

}
