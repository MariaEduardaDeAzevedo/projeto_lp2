package base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import comparators.OrdenaAtvdsMaiorDuracao;
import comparators.OrdenaAtvdsMenosPendencias;
import validacao.Validacao;

/**
 * Representacao de uma Pesquisa, que contem um codigo, descricao, campo de
 * interesse e status.
 */
public class Pesquisa extends Validacao implements Serializable {

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
     * Mapa de pesquisadores associados a uma pesquisa.
     */
    private Map<String, Pesquisador> pesquisadoresAssociados;

    /**
     * Armazena as atividades que foram associadas a uma pesquisa.
     */
    private Map<String, Atividade> atividadesAssociadas;

    /**
     * Problema associado a uma pesquisa.
     */
    private Problema problema;

    /**
     * Mapa de objetivos associados a uma pesquisa.
     */
    private Map<String, Objetivo> objetivos;

    /**
     * Mootivo pelo qual a pesquisa foi desativada.
     */
    private String motivo;

    /**
     * Indica se existem objetivos associados a uma pesquisa.
     */
    private boolean statusObjetivo;

    /**
     * Constroi o objeto Pesquisa a partir dos parametros.
     *
     * @param codigo           codigo da pesquisa.
     * @param descricao        descricao da pesquisa.
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
        this.pesquisadoresAssociados = new LinkedHashMap<String, Pesquisador>();
        this.atividadesAssociadas = new LinkedHashMap<String, Atividade>();
        this.motivo = null;
        this.objetivos = new HashMap<String, Objetivo>();
        this.statusObjetivo = false;
    }

    /**
     * Retorna a representacao da pesquisa.
     *
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
        super.validaStatus(!isAtivada(), "Pesquisa ja ativada.");
        this.ativada = true;
    }

    /**
     * Altera o status da pesquisa para desativada.
     *
     * @param motivo
     */
    public void encerraPesquisa(String motivo) {
        super.validaString(motivo, "Motivo nao pode ser nulo ou vazio.");
        super.validaStatus(this.ativada, "Pesquisa desativada.");
        this.ativada = false;
        this.motivo = motivo;
    }

