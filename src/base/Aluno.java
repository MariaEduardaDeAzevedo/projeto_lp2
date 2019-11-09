package base;
import excecoes.FuncaoInvalidaException;

/**
 * Representação de um aluno, que é, no sistema Psquiza, uma especialização de Pesquisador, ou seja, Aluno é uma subclasse de Pesquisador.
 * Todo aluno, além dos atributos herdados Pesquisador, que são função, nome, biografia, email, foto e status (que diz se o pesquisador é ativo ou não), também
 * tem os atributos semestre de ingresso e seu índice de eficiência acadêmica (iea).
 * Assim como pesquisador, todo aluno é identificado unicamente pelo seu email.
 * @author Daniel Fonseca
 *
 */
public class Aluno extends Pesquisador {
	/**
	 * Inteiro correspondente ao semestre de ingresso do aluno.
	 * Por definição, este inteiro não pode ser negativo e nem igual a 0.
	 */
	private int semestre;
	/**
	 * Valor real correspondente ao índice de eficiência acadêmica do aluno, que deve ser um número entre 0 e 10.
	 */
	private double iea;
	
	/**
	 * Constrói um aluno a partir de seu nome, sua função (que deve, obrigatoriamente, ser "estudante"), sua biografia, seu email, uma string correspondendo
	 * à url da foto do aluno, seu semestre de ingresso e seu índice de eficiência acadêmica (iea)
	 * @param nome nome do aluno
	 * @param funcao função exercida pelo aluno, que deve ser, obrigatoriamente, igual a "estudante"
	 * @param biografia biografia do aluno, uma descrição textual de sua atuação em pesquisa
	 * @param email email do aluno
	 * @param foto url da foto do aluno
	 * @param semestre inteiro correspondente ao semestre de ingresso do aluno
	 * @param iea valor real, que deve ser entre 0 e 10, correspondente ao índice de eficiência acadêmica do aluno
	 */
	public Aluno(String nome, String funcao, String biografia, String email, String foto, int semestre, double iea) {
		super(nome, funcao, biografia, email, foto);
		if (!funcao.equals("estudante")) {
			throw new FuncaoInvalidaException("Pesquisador nao compativel com a especialidade.");
		}
		super.validaSemestreAluno(semestre, "Atributo semestre com formato invalido.");
		super.validaIeaAluno(iea, "Atributo IEA com formato invalido.");
		this.semestre = semestre;
		this.iea = iea;
	}
	
	/**
	 * Retorna uma String que representa o aluno. A representação segue o formato "NOME (FUNCAO) - BIOGRAFIA - EMAIL - FOTO - SEMESTRE - IEA"
	 * O semestre retornado nessa representação deve ter o formato "Xo semestre", em que X é o inteiro correspondente ao semestre de ingresso do aluno.
	 * @return representação em String de um aluno.
	 */
	@Override
	public String toString() {
		return String.format("%s (estudante) - %s - %s - %s - %s - %s", this.getNome(), this.getBiografia(), this.getEmail(), this.getFoto(), (semestre + "o SEMESTRE"), iea);
	}
	
	/**
	 * Altera o valor do semestre de ingresso do aluno.
	 * O novo valor do semestre também deve ser um número inteiro não nulo e positivo.
	 * @param novoValor novo valor inteiro que substituirá o valor do semestre de ingresso atual do aluno.
	 */
	public void setSemestre(int novoValor) {
		super.validaString(String.valueOf(novoValor), "Campo novoValor nao pode ser nulo ou vazio.");
		super.validaSemestreAluno(novoValor, "novoValor com formato invalido.");
		semestre = novoValor;
	}
	
	/**
	 * Altera o valor do índice de eficiência acadêmica do aluno (IEA)
	 * O novo valor do IEA também deve ser um número real menor ou igual a 10 e maior ou igual a 0.
	 * @param novoValor novo valor do IEA do aluno.
	 */
	public void setIEA(double novoValor) {
		super.validaString(String.valueOf(novoValor), "Campo novoValor nao pode ser nulo ou vazio.");
		super.validaIeaAluno(novoValor, "Atributo IEA com formato invalido.");
		iea = novoValor;
	}
	
	/**
	 * Método sobrescrito de Pesquisador que permite a alteração dos atributos de Aluno.
	 * @param atributo nome do atributo a ser alterado.
	 * @param novoValor novo valor do atributo.
	 */
	@Override
	public void alteraPesquisador(String atributo, String novoValor) {
		 super.validaString(atributo, "Atributo nao pode ser vazio ou nulo.");
		 switch(atributo.trim()) {
			case "NOME":
				super.validaString(novoValor, "Campo nome nao pode ser nulo ou vazio.");
				this.setNome(novoValor);
				break;
			case "FUNCAO":
				super.validaString(novoValor, "Campo funcao nao pode ser nulo ou vazio.");
				this.setFuncao(novoValor);
				break;
			case "BIOGRAFIA":
				super.validaString(novoValor, "Campo biografia nao pode ser nulo ou vazio.");
				this.setBiografia(novoValor);
				break;
			case "EMAIL":
				super.validaString(novoValor, "Campo email nao pode ser nulo ou vazio.");
				super.verificaEmail(novoValor);
				this.setEmail(novoValor);
				break;
			case "FOTO":
				super.validaString(novoValor, "Campo fotoURL nao pode ser nulo ou vazio.");
				super.verificaURL(novoValor);
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
