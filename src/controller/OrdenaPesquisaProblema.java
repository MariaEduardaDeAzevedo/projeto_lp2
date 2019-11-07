package controller;

import java.util.Comparator;

import base.Pesquisa;

public class OrdenaPesquisaProblema implements Comparator<Pesquisa> {

	@Override
	public int compare(Pesquisa o1, Pesquisa o2) {
		
		return -1 * (o1.getProblema().toString().compareTo(o2.getProblema().toString()));
	
	}

}
