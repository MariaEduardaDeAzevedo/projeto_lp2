package projeto_lp2;

public class Problema {
	private String descricao;
	private int viabilidade;
	
	public Problema(String descricao, int viabilidade) {
		Validacao verificador = new Validacao();
		verificador.validaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		verificador.validaValor(viabilidade, "Valor invalido de viabilidade.");
		this.descricao = descricao;
		this.viabilidade = viabilidade;
	}
	
	@Override
	public String toString() {
		return descricao + " - " + viabilidade;
	}
	
}
