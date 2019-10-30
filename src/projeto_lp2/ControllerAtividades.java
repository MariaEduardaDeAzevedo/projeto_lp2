package projeto_lp2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerAtividades extends Validacao {
	
	private Map<String, Atividade> atividades;
	private int proximoId;
	
	public ControllerAtividades() {
		
		this.atividades = new HashMap<String, Atividade>();
		this.proximoId = 1;
		
	}
	
	public String cadastrarAtividade(String descricao, String risco, String descricaoRisco) {
		
		super.validaString(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		super.validaString(risco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		super.validaString(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		List valores = new ArrayList<String>();
		valores.add("ALTO");
		valores.add("MEDIO");
		valores.add("BAIXO");
		super.validaValoresPermitidos(valores, risco, "Valor invalido do nivel do risco.");
		
		String id = String.format("A%d", this.proximoId);
		this.proximoId ++;
		this.atividades.put(id, new Atividade(descricao, risco, descricaoRisco, id));
		return id;
		
	}
	
	public String exibirAtividade(String id) {
		
		super.validaString(id, "Campo codigo nao pode ser nulo ou vazio.");
		super.hasValor(this.atividades.containsKey(id), "Atividade nao encontrada");
		
		return this.atividades.get(id).toString();
		
	}
	
	public void cadastrarItem(String id, String descricao) {
		
		super.validaString(id, "Campo codigo nao pode ser nulo ou vazio.");
		super.hasValor(this.atividades.containsKey(id), "Atividade nao encontrada");
		
		this.atividades.get(id).cadastrarItem(descricao);
		
	}
	
	public void apagaAtividade(String id) {
		
		super.validaString(id, "Campo codigo nao pode ser nulo ou vazio.");
		super.hasValor(this.atividades.containsKey(id), "Atividade nao encontrada");
		
		this.atividades.remove(id);
		
	}
	
	public int contaItensPendentes(String id) {
		
		super.validaString(id, "Campo codigo nao pode ser nulo ou vazio.");
		super.hasValor(this.atividades.containsKey(id), "Atividade nao encontrada");
		
		return this.atividades.get(id).contaItensPendentes();
		
	}
	
	public int contaItensRealizados(String id) {
		
		super.validaString(id, "Campo codigo nao pode ser nulo ou vazio.");
		super.hasValor(this.atividades.containsKey(id), "Atividade nao encontrada");
		
		return this.atividades.get(id).contaItensRealizados();
		
	}
	
}
