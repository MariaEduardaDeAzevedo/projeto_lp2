package projeto_lp2;

public class Validacao {
	
	protected void validaString(String valor, String mensagem) {
		
		if (valor == null) {
			
			throw new NullPointerException(mensagem);
			
		} else if(valor.trim().equals("")) {
			
			throw new IllegalArgumentException(mensagem);
			
		}
		
	}
	
	protected void validaValor(int valor, String mensagem) {
		if (valor > 5 || valor < 1) {
			throw new IllegalArgumentException(mensagem);
		}
	}
	
	protected void validaTipo(String tipo, String mensagem) {
		if (!tipo.equals("GERAL") || !tipo.equals("ESPECIFICO")) {
			throw new IllegalArgumentException(mensagem);
		}
	}

}
