package projeto_lp2;

public class Pesquisa extends Validacao {

    private String codigo;
    private String descricao;
    private String campoDeInteresse;
    private boolean ativada;

    public Pesquisa(String codigo, String descricao, String campoDeInteresse) {
        super.validaString(codigo, "Codigo nao pode ser nulo ou vazio.");
        super.validaString(descricao, "Descricao nao pode ser nula ou vazia.");
        super.validaString(campoDeInteresse, "Formato do campo de interesse invalido.");
        this.codigo = codigo;
        this.descricao = descricao;
        this.campoDeInteresse = campoDeInteresse;
        this.ativada = true;
    }

    @Override
    public String toString() {
        return this.codigo + " - " + this.descricao + " - " + this.campoDeInteresse;
    }

    public void ativaPesquisa() {
        if (ativada == true) {
            throw new IllegalArgumentException("Pesquisa ja ativada.");
        }
        this.ativada = true;
    }

    public void encerraPesquisa() {
        if (ativada == false) {
            throw new IllegalArgumentException("Pesquisa desativada.");
        }
        this.ativada = false;
    }

    public void alteraPesquisa(String conteudoASerAlterado, String novoConteudo) {
        if (this.ativada == false) {
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

    public boolean isAtivada() {
        return ativada;
    }
}
