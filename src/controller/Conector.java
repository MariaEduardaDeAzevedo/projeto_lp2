package controller;

import base.*;
import excecoes.ActivationException;
import validacao.Validacao;

import java.util.Collection;

/**
 * Classe utilizada para juntar a lógica de dois ou mais controllers do sistema.
 *
 * @author Daniel Fonseca
 */
public class Conector extends Validacao {


    private ControllerPesquisador cPesquisador;
    private ControllerPesquisas cPesquisas;
    private ControllerProblemas cProblemas;
    private ControllerAtividades cAtividades;
    private ControllerObjetivos cObjetivos;
    private ControllerBuscas cBuscas;

    public Conector(ControllerPesquisador cPesquisador, ControllerPesquisas cPesquisas, ControllerProblemas cProblemas, ControllerAtividades cAtividades, ControllerObjetivos cObjetivos, ControllerBuscas cBuscas) {

        this.cPesquisador = cPesquisador;
        this.cPesquisas = cPesquisas;
        this.cProblemas = cProblemas;
        this.cAtividades = cAtividades;
        this.cObjetivos = cObjetivos;
        this.cBuscas = cBuscas;
    }

    /**
     * Associa um pesquisador cadastrado no sistema (ou qualquer uma de suas especializações) com uma pesquisa, que também deve estar cadastrada no sistema.
     *
     * @param idPesquisa       identificador único da pesquisa.
     * @param emailPesquisador email do pesquisador que se quer associar a pesquisa.
     * @return um valor booleano que indica se a associação do pesquisador a pesquisa foi realizada com sucesso. Se o pesquisador já estiver associado à pesquisa, é retornado
     * false, caso contrário, este é associado à pesquisa e é retornado true.
     */
    public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
        super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        super.validaString(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
        if (!cPesquisas.containsPesquisa(idPesquisa)) {
            throw new NullPointerException("Pesquisa nao encontrada.");
        } else if (!cPesquisas.pesquisaEhAtiva(idPesquisa)) {
            throw new ActivationException("Pesquisa desativada.");
        }
        Pesquisador associado = cPesquisador.getPesquisador(emailPesquisador);
        this.cPesquisador.addAssociado(emailPesquisador);
        return cPesquisas.associaPesquisador(idPesquisa, associado);
    }

    /**
     * Desassocia um pesquisador associado a uma pesquisa cadastrada no sistema.
     *
     * @param idPesquisa       id da pesquisa a que se quer desassociar o pesquisador.
     * @param emailPesquisador email do pesquisador que se quer desassociar da pesquisa.
     * @return retorna um valor booleano que indica se a operação foi realizada com sucesso ou não. É retornado true quando
     * a desassociação é feita com sucesso, e, caso contrário, se o pesquisador não estiver associado à pesquisa, é retornado false, indicando
     * que a desassociação não foi bem sucedida.
     */
    public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
        super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        super.validaString(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
        if (!cPesquisas.containsPesquisa(idPesquisa)) {
            throw new NullPointerException("Pesquisa nao encontrada.");
        } else if (!cPesquisas.pesquisaEhAtiva(idPesquisa)) {
            throw new ActivationException("Pesquisa desativada.");
        }
        this.cPesquisador.removeAssociado(emailPesquisador);
        return cPesquisas.desassociaPesquisador(idPesquisa, emailPesquisador);
    }

    /**
     * Associa um objeto Problema a um objeto Pesquisa pelos seus IDs indicados.
     * Caso a operação seja bem sucedida, retorna-se a String "true". Caso
     * contrário, retorna-se "false"
     *
     * @param idPesquisa String que representa unicamente um objeto Pesquisa
     * @param idProblema String que representa unicamente um objeto Problema
     * @return String referente ao sucesso da operação
     */
    public boolean associaProblema(String idPesquisa, String idProblema) {

        Problema problema = cProblemas.getProblema(idProblema);
        return cPesquisas.associaProblema(idPesquisa, idProblema, problema);

    }

    /**
     * Desassocia um objeto Problema de uma Pesquisa indicada pelos seus IDs
     * Caso a operação seja bem sucedida, retorna-se a String "true". Caso
     * contrário, retorna-se "false"
     *
     * @param idPesquisa String que representa unicamente um objeto Pesquisa
     * @return String referente ao sucesso da operação
     */
    public boolean desassociaProblema(String idPesquisa) {

        return cPesquisas.desassociaProblema(idPesquisa);

    }

