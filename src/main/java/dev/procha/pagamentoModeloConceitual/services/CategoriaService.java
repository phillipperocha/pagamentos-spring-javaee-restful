package dev.procha.pagamentoModeloConceitual.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.procha.pagamentoModeloConceitual.domain.Categoria;
import dev.procha.pagamentoModeloConceitual.exceptions.ObjectNotFoundException;
import dev.procha.pagamentoModeloConceitual.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	// Faremos um tratamento de exceções em nossas requisições.
	
	public Categoria buscar(Integer id) {
		// Aqui buscamos os objetos em Categoria
		// Como o método findById funciona? Bem, se o ID existe, ele retorna o objeto.
		// Se não existe ele retorna nulo.		
	    Optional<Categoria> obj = repo.findById(id);
	    
	    // E aqui utilizaremos um recurso do java 8, se o objeto não existir ele lança uma exceção
	    // Utilizamos um lambda para instanciar a exceção que criaremos agora:
	    return obj.orElseThrow(() -> new ObjectNotFoundException(
	    	"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
}
