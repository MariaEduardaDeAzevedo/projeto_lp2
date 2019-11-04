package controller;

import java.util.HashMap;
import java.util.Map;

import base.Pesquisador;

/**
 * Representacao do Controller responsavel pelos metodos referentes ao pesquisador. 
 *
 */
public class ControllerPesquisador extends Validacao {

	/**
	 * Representacao do mapa responsavel por armazenar os pesquisadores.
	 */
	private Map<String, Pesquisador> pesquisadores;

	/**
	 * Constroi o objeto ControllerPesquisador.
	 */
	public ControllerPesquisador() {
		this.pesquisadores = new HashMap<String, Pesquisador>();
	}
	
	/**
	 * Metodo responsavel por cadastrar um pesquisador no sistema a partir dos seus parametros.
	 * @param nome Nome do pesquisador
	 * @param funcao Funcao do pesquisador
	 * @param biografia Biografia do pesquisador
	 * @param email E-mail do pesquisador 
	 * @param fotoURL URL que representa a foto do pesquisador
	 */
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
	
	/**
	  * Metodo que permite a alteracao dos dados do pesquisador.
	  * @param email E-mail do pesquisador 
	  * @param atributo Atributo a ser alterado
	  * @param novoValor Novo valor que substitui o antigo
	  * 
	  */
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		super.hasValor(this.pesquisadores.containsKey(email), "Pesquisador nao encontrado");
		this.pesquisadores.get(email).alteraPesquisador(atributo, novoValor);
		if(atributo.equals("EMAIL")) {
			this.pesquisadores.put(novoValor, this.pesquisadores.get(email));
			this.pesquisadores.remove(email);
		} 	
	}		
	
	/**
	 * Metodo que permite a desativacao do pesquisador.
	 * @param email E-mail do pesquisador
	 */
	public void desativaPesquisador(String email) {
		super.hasValor(this.pesquisadores.containsKey(email), "Pesquisador nao encontrado");
		this.pesquisadores.get(email).inativaPesquisador();
	}
	
	/**
	 * Metodo que permite a ativacao do pesquisador.
	 * @param email E-mail do pesquisador
	 */
	public void ativaPesquisador(String email) {
		super.hasValor(this.pesquisadores.containsKey(email), "Pesquisador nao encontrado");
		this.pesquisadores.get(email).ativaPesquisador();
	}
	
	/**
	 * Metodo que permite a exibicao de um pesquisador.
	 * @param email E-mail do pesquisador
	 * @return representacao em String de um pesquisador
	 */
	public String exibePesquisador(String email) {
		super.validaString(email, "Campo email nao pode ser nulo ou vazio.");
		super.hasValor(this.pesquisadores.containsKey(email), "Pesquisador nao encontrado");
		return this.pesquisadores.get(email).toString(); 
	}
	
	/**
	 * Metodo que retorna o estado do status do pesquisador.
	 * @param email E-mail do pesquisador
	 * @return valor booleano indicando o status do pesquisador.
	 */
	public boolean pesquisadorEhAtivo(String email) {
		super.validaString(email, "Email nao pode ser vazio ou nulo.");
		super.hasValor(this.pesquisadores.containsKey(email), "Pesquisador nao encontrado");
		return this.pesquisadores.get(email).pesquisadorEhAtivo();
	}
	
}