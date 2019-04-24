package dev.procha.pagamentoModeloConceitual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import dev.procha.pagamentoModeloConceitual.domain.Cliente;
import dev.procha.pagamentoModeloConceitual.dto.ClienteDTO;
import dev.procha.pagamentoModeloConceitual.exceptions.ObjectNotFoundException;
import dev.procha.pagamentoModeloConceitual.repositories.ClienteRepository;
import dev.procha.pagamentoModeloConceitual.resources.exception.DataIntegrityException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado com o Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente update(Cliente obj) {
		// Vamos criar o objeto Cliente com o que achamos no banco
		Cliente newObj = find(obj.getId());
		
		// E criaremos um método auxiliar para atualizar os dados do novo objeto que criamos com base no objeto que 
		// veio como argumento.
		updateData(newObj, obj);
		
		// Depois salvaremos o novo objeto com os dados já atualizados
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		
		try {
			repo.deleteById(id);	
		} catch (DataIntegrityViolationException e) {
		
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");			
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	// Por enquanto não implementaremos aqui, retornaremos uma exceção só de método não implementado
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
}
