package projeto_lp2;

public class Pesquisador extends Validacao {

	private String funcao;
	private String nome;
	private String biografia;
	private String email;
	private String fotoURL;
	private boolean ativada;
	
	public Pesquisador(String nome, String funcao, String biografia, String email, String foto) {
		super.validaString(nome, "Campo NOME nao pode ser nulo ou vazio.");
		super.validaString(funcao, "Campo FUNCAO nao pode ser nulo ou vazio.");
		super.validaString(biografia, "Campo BIOGRAFIA nao pode ser nulo ou vazio.");
		super.validaString(email, "Campo EMAIL nao pode ser nulo ou vazio.");
		super.validaString(fotoURL, "Campo FOTO nao pode ser nulo ou vazio.");
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
		 switch(atributo.trim().toLowerCase()) {
			case "nome":
				super.validaString(novoValor, "Campo NOME nao pode ser nulo ou vazio.");
				this.nome = novoValor;
				break;
			case "funcao":
				super.validaString(novoValor, "Campo FUNCAO nao pode ser nulo ou vazio.");
				this.funcao = novoValor;
				break;
			case "biografia":
				super.validaString(novoValor, "Campo BIOGRAFIA nao pode ser nulo ou vazio.");
				this.biografia = novoValor;
				break;
			case "email":
				super.validaString(novoValor, "Campo EMAIL nao pode ser nulo ou vazio.");
				this.email = novoValor;
				break;
			case "foto":
				super.validaString(novoValor, "Campo FOTO nao pode ser nulo ou vazio.");
				this.fotoURL = novoValor;
				break;
		 }
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
	
	
}
