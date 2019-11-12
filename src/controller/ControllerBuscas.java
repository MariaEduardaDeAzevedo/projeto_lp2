package controller;

import base.*;

import java.util.*;

/**
 * Entidade responsavel pelos metodos referentes a buscas no sistema e a armazenar tais buscas.
 */
public class ControllerBuscas extends Validacao {
    /**
     * Aramzena um mapa das buscas realizadas no sistema de acordo pelo termo buscado.
     */
    private Map<String, List<String>> buscasRealizadas;

    /**
     * Constroi o objeto ControllerBuscas sem parametros e inicializa seus atributos.
     */
    public ControllerBuscas() {
        this.buscasRealizadas = new TreeMap<>();
    }

    /**
     * Busca a ocorrencia de um termo em todas as entidades do sistema.
     * @param termo termo que se deseja verificar a ocorrencia.
     * @param pesquisas Collection com todas as pesquisas cadastradas.
     * @param pesquisadores Collection com todos os pesquisadores cadastrados.
     * @param problemas Collection com todas as problemas cadastrados.
     * @param objetivos Collection com todas os objetivos cadastrados.
     * @param atividades Collection com todas as atividades cadastradas.
     * @return String contendo a representacao das entidades que fazem mencao ao termo.
     */
    public String busca(String termo, Collection<Pesquisa> pesquisas, Collection<Pesquisador> pesquisadores,
                        Collection<Problema> problemas, Collection<Objetivo> objetivos, Collection<Atividade> atividades) {
        super.validaString(termo, "Campo termo nao pode ser nulo ou vazio.");
        List<String> lista_identidades = new ArrayList<String>();
        for (Pesquisa pesquisa : pesquisas) {
            if (pesquisa.buscaTermoDescricao(termo) != null) {
                lista_identidades.add(pesquisa.buscaTermoDescricao(termo));
            }
            if (pesquisa.buscaTermoCampoDeInteresse(termo) != null) {
                lista_identidades.add(pesquisa.buscaTermoCampoDeInteresse(termo));
            }
        }
        List<String> lista_pesquisadores = new ArrayList<String>();
        for (Pesquisador pesquisador : pesquisadores) {
            if (pesquisador.buscaTermo(termo) != null) {
                lista_pesquisadores.add(pesquisador.buscaTermo(termo));
            }
        }
        Collections.reverse(lista_pesquisadores);
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
        lista_identidades.addAll(lista_pesquisadores);
        lista_identidades.addAll(lista_problemas);
        lista_identidades.addAll(lista_objetivos);
        lista_identidades.addAll(lista_atividades);
        //super.hasValor(!(lista_identidades.size() == 0), "Nenhum resultado encontrado");
        String entidadesComTermo = "";
        for (int i = 0; i < lista_identidades.size(); i++) {
            if (i == lista_identidades.size() - 1) {
                entidadesComTermo += lista_identidades.get(i);
            } else {
                entidadesComTermo += lista_identidades.get(i) + " | ";
            }
        } if (lista_identidades.size() != 0) {
            this.buscasRealizadas.put(termo, lista_identidades);
        }
        return entidadesComTermo;
    }

    /**
     * Busca um termo que ja foi previamente buscado no sistema e retorna um determinado elemento da lista de entidades que possuem esse termo.
     * @param termo termo que ja foi previamente buscado.
     * @param numeroDoResultado numero do elemento que se deseja retornar.
     * @return String contendo o elemento que estava na lista dos resultados da busca pelo termo.
     */
    public String busca(String termo, int numeroDoResultado) {
        super.validaString(termo, "Campo termo nao pode ser nulo ou vazio.");
        super.validaNumeroResultado(numeroDoResultado, "Numero do resultado nao pode ser negativo");
        super.hasValor(!(numeroDoResultado - 1 > buscasRealizadas.get(termo).size()), "Entidade nao encontrada.");
        String entidade = this.buscasRealizadas.get(termo).get(numeroDoResultado - 1);
        return entidade;
    }

    /**
     * Conta o numero de entidades que fazem mencao ao termo que ja foi previamente buscado.
     * @param termo termo que já foi previamente buscado.
     * @return int representado o numero de entidades que contem o termo.
     */
    public int contaResultadosBusca(String termo) {
        super.validaString(termo, "Campo termo nao pode ser nulo ou vazio.");
        super.hasValor(buscasRealizadas.containsKey(termo), "Nenhum resultado encontrado");
        return buscasRealizadas.get(termo).size();
    }
}
