package projeto_lp2;

import java.util.HashMap;
import java.util.Map;

public class ControllerPesquisador extends Validacao {

	private Map<String, Pesquisador> pesquisadores;

	public ControllerPesquisador() {
		this.pesquisadores = new HashMap<String, Pesquisador>();
	}
	
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		//falta verificar email e url
		this.pesquisadores.put(email, new Pesquisador(nome, funcao, biografia, email, fotoURL));
	}
	
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		if(!pesquisadores.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado.");
		}
		this.pesquisadores.get(email).alteraPesquisador(atributo, novoValor);
	}
	
	public void desativaPesquisador(String email) {
		if(!pesquisadores.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado.");
		}
		this.pesquisadores.get(email).inativaPesquisador();
	}
	
	public void ativaPesquisador(String email) {
		if(!pesquisadores.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado.");
		}
		this.pesquisadores.get(email).ativaPesquisador();
	}
	
	public String exibePesquisador(String email) {
		if(!pesquisadores.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado.");
		}
		return this.pesquisadores.get(email).toString();
	}
	
}
