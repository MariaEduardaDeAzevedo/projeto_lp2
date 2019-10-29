package projeto_lp2;

public class Objetivo {
	private String tipo;
	private String descricao;
	private int aderencia;
	private int viabilidade;
	
	public Objetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		Validacao verificador = new Validacao();
		verificador.validaString(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		verificador.validaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		verificador.validaTipo(tipo, "Valor invalido de tipo.");
		verificador.validaValor(aderencia, "Valor invalido de aderencia");
		verificador.validaValor(viabilidade, "Valor invalido de viabilidade.");
		this.tipo = tipo;
		this.descricao = descricao;
		this.aderencia = aderencia;
		this.viabilidade = viabilidade;
	}
	
	@Override
	public String toString() {
		int valor = aderencia + viabilidade;
		return tipo + " - " + descricao + " - " + valor;
	}
}
