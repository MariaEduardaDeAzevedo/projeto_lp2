package base;

public class Aluno extends Pesquisador {
	private int semestre;
	private double iea;
	
	public Aluno(String nome, String funcao, String biografia, String email, String foto, int semestre, double iea) {
		super(nome, funcao, biografia, email, foto);
		super.validaSemestreAluno(semestre, "Atributo semestre com formato invalido.");
		super.validaIeaAluno(iea, "Atributo IEA com formato invalido.");
		this.semestre = semestre;
		this.iea = iea;
	}
	
	@Override
	public String toString() {
		return String.format("%s (ALUNO) - %s - %s - %s - %s - %s", this.getNome(), this.getBiografia(), this.getEmail(), this.getFoto(), (semestre + "o SEMESTRE"), iea);
	}
}
