package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import base.*;
import comparators.*;
import validacao.Validacao;

/**
 * Classe Controller responsavel pelos metodos referentes as pesquisas do
 * sistema.
 */
public class ControllerPesquisas extends Validacao implements Serializable {

    /**
     * Armazena um mapa de pesquisas a partir dos seus codigos.
     */
    private Map<String, Pesquisa> pesquisas;

    /**
     * Armazena um mapa de problemas associados a uma pesquisa indicada pelo seu ID
     */
    private Map<String, String> problemasAssociados;

    /**
     * Armazena um mapa de objetivos associados a uma pesquisa indicada pelo seu ID
     */
    private Map<String, String> objetivosAssociados;

    /**
     * Atributo que define a estrategia de escolha de proxima atividade.
     */
    private String estrategia;

    /**
     * Constroi o objeto ControllerPesquisas e inicializa seus atributos.
     */
    public ControllerPesquisas() {
        this.pesquisas = new TreeMap<String, Pesquisa>();
        this.problemasAssociados = new HashMap<String, String>();
        this.objetivosAssociados = new HashMap<String, String>();
        this.estrategia = "MAIS_ANTIGA";

    }

    /**
     * Cadastra uma pesquisa no sistema a partir dos parametros.
     *
     * @param descricao        descricao da pesquisa que se deseja adicionar.
     * @param campoDeInteresse campo de interesse da pesquisa que se deseja
     *                         adicionar.
     * @return String, caso o cadastro da pesquisa seja concluído, representando o
     * codigo da pesquisa.
     */
    public String cadastraPesquisa(String descricao, String campoDeInteresse) {
        super.validaString(descricao, "Descricao nao pode ser nula ou vazia.");
        super.validaString(campoDeInteresse, "Formato do campo de interesse invalido.");
        this.validaTamanhoDoCampo(campoDeInteresse, 255);
        String[] topicos = campoDeInteresse.split(",");
        this.validaTamanhoDoCampo(topicos, 4);
        for (String topico : topicos) {
            validaString(topico, "Formato do campo de interesse invalido.");
            if (topico.length() < 3) {
                throw new IllegalArgumentException("Formato do campo de interesse invalido.");
            }
        }
        String codigo = (campoDeInteresse.substring(0, 3) + "1").toUpperCase();
        if (pesquisas.containsKey(codigo)) {
            int numero = 1;
            while (true) {
                numero += 1;
                codigo = (campoDeInteresse.substring(0, 3) + numero).toUpperCase();
                if (!pesquisas.containsKey(codigo)) {
                    break;
                }
            }
        }
        pesquisas.put(codigo, new Pesquisa(codigo, descricao, campoDeInteresse));
        return codigo;
    }

    /**
     * Metodo privado responsavel por validar o tamanho do campo a ser verificado.
     *
     * @param campo   Array que armazena o campo a ser avaliado.
     * @param tamanho Referencia do tamanho a ser comparado.
     */
    private void validaTamanhoDoCampo(String[] campo, int tamanho) {
        if (campo.length > tamanho) {
            throw new IllegalArgumentException("Formato do campo de interesse invalido.");
        }
    }

    /**
     * Metodo privado responsavel por validar o tamanho do campo a ser verificado.
     *
     * @param campo   String que representa o campo a ser avaliado.
     * @param tamanho Referencia do tamanho a ser comparado.
     */
    private void validaTamanhoDoCampo(String campo, int tamanho) {
        if (campo.length() > tamanho) {
            throw new IllegalArgumentException("Formato do campo de interesse invalido.");
        }
    }

    /**
     * Altera o status de uma pesquisa cadastrada para ativa.
     *
     * @param codigo codigo da pesquisa que se deseja alterar o status.
     */
    public void ativaPesquisa(String codigo) {
        if (!pesquisas.containsKey(codigo)) {
            throw new IllegalArgumentException("Pesquisa nao encontrada.");
        }
        pesquisas.get(codigo).ativaPesquisa();
    }

