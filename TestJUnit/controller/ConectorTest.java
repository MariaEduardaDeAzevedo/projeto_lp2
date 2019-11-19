package controller;

import excecoes.ActivationException;
import excecoes.AssociationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class ConectorTest {

    private Conector cGeralTeste;
    private ControllerPesquisas cPesquisaTeste;
    private ControllerAtividades cAtividadeTeste;
	private ControllerPesquisador cPesquisadorTeste;
	private ControllerProblemas cProblemasTeste;
	private ControllerObjetivos cObjetivosTeste;
	private ControllerBuscas cBuscasTeste;
	private File dados;
	
    @BeforeEach
    void inicializaAtributos() {
        this.cPesquisaTeste = new ControllerPesquisas();
        this.cAtividadeTeste = new ControllerAtividades();
        this.cPesquisadorTeste = new ControllerPesquisador();
        cPesquisadorTeste.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "pesquisador@pesquisador.com", "https://pic_prof");
        cPesquisaTeste.cadastraPesquisa("computacao na neuropsicologia", "computacao, neuropsicologia");
        cAtividadeTeste.cadastrarAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.",
                "BAIXO", "Por se tratar de apenas um monitoramento, o risco nao e elevado.");
        this.cProblemasTeste = new ControllerProblemas();
        this.cObjetivosTeste = new ControllerObjetivos();
        this.cGeralTeste = new Conector(cPesquisadorTeste, cPesquisaTeste, cProblemasTeste, cAtividadeTeste, cObjetivosTeste, null);
        this.cBuscasTeste = new ControllerBuscas();
        this.cGeralTeste = new Conector(cPesquisadorTeste, cPesquisaTeste, cProblemasTeste,
                cAtividadeTeste, cObjetivosTeste, cBuscasTeste);
        this.dados = new File("Arquivo");
    }
    @Test
    void associaAtividadeValida() {
        assertTrue(cGeralTeste.associaAtividade("COM1", "A1"));
    }

    @Test
    void associaAtividadeValidaRepetida() {
        cGeralTeste.associaAtividade("COM1", "A1");
        assertFalse(cGeralTeste.associaAtividade("COM1", "A1"));
    }

    @Test
    void associaAtividadeCodigoPesquisaVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            cGeralTeste.associaAtividade("", "A1");
        });
    }

    @Test
    void associaAtividadeCodigoPesquisaNull() {
        assertThrows(NullPointerException.class, () -> {
            cGeralTeste.associaAtividade(null, "A1");
        });
    }

    @Test
    void associaAtividadeCodigoAtividadeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            cGeralTeste.associaAtividade("COM1", "");
        });
    }

    @Test
    void associaAtividadeCodigoAtividadeNull() {
        assertThrows(NullPointerException.class, () -> {
            cGeralTeste.associaAtividade("COM1", null);
        });
    }

    @Test
    void associaAtividadeCodigoPesquisaInvalido() {
        assertThrows(NullPointerException.class, () -> {
            cGeralTeste.associaAtividade("PAM1", "A1");
        });
    }

    @Test
    void associaAtividadePesquisaDesativada() {
        cPesquisaTeste.encerraPesquisa("COM1", "O lab fechou");
        assertThrows(ActivationException.class, () -> {
            cGeralTeste.associaAtividade("COM1", "A1");
        });
    }

    @Test
    void associaAtividadeCodigoAtividadeInvalido() {
        assertThrows(NullPointerException.class, () -> {
            cGeralTeste.associaAtividade("COM1", "A3");
        });
    }

    @Test
    void desassociaAtividadeValida() {
        cGeralTeste.associaAtividade("COM1", "A1");
        assertTrue(cGeralTeste.desassociaAtividade("COM1", "A1"));
    }

    @Test
    void desassociaAtividadeValidaRepetida() {
        cGeralTeste.associaAtividade("COM1", "A1");
        cGeralTeste.desassociaAtividade("COM1", "A1");
        assertFalse(cGeralTeste.desassociaAtividade("COM1", "A1"));
    }

    @Test
    void desassociaAtividadeCodigoPesquisaVazio() {
        cGeralTeste.associaAtividade("COM1", "A1");
        assertThrows(IllegalArgumentException.class, () -> {
            cGeralTeste.desassociaAtividade("", "A1");
        });
    }

    @Test
    void desassociaAtividadeCodigoPesquisaNull() {
        cGeralTeste.associaAtividade("COM1", "A1");
        assertThrows(NullPointerException.class, () -> {
            cGeralTeste.desassociaAtividade(null, "A1");
        });
    }

    @Test
    void desassociaAtividadeCodigoAtividadeVazio() {
        cGeralTeste.associaAtividade("COM1", "A1");
        assertThrows(IllegalArgumentException.class, () -> {
            cGeralTeste.desassociaAtividade("COM1", "");
        });
    }

    @Test
    void deassociaAtividadeCodigoAtividadeNull() {
        cGeralTeste.associaAtividade("COM1", "A1");
        assertThrows(NullPointerException.class, () -> {
            cGeralTeste.desassociaAtividade("COM1", null);
        });
    }

    @Test
    void deassociaAtividadeCodigoPesquisaInvalido() {
        cGeralTeste.associaAtividade("COM1", "A1");
        assertThrows(IllegalArgumentException.class, () -> {
            cGeralTeste.desassociaAtividade("PAM1", "A1");
        });
    }

    @Test
    void deassociaAtividadePesquisaDesativada() {
        cGeralTeste.associaAtividade("COM1", "A1");
        cPesquisaTeste.encerraPesquisa("COM1", "O lab fechou");
        assertThrows(IllegalArgumentException.class, () -> {
            cGeralTeste.desassociaAtividade("COM1", "A1");
        });
    }

    @Test
    void deassociaAtividadeCodigoAtividadeInvalido() {
        cGeralTeste.associaAtividade("COM1", "A1");
        assertThrows(NullPointerException.class, () -> {
            cGeralTeste.desassociaAtividade("COM1", "A3");
        });
    }

    @Test
    void executaAtividadeValida() {
        cGeralTeste.associaAtividade("COM1", "A1");
        cAtividadeTeste.cadastrarItem("A1", "Monitoramento das hashtags como forma de tentar prever resultados das eleicoes");
        cGeralTeste.executaAtividade("A1", 1, 50);
        assertEquals(1, cAtividadeTeste.getAtividade("A1").contaItensRealizados());
    }

    @Test
    void executaAtividadeRepetida() {
        cGeralTeste.associaAtividade("COM1", "A1");
        cAtividadeTeste.cadastrarItem("A1", "Monitoramento das hashtags como forma de tentar prever resultados das eleicoes");
        cGeralTeste.executaAtividade("A1", 1, 50);
        assertThrows(ActivationException.class, () -> {
            cGeralTeste.executaAtividade("A1", 1, 59);
        });
    }

    @Test
    void executaAtividadeNaoAssociada() {
        assertThrows(IllegalArgumentException.class, () -> {
            cGeralTeste.executaAtividade("A1", 1, 50);
        });
    }

    @Test
    void executaAtividadeCodigoAtividadeVazio() {
        cGeralTeste.associaAtividade("COM1", "A1");
        assertThrows(IllegalArgumentException.class, () -> {
            cGeralTeste.executaAtividade("", 1, 50);
        });
    }

    @Test
    void executaAtividadeCodigoAtividadeNull() {
        cGeralTeste.associaAtividade("COM1", "A1");
        cAtividadeTeste.cadastrarItem("A1", "Monitoramento das hashtags como forma de tentar prever resultados das eleicoes");
        assertThrows(NullPointerException.class, () -> {
            cGeralTeste.executaAtividade(null, 1, 50);
        });
    }

    @Test
    void executaAtividadeItemNulo() {
        cGeralTeste.associaAtividade("COM1", "A1");
        cAtividadeTeste.cadastrarItem("A1", "Monitoramento das hashtags como forma de tentar prever resultados das eleicoes");
        assertThrows(IllegalArgumentException.class, () -> {
            cGeralTeste.executaAtividade("A1", 0, 50);
        });
    }

    @Test
    void executaAtividadeItemNegativo() {
        cGeralTeste.associaAtividade("COM1", "A1");
        cAtividadeTeste.cadastrarItem("A1", "Monitoramento das hashtags como forma de tentar prever resultados das eleicoes");
        assertThrows(IllegalArgumentException.class, () -> {
            cGeralTeste.executaAtividade("A1", -1, 50);
        });
    }

    @Test
    void executaAtividadeItemInvalido() {
        cGeralTeste.associaAtividade("COM1", "A1");
        cAtividadeTeste.cadastrarItem("A1", "Monitoramento das hashtags como forma de tentar prever resultados das eleicoes");
        assertThrows(IllegalArgumentException.class, () -> {
            cGeralTeste.executaAtividade("A1", 5, 50);
        });
    }

    @Test
    void executaAtividadeDuracaoNula() {
        cGeralTeste.associaAtividade("COM1", "A1");
        cAtividadeTeste.cadastrarItem("A1", "Monitoramento das hashtags como forma de tentar prever resultados das eleicoes");
        assertThrows(IllegalArgumentException.class, () -> {
            cGeralTeste.executaAtividade("A1", 1, 0);
        });
    }

    @Test
    void executaAtividadeDuracaoNegativa() {
        cGeralTeste.associaAtividade("COM1", "A1");
        cAtividadeTeste.cadastrarItem("A1", "Monitoramento das hashtags como forma de tentar prever resultados das eleicoes");
        assertThrows(IllegalArgumentException.class, () -> {
            cGeralTeste.executaAtividade("A1", 1, -1);
        });
    }
    
    @Test
    void associaPesquisadorComum() {
    	
    	this.cGeralTeste.associaPesquisador("COM1", "pesquisador@pesquisador.com");
    	
    }
    
    @Test
    void associaPesquisadorInexistente() {
    	
    	 assertThrows(NullPointerException.class, () -> {
    		 this.cGeralTeste.associaPesquisador("COM1", "pesquisador@professor.com");;
         });
    	
    	
    }
    
    @Test
    void associaPesquisadorPesquisaInexistente() {
    	
    	assertThrows(NullPointerException.class, () -> {
   		 	this.cGeralTeste.associaPesquisador("COM2", "pesquisador@pesquisador.com");
        });
    	
    }
    
    @Test
    void associaPesquisadorIDNull() {
    	
    	assertThrows(NullPointerException.class, () -> {
   		 	this.cGeralTeste.associaPesquisador(null, "pesquisador@pesquisador.com");
        });
    	
    }
    
    @Test
    void associaPesquisadorIDVazio() {
    	
    	assertThrows(IllegalArgumentException.class, () -> {
   		 	this.cGeralTeste.associaPesquisador("", "pesquisador@pesquisador.com");
        });
    	
    }
    
    @Test
    void associaPesquisadorEmailNull() {
    	
    	assertThrows(NullPointerException.class, () -> {
   		 	this.cGeralTeste.associaPesquisador("COM1", null);
        });
    	
    }
    
    @Test
    void associaPesquisadorEmailVazio() {
    	
    	assertThrows(IllegalArgumentException.class, () -> {
   		 	this.cGeralTeste.associaPesquisador("COM1", "");
        });
    	
    }
    
    @Test
    void associaPesquisadorPesquisaInativa() {
    	
    	this.cPesquisaTeste.encerraPesquisa("COM1", "falta de verba");
    	assertThrows(ActivationException.class, () -> {
   		 	this.cGeralTeste.associaPesquisador("COM1", "pesquisador@pesquisador.com");
        });
    	
    }
    
    @Test
    void desassociaPesquisadorComum() {
    	
    	this.cGeralTeste.desassociaPesquisador("COM1", "pesquisador@pesquisador.com");
    	
    }
    
    @Test
    void desassociaPesquisadorIDNull() {
    	
    	assertThrows(NullPointerException.class, () -> {
    		this.cGeralTeste.desassociaPesquisador(null, "pesquisador@pesquisador.com");
        });
    	
    }
    
    @Test
    void desassociaPesquisadorIDVazio() {
    	
    	assertThrows(IllegalArgumentException.class, () -> {
    		this.cGeralTeste.desassociaPesquisador("", "pesquisador@pesquisador.com");
        });
    	
    }
    
    @Test
    void desassociaPesquisadorNull() {
    	
    	assertThrows(NullPointerException.class, () -> {
    		this.cGeralTeste.desassociaPesquisador("COM1", null);
        });
    	
    }
    
    @Test
    void desassociaPesquisadorVazio() {
    	
    	assertThrows(IllegalArgumentException.class, () -> {
    		this.cGeralTeste.desassociaPesquisador("COM1", "");
        });
    	
    }

    @Test
    void associaProblemaValidoTrue() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	assertEquals(true, cGeralTeste.associaProblema("HUM1", "P2"));
    }
    
    @Test
    void associaProblemaValidoFalse() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cGeralTeste.associaProblema("HUM1", "P2");
    	assertEquals(false, cGeralTeste.associaProblema("HUM1", "P2"));
    }
    
    @Test
    void associaProblemaIdPesquisaNull() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.associaProblema(null, "P2");
        });
    }
    
    @Test
    void associaProblemaIdPesquisaVazio() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	assertThrows(IllegalArgumentException.class, () -> {
    		cGeralTeste.associaProblema("", "P2");
        });
    }
    
    @Test
    void associaProblemaIdProblemaNull() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.associaProblema("HUM1", null);
        });
    }
    
    @Test
    void associaProblemaIdProblemaVazio() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	assertThrows(IllegalArgumentException.class, () -> {
    		cGeralTeste.associaProblema("HUM1", "");
        });
    }
    
    @Test
    void associaProblemaPesquisaJaAssociada() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cGeralTeste.associaProblema("HUM1", "P2");
    	assertThrows(AssociationException.class, () -> {
    		cGeralTeste.associaProblema("HUM1", "P1");
        });
    }
    
    @Test
    void desassociaProblemaValido() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cGeralTeste.associaProblema("HUM1", "P1");
    	assertEquals(true, cGeralTeste.desassociaProblema("HUM1"));
    	cGeralTeste.desassociaProblema("HUM1");
    	assertEquals(false, cGeralTeste.desassociaProblema("HUM1"));
    }
    
    @Test
    void desassociaProblemaIdPesquisaNull() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cGeralTeste.associaProblema("HUM1", "P1");
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.desassociaProblema(null);
        });
    }
    
    @Test
    void desassociaProblemaIdPesquisaVazio() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cGeralTeste.associaProblema("HUM1", "P1");
    	assertThrows(IllegalArgumentException.class, () -> {
    		cGeralTeste.desassociaProblema("");
        });
    }
    
    @Test
    void desassociaProblemaPesquisaInexistente() {
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.desassociaProblema("HUM1");
        });
    }
    
    @Test
    void desassociaProblemaPesquisaNaoEncontrada() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cGeralTeste.associaProblema("HUM1", "P1");
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.desassociaProblema("HUM2");
        });
    }
    
    @Test
    void desassociaProblemaPesquisaDesativada() {
    	cProblemasTeste.cadastraProblema("A interferência americana nos países da América Latina", 4);
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cGeralTeste.associaProblema("HUM1", "P1");
    	cPesquisaTeste.encerraPesquisa("HUM1", "Pesquisa censurada pelos Estados Unidos");
    	assertThrows(ActivationException.class, () -> {
    		cGeralTeste.desassociaProblema("HUM1");
        });
    }
    
    @Test
    void associaObjetivoValido() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	assertEquals(true, cGeralTeste.associaObjetivo("HUM1", "O1"));
    	cGeralTeste.associaObjetivo("HUM1", "O1");
    	assertEquals(false, cGeralTeste.associaObjetivo("HUM1", "O1"));
    }
    
    @Test
    void associaObjetivoJaAssociado() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	cPesquisaTeste.cadastraPesquisa("Consequências do Colonialismo no século XXI", "historia");
    	cGeralTeste.associaObjetivo("HUM1", "O1");
    	assertThrows(AssociationException.class, () -> {
    		cGeralTeste.associaObjetivo("HIS1", "O1");
        });
    }
    
    @Test
    void associaObjetivoIdPesquisaNull() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.associaObjetivo(null, "O1");
        });
    }
    
    @Test
    void associaObjetivoIdPesquisaVazio() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	assertThrows(IllegalArgumentException.class, () -> {
    		cGeralTeste.associaObjetivo("", "O1");
        });
    }
    
    @Test
    void associaObjetivoIdProblemaNull() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.associaObjetivo("HUM1", null);
        });
    }
    
    @Test
    void associaObjetivoIdProblemaVazio() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	assertThrows(IllegalArgumentException.class, () -> {
    		cGeralTeste.associaObjetivo("HUM1", "");
        });
    }
    
    @Test
    void associaObjetivoPesquisaNaoEncontrada() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.associaObjetivo("HUM2", "O1");
        });
    }
    
    @Test
    void associaObjetivoPesquisaInexistente() {
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.associaObjetivo("HUM1", "O1");
        });
    }
    
    @Test
    void associaObjetivoPesquisaDesativada() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	cPesquisaTeste.encerraPesquisa("HUM1", "Pesquisa censurada pelos imperialistas");
    	assertThrows(ActivationException.class, () -> {
    		cGeralTeste.associaObjetivo("HUM1", "O1");
        });
    }
    
    @Test
    void desassociaObjetivoValido() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	cGeralTeste.associaObjetivo("HUM1", "O1");
    	assertEquals(true, cGeralTeste.desassociaObjetivo("HUM1", "O1"));
    	cGeralTeste.desassociaObjetivo("HUM1", "O1");
    	assertEquals(false, cGeralTeste.desassociaObjetivo("HUM1", "O1"));
    }
    
    @Test
    void desassociaObjetivoIdPesquisaNull() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	cGeralTeste.associaObjetivo("HUM1", "O1");
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.associaObjetivo(null, "O1");
        });
    }
    
    @Test
    void desassociaObjetivoIdPesquisaVazio() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	cGeralTeste.associaObjetivo("HUM1", "O1");
    	assertThrows(IllegalArgumentException.class, () -> {
    		cGeralTeste.associaObjetivo("", "O1");
        });
    }
    
    @Test
    void desassociaObjetivoIdObjetivoNull() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	cGeralTeste.associaObjetivo("HUM1", "O1");
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.associaObjetivo("HUM1", null);
        });
    }
    
    @Test
    void desassociaObjetivoIdObjetivoVazio() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	cGeralTeste.associaObjetivo("HUM1", "O1");
    	assertThrows(IllegalArgumentException.class, () -> {
    		cGeralTeste.associaObjetivo("HUM1", "");
        });
    }
    
    @Test
    void desassociaObjetivoPesquisaNaoEncontrada() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.desassociaObjetivo("HUM2", "O1");
        });
    }
    
    @Test
    void desassociaObjetivoPesquisaInexistente() {
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	assertThrows(NullPointerException.class, () -> {
    		cGeralTeste.desassociaObjetivo("HUM1", "O1");
        });
    }
    
    @Test
    void desassociaObjetivoPesquisaDesativada() {
    	cPesquisaTeste.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
    	cObjetivosTeste.cadastraObjetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2);
    	cGeralTeste.associaObjetivo("HUM1", "O1");
    	cPesquisaTeste.encerraPesquisa("HUM1", "Pesquisa censurada pelos imperialistas");
    	assertThrows(ActivationException.class, () -> {
    		cGeralTeste.desassociaObjetivo("HUM1", "O1");
        });
    }
    
    @Test
    void salvarArquivosComum() {
    	
    	this.cGeralTeste.salvarArquivos();
    	List<String> valores = new ArrayList<String>();
    	valores.add("Pesquisas");
    	valores.add("Pesquisadores");
    	valores.add("Objetivos");
    	valores.add("Problemas");
    	valores.add("Atividades");
    	valores.add("Problemas Associados");
    	valores.add("Objetivos Associados");
    	valores.add("Proximo ID das Atividades");
    	valores.add("Proximo ID dos Objetivos");
    	valores.add("Proximo ID dos Problemas");
    	
    	boolean resultado = true;
    	
    	for (String s : this.dados.list()) {
    		if (! valores.contains(s)) {
    			resultado = false;
    			break;	
    		}
    		File file = new File("Arquivo" + File.separator + s);
    		file.delete();
    	}
    	assertEquals(true, resultado);	
    }
}