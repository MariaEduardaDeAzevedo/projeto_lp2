package projeto_lp2;

public class Validacao {
	
	protected void validaString(String valor, String mensagem) {
		
		if (valor == null) {
			
			throw new NullPointerException(mensagem);
			
		} else if(valor.trim().equals("")) {
			
			throw new IllegalArgumentException(mensagem);
			
		}
		
	}

}
