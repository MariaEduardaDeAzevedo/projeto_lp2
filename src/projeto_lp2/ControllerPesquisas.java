package projeto_lp2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ControllerPesquisas extends Validacao{

    private Map<String, Pesquisa> pesquisas;

    public ControllerPesquisas(){
        this.pesquisas = new TreeMap<String, Pesquisa>();
    }

    public String cadastraPesquisa(String descricao, String campoDeInteresse) {
        super.validaString(descricao, "Descricao nao pode ser nula ou vazia.");
        super.validaString(campoDeInteresse, "Formato do campo de interesse invalido.");
        if (campoDeInteresse.length() > 255) {
            throw new IllegalArgumentException("Formato do campo de interesse invalido.");
        }
        String[] topicos = campoDeInteresse.split(",");
        if (topicos.length > 4){
            throw new IllegalArgumentException("Formato do campo de interesse invalido.");
        }
        for (String topico : topicos){
            validaString(topico, "Formato do campo de interesse invalido.");
            if (topico.length() < 3){
                throw new IllegalArgumentException("Formato do campo de interesse invalido.");
            }
        }
        String codigo;
        if (pesquisas.containsKey((campoDeInteresse.substring(0, 3) + "1").toUpperCase())){
            codigo = (campoDeInteresse.substring(0, 3) + "2").toUpperCase();
            pesquisas.put(codigo, new Pesquisa(codigo, descricao, campoDeInteresse));
        } else {
            codigo = (campoDeInteresse.substring(0, 3) + "1").toUpperCase();
            pesquisas.put(codigo, new Pesquisa(codigo, descricao, campoDeInteresse));
        }
        return codigo;
    }

    public void ativaPesquisa(String codigo) {
        if (!pesquisas.containsKey(codigo)){
            throw new IllegalArgumentException("Pesquisa nao encontrada.");
        }
        pesquisas.get(codigo).ativaPesquisa();
    }

    public void encerraPesquisa(String codigo, String motivo) {
        super.validaString(codigo, "Codigo nao pode ser nulo ou vazio.");
        if (!pesquisas.containsKey(codigo)){
            throw new IllegalArgumentException("Pesquisa nao encontrada.");
        }
        pesquisas.get(codigo).encerraPesquisa();
    }

    public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
        super.validaString(codigo, "Codigo nao pode ser nulo ou vazio.");
        if (!pesquisas.containsKey(codigo)){
            throw new IllegalArgumentException("Pesquisa nao encontrada.");
        }
        pesquisas.get(codigo).alteraPesquisa(conteudoASerAlterado, novoConteudo);
    }

    public String exibePesquisa(String codigo) {
        super.validaString(codigo, "Codigo nao pode ser nulo ou vazio.");
        if (!pesquisas.containsKey(codigo)){
            throw new IllegalArgumentException("Pesquisa nao encontrada.");
        }
        return pesquisas.get(codigo).toString();
    }

    public boolean pesquisaEhAtiva(String codigo) {
        super.validaString(codigo, "Codigo nao pode ser nulo ou vazio.");
        if (!pesquisas.containsKey(codigo)){
            throw new IllegalArgumentException("Pesquisa nao encontrada.");
        }
        return pesquisas.get(codigo).isAtivada();
    }
}
