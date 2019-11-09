package comparators;

import java.util.Comparator;

import base.Pesquisa;

public class OrdenaPesquisaObjetivo implements Comparator<Pesquisa> {

	@Override
	/**
	 * Classe comparadora para ordenar Pesquisa em ordem crescente pelo ID de um Objetivo a ela associado
	 * 
	 * @author Maria Eduarda de Azevedo Silva - 119110210
	 *
	 */
	public int compare(Pesquisa o1, Pesquisa o2) {
		
		return 1 * (o1.getObjetivo().toString().compareTo(o2.getObjetivo().toString()));
	
	}

}