    /**
     * Associa um objeto Objetivo a um objeto Pesquisa pelos seus ID's indicados.
     * Caso a operação seja bem sucedida, retorna-se a String "true". Caso
     * contrário, retorna-se "false"
     *
     * @param idPesquisa String que representa unicamente um objeto Pesquisa
     * @param idObjetivo String que representa unicamente um objeto Objetivo
     * @return String referente ao sucesso da operação
     */
    public boolean associaObjetivo(String idPesquisa, String idObjetivo) {

        Objetivo objetivo = cObjetivos.getObjetivo(idObjetivo);
        return cPesquisas.associaObjetivo(idPesquisa, idObjetivo, objetivo);

    }

    /**
     * Desassocia um objeto Objetivo de uma Pesquisa indicada pelos seus IDs
     * Caso a operação seja bem sucedida, retorna-se a String "true". Caso
     * contrário, retorna-se "false"
     *
     * @param idPesquisa String que representa unicamente um objeto Pesquisa
     * @param idProblema String que representa unicamente um objeto Objetivo
     * @return String referente ao sucesso da operação
     */
    public boolean desassociaObjetivo(String idPesquisa, String idProblema) {

        return cPesquisas.desassociaObjetivo(idPesquisa, idProblema);

    }

    /**
     * Busca a ocorrencia de um termo em todas as entidades do sistema.
     *
     * @param termo termo que se deseja verificar a ocorrencia.
     * @return String contendo a representacao das entidades que fazem mencao ao termo.
     */
    public String busca(String termo) {

        Collection<Pesquisa> pesquisas = this.cPesquisas.getPesquisas();


        Collection<Pesquisador> pesquisadores = this.cPesquisador.getPesquisadores();


        Collection<Problema> problemas = this.cProblemas.getProblemas();


        Collection<Objetivo> objetivos = this.cObjetivos.getObjetivos();


        Collection<Atividade> atividades = this.cAtividades.getAtividades();
        return cBuscas.busca(termo, pesquisas, pesquisadores, problemas, objetivos, atividades);
    }

    /**
     * Busca um termo que ja foi previamente buscado no sistema e retorna um determinado elemento da lista de entidades que possuem esse termo.
     *
     * @param termo             termo que ja foi previamente buscado.
     * @param numeroDoResultado numero do elemento que se deseja retornar.
     * @return String contendo o elemento que estava na lista dos resultados da busca pelo termo.
     */
    public String busca(String termo, int numeroDoResultado) {

        Collection<Pesquisa> pesquisas = this.cPesquisas.getPesquisas();


        Collection<Pesquisador> pesquisadores = this.cPesquisador.getPesquisadores();


        Collection<Problema> problemas = this.cProblemas.getProblemas();


        Collection<Objetivo> objetivos = this.cObjetivos.getObjetivos();


        Collection<Atividade> atividades = this.cAtividades.getAtividades();
        return cBuscas.buscaResultado(termo, pesquisas, pesquisadores, problemas, objetivos, atividades, numeroDoResultado);
    }

    /**
     * Conta o numero de entidades que fazem mencao ao termo que ja foi previamente buscado.
     *
     * @param termo termo que já foi previamente buscado.
     * @return int representado o numero de entidades que contem o termo.
     */
    public int contaResultadosBusca(String termo) {
        Collection<Pesquisa> pesquisas = this.cPesquisas.getPesquisas();


        Collection<Pesquisador> pesquisadores = this.cPesquisador.getPesquisadores();


        Collection<Problema> problemas = this.cProblemas.getProblemas();


        Collection<Objetivo> objetivos = this.cObjetivos.getObjetivos();


        Collection<Atividade> atividades = this.cAtividades.getAtividades();

        return cBuscas.contaResultadosBusca(pesquisas, pesquisadores, problemas, objetivos, atividades, termo);
    }

    /**
     * Metodo que permite a associacao de uma atividade a uma determinada pesquisa.
     *
     * @param codigoPesquisa  Codigo da Pesquisa
     * @param codigoAtividade Codigo da atividade a ser associada a pesquisa
     * @return valor booleano indicando se a associacao foi bem sucedida ou nao.
     */
    public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
        super.validaString(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
        super.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
        super.hasValor(cAtividades.containsAtividade(codigoAtividade), "Atividade nao encontrada");
        Atividade atividade = cAtividades.getAtividade(codigoAtividade);
        return cPesquisas.associaAtividade(codigoPesquisa, atividade);

    }

