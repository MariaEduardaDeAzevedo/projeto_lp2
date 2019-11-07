package base;
import excecoes.FuncaoInvalidaException;

public class Professor extends Pesquisador {
	private String formacao;
	private String unidade;
	private String dataContratacao;
	
	public Professor(String nome, String funcao, String biografia, String email, String foto, String formacao, String unidade, String dataContratacao) {
		super(nome, funcao, biografia, email, foto);
		if (!funcao.equals("professor")) {
			throw new FuncaoInvalidaException("Pesquisador nao compativel com a especialidade.");
		}
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
	
	public void setFormacao(String novoValor) {
		super.validaString(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		formacao = novoValor;
	}
	
	public void setUnidade(String novoValor) {
		super.validaString(novoValor, "Campo unidade nao pode ser nulo ou vazio.");
		unidade = novoValor;
	}
	
	public void setData(String novoValor) {
		super.validaString(novoValor, "Campo data nao pode ser nulo ou vazio.");
		super.validaData(novoValor, "Atributo data com formato invalido.");
		dataContratacao = novoValor;
	}
	
	@Override
	public void alteraPesquisador(String atributo, String novoValor) {
		 super.validaString(atributo, "Atributo nao pode ser vazio ou nulo.");
		 switch(atributo.trim()) {
		 	case "NOME":
				super.validaString(novoValor, "Campo nome nao pode ser nulo ou vazio.");
				this.setNome(novoValor);
				break;
			case "FUNCAO":
				super.validaString(novoValor, "Campo funcao nao pode ser nulo ou vazio.");
				this.setFuncao(novoValor);
				break;
			case "BIOGRAFIA":
				super.validaString(novoValor, "Campo biografia nao pode ser nulo ou vazio.");
				this.setBiografia(novoValor);
				break;
			case "EMAIL":
				super.validaString(novoValor, "Campo email nao pode ser nulo ou vazio.");
				super.verificaEmail(novoValor);
				this.setEmail(novoValor);
				break;
			case "FOTO":
				super.validaString(novoValor, "Campo fotoURL nao pode ser nulo ou vazio.");
				super.verificaURL(novoValor);
				this.setFoto(novoValor);
				break;
			case "FORMACAO":
				super.validaString(novoValor, "Campo formacao nao pode ser nulo ou vazio.");
				this.setFormacao(novoValor);
				break;
			case "UNIDADE":
				super.validaString(novoValor, "Campo unidade nao pode ser nulo ou vazio.");
				this.setUnidade(novoValor);
				break;
			case "DATA":
				super.validaString(novoValor, "Campo data nao pode ser nulo ou vazio.");
				super.validaData(novoValor, "Atributo data com formato invalido.");
				this.setData(novoValor);
				break;
			default:
				throw new IllegalArgumentException("Atributo invalido.");
		 }
	 }
}
