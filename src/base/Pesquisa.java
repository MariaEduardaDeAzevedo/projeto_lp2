package base;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import controller.Validacao;
import excecoes.ActivationException;

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
    
    private Map<String, Pesquisador> pesquisadoresAssociados;
    
    /**
     * Armazena as atividades que foram associadas a alguma pesquisa.
     */
    private Map<String, Atividade> atividadesAssociadas;
    
    private Problema problema;
    
    private Objetivo objetivo;

	private String motivo;
    

    
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
        this.pesquisadoresAssociados = new HashMap<String, Pesquisador>();
        this.atividadesAssociadas = new HashMap<String, Atividade>();
        this.motivo = null;
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
    	super.validaStatus(! isAtivada(), "Pesquisa ja ativada.");
        this.ativada = true;
    }

    /**
     * Altera o status da pesquisa para desativada.
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
     * @param conteudoASerAlterado atributo que se deseja alterar (permitido alterar a descricao ou o campo de interesse).
     * @param novoConteudo conteudo para qual o atributo deve ser alterado.
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
     * @return boolean representando se a pesquisa esta ativada ou nao.
     */
    public boolean isAtivada() {
        return ativada;
    }

    /**
     * Compara se duas pesquisas sao iguais.
     * @param o pesquisa com qual se deseja comparar.
     * @return boolean confirmando se as pesquisas sao iguais ou nao.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pesquisa pesquisa = (Pesquisa) o;
        return Objects.equals(codigo, pesquisa.codigo);
    }

    /**
     * Gera um codigo unico da Pesquisa a partir do seu atributo Codigo.
     * @return int representando o codigo unico da pesquisa.
     */
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
    
    public void setProblema(Problema problema) {
    	
    	this.problema = problema;
    	
    }
    
    public void setObjetivo(Objetivo objetivo) {
    	
    	this.objetivo = objetivo;
    	
    }

	public Problema getProblema() {
		
		return this.problema;
	
	}
	
	/**
	 * Associa um pesquisador a esta pesquisa.
	 * Retorna um valor booleano que indica se a associação foi bem sucedida ou não.
	 * @param associado pesquisador que se quer associar a esta pesquisa.
	 * @return true, caso a associação tenha sido bem sucedida, ou seja, se o pesquisador já não estiver associado à esta pesquisa, ou false, caso a associação
	 * não seja bem sucedida, ou seja, se o pesquisador já esteja associado a esta pesquisa.
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
	 * Desassocia um pesquisador a esta pesquisa.
	 * Retorna um valor booleano que indica se a desassociação foi bem sucedida ou não.
	 * @param emailPesquisador email do pesquisador que se quer desassociar desta pesquisa.
	 * @return true, caso a desassociação seja bem sucedida, ou seja, se o email passado como parâmetro identificar um pesquisador associado
	 * a esta pesquisa e este sendo removido, ou false, caso a desassociação não seja bem sucedida, ou seja, se o email passado como parâmetro não identificar
	 * nenhum pesquisador associado a esta pesquisa.
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
     * @param termo termo que se deseja procurar.
     * @return String contendo o codigo e a descricao da pesquisa, caso o termo seja mencionado, se nao, o objeto null é retornado.
     */
    public String buscaTermoDescricao(String termo) {
        if (this.descricao.toLowerCase().contains(termo.toLowerCase())) {
            return this.codigo + ": " + this.descricao;
        }
        return null;
    }

    /**
     * Busca se um termo esta contido no campo de interesse.
     * @param termo termo que se deseja procurar.
     * @return String contendo o codigo e o campo de interesse da pesquisa, caso o termo seja mencionado, se nao, o objeto null é retornado.
     */
    public String buscaTermoCampoDeInteresse(String termo) {
        if (this.campoDeInteresse.toLowerCase().contains(termo.toLowerCase())) {
            return this.codigo + ": " + this.campoDeInteresse;
        }
        return null;
    }

    /**
     * Metodo que permite a associacao de uma atividade a uma Pesquisa.
     * @param atividade Atividade a ser associada
     * @return valor booleano indicando se a associacao foi bem sucedida ou nao.
     */
	public boolean associaAtividade(Atividade atividade) {
	
		if(atividadesAssociadas.containsKey(atividade.getId())) {
			return false;
		}
		this.atividadesAssociadas.put(atividade.getId(), atividade);
		return true;
	}

	/**
     * Metodo que permite a desassociacao de uma atividade associada a uma Pesquisa.
     * @param atividade Atividade a ser desassociada
     * @return valor booleano indicando se a desassociacao foi bem sucedida ou nao.
     */
	public boolean desassociaAtividade(String codigoAtividade) {
		if(!atividadesAssociadas.containsKey(codigoAtividade)) {
			return false;
		}
		this.atividadesAssociadas.remove(codigoAtividade);
		return true;
	}
	
	/**
	 * Retorna um valor booleano que indica se a pesquisa possui um pesquisador associado ou não.
	 * @param emailPesquisador email que identifica o pesquisador.
	 * @return true, caso o email passado como parâmetro identifique um pesquisador associado à pesquisa, ou false, caso o email
	 * não identifique nenhum pesquisador associado à pesquisa.
	 */
	public boolean containsPesquisador(String emailPesquisador) {
		if (pesquisadoresAssociados.containsKey(emailPesquisador)) {
			return true;
		}
		return false;
	}

	public Objetivo getObjetivo() {
		
		return this.objetivo;
	
	}


	/**
	 * Metodo que verifica se existe determinada atividade associada a alguma pesquisa.
	 * @param codigoAtividade Codigo da atividade a ser verificada.
	 * @return valor booleano indicando se a atividade esta associada ou nao.
	 */
	public boolean contemAtividadeAssociada(String codigoAtividade) {
		return atividadesAssociadas.containsKey(codigoAtividade);
	}


	public Collection<Pesquisador> getPesquisadoresAssociados() {
		return this.pesquisadoresAssociados.values();
	}


	public Collection<Atividade> getAtividadesAssociadas() {	
		return this.atividadesAssociadas.values();
	}


	public String getResultados() {
		
		String resultado = "- " + this.toString() + System.lineSeparator();
		
		resultado += "	- Resultados:" + System.lineSeparator();
		
		for (Atividade a : this.atividadesAssociadas.values()) {
			
			resultado += a.toStringResultado();
			
		}
		
		return resultado;
		
	}
	
}
