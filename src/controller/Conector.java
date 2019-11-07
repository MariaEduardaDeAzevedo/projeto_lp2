package controller;

import base.*;
import excecoes.ActivationException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Conector extends Validacao {
	public void associaPesquisador(ControllerPesquisador cPesquisador, ControllerPesquisas cPesquisas,
			String idPesquisa, String emailPesquisador) {
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

	/**
	 * Associa um objeto Problema a um objeto Pesquisa pelos seus IDs indicados.
	 * Caso a operação seja bem sucedida, retorna-se a String "true". Caso
	 * contrário, retorna-se "false"
	 * 
	 * @param cPesquisa  Entidade controladora de objetos Pesquisa
	 * @param cProblema  Entidade controladora de objetos Problema
	 * @param idPesquisa String que representa unicamente um objeto Pesquisa
	 * @param idProblema String que representa unicamente um objeto Problema
	 * @return String referente ao sucesso da operação
	 */
	public String associaProblema(ControllerPesquisas cPesquisa, ControllerProblemas cProblema, String idPesquisa,
			String idProblema) {

		Problema problema = cProblema.getProblema(idProblema);
		return cPesquisa.associaProblema(idPesquisa, idProblema, problema);

	}
	
	/**
	 * Desassocia um objeto Problema de uma Pesquisa indicada pelos seus IDs
	 * Caso a operação seja bem sucedida, retorna-se a String "true". Caso
	 * contrário, retorna-se "false"
	 * 
	 * 
	 * @param cPesquisa  Entidade controladora de objetos Pesquisa
	 * @param cProblema  Entidade controladora de objetos Problema
	 * @param idPesquisa String que representa unicamente um objeto Pesquisa
	 * @param idProblema String que representa unicamente um objeto Problema
	 * @return String referente ao sucesso da operação
	 */
	public String desassociaProblema(ControllerPesquisas cPesquisa, ControllerProblemas cProblema, String idPesquisa,
			String idProblema) {

		return cPesquisa.desassociaProblema(idPesquisa, idProblema);

	}
	
	/**
	 * Associa um objeto Objetivo a um objeto Pesquisa pelos seus ID's indicados.
	 * Caso a operação seja bem sucedida, retorna-se a String "true". Caso
	 * contrário, retorna-se "false"
	 * 
	 * @param cPesquisa  Entidade controladora de objetos Pesquisa
	 * @param cObjetivo  Entidade controladora de objetos Objetivo
	 * @param idPesquisa String que representa unicamente um objeto Pesquisa
	 * @param idObjetivo String que representa unicamente um objeto Objetivo
	 * @return String referente ao sucesso da operação
	 */
	public String associaObjetivo(ControllerPesquisas cPesquisa, ControllerObjetivos cObjetivo, String idPesquisa,
			String idObjetivo) {

		Objetivo objetivo = cObjetivo.getObjetivo(idObjetivo);
		return cPesquisa.associaObjetivo(idPesquisa, idObjetivo, objetivo);

	}
	
	/**
	 * Desassocia um objeto Objetivo de uma Pesquisa indicada pelos seus IDs
	 * Caso a operação seja bem sucedida, retorna-se a String "true". Caso
	 * contrário, retorna-se "false"
	 * 
	 * 
	 * @param cPesquisa  Entidade controladora de objetos Pesquisa
	 * @param cObjetivo  Entidade controladora de objetos Problema
	 * @param idPesquisa String que representa unicamente um objeto Pesquisa
	 * @param idObjetivo String que representa unicamente um objeto Objetivo
	 * @return String referente ao sucesso da operação
	 */
	public String desassociaObjetivo(ControllerPesquisas cPesquisa, ControllerProblemas cProblema, String idPesquisa,
			String idProblema) {

		return cPesquisa.desassociaObjetivo(idPesquisa, idProblema);

	}

	public String busca(ControllerPesquisas cPesquisa, ControllerPesquisador cPesquisador,
			ControllerProblemas cProblema, ControllerObjetivos cObjetivo, ControllerAtividades cAtividade,
			ControllerBuscas cBuscas, String termo) {

		Collection<Pesquisa> pesquisas = cPesquisa.getPesquisas();

		Collection<Pesquisador> pesquisadores = cPesquisador.getPesquisadores();

		Collection<Problema> problemas = cProblema.getProblemas();

		Collection<Objetivo> objetivos = cObjetivo.getObjetivos();

		Collection<Atividade> atividades = cAtividade.getAtividades();
		return cBuscas.busca(termo, pesquisas, pesquisadores, problemas, objetivos, atividades);
	}

}
