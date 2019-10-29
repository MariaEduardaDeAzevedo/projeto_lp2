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
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		this.cPesquisador.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
	}
	
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		this.cPesquisador.alteraPesquisador(email, atributo, novoValor);
	}
	
	public void desativaPesquisador(String email) {
		this.cPesquisador.desativaPesquisador(email);
	}
	
	public void ativaPesquisador(String email) {
		this.cPesquisador.ativaPesquisador(email);
	}
	
	public void exibePesquisador(String email) {
		this.cPesquisador.exibePesquisador(email);
	}
	
	public void pesquisadorEhAtivo(String email) {
		this.cPesquisador.pesquisadorEhAtivo(email);
	}
	
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
