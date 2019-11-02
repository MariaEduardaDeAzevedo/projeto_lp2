package excecoes;

public class ActivationException extends RuntimeException {
	
	private String mensagem;
	
	public ActivationException(String mensagem) {
		
		super(mensagem);
		this.mensagem = mensagem;
		
	}

}
