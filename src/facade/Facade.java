package facade;

import controller.*;

public class Facade {

    private ControllerAtividades cAtividade;
    private ControllerPesquisador cPesquisador;
    private ControllerPesquisas cPesquisa;
    private ControllerObjetivos cObjetivo;
    private ControllerProblemas cProblema;
    private Conector cGeral;
    private ControllerBuscas cBuscas;

    public Facade() {

        this.cAtividade = new ControllerAtividades();
        this.cPesquisa = new ControllerPesquisas();
        this.cPesquisador = new ControllerPesquisador();
        this.cObjetivo = new ControllerObjetivos();
        this.cProblema = new ControllerProblemas();
        this.cGeral = new Conector();
        this.cBuscas = new ControllerBuscas(cGeral);

    }

    //US1
    public String cadastraPesquisa(String descricao, String campoDeInteresse) {

        return this.cPesquisa.cadastraPesquisa(descricao, campoDeInteresse);
    }

    public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {

        this.cPesquisa.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
    }

    public void encerraPesquisa(String codigo, String motivo) {

        this.cPesquisa.encerraPesquisa(codigo, motivo);
    }

    public void ativaPesquisa(String codigo) {

        this.cPesquisa.ativaPesquisa(codigo);
    }

    public String exibePesquisa(String codigo) {

        return this.cPesquisa.exibePesquisa(codigo);
    }

    public boolean pesquisaEhAtiva(String codigo) {

        return this.cPesquisa.pesquisaEhAtiva(codigo);
    }

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

    public String exibePesquisador(String email) {
       return this.cPesquisador.exibePesquisador(email);
    }

    public boolean pesquisadorEhAtivo(String email) {
        return this.cPesquisador.pesquisadorEhAtivo(email);
    }

    //US3
    public void cadastraProblema(String descricao, int viabilidade) {
    	cProblema.cadastraProblema(descricao, viabilidade);
    }
    
    public void cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
    	cObjetivo.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
    }
    
    public void apagarProblema(String codigo) throws Exception {
    	cProblema.apagarProblema(codigo);
    }
    
    public void apagarObjetivo(String codigo) throws Exception {
    	cObjetivo.apagarObjetivo(codigo);
    }
    
    public String exibeProblema(String codigo) throws Exception {
    	return cProblema.exibeProblema(codigo);
    }
    
    public String exibeObjetivo(String codigo) throws Exception {
    	return cObjetivo.exibeObjetivo(codigo);
    }

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
    
    //US5 
    
    public String associaProblema(String idPesquisa, String idProblema) {
    	
    	return this.cGeral.associaProblema(cPesquisa, cProblema, idPesquisa, idProblema);
    	
    }
    
    public String desassociaProblema(String idPesquisa, String idProblema) {
    	
    	return this.cGeral.desassociaProblema(cPesquisa, cProblema, idPesquisa, idProblema);
    	
    }
    
    public String associaObjetivo(String idPesquisa, String idObjetivo) {
    	
    	return this.cGeral.associaObjetivo(cPesquisa, cObjetivo, idPesquisa, idObjetivo);
    	
    }
    
    public String desassociaObjetivo(String idPesquisa, String idProblema) {
    	
    	return this.cGeral.desassociaObjetivo(cPesquisa, cProblema, idPesquisa, idProblema);
    	
    }
    
    
    //US6
    public void associaPesquisador(String idPesquisa, String emailPesquisador) {
    	cGeral.associaPesquisador(cPesquisador, cPesquisa, idPesquisa, emailPesquisador);
    }
    
    public void desassociaPesquisador(String idPesquisa, String emailPesquisador) {
    	cGeral.desassociaPesquisador(cPesquisa, idPesquisa, emailPesquisador);
    }
    
    public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
    	cPesquisador.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
    }
    
    public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
    	cPesquisador.cadastraEspecialidadeAluno(email, semestre, IEA);
    }

    //US8
    public String busca(String termo){
        //return this.cBuscas.busca(termo);
        return "oi";
    }
    
    /**
    public String busca(String termo, int numeroDoResultado){
    	
    	return "oi";
    	
    }

    public int contaResultadosBusca(String termo){
    	
        return this.cGeral.busca(cPesquisa, cPesquisador, cProblema, cObjetivo, cAtividade, cBuscas, termo);
    }

    public String busca(String termo, int numeroDoResultado){
		return null;
    }

    public int contaResultadosBusca(String termo){
    	return 1;

    }
*/
}
