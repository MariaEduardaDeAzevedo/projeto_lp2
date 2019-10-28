package projeto_lp2;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Atividade extends Validacao {
	
	private String descricao;
	private Period duracao;
	private List<Item> resultados;
	private String risco;
	private String descricaoRisco;
	private String id;
	
	public Atividade(String descricao, String risco, String descricaoRisco, String id) {
		
		super.validaString(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		super.validaString(risco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		super.validaString(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		
		this.descricao = descricao;
		this.risco = risco;
		this.descricaoRisco = descricaoRisco;
		this.duracao = Period.ofDays(8);
		this.resultados = new ArrayList<Item>();
		this.id = id;
		
	}
	
	public void cadastrarItem(String descricao) {
		
		this.resultados.add(new Item(descricao));
		
	}
	
	@Override
	public String toString() {
		
		return String.format("%s (%s - %s)", this.descricao, this.risco, this.descricaoRisco);
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Atividade other = (Atividade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public int contaItensPendentes() {
		
		int contador = 0;
		
		for (Item i : this.resultados) {
			
			if (! i.getStatus()) {
				
				contador ++;
				
			}
			
		}
		
		return contador;
		
	}
	
	public int contaItensRealizados() {
		
		int contador = 0;
		
		for (Item i : this.resultados) {
			
			if (i.getStatus()) {
				
				contador ++;
				
			}
			
		}
		
		return contador;
		
	}
	
}
