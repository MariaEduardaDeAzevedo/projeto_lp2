package excecoes;

public class AssociationException extends RuntimeException{
	
	public AssociationException(String mensagem) {
		
		super(mensagem);
		
	}
	
	public AssociationException() {
		
		super("");
		
	}
	
}
