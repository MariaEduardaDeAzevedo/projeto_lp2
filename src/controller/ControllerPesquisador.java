package controller;
import excecoes.FuncaoInvalidaException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import base.Pesquisador;
import base.Aluno;
import base.Professor;

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
	
	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		super.validaString(email, "Campo email nao pode ser nulo ou vazio.");
		super.validaString(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		super.validaString(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		super.validaString(data, "Campo data nao pode ser nulo ou vazio.");
		super.validaData(data, "Atributo data com formato invalido.");
		if (!pesquisadores.containsKey(email)) {
			throw new NullPointerException("Pesquisadora nao encontrada.");
		}
		if (!pesquisadores.get(email).getFuncao().equals("professor")) {
			throw new FuncaoInvalidaException("Pesquisador nao compativel com a especialidade.");
		}
		Pesquisador naoEspecializado = pesquisadores.get(email);
		Professor especializado = new Professor(naoEspecializado.getNome(), naoEspecializado.getFuncao(), naoEspecializado.getBiografia(), naoEspecializado.getEmail(), naoEspecializado.getFoto(), formacao, unidade, data);
		pesquisadores.remove(email);
		pesquisadores.put(email, especializado);
	}
	
	public void cadastraEspecialidadeAluno(String email, int semestre, double iea) {
		super.validaString(email, "Campo email nao pode ser nulo ou vazio.");
		super.validaString(String.valueOf(semestre), "Campo semestre nao pode ser nulo ou vazio.");
		super.validaString(String.valueOf(iea), "Campo IEA nao pode ser nulo ou vazio.");
		super.validaIeaAluno(iea, "Atributo IEA com formato invalido.");
		super.validaSemestreAluno(semestre, "Atributo semestre com formato invalido.");
		if (!pesquisadores.containsKey(email)) {
			throw new NullPointerException("Pesquisadora nao encontrada.");
		}
		if (!pesquisadores.get(email).getFuncao().equals("estudante")) {
			throw new FuncaoInvalidaException("Pesquisador nao compativel com a especialidade.");
		}
		Pesquisador naoEspecializado = pesquisadores.get(email);
		Aluno especializado = new Aluno(naoEspecializado.getNome(), naoEspecializado.getFuncao(), naoEspecializado.getBiografia(), naoEspecializado.getEmail(), naoEspecializado.getFoto(), semestre, iea);
		pesquisadores.remove(email);
		pesquisadores.put(email, especializado);
	}

	/**
	 * Retorna os valores do mapa de pesquisadores.
	 * @return Collection contendo todos os objetos do tipo Pesquisador cadastrados no sistema.
	 */
    public Collection<Pesquisador> getPesquisadores() {
		return pesquisadores.values();
    }
    
    public Pesquisador getPesquisador(String idPesquisador) {
    	super.validaString(idPesquisador, "email do pesquisador não pode ser nulo ou vazio");
    	return pesquisadores.get(idPesquisador);
    }
    
    public String listaPesquisadores(String tipo) {
    	super.validaString(tipo, "Campo tipo nao pode ser nulo ou vazio.");
    	if (!tipo.equals("EXTERNO") && !tipo.equals("ALUNA") && !tipo.equals("PROFESSORA")) {
    		throw new IllegalArgumentException("Tipo " + tipo + " inexistente.");
    	}
    	String tipoReal = "";
    	if (tipo.equals("EXTERNO")) {
    		tipoReal += tipo.toLowerCase();
    	} else if (tipo.equals("ALUNA")) {
    		tipoReal += "estudante";
    	} else if (tipo.equals("PROFESSORA")) {
    		tipoReal += "professor";
    	}
    	String listagem = "";
    	int max = this.qtdTipo(tipoReal);
    	int cont = 0;
    	for (Map.Entry<String, Pesquisador> entry : pesquisadores.entrySet()) {
    		if (cont == max - 1) {
    			if (entry.getValue().getFuncao().equals(tipoReal)) {
    				listagem += entry.getValue().toString();
    			}
    		} else if (cont < max) {
    			if (entry.getValue().getFuncao().equals(tipoReal)) {
    				listagem += entry.getValue().toString() + " | ";
    				cont++;
    			}
    		}
    	}
    	return listagem;
    }
    
    private int qtdTipo(String funcao) {
    	int qtd = 0;
    	for (Map.Entry<String, Pesquisador> entry : pesquisadores.entrySet()) {
    		if (entry.getValue().getFuncao().equals(funcao)) {
    			qtd += 1;
    		}
    	}
    	return qtd;
    }
}
