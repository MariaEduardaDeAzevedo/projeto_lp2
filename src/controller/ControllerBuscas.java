package controller;

import base.*;
import validacao.Validacao;

import java.util.*;

/**
 * Entidade responsavel pelos metodos referentes a buscas no sistema.
 */
public class ControllerBuscas extends Validacao {

    /**
     * Constroi o objeto ControllerBuscas sem parametros e inicializa seus atributos.
     */
    public ControllerBuscas() {
    }

    /**
     * Busca a ocorrencia de um termo em todas as entidades do sistema.
     *
     * @param termo         termo que se deseja verificar a ocorrencia.
     * @param pesquisas     Collection com todas as pesquisas cadastradas.
     * @param pesquisadores Collection com todos os pesquisadores cadastrados.
     * @param problemas     Collection com todas as problemas cadastrados.
     * @param objetivos     Collection com todas os objetivos cadastrados.
     * @param atividades    Collection com todas as atividades cadastradas.
     * @return String contendo a representacao das entidades que fazem mencao ao termo.
     */
    public String busca(String termo, Collection<Pesquisa> pesquisas, Collection<Pesquisador> pesquisadores,
                        Collection<Problema> problemas, Collection<Objetivo> objetivos, Collection<Atividade> atividades) {
        super.validaString(termo, "Campo termo nao pode ser nulo ou vazio.");
        List<String> lista_identidades = retornaEntidadesComTermo(pesquisas, pesquisadores, problemas, objetivos,
                atividades, termo);
        String entidadesComTermo = "";
        for (int i = 0; i < lista_identidades.size(); i++) {
            if (i == lista_identidades.size() - 1) {
                entidadesComTermo += lista_identidades.get(i);
            } else {
                entidadesComTermo += lista_identidades.get(i) + " | ";
            }
        }
        return entidadesComTermo;
    }

    /**
     * Busca um termo que ja foi previamente buscado no sistema e retorna um determinado elemento da lista de entidades que possuem esse termo.
     *
     * @param termo             termo que ja foi previamente buscado.
     * @param numeroDoResultado numero do elemento que se deseja retornar.
     * @return String contendo o elemento que estava na lista dos resultados da busca pelo termo.
     */
    public String buscaResultado(String termo, Collection<Pesquisa> pesquisas, Collection<Pesquisador> pesquisadores,
                                 Collection<Problema> problemas, Collection<Objetivo> objetivos, Collection<Atividade> atividades, int numeroDoResultado) {
        super.validaString(termo, "Campo termo nao pode ser nulo ou vazio.");
        super.validaNumeroResultado(numeroDoResultado, "Numero do resultado nao pode ser negativo");
        List entidades = retornaEntidadesComTermo(pesquisas, pesquisadores, problemas, objetivos,
                atividades, termo);
        if (numeroDoResultado - 1 > entidades.size()){
            throw new IllegalArgumentException("Entidade nao encontrada.");
        }
        return entidades.get(numeroDoResultado - 1).toString();
    }

    /**
     * Conta o numero de entidades que fazem mencao ao termo que ja foi previamente buscado.
     *
     * @param termo termo que ja foi previamente buscado.
     * @return int representado o numero de entidades que contem o termo.
     */
    public int contaResultadosBusca(Collection<Pesquisa> pesquisas, Collection<Pesquisador> pesquisadores,
                                    Collection<Problema> problemas, Collection<Objetivo> objetivos, Collection<Atividade> atividades, String termo) {
        super.validaString(termo, "Campo termo nao pode ser nulo ou vazio.");
        List entidades = retornaEntidadesComTermo(pesquisas, pesquisadores, problemas, objetivos,
                atividades, termo);
        if (entidades.size() == 0){
            throw new IllegalArgumentException("Nenhum resultado encontrado");
        }
        return entidades.size();
    }

    /**
     * Busca a ocorrencia de um termo em todas as entidades cadastradas do sistema.
     *
     * @param termo         termo que se deseja verificar a ocorrencia.
     * @param pesquisas     Collection com todas as pesquisas cadastradas.
     * @param pesquisadores Collection com todos os pesquisadores cadastrados.
     * @param problemas     Collection com todas as problemas cadastrados.
     * @param objetivos     Collection com todas os objetivos cadastrados.
     * @param atividades    Collection com todas as atividades cadastradas.
     * @return List contendo todas as entidades que faziam mencao ao termo buscado.
     */
    private List retornaEntidadesComTermo(Collection<Pesquisa> pesquisas, Collection<Pesquisador> pesquisadores,
                                          Collection<Problema> problemas, Collection<Objetivo> objetivos, Collection<Atividade> atividades, String termo) {
        List entidades = new ArrayList<>();
        List<String> lista_pesquisas = new ArrayList<>();
        for (Pesquisa pesquisa : pesquisas) {
            if (pesquisa.buscaTermoCampoDeInteresse(termo) != null) {
                lista_pesquisas.add(pesquisa.buscaTermoCampoDeInteresse(termo));
            }
            if (pesquisa.buscaTermoDescricao(termo) != null) {
                lista_pesquisas.add(pesquisa.buscaTermoDescricao(termo));
            }
        }
        Collections.reverse(lista_pesquisas);
        List<String> lista_pesquisadores = new ArrayList<String>();
        for (Pesquisador pesquisador : pesquisadores) {
            if (pesquisador.buscaTermo(termo) != null) {
                lista_pesquisadores.add(pesquisador.buscaTermo(termo));
            }
        }
        //Collections.reverse(lista_pesquisadores);
        List<String> lista_problemas = new ArrayList<String>();
        for (Problema problema : problemas) {
            if (problema.buscaTermo(termo) != null) {
                lista_problemas.add(problema.buscaTermo(termo));
            }
        }
        Collections.reverse(lista_problemas);
        List<String> lista_objetivos = new ArrayList<String>();
        for (Objetivo objetivo : objetivos) {
            if (objetivo.buscaTermo(termo) != null) {
                lista_objetivos.add(objetivo.buscaTermo(termo));
            }
        }
        Collections.reverse(lista_objetivos);
        List<String> lista_atividades = new ArrayList<String>();
        for (Atividade atividade : atividades) {
            if (atividade.buscaTermoDescricao(termo) != null) {
                lista_atividades.add(atividade.buscaTermoDescricao(termo));
            } else if (atividade.buscaTermoDescricaoDoRisco(termo) != null) {
                lista_atividades.add(atividade.buscaTermoDescricaoDoRisco(termo));
            }
        }
        Collections.reverse(lista_atividades);
        entidades.addAll(lista_pesquisas);
        entidades.addAll(lista_pesquisadores);
        entidades.addAll(lista_problemas);
        entidades.addAll(lista_objetivos);
        entidades.addAll(lista_atividades);
        return entidades;
    }
}