package base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import validacao.Validacao;

/**
 * Classe que representa uma atividade de pesquisa
 * 
 */
public class Atividade extends Validacao implements Serializable {

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
	 * Duracao da execucao de um item de uma atividade.
	 */
	private int duracao;

	/**
	 * Mapa que armazena os resultados cadastrados em uma atividade.
	 */
	private Map<Integer, String> resultados;

	/**
	 * Variavel de controle que fornece o numero do resultado cadastrado.
	 */
	private int numeroResultado;

	/**
	 * Atividade subsequente que deve ser realizada apos esta atividade. Por padrao,
	 * quando uma atividade é contruida, esta nao tem uma atividade subsequente.
	 */
	private Atividade proxAtv;

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
		this.resultados = new HashMap<Integer, String>();
		this.numeroResultado = 0;
		this.proxAtv = null;
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

	/**
	 * Busca se um termo esta contido na descricao.
	 * 
	 * @param termo termo que se deseja procurar.
	 * @return String contendo o id e a descricao da atividade, caso o termo seja
	 *         mencionado, se nao, o objeto null é retornado.
	 */
	public String buscaTermoDescricao(String termo) {
		if (this.descricao.toLowerCase().contains(termo.toLowerCase())) {
			return this.id + ": " + this.descricao;
		}
		return null;
	}

	/**
	 * Busca se um termo esta contido no campo de interesse.
	 * 
	 * @param termo termo que se deseja procurar.
	 * @return String contendo o id e a descricao do risco da atividade, caso o
	 *         termo seja mencionado, se nao, o objeto null é retornado.
	 */
	public String buscaTermoDescricaoDoRisco(String termo) {
		if (this.descricaoRisco.toLowerCase().contains(termo.toLowerCase())) {
			return this.id + ": " + this.descricaoRisco;
		}
		return null;
	}

	/**
	 * Metodo que permite acesso ao Id de uma atividade.
	 * 
	 * @return Id da atividade
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Metodo que permite a execucao de um item.
	 * 
	 * @param item    Item a ser executado
	 * @param duracao Duracao da execucao.
	 */
	public void executaItem(int item, int duracao) {
		if (item > itens.size() || item <= 0) {
			throw new IllegalArgumentException("Item nao encontrado.");
		}
		this.itens.get(item - 1).realizar(duracao);
		this.duracao += duracao;
	}

	/**
	 * Metodo que permite o acesso a duracao da execucao de um item.
	 * 
	 * @return valor inteiro com a duracao.
	 */
	public int getDuracao() {
		return duracao;
	}

	/**
	 * Metodo que cadastra um resultado em uma atividade.
	 * 
	 * @param resultado Resultado a ser cadastrado
	 * @return valor inteiro indicando o numero do resultado cadastrado.
	 */
	public int cadastraResultado(String resultado) {

		this.resultados.put(++numeroResultado, resultado);
		return numeroResultado;
	}

	/**
	 * Metodo que permite remover os resultados cadastrados na atividade.
	 * 
	 * @param numeroDoResultado Numero do resultado.
	 * @return valor booleano indicando se a remocao foi bem sucedida ou nao.
	 */
	public boolean removeResultado(int numeroDoResultado) {
		if (!resultados.containsKey(numeroDoResultado)) {
			throw new IllegalArgumentException("Resultado nao encontrado.");
		}
		this.resultados.remove(numeroDoResultado);
		return true;
	}

	/**
	 * Metodo que gera uma String com a listagem dos resultados cadastrados na
	 * atividade.
	 * 
	 * @return lista de String com os resultados.
	 */
	public String listaResultados() {
		String listaResultados = "";

		for (String resultado : this.resultados.values()) {
			listaResultados += resultado + " | ";
		}
		return listaResultados.substring(0, listaResultados.length() - 3);
	}

	/**
	 * Retorna um valor booleano que indica se uma atividade possui uma atividade
	 * subsequente ou nao.
	 * 
	 * @return true, caso a atividade possua uma atividade que a suceda, ou false,
	 *         caso esta atividade nao possua nenhuma atividade subsequente.
	 */
	public boolean hasProx() {
		if (proxAtv != null) {
			return true;
		}
		return false;
	}

	/**
	 * Define a atividade subsequente desta atividade.
	 * 
	 * @param prox objeto do tipo Atividade que sera a atividade subsequente desta.
	 */
	public void defProx(Atividade prox) {
		super.verificaAtvPrcdnt(isLoop(prox, this), "Criacao de loops negada.");
		proxAtv = prox;
	}

	/**
	 * Metodo que retira a atividade subsequente desta atividade.
	 */
	public void retiraProx() {
		proxAtv = null;
	}

	/**
	 * Retorna a Atividade subsequente desta atividade.
	 * 
	 * @return Atividade que sucede esta.
	 */
	public Atividade getProx() {
		return proxAtv;
	}

	/**
	 * Retorna um valor booleano que indica se a adiçao de uma
	 * atividade como subsequente de outra implica, ou nao, em um loop.
	 * 
	 * @param precedente Atividade precedente, que se quer adicionar uma
	 *                   subsequente.
	 * @param proxAdd    Atividade que se quer adicionar como subsequente.
	 * @return true, caso a adiçao de uma atividade subsequente em outra atividade
	 *         resulte na criaçao de um loop, ou false, caso contrario. =======
	 *         Retorna um valor booleano que indica se a adicao de uma atividade
	 *         como subsequente de outra implica, ou nao, em um loop.
	 */
	private boolean isLoop(Atividade proxAdd, Atividade precedente) {
		if (proxAdd.hasProx()) {
			if (proxAdd.getProx().equals(precedente)) {
				return true;
			}
			return isLoop(proxAdd.getProx(), precedente);
		}
		return false;
	}

