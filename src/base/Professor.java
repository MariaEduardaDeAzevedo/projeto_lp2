package base;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representacao de um professor, que e, no sistema Psquiza, uma especializacao de Pesquisador, sendo esta sua superclasse.
 * Todo professor, por tambem ser um pesquisador, possui um nome, uma biografia, um email, uma foto e um status (que diz se o pesquisador e ativo ou nao), ainda tendo
 * tambem uma formacao, unidade alocada e data de contratacao.
 * Assim como pesquisador, todo professor e identificado unicamente pelo seu email.
 * @author Daniel Fonseca
 *
 */
public class Professor extends Pesquisador implements Serializable {
	/**
	 * Grau de formacao do professor.
	 */
	private String formacao;
	/**
	 * Unidade alocada do professor.
	 */
	private String unidade;
	/**
	 * Data de contratacao do professor, que deve, obrigatoriamente, seguir o formato "DD/MM/AAAA", em que DD corresponde ao dia (e deve ser um valor entre 1 e 31), MM corresponde ao mês (devendo 
	 * ser um valor entre 1 e 12) e AAAA corresponde ao ano, que não pode ser superior a 2019.
	 * O dia e o mes devem, obrigatoriamente, ter dois digitos. Ja o ano deve ter sempre quatro digitos.
	 */
	private String dataContratacao;
	
	/**
	 * Constroi um professor a partir de seu nome, sua função, sua biografia, seu email, a url de sua foto, seu grau de formação, sua unidade alocada
	 * e sua data de contratacao.
	 * @param nome nome do professor.
	 * @param funcao função do professor, que deve, obrigatoriamente, ser "professor".
	 * @param biografia biografia do professor, que é uma descricao textual de sua atuacao em pesquisa.
	 * @param email email do professor.
	 * @param foto url da foto do professor.
	 * @param formacao grau de formacao do professor.
	 * @param unidade unidade alocada do professor.
	 * @param dataContratacao data de contratacao do professor, que deve, obrigatoriamente, seguir o formato "DD/MM/AAAA".
	 */
	public Professor(String nome, String funcao, String biografia, String email, String foto, String formacao, String unidade, String dataContratacao) {
		super(nome, funcao, biografia, email, foto);
		List lista = new ArrayList<String>();
		lista.add("professor");
		super.validaValoresPermitidos(lista , funcao, "Pesquisador nao compativel com a especialidade.");
		super.validaString(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		super.validaString(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		super.validaString(dataContratacao, "Campo data nao pode ser nulo ou vazio.");
		super.validaData(dataContratacao, "Atributo data com formato invalido.");
		this.formacao = formacao;
		this.unidade = unidade;
		this.dataContratacao = dataContratacao;
	}
	
	/**
	 * Retorna uma String que representa o professor. A representacao segue o formato "NOME (FUNÇÃO) - BIOGRAFIA - EMAIL - FOTO - FORMACAO - UNIDADE - DATACONTRATACAO".
	 * @return representacao textual do professor.
	 */
	@Override
	public String toString() {
		return String.format("%s (%s) - %s - %s - %s - %s - %s - %s", this.getNome(), this.getFuncao(), this.getBiografia(), this.getEmail(), this.getFoto(), formacao, unidade, dataContratacao);
	}
	
	/**
	 * Altera o valor da formacao do professor.
	 * @param novoValor novo valor do grau de formacao do professor, que substituira o antigo valor.
	 */
	public void setFormacao(String novoValor) {
		super.validaString(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		formacao = novoValor;
	}
	
	/**
	 * Altera o valor da unidade do professor.
	 * @param novoValor novo valor da unidade alocada do professor, que substituira o antigo valor.
	 */
	public void setUnidade(String novoValor) {
		super.validaString(novoValor, "Campo unidade nao pode ser nulo ou vazio.");
		unidade = novoValor;
	}
	
	/**
	 * Altera o valor da data de contratacao do professor.
	 * @param novoValor novo valor da data de contratacao do professor, que substituira o antigo e tambem deve ter o formato "DD/MM/AAAA".
	 */
	public void setData(String novoValor) {
		super.validaString(novoValor, "Campo data nao pode ser nulo ou vazio.");
		super.validaData(novoValor, "Atributo data com formato invalido.");
		dataContratacao = novoValor;
	}
	
	/**
	 * Metodo sobrescrito de Pesquisador que permite a alteracao dos atributos de Professor.
	 * @param atributo nome do atributo a ser alterado.
	 * @param novoValor novo valor do atributo.
	 */
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
