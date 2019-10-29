package projeto_lp2;

import java.util.HashMap;
import java.util.Map;

public class ControllerProblemas {
	private Map<String, Problema> problemas;
	private int idNumber;
	
	public ControllerProblemas() {
		this.problemas = new HashMap<String, Problema>();
		this.idNumber = 1;
	}
	
	public void cadastraProblema(String descricao, int viabilidade) {
		Validacao verificador = new Validacao();
		verificador.validaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		verificador.validaValor(viabilidade, "Valor invalido de viabilidade.");
		String idProblema = "P" + idNumber;
		idNumber++;
		Problema novoProblema = new Problema(descricao, viabilidade);
		problemas.put(idProblema, novoProblema);
	}
	
	public String exibeProblema(String idProblema) throws Exception {
		Validacao verificador = new Validacao();
		verificador.validaString(idProblema, "id do problema não pode ser vazio ou nulo");
		if (!problemas.containsKey(idProblema)) {
			throw new Exception("Problema nao encontrado");
		}
		return idProblema + " - " + problemas.get(idProblema).toString();
	}
	
	public void apagarProblema(String idProblema) throws Exception {
		Validacao verificador = new Validacao();
		verificador.validaString(idProblema, "Campo codigo nao pode ser nulo ou vazio.");
		if (!problemas.containsKey(idProblema)) {
			throw new Exception("Problema nao encontrado");
		}
		problemas.remove(idProblema);
	}
}
