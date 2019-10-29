import org.junit.jupiter.api.BeforeEach;
import projeto_lp2.Pesquisador;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PesquisadorTest {

    Pesquisador pesquisadorTeste;
    Pesquisador pesquisadorTesteCopia;
    Pesquisador pesquisadorTesteDiferente;

    @BeforeEach
    void criaPesquisador(){
        pesquisadorTeste = new Pesquisador("Arthur", "estudante", "estudante de Computação", "arthur@example.com", "https://formation");
        pesquisadorTesteCopia = new Pesquisador("Arthur", "estudante", "estudante de Computação", "arthur@example.com", "https://formation");
        pesquisadorTesteDiferente = new Pesquisador("Brenda", "estudante", "estudante de Computação", "brenda@example.com", "https://flawless");
    }

    void criaPesquisadorNomeVazio(){
        assertThrows(IllegalArgumentException.class, () -> {
            Pesquisador pesquisadorTeste1 = new Pesquisador("", "estudante", "estudante de Computação", "arthur@example.com", "https://formation");
        });
    }

    void criaPesquisadorNomeNull(){
        assertThrows(NullPointerException.class, () -> {
            Pesquisador pesquisadorTeste1 = new Pesquisador(null, "estudante", "estudante de Computação", "arthur@example.com", "https://formation");
        });
    }

    void criaPesquisadorFuncaoVazia(){
        assertThrows(IllegalArgumentException.class, () -> {
            Pesquisador pesquisadorTeste1 = new Pesquisador("Arthur", "", "estudante de Computação", "arthur@example.com", "https://formation");
        });
    }

    void criaPesquisadorFuncaoNull(){
        assertThrows(NullPointerException.class, () -> {
            Pesquisador pesquisadorTeste1 = new Pesquisador("Arthur", null, "estudante de Computação", "arthur@example.com", "https://formation");
        });
    }

    void criaPesquisadorBiografiaVazia(){
        assertThrows(IllegalArgumentException.class, () -> {
            Pesquisador pesquisadorTeste1 = new Pesquisador("Arthur", "estudante", "", "arthur@example.com", "https://formation");
        });
    }

    void criaPesquisadorBiografiaNull(){
        assertThrows(NullPointerException.class, () -> {
            Pesquisador pesquisadorTeste1 = new Pesquisador("Arthur", "estudante", null, "arthur@example.com", "https://formation");
        });
    }

    void criaPesquisadorEmailVazio(){
        assertThrows(IllegalArgumentException.class, () -> {
            Pesquisador pesquisadorTeste1 = new Pesquisador("Arthur", "estudante", "estudante de Computação", "", "https://formation");
        });
    }

    void criaPesquisadorEmailNull(){
        assertThrows(NullPointerException.class, () -> {
            Pesquisador pesquisadorTeste1 = new Pesquisador("Arthur", "estudante", "estudante de Computação", null, "https://formation");
        });
    }

    void criaPesquisadorFotoVazia(){
        assertThrows(IllegalArgumentException.class, () -> {
            Pesquisador pesquisadorTeste1 = new Pesquisador("Arthur", "estudante", "estudante de Computação", "arthur@example.com", "");
        });
    }

    void criaPesquisadorFotoNull(){
        assertThrows(NullPointerException.class, () -> {
            Pesquisador pesquisadorTeste1 = new Pesquisador("Arthur", "estudante", "estudante de Computação", "arthur@example.com", null);
        });
    }

    void ativaPesquisadorAtivado(){
        assertThrows(IllegalArgumentException.class, () -> {
            pesquisadorTeste.ativaPesquisador();
        });
    }

    void ativaPesquisadorDesativado(){
        pesquisadorTeste.inativaPesquisador();
        pesquisadorTeste.ativaPesquisador();
        //assertTrue(pesquisadorTeste.isAtivada());
    }

    void inativaPesquisadorAtivado(){
        pesquisadorTeste.inativaPesquisador();
        //assertFalse(pesquisadorTeste.isAtivada());
    }

    void inativaPesquisadorInativado() {
        pesquisadorTeste.inativaPesquisador();
        assertThrows(IllegalArgumentException.class, () -> {
            pesquisadorTeste.inativaPesquisador();
        });
    }
}
