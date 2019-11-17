package comparators;

import java.util.Comparator;

import base.Atividade;

public class OrdenaAtvdsMenosPendencias implements Comparator<Atividade>{

	@Override
	public int compare(Atividade a1, Atividade a2) {
		if(a1.contaItensPendentes() > a2.contaItensPendentes()) {
			return 1;
		}else if(a1.contaItensPendentes() < a2.contaItensPendentes()) {
			return -1;
		}
		return 0;
		
	}
	
	
	
}
