package validacao;

import java.time.Period;
import java.util.List;
import java.util.Map;

import base.Pesquisa;
import base.Problema;
import excecoes.ActivationException;
import excecoes.AssociationException;
import excecoes.SequenceException;

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
	 * 
	 * @param email E-mail do pesquisador
	 */
	protected void verificaEmail(String email) {
		if (email.startsWith("@") || email.endsWith("@") || email.indexOf("@") == -1) {
			throw new IllegalArgumentException("Formato de email invalido.");
		}
	}

	/**
	 * Metodo que verifica a validacao da URL da foto do pesquisador.
	 * 
	 * @param url String que indica a url a ser avaliada
	 */
	protected void verificaURL(String url) {
		if (url.length() < 8 || (!url.substring(0, 7).equals("http://") && !url.substring(0, 8).equals("https://"))) {
			throw new IllegalArgumentException("Formato de foto invalido.");
		}
	}

	/**
	 * Valida um valor de aderencia utilizado no sistema Psquiza
	 * @param valor valor que se quer validar
	 * @param mensagem mensagem de erro exibida no lancamento de excecoes
	 */
	protected void validaValor(int valor, String mensagem) {
		if (valor > 5 || valor < 1) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	/**
	 * Valida o tipo de um objetivo cadastrado no sistema Psquiza.
	 * @param tipo tipo do objetivo que se quer validar.
	 * @param mensagem mensagem de erro exibida se a validacao lancar uma excecao.
	 */
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

	/**
	 * Avalia se o status de uma classe que pode ser ativada/desativada esta
	 * desativada e lanca uma ActivationException nesse caso
	 * 
	 * @param status boolean com o status a ser avaliado
	 * @param mensagem String com mensagem de erro
	 */
	public void validaStatus(boolean status, String mensagem) {
		if (!status) {
			throw new ActivationException(mensagem);
		}
	}

	/**
	 * Valida o formato de uma data.
	 * @param data data que se deseja validar o formato.
	 * @param mensagem mensagem de erro exibida nas excecoes lancadas por este metodo.
	 */
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

	/**
	 * Valida o valor do semestre de um aluno cadastrado no sistema Psquiza.
	 * @param semestre inteiro que corresponde ao semestre de um aluno cadastrado no sistema.
	 * @param mensagem mensagem de erro exibida nas excecoes lancadas por este metodo.
	 */
	protected void validaSemestreAluno(int semestre, String mensagem) {
		if (semestre <= 0) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	/**
	 * Valida o valor do indice de eficiencia academico (iea) de um aluno.
	 * @param iea valor do iea.
	 * @param mensagem mensagem de erro exibida nas excecoes lancadas por este metodo.
	 */
	protected void validaIeaAluno(double iea, String mensagem) {
		if (iea > 10 || iea < 0) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	/**
	 * Metodo que avalia uma associação entre duas classes. As situacoes avaliadas
	 * são:
	 * 
	 * - 1. Caso o id1 (chave) exista no mapa e a operacao seja de associacao (true)
	 * e o valor apontado por id1 no mapa seja igual a id2, lanca-se um
	 * IllegalArgumentException() que sinaliza a situacao de que a associacao ja foi
	 * feita.
	 * 
	 * - 2. Se o mapa tem o valor de id2 e a operação e de associacao (true),
	 * lança-se uma AssociationException() que indica que o sistema nao permite que
	 * mais de um objeto seja associado a outro.
	 * 
	 * - 3. Se o mapa nao contem id1 (chave) e a operacao e de desassociacao
	 * (false), lanca-se um NullPointerException()
	 * 
	 * 
	 * @param id1      chave do mapa
	 * @param id2      valor do mapa
	 * @param mapa     mapa que guarda as associacoes
	 * @param operacao true para associacao e false para desassociacao
	 * @param mensagem Mensagem de erro a ser exibida no caso 2. Caso nao seja
	 *                 necessaria, coloque uma String vazia.
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

	/**
	 * Valida o numero de um determinado resultado.
	 * @param numero numero do resultado que se deseja validar.
	 * @param mensagem mensagme de erro exibida quando excecoes sao lancadas por este metodo.
	 */
	protected void validaNumeroResultado(int numero, String mensagem) {
		if (numero < 0) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	/**
	 * Metodo de validacao que verifica se um valor inteiro é menor ou igual a zero,
	 * lancando uma excecao caso a condicao retorne true.
	 * 
	 * @param numero   Numero a ser verificado
	 * @param mensagem Mensagem de excecao.
	 */
	protected void verificaNuloNegativo(int numero, String mensagem) {
		if (numero <= 0) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	/**
	 * Valida se a determinada atividade do sistema tem uma atividade que a sucede.
	 * @param valor valor booleano que indica se a atividade tem ou nao outra atividade subsequente
	 * @param mensagem mensagem de erro exibida pelas excecoes lancadas por este metodo
	 */
	protected void verificaAtvPrcdnt(boolean valor, String mensagem) {
		if (valor) {
			throw new SequenceException(mensagem);
		}
	}

}
