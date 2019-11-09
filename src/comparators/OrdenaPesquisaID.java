package comparators;

import java.util.Comparator;

import base.Pesquisa;

/**
 * Classe comparadora para ordenar Pesquisa em ordem crescente por ID
 * 
 * @author Maria Eduarda de Azevedo Silva - 119110210
 *
 */
public class OrdenaPesquisaID implements Comparator<Pesquisa> {

	@Override
	/**
	 * Compara dois objetos pesquisa e retorna um inteiro referente a ordem de seus IDs
	 * 
	 */
	public int compare(Pesquisa o1, Pesquisa o2) {
		
		return -1 * (o1.toString().compareTo(o2.toString()));
	
	}

}