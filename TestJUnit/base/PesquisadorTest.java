package base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void criaPesquisadorNomeVazio(){
        assertThrows(IllegalArgumentException.class, () -> {
            Pesquisador pesquisadorTeste1 = new Pesquisador("", "estudante", "estudante de Computação", "arthur@example.com", "https://formation");
        });
    }

    @Test
    void criaPesquisadorNomeNull(){
        assertThrows(NullPointerException.class, () -> {
            Pesquisador pesquisadorTeste1 = new Pesquisador(null, "estudante", "estudante de Computação", "arthur@example.com", "https://formation");
        });
    }

    @Test
    void criaPesquisadorFuncaoVazia(){
        assertThrows(IllegalArgumentException.class, () -> {
            Pesquisador pesquisadorTeste1 = new Pesquisador("Arthur", "", "estudante de Computação", "arthur@example.com", "https://formation");
        });
    }

    @Test
    void criaPesquisadorFuncaoNull(){
        assertThrows(NullPointerException.class, () -> {
            Pesquisador pesquisadorTeste1 = new Pesquisador("Arthur", null, "estudante de Computação", "arthur@example.com", "https://formation");
        });
    }

    @Test
    void criaPesquisadorBiografiaVazia(){
        assertThrows(IllegalArgumentException.class, () -> {
            Pesquisador pesquisadorTeste1 = new Pesquisador("Arthur", "estudante", "", "arthur@example.com", "https://formation");
        });
    }

    @Test
    void criaPesquisadorBiografiaNull(){
        assertThrows(NullPointerException.class, () -> {
            Pesquisador pesquisadorTeste1 = new Pesquisador("Arthur", "estudante", null, "arthur@example.com", "https://formation");
        });
    }

    @Test
    void criaPesquisadorEmailVazio(){
        assertThrows(IllegalArgumentException.class, () -> {
            Pesquisador pesquisadorTeste1 = new Pesquisador("Arthur", "estudante", "estudante de Computação", "", "https://formation");
        });
    }

    @Test
    void criaPesquisadorEmailNull(){
        assertThrows(NullPointerException.class, () -> {
            Pesquisador pesquisadorTeste1 = new Pesquisador("Arthur", "estudante", "estudante de Computação", null, "https://formation");
        });
    }

    @Test
    void criaPesquisadorFotoVazia(){
        assertThrows(IllegalArgumentException.class, () -> {
            Pesquisador pesquisadorTeste1 = new Pesquisador("Arthur", "estudante", "estudante de Computação", "arthur@example.com", "");
        });
    }

    @Test
    void criaPesquisadorFotoNull(){
        assertThrows(NullPointerException.class, () -> {
            Pesquisador pesquisadorTeste1 = new Pesquisador("Arthur", "estudante", "estudante de Computação", "arthur@example.com", null);
        });
    }

    @Test
    void ativaPesquisadorAtivado(){
        assertThrows(IllegalArgumentException.class, () -> {
            pesquisadorTeste.ativaPesquisador();
        });
    }

    @Test
    void ativaPesquisadorDesativado(){
        pesquisadorTeste.inativaPesquisador();
        pesquisadorTeste.ativaPesquisador();
        assertTrue(pesquisadorTeste.pesquisadorEhAtivo());
    }

    @Test
    void inativaPesquisadorAtivado(){
        pesquisadorTeste.inativaPesquisador();
        assertFalse(pesquisadorTeste.pesquisadorEhAtivo());
    }

    @Test
    void inativaPesquisadorInativado() {
        pesquisadorTeste.inativaPesquisador();
        assertThrows(IllegalArgumentException.class, () -> {
            pesquisadorTeste.inativaPesquisador();
        });
    }

    @Test
    void alteraPesquisadorAtributoVazio(){
        assertThrows(IllegalArgumentException.class, () -> {
            pesquisadorTeste.alteraPesquisador("", "java");
        });
    }

    @Test
    void alteraPesquisadorAtributoNull(){
        assertThrows(NullPointerException.class, () -> {
            pesquisadorTeste.alteraPesquisador(null, "java");
        });
    }

    @Test
    void alteraPesquisadorAtributoNomeVazio(){
        assertThrows(IllegalArgumentException.class, () -> {
            pesquisadorTeste.alteraPesquisador("NOME", "");
        });
    }

    @Test
    void alteraPesquisadorAtributoNomeNull(){
        assertThrows(NullPointerException.class, () -> {
            pesquisadorTeste.alteraPesquisador("NOME", null);
        });
    }

    @Test
    void alteraPesquisadorAtributoFuncaoVazia(){
        assertThrows(IllegalArgumentException.class, () -> {
            pesquisadorTeste.alteraPesquisador("FUNCAO", "");
        });
    }

    @Test
    void alteraPesquisadorAtributoFuncaoNull(){
        assertThrows(NullPointerException.class, () -> {
            pesquisadorTeste.alteraPesquisador("FUNCAO", null);
        });
    }

    @Test
    void alteraPesquisadorAtributoBiografiaVazia(){
        assertThrows(IllegalArgumentException.class, () -> {
            pesquisadorTeste.alteraPesquisador("BIOGRAFIA", "");
        });
    }

    @Test
    void alteraPesquisadorAtributoBiografiaNull(){
        assertThrows(NullPointerException.class, () -> {
            pesquisadorTeste.alteraPesquisador("BIOGRAFIA", null);
        });
    }

    @Test
    void alteraPesquisadorAtributoEmailVazio(){
        assertThrows(IllegalArgumentException.class, () -> {
            pesquisadorTeste.alteraPesquisador("EMAIL", "");
        });
    }

    @Test
    void alteraPesquisadorAtributoEmailNull(){
        assertThrows(NullPointerException.class, () -> {
            pesquisadorTeste.alteraPesquisador("EMAIL", null);
        });
    }

    @Test
    void alteraPesquisadorAtributoFotoVazio(){
        assertThrows(IllegalArgumentException.class, () -> {
            pesquisadorTeste.alteraPesquisador("FOTO", "");
        });
    }

    @Test
    void alteraPesquisadorAtributoFotoNull(){
        assertThrows(NullPointerException.class, () -> {
            pesquisadorTeste.alteraPesquisador("FOTO", null);
        });
    }

    @Test
    void alteraPesquisadorAtributoInvalido(){
        assertThrows(IllegalArgumentException.class, () -> {
            pesquisadorTeste.alteraPesquisador("FAMILIA", "Ferreira");
        });
    }

    @Test
    void alteraPesquisadorAtributoNomeValido(){
            pesquisadorTeste.alteraPesquisador("NOME", "Pedro");
            assertEquals("Pedro (estudante) - estudante de Computação - arthur@example.com - https://formation",
                    pesquisadorTeste.toString());
    }

    @Test
    void alteraPesquisadorAtributoFuncaoValida(){
        pesquisadorTeste.alteraPesquisador("FUNCAO", "professor");
        assertEquals("Arthur (professor) - estudante de Computação - arthur@example.com - https://formation",
                pesquisadorTeste.toString());
    }

    @Test
    void alteraPesquisadorAtributoEmailValido(){
        pesquisadorTeste.alteraPesquisador("EMAIL", "pedro@example.com");
        assertEquals("Arthur (estudante) - estudante de Computação - pedro@example.com - https://formation",
                pesquisadorTeste.toString());
    }

    @Test
    void alteraPesquisadorAtributoBiografiaValida(){
        pesquisadorTeste.alteraPesquisador("BIOGRAFIA", "universitário");
        assertEquals("Arthur (estudante) - universitário - arthur@example.com - https://formation",
                pesquisadorTeste.toString());
    }

    @Test
    void alteraPesquisadorAtributoFotoValida(){
        pesquisadorTeste.alteraPesquisador("FOTO", "https://drunkinlove");
        assertEquals("Arthur (estudante) - estudante de Computação - arthur@example.com - https://drunkinlove",
                pesquisadorTeste.toString());
    }

    @Test
    void toStringPesquisador(){
        assertEquals("Arthur (estudante) - estudante de Computação - arthur@example.com - https://formation",
                pesquisadorTeste.toString());
    }

    @Test
    void hashCodePesquisador(){
        assertEquals(pesquisadorTeste.hashCode(), pesquisadorTesteCopia.hashCode());
    }

    @Test
    void equalsTrue(){
        assertTrue(pesquisadorTeste.equals(pesquisadorTesteCopia));
    }

    @Test
    void equalsTrueMesmoObjeto(){
        assertTrue(pesquisadorTeste.equals(pesquisadorTeste));
    }

    @Test
    void equalsFalse(){
        assertFalse(pesquisadorTeste.equals(pesquisadorTesteDiferente));
    }

    @Test
    void equalsFalseObjetoDiferente(){
        Pesquisa pesquisaTeste = new Pesquisa("COM1","Homofobia em mensagens online de alunos de" +
                " computacao do primeiro periodo.", "computacao, homofobia");
        assertFalse(pesquisadorTeste.equals(pesquisaTeste));
    }

    @Test
    void equalsFalseNull(){
        assertFalse(pesquisadorTeste.equals(null));
    }

    @Test
    void pesquisadorEhAtivoTrue(){
        assertTrue(pesquisadorTeste.pesquisadorEhAtivo());
    }

    @Test
    void pesquisadorEhAtivoFalse(){
        pesquisadorTeste.inativaPesquisador();
        assertFalse(pesquisadorTeste.pesquisadorEhAtivo());
    }
}