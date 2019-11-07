package controller;

import java.util.Comparator;

import base.Pesquisa;

public class OrdenaPesquisaObjetivo implements Comparator<Pesquisa> {

	@Override
	public int compare(Pesquisa o1, Pesquisa o2) {
		
		/*
		 * Dúvida:
		 * 
		 * [listar primeiro a pesquisa com objetivo de maior ID].
		 * 
		 */
		return -1 * (o1.getObjetivo().toString().compareTo(o2.getObjetivo().toString()));
	
		/*
		COM1 - Homofobia em mensagens online de alunos de computacao do primeiro periodo. - computacao, homofobia | COM2 - Autoavaliacao na Disciplina de Programacao Orientada a Objeto. - computacao, poo | PSI1 - Alienacao Parental e o Sistema de Justica Brasileiro. - psicologia, sistema juridico, alienacao parental, brasil | FER1 - Aspectos da fermentacao do mosto cervejeiro por leveduras nao-Saccharomyces. - fermentacao, cerveja | ELE1 - Aumento da evasao no numero de eleitores paraibanos. - eleicao, paraiba
		COM2 - Autoavaliacao na Disciplina de Programacao Orientada a Objeto. - computacao, poo | COM1 - Homofobia em mensagens online de alunos de computacao do primeiro periodo. - computacao, homofobia | PSI1 - Alienacao Parental e o Sistema de Justica Brasileiro. - psicologia, sistema juridico, alienacao parental, brasil | FER1 - Aspectos da fermentacao do mosto cervejeiro por leveduras nao-Saccharomyces. - fermentacao, cerveja | ELE1 - Aumento da evasao no numero de eleitores paraibanos. - eleicao, paraiba
		*/
		
	}

}
