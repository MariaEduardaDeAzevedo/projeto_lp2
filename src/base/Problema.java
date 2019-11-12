package base;

import controller.Validacao;

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
	 * id que identifica um problema unicamente.
	 * Todo id de um problema tem o formato "Pn", em que n é um número inteiro.
	 */
	private String id;
	
	/**
	 * Constrói um Problema a partir de uma descrição e de um valor inteiro que corresponde à viabilidade.
	 * @param descricao descrição do problema
	 * @param viabilidade valor inteiro que pode ir de 1 a 5 e corresponde à viabilidade
	 * @param id id que identifica unicamente um problema
	 */
	public Problema(String descricao, int viabilidade, String id) {
		super.validaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		super.validaValor(viabilidade, "Valor invalido de viabilidade.");
		super.validaString(id, "id nao pode ser nulo ou vazio");
		this.descricao = descricao;
		this.viabilidade = viabilidade;
		this.id = id;
	}
	
	/**
	 * Retorna um inteiro que representa um Problema.
	 * O critério utilizado para gerar este inteiro é o id do problema.
	 * @return número inteiro que representa um problema
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Retorna um valor booleano que indica se um Problema é igual a outro passado como parâmetro do método.
	 * Dois problemas são iguais quando estes tem o mesmo id.
	 * @return true, se o problema for igual ao problema passado como parâmetro, ou false, se o problema for diferente
	 * do problema passado como método
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Problema other = (Problema) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * Retorna a representação textual de um Problema no formato "DESCRIÇÃO - VIABILIDADE".
	 * @return String que corresponde à representação textual de um Problema
	 */
	@Override
	public String toString() {
		return descricao + " - " + viabilidade;
	}

    public String buscaTermo(String termo) {
		if (this.descricao.toLowerCase().contains(termo.toLowerCase())) {
			return this.id + ": " + this.descricao;
		}
		return null;
    }

    /**
     * Retorna o ID que identifica unicamente um objeto Problema
     * 
     * @return String com ID que representa unicamente objeto Problema
     */
	public String getId() {
		
		return this.id;
	
	}
}
