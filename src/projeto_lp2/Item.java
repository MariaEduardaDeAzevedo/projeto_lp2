package projeto_lp2;

public class Item extends Validacao{

	private String descricao;
	private boolean status;
	private String codigo;
	private String statusString;

	public Item(String descricao) {
		
		super.validaString(descricao, "Item nao pode ser nulo ou vazio.");
		
		this.descricao = descricao;
		this.status = false;
		this.statusString = "PENDENTE";
	}
	
	private void realizar() {
		
		this.statusString = "REALIZADO";
		this.status = true;
	}

	public boolean getStatus() {
		
		return this.status;
	
	}
	
}
