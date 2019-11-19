package controller;

import java.io.Serializable;
import java.util.*;

import base.Atividade;
import base.Pesquisa;
import base.Problema;
import comparators.OrdenaAtvdsMaiorDuracao;
import comparators.OrdenaAtvdsMenosPendencias;
import comparators.OrdenaPesquisaID;

/**
 * Entidade controladora de objetos Atividade e Item
 * 
 * @author Maria Eduarda de Azevedo Silva - 119110210
 *
 */
public class ControllerAtividades extends Validacao implements Serializable {

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

		this.atividades = new LinkedHashMap<String, Atividade>();
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

	/**
	 * Retorna uma Collection com as atividades do sistema
	 * 
	 * @return Collection com as atividades do sistema
	 */
	public Collection<Atividade> getAtividades() {
		return this.atividades.values();
	}

	/**
	 * Metodo que permite acessar uma ativiade.
	 * 
	 * @param id da atividade.
	 * @return atividade.
	 */
	public Atividade getAtividade(String id) {
		return this.atividades.get(id);
	}

	/**
	 * Metodo que verifica se existe uma atividade no mapa de atividades.
	 * 
	 * @param id da atividade
	 * @return valor booleando indicando se existe ou não a atividade.
	 */
	public boolean containsAtividade(String id) {
		return atividades.containsKey(id);
	}

