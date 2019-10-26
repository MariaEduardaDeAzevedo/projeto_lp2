package projeto_lp2;

public class Item {

	private String descricao;
	private boolean status;
	private String codigo;
	private String statusString;

	public Item(String descricao) {
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
