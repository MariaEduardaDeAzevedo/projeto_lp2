package comparators;

import java.util.Comparator;

import base.Atividade;

public class OrdenaAtvdsMaiorDuracao implements Comparator<Atividade>{

	@Override
	public int compare(Atividade a1, Atividade a2) {
		if(a1.getDuracao() > a2.getDuracao()) {
			return -1;
		} else if(a1.getDuracao() < a2.getDuracao()) {
			return 1;
		}
		return 0;
	}
	
	
}
