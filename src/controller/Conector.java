package controller;

import base.*;

import java.util.List;

public class Conector {
	
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
}