    /**
     * Altera alguns atributos da pesquisa.
     *
     * @param conteudoASerAlterado atributo que se deseja alterar (permitido alterar
     *                             a descricao ou o campo de interesse).
     * @param novoConteudo         conteudo para qual o atributo deve ser alterado.
     */
    public void alteraPesquisa(String conteudoASerAlterado, String novoConteudo) {
        super.validaStatus(this.ativada, "Pesquisa desativada.");
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
     *
     * @return boolean representando se a pesquisa esta ativada ou nao.
     */
    public boolean isAtivada() {
        return ativada;
    }

    /**
     * Compara se duas pesquisas sao iguais.
     *
     * @param o pesquisa com qual se deseja comparar.
     * @return boolean confirmando se as pesquisas sao iguais ou nao.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Pesquisa pesquisa = (Pesquisa) o;
        return Objects.equals(codigo, pesquisa.codigo);
    }

    /**
     * Gera um codigo unico da Pesquisa a partir do seu atributo Codigo.
     *
     * @return int representando o codigo unico da pesquisa.
     */
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    /**
     * Associa o problema a pesquisa.
     *
     * @param problema problema que se deseja associar com a pesquisa.
     */
    public void setProblema(Problema problema) {
        this.problema = problema;
    }

    /**
     * Adiciona objetivo na pesquisa.
     *
     * @param id       ID do objetivo que se deseja adicionar.
     * @param objetivo objetivo que se deseja adicionar.
     */
    public void addObjetivo(String id, Objetivo objetivo) {
        this.objetivos.put(id, objetivo);
        this.statusObjetivo = true;
    }

    /**
     * Retorna o problema com qual a atividade foi associada.
     *
     * @return Problema que a atividade foi associada.
     */
    public Problema getProblema() {
        return this.problema;
    }

    /**
     * Associa um pesquisador a esta pesquisa. Retorna um valor booleano que indica
     * se a associação foi bem sucedida ou não.
     *
     * @param associado pesquisador que se quer associar a esta pesquisa.
     * @return true, caso a associação tenha sido bem sucedida, ou seja, se o
     * pesquisador já não estiver associado à esta pesquisa, ou false, caso
     * a associação não seja bem sucedida, ou seja, se o pesquisador já
     * esteja associado a esta pesquisa.
     */
    public boolean associaPesquisador(Pesquisador associado) {
        super.validaStatus(this.ativada, "Pesquisa desativada.");
        if (pesquisadoresAssociados.containsKey(associado.getEmail())) {
            return false;
        }
        pesquisadoresAssociados.put(associado.getEmail(), associado);
        return true;
    }

    /**
     * Desassocia um pesquisador a esta pesquisa. Retorna um valor booleano que
     * indica se a desassociação foi bem sucedida ou não.
     *
     * @param emailPesquisador email do pesquisador que se quer desassociar desta
     *                         pesquisa.
     * @return true, caso a desassociação seja bem sucedida, ou seja, se o email
     * passado como parâmetro identificar um pesquisador associado a esta
     * pesquisa e este sendo removido, ou false, caso a desassociação não
     * seja bem sucedida, ou seja, se o email passado como parâmetro não
     * identificar nenhum pesquisador associado a esta pesquisa.
     */
    public boolean desassociaPesquisador(String emailPesquisador) {
        super.validaString(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
        super.validaStatus(this.ativada, "Pesquisa desativada.");
        if (!pesquisadoresAssociados.containsKey(emailPesquisador)) {
            return false;
        }
        pesquisadoresAssociados.remove(emailPesquisador);
        return true;
    }

    /**
     * Busca se um termo esta contido na descricao.
     *
     * @param termo termo que se deseja procurar.
     * @return String contendo o codigo e a descricao da pesquisa, caso o termo seja
     * mencionado, se nao, o objeto null é retornado.
     */
    public String buscaTermoDescricao(String termo) {
        if (this.descricao.toLowerCase().contains(termo.toLowerCase())) {
            return this.codigo + ": " + this.descricao;
        }
        return null;
    }

    /**
     * Busca se um termo esta contido no campo de interesse.
     *
     * @param termo termo que se deseja procurar.
     * @return String contendo o codigo e o campo de interesse da pesquisa, caso o
     * termo seja mencionado, se nao, o objeto null é retornado.
     */
    public String buscaTermoCampoDeInteresse(String termo) {
        if (this.campoDeInteresse.toLowerCase().contains(termo.toLowerCase())) {
            return this.codigo + ": " + this.campoDeInteresse;
        }
        return null;
    }

    /**
     * Metodo que permite a associacao de uma atividade a uma Pesquisa.
     *
     * @param atividade Atividade a ser associada
     * @return valor booleano indicando se a associacao foi bem sucedida ou nao.
     */
    public boolean associaAtividade(Atividade atividade) {
        if (atividadesAssociadas.containsKey(atividade.getId())) {
            return false;
        }
        this.atividadesAssociadas.put(atividade.getId(), atividade);
        return true;
    }

    /**
     * Metodo que permite a desassociacao de uma atividade associada a uma Pesquisa.
     *
     * @param codigoAtividade Codigo da atividade a ser desassociada
     * @return valor booleano indicando se a desassociacao foi bem sucedida ou nao.
     */
    public boolean desassociaAtividade(String codigoAtividade) {
        if (!atividadesAssociadas.containsKey(codigoAtividade)) {
            return false;
        }
        this.atividadesAssociadas.remove(codigoAtividade);
        return true;
    }

    /**
     * Retorna um valor booleano que indica se a pesquisa possui um pesquisador
     * associado ou não.
     *
     * @param emailPesquisador email que identifica o pesquisador.
     * @return true, caso o email passado como parâmetro identifique um pesquisador
     * associado à pesquisa, ou false, caso o email não identifique nenhum
     * pesquisador associado à pesquisa.
     */
    public boolean containsPesquisador(String emailPesquisador) {
        if (pesquisadoresAssociados.containsKey(emailPesquisador)) {
            return true;
        }
        return false;
    }

    /**
     * Metodo que verifica se existe determinada atividade associada a alguma
     * pesquisa.
     *
     * @param codigoAtividade Codigo da atividade a ser verificada.
     * @return valor booleano indicando se a atividade esta associada ou nao.
     */
    public boolean contemAtividadeAssociada(String codigoAtividade) {
        return atividadesAssociadas.containsKey(codigoAtividade);
    }

    /**
     * Retorna todos os pesquisadores que foram associados com a pesquisa.
     *
     * @return Collection contendo todos os pesquisadores associados com a pesquisa.
     */
    public Collection<Pesquisador> getPesquisadoresAssociados() {
        return this.pesquisadoresAssociados.values();
    }

    /**
     * Gera e retorna uma String com os resultados da Pesquisa
     *
     * @return String com os resultados da Pesquisa
     */
    public String getResultados() {
        String resultado = "- Pesquisa: " + this.toString();
        resultado += System.lineSeparator() + "	- Resultados:";
        for (Atividade a : this.atividadesAssociadas.values()) {
            resultado += a.toStringResultado();
        }
        return resultado;
    }

    /**
     * Remove um Objetivo indicado pelo ID do mapa de objetivos associados
     *
     * @param idObjetivo String que identifica unicamente um objeto Objetivo
     */
    public void removeObjetivo(String idObjetivo) {
        this.objetivos.remove(idObjetivo);
        if (this.objetivos.size() == 0) {
            this.statusObjetivo = false;
        }
    }

    /**
     * Retorna o valor do atributo statusObjetivo
     *
     * @return boolean referente ao valor do atributo statusObjetivo
     */
    public boolean hasObjetivo() {
        return this.statusObjetivo;
    }

    /**
     * Avalia os objetivos associados e retorna o maior ID encontrado
     *
     * @return String com maior ID de um objetivo associado
     */
    public String getMaiorIDObjetivo() {
        List<String> lista = new ArrayList<String>();
        for (String s : this.objetivos.keySet()) {
            lista.add(s);
        }
        Collections.sort(lista);
        return lista.get(lista.size() - 1);
    }

    /**
     * Gera e retorna um resumo do objeto Pesquisa em String
     *
     * @return String com resumo do objeto Pesquisa
     */
    public String getResumo() {
        String resumo = "- Pesquisa: " + this.toString();
        resumo += System.lineSeparator() + "	- Pesquisadores:";
        for (Pesquisador p : this.pesquisadoresAssociados.values()) {
            resumo += System.lineSeparator() + "		- " + p.toString();
        }
        resumo += System.lineSeparator() + "	- Problema:";
        if (this.problema != null) {
            resumo += System.lineSeparator() + "		- " + this.problema.getId() + " - " + this.problema.toString();
        }
        resumo += System.lineSeparator() + "	- Objetivos:";
        for (Objetivo o : this.objetivos.values()) {
            resumo += System.lineSeparator() + "		- " + o.toString();
        }
        resumo += System.lineSeparator() + "	- Atividades:";
        for (Atividade a : this.atividadesAssociadas.values()) {
            resumo += a.toStringResumo();
        }
        return resumo;
    }

    /**
     * Altera o tipo de um pesquisador para Professor em caso de especializacao.
     *
     * @param email     String que indica o email unico de cada pesquisador
     * @param professor Objeto Professor que indica o novo objeto a ser apontado
     *                  pelo identificador unico
     */
    public void alteraPesquisadorProfessor(String email, Professor professor) {
        this.pesquisadoresAssociados.remove(email);
        this.pesquisadoresAssociados.put(email, professor);
    }

    /**
     * Altera o tipo de um pesquisador para Aluno em caso de especializacao.
     *
     * @param email String que indica o email unico de cada pesquisador
     * @param aluno Objeto Aluno que indica o novo objeto a ser apontado
     *              pelo identificador unico
     */
    public void alteraPesquisadorAluno(String email, Aluno aluno) {
        this.pesquisadoresAssociados.remove(email);
        this.pesquisadoresAssociados.put(email, aluno);
    }

    /**
     * Metodo que verifica se uma atividade possui itens pendentes.
     *
     * @return Id da primeira atividade encontrada com item pendente ou excecao indicando que a atividade
     * nao tem pendencias.
     */
    public String hasItemPendente() {
        for (Atividade atividade : atividadesAssociadas.values()) {
            if (atividade.hasItemPendente()) {
                return atividade.getId();
            }
        }
        throw new IllegalArgumentException("Pesquisa sem atividades com pendencias.");
    }

    /**
     * Metodo que ordena atividades de acordo com o menor numero de pendencias.
     *
     * @return Id da atividade com menor numero de pendencias.
     */
    public String ordenaAtvdsMenosPendencias() {
        List<Atividade> lista = new ArrayList<Atividade>();
        for (Atividade atividade : this.atividadesAssociadas.values()) {
            if (atividade.hasItemPendente())
                lista.add(atividade);
        }
        Collections.sort(lista, new OrdenaAtvdsMenosPendencias());
        return lista.get(0).getId();
    }

    /**
     * Metodo que ordena atividades de acordo com a maior duracao de execucao.
     *
     * @return Id da atividade que teve a maior duracao.
     */
    public String ordenaAtvdsMaiorDuracao() {
        List<Atividade> lista = new ArrayList<Atividade>();
        for (Atividade atividade : this.atividadesAssociadas.values()) {
            if (atividade.hasItemPendente())
                lista.add(atividade);
        }
        Collections.sort(lista, new OrdenaAtvdsMaiorDuracao());
        return lista.get(0).getId();
    }

    /**
     * Retorna a atividade de maior risco associada a pesquisa.
     *
     * @return String contendo o ID da atividade com o maior risco associado a pesqusa.
     */
    public String getMaiorRisco() {
        List<String> lista1 = new ArrayList<>();
        List<String> lista2 = new ArrayList<>();
        List<String> lista3 = new ArrayList<>();
        List<String> lista4 = new ArrayList<>();
        for (Atividade atividade : this.atividadesAssociadas.values()) {
            if (atividade.getRisco().equals("ALTO")) {
                lista1.add(atividade.getId());
            } else if (atividade.getRisco().equals("MEDIO")) {
                lista2.add(atividade.getId());
            } else if (atividade.getRisco().equals("BAIXO")) {
                lista3.add(atividade.getId());
            }
        }
        lista4.addAll(lista1);
        lista4.addAll(lista2);
        lista4.addAll(lista3);
        return lista4.get(0);
    }
}