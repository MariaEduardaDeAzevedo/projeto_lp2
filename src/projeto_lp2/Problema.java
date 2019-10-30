package projeto_lp2;

public class Problema extends Validacao {
	private String descricao;
	private int viabilidade;
	
	public Problema(String descricao, int viabilidade) {
		super.validaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		super.validaValor(viabilidade, "Valor invalido de viabilidade.");
		this.descricao = descricao;
		this.viabilidade = viabilidade;
	}
	
	@Override
	public String toString() {
		return descricao + " - " + viabilidade;
	}
	
}
