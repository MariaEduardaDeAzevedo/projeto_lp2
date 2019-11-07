package base;

import controller.Validacao;

/**
 * Classe que representa um item de uma atividade de pesquisa
 * 
 * @author Maria Eduarda de Azevedo - 119110210
 *
 */
public class Item extends Validacao {

	/**
	 * Descricao do item
	 */
	private String descricao;

	/**
	 * Status que indica se o item esta pendente ou realizado
	 */
	private boolean status;

	/**
	 * Representacao textual do status do item
	 */
	private String statusString;

	/**
	 * Constroi um objeto Item partindo de sua descricao
	 * 
	 * @param descricao String com descricao de um item que compoe uma atividade de
	 *                  pesquisa
	 */
	public Item(String descricao) {

		super.validaString(descricao, "Item nao pode ser nulo ou vazio.");

		this.descricao = descricao;
		this.status = false;
		this.statusString = "PENDENTE";
	}

	/**
	 * Altera os atributos status e statusString, tornando um item pendente
	 * realizado
	 */
	public void realizar() {

		this.statusString = "REALIZADO";
		this.status = true;
	}

	/**
	 * Retorna o valor do atributo status de um objeto Item.
	 * 
	 * false - Pendente true - realizado
	 * 
	 * @return boolean referente ao status de pendencia de um objeto Item
	 */
	public boolean getStatus() {

		return this.status;

	}

	@Override
	/**
	 * Cria e retorna uma representacao textual para um objeto Item nos seguintes
	 * moldes: "STATUS - Descricao do Item"
	 */
	public String toString() {

		return this.statusString + " - " + this.descricao;

	}

}
