package dev.procha.pagamentoModeloConceitual.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.procha.pagamentoModeloConceitual.domain.Pedido;
import dev.procha.pagamentoModeloConceitual.exceptions.ObjectNotFoundException;
import dev.procha.pagamentoModeloConceitual.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado com o Id: " + id + ", Tipo: " + Pedido.class.getName())
			);
	}
}
