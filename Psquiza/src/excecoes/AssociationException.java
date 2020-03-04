package excecoes;

/**
 * Subtipo de RuntimeException que e lancada quando ha um problema de associacao
 * em uma classe, isto e, quando nao se aceita associar um objeto no lugar de um
 * ja associado
 */
public class AssociationException extends RuntimeException {

	/**
	 * Constroi um objeto AssociationException, partindo do construtor da classe mae, RuntimeException.
	 * @param mensagem String com mensagem de erro a ser lancada junto com a excecao
	 */
	public AssociationException(String mensagem) {

		super(mensagem);

	}
	
	/**
	 * Constroi um objeto ActivationException, partindo do construtor da classe mae, RuntimeException.
	 */
	public AssociationException() {

		super("");

	}

}
