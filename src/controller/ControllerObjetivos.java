package controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import base.Objetivo;
import validacao.Validacao;
import base.Arquivo;

/**
 * Classe controller de Objetivo, que armazena todos os objetivos cadastrados no sistema e contem os metodos utilizados pela fachada
 * para realizar operacoes com os objetivos.
 */
public class ControllerObjetivos extends Validacao implements Serializable {
    /**
     * Mapa que armazena todos os objetivos cadastrados no sistema.
     * Neste mapa, a chave e o codigo do objetivo, cujo qual este e identificado unicamente e o valor e um Objetivo.
     * O codigo de todo objetivo cadastrado tem o formato "O" + id gerado automaticamente (a partir de 1).
     */
    private Map<String, Objetivo> objetivos;
    /**
     * inteiro utilizado como id para gerar os codigos que sao utilizados como chave no mapa que armazena os objetivos cadastrados no sistema.
     */
    private int idNumber;

    /**
     * Constroi um controller de Objetivo.
     * Por padrao, todo controller desse tipo começa sem nenhum Objetivo cadastrado e o seu id utilizado para gerar os codigos utilizados como chave
     * no mapa que armazena todos os objetivos começa como sendo 1.
     */
    public ControllerObjetivos() {
        this.objetivos = new TreeMap<String, Objetivo>();
        this.idNumber = 1;
    }

    /**
     * Cadastra um objetivo no sistema a partir de seu tipo (pode ser "GERAL" ou "ESPECIFICO"), sua descricao e mais dois valores
     * que irao corresponder a aderencia e a viabilidade do objetivo.
     * Todo objetivo cadastrado e armazenado no mapa e tem sua chave como sendo seu codigo que o identifica unicamente no sistema, este
     * codigo e gerado pela concatenação de "O" + id gerado automaticamente (a partir de 1).
     *
     * @param tipo        tipo do objetivo a ser cadastrado no sistema (pode ser "GERAL" ou "ESPECIFICO"
     * @param descricao   descricao do objetivo a ser cadastrado no sistema
     * @param aderencia   valor inteiro correspondente a aderencia do objetivo a ser cadastrado no sistema, pode ser qualquer valor no intervalo de 1 a 5
     * @param viabilidade valor inteiro correspondente a viabilidade do objetivo a ser cadastrado no sistema, pode ser qualquer valor no intervalo de 1 a 5
     */
    public void cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
        super.validaString(tipo, "Campo tipo nao pode ser nulo ou vazio.");
        super.validaTipo(tipo, "Valor invalido de tipo.");
        super.validaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
        super.validaValor(aderencia, "Valor invalido de aderencia");
        super.validaValor(viabilidade, "Valor invalido de viabilidade.");
        String idObjetivo = "O" + idNumber;
        idNumber++;
        objetivos.put(idObjetivo, new Objetivo(tipo, descricao, aderencia, viabilidade, idObjetivo));
    }

    /**
     * Exibe a representacao textual de um determinado objetivo cadastrado no sistema.
     * A representacao textual de todo objetivo é dada no formato "CODIGO DO OBJETIVO - TIPO - DESCRICAO - VALOR"
     *
     * @param idObjetivo o codigo que identifica unicamente o objetivo no sistema
     * @return String que corresponde a representacao textual do objetivo
     */
    public String exibeObjetivo(String idObjetivo) {
        super.validaString(idObjetivo, "Codigo do objetivo passado não pode ser vazio ou nulo");
        super.hasValor(this.objetivos.containsKey(idObjetivo), "Objetivo nao encontrado");
        return objetivos.get(idObjetivo).toString();
    }

    /**
     * Apaga um objetivo do sistema, ou seja, retira um objetivo cadastrado no sistema atraves do seu codigo.
     *
     * @param idObjetivo codigo que identifica unicamente o objetivo cadastrado no sistema.
     */
    public void apagarObjetivo(String idObjetivo) {
        super.validaString(idObjetivo, "Campo codigo nao pode ser nulo ou vazio.");
        super.hasValor(this.objetivos.containsKey(idObjetivo), "Objetivo nao encontrado");
        objetivos.remove(idObjetivo);
    }

    /**
     * Retorna um Objetivo cadastrado no mapa de objetivos, a partir do ID recebido como parametro.
     *
     * @param id ID do objetivo que se deseja retornar.
     * @return Objetivo que tinha o ID passado como parametro.
     */
    public Objetivo getObjetivo(String id) {
        return this.objetivos.get(id);
    }

    /**
     * Retorna os valores do mapa de objetivos.
     *
     * @return Collection contendo todos os objetos do tipo Objetivo cadastrados no sistema.
     */
    public Collection<Objetivo> getObjetivos() {
        return this.objetivos.values();
    }

    /**
     * Metodo responsavel por salvar a collection que contem os objetivos e o numero do
     * proximo ID para cadastro de um objetivo.
     */
    public void salvarArquivos() {
        Arquivo arquivo = new Arquivo();
        arquivo.salvarArquivos(this.objetivos, "Objetivos", "Dados");
        arquivo.salvarArquivoInt(this.idNumber, "Proximo ID dos Objetivos", "Dados");
    }

    /**
     * Metodo responsavel por recuperar a collection que contem os objetivos e o numero
     * do proximo ID para cadastro de um objetivo, de uma utilizacao anterior.
     */
    public void carregarArquivos() {
        Arquivo arquivo = new Arquivo();
        this.objetivos = (TreeMap<String, Objetivo>) arquivo.carregarArquivos("Objetivos", "Dados");
        this.idNumber = arquivo.carregarArquivoInt("Proximo ID dos Objetivos", "Dados");
    }
}