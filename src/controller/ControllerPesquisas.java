package controller;
import java.util.*;

import base.Atividade;
import base.Objetivo;
import base.Pesquisa;
import base.Pesquisador;
import base.Problema;
import excecoes.ActivationException;
import comparators.*;

/**
 * Classe Controller responsavel pelos metodos referentes as pesquisas do sistema.
 */
public class ControllerPesquisas extends Validacao {

    /**
     * Armazena um mapa de pesquisas a partir dos seus codigos.
     */
    private Map<String, Pesquisa> pesquisas;

    private Conector conector;
    
    private Map<String, String> problemasAssociados;

	private Map<String, String> objetivosAssociados;
	


	

    /**
     * Constroi o objeto ControllerPesquisas e inicializa seus atributos.
     */
    public ControllerPesquisas() {
        this.pesquisas = new HashMap<String, Pesquisa>();
        this.conector = new Conector();
        this.problemasAssociados = new HashMap<String, String>();
        this.objetivosAssociados = new HashMap<String, String>();

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
    	super.validaString(motivo, "Motivo nao pode ser nulo ou vazio.");
        super.hasValor(pesquisas.containsKey(codigo), "Pesquisa nao encontrada.");
        pesquisas.get(codigo).encerraPesquisa(motivo);
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

    //ADICIONAR METODOS DA US5

    /**
     * Retorna os valores do mapa de pesquisas.
     * @return Collection contendo todos os objetos do tipo Pesquisa cadastrados no sistema.
     */
    public Collection<Pesquisa> getPesquisas() {
        return pesquisas.values();
    }
    
    public boolean containsPesquisa(String idPesquisa) {
    	return pesquisas.containsKey(idPesquisa);
    }
    
    public boolean associaPesquisador(String idPesquisa, Pesquisador associado) {
    	if (!pesquisas.containsKey(idPesquisa)) {
    		throw new NullPointerException("Pesquisa nao encontrada.");
    	}
    	if (!pesquisas.get(idPesquisa).isAtivada()) {
    		throw new ActivationException("Pesquisa desativada.");
    	}
    	if (pesquisas.get(idPesquisa).containsPesquisador(associado.getEmail())) {
    		return false;
    	}
    	return pesquisas.get(idPesquisa).associaPesquisador(associado);
    }
    
    public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
    	super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio." );
    	super.validaString(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
    	if (!pesquisas.containsKey(idPesquisa)) {
    		throw new NullPointerException("Pesquisa nao encontrada.");
    	}
    	if (!pesquisas.get(idPesquisa).isAtivada()) {
    		throw new ActivationException("Pesquisa desativada.");
    	}
    	if (!pesquisas.get(idPesquisa).containsPesquisador(emailPesquisador)) {
    		return false;
    	}
    	return pesquisas.get(idPesquisa).desassociaPesquisador(emailPesquisador);
    }
    
    public boolean containsPesquisador(String idPesquisa, String emailPesquisador) {
    	super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
    	super.validaString(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
    	if (!pesquisas.containsKey(idPesquisa)) {
    		throw new NullPointerException("Pesquisa nao encontrada.");
    	}
    	return pesquisas.get(idPesquisa).containsPesquisador(emailPesquisador);
    }

	public Pesquisa getPesquisa(String idPesquisa) {
		
		return this.pesquisas.get(idPesquisa);
		
	}
	
public String associaProblema(String idPesquisa, String idProblema, Problema problema) {
		
		super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		super.validaString(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		super.hasValor(this.pesquisas.containsKey(idPesquisa), "Pesquisa nao encontrada.");
		super.validaStatus(this.pesquisas.get(idPesquisa).isAtivada(), "Pesquisa desativada.");
		
		try {
			
			super.hasAssociado(idProblema, idPesquisa, this.problemasAssociados, true, "Pesquisa ja associada a um problema.");
			
		} catch (IllegalArgumentException e) {
			
			return "false";
			
		}
		
		this.pesquisas.get(idPesquisa).setProblema(problema);
		this.problemasAssociados.put(idProblema, idPesquisa);
		
		return "true";
		
	}

	public String desassociaProblema(String idPesquisa, String idProblema) {
	
		super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		super.validaString(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		super.hasValor(this.pesquisas.containsKey(idPesquisa), "Pesquisa nao encontrada.");
		super.validaStatus(this.pesquisaEhAtiva(idPesquisa), "Pesquisa desativada.");
		
		try {
			
			super.hasAssociado(idProblema, idPesquisa, this.problemasAssociados, false, "");
			
		} catch (NullPointerException e) {
			
			return "false";
			
		}
		
		this.problemasAssociados.remove(idProblema);
		this.pesquisas.get(idPesquisa).setProblema(null);
		return "true";
	
	}

	public String associaObjetivo(String idPesquisa, String idObjetivo, Objetivo objetivo) {
		
		super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		super.validaString(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		super.hasValor(this.pesquisas.containsKey(idPesquisa), "Pesquisa nao encontrada.");
		super.validaStatus(this.pesquisaEhAtiva(idPesquisa), "Pesquisa desativada.");
		
		try {
			
			super.hasAssociado(idPesquisa, idObjetivo, this.objetivosAssociados, true, "Objetivo ja associado a uma pesquisa.");
			
		} catch (IllegalArgumentException e) {
			
			return "false";
			
		}
		
		this.pesquisas.get(idPesquisa).setObjetivo(objetivo);
		this.objetivosAssociados.put(idPesquisa, idObjetivo);
		
		return "true";
		
	}

	public String desassociaObjetivo(String idPesquisa, String idObjetivo) {
		
		super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		super.validaString(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		super.validaStatus(this.pesquisaEhAtiva(idPesquisa), "Pesquisa desativada.");
		super.hasValor(this.containsPesquisa(idPesquisa), "Pesquisa nao encontrada.");
		
		try {
			
			super.hasAssociado(idPesquisa, idObjetivo, this.objetivosAssociados, false, "");
			
		} catch (NullPointerException e) {
			
			return "false";
			
		}
		
		this.objetivosAssociados.remove(idPesquisa);
		this.pesquisas.get(idPesquisa).setObjetivo(null);
		return "true";
		
	}

	public String listar(String ordem) {
		
		List<String> valores = new ArrayList<String>();
		valores.add("PESQUISA");
		valores.add("PROBLEMA");
		valores.add("OBJETIVOS");
		super.validaValoresPermitidos(valores , ordem, "Valor invalido da ordem");
		
		String lista = "";
		
		if (ordem.equals("PESQUISA")) {
			
			return this.ordenaPesquisa();
			
		} else if (ordem.equals("PROBLEMA")) {
			
			return this.ordenaProblema();
			
		} 			
		
		return this.ordenaObjetivo();
	
	
	}

	private String ordenaObjetivo() {

		List<Pesquisa> comObjetivo = new ArrayList<Pesquisa>();
		List<Pesquisa> semObjetivo = new ArrayList<Pesquisa>();
	
		for (Pesquisa p : this.pesquisas.values()) {
			
			if (p.getObjetivo() == null) {
				
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
		
		for (int i = 1; i < comObjetivo.size(); i ++) {
			
			listagem += " | " + comObjetivo.get(i);
			
		}
		
		for (int i = 0; i < semObjetivo.size(); i ++) {
			
			listagem += " | " + semObjetivo.get(i);
			
		}
		
		return listagem;

		
	}

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
		
		for (int i = 1; i < comProblema.size(); i ++) {
			
			listagem += " | " + comProblema.get(i);
			
		}
		
		for (int i = 0; i < semProblema.size(); i ++) {
			
			listagem += " | " + semProblema.get(i);
			
		}
		
		return listagem;
		
	}

	private String ordenaPesquisa() {
		
		List<Pesquisa> lista = new ArrayList<Pesquisa>();
		
		for (Pesquisa p : this.pesquisas.values()) {
			
			lista.add(p);
			
		}
		
		Comparator<Pesquisa> comparador = new OrdenaPesquisaID(); 
		
		Collections.sort(lista, comparador);
		
		String listagem = lista.get(0).toString();
		
		for (int i = 1 ; i < lista.size(); i ++) {
			
			listagem += " | " + lista.get(i).toString();
			
		}
		
		return listagem;
		
	}

	
	public boolean associaAtividade(String codigoPesquisa, Atividade atividade) {
		super.validaStatus(this.pesquisaEhAtiva(codigoPesquisa), "Pesquisa desativada.");
		super.hasValor(this.containsPesquisa(codigoPesquisa), "Pesquisa nao encontrada.");
		return this.pesquisas.get(codigoPesquisa).associaAtividade(atividade);
	}

	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		super.validaStatus(this.pesquisaEhAtiva(codigoPesquisa), "Pesquisa desativada.");
		super.hasValor(this.containsPesquisa(codigoPesquisa), "Pesquisa nao encontrada.");
		return pesquisas.get(codigoPesquisa).desassociaAtividade(codigoAtividade);
	}

}