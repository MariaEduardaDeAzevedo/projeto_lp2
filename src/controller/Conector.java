package controller;

import base.Objetivo;
import base.Problema;

public class Conector {
	
	private ControllerProblemas cProblema;
	private ControllerObjetivos cObjetivo;
	
	public Conector() {
		
		this.cProblema = new ControllerProblemas();
		this.cObjetivo = new ControllerObjetivos();
		
	}
	
	public Problema getProblema(String id) {
		
		return this.cProblema.getProblema(id);
		
	}

	public Objetivo getObjetivo(String id) {
	
		return this.cObjetivo.getObjetivo(id);
	}

}
