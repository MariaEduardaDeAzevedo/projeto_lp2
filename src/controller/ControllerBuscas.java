package controller;

import base.*;

import java.util.*;

public class ControllerBuscas extends Validacao {
    private Conector conector;
    private Map<String, List<String>> buscasRealizadas;

    public ControllerBuscas(Conector conector) {
        this.conector = conector;
        this.buscasRealizadas = new TreeMap<>();
    }

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
        super.hasValor(!(lista_identidades.size() == 0), "Nenhum resultado encontrado");
        String entidadesComTermo = "";
        for (int i = 0; i < lista_identidades.size(); i++) {
            if (i == lista_identidades.size() - 1) {
                entidadesComTermo += lista_identidades.get(i);
            } else {
                entidadesComTermo += lista_identidades.get(i) + " | ";
            }
        }
        this.buscasRealizadas.put(termo, lista_identidades);
        return entidadesComTermo;
    }

    public String busca(String termo, int numeroDoResultado) {
        super.validaString(termo, "Campo termo nao pode ser nulo ou vazio.");
        super.validaNumeroResultado(numeroDoResultado, "Numero do resultado nao pode ser negativo");
        super.hasValor(!(numeroDoResultado - 1 > buscasRealizadas.get(termo).size()), "Entidade nao encontrada.");
        String entidade = this.buscasRealizadas.get(termo).get(numeroDoResultado - 1);
        return entidade;
    }

    public int contaResultadosBusca(String termo) {
        super.validaString(termo, "Campo termo nao pode ser nulo ou vazio.");
        super.hasValor(buscasRealizadas.containsKey(termo), "Nenhum resultado encontrado");
        return buscasRealizadas.get(termo).size();
    }
}
