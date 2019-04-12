package dev.procha.pagamentoModeloConceitual.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.procha.pagamentoModeloConceitual.domain.Categoria;
import dev.procha.pagamentoModeloConceitual.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Categoria obj = service.buscar(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	// É void porque vai ser uma resposta Http que não vai ter corpo.
	
	// Vamos fazer o método insert recebendo uma Categoria
	
	//** A ANOTAÇÃO @RequestBody faz com que o Json seja convertido n objeto java do tipo Categoria automaticamente!
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		
		// Esse método vai ter que chamar um serviço que insere essa nova categoria no banco
		
		// Porque o obj vai receber o insert? Porque a operação insert do JPA ela retorna o objeto
		// E quando retornar ele vai ter um Id real, coisa que não tinha quando inserimos.
		obj = service.insert(obj);
		
		// ***** OBS *****
		// Aqui vai entrar uma boa prática! O protocolo HTtp quando estamos inserindo um recurso
		// tem um código de resposta particular para isso. ** google -> http status code
		
		// Quando uma requisição está inserindo, temos um código em particular que é o 201
		// E também nos diz que além de retornar o código, temos que retornar a URI do novo recurso
		
		// Então vamos aprender a fazer isso :D
		
		// Como o nosso obj, agora após o retorno possui o ID faremos assim (uma forma bem particular do java):
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		// O método fromCurrentRequest, ele pega a URI que usamos para inserir, ou seja, localhost:8080/categorias
		// e acrescentamos o /id do obeto que criamos. Atribuimos o valor ao {id} utilizando o BuildAndExpand
		// E por fim convertemos ele pra URI
		
		// O método created do ResponseEntity já retorna o código correto de inserção 201
		
		return  ResponseEntity.created(uri).build();
	}

}
