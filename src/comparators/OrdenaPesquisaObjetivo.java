package comparators;

import java.util.Comparator;

import base.Pesquisa;

/**
 * Classe de ordenacao de objetos pesquisa com base em seus objetivos associados
 */
public class OrdenaPesquisaObjetivo implements Comparator<Pesquisa> {

    @Override
    /**
     * Classe comparadora para ordenar Pesquisa em ordem crescente pelo ID de um Objetivo a ela associado.
     */
    public int compare(Pesquisa o1, Pesquisa o2) {
        return o1.getMaiorIDObjetivo().toString().compareTo(o2.getMaiorIDObjetivo().toString());
    }
}