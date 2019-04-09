package dev.procha.pagamentoModeloConceitual;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.procha.pagamentoModeloConceitual.domain.Categoria;
import dev.procha.pagamentoModeloConceitual.repositories.CategoriaRepository;

@SpringBootApplication
// Para isso, a classe principal do sistema deve implementar uma interface chamada CommandLineRunner
// Essa interface permite implementar um método auxiliar para executar uma ação quando a aplicação iniciar.
public class PagamentoModeloConceitualApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(PagamentoModeloConceitualApplication.class, args);
	}
	
	// (**) - Referenciado abaixo
	@Autowired
	private CategoriaRepository categoriaRepository;

	// Esse é o método que ele pede
	@Override
	public void run(String... args) throws Exception {
		// Aqui vamos instanciar os nossos objetos pra já iniciar com eles em banco
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		// Agora precisamos salvá-los em banco.
		// Para isso precisamos utilizar o CategoriaRepository
		// Faremos uma dependência dele lá em cima em (**)
		
		// Agora, chamaremos aqui para utilizar a inserção
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		// O comando asList() cria automaticamente uma lista com os objetos no argumento
		
	}
	
	

}
