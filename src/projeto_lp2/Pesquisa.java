package projeto_lp2;

/**
 * Representacao de uma Pesquisa, que contem um codigo, descricao, campo de interesse e status.
 */
public class Pesquisa extends Validacao {

    /**
     * Armazena o codigo da pesquisa no sistema.
     */
    private String codigo;

    /**
     * Armazena a descricao da pesquisa.
     */
    private String descricao;

    /**
     * Armazena o campo de interesse da pesquisa.
     */
    private String campoDeInteresse;

    /**
     * Armazena o status da pesquisa.
     */
    private boolean ativada;

    /**
     * Constroi o objeto Pesquisa a partir dos parametros.
     * @param codigo codigo da pesquisa.
     * @param descricao descricao da pesquisa.
     * @param campoDeInteresse campo de interese da pesquisa.
     */
    public Pesquisa(String codigo, String descricao, String campoDeInteresse) {
        super.validaString(codigo, "Codigo nao pode ser nulo ou vazio.");
        super.validaString(descricao, "Descricao nao pode ser nula ou vazia.");
        super.validaString(campoDeInteresse, "Formato do campo de interesse invalido.");
        this.codigo = codigo;
        this.descricao = descricao;
        this.campoDeInteresse = campoDeInteresse;
        this.ativada = true;
    }

    /**
     * Retorna a representacao da pesquisa.
     * @return String contendo a representacao textual de uma pesquisa.
     */
    @Override
    public String toString() {
        return this.codigo + " - " + this.descricao + " - " + this.campoDeInteresse;
    }

    /**
     * Altera o status da pesquisa para ativada.
     */
    public void ativaPesquisa() {
        if (isAtivada()) {
            throw new IllegalArgumentException("Pesquisa ja ativada.");
        }
        this.ativada = true;
    }

    /**
     * Altera o status da pesquisa para desativada.
     */
    public void encerraPesquisa() {
        if (!isAtivada()) {
            throw new IllegalArgumentException("Pesquisa desativada.");
        }
        this.ativada = false;
    }

    /**
     * Altera alguns atributos da pesquisa.
     * @param conteudoASerAlterado atributo que se deseja alterar (permitido alterar a descricao ou o campo de interesse).
     * @param novoConteudo conteudo para qual o atributo deve ser alterado.
     */
    public void alteraPesquisa(String conteudoASerAlterado, String novoConteudo) {
        if (!isAtivada()) {
            throw new IllegalArgumentException("Pesquisa desativada.");
        }
        if (conteudoASerAlterado.equals("DESCRICAO")) {
            super.validaString(novoConteudo, "Descricao nao pode ser nula ou vazia.");
            this.descricao = novoConteudo;
        } else if (conteudoASerAlterado.equals("CAMPO")) {
            super.validaString(novoConteudo, "Formato do campo de interesse invalido.");
            this.campoDeInteresse = novoConteudo;
        } else {
            throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
        }
    }

    /**
     * Retorna o status da pesquisa.
     * @return boolean representando se a pesquisa esta ativada ou nao.
     */
    public boolean isAtivada() {
        return ativada;
    }
}
