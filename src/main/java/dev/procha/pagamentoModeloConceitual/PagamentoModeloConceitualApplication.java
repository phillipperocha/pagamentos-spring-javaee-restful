package dev.procha.pagamentoModeloConceitual;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.procha.pagamentoModeloConceitual.domain.Categoria;
import dev.procha.pagamentoModeloConceitual.domain.Cidade;
import dev.procha.pagamentoModeloConceitual.domain.Cliente;
import dev.procha.pagamentoModeloConceitual.domain.Endereco;
import dev.procha.pagamentoModeloConceitual.domain.Estado;
import dev.procha.pagamentoModeloConceitual.domain.Produto;
import dev.procha.pagamentoModeloConceitual.domain.enums.TipoCliente;
import dev.procha.pagamentoModeloConceitual.repositories.CategoriaRepository;
import dev.procha.pagamentoModeloConceitual.repositories.CidadeRepository;
import dev.procha.pagamentoModeloConceitual.repositories.ClienteRepository;
import dev.procha.pagamentoModeloConceitual.repositories.EnderecoRepository;
import dev.procha.pagamentoModeloConceitual.repositories.EstadoRepository;
import dev.procha.pagamentoModeloConceitual.repositories.ProdutoRepository;

@SpringBootApplication
public class PagamentoModeloConceitualApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(PagamentoModeloConceitualApplication.class, args);
	}
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	// Adicionamos as dependências
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		// Adicionando os produtos as suas respectivas categorias
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));		
		// E o contrário
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		// Salvando as categorias e produtos
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		// Instancias dos Estados e Cidades
		Estado est1 = new Estado(null, "Paraíba");
		Estado est2 = new Estado(null, "São Paulo");
		
		// Como é um relacionamento UmParaMuitos a cidade já reconhece seu estado
		Cidade c1 = new Cidade(null, "João Pessoa", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Baueyx", est1);
		
		
		// Mas o estado precisa também reconhecer suas cidades
		// Então faremos a maneira de adicionar na sua lista de cidades.
		est1.getCidades().addAll(Arrays.asList(c1, c3));
		est2.getCidades().addAll(Arrays.asList(c2));
		
		// Agora salvaremos vários estados de uma vez, fazendo a transformação em lista
		// Como o estado tem várias cidades, inclusive nenhuma, salvamos primeiro o estado
		// E depois a cidade.
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		// Instanciado os Clientes
		// Atenção da forma que chama os enumerations! (novidade)
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		// adicionar os telefones ao Cliente cli1
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		// Agora instanciaremos os Enderecos
		Endereco e1 = new Endereco(null, "Av. Epitácio Pessoa", "300", "Apto 203", "Estados", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Av. Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		// Agora precisamos associar para que o Cliente conheça os endereços dele
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		// Agora salvando eles no banco
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}
}
