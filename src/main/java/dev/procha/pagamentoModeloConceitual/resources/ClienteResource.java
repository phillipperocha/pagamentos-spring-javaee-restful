package dev.procha.pagamentoModeloConceitual.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.procha.pagamentoModeloConceitual.domain.Cliente;
import dev.procha.pagamentoModeloConceitual.services.ClienteService;

// Avisar que temos um Controlador Rest
@RestController
// E mapear sua rota
@RequestMapping(value="/clientes")
public class ClienteResource {

	// Por a dependencia de seu serviço
	@Autowired
	private ClienteService service;
	
	// Mapear o endpoint /cliente/{id}, com método Get
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		
		Cliente obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}

}