    /**
     * Altera o status de uma pesquisa cadastrada para inativa.
     *
     * @param codigo codigo da pesquisa que se deseja alterar o status.
     * @param motivo motivo pelo qual a pesquisa está sendo desativada.
     */
    public void encerraPesquisa(String codigo, String motivo) {
        super.validaString(codigo, "Codigo nao pode ser nulo ou vazio.");
        super.validaString(motivo, "Motivo nao pode ser nulo ou vazio.");
        super.hasValor(pesquisas.containsKey(codigo), "Pesquisa nao encontrada.");
        pesquisas.get(codigo).encerraPesquisa(motivo);
    }

    /**
     * Altera a descricao ou campo de interesse de uma pesquisa cadastrada.
     *
     * @param codigo               codigo da pesquisa que se deseja alterar.
     * @param conteudoASerAlterado conteudo da pesquisa que se deseja alterar.
     * @param novoConteudo         conteudo que devera substituir o anteriormente
     *                             cadastrado na pesquisa.
     */
    public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
        super.validaString(codigo, "Codigo nao pode ser nulo ou vazio.");
        super.hasValor(pesquisas.containsKey(codigo), "Pesquisa nao encontrada.");
        pesquisas.get(codigo).alteraPesquisa(conteudoASerAlterado, novoConteudo);
    }

    /**
     * Exibe a representacao de uma pesquisa cadastrada.
     *
     * @param codigo codigo da pesquisa que se deseja exibir.
     * @return String contendo a representacao da pesquisa.
     */
    public String exibePesquisa(String codigo) {
        super.validaString(codigo, "Codigo nao pode ser nulo ou vazio.");
        super.hasValor(pesquisas.containsKey(codigo), "Pesquisa nao encontrada.");
        return pesquisas.get(codigo).toString();
    }

    /**
     * Retorna o status atual da pesquisa cadastrada.
     *
     * @param codigo codigo da pesquisa que se deseja verificar o status.
     * @return boolean representando se a pesquisa esta ativa ou nao.
     */
    public boolean pesquisaEhAtiva(String codigo) {
        super.validaString(codigo, "Codigo nao pode ser nulo ou vazio.");
        super.hasValor(pesquisas.containsKey(codigo), "Pesquisa nao encontrada.");
        return pesquisas.get(codigo).isAtivada();
    }


    /**
     * Retorna os valores do mapa de pesquisas.
     *
     * @return Collection contendo todos os objetos do tipo Pesquisa cadastrados no
     * sistema.
     */
    public Collection<Pesquisa> getPesquisas() {
        return pesquisas.values();
    }

    /**
     * Retorna um valor booleano que indica se uma pesquisa esta ou nao cadastrada
     * no sistema.
     *
     * @param idPesquisa id da pesquisa.
     * @return true, caso a pesquisa esteja cadastrada no sistema, ou false, caso
     * não esteja cadastrada no sistema.
     */
    public boolean containsPesquisa(String idPesquisa) {
        return pesquisas.containsKey(idPesquisa);
    }

    /**
     * Associa um pesquisador a uma pesquisa cadastrada no sistema. Retorna um valor
     * booleano que indica se a associacao foi bem sucedida ou nao.
     *
     * @param idPesquisa id da pesquisa a que se quer associar o pesquisador.
     * @param associado  Pesquisador que se quer associar à pesquisa.
     * @return true, caso a associacao seja bem sucedida, ou seja, caso o
     * pesquisador ja nao esteja associado a pesquisa ou false, caso a
     * associacao nao seja bem sucedida, ou seja, se o pesquisador ja
     * estiver associado a pesquisa.
     */
    public boolean associaPesquisador(String idPesquisa, Pesquisador associado) {
        super.hasValor(this.pesquisas.containsKey(idPesquisa), "Pesquisa nao encontrada.");
        super.validaStatus(pesquisas.get(idPesquisa).isAtivada(), "Pesquisa desativada.");
        if (pesquisas.get(idPesquisa).containsPesquisador(associado.getEmail())) {
            return false;
        }
        return pesquisas.get(idPesquisa).associaPesquisador(associado);
    }

    /**
     * Desassocia um pesquisador de uma pesquisa cadastrada no sistema. Retorna um
     * valor booleano que indica se a desassociacao foi bem sucedida ou nao.
     *
     * @param idPesquisa       id da pesquisa a que se quer desassociar o
     *                         pesquisador.
     * @param emailPesquisador email do pesquisador que se quer desassociar da
     *                         pesquisa.
     * @return true, caso a desassociacao seja bem sucedida, ou seja, se o
     * pesquisador que estava associado for removido, ou false, caso a
     * desassociacao for mal sucedida, ou seja, se o pesquisador nao estiver
     * associado a pesquisa.
     */
    public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
        super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        super.validaString(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
        super.hasValor(this.pesquisas.containsKey(idPesquisa), "Pesquisa nao encontrada.");
        super.validaStatus(pesquisas.get(idPesquisa).isAtivada(), "Pesquisa desativada.");

        if (!pesquisas.get(idPesquisa).containsPesquisador(emailPesquisador)) {
            return false;
        }
        return pesquisas.get(idPesquisa).desassociaPesquisador(emailPesquisador);
    }

    /**
     * Associa um Problema, indicado pelo seu ID, a uma Pesquisa, tambem indicada
     * pelo seu ID e retorna uma String referente ao sucesso da operação
     *
     * @param idPesquisa String que representa unicamente um objeto Pesquisa
     * @param idProblema String que representa unicamente um objeto Problema
     * @param problema   Objeto Problema a ser associado a pesquisa indicada
     * @return String referente ao sucesso da operação
     */
    public boolean associaProblema(String idPesquisa, String idProblema, Problema problema) {
        super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        super.validaString(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
        super.hasValor(this.pesquisas.containsKey(idPesquisa), "Pesquisa nao encontrada.");
        super.validaStatus(this.pesquisas.get(idPesquisa).isAtivada(), "Pesquisa desativada.");
        try {
            super.hasAssociado(idProblema, idPesquisa, this.problemasAssociados, true,
                    "Pesquisa ja associada a um problema.");
        } catch (IllegalArgumentException e) {
            return false;
        }
        this.pesquisas.get(idPesquisa).setProblema(problema);
        this.problemasAssociados.put(idProblema, idPesquisa);
        return true;
    }

    /**
     * Desassocia um Problema de uma Pesquisa, indicada pelo seu ID
     *
     * @param idPesquisa String que representa unicamente um objeto do tipo Pesquisa
     * @return String referente ao sucesso da operacao
     */
    public boolean desassociaProblema(String idPesquisa) {
        super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        super.hasValor(this.pesquisas.containsKey(idPesquisa), "Pesquisa nao encontrada.");
        super.validaStatus(this.pesquisaEhAtiva(idPesquisa), "Pesquisa desativada.");
        if (!this.problemasAssociados.containsValue(idPesquisa)) {
            return false;
        }
        this.problemasAssociados.remove(this.pesquisas.get(idPesquisa).getProblema().getId());
        this.pesquisas.get(idPesquisa).setProblema(null);
        return true;
    }

    /**
     * Associa um Objetivo, indicado pelo seu ID, a uma Pesquisa, tambem indicada
     * pelo seu ID e retorna uma String referente ao sucesso da operação
     *
     * @param idPesquisa String que representa unicamente um objeto Pesquisa
     * @param idObjetivo String que representa unicamente um objeto Objetivo
     * @return String referente ao sucesso da operacao
     */
    public boolean associaObjetivo(String idPesquisa, String idObjetivo, Objetivo objetivo) {
        super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        super.validaString(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
        super.hasValor(this.pesquisas.containsKey(idPesquisa), "Pesquisa nao encontrada.");
        super.validaStatus(this.pesquisaEhAtiva(idPesquisa), "Pesquisa desativada.");
        try {
            super.hasAssociado(idPesquisa, idObjetivo, this.objetivosAssociados, true,
                    "Objetivo ja associado a uma pesquisa.");
        } catch (IllegalArgumentException e) {
            return false;
        }
        this.pesquisas.get(idPesquisa).addObjetivo(idObjetivo, objetivo);
        this.objetivosAssociados.put(idPesquisa, idObjetivo);
        return true;
    }

    /**
     * Desassocia um Objetivo, indicado pelo seu ID, a uma Pesquisa, tambem indicada
     * pelo seu ID e retorna uma String referente ao sucesso da operacao
     *
     * @param idPesquisa String que representa unicamente um objeto Pesquisa
     * @param idObjetivo String que representa unicamente um objeto Objetivo
     * @return String referente ao sucesso da operacao
     */
    public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
        super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        super.validaString(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
        super.validaStatus(this.pesquisaEhAtiva(idPesquisa), "Pesquisa desativada.");
        super.hasValor(this.containsPesquisa(idPesquisa), "Pesquisa nao encontrada.");
        try {
            super.hasAssociado(idPesquisa, idObjetivo, this.objetivosAssociados, false, "");
        } catch (NullPointerException e) {
            return false;
        }
        this.objetivosAssociados.remove(idPesquisa);
        this.pesquisas.get(idPesquisa).removeObjetivo(idObjetivo);
        return true;
    }

    /**
     * Cria uma listagem em String com todas as pesquisas do sistema ordenadas pela
     * ordem indicada como parametro. Por padrao, as ordem aceitas sao: - PESQUISA -
     * PROBLEMAS - OBJETIVO
     *
     * @param ordem String que indica a ordem que deve ser gerada a listagem
     * @return String com a listagem de todas as pesquisas do sistema
     */
    public String listar(String ordem) {
        List<String> valores = new ArrayList<String>();
        valores.add("PESQUISA");
        valores.add("PROBLEMA");
        valores.add("OBJETIVOS");
        super.validaValoresPermitidos(valores, ordem, "Valor invalido da ordem");
        if (ordem.equals("PESQUISA")) {
            return this.ordenaPesquisa();
        } else if (ordem.equals("PROBLEMA")) {
            return this.ordenaProblema();
        }
        return this.ordenaObjetivo();
    }

    /**
     * Ordena as pesquisas do sistema por ordem do Objetivo de maior ID para o menor
     *
     * @return String com listagem de objetos Pesquisa do sistema ordenados pelo
     * quesito OBJETIVOS
     */
    private String ordenaObjetivo() {
        List<Pesquisa> comObjetivo = new ArrayList<Pesquisa>();
        List<Pesquisa> semObjetivo = new ArrayList<Pesquisa>();

        for (Pesquisa p : this.pesquisas.values()) {
            if (p.hasObjetivo() == false) {
                semObjetivo.add(p);
            } else {
                comObjetivo.add(p);
            }
        }
        Comparator<Pesquisa> comparadorObjetivo = new OrdenaPesquisaObjetivo();
        Comparator<Pesquisa> comparadorPesquisa = new OrdenaPesquisaID();
        Collections.sort(comObjetivo, comparadorObjetivo);
        Collections.sort(semObjetivo, comparadorPesquisa);
        String listagem = comObjetivo.get(0).toString();
        for (int i = 1; i < comObjetivo.size(); i++) {
            listagem += " | " + comObjetivo.get(i);
        }
        for (int i = 0; i < semObjetivo.size(); i++) {
            listagem += " | " + semObjetivo.get(i);
        }
        return listagem;
    }

    /**
     * Ordena as pesquisas do sistema por ordem do Problema de maior ID para o menor
     *
     * @return String com listagem de objetos Pesquisa do sistema ordenados pelo
     * quesito PROBLEMA
     */
    private String ordenaProblema() {
        List<Pesquisa> comProblema = new ArrayList<Pesquisa>();
        List<Pesquisa> semProblema = new ArrayList<Pesquisa>();
        for (Pesquisa p : this.pesquisas.values()) {
            if (p.getProblema() == null) {
                semProblema.add(p);
            } else {
                comProblema.add(p);
            }
        }
        Comparator<Pesquisa> comparadorProblema = new OrdenaPesquisaProblema();
        Comparator<Pesquisa> comparadorPesquisa = new OrdenaPesquisaID();
        Collections.sort(comProblema, comparadorProblema);
        Collections.sort(semProblema, comparadorPesquisa);
        String listagem = comProblema.get(0).toString();
        for (int i = 1; i < comProblema.size(); i++) {
            listagem += " | " + comProblema.get(i);
        }
        for (int i = 0; i < semProblema.size(); i++) {
            listagem += " | " + semProblema.get(i);
        }
        return listagem;
    }

    /**
     * Ordena as pesquisas do sistema por ordem da de maior ID para o menor
     *
     * @return String com listagem de objetos Pesquisa do sistema ordenados pelo
     * quesito PESQUISA
     */
    private String ordenaPesquisa() {
        List<Pesquisa> lista = new ArrayList<Pesquisa>();
        for (Pesquisa p : this.pesquisas.values()) {
            lista.add(p);
        }
        Comparator<Pesquisa> comparador = new OrdenaPesquisaID();
        Collections.sort(lista, comparador);
        String listagem = lista.get(0).toString();
        for (int i = 1; i < lista.size(); i++) {
            listagem += " | " + lista.get(i).toString();
        }
        return listagem;
    }

    /**
     * Metodo que permite a associacao de uma atividade a uma pesquisa.
     *
     * @param codigoPesquisa Codigo da Pesquisa
     * @param atividade      Atividade a ser associada
     * @return valor booleando indicando se a associacao foi bem sucedida ou nao.
     */
    public boolean associaAtividade(String codigoPesquisa, Atividade atividade) {
        super.validaStatus(this.pesquisaEhAtiva(codigoPesquisa), "Pesquisa desativada.");
        super.hasValor(this.containsPesquisa(codigoPesquisa), "Pesquisa nao encontrada.");
        return this.pesquisas.get(codigoPesquisa).associaAtividade(atividade);
    }

    /**
     * Metodo que permite a desassociacao de uma atividade que ja foi associada a
     * uma pesquisa.
     *
     * @param codigoPesquisa  Codigo da pesquisa
     * @param codigoAtividade codigo da atividade
     * @return valor booleando indicando se a desassociacao foi bem sucedida ou nao.
     */
    public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
        super.validaStatus(this.pesquisaEhAtiva(codigoPesquisa), "Pesquisa desativada.");
        super.hasValor(this.containsPesquisa(codigoPesquisa), "Pesquisa nao encontrada.");
        return pesquisas.get(codigoPesquisa).desassociaAtividade(codigoAtividade);
    }

    /**
     * Metodo que verifica se existe determinada atividade associada a uma pesquisa.
     *
     * @param codigoAtividade Codigo da atividade.
     * @return valor booleando indicando se a atividade esta associada a alguma
     * pesquisa ou nao.
     */
    public boolean contemAtividadeAssociada(String codigoAtividade) {
        for (Pesquisa pesquisa : pesquisas.values()) {
            if (pesquisa.contemAtividadeAssociada(codigoAtividade)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo responsavel por salvar a collection que contem as pesquisas, os problemas associados e
     * os objetivos associados.
     */
    public void salvarArquivos() {
        Arquivo arquivo = new Arquivo();
        arquivo.salvarArquivos(this.pesquisas, "Pesquisas", "Dados");
        arquivo.salvarArquivos(this.problemasAssociados, "Problemas Associados", "Dados");
        arquivo.salvarArquivos(this.objetivosAssociados, "Objetivos Associados", "Dados");
    }

    /**
     * Metodo responsavel por recuperar a collection que contem as pesquisas, os problemas associados e
     * os objetivos associados, de uma utilizacao anterior.
     */
    public void carregarArquivos() {
        Arquivo arquivo = new Arquivo();
        this.pesquisas = (TreeMap<String, Pesquisa>) arquivo.carregarArquivos("Pesquisas", "Dados");
        this.problemasAssociados = (HashMap<String, String>) arquivo.carregarArquivos("Problemas Associados", "Dados");
        this.objetivosAssociados = (HashMap<String, String>) arquivo.carregarArquivos("Objetivos Associados", "Dados");
    }

    /**
     * Altera o tipo apontado pelo email de um Pesquisador para Professor no sistema
     *
     * @param email     String que identifica unicamente um Professor/Pesquisador
     * @param professor novo objeto a ser apontado pelo email
     */
    public void alteraPesquisadorProfessor(String email, Professor professor) {
        for (Pesquisa p : this.pesquisas.values()) {
            if (p.containsPesquisador(email)) {
                p.alteraPesquisadorProfessor(email, professor);
            }
        }
    }

    /**
     * Altera o tipo apontado pelo email de um Pesquisador para Aluno no sistema
     *
     * @param email String que identifica unicamente um Aluno/Pesquisador
     */
    public void alteraPesquisadorAluno(String email, Aluno aluno) {
        for (Pesquisa p : this.pesquisas.values()) {
            if (p.containsPesquisador(email)) {
                p.alteraPesquisadorAluno(email, aluno);
            }
        }
    }

    /**
     * Acessa um objeto Pesquisa indicado pelo seu ID, pega as informacoes relativas
     * aos seus resultados e grava em um arquivo de texto armazenado na raiz do
     * projeto. O arquivo criado tem o seguinte modelo: "IDPesquisa-Resultados.txt"
     *
     * @param id String que identifica unicamente um objeto Pesquisa
     * @throws IOException em caso de falhas, uma exceção de entrada e saida será
     *                     lançada
     */
    public void gravarResultadosPesquisa(String id) throws IOException {
        super.validaString(id, "Pesquisa nao pode ser nula ou vazia.");
        super.hasValor(this.pesquisas.containsKey(id), "Pesquisa nao encontrada.");
        String resultados = this.pesquisas.get(id).getResultados();
        File file = new File(id + "-Resultados" + ".txt");
        FileWriter writer = new FileWriter(file);
        writer.write(resultados);
        writer.close();
    }

    /**
     * Acessa um objeto Pesquisa indicado pelo seu ID, pega as informacoes que
     * caracterizam um resumo e grava em um arquivo de texto armazenado na raiz do
     * projeto. O arquivo criado tem o seguinte modelo: "IDPesquisa.txt"
     *
     * @param id String que identifica unicamente um objeto Pesquisa
     * @throws IOException em caso de falhas, uma exceção de entrada e saida será
     *                     lançada
     */
    public void gravarResumoPesquisa(String id) throws IOException {
        super.validaString(id, "Pesquisa nao pode ser nula ou vazia.");
        super.hasValor(this.pesquisas.containsKey(id), "Pesquisa nao encontrada.");
        String resumo = this.pesquisas.get(id).getResumo();
        File file = new File("_" + id + ".txt");
        FileWriter writer = new FileWriter(file);
        writer.write(resumo);
        writer.close();
    }

    /**
     * Metodo que configura uma estrategia de deteccao da proxima atividade com itens pendentes.
     * Podendo ser MAIS_ANTIGA, MAIOR_DURACAO, MAIOR_RISCO e MENOS_PENDENCIAS.
     *
     * @param estrategia Estrategia a ser configurada.
     */
    public void configuraEstrategia(String estrategia) {
        super.validaString(estrategia, "Estrategia nao pode ser nula ou vazia.");
        List valores = new ArrayList<String>();
        valores.add("MAIS_ANTIGA");
        valores.add("MAIOR_DURACAO");
        valores.add("MAIOR_RISCO");
        valores.add("MENOS_PENDENCIAS");
        super.validaValoresPermitidos(valores, estrategia, "Valor invalido da estrategia");
        this.estrategia = estrategia;
    }

    /**
     * Metodo que retorna qual a proxima atividade sugerida de acordo com a estrategia a ser utilizada.
     *
     * @param codigoPesquisa Codigo da pesquisa que possui a atividade a ser retornada.
     * @return String contendo o ID da proxima atividade de acordo com a estrategia de ordenacao.
     */
    public String proximaAtividade(String codigoPesquisa) {
        super.validaString(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
        super.validaStatus(pesquisaEhAtiva(codigoPesquisa), "Pesquisa desativada.");
        super.hasValor(containsPesquisa(codigoPesquisa), "Pesquisa nao encontrada.");
        switch (this.estrategia) {
            case "MAIS_ANTIGA":
                return this.pesquisas.get(codigoPesquisa).hasItemPendente();
            case "MAIOR_DURACAO":
                return this.pesquisas.get(codigoPesquisa).ordenaAtividadesMaiorDuracao();
            case "MAIOR_RISCO":
                return this.pesquisas.get(codigoPesquisa).getMaiorRisco();
            case "MENOS_PENDENCIAS":
                return this.pesquisas.get(codigoPesquisa).ordenaAtividadesMenosPendencias();
        }
        return null;
    }
}