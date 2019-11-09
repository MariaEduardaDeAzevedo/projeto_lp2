package base;

import controller.Validacao;

/**
 * Representacao de um pesquisador, que contem funcao, nome, biografia, email e url da foto.
 *
 */
public class Pesquisador extends Validacao {

	/**
	 * Representacao da funcao que o pesquisador exerce.
	 */
	private String funcao;
	/**
	 * Representacao do nome do pesquisador.
	 */
	private String nome;
	/**
	 * Representacao da biografia que descreve o pesquisador.
	 */
	private String biografia;
	/**
	 * Representacao do e-mail do pesquisador.
	 */
	private String email;
	/**
	 * Representacao da url que fornece a foto do pesquisador.
	 */
	private String fotoURL;
	/**
	 * Representacao em booleano do status do pesquisador.
	 */
	private boolean ativada;
	
	/**
	 * Constroi a representacao de um pesquisador, a partir do seu nome, funcao, biografia, email e foto.
	 * @param nome Nome do pesquisador
	 * @param funcao Funcao do pesquisador
	 * @param biografia Biografia do pesquisador
	 * @param email E-mail do pesquisador
	 * @param foto Foto do pesquisador
	 */
	public Pesquisador(String nome, String funcao, String biografia, String email, String foto) {
		super.validaString(nome, "Campo nome nao pode ser nulo ou vazio.");
		super.validaString(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		super.validaString(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		super.validaString(email, "Campo email nao pode ser nulo ou vazio.");
		super.validaString(foto, "Campo fotoURL nao pode ser nulo ou vazio.");
		super.verificaEmail(email);
		super.verificaURL(foto);
		this.funcao = funcao;
		this.nome = nome;
		this.biografia = biografia;
		this.email = email;
		this.fotoURL = foto;
		this.ativada = true;
	}
	
	/**
	 * Metodo que verifica o status do pesquisador e torna o mesmo ativo.
	 */
	 public void ativaPesquisador() {
		 if (ativada == true) {
			 throw new IllegalArgumentException("Pesquisador ja ativado.");
		 }
	     this.ativada = true;
	 }

	 /**
	 * Metodo que verifica o status do pesquisador e torna o mesmo inativo.
	 */
	 public void inativaPesquisador() {
		 if (ativada == false) {
			 throw new IllegalArgumentException("Pesquisador inativo.");
	     }
	     this.ativada = false;
	 }
	 
	 /**
	  * Metodo que permite a alteracao dos dados do pesquisador. 
	  * @param atributo Atributo a ser alterado
	  * @param novoValor Novo valor que substitui o antigo
	  */
	 public void alteraPesquisador(String atributo, String novoValor) {
		 super.validaString(atributo, "Atributo nao pode ser vazio ou nulo.");
		 switch(atributo.trim()) {
			case "NOME":
				super.validaString(novoValor, "Campo nome nao pode ser nulo ou vazio.");
				this.nome = novoValor;
				break;
			case "FUNCAO":
				super.validaString(novoValor, "Campo funcao nao pode ser nulo ou vazio.");
				this.funcao = novoValor;
				break;
			case "BIOGRAFIA":
				super.validaString(novoValor, "Campo biografia nao pode ser nulo ou vazio.");
				this.biografia = novoValor;
				break;
			case "EMAIL":
				super.validaString(novoValor, "Campo email nao pode ser nulo ou vazio.");
				super.verificaEmail(novoValor);
				this.email = novoValor;
				break;
			case "FOTO":
				super.validaString(novoValor, "Campo fotoURL nao pode ser nulo ou vazio.");
				super.verificaURL(novoValor);
				this.fotoURL = novoValor;
				break;
			default:
				throw new IllegalArgumentException("Atributo invalido.");
		 }
	 }

	 /**
	  * Representacao em String dos dados do pesquisador.
	  */
	@Override
	public String toString() {
		return String.format("%s (%s) - %s - %s - %s", this.nome, this.funcao, this.biografia, this.email, this.fotoURL);
	}
	

	/**
	 * Metodo que compara de um pesquisador é igual ao outro atraves do email.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}
	
	/**
	 * Metodo que compara de um pesquisador é igual ao outro atraves do email, 
	 * retornando um booleano.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pesquisador other = (Pesquisador) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
	public boolean pesquisadorEhAtivo() {
		return ativada;
	}
	
	/**
	 * Retorna o valor do nome do pesquisador.
	 * @return String que corresponde ao nome do pesquisador.
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Retorna o valor da função do pesquisador.
	 * @return String que corresponde à função do pesquisador.
	 */
	public String getFuncao() {
		return funcao;
	}
	
	/**
	 * Retorna a biografia do pesquisador.
	 * @return String que corresponde à biografia do pesquisador.
	 */
	public String getBiografia() {
		return biografia;
	}
	
	/**
	 * Retorna o email do pesquisador.
	 * @return String que corresponde ao email do pesquisador.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Retorna a URL da foto do pesquisador.
	 * @return String que corresponde à URL da foto do pesquisador.
	 */
	public String getFoto() {
		return fotoURL;
	}

    public String buscaTermo(String termo) {
		if (biografia.toLowerCase().contains(termo.toLowerCase())){
			return this.email + ": " + this.biografia;
		}
		return null;
    }
    
    /**
     * Altera o valor do nome do pesquisador.
     * @param novoValor String correspondente ao novo valor do nome do pesquisador, que substituirá o antigo valor do
     * nome deste.
     */
    public void setNome(String novoValor) {
    	super.validaString(novoValor, "novoValor nao pode ser nulo ou vazio.");
    	nome = novoValor;
    }
    
    /**
     * Altera o valor da função do pesquisador.
     * @param novoValor String correspondente ao novo valor da função do pesquisador, que substituirá o antigo
     * valor da função deste.
     */
    public void setFuncao(String novoValor) {
    	super.validaString(novoValor, "novoValor nao pode ser nulo ou vazio.");
    	funcao = novoValor;
    }
    
    /**
     * Altera o valor da biografia do pesquisador.
     * @param novoValor String que corresponde ao novo valor da biografia do pesquisador, que substituirá o antigo
     * valor da biografia deste.
     */
    public void setBiografia(String novoValor) {
    	super.validaString(novoValor, "novoValor nao pode ser nulo ou vazio.");
    	biografia = novoValor;
    }
    
    /**
     * Altera o valor do email do pesquisador.
     * @param novoValor String que corresponde ao novo valor do email do pesquisador, que substituirá o antigo
     * valor do email deste.
     */
    public void setEmail(String novoValor) {
    	super.validaString(novoValor, "novoValor nao pode ser nulo ou vazio.");
    	super.verificaEmail(novoValor);
    	email = novoValor;
    }
    
    /**
     * Altera o valor da URL da foto do pesquisador.
     * @param novoValor String que corresponde ao novo valor da URL da foto do pesquisador, que substituirá o antigo
     * valor desta.
     */
    public void setFoto(String novoValor) {
    	super.validaString(novoValor, "novoValor nao pode ser nulo ou vazio.");
    	super.verificaURL(novoValor);
    	fotoURL = novoValor;
    }
}




