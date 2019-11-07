package comparators;

import java.util.Comparator;

import base.Pesquisa;

public class OrdenaPesquisa implements Comparator<Pesquisa> {

	@Override
	public int compare(Pesquisa o1, Pesquisa o2) {
		
		return -1 * (o1.toString().compareTo(o2.toString()));
	
	}

}
