package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.ControllerPesquisador;

import static org.junit.jupiter.api.Assertions.*;

class ControllerPesquisadorTest {

    ControllerPesquisador controllerPesquisadorTeste;

    @BeforeEach
    void constroiController(){
        controllerPesquisadorTeste = new ControllerPesquisador();
    }

    @Test
    void cadastraPesquisadorValido() {
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        assertEquals("Arthur (estudante) - estudante de Computação - arthur@example.com - https://formation",
                controllerPesquisadorTeste.exibePesquisador("arthur@example.com"));
    }

    @Test
    void cadastraPesquisadorNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            controllerPesquisadorTeste.cadastraPesquisador("", "estudante",
                    "estudante de Computação", "arthur@example.com", "https://formation");
        });
    }

    @Test
    void cadastraPesquisadorNomeNull(){
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.cadastraPesquisador(null, "estudante",
                    "estudante de Computação", "arthur@example.com", "https://formation");
        });
    }

    @Test
    void cadastraPesquisadorFuncaoVazia(){
        assertThrows(IllegalArgumentException.class, () -> {
            controllerPesquisadorTeste.cadastraPesquisador("Arthur", "", "estudante de Computação", "arthur@example.com", "https://formation");
        });
    }

    @Test
    void cadastraPesquisadorFuncaoNull(){
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.cadastraPesquisador("Arthur", null, "estudante de Computação", "arthur@example.com", "https://formation");
        });
    }

    @Test
    void cadastraPesquisadorBiografiaVazia(){
        assertThrows(IllegalArgumentException.class, () -> {
            controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante", "", "arthur@example.com", "https://formation");
        });
    }

    @Test
    void cadastraPesquisadorBiografiaNull(){
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante", null, "arthur@example.com", "https://formation");
        });
    }

    @Test
    void cadastraPesquisadorEmailVazio(){
        assertThrows(IllegalArgumentException.class, () -> {
            controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante", "estudante de Computação", "", "https://formation");
        });
    }

    @Test
    void cadastraPesquisadorEmailNull(){
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante", "estudante de Computação", null, "https://formation");
        });
    }

    @Test
    void cadastraPesquisadorFotoVazia(){
        assertThrows(IllegalArgumentException.class, () -> {
            controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante", "estudante de Computação", "arthur@example.com", "");
        });
    }

    @Test
    void cadastraPesquisadorFotoNull(){
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante", "estudante de Computação", "arthur@example.com", null);
        });
    }

    @Test
    void alteraPesquisadorEmailValido(){
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        controllerPesquisadorTeste.alteraPesquisador("arthur@example.com", "EMAIL", "pedro@example.com");
        assertEquals("Arthur (estudante) - estudante de Computação - pedro@example.com - https://formation",
                controllerPesquisadorTeste.exibePesquisador("pedro@example.com"));
    }
    
    @Test
    void alteraPesquisadorEmailInvalido(){
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.alteraPesquisador("arthur@example.com", "NOME", "java");
        });
    }

    @Test
    void alteraPesquisadorAtributoVazio(){
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerPesquisadorTeste.alteraPesquisador("arthur@example.com", "", "java");
        });
    }

    @Test
    void alteraPesquisadorAtributoNull(){
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.alteraPesquisador("arthur@example.com", null, "java");
        });
    }

    @Test
    void alteraPesquisadorAtributoNovoValorVazio(){
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerPesquisadorTeste.alteraPesquisador("arthur@example.com", "NOME", "");
        });
    }

    @Test
    void alteraPesquisadorAtributoNovoValorNull(){
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.alteraPesquisador("arthur@example.com", "NOME", null);
        });
    }

    @Test
    void ativaPesquisadorEmailInvalido(){
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.ativaPesquisador("arthur@xample.com");
        });
    }

    @Test
    void ativaPesquisadorAtivado(){
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerPesquisadorTeste.ativaPesquisador("arthur@example.com");
        });
    }

    @Test
    void ativaPesquisadorDesativado(){
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        controllerPesquisadorTeste.desativaPesquisador("arthur@example.com");
        controllerPesquisadorTeste.ativaPesquisador("arthur@example.com");
        assertTrue(controllerPesquisadorTeste.pesquisadorEhAtivo("arthur@example.com"));
    }

    @Test
    void desativaPesquisadorAtivado(){
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        controllerPesquisadorTeste.desativaPesquisador("arthur@example.com");
        assertFalse(controllerPesquisadorTeste.pesquisadorEhAtivo("arthur@example.com"));
    }

    @Test
    void desativaPesquisadorInativado() {
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        controllerPesquisadorTeste.desativaPesquisador("arthur@example.com");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerPesquisadorTeste.desativaPesquisador("arthur@example.com");
        });
    }

    @Test
    void exibePesquisadorEmailInvalido() {
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.exibePesquisador("arthur@example.com");
        });
    }

    @Test
    void exibePesquisadorValido() {
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        assertEquals("Arthur (estudante) - estudante de Computação - arthur@example.com - https://formation",
                controllerPesquisadorTeste.exibePesquisador("arthur@example.com"));
    }

    @Test
    void pesquisadorEhAtivoEmailInvalido() {
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.pesquisadorEhAtivo("arthur@example.com");
        });
    }

    @Test
    void pesquisadorEhAtivoTrue(){
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        assertTrue(controllerPesquisadorTeste.pesquisadorEhAtivo("arthur@example.com"));
    }

    @Test
    void pesquisadorEhAtivoFalse(){
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        controllerPesquisadorTeste.desativaPesquisador("arthur@example.com");
        assertFalse(controllerPesquisadorTeste.pesquisadorEhAtivo("arthur@example.com"));
    }
}