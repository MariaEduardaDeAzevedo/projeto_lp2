package controller;

import base.*;
import excecoes.ActivationException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Conector extends Validacao {
    public boolean associaPesquisador(ControllerPesquisador cPesquisador, ControllerPesquisas cPesquisas, String idPesquisa, String emailPesquisador) {
        super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        super.validaString(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
        if (!cPesquisas.containsPesquisa(idPesquisa)) {
            throw new NullPointerException("Pesquisa nao encontrada.");
        }
        else if (!cPesquisas.pesquisaEhAtiva(idPesquisa)) {
            throw new ActivationException("Pesquisa desativada.");
        }
        Pesquisador associado = cPesquisador.getPesquisador(emailPesquisador);
        return cPesquisas.associaPesquisador(idPesquisa, associado);
    }

    public boolean desassociaPesquisador(ControllerPesquisas cPesquisas, String idPesquisa, String emailPesquisador) {
        super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        super.validaString(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
        if (!cPesquisas.containsPesquisa(idPesquisa)) {
            throw new NullPointerException("Pesquisa nao encontrada.");
        }
        else if (!cPesquisas.pesquisaEhAtiva(idPesquisa)) {
            throw new ActivationException("Pesquisa desativada.");
        }
        return cPesquisas.desassociaPesquisador(idPesquisa, emailPesquisador);
    }

    /**
     * public String associaProblema(ControllerPesquisas cPesquisa, ControllerProblemas cProblema, String idPesquisa,String idProblema) {
     * <p>
     * Problema problema = cProblema.getProblema(idProblema);
     * return cPesquisa.associaProblema(idPesquisa, idProblema, problema);
     * <p>
     * }
     **/

    public String desassociaProblema(ControllerPesquisas cPesquisa, ControllerProblemas cProblema, String idPesquisa, String idProblema) {

        return cPesquisa.desassociaProblema(idPesquisa, idProblema);

    }

    public String associaObjetivo(ControllerPesquisas cPesquisa, ControllerObjetivos cObjetivo, String idPesquisa, String idObjetivo) {

        Objetivo objetivo = cObjetivo.getObjetivo(idObjetivo);
        return cPesquisa.associaObjetivo(idPesquisa, idObjetivo, objetivo);

    }

    public String desassociaObjetivo(ControllerPesquisas cPesquisa, ControllerProblemas cProblema, String idPesquisa, String idProblema) {

        return cPesquisa.desassociaObjetivo(idPesquisa, idProblema);

    }

    public String busca(ControllerPesquisas cPesquisa, ControllerPesquisador cPesquisador, ControllerProblemas cProblema, ControllerObjetivos cObjetivo, ControllerAtividades cAtividade, ControllerBuscas cBuscas, String termo) {

        Collection<Pesquisa> pesquisas = cPesquisa.getPesquisas();


        Collection<Pesquisador> pesquisadores = cPesquisador.getPesquisadores();


        Collection<Problema> problemas = cProblema.getProblemas();


        Collection<Objetivo> objetivos = cObjetivo.getObjetivos();


        Collection<Atividade> atividades = cAtividade.getAtividades();
        return cBuscas.busca(termo, pesquisas, pesquisadores, problemas, objetivos, atividades);
    }

    public String busca(ControllerBuscas cBuscas, String termo, int numeroDoResultado) {
        return cBuscas.busca(termo, numeroDoResultado);
    }

    public boolean associaAtividade(ControllerPesquisas cPesquisa, ControllerAtividades cAtividade,
                                    String codigoPesquisa, String codigoAtividade) {
        super.validaString(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
        super.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
        super.hasValor(cAtividade.containsAtividade(codigoAtividade), "Atividade nao encontrada.");
        Atividade atividade = cAtividade.getAtividade(codigoAtividade);
        return cPesquisa.associaAtividade(codigoPesquisa, atividade);

    }

    public boolean desassociaAtividade(ControllerPesquisas cPesquisa, String codigoPesquisa, String codigoAtividade) {
        super.validaString(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
        super.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
        if (cPesquisa.containsPesquisa(codigoPesquisa)) {
            throw new IllegalArgumentException("Pesquisa nao encontrada.");
        }
        if (!cPesquisa.pesquisaEhAtiva(codigoPesquisa)) {
            throw new IllegalArgumentException("Pesquisa desativada.");
        }
        return cPesquisa.desassociaAtividade(codigoPesquisa, codigoAtividade);
    }

}
