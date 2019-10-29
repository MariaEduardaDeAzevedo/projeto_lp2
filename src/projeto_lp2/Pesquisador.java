package projeto_lp2;


public class Pesquisador extends Validacao {

	private String funcao;
	private String nome;
	private String biografia;
	private String email;
	private String fotoURL;
	private boolean ativada;
	
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
	
	 public void ativaPesquisador() {
		 if (ativada == true) {
			 throw new IllegalArgumentException("Pesquisador ja ativado.");
		 }
	     this.ativada = true;
	 }

	 public void inativaPesquisador() {
		 if (ativada == false) {
			 throw new IllegalArgumentException("Pesquisador inativo.");
	     }
	     this.ativada = false;
	 }
	 
	public void alteraPesquisador(String atributo, String novoValor) {
		super.validaString(atributo, "Atributo nao pode ser vazio ou nulo.");
		//atributo.trim().toLowerCase();
		if(atributo.equals("nome")) {
			super.validaString(novoValor, "Campo nome nao pode ser nulo ou vazio.");
			this.nome = novoValor;
		} else if(atributo.equals("funcao")) {
			super.validaString(novoValor, "Campo funcao nao pode ser nulo ou vazio.");
			this.funcao = novoValor;
		} else if(atributo.equals("biografia")) {
			super.validaString(novoValor, "Campo biografia nao pode ser nulo ou vazio.");
			this.biografia = novoValor;
		} else if(atributo.equals("email")) {
			super.validaString(novoValor, "Campo email nao pode ser nulo ou vazio.");
			super.verificaEmail(novoValor);
			this.email = novoValor;
		} else if(atributo.equals("foto")) {
			super.validaString(novoValor, "Campo fotoURL nao pode ser nulo ou vazio.");
			super.verificaURL(novoValor);
			this.fotoURL = novoValor;
		} else {
			throw new IllegalArgumentException("Atributo invalido.");
			
		}
		
	}

	
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFotoURL(String fotoURL) {
		this.fotoURL = fotoURL;
	}

	@Override
	public String toString() {
		return String.format("%s (%s) - %s - %s - %s", this.nome, this.funcao, this.biografia, this.email, this.fotoURL);
		//return this.nome + "(" + this.funcao + ") - " + this.biografia + " - " + this.fotoURL;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}
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
	
}