	/**
	 * Retorna um inteiro que representa a quantidade de atividades que sucedem esta
	 * atividade.
	 * 
	 * @return numero inteiro que corresponde a quantidade de atividades que sao
	 *         subsequentes a esta atividade.
	 */
	public int contaProximos() {
		if (!hasProx()) {
			return 0;
		}
		return 1 + this.getProx().contaProximos();
	}

	/**
	 * Retorna o id de uma atividade subsequente de indice n, passado como parametro
	 * do metodo. Por exemplo, se uma atividade tem 5 atividades que a sucedem e e
	 * passado como parametro do metodo o inteiro 3, entao, deve ser retornado o id
	 * do terceiro sucessor desta atividade.
	 * 
	 * @param index indice da atividade que sucede esta e que se quer retornar o id.
	 * @return id da n-atividade sucessora desta atividade.
	 */
	public String pegaProximo(int index) {
		super.verificaAtvPrcdnt(!this.hasProx(), "Atividade inexistente.");
		super.verificaNuloNegativo(index, "EnesimaAtividade nao pode ser negativa ou zero.");
		if (index == 1 && this.hasProx()) {
			return proxAtv.getId();
		}
		index--;
		return this.getProx().pegaProximo(index);
	}

	/**
	 * Retorna o risco desta atividade. O risco de uma atividade pode ser "BAIXO",
	 * "MEDIO" ou "ALTO".
	 * 
	 * @return String correspondente ao risco desta atividade.
	 */
	public String getRisco() {
		return risco;
	}

	/**
	 * Converte o risco de uma atividade para um determinado valor inteiro.
	 * 
	 * @return inteiro que representa o risco de uma atividade.
	 */
	public int converteRisco() {
		if (risco.equals("ALTO")) {
			return 2;
		} else if (risco.equals("MEDIO")) {
			return 1;
		}
		return 0;
	}

	/**
	 * Retorna o id da atividade que sucede esta atividade que tem maior risco
	 * dentre suas antecessoras.
	 * 
	 * @param precedente atividade precedente, cuja a qual se quer analisar as suas
	 *                   sucessoras.
	 * @param comparacao primeira atividade da sequência de atividades que se quer
	 *                   analisar.
	 * @return id da atividade de maior risco dentre as sucessoras desta atividade.
	 */
	public String pegaMaiorRiscoAtividades(Atividade precedente, Atividade comparacao) {
		super.hasValor(this.hasProx(), "Nao existe proxima atividade.");
		if (!this.getProx().hasProx()) {
			if (this.equals(comparacao) && this.contaProximos() == 1) {
				return this.getProx().getId();
			}
			if (this.converteRisco() > this.getProx().converteRisco()) {
				if (this.converteRisco() >= precedente.converteRisco()) {
					return this.getId();
				} else {
					return precedente.getId();
				}
			} else {
				if (this.getProx().converteRisco() >= precedente.converteRisco()) {
					return this.getProx().getId();
				} else {
					return precedente.getId();
				}
			}
		}
		if (this.converteRisco() > this.getProx().converteRisco()) {
			return this.getProx().pegaMaiorRiscoAtividades(this, comparacao);
		} else {
			return this.getProx().pegaMaiorRiscoAtividades(this.getProx(), comparacao);
		}
	}

	/**
	 * Gera e retorna uma representacao em String de uma Atividade para ser colocada
	 * em um arquivo de resultado
	 * 
	 * @return String que representa uma atividade para ser escrita em um arquivo de
	 *         resultado
	 */
	public String toStringResultado() {
		String listagem = System.lineSeparator() + String.format("		- %s", this.descricao);

		for (int i = 0; i < this.itens.size(); i++) {
			if (this.itens.get(i).getStatus() == true) {
				listagem += System.lineSeparator() + "			- ITEM" + (i + 1) + " - "
						+ this.itens.get(i).getDuracao();
			}
		}

		for (String s : this.resultados.values()) {
			listagem += System.lineSeparator() + "			- " + s;
		}
		return listagem;
	}

	/**
	 * Gera uma representacao em String de uma Atividade para ser colocada em um
	 * arquivo de resumo
	 * 
	 * @return String que representa uma atividade para ser escrita em um arquivo de
	 *         resumo
	 */
	public String toStringResumo() {
		String listagem = System.lineSeparator()
				+ String.format("		- %s (%s - %s)", this.descricao, this.risco, this.descricaoRisco);

		for (int i = 0; i < this.itens.size(); i++) {
			listagem += System.lineSeparator() + "			- " + this.itens.get(i).getStatusString() + " - ITEM"
					+ (i + 1);
		}
		return listagem;
	}

	/**
	 * Metodo que verifica se existe itens pendentes na atividade.
	 * 
	 * @return valor booleano indicando se existe pendencia ou nao.
	 */
	public boolean hasItemPendente() {
		if (contaItensPendentes() == 0) {
			return false;
		}
		return true;
	}
}