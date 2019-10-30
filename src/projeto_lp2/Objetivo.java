package projeto_lp2;

/**
 * Representação de um objetivo de uma pesquisa. Um objetivo define a finalidade de uma pesquisa e pode ser de dois tipos: geral ou específico.
 * Um objetivo geral é mais abrangente e responde diretamente ao problema da pesquisa, um objetivo específico delimita alvos específicos para atingir
 * o objetivo geral.
 * Todo objetivo tem um tipo, podendo ser geral ou específico, uma descrição e dois valores inteiros que podem ir de 1 a 5 que correspondem
 * à aderência e à viabilidade do objetivo.
 * @author Daniel Fonseca
 *
 */
public class Objetivo extends Validacao {
	/**
	 * Tipo do objetivo. Pode ser geral ou específico.
	 */
	private String tipo;
	/**
	 * Descrição do objetivo.
	 */
	private String descricao;
	/**
	 * Inteiro que pode ir de 1 a 5 e corresponde à aderência do objetivo.
	 */
	private int aderencia;
	/**
	 * Inteiro que pode ir de 1 a 5 e corresponde à viabilidade do objetivo.
	 */
	private int viabilidade;
	
	/**
	 * Constrói um objetivo a partir de um tipo (geral ou específico), de uma descrição e de dois inteiros correspondentes à
	 * aderência e viabilidade do objetivo.
	 * @param tipo tipo do objetivo (pode ser "GERAL" ou "ESPECÍFICO"
	 * @param descricao descrição do objetivo
	 * @param aderencia inteiro que corresponde à aderência do objetivo, pode ser qualquer valor de 1 a 5
	 * @param viabilidade inteiro que corresponde à viabilidade do objetivo, pode ser qualquer valor de 1 a 5
	 */
	public Objetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		super.validaString(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		super.validaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		super.validaTipo(tipo, "Valor invalido de tipo.");
		super.validaValor(aderencia, "Valor invalido de aderencia");
		super.validaValor(viabilidade, "Valor invalido de viabilidade.");
		this.tipo = tipo;
		this.descricao = descricao;
		this.aderencia = aderencia;
		this.viabilidade = viabilidade;
	}
	
	/**
	 * Retorna a representação textual de um Objetivo no formato "TIPO - DESCRIÇÃO - VALOR"
	 * O valor retornado nessa representação textual corresponde à soma dos valores dos atributos "aderencia" e "viabilidade" do objetivo.
	 * @return String que corresponde à representação textual de um Objetivo
	 */
	@Override
	public String toString() {
		int valor = aderencia + viabilidade;
		return tipo + " - " + descricao + " - " + valor;
	}
}
