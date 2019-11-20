package base;

import java.io.Serializable;

import validacao.Validacao;

/**
 * Representacao de um objetivo de uma pesquisa. Um objetivo define a finalidade de uma pesquisa e pode ser de dois tipos: geral ou especifico.
 * Um objetivo geral e mais abrangente e responde diretamente ao problema da pesquisa, um objetivo especifico delimita alvos específicos para atingir
 * o objetivo geral.
 * Todo objetivo tem um tipo, podendo ser geral ou especifico, uma descricao e dois valores inteiros que podem ir de 1 a 5 que correspondem
 * a aderencia e a viabilidade do objetivo.
 *
 */
public class Objetivo extends Validacao implements Serializable {
	/**
	 * Tipo do objetivo. Pode ser geral ou especifico.
	 */
	private String tipo;
	/**
	 * Descricao do objetivo.
	 */
	private String descricao;
	/**
	 * Inteiro que pode ir de 1 a 5 e corresponde a aderencia do objetivo.
	 */
	private int aderencia;
	/**
	 * Inteiro que pode ir de 1 a 5 e corresponde a viabilidade do objetivo.
	 */
	private int viabilidade;
	/**
	 * id que identifica unicamente um Objetivo.
	 * Todo id tem o formato "On", em que n e um número inteiro.
	 */
	private String id;
	
	/**
	 * Constroi um objetivo a partir de um tipo (geral ou específico), de uma descricao e de dois inteiros correspondentes a
	 * aderencia e viabilidade do objetivo.
	 * @param tipo tipo do objetivo (pode ser "GERAL" ou "ESPECÍFICO")
	 * @param descricao descricao do objetivo
	 * @param aderencia inteiro que corresponde a aderencia do objetivo, pode ser qualquer valor de 1 a 5
	 * @param viabilidade inteiro que corresponde a viabilidade do objetivo, pode ser qualquer valor de 1 a 5
	 * @param id id que identifica unicamente um Objetivo
	 */
	public Objetivo(String tipo, String descricao, int aderencia, int viabilidade, String id) {
		super.validaString(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		super.validaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		super.validaTipo(tipo, "Valor invalido de tipo.");
		super.validaValor(aderencia, "Valor invalido de aderencia");
		super.validaValor(viabilidade, "Valor invalido de viabilidade.");
		super.validaString(id, "id nao pode ser nulo ou vazio");
		this.tipo = tipo;
		this.descricao = descricao;
		this.aderencia = aderencia;
		this.viabilidade = viabilidade;
		this.id = id;
	}
	
	/**
	 * Retorna a representacao textual de um Objetivo no formato "TIPO - DESCRIÇÃO - VALOR"
	 * O valor retornado nessa representação textual corresponde a soma dos valores dos atributos "aderencia" e "viabilidade" do objetivo.
	 * @return String que corresponde a representação textual de um Objetivo
	 */
	@Override
	public String toString() {
		int valor = aderencia + viabilidade;
		return id + " - " + tipo + " - " + descricao + " - " + valor;
	}

	/**
	 * Retorna um inteiro que representa um objetivo.
	 * O criterio utilizado para a geração deste inteiro e o id do objetivo.
	 * @return numero inteiro que representa um objetivo de acordo com seu id
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Retorna um valor booleano que indica se este objetivo e igual a outro objetivo passado como parametro
	 * do metodo.
	 * O criterio utilizado para analisar se um objetivo e igual ou diferente a outro objetivo e o id destes, se estes
	 * tiverem o mesmo id, eles sao iguais, caso contrario sao diferentes.
	 * @return true, caso os objetos forem iguais, ou false, caso os objetos forem diferentes
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Objetivo other = (Objetivo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * Busca se um termo esta contido na descricao.
	 * @param termo termo que se deseja procurar.
	 * @return String contendo o id e a descricao do objetivo, caso o termo seja mencionado, se nao, o objeto null e retornado.
	 */
    public String buscaTermo(String termo) {
		if (this.descricao.toLowerCase().contains(termo.toLowerCase())) {
			return this.id + ": " + this.descricao;
		}
		return null;
    }
}
