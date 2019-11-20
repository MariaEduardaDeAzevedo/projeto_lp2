package base;

import java.io.Serializable;

import validacao.Validacao;

/**
 * Representacao de um problema de um problema de uma pesquisa.
 * Todo problema contem uma descricao e um valor inteiro que vai de 1 a 5 corresponde a viabilidade.
 */
public class Problema extends Validacao implements Serializable {
    /**
     * Descricao do problema.
     */
    private String descricao;
    /**
     * Inteiro que pode ser qualquer valor de 1 a 5 e representa a viabilidade da resolucao do problema
     */
    private int viabilidade;
    /**
     * id que identifica um problema unicamente.
     * Todo id de um problema tem o formato "Pn", em que n e um numero inteiro.
     */
    private String id;

    /**
     * Constroi um Problema a partir de uma descricao e de um valor inteiro que corresponde a viabilidade.
     *
     * @param descricao   descricao do problema
     * @param viabilidade valor inteiro que pode ir de 1 a 5 e corresponde a viabilidade
     * @param id          id que identifica unicamente um problema
     */
    public Problema(String descricao, int viabilidade, String id) {
        super.validaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
        super.validaValor(viabilidade, "Valor invalido de viabilidade.");
        super.validaString(id, "id nao pode ser nulo ou vazio");
        this.descricao = descricao;
        this.viabilidade = viabilidade;
        this.id = id;
    }

    /**
     * Retorna um inteiro que representa um Problema.
     * O criterio utilizado para gerar este inteiro e o id do problema.
     *
     * @return numero inteiro que representa um problema
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * Retorna um valor booleano que indica se um Problema e igual a outro passado como parametro do metodo.
     * Dois problemas sao iguais quando estes tem o mesmo id.
     *
     * @return true, se o problema for igual ao problema passado como parametro, ou false, se o problema for diferente
     * do problema passado como metodo
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Problema other = (Problema) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    /**
     * Retorna a representacao textual de um Problema no formato "DESCRICAO - VIABILIDADE".
     *
     * @return String que corresponde a representacao textual de um Problema
     */
    @Override
    public String toString() {
        return descricao + " - " + viabilidade;
    }

    /**
     * Busca se um termo esta contido na descricao.
     *
     * @param termo termo que se deseja procurar.
     * @return String contendo o id e a descricao do problema, caso o termo seja mencionado, se nao, o objeto null e retornado.
     */
    public String buscaTermo(String termo) {
        if (this.descricao.toLowerCase().contains(termo.toLowerCase())) {
            return this.id + ": " + this.descricao;
        }
        return null;
    }

    /**
     * Retorna o ID que identifica unicamente um objeto Problema
     *
     * @return String com ID que representa unicamente objeto Problema
     */
    public String getId() {
        return this.id;
    }
}