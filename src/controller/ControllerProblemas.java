package controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import base.Arquivo;
import base.Problema;

/**
 * Classe controller de Problema, que armazena todos os problemas cadastrados no sistema e realiza todos as operações relacionadas
 * aos problemas. 
 *
 */
public class ControllerProblemas extends Validacao implements Serializable {
	/**
	 * Mapa que tem como chave uma String, que corresponde a um código que identifica unicamente um Problema no sistema, e tem como valor
	 * um Problema. Esta coleção armazena todos os problemas cadastrados no sistema.
	 */
	private Map<String, Problema> problemas;
	/**
	 * Número utilizado para gerar os códigos que identificam unicamente os problemas no sistema.
	 */
	private int idNumber;
	
	/**
	 * Constrói um novo controller de Problema que, por padrão, começa sem nenhum Problema cadastrado e o valor do número
	 * utilizado para gerar o código dos problemas cadastrados no sistema começa como sendo 1.
	 */
	public ControllerProblemas() {
		this.problemas = new TreeMap<String, Problema>();
		this.idNumber = 1;
	}
	
	/**
	 * Cadastra um novo problema no sistema a partir de uma descrição e de um valor inteiro que vai de 1 a 5 correspondente à
	 * viabilidade do problema.
	 * Todo problema cadastrado no sistema será identificado por um código no formato "P" + id gerado automaticamente (a partir de 1).
	 * @param descricao descrição do problema
	 * @param viabilidade valor inteiro que pode ir de 1 a 5 e corresponde à viabilidade do problema
	 */
	public void cadastraProblema(String descricao, int viabilidade) {
		super.validaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		super.validaValor(viabilidade, "Valor invalido de viabilidade.");
		String idProblema = "P" + idNumber;
		idNumber++;
		Problema novoProblema = new Problema(descricao, viabilidade, idProblema);
		problemas.put(idProblema, novoProblema);
	}
	
	/**
	 * Retorna a representação textual de determinado problema cadastrado no sistema.
	 * A representação textual de um problema é retornada no formato "CÓDIGO DO PROBLEMA - DESCRIÇÃO - VIABILIDADE".
	 * @param idProblema código que identifica unicamente o problema no sistema
	 * @return String que corresponde à representação textual de determinado problema cadastrado no sistema
	 */
	public String exibeProblema(String idProblema) {
		super.validaString(idProblema, "id do problema não pode ser vazio ou nulo");
		super.hasValor(this.problemas.containsKey(idProblema), "Problema nao encontrado");
		return idProblema + " - " + problemas.get(idProblema).toString();
	}
	
	/**
	 * Remove um problema cadastrado do sistema através de seu código de identificação.
	 * @param idProblema código que identifica o problema unicamente no sistema.
	 */
	public void apagarProblema(String idProblema) {
		super.validaString(idProblema, "Campo codigo nao pode ser nulo ou vazio.");
		super.hasValor(this.problemas.containsKey(idProblema), "Problema nao encontrado");
		problemas.remove(idProblema);
	}
	
	public Problema getProblema(String id) {		
		return this.problemas.get(id);
	}

	/**
	 * Retorna os valores do mapa de problemas.
	 * @return Collection contendo todos os objetos do tipo Problema cadastrados no sistema.
	 */
    public Collection<Problema> getProblemas() {
		return this.problemas.values();
    }

    public void salvarArquivos() {
		Arquivo arquivo = new Arquivo();
		arquivo.salvarArquivos(this.problemas, "Problemas");
		arquivo.salvarArquivoInt(this.idNumber, "Proximo ID dos Problemas");
    }

    public void carregarArquivos() throws Exception {
		Arquivo arquivo = new Arquivo();
		this.problemas = (TreeMap<String, Problema>) arquivo.carregarArquivos("Problemas");
		this.idNumber = arquivo.carregarArquivoInt("Proximo ID dos Problemas");
	}
}
