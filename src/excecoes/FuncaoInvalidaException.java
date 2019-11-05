package excecoes;

public class FuncaoInvalidaException extends RuntimeException {
	public FuncaoInvalidaException(String mensagem) {
		super(mensagem);
	}
}
