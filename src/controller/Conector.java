package controller;

import base.*;
import excecoes.ActivationException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Conector extends Validacao {
	public void associaPesquisador(ControllerPesquisador cPesquisador, ControllerPesquisas cPesquisas, String idPesquisa, String emailPesquisador) {
		super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		super.validaString(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		if (!cPesquisas.containsPesquisa(idPesquisa)) {
			throw new NullPointerException("Pesquisa nao encontrada.");
		}
		if (!cPesquisas.pesquisaEhAtiva(idPesquisa)) {
			throw new ActivationException("Pesquisa desativada.");
		}
		Pesquisador associado = cPesquisador.getPesquisador(emailPesquisador);
		cPesquisas.associaPesquisador(idPesquisa, associado);
	}
	
	public void desassociaPesquisador(ControllerPesquisas cPesquisas, String idPesquisa, String emailPesquisador) {
		super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		super.validaString(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		if (cPesquisas.containsPesquisa(idPesquisa)) {
			throw new NullPointerException("Pesquisa nao encontrada.");
		}
		if (!cPesquisas.pesquisaEhAtiva(idPesquisa)) {
			throw new ActivationException("Pesquisa desativada.");
		}
		cPesquisas.desassociaPesquisador(idPesquisa, emailPesquisador);
	}

    public String busca(ControllerPesquisas cPesquisa, ControllerPesquisador cPesquisador, ControllerProblemas cProblema, ControllerObjetivos cObjetivo, ControllerAtividades cAtividade, ControllerBuscas cBuscas, String termo) {

			Collection<Pesquisa> pesquisas =  cPesquisa.getPesquisas();


			Collection<Pesquisador> pesquisadores = cPesquisador.getPesquisadores();


			Collection<Problema> problemas = cProblema.getProblemas();


			Collection<Objetivo> objetivos = cObjetivo.getObjetivos();


			Collection<Atividade> atividades = cAtividade.getAtividades();
		return cBuscas.busca(termo, pesquisas, pesquisadores, problemas, objetivos, atividades);
	}
}
