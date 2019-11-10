package controller;

import base.*;
import excecoes.ActivationException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Classe utilizada para juntar a lógica de dois ou mais controllers do sistema.
 * @author Daniel Fonseca
 *
 */
public class Conector extends Validacao {
	/**
	 * Associa um pesquisador cadastrado no sistema (ou qualquer uma de suas especializações) com uma pesquisa, que também deve estar cadastrada no sistema.
	 * @param cPesquisador controller de Pesquisador, que armazena todas os pesquisadores cadastrados no sistema.
	 * @param cPesquisas controller de Pesquisa, que armazena todas as pesquisas cadastradas no sistema.
	 * @param idPesquisa identificador único da pesquisa.
	 * @param emailPesquisador email do pesquisador que se quer associar a pesquisa.
	 * @return um valor booleano que indica se a associação do pesquisador a pesquisa foi realizada com sucesso. Se o pesquisador já estiver associado à pesquisa, é retornado
	 * false, caso contrário, este é associado à pesquisa e é retornado true.
	 */
    public boolean associaPesquisador(ControllerPesquisador cPesquisador, ControllerPesquisas cPesquisas, String idPesquisa, String emailPesquisador) {
        super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        super.validaString(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
        if (!cPesquisas.containsPesquisa(idPesquisa)) {
            throw new NullPointerException("Pesquisa nao encontrada.");
        }
        else if (!cPesquisas.pesquisaEhAtiva(idPesquisa)) {
            throw new ActivationException("Pesquisa desativada.");
        }
        Pesquisador associado = cPesquisador.getPesquisador(emailPesquisador);
        return cPesquisas.associaPesquisador(idPesquisa, associado);
    }
    
    /**
     * Desassocia um pesquisador associado a uma pesquisa cadastrada no sistema.
     * @param cPesquisas controller de Pesquisa, que armazena todas as pesquisas cadastradas no sistema.
     * @param idPesquisa id da pesquisa a que se quer desassociar o pesquisador.
     * @param emailPesquisador email do pesquisador que se quer desassociar da pesquisa.
     * @return retorna um valor booleano que indica se a operação foi realizada com sucesso ou não. É retornado true quando
     * a desassociação é feita com sucesso, e, caso contrário, se o pesquisador não estiver associado à pesquisa, é retornado false, indicando
     * que a desassociação não foi bem sucedida.
     */
    public boolean desassociaPesquisador(ControllerPesquisas cPesquisas, String idPesquisa, String emailPesquisador) {
        super.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        super.validaString(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
        if (!cPesquisas.containsPesquisa(idPesquisa)) {
            throw new NullPointerException("Pesquisa nao encontrada.");
        }
        else if (!cPesquisas.pesquisaEhAtiva(idPesquisa)) {
            throw new ActivationException("Pesquisa desativada.");
        }
        return cPesquisas.desassociaPesquisador(idPesquisa, emailPesquisador);
    }
    
    /**
	 * Associa um objeto Problema a um objeto Pesquisa pelos seus IDs indicados.
	 * Caso a operação seja bem sucedida, retorna-se a String "true". Caso
	 * contrário, retorna-se "false"
	 * 
	 * @param cPesquisa  Entidade controladora de objetos Pesquisa
	 * @param cProblema  Entidade controladora de objetos Problema
	 * @param idPesquisa String que representa unicamente um objeto Pesquisa
	 * @param idProblema String que representa unicamente um objeto Problema
	 * @return String referente ao sucesso da operação
	 */
	public String associaProblema(ControllerPesquisas cPesquisa, ControllerProblemas cProblema, String idPesquisa,
			String idProblema) {

		Problema problema = cProblema.getProblema(idProblema);
		return cPesquisa.associaProblema(idPesquisa, idProblema, problema);

	}
	
	/**
	 * Desassocia um objeto Problema de uma Pesquisa indicada pelos seus IDs
	 * Caso a operação seja bem sucedida, retorna-se a String "true". Caso
	 * contrário, retorna-se "false"
	 * 
	 * 
	 * @param cPesquisa  Entidade controladora de objetos Pesquisa
	 * @param idPesquisa String que representa unicamente um objeto Pesquisa
	 * @return String referente ao sucesso da operação
	 */
	public String desassociaProblema(ControllerPesquisas cPesquisa, String idPesquisa) {

		return cPesquisa.desassociaProblema(idPesquisa);

	}
	
	/**
	 * Associa um objeto Objetivo a um objeto Pesquisa pelos seus ID's indicados.
	 * Caso a operação seja bem sucedida, retorna-se a String "true". Caso
	 * contrário, retorna-se "false"
	 * 
	 * @param cPesquisa  Entidade controladora de objetos Pesquisa
	 * @param cObjetivo  Entidade controladora de objetos Objetivo
	 * @param idPesquisa String que representa unicamente um objeto Pesquisa
	 * @param idObjetivo String que representa unicamente um objeto Objetivo
	 * @return String referente ao sucesso da operação
	 */
	public String associaObjetivo(ControllerPesquisas cPesquisa, ControllerObjetivos cObjetivo, String idPesquisa,
			String idObjetivo) {

		Objetivo objetivo = cObjetivo.getObjetivo(idObjetivo);
		return cPesquisa.associaObjetivo(idPesquisa, idObjetivo, objetivo);

	}
	
	/**
	 * Desassocia um objeto Objetivo de uma Pesquisa indicada pelos seus IDs
	 * Caso a operação seja bem sucedida, retorna-se a String "true". Caso
	 * contrário, retorna-se "false"
	 * 
	 * 
	 * @param cPesquisa  Entidade controladora de objetos Pesquisa
	 * @param cObjetivo  Entidade controladora de objetos Problema
	 * @param idPesquisa String que representa unicamente um objeto Pesquisa
	 * @param idObjetivo String que representa unicamente um objeto Objetivo
	 * @return String referente ao sucesso da operação
	 */
	public String desassociaObjetivo(ControllerPesquisas cPesquisa, ControllerProblemas cProblema, String idPesquisa,
			String idProblema) {

		return cPesquisa.desassociaObjetivo(idPesquisa, idProblema);

	}
    
    /**
     * Busca a ocorrencia de um termo em todas as entidades do sistema.
     * @param cPesquisa ControllerPesquisas com todas as pesquisas cadastradas.
     * @param cPesquisador ControllerPesquisadores com todos os pesquisadores cadastrados.
     * @param cProblema ControllerProblemas com todas as problemas cadastrados.
     * @param cObjetivo ControllerObjetivos com todas os objetivos cadastrados.
     * @param cAtividade ControllerAtividades com todas as atividades cadastradas.
     * @param cBuscas ControllerBuscas responsavel por verificar a ocorrencia do termo nas outras entidades.
     * @param termo termo que se deseja verificar a ocorrencia.
     * @return String contendo a representacao das entidades que fazem mencao ao termo.
     */
    public String busca(ControllerPesquisas cPesquisa, ControllerPesquisador cPesquisador, ControllerProblemas cProblema, ControllerObjetivos cObjetivo, ControllerAtividades cAtividade, ControllerBuscas cBuscas, String termo) {

        Collection<Pesquisa> pesquisas = cPesquisa.getPesquisas();


        Collection<Pesquisador> pesquisadores = cPesquisador.getPesquisadores();


        Collection<Problema> problemas = cProblema.getProblemas();


        Collection<Objetivo> objetivos = cObjetivo.getObjetivos();


        Collection<Atividade> atividades = cAtividade.getAtividades();
        return cBuscas.busca(termo, pesquisas, pesquisadores, problemas, objetivos, atividades);
    }

    /**
     * Busca um termo que ja foi previamente buscado no sistema e retorna um determinado elemento da lista de entidades que possuem esse termo.
     * @param cBuscas ControllerBuscas com todas as buscas ja realizadas pelo sistema.
     * @param termo termo que ja foi previamente buscado.
     * @param numeroDoResultado numero do elemento que se deseja retornar.
     * @return String contendo o elemento que estava na lista dos resultados da busca pelo termo.
     */
    public String busca(ControllerBuscas cBuscas, String termo, int numeroDoResultado) {
        return cBuscas.busca(termo, numeroDoResultado);
    }

    /**
     * Conta o numero de entidades que fazem mencao ao termo que ja foi previamente buscado.
     * @param cBuscas ControllerBuscas com todas as buscas já realizadas pelo sistema.
     * @param termo termo que já foi previamente buscado.
     * @return int representado o numero de entidades que contem o termo.
     */
    public int contaResultadosBusca(ControllerBuscas cBuscas, String termo) {
        return cBuscas.contaResultadosBusca(termo);
    }

    public boolean associaAtividade(ControllerPesquisas cPesquisa, ControllerAtividades cAtividade,
                                    String codigoPesquisa, String codigoAtividade) {
        super.validaString(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
        super.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
        super.hasValor(cAtividade.containsAtividade(codigoAtividade), "Atividade nao encontrada");
        Atividade atividade = cAtividade.getAtividade(codigoAtividade);
        return cPesquisa.associaAtividade(codigoPesquisa, atividade);

    }

    public boolean desassociaAtividade(ControllerPesquisas cPesquisa, String codigoPesquisa, String codigoAtividade) {
        super.validaString(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
        super.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
        if (cPesquisa.containsPesquisa(codigoPesquisa)) {
            throw new IllegalArgumentException("Pesquisa nao encontrada.");
        }
        if (!cPesquisa.pesquisaEhAtiva(codigoPesquisa)) {
            throw new IllegalArgumentException("Pesquisa desativada.");
        }
        return cPesquisa.desassociaAtividade(codigoPesquisa, codigoAtividade);
    }
}
