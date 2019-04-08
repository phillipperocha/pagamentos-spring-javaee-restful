package dev.procha.pagamentoModeloConceitual.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// Como ela será um controlador Rest precisamos passar a diretiva através de um anotation
@RestController
// E aqui passaremos o endpoint rest que queremos pra esse controlador
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	// Agora vamos criar um método básico apenas pra teste
	// Para essa função ser rest temos que associá-la a algum verbo http, no caso o GET
	@RequestMapping(method=RequestMethod.GET)
	public String listar() {
		return "Rest está funcionando!";
	}

}
