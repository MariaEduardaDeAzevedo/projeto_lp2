package controller;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import base.Pesquisa;
import base.Pesquisador;
import base.Problema;
import excecoes.ActivationException;
import excecoes.AssociationException;

/**
 * Classe Controller responsavel pelos metodos referentes as pesquisas do sistema.
 */
public class ControllerPesquisas extends Validacao {

    /**
     * Armazena um mapa de pesquisas a partir dos seus codigos.
     */
    private Map<String, Pesquisa> pesquisas;

    private Conector conector;
    
    private Map<String, String> associadas;

    /**
     * Constroi o objeto ControllerPesquisas e inicializa seus atributos.
     */
    public ControllerPesquisas() {
        this.pesquisas = new HashMap<String, Pesquisa>();
        this.conector = new Conector();
        this.associadas = new HashMap<String, String>();
    }

    /**
     * Cadastra uma pesquisa no sistema a partir dos parametros.
     *
     * @param descricao        descricao da pesquisa que se deseja adicionar.
     * @param campoDeInteresse campo de interesse da pesquisa que se deseja adicionar.
     * @return String, caso o cadastro da pesquisa seja concluído, representando o codigo da pesquisa.
     */
    public String cadastraPesquisa(String descricao, String campoDeInteresse) {
        super.validaString(descricao, "Descricao nao pode ser nula ou vazia.");
        super.validaString(campoDeInteresse, "Formato do campo de interesse invalido.");
        if (campoDeInteresse.length() > 255) {
            throw new IllegalArgumentException("Formato do campo de interesse invalido.");
        }
        String[] topicos = campoDeInteresse.split(",");
        if (topicos.length > 4) {
            throw new IllegalArgumentException("Formato do campo de interesse invalido.");
        }
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
        super.hasValor(pesquisas.containsKey(codigo), "Pesquisa nao encontrada.");
        pesquisas.get(codigo).encerraPesquisa();
    }

    /**
     * Altera a descricao ou campo de interesse de uma pesquisa cadastrada.
     *
     * @param codigo               codigo da pesquisa que se deseja alterar.
     * @param conteudoASerAlterado conteudo da pesquisa que se deseja alterar.
     * @param novoConteudo         conteudo que devera substituir o anteriormente cadastrado na pesquisa.
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


    public String associaProblema(String idPesquisa, String idProblema) {
    	super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
    	super.validaString(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
    	super.hasValor(this.pesquisas.containsKey(idPesquisa), "Pesquisa nao encontrada.");
    	super.validaStatus(this.pesquisas.get(idPesquisa).isAtivada(), "Pesquisa desativada.");

    	try {
    		
    		super.hasProblemaAssociado(idProblema, idPesquisa, this.associadas, true);
    		
    	} catch(IllegalArgumentException e) {
    		

    		super.isProblemaAssociado(this.conector.getProblema(idProblema), this.pesquisas.get(idPesquisa), "Pesquisa ja associada a um problema.");

    	} catch (IllegalArgumentException e) {

    		return "false";

    	}
    		
    	this.pesquisas.get(idPesquisa).setProblema(this.conector.getProblema(idProblema));
    	this.associadas.put(idPesquisa, idProblema);
    	

    	this.pesquisas.get(idPesquisa).setProblema(this.conector.getProblema(idProblema));

    	return "sucesso";

    }

    public boolean desassociaProblema(String idPesquisa, String idProblema) {
    	super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
    	super.validaString(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
    	super.hasValor(this.pesquisas.containsKey(idPesquisa), "Pesquisa nao encontrada.");
    	super.validaStatus(this.pesquisas.get(idPesquisa).isAtivada(), "Pesquisa desativada.");
    	
    	try {
    		
    		super.hasProblemaAssociado(idProblema, idPesquisa, this.associadas, false);
    		
    	} catch(NullPointerException e) {
    		
    		return "false";
    		
    	}
    	
    	this.pesquisas.get(idPesquisa).setProblema(null);

    	this.associadas.put(idPesquisa, idProblema);
    	
    	return "sucesso";

    	return true;


    }

    public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
    	super.hasValor(this.pesquisas.containsKey(idPesquisa), "Pesquisa nao encontrada.");
    	this.pesquisas.get(idPesquisa).setObjetivo(this.conector.getObjetivo(idObjetivo));
    	return true;

    }

    public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
    	super.validaString(idPesquisa, "Nulo ou vazio.");
    	super.validaString(idObjetivo, "Nulo ou vazio.");
    	super.hasValor(this.pesquisas.containsKey(idPesquisa), "Pesquisa nao encontrada.");
    	super.validaStatus(this.pesquisas.get(idPesquisa).isAtivada(), "Pesquisa desativada.");



    	this.pesquisas.get(idPesquisa).setObjetivo(null);

    	return true;

    }

    public List<Pesquisa> getPesquisas() {
        return (List<Pesquisa>) pesquisas.values();
    }
    
    public boolean containsPesquisa(String idPesquisa) {
    	return pesquisas.containsKey(idPesquisa);
    }
    
    public void associaPesquisador(String idPesquisa, Pesquisador associado) {
    	if (!pesquisas.containsKey(idPesquisa)) {
    		throw new NullPointerException("Pesquisa nao encontrada.");
    	}
    	if (pesquisas.get(idPesquisa).isAtivada()) {
    		throw new ActivationException("Pesquisa desativada.");
    	}
    	pesquisas.get(idPesquisa).associaPesquisador(associado);
    }
    
    public void desassociaPesquisador(String idPesquisa, String emailPesquisador) {
    	super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio." );
    	super.validaString(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
    	if (!pesquisas.containsKey(idPesquisa)) {
    		throw new NullPointerException("Pesquisa nao encontrada.");
    	}
    	if (!pesquisas.get(idPesquisa).isAtivada()) {
    		throw new ActivationException("Pesquisa desativada.");
    	}
    	pesquisas.get(idPesquisa).desassociaPesquisador(emailPesquisador);
    }
}