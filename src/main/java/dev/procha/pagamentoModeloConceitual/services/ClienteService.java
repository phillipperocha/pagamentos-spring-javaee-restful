package dev.procha.pagamentoModeloConceitual.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.procha.pagamentoModeloConceitual.domain.Cliente;
import dev.procha.pagamentoModeloConceitual.exceptions.ObjectNotFoundException;
import dev.procha.pagamentoModeloConceitual.repositories.ClienteRepository;

// Precisamos dizer que é um service
@Service
public class ClienteService {
	
	// Precisamos do repository de clientes
	@Autowired
	private ClienteRepository repo;
	
	// E precisamos implementar a busca por Id
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado com o Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}