    /**
     * Metodo que permite a desassociacao de uma atividade a uma determinada pesquisa.
     *
     * @param codigoPesquisa  Codigo da Pesquisa
     * @param codigoAtividade Codigo da atividade a ser associada a pesquisa
     * @return valor booleano indicando se a desassociacao foi bem sucedida ou nao.
     */
    public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
        super.validaString(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
        super.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
        super.hasValor(cAtividades.containsAtividade(codigoAtividade), "Atividade nao encontrada");

        if (!cPesquisas.containsPesquisa(codigoPesquisa)) {
            throw new IllegalArgumentException("Pesquisa nao encontrada.");
        }
        if (!cPesquisas.pesquisaEhAtiva(codigoPesquisa)) {
            throw new IllegalArgumentException("Pesquisa desativada.");
        }
        return cPesquisas.desassociaAtividade(codigoPesquisa, codigoAtividade);
    }

    /**
     * Metodo que permite a exeucacao de uma atividade, atraves do codigo da atividade
     * a ser executada, o item e a duracao do processo.
     *
     * @param codigoAtividade Codigo da atividade
     * @param item            Item a ser executado
     * @param duracao         Duracao da execucao
     */
    public void executaAtividade(String codigoAtividade, int item, int duracao) {
        super.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
        super.verificaNuloNegativo(item, "Item nao pode ser nulo ou negativo.");
        super.verificaNuloNegativo(duracao, "Duracao nao pode ser nula ou negativa.");
        if (!cPesquisas.contemAtividadeAssociada(codigoAtividade)) {
            throw new IllegalArgumentException("Atividade sem associacoes com pesquisas.");
        }
        cAtividades.executaAtividade(codigoAtividade, item, duracao);


    }

    /**
     * Metodo responsavel por salvar os dados de todos os cotrollers do sistema.
     */
    public void salvarArquivos() {
        this.cPesquisas.salvarArquivos();
        this.cAtividades.salvarArquivos();
        this.cPesquisador.salvarArquivos();
        this.cProblemas.salvarArquivos();
        this.cObjetivos.salvarArquivos();
    }

    /**
     * Metodo responsavel por recuperar todos os dados de todos os controllers do sistema, de uma
     * utilizacao anterior.
     */
    public void carregarArquivos() {
        this.cPesquisas.carregarArquivos();
        this.cAtividades.carregarArquivos();
        this.cPesquisador.carregarArquivos();
        this.cProblemas.carregarArquivos();
        this.cObjetivos.carregarArquivos();
    }

    /**
     * Especializa um Pesquisador cadastrado no sistema, que deve obrigatoriamente ter a função "professor", para Professor, que é subclasse
     * de Pesquisador e tem três atributos a mais que sua superclasse, que são: formação, unidade e data de contratação.
     *
     * @param email    email do pesquisador que se quer especializar.
     * @param formacao grau de formação do professor.
     * @param unidade  unidade alocada do professor.
     * @param data     data de contratação do professor.
     */
    public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
        if (this.cPesquisador.isAssociado(email)) {
            this.cPesquisas.alteraPesquisador(email, cPesquisador.cadastraEspecialidadeProfessor(email, formacao, unidade, data));
        } else {
            cPesquisador.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
        }
    }

    /**
     * Especializa um Pesquisador cadastrado no sistema, que deve obrigatoriamente ter a função "estudante", para Aluno, que é subclasse
     * de Pesquisador, e tem dois atributos a mais que sua superclasse, que são: semestre de ingresso e índice de eficiência acadêmica (IEA).
     *
     * @param email    email do pesquisador que se quer especializar.
     * @param semestre valor inteiro que corresponde ao semestre de ingresso do Aluno. Este valor não pode ser um número menor ou igual a zero.
     * @param iea      valor correspondente ao índice de eficiência acadêmica do aluno. Este valor não pode ser menor que zero e nem maior que um.
     */
    public void cadastraEspecialidadeAluno(String email, int semestre, double iea) {
        if (this.cPesquisador.isAssociado(email)) {
            this.cPesquisas.alteraPesquisadorAluno(email, cPesquisador.cadastraEspecialidadeAluno(email, semestre, iea));
        } else {
            cPesquisador.cadastraEspecialidadeAluno(email, semestre, iea);
        }
    }
}
