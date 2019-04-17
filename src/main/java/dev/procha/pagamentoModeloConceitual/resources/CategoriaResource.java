package dev.procha.pagamentoModeloConceitual.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		
		List<Categoria> list = service.findAll();
		
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	
	// Agora utilizaremos um value para que ele precise ir em categorias/page para acessar esse endpoint
	@RequestMapping(value="page", method=RequestMethod.GET)
	
	// O método não vai retornar uma Page<Categoria> e acrescentar no método findPage os parâmetros que temos que passar
	
	// Além disso teremos que por algumas anotações nesses parâmetros para que eles sejam pegos na requisição
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			// Esses parâmetros vamos deixar como opcionais, não os deixaremos como PathVariables.
			// Vamos fazê-los como parâmetros do GET e não como variáveis
			// O caminho ficará: /categorias/page?page=0&linesPerPage=20....
			
			// Para que eles sejam parâmetros opcionais utilizaremos @RequestParm passando nome e valor padrão.
			// Nesse caso da gente, se não informarmos o valor da página automaticamente irá para a página 0.
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			// Sugestão do linesPerPage é por o 24, porque é múltiplo de 1,2, 3, 4 e fica fácil para organizar na tela
			@RequestParam(value="linesPerPage", defaultValue = "24")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "nome")String orderBy, 
			@RequestParam(value="direction", defaultValue = "ASC")String direction) {
		
			Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
			
			// Agora temos que converter uma Page de Categoria em uma Page de DTO
			// Como o Page já é Java8 compliance, não precisamos nem do .stream que usamos antes
			// Nem do .collect() que também usamos antes.
			
			Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
			
			return ResponseEntity.ok().body(listDto);
		
	}
	

}
