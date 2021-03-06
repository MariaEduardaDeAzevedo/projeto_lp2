package base;

import java.io.Serializable;

import validacao.Validacao;

/**
 * Classe que representa um item de uma atividade de pesquisa
 * 
 * @author Maria Eduarda de Azevedo - 119110210
 *
 */
public class Item extends Validacao implements Serializable {

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
	 * Tempo de duracao de um item
	 */
	private int duracao;

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
		this.duracao = 1;
	}

	/**
	 * Altera os atributos status e statusString, tornando um item pendente
	 * realizado
	 */
	public void realizar(int duracao) {
		super.validaStatus(! status, "Item ja executado.");
		this.statusString = "REALIZADO";
		this.duracao = duracao;
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
	
	/**
	 * Retorna a representacao em String de um Item
	 * @return String que representa o status de um Item
	 */
	public String getStatusString() {
		return this.statusString;
	}
	
	/**
	 * Retorna a duracao do Item em questao
	 * @return int com o valor do atributo duracao
	 */
	public int getDuracao() {	
		return this.duracao;
	}
}
