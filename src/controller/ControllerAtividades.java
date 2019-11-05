package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.Atividade;

/**
 * Entidade controladora de objetos Atividade e Item
 * 
 * @author Maria Eduarda de Azevedo Silva - 119110210
 *
 */
public class ControllerAtividades extends Validacao {

	/**
	 * Mapa de objetos Atividade. Todo objeto Atividade e apontado por uma String
	 * que indica seu identificador unico
	 */
	private Map<String, Atividade> atividades;

	/**
	 * Atributo que guarda a parte inteira do proximo ID a ser cadastrado em um
	 * objeto Atividade
	 */
	private int proximoId;

	/**
	 * Constroi um objeto ControllerAtividades
	 */
	public ControllerAtividades() {

		this.atividades = new HashMap<String, Atividade>();
		this.proximoId = 1;

	}

	/**
	 * Cadastra uma atividade no sistema partindo de uma descricao, um risco e sua
	 * descricao
	 * 
	 * @param descricao      String que indica a descricao de uma atividade
	 * @param risco          String que indica o risco de uma atividade
	 * @param descricaoRisco String que indica a descricao do risco de uma atividade
	 * 
	 * @return String com o ID referente a atividade cadastrada
	 */
	public String cadastrarAtividade(String descricao, String risco, String descricaoRisco) {

		super.validaString(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		super.validaString(risco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		super.validaString(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		List valores = new ArrayList<String>();
		valores.add("ALTO");
		valores.add("MEDIO");
		valores.add("BAIXO");
		super.validaValoresPermitidos(valores, risco, "Valor invalido do nivel do risco.");

		String id = String.format("A%d", this.proximoId);
		this.proximoId++;
		this.atividades.put(id, new Atividade(descricao, risco, descricaoRisco, id));
		return id;

	}

	/**
	 * Acessa o mapa de objetos Atividade pelo ID indicado como parametro e retorna
	 * uma String com a representacao textual da atividade acessada
	 * 
	 * @param id String que identifica unicamente um objeto Atividade
	 * 
	 * @return String com representacao textual de um objeto Atividade
	 */
	public String exibirAtividade(String id) {

		super.validaString(id, "Campo codigo nao pode ser nulo ou vazio.");
		super.hasValor(this.atividades.containsKey(id), "Atividade nao encontrada");

		return this.atividades.get(id).toString();

	}

	/**
	 * Cadastra um item em uma atividade indicada pelo seu id, partindo de uma
	 * descricao do item
	 * 
	 * @param id        String que identifica unicamente um objeto Atividade
	 * @param descricao String que indica a descricao de um item
	 */
	public void cadastrarItem(String id, String descricao) {

		super.validaString(id, "Campo codigo nao pode ser nulo ou vazio.");
		super.hasValor(this.atividades.containsKey(id), "Atividade nao encontrada");

		this.atividades.get(id).cadastrarItem(descricao);

	}

	/**
	 * Acessa um objeto Atividade pelo seu ID e a remove do sistema
	 * 
	 * @param id String que identifica unicamente um objeto Atividade
	 */
	public void apagaAtividade(String id) {

		super.validaString(id, "Campo codigo nao pode ser nulo ou vazio.");
		super.hasValor(this.atividades.containsKey(id), "Atividade nao encontrada");

		this.atividades.remove(id);

	}

	/**
	 * Acessa um objeto Atividade no mapa pelo seu ID e retorna a quantidade de
	 * itens pendentes desse objeto
	 * 
	 * @param id String que representa unicamente um objeto Atividade
	 * @return inteiro com quantidade de itens pendentes da atividade
	 */
	public int contaItensPendentes(String id) {

		super.validaString(id, "Campo codigo nao pode ser nulo ou vazio.");
		super.hasValor(this.atividades.containsKey(id), "Atividade nao encontrada");

		return this.atividades.get(id).contaItensPendentes();

	}

	/**
	 * Acessa um objeto Atividade no mapa pelo seu ID e retorna a quantidade de
	 * itens realizados desse objeto
	 * 
	 * @param id String que representa unicamente um objeto Atividade
	 * @return inteiro com quantidade de itens realizados da atividade
	 */
	public int contaItensRealizados(String id) {

		super.validaString(id, "Campo codigo nao pode ser nulo ou vazio.");
		super.hasValor(this.atividades.containsKey(id), "Atividade nao encontrada");

		return this.atividades.get(id).contaItensRealizados();

	}

    public List<Atividade> getAtividades() {
		return (List<Atividade>) this.atividades.values();
    }
}
