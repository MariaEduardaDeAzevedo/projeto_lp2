package controller;

import excecoes.ActivationException;
import excecoes.AssociationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConectorTest {

    private Conector cGeralTeste;
    private ControllerPesquisas cPesquisaTeste;
    private ControllerAtividades cAtividadeTeste;
	private ControllerPesquisador cPesquisadorTeste;
	private ControllerProblemas cProblemasTeste;
	private ControllerObjetivos cObjetivosTeste;
	
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
        this.cProblemasTeste = new ControllerProblemas();
        this.cObjetivosTeste = new ControllerObjetivos();
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
    
    @Test
    void associaProblemaValidoTrue() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	assertEquals("true", cGeralTeste.associaProblema(cPesquisaTeste, cProblemasTeste, "HUM1", "P2"));
    }
    
    @Test
    void associaProblemaValidoFalse() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cGeralTeste.associaProblema(cPesquisaTeste, cProblemasTeste, "HUM1", "P2");
    	assertEquals("false", cGeralTeste.associaProblema(cPesquisaTeste, cProblemasTeste, "HUM1", "P2"));
    }
    
    @Test
    void associaProblemaIdPesquisaNull() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.associaProblema(cPesquisaTeste, cProblemasTeste, null, "P2");
        });
    }
    
    @Test
    void associaProblemaIdPesquisaVazio() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	assertThrows(IllegalArgumentException.class, () -> {
    		cGeralTeste.associaProblema(cPesquisaTeste, cProblemasTeste, "", "P2");
        });
    }
    
    @Test
    void associaProblemaIdProblemaNull() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.associaProblema(cPesquisaTeste, cProblemasTeste, "HUM1", null);
        });
    }
    
    @Test
    void associaProblemaIdProblemaVazio() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	assertThrows(IllegalArgumentException.class, () -> {
    		cGeralTeste.associaProblema(cPesquisaTeste, cProblemasTeste, "HUM1", "");
        });
    }
    
    @Test
    void associaProblemaPesquisaJaAssociada() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cGeralTeste.associaProblema(cPesquisaTeste, cProblemasTeste, "HUM1", "P2");
    	assertThrows(AssociationException.class, () -> {
    		cGeralTeste.associaProblema(cPesquisaTeste, cProblemasTeste, "HUM1", "P1");
        });
    }
    
    @Test
    void desassociaProblemaValido() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cGeralTeste.associaProblema(cPesquisaTeste, cProblemasTeste, "HUM1", "P1");
    	assertEquals("true", cGeralTeste.desassociaProblema(cPesquisaTeste, "HUM1"));
    	cGeralTeste.desassociaProblema(cPesquisaTeste, "HUM1");
    	assertEquals("false", cGeralTeste.desassociaProblema(cPesquisaTeste, "HUM1"));
    }
    
    @Test
    void desassociaProblemaIdPesquisaNull() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cGeralTeste.associaProblema(cPesquisaTeste, cProblemasTeste, "HUM1", "P1");
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.desassociaProblema(cPesquisaTeste, null);
        });
    }
    
    @Test
    void desassociaProblemaIdPesquisaVazio() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cGeralTeste.associaProblema(cPesquisaTeste, cProblemasTeste, "HUM1", "P1");
    	assertThrows(IllegalArgumentException.class, () -> {
    		cGeralTeste.desassociaProblema(cPesquisaTeste, "");
        });
    }
    
    @Test
    void desassociaProblemaPesquisaInexistente() {
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.desassociaProblema(cPesquisaTeste, "HUM1");
        });
    }
    
    @Test
    void desassociaProblemaPesquisaNaoEncontrada() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cGeralTeste.associaProblema(cPesquisaTeste, cProblemasTeste, "HUM1", "P1");
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.desassociaProblema(cPesquisaTeste, "HUM2");
        });
    }
    
    @Test
    void desassociaProblemaPesquisaDesativada() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cGeralTeste.associaProblema(cPesquisaTeste, cProblemasTeste, "HUM1", "P1");
    	cPesquisaTeste.encerraPesquisa("HUM1", "Pesquisa censurada pelos Estados Unidos");
    	assertThrows(ActivationException.class, () -> {
    		cGeralTeste.desassociaProblema(cPesquisaTeste, "HUM1");
        });
    }
    
    @Test
    void associaObjetivoValido() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	assertEquals("true", cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, "HUM1", "O1"));
    	cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, "HUM1", "O1");
    	assertEquals("false", cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, "HUM1", "O1"));
    }
    
    @Test
    void associaObjetivoJaAssociado() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	cPesquisaTeste.cadastraPesquisa("Consequências do Colonialismo no século XXI", "historia");
    	cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, "HUM1", "O1");
    	assertThrows(AssociationException.class, () -> {
    		cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, "HIS1", "O1");
        });
    }
    
    @Test
    void associaObjetivoIdPesquisaNull() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, null, "O1");
        });
    }
    
    @Test
    void associaObjetivoIdPesquisaVazio() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	assertThrows(IllegalArgumentException.class, () -> {
    		cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, "", "O1");
        });
    }
    
    @Test
    void associaObjetivoIdProblemaNull() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, "HUM1", null);
        });
    }
    
    @Test
    void associaObjetivoIdProblemaVazio() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	assertThrows(IllegalArgumentException.class, () -> {
    		cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, "HUM1", "");
        });
    }
    
    @Test
    void associaObjetivoPesquisaNaoEncontrada() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, "HUM2", "O1");
        });
    }
    
    @Test
    void associaObjetivoPesquisaInexistente() {
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, "HUM1", "O1");
        });
    }
    
    @Test
    void associaObjetivoPesquisaDesativada() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	cPesquisaTeste.encerraPesquisa("HUM1", "Pesquisa censurada pelos imperialistas");
    	assertThrows(ActivationException.class, () -> {
    		cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, "HUM1", "O1");
        });
    }
    
    @Test
    void desassociaObjetivoValido() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, "HUM1", "O1");
    	assertEquals("true", cGeralTeste.desassociaObjetivo(cPesquisaTeste, cProblemasTeste, "HUM1", "O1"));
    	cGeralTeste.desassociaObjetivo(cPesquisaTeste, cProblemasTeste, "HUM1", "O1");
    	assertEquals("false", cGeralTeste.desassociaObjetivo(cPesquisaTeste, cProblemasTeste, "HUM1", "O1"));
    }
    
    @Test
    void desassociaObjetivoIdPesquisaNull() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, "HUM1", "O1");
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, null, "O1");
        });
    }
    
    @Test
    void desassociaObjetivoIdPesquisaVazio() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, "HUM1", "O1");
    	assertThrows(IllegalArgumentException.class, () -> {
    		cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, "", "O1");
        });
    }
    
    @Test
    void desassociaObjetivoIdObjetivoNull() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, "HUM1", "O1");
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, "HUM1", null);
        });
    }
    
    @Test
    void desassociaObjetivoIdObjetivoVazio() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, "HUM1", "O1");
    	assertThrows(IllegalArgumentException.class, () -> {
    		cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, "HUM1", "");
        });
    }
    
    @Test
    void desassociaObjetivoPesquisaNaoEncontrada() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.desassociaObjetivo(cPesquisaTeste, cProblemasTeste, "HUM2", "O1");
        });
    }
    
    @Test
    void desassociaObjetivoPesquisaInexistente() {
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.desassociaObjetivo(cPesquisaTeste, cProblemasTeste, "HUM1", "O1");
        });
    }
    
    @Test
    void desassociaObjetivoPesquisaDesativada() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	cGeralTeste.associaObjetivo(cPesquisaTeste, cObjetivosTeste, "HUM1", "O1");
    	cPesquisaTeste.encerraPesquisa("HUM1", "Pesquisa censurada pelos imperialistas");
    	assertThrows(ActivationException.class, () -> {
    		cGeralTeste.desassociaObjetivo(cPesquisaTeste, cProblemasTeste, "HUM1", "O1");
        });
    }
}