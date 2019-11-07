package controller;

import java.time.Period;
import java.util.List;
import java.util.Map;

import base.Pesquisa;
import base.Problema;
import excecoes.ActivationException;
import excecoes.AssociationException;

public class Validacao {

	/**
	 * Avalia se um valor em String e nulo ou vazio e lanca uma excecao referente a
	 * tais situacoes
	 * 
	 * @param valor    String que se deseja avaliar
	 * @param mensagem String com mensagem que deve ser lancada junto a excecao
	 */
	protected void validaString(String valor, String mensagem) {

		if (valor == null) {

			throw new NullPointerException(mensagem);

		} else if (valor.trim().equals("")) {

			throw new IllegalArgumentException(mensagem);

		}

	}

	/**
	 * Metodo que verifica a validacao do e-mail.
	 * @param email E-mail do pesquisador
	 */
	protected void verificaEmail(String email) {
		if (email.startsWith("@") || email.endsWith("@") || email.indexOf("@") == -1) {
			throw new IllegalArgumentException("Formato de email invalido.");
		}
	}

	/**
	 * Metodo que verifica a validacao da URL da foto do pesquisador.
	 * @param url
	 */
	protected void verificaURL(String url) {
		if (url.length() < 8 || (!url.substring(0, 7).equals("http://") && !url.substring(0, 8).equals("https://"))) {
			throw new IllegalArgumentException("Formato de foto invalido.");
		}
	}

	protected void validaValor(int valor, String mensagem) {
		if (valor > 5 || valor < 1) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	protected void validaTipo(String tipo, String mensagem) {
		if (!tipo.equals("GERAL") && !tipo.equals("ESPECIFICO")) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	/**
	 * Recebe uma lista de valores que sao permitidos pelo sistema e lanca uma
	 * excecao caso o valor inserido nao seja permitido
	 * 
	 * @param valores  Lista com os valores permitidos pelo sistema
	 * @param valor    String com o valor que deseja-se avaliar
	 * @param mensagem Mensagem de erro a ser lancada com a excecao
	 */
	protected void validaValoresPermitidos(List valores, String valor, String mensagem) {

		if (!valores.contains(valor)) {

			throw new IllegalArgumentException(mensagem);

		}

	}

	/**
	 * Avalia se existe o objeto acessado. Utilize os metodos de colecoes contains,
	 * containsKey para poder gerar um boolean referente a existencia do objeto
	 * 
	 * @param bool     Boolean com valor referente a existencia de um objeto no
	 *                 sistema
	 * @param mensagem Mensagem que sera retornada com a excecao
	 */
	protected void hasValor(boolean bool, String mensagem) {

		if (!bool) {

			throw new NullPointerException(mensagem);

		}

	}

	public void validaStatus(boolean status, String mensagem) {
		
		if (!status) {
			
			throw new ActivationException(mensagem);
			
		}
		
	}
	
	protected void validaData(String data, String mensagem) {
		if (data.length() != 10) {
			throw new IllegalArgumentException("Atributo data com formato invalido.");
		}
		String[] dataSplit = data.split("/");
		int comparacaoDia = dataSplit[0].compareTo("31");
		int comparacaoMes = dataSplit[1].compareTo("12");
		int comparacaoAno = dataSplit[2].compareTo("2019");
		if (comparacaoDia > 0 || comparacaoMes > 0 || comparacaoAno > 0) {
			throw new IllegalArgumentException("Atributo data com formato invalido.");
		}
	}
	
	protected void validaSemestreAluno(int semestre, String mensagem) {
		if (semestre <= 0) {
			throw new IllegalArgumentException(mensagem);
		}
	}
	
	protected void validaIeaAluno(double iea, String mensagem) {
		if (iea > 10 || iea < 0) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	/**
	 * Método que avalia uma associação entre duas classes. As situações avaliadas
	 * são:
	 * 
	 * - 1. Caso o id1 (chave) exista no mapa e a operacão seja de associação (true)
	 * e o valor apontado por id1 no mapa seja igual a id2, lança-se um
	 * IllegalArgumentException() que sinaliza a situação de que a associação já foi
	 * feita.
	 * 
	 * - 2. Se o mapa tem o valor de id2 e a operação é de associação (true),
	 * lança-se uma AssociationException() que indica que o sistema nao permite que
	 * mais de um objeto seja associado a outro.
	 * 
	 * - 3. Se o mapa nao contém id1 (chave) e a operação é de desassociação
	 * (false), lança-se um NullPointerException()
	 * 
	 * 
	 * @param id1      chave do mapa
	 * @param id2      valor do mapa
	 * @param mapa     mapa que guarda as associações
	 * @param operacao true para associação e false para desassociação
	 * @param mensagem Mensagem de erro a ser exibida no caso 2. Caso não seja
	 *                 necessária, coloque uma String vazia.
	 */
	protected void hasAssociado(String id1, String id2, Map<String, String> mapa, boolean operacao, String mensagem) {

		if (mapa.containsKey(id1) && operacao && mapa.get(id1).equals(id2)) {

			throw new IllegalArgumentException();

		}

		if (mapa.containsValue(id2) && operacao) {

			throw new AssociationException(mensagem);

		}

		if (!(mapa.containsKey(id1)) && !(operacao)) {

			throw new NullPointerException();

		}

	}
	
	protected void validaNumeroResultado(int numero, String mensagem){
		if (numero < 0) {
			throw new IllegalArgumentException(mensagem);
		}
	}
	
	protected void verificaNuloNegativo(int item, String mensagem) {
		if(item <= 0) {
			throw new IllegalArgumentException(mensagem);
		}
	}

}
