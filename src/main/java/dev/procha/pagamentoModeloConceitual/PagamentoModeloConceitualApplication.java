package dev.procha.pagamentoModeloConceitual;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.procha.pagamentoModeloConceitual.domain.Categoria;
import dev.procha.pagamentoModeloConceitual.domain.Produto;
import dev.procha.pagamentoModeloConceitual.repositories.CategoriaRepository;
import dev.procha.pagamentoModeloConceitual.repositories.ProdutoRepository;

@SpringBootApplication
public class PagamentoModeloConceitualApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(PagamentoModeloConceitualApplication.class, args);
	}
	// Injetando as dependencias do repositorio de produtos
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		// Instanciaremos os produtos
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		// Já criamos os Produtos e Categorias mas as listas que fazem a relação entre eles ainda estão vazias
		// Temos que associar a categoria aos seus respectivos produtos
		
		// Pegaremos a categoria cat1, pegaremos a sua lista de produtos e adicionaremos os itens nela
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		// O mesmo com cat2
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		// Agora as categorias conhecem os produtos que estão associados com elas, porém os produtos ainda não conhecem suas categorias
		// Então faremos o mesmo com os produtos.
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		// Pronto, agora fizemos as associações de mão dupla entre Categorias e Produtos.
		
		// Já estamos salvando as nossas Categorias, falta criar um Repository de Produtos
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
		// Agora salvando os produtos
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
	}
	
	

}
