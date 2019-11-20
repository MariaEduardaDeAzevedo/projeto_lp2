package comparators;

import java.util.Comparator;

import base.Atividade;

/**
 * Classe comparadora para ordenar Atividade em ordem crescente de menos pendencias.
 */
public class OrdenaAtvdsMenosPendencias implements Comparator<Atividade> {

    /**
     * Compara dois objetos, retornando o valor de 1 se o primeiro objeto for maior que o segundo,
     * retorna -1 se o primeiro for menor que o segundo e retorna 0 se os valores de referencia
     * forem iguais.
     */
    @Override
    public int compare(Atividade a1, Atividade a2) {
        if (a1.contaItensPendentes() > a2.contaItensPendentes()) {
            return 1;
        } else if (a1.contaItensPendentes() < a2.contaItensPendentes()) {
            return -1;
        } else {
            return a1.getId().compareTo(a2.getId());
        }
    }
}