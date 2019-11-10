package base;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.Validacao;

/**
 * Classe que representa uma atividade de pesquisa
 * 
 * @author Maria Eduarda Eduarda de Azevedo Silva - 119110210
 *
 */
public class Atividade extends Validacao {

	/**
	 * Descricao da atividade
	 */
	private String descricao;

	/**
	 * Lista de itens que compoem uma atividade
	 */
	private List<Item> itens;

	/**
	 * Nivel de risco da atividade
	 */
	private String risco;

	/**
	 * Descricao do risco da atividade
	 */
	private String descricaoRisco;

	/**
	 * ID que identifica unicamente uma atividade
	 */
	private String id;


	/**
	 * Constroi um objeto Atividade partindo de uma descricao, um nivel de risco e
	 * sua descricao e um ID
	 * 
	 * @param descricao      String que indica a descricao da atividade
	 * @param risco          String que indica o nivel do risco da atividade,
	 *                       podendo ser ALTO, MEDIO ou BAIXO
	 * @param descricaoRisco String que indica a descricao do risco
	 * @param id             String no formato An, no qual n e um numero inteiro,
	 *                       que identifica um objeto Atividade
	 */
	public Atividade(String descricao, String risco, String descricaoRisco, String id) {

		super.validaString(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		super.validaString(risco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		super.validaString(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");

		List valores = new ArrayList<String>();
		valores.add("BAIXO");
		valores.add("MEDIO");
		valores.add("ALTO");

		super.validaValoresPermitidos(valores, risco, "Valor invalido do nivel do risco.");

		this.descricao = descricao;
		this.risco = risco;
		this.descricaoRisco = descricaoRisco;
		this.itens = new ArrayList<Item>();
		this.id = id;

	}

	/**
	 * Cria um objeto Item e o adiciona na lista de itens
	 *
	 * @param descricao String que indica a descricao de um item a ser cadastrado na
	 *                  atividade
	 */
	public void cadastrarItem(String descricao) {

		super.validaString(descricao, "Item nao pode ser nulo ou vazio.");
		this.itens.add(new Item(descricao));

	}

	@Override
	/**
	 * Cria e retorna uma representacao em String de um objeto Atividade. A
	 * representacao em String de um objeto Atividade segue o seguinte modelo:
	 * "Descricao da atividade (RISCO - descricao do risco)" seguido de uma listagem
	 * de seus itens
	 * 
	 * @return String com representacao textual de um objeto Atividade
	 * 
	 */
	public String toString() {

		String listagem = String.format("%s (%s - %s)", this.descricao, this.risco, this.descricaoRisco);

		for (int i = 0; i < this.itens.size(); i++) {

			listagem += " | " + this.itens.get(i).toString();

		}

		return listagem;

	}

	@Override
	/**
	 * Gera e retorna uma representacao em inteiro de um objeto Atividade, partindo
	 * de seu ID
	 * 
	 * @return int com representacao em inteiro de um objeto Atividade
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	/**
	 * Compara dois objetos e retorna um boolean referente a sua equidade Dois
	 * objetos Atividade sao tidos como iguais quando tem o mesmo id
	 * 
	 * @return boolean referente a equidade dos objetos
	 * 
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atividade other = (Atividade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * Itera pelos itens do objeto Atividade e retorna a quantidade de itens que
	 * estao pendentes
	 * 
	 * @return inteiro com a quantidade de itens pendentes
	 */
	public int contaItensPendentes() {

		int contador = 0;

		for (Item i : this.itens) {

			if (!i.getStatus()) {

				contador++;

			}

		}

		return contador;

	}

	/**
	 * Itera pelos itens do objeto Atividade e retorna a quantidade de itens que ja
	 * foram realizados
	 * 
	 * @return inteiro com quantidade de itens que ja foram realizados
	 */
	public int contaItensRealizados() {

		int contador = 0;

		for (Item i : this.itens) {

			if (i.getStatus()) {

				contador++;

			}

		}

		return contador;

	}

    public String buscaTermoDescricao(String termo) {
		if (this.descricao.toLowerCase().contains(termo.toLowerCase())) {
			return this.id + ": " + this.descricao;
		}
		return null;
    }

	public String buscaTermoDescricaoDoRisco(String termo) {
		if (this.descricaoRisco.toLowerCase().contains(termo.toLowerCase())) {
			return this.id + ": " + this.descricaoRisco;
		}
		return null;
	}

	/**
	 * Metodo que permite acesso ao Id de uma atividade.
	 * @return Id da atividade
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Metodo que permite a execucao de um item.
	 * @param item Item a ser executado
	 * @param duracao Duracao da execucao.
	 */
	public void executaItem(int item, int duracao) {
		if(item > itens.size()) {
			throw new IllegalArgumentException("Item nao encontrado.");
		}
		/**
		if(itensExecutados.contains(item)) {
			throw new IllegalArgumentException("Item ja executado.");
		}
		
		this.itensExecutados.add(item);
		**/
		this.itens.get(item - 1).realizar();
		
	}
}
