package excecoes;

/**
 * Subtipo de RuntimeException que e lancada quando ha um problema de ativacao de uma classe que pode ser ativada/desativada
 */
public class ActivationException extends RuntimeException {
	
	/**
	 * Constroi um objeto ActivationException, partindo do construtor da classe mae, RuntimeException.
	 * @param mensagem String com mensagem de erro a ser lancada junto com a excecao
	 */
	public ActivationException(String mensagem) {
		
		super(mensagem);
		
	}

}
