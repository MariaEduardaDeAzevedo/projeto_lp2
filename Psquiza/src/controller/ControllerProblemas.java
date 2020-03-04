package controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import base.Arquivo;
import base.Problema;
import validacao.Validacao;

/**
 * Classe controller de Problema, que armazena todos os problemas cadastrados no sistema e realiza todos as operacoes relacionadas
 * aos problemas.
 */
public class ControllerProblemas extends Validacao {
    /**
     * Mapa que tem como chave uma String, que corresponde a um codigo que identifica unicamente um Problema no sistema, e tem como valor
     * um Problema. Esta colecao armazena todos os problemas cadastrados no sistema.
     */
    private Map<String, Problema> problemas;
    /**
     * Numero utilizado para gerar os codigos que identificam unicamente os problemas no sistema.
     */
    private int idNumber;

    /**
     * Constroi um novo controller de Problema que, por padrao, começa sem nenhum Problema cadastrado e o valor do numero
     * utilizado para gerar o codigo dos problemas cadastrados no sistema começa como sendo 1.
     */
    public ControllerProblemas() {
        this.problemas = new TreeMap<String, Problema>();
        this.idNumber = 1;
    }

    /**
     * Cadastra um novo problema no sistema a partir de uma descricao e de um valor inteiro que vai de 1 a 5 correspondente a
     * viabilidade do problema.
     * Todo problema cadastrado no sistema sera identificado por um codigo no formato "P" + id gerado automaticamente (a partir de 1).
     *
     * @param descricao   descricao do problema
     * @param viabilidade valor inteiro que pode ir de 1 a 5 e corresponde a viabilidade do problema
     */
    public void cadastraProblema(String descricao, int viabilidade) {
        super.validaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
        super.validaValor(viabilidade, "Valor invalido de viabilidade.");
        String idProblema = "P" + idNumber;
        idNumber++;
        Problema novoProblema = new Problema(descricao, viabilidade, idProblema);
        problemas.put(idProblema, novoProblema);
    }

    /**
     * Retorna a representacao textual de determinado problema cadastrado no sistema.
     * A representacao textual de um problema e retornada no formato "CODIGO DO PROBLEMA - DESCRICAO - VIABILIDADE".
     *
     * @param idProblema codigo que identifica unicamente o problema no sistema
     * @return String que corresponde a representacao textual de determinado problema cadastrado no sistema
     */
    public String exibeProblema(String idProblema) {
        super.validaString(idProblema, "id do problema não pode ser vazio ou nulo");
        super.hasValor(this.problemas.containsKey(idProblema), "Problema nao encontrado");
        return idProblema + " - " + problemas.get(idProblema).toString();
    }

    /**
     * Remove um problema cadastrado do sistema atraves de seu codigo de identificacao.
     *
     * @param idProblema codigo que identifica o problema unicamente no sistema.
     */
    public void apagarProblema(String idProblema) {
        super.validaString(idProblema, "Campo codigo nao pode ser nulo ou vazio.");
        super.hasValor(this.problemas.containsKey(idProblema), "Problema nao encontrado");
        problemas.remove(idProblema);
    }

    /**
     * Retorna um objeto do tipo Problema cadastrado no sistema.
     *
     * @param id id do problema que se quer retornar.
     * @return objeto do tipo Problema que esta cadastrado no sistema.
     */
    public Problema getProblema(String id) {
        return this.problemas.get(id);
    }

    /**
     * Retorna os valores do mapa de problemas.
     *
     * @return Collection contendo todos os objetos do tipo Problema cadastrados no sistema.
     */
    public Collection<Problema> getProblemas() {
        return this.problemas.values();
    }

    /**
     * Metodo responsavel por salvar a collection que contem os problemas e o numero do
     * proximo ID para cadastro de um problema.
     */
    public void salvarArquivos() {
        Arquivo arquivo = new Arquivo();
        arquivo.salvarArquivos(this.problemas, "Problemas", "Dados");
        arquivo.salvarArquivoInt(this.idNumber, "Proximo ID dos Problemas", "Dados");
    }

    /**
     * Metodo responsavel por recuperar a collection que contem os problemas e o numero
     * do proximo ID para cadastro de um problema, de uma utilizacao anterior.
     */
    public void carregarArquivos() {
        Arquivo arquivo = new Arquivo();
        this.problemas = (TreeMap<String, Problema>) arquivo.carregarArquivos("Problemas", "Dados");
        this.idNumber = arquivo.carregarArquivoInt("Proximo ID dos Problemas", "Dados");
    }
}
