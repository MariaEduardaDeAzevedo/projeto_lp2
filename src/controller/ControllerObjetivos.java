package controller;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import base.Objetivo;

/**
 * Classe controller de Objetivo, que armazena todos os objetivos cadastrados no sistema e contém os métodos utilizados pela fachada
 * para realizar operações com os objetivos.
 * @author Daniel Fonseca
 *
 */
public class ControllerObjetivos extends Validacao {
	/**
	 * Mapa que armazena todos os objetivos cadastrados no sistema.
	 * Neste mapa, a chave é o código do objetivo, cujo qual este é identificado unicamente e o valor é um Objetivo.
	 * O código de todo objetivo cadastrado tem o formato "O" + id gerado automaticamente (a partir de 1).
	 */
	private Map<String, Objetivo> objetivos;
	/**
	 * inteiro utilizado como id para gerar os códigos que são utilizados como chave no mapa que armazena os objetivos cadastrados no sistema.
	 */
	private int idNumber;
	
	/**
	 * Constrói um controller de Objetivo.
	 * Por padrão, todo controller desse tipo começa sem nenhum Objetivo cadastrado e o seu id utilizado para gerar os códigos utilizados como chave
	 * no mapa que armazena todos os objetivos começa como sendo 1.
	 */
	public ControllerObjetivos() {
		this.objetivos = new HashMap<String, Objetivo>();
		this.idNumber = 1;
	}
	
	/**
	 * Cadastra um objetivo no sistema a partir de seu tipo (pode ser "GERAL" ou "ESPECIFICO"), sua descrição e mais dois valores
	 * que irão corresponder à aderência e à viabilidade do objetivo.
	 * Todo objetivo cadastrado é armazenado no mapa e tem sua chave como sendo seu código que o identifica unicamente no sistema, este
	 * código é gerado pela concatenação de "O" + id gerado automaticamente (a partir de 1).
	 * @param tipo tipo do objetivo a ser cadastrado no sistema (pode ser "GERAL" ou "ESPECIFICO"
	 * @param descricao descrição do objetivo a ser cadastrado no sistema
	 * @param aderencia valor inteiro correspondente à aderência do objetivo a ser cadastrado no sistema, pode ser qualquer valor no intervalo de 1 a 5
	 * @param viabilidade valor inteiro correspondente à viabilidade do objetivo a ser cadastrado no sistema, pode ser qualquer valor no intervalo de 1 a 5
	 */
	public void cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		super.validaString(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		super.validaTipo(tipo, "Valor invalido de tipo.");
		super.validaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		super.validaValor(aderencia, "Valor invalido de aderencia");
		super.validaValor(viabilidade, "Valor invalido de viabilidade.");
		String idObjetivo = "O" + idNumber;
		idNumber++;
		objetivos.put(idObjetivo, new Objetivo(tipo, descricao, aderencia, viabilidade, idObjetivo));
	}
	
	/**
	 * Exibe a representação textual de um determinado objetivo cadastrado no sistema.
	 * A representação textual de todo objetivo é dada no formato "CÓDIGO DO OBJETIVO - TIPO - DESCRIÇÃO - VALOR"
	 * @param idObjetivo o código que identifica unicamente o objetivo no sistema
	 * @return String que corresponde à representação textual do objetivo
	 * @throws Exception exceções são lançadas quando o código do objetivo passado como parâmetro é vazio ou nulo ou quando
	 * o código passado como parâmetro não identifica nenhum objetivo cadastrado no sistema
	 */
	public String exibeObjetivo(String idObjetivo) throws Exception {
		super.validaString(idObjetivo, "Codigo do objetivo passado não pode ser vazio ou nulo");
		if (!objetivos.containsKey(idObjetivo)) {
			throw new Exception("Objetivo nao encontrado");
		}
		return objetivos.get(idObjetivo).toString();
	}
	
	/**
	 * Apaga um objetivo do sistema, ou seja, retira um objetivo cadastrado no sistema através do seu código.
	 * @param idObjetivo código que identifica unicamente o objetivo cadastrado no sistema
	 * @throws Exception exceções são lançadas quando o código passado como parâmetro no método é vazio ou nulo ou quando o código passado como
	 * parâmetro não identifica nenhum objetivo cadastrado
	 */
	public void apagarObjetivo(String idObjetivo) throws Exception {
		super.validaString(idObjetivo, "Campo codigo nao pode ser nulo ou vazio.");
		if (!objetivos.containsKey(idObjetivo)) {
			throw new Exception("Objetivo nao encontrado");
		}
		objetivos.remove(idObjetivo);
	}

	public Objetivo getObjetivo(String id) {
		
		return this.objetivos.get(id);
	
	}

	/**
	 * Retorna os valores do mapa de objetivos.
	 * @return Collection contendo todos os objetos do tipo Objetivo cadastrados no sistema.
	 */
    public Collection<Objetivo> getObjetivos() {
		return this.objetivos.values();
    }
}