	/**
	 * Metodo que permite a execucao de uma atividade, atraves do seu codigo,
	 * determinando o item a ser executado e a duracao da execucao.
	 * 
	 * @param codigoAtividade codigo da atividade.
	 * @param item            item a ser executado.
	 * @param duracao         Duracao da execucao.
	 */
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		super.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		super.verificaNuloNegativo(item, "Item nao pode ser nulo ou negativo.");
		super.verificaNuloNegativo(duracao, "Duracao nao pode ser nula ou negativa.");
		this.atividades.get(codigoAtividade).executaItem(item, duracao);
	}

	/**
	 * Metodo que permite o cadastro de um resultado em uma determinada atividade.
	 * 
	 * @param codigoAtividade codigo da atividade.
	 * @param resultado       Resultado a ser cadastrado.
	 * @return inteiro indicando o índice do resultado, que representa o seu numero.
	 */
	public int cadastraResultado(String codigoAtividade, String resultado) {
		super.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		super.validaString(resultado, "Resultado nao pode ser nulo ou vazio.");
		return this.atividades.get(codigoAtividade).cadastraResultado(resultado);
	}

	/**
	 * Metodo que permite a remocao de um resultado cadastrado, a partir do codigo
	 * da atividade e do numero do resultado.
	 * 
	 * @param codigoAtividade codigo da atividade.
	 * @param numeroResultado Numero do resultado.
	 * @return valor booleano indicando se a remocao foi bem sucedida ou nao.
	 */
	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		super.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		super.hasValor(this.atividades.containsKey(codigoAtividade), "Atividade nao encontrada");
		super.verificaNuloNegativo(numeroResultado, "numeroResultado nao pode ser nulo ou negativo.");
		return this.atividades.get(codigoAtividade).removeResultado(numeroResultado);

	}

	/**
	 * Metodo que lista os resultados cadastrados em uma determinada atividade.
	 * 
	 * @param codigoAtividade Codigo da atividade
	 * @return Resultados listados.
	 */
	public String listaResultados(String codigoAtividade) {
		super.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		super.hasValor(this.atividades.containsKey(codigoAtividade), "Atividade nao encontrada");
		return this.atividades.get(codigoAtividade).listaResultados();

	}

	/**
	 * Metodo que permite ter acesso a duracao de execucao de uma determinada
	 * atividade.
	 * 
	 * @param codigoAtividade Codigo da atividade
	 * @return valor inteiro com a duracao.
	 */
	public int getDuracao(String codigoAtividade) {
		super.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		super.hasValor(this.atividades.containsKey(codigoAtividade), "Atividade nao encontrada");
		return this.atividades.get(codigoAtividade).getDuracao();
	}

	/**
	 * Define a atividade subsequente de outra atividade cadastrada no sistema. A
	 * atividade precedente não pode ter uma subsequente para que a operação
	 * aconteça com sucesso.
	 * 
	 * @param idPrecedente  id da atividade precedente, ou seja, da atividade a qual
	 *                      se quer adicionar uma subsequente.
	 * @param idSubsequente id da atividade que se deseja adicionar como subsequente
	 *                      à outra atividade cadastrada no sistema.
	 */
	public void defineProximaAtividade(String idPrecedente, String idSubsequente) {
		super.validaString(idPrecedente, "Atividade nao pode ser nulo ou vazio.");
		super.validaString(idSubsequente, "Atividade nao pode ser nulo ou vazio.");
		super.hasValor(atividades.containsKey(idPrecedente), "Atividade nao encontrada.");
		super.hasValor(atividades.containsKey(idSubsequente), "Atividade nao encontrada.");
		super.verificaAtvPrcdnt(atividades.get(idPrecedente).hasProx(), "Atividade ja possui uma subsequente.");
		atividades.get(idPrecedente).defProx(atividades.get(idSubsequente));
	}

	/**
	 * Retira a atividade subsequente de uma atividade cadastrada no sistema.
	 * Retirar uma atividade subsequente não significa retirar todas as outras que
	 * sucedem esta atividade subsequente, uma vez que, quando esta é retirada, a
	 * sequência é quebrada em duas.
	 * 
	 * @param idPrecedente id da atividade da qual se quer retirar sua subsequente.
	 */
	public void tiraProximaAtividade(String idPrecedente) {
		super.validaString(idPrecedente, "Atividade nao pode ser nulo ou vazio.");
		super.hasValor(atividades.containsKey(idPrecedente), "Atividade nao encontrada.");
		atividades.get(idPrecedente).retiraProx();
	}

	/**
	 * Retorna um número inteiro que corresponde à quantidade de atividades
	 * subsequentes uma atividade cadastrada no sistema tem.
	 * 
	 * @param idPrecedente id da atividade que se quer contar quantas atividades a
	 *                     sucedem.
	 * @return inteiro equivalente à quantidade de atividades que sucedem uma
	 *         atividade cadastrada no sistema.
	 */
	public int contaProximos(String idPrecedente) {
		super.validaString(idPrecedente, "Atividade nao pode ser nulo ou vazio.");
		super.hasValor(atividades.containsKey(idPrecedente), "Atividade nao encontrada.");
		return atividades.get(idPrecedente).contaProximos();
	}

	/**
	 * Retorna o id de uma enésima atividade que sucede uma atividade cadastrada no
	 * sistema.
	 * 
	 * @param idAtividade      id da atividade que se quer retornar o id de algum de
	 *                         suas outras atividades sucessoras.
	 * @param enesimaAtividade index da atividade que se quer retornar o id.
	 * @return String correspondente ao id da sucessora de número n (representado
	 *         por enesimaAtividade).
	 */
	public String pegaProximo(String idAtividade, int enesimaAtividade) {
		super.validaString(idAtividade, "Atividade nao pode ser nulo ou vazio.");
		super.verificaNuloNegativo(enesimaAtividade, "EnesimaAtividade nao pode ser negativa ou zero.");
		super.hasValor(atividades.containsKey(idAtividade), "Atividade inexistente.");
		return atividades.get(idAtividade).pegaProximo(enesimaAtividade);
	}

	/**
	 * Retorna o id da atividade de maior risco de uma sequência de atividades
	 * cadastradas no sistema.
	 * 
	 * @param idAtividade id da atividade que se quer retornar o id da atividade de
	 *                    maior risco.
	 * @return String correspondente ao id da atividade de maior risco da sequência
	 *         das sucessoras de uma determinada atividade cadastrada no sistema.
	 */
	public String pegaMaiorRiscoAtividades(String idAtividade) {
		super.validaString(idAtividade, "Atividade nao pode ser nulo ou vazio.");
		super.hasValor(atividades.containsKey(idAtividade), "Atividade nao encontrada.");
		super.hasValor(atividades.get(idAtividade).hasProx(), "Nao existe proxima atividade.");
		return atividades.get(idAtividade).pegaMaiorRiscoAtividades();
	}

	public void salvarArquivos() {
		Serializador serializador = new Serializador();
		serializador.salvarArquivos(this.atividades, "Atividades");
		serializador.salvarArquivoInt(this.proximoId, "Proximo ID das Atividades");
	}

	public void carregarArquivos() throws Exception {
		Serializador serializador = new Serializador();
		this.atividades = (LinkedHashMap<String, Atividade>) serializador.carregarArquivos("Atividades");
		this.proximoId = serializador.carregarArquivoInt("Proximo ID das Atividades");
	}
	
	

	

	
}