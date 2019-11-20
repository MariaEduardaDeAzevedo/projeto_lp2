package base;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representacao de um aluno, que e, no sistema Psquiza, uma especializacao de Pesquisador, ou seja, Aluno e uma subclasse de Pesquisador.
 * Todo aluno, alem dos atributos herdados Pesquisador, que sao funcao, nome, biografia, email, foto e status (que diz se o pesquisador e ativo ou não), tambem
 * tem os atributos semestre de ingresso e seu índice de eficiência acadêmica (iea).
 * Assim como pesquisador, todo aluno e identificado unicamente pelo seu email.
 *
 */
public class Aluno extends Pesquisador implements Serializable {
	/**
	 * Inteiro correspondente ao semestre de ingresso do aluno.
	 * Por definicao, este inteiro nao pode ser negativo e nem igual a 0.
	 */
	private int semestre;
	/**
	 * Valor real correspondente ao indice de eficiência academica do aluno, que deve ser um numero entre 0 e 10.
	 */
	private double iea;
	
	/**
	 * Constroi um aluno a partir de seu nome, sua funcao (que deve, obrigatoriamente, ser "estudante"), sua biografia, seu email, uma string correspondendo
	 * a url da foto do aluno, seu semestre de ingresso e seu indice de eficiencia academica (iea)
	 * @param nome nome do aluno
	 * @param funcao função exercida pelo aluno, que deve ser, obrigatoriamente, igual a "estudante"
	 * @param biografia biografia do aluno, uma descricao textual de sua atuação em pesquisa
	 * @param email email do aluno
	 * @param foto url da foto do aluno
	 * @param semestre inteiro correspondente ao semestre de ingresso do aluno
	 * @param iea valor real, que deve ser entre 0 e 10, correspondente ao indice de eficiencia academica do aluno
	 */
	public Aluno(String nome, String funcao, String biografia, String email, String foto, int semestre, double iea) {
		super(nome, funcao, biografia, email, foto);
		List lista = new ArrayList<String>();
		lista.add("estudante");
		super.validaValoresPermitidos(lista , funcao, "Pesquisador nao compativel com a especialidade.");
		super.validaSemestreAluno(semestre, "Atributo semestre com formato invalido.");
		super.validaIeaAluno(iea, "Atributo IEA com formato invalido.");
		this.semestre = semestre;
		this.iea = iea;
	}
	
	/**
	 * Retorna uma String que representa o aluno. A representacao segue o formato "NOME (FUNCAO) - BIOGRAFIA - EMAIL - FOTO - SEMESTRE - IEA"
	 * O semestre retornado nessa representacao deve ter o formato "Xo semestre", em que X e o inteiro correspondente ao semestre de ingresso do aluno.
	 * @return representação em String de um aluno.
	 */
	@Override
	public String toString() {
		return String.format("%s (estudante) - %s - %s - %s - %s - %s", this.getNome(), this.getBiografia(), this.getEmail(), this.getFoto(), (semestre + "o SEMESTRE"), iea);
	}
	
	/**
	 * Altera o valor do semestre de ingresso do aluno.
	 * O novo valor do semestre tambem deve ser um numero inteiro nao nulo e positivo.
	 * @param novoValor novo valor inteiro que substituira o valor do semestre de ingresso atual do aluno.
	 */
	public void setSemestre(int novoValor) {
		super.validaString(String.valueOf(novoValor), "Campo novoValor nao pode ser nulo ou vazio.");
		super.validaSemestreAluno(novoValor, "novoValor com formato invalido.");
		semestre = novoValor;
	}
	
	/**
	 * Altera o valor do indice de eficiencia academica do aluno (IEA)
	 * O novo valor do IEA tambem deve ser um numero real menor ou igual a 10 e maior ou igual a 0.
	 * @param novoValor novo valor do IEA do aluno.
	 */
	public void setIEA(double novoValor) {
		super.validaString(String.valueOf(novoValor), "Campo novoValor nao pode ser nulo ou vazio.");
		super.validaIeaAluno(novoValor, "Atributo IEA com formato invalido.");
		iea = novoValor;
	}
	
	/**
	 * Metodo sobrescrito de Pesquisador que permite a alteracao dos atributos de Aluno.
	 * @param atributo nome do atributo a ser alterado.
	 * @param novoValor novo valor do atributo.
	 */
	@Override
	public void alteraPesquisador(String atributo, String novoValor) {
		 super.validaString(atributo, "Atributo nao pode ser vazio ou nulo.");
		 switch(atributo.trim()) {
			case "NOME":
				this.setNome(novoValor);
				break;
			case "FUNCAO":
				this.setFuncao(novoValor);
				break;
			case "BIOGRAFIA":
				this.setBiografia(novoValor);
				break;
			case "EMAIL":
				this.setEmail(novoValor);
				break;
			case "FOTO":
				this.setFoto(novoValor);
				break;
			case "SEMESTRE":
				super.validaString(novoValor, "Campo semestre nao pode ser nulo ou vazio.");
				int novoSemestre = Integer.parseInt(novoValor);
				this.validaSemestreAluno(novoSemestre, "Atributo semestre com formato invalido.");
				this.setSemestre(novoSemestre);
				break;
			case "IEA":
				super.validaString(novoValor, "Campo IEA nao pode ser nulo ou vazio.");
				double novoIEA = Double.parseDouble(novoValor);
				this.validaIeaAluno(novoIEA, "Atributo IEA com formato invalido.");
				this.setIEA(novoIEA);
				break;
			default:
				throw new IllegalArgumentException("Atributo invalido.");
		 }
	 }
}
