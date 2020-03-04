package facade;

import java.io.IOException;

import controller.*;

public class Facade {

    private ControllerAtividades cAtividade;
    private ControllerPesquisador cPesquisador;
    private ControllerPesquisas cPesquisa;
    private ControllerObjetivos cObjetivo;
    private ControllerProblemas cProblema;
    private ControllerBuscas cBuscas;
    private Conector conector;

    public Facade() {
        this.cAtividade = new ControllerAtividades();
        this.cPesquisa = new ControllerPesquisas();
        this.cPesquisador = new ControllerPesquisador();
        this.cObjetivo = new ControllerObjetivos();
        this.cProblema = new ControllerProblemas();
        this.cBuscas = new ControllerBuscas();
        this.conector = new Conector(cPesquisador, cPesquisa, cProblema, cAtividade, cObjetivo, cBuscas);
    }

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

    public void cadastraProblema(String descricao, int viabilidade) {
    	cProblema.cadastraProblema(descricao, viabilidade);
    }
    
    public void cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
    	cObjetivo.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
    }
    
    public void apagarProblema(String codigo) {
    	cProblema.apagarProblema(codigo);
    }
    
    public void apagarObjetivo(String codigo) {
    	cObjetivo.apagarObjetivo(codigo);
    }
    
    public String exibeProblema(String codigo) {
    	return cProblema.exibeProblema(codigo);
    }
    
    public String exibeObjetivo(String codigo) {
    	return cObjetivo.exibeObjetivo(codigo);
    }

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

    public boolean associaProblema(String idPesquisa, String idProblema) {
    	return this.conector.associaProblema(idPesquisa, idProblema);
    }
    
    public boolean desassociaProblema(String idPesquisa) {
    	return this.conector.desassociaProblema(idPesquisa);
    }
    
    public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
    	return this.conector.associaObjetivo(idPesquisa, idObjetivo);
    }
    
    public boolean desassociaObjetivo(String idPesquisa, String idProblema) {
    	return this.conector.desassociaObjetivo(idPesquisa, idProblema);
    }
    
    public String listaPesquisas(String ordem) {
    	return this.cPesquisa.listar(ordem);
    }
    
    public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
    	return conector.associaPesquisador(idPesquisa, emailPesquisador);
    }
    
    public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
    	return conector.desassociaPesquisador(idPesquisa, emailPesquisador);
    }
    
    public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
    	conector.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
    }
    
    public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
    	conector.cadastraEspecialidadeAluno(email, semestre, IEA);
    }
    
    public String listaPesquisadores(String tipo) {
    	return cPesquisador.listaPesquisadores(tipo);
    }

    public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
    	return this.conector.associaAtividade(codigoPesquisa, codigoAtividade);
    }
    
    public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
    	return this.conector.desassociaAtividade(codigoPesquisa, codigoAtividade);
    }
    
    public void executaAtividade(String codigoAtividade, int item, int duracao) {
    	this.conector.executaAtividade(codigoAtividade, item, duracao);
    }
    public int cadastraResultado(String codigoAtividade, String resultado) {
    	return this.cAtividade.cadastraResultado(codigoAtividade, resultado);
    }
    
    public boolean removeResultado(String codigoAtividade, int numeroResultado) {
    	return this.cAtividade.removeResultado(codigoAtividade, numeroResultado);
    }
    
    public String listaResultados(String codigoAtividade) {
    	return this.cAtividade.listaResultados(codigoAtividade);
    }
    
    public int getDuracao(String codigoAtividade) {
    	return this.cAtividade.getDuracao(codigoAtividade);
    }

    public String busca(String termo){
        return this.conector.busca(termo);
    }

    public String busca(String termo, int numeroDoResultado){
        return this.conector.busca(termo, numeroDoResultado);
    }

    public int contaResultadosBusca(String termo){
        return this.conector.contaResultadosBusca(termo);
    }
   
    public void defineProximaAtividade(String idPrecedente, String idSubsquente) {
    	cAtividade.defineProximaAtividade(idPrecedente, idSubsquente);
    }
    
    public void tiraProximaAtividade(String idPrecedente) {
    	cAtividade.tiraProximaAtividade(idPrecedente);
    }
    
    public int contaProximos(String idPrecedente) {
    	return cAtividade.contaProximos(idPrecedente);
    }
    
    public String pegaProximo(String idAtividade, int enesimaAtividade) {
    	return cAtividade.pegaProximo(idAtividade, enesimaAtividade);
    }
    
    public String pegaMaiorRiscoAtividades(String idAtividade) {
    	return cAtividade.pegaMaiorRiscoAtividades(idAtividade);
    }

	public void configuraEstrategia(String estrategia) {
    	this.cPesquisa.configuraEstrategia(estrategia);
    }

    public String proximaAtividade(String codigoPesquisa) {
    	return this.cPesquisa.proximaAtividade(codigoPesquisa);
    }
    
    public void gravarResultados(String id) throws IOException {
    	this.cPesquisa.gravarResultadosPesquisa(id);
    }
    
    public void gravarResumo(String id) throws IOException {
    	this.cPesquisa.gravarResumoPesquisa(id);
    }

    public void salvar(){
        this.conector.salvarArquivos();
    }

    public void carregar(){
        this.conector.carregarArquivos();
    }
}
