package controller;

import excecoes.ActivationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConectorTest {

    private Conector cGeralTeste;
    private ControllerPesquisas cPesquisaTeste;
    private ControllerAtividades cAtividadeTeste;
	private ControllerPesquisador cPesquisadorTeste;

    @BeforeEach
    void inicializaAtributos() {
        this.cGeralTeste = new Conector();
        this.cPesquisaTeste = new ControllerPesquisas();
        this.cAtividadeTeste = new ControllerAtividades();
        this.cPesquisadorTeste = new ControllerPesquisador();
        cPesquisadorTeste.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "pesquisador@pesquisador.com", "https://pic_prof");
        cPesquisaTeste.cadastraPesquisa("computacao na neuropsicologia", "computacao, neuropsicologia");
        cAtividadeTeste.cadastrarAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.",
                "BAIXO", "Por se tratar de apenas um monitoramento, o risco nao e elevado.");
    }

    @Test
    void associaAtividadeValida() {
        assertTrue(cGeralTeste.associaAtividade(cPesquisaTeste, cAtividadeTeste, "COM1", "A1"));
    }

    @Test
    void associaAtividadeValidaRepetida() {
        cGeralTeste.associaAtividade(cPesquisaTeste, cAtividadeTeste, "COM1", "A1");
        assertFalse(cGeralTeste.associaAtividade(cPesquisaTeste, cAtividadeTeste, "COM1", "A1"));
    }

    @Test
    void associaAtividadeCodigoPesquisaVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            cGeralTeste.associaAtividade(cPesquisaTeste, cAtividadeTeste, "", "A1");
        });
    }

    @Test
    void associaAtividadeCodigoPesquisaNull() {
        assertThrows(NullPointerException.class, () -> {
            cGeralTeste.associaAtividade(cPesquisaTeste, cAtividadeTeste, null, "A1");
        });
    }

    @Test
    void associaAtividadeCodigoAtividadeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            cGeralTeste.associaAtividade(cPesquisaTeste, cAtividadeTeste, "COM1", "");
        });
    }

    @Test
    void associaAtividadeCodigoAtividadeNull() {
        assertThrows(NullPointerException.class, () -> {
            cGeralTeste.associaAtividade(cPesquisaTeste, cAtividadeTeste, "COM1", null);
        });
    }

    @Test
    void associaAtividadeCodigoPesquisaInvalido() {
        assertThrows(NullPointerException.class, () -> {
            cGeralTeste.associaAtividade(cPesquisaTeste, cAtividadeTeste, "PAM1", "A1");
        });
    }

    @Test
    void associaAtividadePesquisaDesativada() {
        cPesquisaTeste.encerraPesquisa("COM1", "O lab fechou");
        ActivationException exception = new ActivationException("Pesquisa desativada.");
        assertThrows(IllegalArgumentException.class, () -> {
            cGeralTeste.associaAtividade(cPesquisaTeste, cAtividadeTeste, "COM1", "A1");
        });
    }

    @Test
    void associaAtividadeCodigoAtividadeInvalido() {
        assertThrows(NullPointerException.class, () -> {
            cGeralTeste.associaAtividade(cPesquisaTeste, cAtividadeTeste, "COM1", "A3");
        });
    }

    @Test
    void desassociaAtividadeValida() {
        cGeralTeste.associaAtividade(cPesquisaTeste, cAtividadeTeste, "COM1", "A1");
        assertTrue(cGeralTeste.desassociaAtividade(cPesquisaTeste, "COM1", "A1"));
    }

    @Test
    void desassociaAtividadeValidaRepetida() {
        cGeralTeste.associaAtividade(cPesquisaTeste, cAtividadeTeste, "COM1", "A1");
        cGeralTeste.desassociaAtividade(cPesquisaTeste, "COM1", "A1");
        assertFalse(cGeralTeste.desassociaAtividade(cPesquisaTeste, "COM1", "A1"));
    }

    @Test
    void desassociaAtividadeCodigoPesquisaVazio() {
        cGeralTeste.associaAtividade(cPesquisaTeste, cAtividadeTeste, "COM1", "A1");
        assertThrows(IllegalArgumentException.class, () -> {
            cGeralTeste.desassociaAtividade(cPesquisaTeste, "", "A1");
        });
    }

    @Test
    void desassociaAtividadeCodigoPesquisaNull() {
        cGeralTeste.associaAtividade(cPesquisaTeste, cAtividadeTeste, "COM1", "A1");
        assertThrows(NullPointerException.class, () -> {
            cGeralTeste.desassociaAtividade(cPesquisaTeste, null, "A1");
        });
    }

    @Test
    void desassociaAtividadeCodigoAtividadeVazio() {
        cGeralTeste.associaAtividade(cPesquisaTeste, cAtividadeTeste, "COM1", "A1");
        assertThrows(IllegalArgumentException.class, () -> {
            cGeralTeste.desassociaAtividade(cPesquisaTeste, "COM1", "");
        });
    }

    @Test
    void deassociaAtividadeCodigoAtividadeNull() {
        cGeralTeste.associaAtividade(cPesquisaTeste, cAtividadeTeste, "COM1", "A1");
        assertThrows(NullPointerException.class, () -> {
            cGeralTeste.desassociaAtividade(cPesquisaTeste, "COM1", null);
        });
    }

    @Test
    void deassociaAtividadeCodigoPesquisaInvalido() {
        cGeralTeste.associaAtividade(cPesquisaTeste, cAtividadeTeste, "COM1", "A1");
        assertThrows(NullPointerException.class, () -> {
            cGeralTeste.desassociaAtividade(cPesquisaTeste, "PAM1", "A1");
        });
    }

    @Test
    void deassociaAtividadePesquisaDesativada() {
        cGeralTeste.associaAtividade(cPesquisaTeste, cAtividadeTeste, "COM1", "A1");
        cPesquisaTeste.encerraPesquisa("COM1", "O lab fechou");
        assertThrows(IllegalArgumentException.class, () -> {
            cGeralTeste.desassociaAtividade(cPesquisaTeste, "COM1", "A1");
        });
    }

    @Test
    void deassociaAtividadeCodigoAtividadeInvalido() {
        cGeralTeste.associaAtividade(cPesquisaTeste, cAtividadeTeste, "COM1", "A1");
        assertThrows(IllegalArgumentException.class, () -> {
            cGeralTeste.desassociaAtividade(cPesquisaTeste, "COM1", "A3");
        });
    }
    
    @Test
    void associaPesquisadorComum() {
    	
    	this.cGeralTeste.associaPesquisador(cPesquisadorTeste, cPesquisaTeste, "COM1", "pesquisador@pesquisador.com");
    	
    }
    
    @Test
    void associaPesquisadorInexistente() {
    	
    	 assertThrows(NullPointerException.class, () -> {
    		 this.cGeralTeste.associaPesquisador(cPesquisadorTeste, cPesquisaTeste, "COM1", "pesquisador@professor.com");;
         });
    	
    	
    }
    
    @Test
    void associaPesquisadorPesquisaInexistente() {
    	
    	assertThrows(NullPointerException.class, () -> {
   		 	this.cGeralTeste.associaPesquisador(cPesquisadorTeste, cPesquisaTeste, "COM2", "pesquisador@pesquisador.com");
        });
    	
    }
    
    @Test
    void associaPesquisadorIDNull() {
    	
    	assertThrows(NullPointerException.class, () -> {
   		 	this.cGeralTeste.associaPesquisador(cPesquisadorTeste, cPesquisaTeste, null, "pesquisador@pesquisador.com");
        });
    	
    }
    
    @Test
    void associaPesquisadorIDVazio() {
    	
    	assertThrows(IllegalArgumentException.class, () -> {
   		 	this.cGeralTeste.associaPesquisador(cPesquisadorTeste, cPesquisaTeste, "", "pesquisador@pesquisador.com");
        });
    	
    }
    
    @Test
    void associaPesquisadorEmailNull() {
    	
    	assertThrows(NullPointerException.class, () -> {
   		 	this.cGeralTeste.associaPesquisador(cPesquisadorTeste, cPesquisaTeste, "COM1", null);
        });
    	
    }
    
    @Test
    void associaPesquisadorEmailVazio() {
    	
    	assertThrows(IllegalArgumentException.class, () -> {
   		 	this.cGeralTeste.associaPesquisador(cPesquisadorTeste, cPesquisaTeste, "COM1", "");
        });
    	
    }
    
    @Test
    void associaPesquisadorPesquisaInativa() {
    	
    	this.cPesquisaTeste.encerraPesquisa("COM1", "falta de verba");
    	assertThrows(ActivationException.class, () -> {
   		 	this.cGeralTeste.associaPesquisador(cPesquisadorTeste, cPesquisaTeste, "COM1", "pesquisador@pesquisador.com");
        });
    	
    }
    
    @Test
    void desassociaPesquisadorComum() {
    	
    	this.cGeralTeste.desassociaPesquisador(cPesquisaTeste, "COM1", "pesquisador@pesquisador.com");
    	
    }
    
    @Test
    void desassociaPesquisadorIDNull() {
    	
    	assertThrows(NullPointerException.class, () -> {
    		this.cGeralTeste.desassociaPesquisador(cPesquisaTeste, null, "pesquisador@pesquisador.com");
        });
    	
    }
    
    @Test
    void desassociaPesquisadorIDVazio() {
    	
    	assertThrows(IllegalArgumentException.class, () -> {
    		this.cGeralTeste.desassociaPesquisador(cPesquisaTeste, "", "pesquisador@pesquisador.com");
        });
    	
    }
    
    @Test
    void desassociaPesquisadorNull() {
    	
    	assertThrows(NullPointerException.class, () -> {
    		this.cGeralTeste.desassociaPesquisador(cPesquisaTeste, "COM1", null);
        });
    	
    }
    
    @Test
    void desassociaPesquisadorVazio() {
    	
    	assertThrows(IllegalArgumentException.class, () -> {
    		this.cGeralTeste.desassociaPesquisador(cPesquisaTeste, "COM1", "");
        });
    	
    }
}