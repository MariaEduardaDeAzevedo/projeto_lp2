package comparators;

import java.util.Comparator;

import base.Pesquisa;

/**
 * Classe comparadora para ordenar Pesquisa em ordem crescente por ID do Problema a ela associado
 * 
 * @author Maria Eduarda de Azevedo Silva - 119110210
 *
 */
public class OrdenaPesquisaProblema implements Comparator<Pesquisa> {

	@Override
	/** 
	 * Compara dois objetos Pesquisa em relação a seus problemas associados e retorna um inteiro referente a ordem
	 */
	public int compare(Pesquisa o1, Pesquisa o2) {
		
		return o1.getProblema().toString().compareTo(o2.getProblema().toString());
	
	}

}