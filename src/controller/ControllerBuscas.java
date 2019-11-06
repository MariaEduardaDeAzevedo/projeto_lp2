package controller;

import base.*;

import java.util.*;

public class ControllerBuscas extends Validacao{
    private Conector conector;
    private Map<String, List> buscasRealizadas;

    public ControllerBuscas(Conector conector){
        this.conector = conector;
        this.buscasRealizadas = new TreeMap<>();
    }

    public String busca(String termo, Collection<Pesquisa> pesquisas, Collection<Pesquisador> pesquisadores,
                        Collection<Problema> problemas, Collection<Objetivo> objetivos, Collection<Atividade> atividades) {
        super.validaString(termo, "Campo termo nao pode ser nulo ou vazio.");
        List<String> lista_identidades = new ArrayList<String>();
        for (Pesquisa pesquisa : pesquisas){
            if (pesquisa.buscaTermoDescricao(termo) != null) {
                lista_identidades.add(pesquisa.buscaTermoDescricao(termo));
            } if (pesquisa.buscaTermoCampoDeInteresse(termo) != null) {
                lista_identidades.add(pesquisa.buscaTermoCampoDeInteresse(termo));
            }
        }

        for (Pesquisador pesquisador : pesquisadores) {
            if (pesquisador.buscaTermo(termo) != null) {
                lista_identidades.add(pesquisador.buscaTermo(termo));
            }
        }
        for (Problema problema : problemas) {
            if (problema.buscaTermo(termo) != null) {
                lista_identidades.add(problema.buscaTermo(termo));
            }
        }
        for (Objetivo objetivo : objetivos) {
            if (objetivo.buscaTermo(termo) != null) {
                lista_identidades.add(objetivo.buscaTermo(termo));
            }
        }
        for (Atividade atividade : atividades) {
            if (atividade.buscaTermoDescricao(termo) != null) {
                lista_identidades.add(atividade.buscaTermoDescricao(termo));
            } else if (atividade.buscaTermoDescricaoDoRisco(termo) != null) {
                lista_identidades.add(atividade.buscaTermoDescricaoDoRisco(termo));
            }
        }
        /*for (int i = 0; i < pesquisas.size(); i++) {
            if (pesquisas.get(i).buscaTermoDescricao(termo) != null) {
                lista_identidades.add(pesquisas.get(i).buscaTermoDescricao(termo));
            } else if (pesquisas.get(i).buscaTermoCampoDeInteresse(termo) != null) {
                lista_identidades.add(pesquisas.get(i).buscaTermoCampoDeInteresse(termo));
            }
        }
        for (int i = 0; i < pesquisadores.size(); i++) {
            if (pesquisadores.get(i).buscaTermo(termo) != null) {
                lista_identidades.add(pesquisadores.get(i).buscaTermo(termo));
            }
        }
        for (int i = 0; i < problemas.size(); i++) {
            if (problemas.get(i).buscaTermo(termo) != null) {
                lista_identidades.add(problemas.get(i).buscaTermo(termo));
            }
        }
        for (int i = 0; i < objetivos.size(); i++) {
            if (objetivos.get(i).buscaTermo(termo) != null) {
                lista_identidades.add(objetivos.get(i).buscaTermo(termo));
            }
        }
        for (int i = 0; i < atividades.size(); i++) {
            if (atividades.get(i).buscaTermoDescricao(termo) != null) {
                lista_identidades.add(atividades.get(i).buscaTermoDescricao(termo));
            } else if (atividades.get(i).buscaTermoDescricaoDoRisco(termo) != null) {
                lista_identidades.add(atividades.get(i).buscaTermoDescricaoDoRisco(termo));
            }
        }*/
        //super.hasValor(lista_identidades.isEmpty(), "Nenhum resultado encontrado");
        String entidadesComTermo = "";
        for (int i = 0; i < lista_identidades.size(); i++) {
            if (i == lista_identidades.size() - 1) {
                entidadesComTermo += lista_identidades.get(i);
            } else {
                entidadesComTermo += lista_identidades.get(i) + " | ";
            }
        }
        buscasRealizadas.put(termo, lista_identidades);
        return entidadesComTermo;
    }
}
