package dev.procha.pagamentoModeloConceitual.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import dev.procha.pagamentoModeloConceitual.domain.enums.TipoCliente;

@Entity
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String email;
	private String cpfOuCnpj;
	// O tipo cliente Internamente será armazenado como um inteiro
	// Mas para o mundo externo a classe vai expor um dado TipoCliente 
	private Integer tipo;
	// Os getters, setters e construtores do tipo serão tipoCliente,
	// no construtor mudaremos um pouco, cheque lá.
	
	
	// O cliente pode ter vários endereços
	// E é mapeado na classe endereço como "cliente", é a sua FK lá
	@OneToMany(mappedBy = "cliente")
	private List<Endereco> enderecos = new ArrayList<>();
	
	// O telefone está modelado como uma entidade fraca (weak entity), ela nem tem id próprio
	// E ela é totalmente dependente do cliente.
	
	// Nesse caso, como o telefone é simplesmente uma String que só tem o número, nem criaremos uma classe
	// Dependendo da situação, podemos nem implementar uma classe quando ela for simples demais.
	// Mesmo ela tendo sido desenhada no projeto.
	
	// Vamos implementá-la como uma coleção de Strings associadas ao cliente
	// Usaremos um conjunto (set), pra garantir que não haverá repetição
	
	// Para mapear os telefones como entidade fraca também é diferente
	// Vamos usar a anotação @ElementCollection
	@ElementCollection
	// Agora informaremos o nome da tabela auxiliar que terá no banco de dados para
	// guardar os telefones
	@CollectionTable(name = "TELEFONE")
	private Set<String> telefones = new HashSet<>();
	
	// Pronto! Representamos os telefones como um conjunto de Strings.
	
	public Cliente() {
		
	}

	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		// Faremos o getCodigo do tipo
		this.tipo = tipo.getCod();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoCliente getTipo() {
		// Aqui no get usaremos a operação que fizemos na classe tipoCliente
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		// Só vamos armazenar o codigo
		this.tipo = tipo.getCod();
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
