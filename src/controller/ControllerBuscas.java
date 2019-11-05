package controller;

import base.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ControllerBuscas extends Validacao{
    private Conector conector;
    private Map<String, List> buscasRealizadas;

    public ControllerBuscas(Conector conector){
        this.conector = conector;
        this.buscasRealizadas = new TreeMap<>();
    }

    public String busca(String termo) {
        super.validaString(termo, "Campo termo nao pode ser nulo ou vazio.");
        List<String> lista_identidades = new ArrayList<String>();
        List<Pesquisa> pesquisas = conector.getPesquisas();
        for (int i = 0; i < pesquisas.size(); i++) {
            if (pesquisas.get(i).buscaTermoDescricao(termo) != null) {
                lista_identidades.add(pesquisas.get(i).buscaTermoDescricao(termo));
            } else if (pesquisas.get(i).buscaTermoCampoDeInteresse(termo) != null) {
                lista_identidades.add(pesquisas.get(i).buscaTermoCampoDeInteresse(termo));
            }
        }
        List<Pesquisador> pesquisadores = conector.getPesquisadores();
        for (int i = 0; i < pesquisadores.size(); i++) {
            if (pesquisadores.get(i).buscaTermo(termo) != null) {
                lista_identidades.add(pesquisadores.get(i).buscaTermo(termo));
            }
        }
        List<Problema> problemas = conector.getProblemas();
        for (int i = 0; i < problemas.size(); i++) {
            if (problemas.get(i).buscaTermo(termo) != null) {
                lista_identidades.add(problemas.get(i).buscaTermo(termo));
            }
        }
        List<Objetivo> objetivos = conector.getObjetivos();
        for (int i = 0; i < objetivos.size(); i++) {
            if (objetivos.get(i).buscaTermo(termo) != null) {
                lista_identidades.add(objetivos.get(i).buscaTermo(termo));
            }
        }
        List<Atividade> atividades = conector.getAtividades();
        for (int i = 0; i < objetivos.size(); i++) {
            if (atividades.get(i).buscaTermoDescricao(termo) != null) {
                lista_identidades.add(atividades.get(i).buscaTermoDescricao(termo));
            } else if (atividades.get(i).buscaTermoDescricaoDoRisco(termo) != null) {
                lista_identidades.add(atividades.get(i).buscaTermoDescricaoDoRisco(termo));
            }
        }
        super.hasValor(lista_identidades.isEmpty(), "Nenhum resultado encontrado");
        String entidadesComTermo = "";
        for (int i = 0; i < lista_identidades.size(); i++) {
            if (i == lista_identidades.size() - 1) {
                entidadesComTermo += lista_identidades.get(i);
            } else {
                entidadesComTermo += lista_identidades.get(i);
            }
        }
        buscasRealizadas.put(termo, lista_identidades);
        return entidadesComTermo;
    }
}
