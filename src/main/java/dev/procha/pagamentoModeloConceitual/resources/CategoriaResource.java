package dev.procha.pagamentoModeloConceitual.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.procha.pagamentoModeloConceitual.domain.Categoria;
import dev.procha.pagamentoModeloConceitual.dto.CategoriaDTO;
import dev.procha.pagamentoModeloConceitual.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		
		// Como mudamos o Find no Serive atualizaremos aqui
		Categoria obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		obj = service.insert(obj);
	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return  ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id) {
		obj.setId(id);
		
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();		
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	// Mudar a List<Categoria> para List<CategoriaDTO>
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		
		// Como fizemos o construtor do CategoriaDTO a partir de uma Categoria
		// Percorreremos a List<Categoria>, e para cada elemento na lista, instanciaremos um DTO correspondente
		List<Categoria> list = service.findAll();
		
		// Isso é um recurso do java 8 chamado stream.
		// Para cada elemento obj da lista ele vai executar o arrowfunction
		
		// Ele instanciará uma CategoriaDTO, feito isso temos que voltar o objeto pra lista
		// para isso usamos o .collect(Collectors.toList());
		
		// Com isso em apenas uma linha conseguimos converter uma lista para outra lista de tipos diferentes
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		
		// Agora retornaremos a nova lista como response
		return ResponseEntity.ok().body(listDto);
	}
	

}
