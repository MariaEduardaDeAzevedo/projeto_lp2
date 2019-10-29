package projeto_lp2;
import java.util.HashMap;
import java.util.Map;

public class ControllerObjetivos {
	private Map<String, Objetivo> objetivos;
	private int idNumber;
	
	public ControllerObjetivos() {
		this.objetivos = new HashMap<String, Objetivo>();
		this.idNumber = 1;
	}
	
	public void cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		Validacao verificador = new Validacao();
		verificador.validaString(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		verificador.validaTipo(tipo, "Valor invalido de tipo.");
		verificador.validaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		verificador.validaValor(aderencia, "Valor invalido de aderencia");
		verificador.validaValor(viabilidade, "Valor invalido de viabilidade.");
		String idObjetivo = "O" + idNumber;
		idNumber++;
		objetivos.put(idObjetivo, new Objetivo(tipo, descricao, aderencia, viabilidade));
	}
	
	public String exibeObjetivo(String idObjetivo) throws Exception {
		Validacao verificador = new Validacao();
		verificador.validaString(idObjetivo, "Codigo do objetivo passado não pode ser vazio ou nulo");
		if (!objetivos.containsKey(idObjetivo)) {
			throw new Exception("Objetivo nao encontrado");
		}
		return idObjetivo + " - " + objetivos.get(idObjetivo).toString();
	}
	
	public void apagarObjetivo(String idObjetivo) throws Exception {
		Validacao verificador = new Validacao();
		verificador.validaString(idObjetivo, "Campo codigo nao pode ser nulo ou vazio.");
		if (!objetivos.containsKey(idObjetivo)) {
			throw new Exception("Objetivo nao encontrado");
		}
		objetivos.remove(idObjetivo);
	}
	
}
