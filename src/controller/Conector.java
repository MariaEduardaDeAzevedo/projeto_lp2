package controller;

import base.*;
import excecoes.ActivationException;

import java.util.List;

public class Conector extends Validacao {
	
	private ControllerProblemas cProblema;
	private ControllerObjetivos cObjetivo;
	private ControllerPesquisas cPesquisas;
	private ControllerPesquisador cPesquisador;
	private ControllerAtividades cAtividades;
	
	public Conector() {
		
		this.cProblema = new ControllerProblemas();
		this.cObjetivo = new ControllerObjetivos();
		this.cPesquisas = new ControllerPesquisas();
		this.cPesquisador = new ControllerPesquisador();
		this.cAtividades = new ControllerAtividades();
		
	}
	
	public Problema getProblema(String id) {
		
		return this.cProblema.getProblema(id);
		
	}

	public Objetivo getObjetivo(String id) {
	
		return this.cObjetivo.getObjetivo(id);
	}

	public List<Pesquisa> getPesquisas(){

		return this.cPesquisas.getPesquisas();
	}

	public List<Pesquisador> getPesquisadores(){
		return this.cPesquisador.getPesquisadores();
	}

	public List<Problema> getProblemas() {
		return this.cProblema.getProblemas();
	}

	public List<Objetivo> getObjetivos() {
		return this.cObjetivo.getObjetivos();
	}

	public List<Atividade> getAtividades() {
		return this.cAtividades.getAtividades();
	}
	
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
}
