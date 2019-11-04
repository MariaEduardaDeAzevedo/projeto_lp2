package base;

public class Professor extends Pesquisador {
	private String formacao;
	private String unidade;
	private String dataContratacao;
	
	public Professor(String nome, String funcao, String biografia, String email, String foto, String formacao, String unidade, String dataContratacao) {
		super(nome, funcao, biografia, email, foto);
		super.validaString(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		super.validaString(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		super.validaString(dataContratacao, "Campo data nao pode ser nulo ou vazio.");
		super.validaData(dataContratacao, "Atributo data com formato invalido.");
		this.formacao = formacao;
		this.unidade = unidade;
		this.dataContratacao = dataContratacao;
	}
	
	@Override
	public String toString() {
		return String.format("%s (%s) - %s - %s - %s - %s - %s - %s", this.getNome(), this.getFuncao(), this.getBiografia(), this.getEmail(), this.getFoto(), formacao, unidade, dataContratacao);
	}
}
