package base;
import excecoes.FuncaoInvalidaException;

public class Aluno extends Pesquisador {
	private int semestre;
	private double iea;
	
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
	
	@Override
	public String toString() {
		return String.format("%s (estudante) - %s - %s - %s - %s - %s", this.getNome(), this.getBiografia(), this.getEmail(), this.getFoto(), (semestre + "o SEMESTRE"), iea);
	}
	
	public void setSemestre(int novoValor) {
		super.validaString(String.valueOf(novoValor), "Campo novoValor nao pode ser nulo ou vazio.");
		super.validaSemestreAluno(novoValor, "novoValor com formato invalido.");
		semestre = novoValor;
	}
	
	public void setIEA(double novoValor) {
		super.validaString(String.valueOf(novoValor), "Campo novoValor nao pode ser nulo ou vazio.");
		super.validaIeaAluno(novoValor, "Atributo IEA com formato invalido.");
		iea = novoValor;
	}
	
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
