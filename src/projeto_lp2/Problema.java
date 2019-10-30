package projeto_lp2;

/**
 * Representação de um problema de um problema de uma pesquisa.
 * Todo problema contém uma descrição e um valor inteiro que vai de 1 a 5 corresponde à viabilidade. 
 * @author Daniel Fonseca
 *
 */
public class Problema extends Validacao {
	/**
	 * Descrição do problema.
	 */
	private String descricao;
	/**
	 * Inteiro que pode ser qualquer valor de 1 a 5 e representa a viabilidade da resolução do problema
	 */
	private int viabilidade;
	
	/**
	 * Constrói um Problema a partir de uma descrição e de um valor inteiro que corresponde à viabilidade.
	 * @param descricao descrição do problema
	 * @param viabilidade valor inteiro que pode ir de 1 a 5 e corresponde à viabilidade
	 */
	public Problema(String descricao, int viabilidade) {
		super.validaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		super.validaValor(viabilidade, "Valor invalido de viabilidade.");
		this.descricao = descricao;
		this.viabilidade = viabilidade;
	}
	
	/**
	 * Retorna a representação textual de um Problema no formato "DESCRIÇÃO - VIABILIDADE".
	 * @return String que corresponde à representação textual de um Problema
	 */
	@Override
	public String toString() {
		return descricao + " - " + viabilidade;
	}
	
}
