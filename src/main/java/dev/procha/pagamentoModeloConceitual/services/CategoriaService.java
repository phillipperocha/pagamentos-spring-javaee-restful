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
	
	public Categoria buscar(Integer id) {
	    Optional<Categoria> obj = repo.findById(id);
	    
	    return obj.orElseThrow(() -> new ObjectNotFoundException(
	    	"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		// Apenas por desencargo de consciência criaremos uma operação aqui só pra garantir que inserímos um objeto novo
		obj.setId(null);

		// Esse ID na inserção tem que ser nulo, porque se não for o método .save vai encarar como se fosse uma atualização
		// e não uma inserção.
		
		// E depois retornamos o objeto		
		return repo.save(obj);
	}
	
}
