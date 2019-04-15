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
	
	// Vamos também atualizar o nome do método para o padrão que estamos usando em inglês
	public Categoria find(Integer id) {
	    Optional<Categoria> obj = repo.findById(id);
	    
	    return obj.orElseThrow(() -> new ObjectNotFoundException(
	    	"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);

		return repo.save(obj);
	}
	
	// Implementaremos agora o nosso método de atualização
	public Categoria update(Categoria obj) {
		
		// O método save do repository do SpringData serve tanto pra inserir como pra criar um novo.
		// Ele muda quando tem um ID ou não passado como argumento.
	
		// Antes de retornar o método verificaremos se o ID existe
		
		find(obj.getId()); // O find vai buscar o objeto e se não existir ele vai estourar uma exceção
		
		
		// Agora retornaremos o que devemos
		return repo.save(obj);
	}
	
}
