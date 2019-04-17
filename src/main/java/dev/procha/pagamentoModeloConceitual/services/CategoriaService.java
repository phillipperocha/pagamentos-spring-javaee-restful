package dev.procha.pagamentoModeloConceitual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import dev.procha.pagamentoModeloConceitual.domain.Categoria;
import dev.procha.pagamentoModeloConceitual.exceptions.ObjectNotFoundException;
import dev.procha.pagamentoModeloConceitual.repositories.CategoriaRepository;
import dev.procha.pagamentoModeloConceitual.resources.exception.DataIntegrityException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
	    Optional<Categoria> obj = repo.findById(id);
	    
	    return obj.orElseThrow(() -> new ObjectNotFoundException(
	    	"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);

		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		
		try {
			repo.deleteById(id);	
		} catch (DataIntegrityViolationException e) {
		
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");			
		}
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	// Faremos uma função só para retornar a quantidade de categorias que quisermos
	// Ela retornará uma página de categorias
	// Para isso utilizaremos uma classe do Spring Data chamada Page.
	// Esse Page vai encapsular informações e operações sobre a paginação
	// Teremos que informar nele qual a página quer buscar (a contagem de páginas começa de zero)
	// Em seguida diremos quantas linhas por página e por qual atributo deverá ser ordenado e outro em qual direção
	// Descendente ou Ascendente.
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		// Para fazermos uma consulta e retornar uma página de dados temos que criar outro objeto do tipo PageRequest
		// Esse PageRequest é um objeto que vai preparar as minhas informações para que faça a consulta que nos retorne
		// a página de dados.
		// O direction precisa receber um objeto Direciton, para isso utilizamos esse método estático dele.
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		// Agora é só retornar o findAll passando o PageRequest como argumento que automaticamente o findAll
		// Vai considerar o PageRequest como argumento e vai retornar a página
		return repo.findAll(pageRequest);
	}
	
}
