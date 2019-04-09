package dev.procha.pagamentoModeloConceitual.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.procha.pagamentoModeloConceitual.domain.Categoria;
import dev.procha.pagamentoModeloConceitual.services.CategoriaService;

@RestController
// Temos que acrescentar que nesse endpoint ele ainda vai receber o ID da categoria
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	// Injetaremos a dependência do CategoriaService, que buscará pelo id
	@Autowired
	private CategoriaService service;
	
	// Para isso colocaremos aqui um value, pode ser {id} ou qualquer outro nome dentro de chaves.
	// Ou seja, o endpoint desse método agora vai ser /categorias/(algumacoisa)
	// Mudaremos o nome do método pra find e diremos que ele vai receber um Integer
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	// Para que o Spring saiba que esse Id de baixo vai ser o mesmo que veio na requisição
	// Temos que incluir uma anotação chamada @PathVariable
	
	// Agora vamos sofisticar um pouco nosso método. Ele não retornará uma lista nem mesmo uma categoria
	// E sim um ResponseEntity. Esse tipo ResponseEntity é um tipo especial do Spring
	// que ele já encapsula várias informações de uma resposta Http para um serviço rest
	
	// O tipo dele colocaremos ? porque pode ser qualquer tipo, pode encontrar ou não
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Categoria obj = service.buscar(id);
		
		return ResponseEntity.ok().body(obj);
	}

}
