package projeto_lp2;

public class Facade {

	private ControllerAtividades cAtividade;
	private ControllerPesquisador cPesquisador;
	private ControllerPesquisas cPesquisa;
	
	public Facade() {
		
		this.cAtividade = new ControllerAtividades();
		this.cPesquisa = new ControllerPesquisas();
		this.cPesquisador = new ControllerPesquisador();
		
	}
	
	//US1
	
	//US2
	
	//US3
	
	//US4
	public void cadastraAtividade(String descricao, String risco, String descricaoRisco) {
		
		this.cAtividade.cadastrarAtividade(descricao, risco, descricaoRisco);
		
	}
	
	public void apagaAtividade(String id) {
		
		this.cAtividade.apagaAtividade(id);
		
	}
	
	public void cadastraItem(String id, String descricao) {
		
		this.cAtividade.cadastrarItem(id, descricao);
		
	}
	
	public String exibeAtividade(String id) {
		
		return this.cAtividade.exibirAtividade(id);
		
	}
	
	public int contaItensPendentes(String id) {
		
		return this.cAtividade.contaItensPendentes(id);
		
	}
	
	public int contaItensRealizados(String id) {
		
		return this.cAtividade.contaItensRealizados(id);
		
	}
	
}
