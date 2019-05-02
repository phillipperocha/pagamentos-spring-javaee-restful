package dev.procha.pagamentoModeloConceitual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.procha.pagamentoModeloConceitual.domain.Cidade;
import dev.procha.pagamentoModeloConceitual.domain.Cliente;
import dev.procha.pagamentoModeloConceitual.domain.Endereco;
import dev.procha.pagamentoModeloConceitual.domain.enums.TipoCliente;
import dev.procha.pagamentoModeloConceitual.dto.ClienteDTO;
import dev.procha.pagamentoModeloConceitual.dto.ClienteNewDTO;
import dev.procha.pagamentoModeloConceitual.exceptions.ObjectNotFoundException;
import dev.procha.pagamentoModeloConceitual.repositories.ClienteRepository;
import dev.procha.pagamentoModeloConceitual.repositories.EnderecoRepository;
import dev.procha.pagamentoModeloConceitual.resources.exception.DataIntegrityException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	// Criar uma instância do EnderecoRepository para poder salvá-lo
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado com o Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional // para salvar tanto o cliente como os endereços na mesma transação do banco
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		
		// Agora vamos salvar o endereço que pegamos do cliente
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		
		updateData(newObj, obj);
		
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		
		try {
			repo.deleteById(id);	
		} catch (DataIntegrityViolationException e) {
		
			throw new DataIntegrityException("Não é possível excluir um Cliente porque há pedidos relacionadas.");			
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
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		// O clienteNewDTO recebe um tipo como um Integer, enquanto o new Cliente recebe um tipo.
		// Precisamos converter
		
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		
		// Agora instanciando o endereço, mas para isso precisamos instanciar a cidade
		// e passar o seu código como ID, para quando for salvar o Endereço no banco ele saber a qual cidade está ligado
		Cidade cidade = new Cidade(objDto.getCidadeId(), null, null);
		
		Endereco end = new Endereco(null,  objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cidade);
		
		// daí incluímos o endereço na lista de endereços do cliente.
		cli.getEnderecos().add(end);
		
		// e os telefones
		cli.getTelefones().add(objDto.getTelefone1());
		
		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
}
