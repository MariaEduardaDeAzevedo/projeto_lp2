package projeto_lp2;

import java.util.HashMap;
import java.util.Map;

public class ControllerPesquisador extends Validacao {

	private Map<String, Pesquisador> pesquisadores;

	public ControllerPesquisador() {
		this.pesquisadores = new HashMap<String, Pesquisador>();
	}
	
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		super.validaString(nome, "Campo nome nao pode ser nulo ou vazio.");
		super.validaString(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		super.validaString(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		super.validaString(email, "Campo email nao pode ser nulo ou vazio.");
		super.validaString(fotoURL, "Campo fotoURL nao pode ser nulo ou vazio.");
		super.verificaEmail(email);
		super.verificaURL(fotoURL);
		this.pesquisadores.put(email, new Pesquisador(nome, funcao, biografia, email, fotoURL));
	}
	
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		super.hasValor(this.pesquisadores.containsKey(email), "Pesquisador nao encontrado");
		this.pesquisadores.get(email).alteraPesquisador(atributo, novoValor);
		if(atributo.equals("EMAIL")) {
			this.pesquisadores.put(novoValor, this.pesquisadores.get(email));
			this.pesquisadores.remove(email);
		} 	
	}		
			 
	public void desativaPesquisador(String email) {
		super.hasValor(this.pesquisadores.containsKey(email), "Pesquisador nao encontrado");
		this.pesquisadores.get(email).inativaPesquisador();
	}
	
	public void ativaPesquisador(String email) {
		super.hasValor(this.pesquisadores.containsKey(email), "Pesquisador nao encontrado");
		this.pesquisadores.get(email).ativaPesquisador();
	}
	
	public String exibePesquisador(String email) {
		super.validaString(email, "Campo email nao pode ser nulo ou vazio.");
		super.hasValor(this.pesquisadores.containsKey(email), "Pesquisador nao encontrado");
		return this.pesquisadores.get(email).toString(); 
	}
	
	public boolean pesquisadorEhAtivo(String email) {
		super.validaString(email, "Email nao pode ser vazio ou nulo.");
		super.hasValor(this.pesquisadores.containsKey(email), "Pesquisador nao encontrado");
		return this.pesquisadores.get(email).pesquisadorEhAtivo();
	}
	
}
