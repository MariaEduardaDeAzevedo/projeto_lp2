package projeto_lp2;

import java.util.List;

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

}
