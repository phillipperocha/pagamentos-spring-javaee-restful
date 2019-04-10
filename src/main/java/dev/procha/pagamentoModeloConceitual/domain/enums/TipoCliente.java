package dev.procha.pagamentoModeloConceitual.domain.enums;

public enum TipoCliente {

	// A implementação mais básica de um Enumeration é implementar os valores
	// separados por vírgula.
	
	// Quando mapearmos esse Enumeration com o JPA, ele vai salvar no banco de uma de duas maneiras:
	
	// Ou ele salva o nome em forma de String, ou ele grava um int
	
	// O int por padrão vai começar no 0 e seguindo conforme os enumerations vai descendo.
	
	// Gravar um enum com String não é legal, porque ocupa muito espaço desnecessariamente.
	
	// Se quisermos gravar como números isso é feito automaticamente, mas tem um problema:
	
	// Ele vai pegar o primeiro e por 0, o segundo 1 e assim por diante. No futuro se algum
	// Dev pegar e adicionar um Enum a mais aqui na ordem errada, vai acabar com todos os seus dados.
	
	// O ideal então é fazermos um controle manual de qual numero é qual informação
	
	// Primeiro colocamos um número em cada valor (e um nome caso queira imprimir esses valores):
	
	// PESSOAFISICA(1, "Pessoa Física"), PESSOAJURIDICA(2, "Pessoa Jurídica")
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(1, "Pessoa Jurídica");
	
	// Precisamos criar um construtor para incluir esses valores em nosso enumeration
	// e também duas variáveis para guardar esses valores
	
	private int cod;
	private String descricao;
	
	// Construtor de tipo ENUMERATION ele é PRIVATE!
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	// E FAZER APENAS O MÉTODO GET! (SEM SET)
	// Uma vez que instanciamos um Enumeration nunca mais mudamos o nome dele
	
	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	// Agora faremos uma operação que recebe o código e retorna o tipo cliente
	
	public static TipoCliente toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (TipoCliente x : TipoCliente.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		} 
		
		// Se o código não pertencer a ninguém lançaremos uma exceção
		throw new IllegalArgumentException("Id inválido: " + cod);
		
	}
	
}
