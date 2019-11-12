package controller;

import base.Atividade;
import base.Objetivo;
import base.Pesquisador;
import base.Problema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.ControllerPesquisas;
import excecoes.ActivationException;
import excecoes.AssociationException;

import static org.junit.jupiter.api.Assertions.*;

class ControllerPesquisasTest {

	ControllerPesquisas controllerPesquisasTest;
	private ControllerPesquisador controllerPesquisadorTest;
	
	@BeforeEach
	void constroiController() {
		controllerPesquisasTest = new ControllerPesquisas();
		controllerPesquisadorTest = new ControllerPesquisador();
	}
	
	@Test
	void cadastraPesquisaValida() {
		controllerPesquisasTest.cadastraPesquisa("pesquisa animais", "gosto de animais");
	}
	
	@Test
	void cadastraPesquisaDescricaoVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisasTest.cadastraPesquisa("", "gosto de animais");
		});
	}
	
	@Test
	void cadastraPesquisaDescricaoNull() {
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisasTest.cadastraPesquisa(null, "gosto de animais");
		});
	}
	
	@Test
	void cadastraPesquisaCampoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisasTest.cadastraPesquisa("pesquisa animais", "");
		});
	}
	
	@Test
	void cadastraPesquisaCampoNull() {
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisasTest.cadastraPesquisa("pesquisa animais", null);
		});
	}
	
	@Test
	void cadastraPesquisatudoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisasTest.cadastraPesquisa("", "");
		});
	}
	
	@Test
	void cadastraPesquisaTudoNull() {
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisasTest.cadastraPesquisa(null, null);
		});
	}
	
	@Test
	void cadastraPesquisaCampoMaior255() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisasTest.cadastraPesquisa("pesquisa animais", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		});
	}
	
	@Test
	void cadastraPesquisaTopicosMaiores4() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisasTest.cadastraPesquisa("pesquisa animais", "cobra, gato, falcao, obelisco, arthur");
		});
	}
	
	@Test
	void cadastraPesquisaTopicoMenor3() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisasTest.cadastraPesquisa("pesquisa animais", "eu, me, amo");
		});
	}
	
	@Test
	void ativaPesquisaCodigoInvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisasTest.ativaPesquisa("pes1");
		});
	}	
	
	@Test
	void ativaPesquisaAtivada() {
		controllerPesquisasTest.cadastraPesquisa("pesquisa gravidas", "mulheres gravidas, bebes");
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisasTest.ativaPesquisa("mul1");
		});
	}	
	
	@Test
	void ativaPesquisaDesativada() {
		controllerPesquisasTest.cadastraPesquisa("pesquisa gravidas", "mulheres gravidas, bebes");
		controllerPesquisasTest.encerraPesquisa("MUL1", "no money");
		controllerPesquisasTest.ativaPesquisa("MUL1");
		assertTrue(controllerPesquisasTest.pesquisaEhAtiva("MUL1"));
	}	
	
	
	@Test
	void exibePesquisa() {
		controllerPesquisasTest.cadastraPesquisa("pesquisa gravidas", "mulheres gravidas, bebes");
		assertEquals("MUL1 - pesquisa gravidas - mulheres gravidas, bebes", controllerPesquisasTest.exibePesquisa("MUL1"));
	}
	
	@Test
	void alteraPesquisaCodigoValido() {
		controllerPesquisasTest.cadastraPesquisa("tecnologia", "notebooks caros, dell");
		controllerPesquisasTest.alteraPesquisa("NOT1", "DESCRICAO", "mts tecnologias");
		assertEquals("NOT1 - mts tecnologias - notebooks caros, dell", controllerPesquisasTest.exibePesquisa("NOT1"));
	}
	 
	@Test
	void alteraPesquisaCodigoVazio() {
		controllerPesquisasTest.cadastraPesquisa("tecnologia", "notebooks caros, dell");
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisasTest.alteraPesquisa("", "DESCRICAO", "mts tecnologias");
		});
	}
	
	@Test
	void alteraPesquisaCodigoNull() {
		controllerPesquisasTest.cadastraPesquisa("tecnologia", "notebooks caros, dell");
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisasTest.alteraPesquisa(null, "DESCRICAO", "mts tecnologias");
		});
	}

    @Test
    void associaAtividadeValida() {
		controllerPesquisasTest.cadastraPesquisa("computacao na neuropsicologia", "computacao, neuropsicologia");
    	Atividade A1 = new Atividade("Monitoramento de chats dos alunos de computacao do primeiro periodo",
				"BAIXO", "Por se tratar de apenas um monitoramento, o risco nao e elevado.", "A1");
		assertTrue(controllerPesquisasTest.associaAtividade("COM1", A1));
	}

	@Test
	void associaAtividadeValidaRepetida() {
		controllerPesquisasTest.cadastraPesquisa("computacao na neuropsicologia", "computacao, neuropsicologia");
		Atividade A1 = new Atividade("Monitoramento de chats dos alunos de computacao do primeiro periodo",
				"BAIXO", "Por se tratar de apenas um monitoramento, o risco nao e elevado.", "A1");
		controllerPesquisasTest.associaAtividade("COM1", A1);
		assertFalse(controllerPesquisasTest.associaAtividade("COM1", A1));
	}

	@Test
	void associaAtividadePesquisaInvalida() {
		Atividade A1 = new Atividade("Monitoramento de chats dos alunos de computacao do primeiro periodo",
				"BAIXO", "Por se tratar de apenas um monitoramento, o risco nao e elevado.", "A1");
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisasTest.associaAtividade("COM1", A1);
		});
	}

	@Test
	void associaAtividadePesquisaEncerrada() {
		controllerPesquisasTest.cadastraPesquisa("computacao na neuropsicologia", "computacao, neuropsicologia");
		controllerPesquisasTest.encerraPesquisa("COM1", "O lab fechou");
		Atividade A1 = new Atividade("Monitoramento de chats dos alunos de computacao do primeiro periodo",
				"BAIXO", "Por se tratar de apenas um monitoramento, o risco nao e elevado.", "A1");
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisasTest.associaAtividade("COM1", A1);
		});
	}

	@Test
	void desassociaAtividadeValida() {
		controllerPesquisasTest.cadastraPesquisa("computacao na neuropsicologia", "computacao, neuropsicologia");
		Atividade A1 = new Atividade("Monitoramento de chats dos alunos de computacao do primeiro periodo",
				"BAIXO", "Por se tratar de apenas um monitoramento, o risco nao e elevado.", "A1");
		controllerPesquisasTest.associaAtividade("COM1", A1);
		assertTrue(controllerPesquisasTest.desassociaAtividade("COM1", "A1"));
	}

	@Test
	void desassociaAtividadeValidaRepetida() {
		controllerPesquisasTest.cadastraPesquisa("computacao na neuropsicologia", "computacao, neuropsicologia");
		Atividade A1 = new Atividade("Monitoramento de chats dos alunos de computacao do primeiro periodo",
				"BAIXO", "Por se tratar de apenas um monitoramento, o risco nao e elevado.", "A1");
		controllerPesquisasTest.associaAtividade("COM1", A1);
		controllerPesquisasTest.desassociaAtividade("COM1", "A1");
		assertFalse(controllerPesquisasTest.desassociaAtividade("COM1", "A1"));
	}

	@Test
	void desassociaAtividadePesquisaInvalida() {
		Atividade A1 = new Atividade("Monitoramento de chats dos alunos de computacao do primeiro periodo",
				"BAIXO", "Por se tratar de apenas um monitoramento, o risco nao e elevado.", "A1");
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisasTest.desassociaAtividade("COM1", "A1");
		});
	}

	@Test
	void desassociaAtividadePesquisaEncerrada() {
		controllerPesquisasTest.cadastraPesquisa("computacao na neuropsicologia", "computacao, neuropsicologia");
		Atividade A1 = new Atividade("Monitoramento de chats dos alunos de computacao do primeiro periodo",
				"BAIXO", "Por se tratar de apenas um monitoramento, o risco nao e elevado.", "A1");
		controllerPesquisasTest.associaAtividade("COM1", A1);
		controllerPesquisasTest.encerraPesquisa("COM1", "O lab fechou");
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisasTest.desassociaAtividade("COM1", "A1");
		});
	}
	
	@Test
	void associaPesquisadorComum() {
		
		controllerPesquisasTest.cadastraPesquisa("Busca pelo aumento do protagonismo feminino na área de TI", "computação, sociologia");
		controllerPesquisadorTest.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		Pesquisador pesq = controllerPesquisadorTest.getPesquisador("professor@pesquisador.com");
		assertEquals(true, controllerPesquisasTest.associaPesquisador("COM1", pesq));
		
	}
	
	@Test
	void associaPesquisadorIDPesquisaNull() {
		
		controllerPesquisasTest.cadastraPesquisa("Busca pelo aumento do protagonismo feminino na área de TI", "computação, sociologia");
		controllerPesquisadorTest.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		Pesquisador pesq = controllerPesquisadorTest.getPesquisador("professor@pesquisador.com");
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisasTest.associaPesquisador(null, pesq);
		});
		
	}
	
	@Test
	void associaPesquisadorIDPesquisaVazio() {
		
		controllerPesquisasTest.cadastraPesquisa("Busca pelo aumento do protagonismo feminino na área de TI", "computação, sociologia");
		controllerPesquisadorTest.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		Pesquisador pesq = controllerPesquisadorTest.getPesquisador("professor@pesquisador.com");
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisasTest.associaPesquisador("", pesq);
		});
		
	}
	
	@Test
	void associaPesquisadorNull() {
		
		controllerPesquisasTest.cadastraPesquisa("Busca pelo aumento do protagonismo feminino na área de TI", "computação, sociologia");
		controllerPesquisadorTest.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisasTest.associaPesquisador("COM1", null);
		});
		
	}
	
	@Test
	void associaPesquisadorInexistente() {
		
		controllerPesquisasTest.cadastraPesquisa("Busca pelo aumento do protagonismo feminino na área de TI", "computação, sociologia");
		controllerPesquisadorTest.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		assertThrows(NullPointerException.class, () -> {
			Pesquisador pesq = controllerPesquisadorTest.getPesquisador("professor@professor.com");
			controllerPesquisasTest.associaPesquisador("COM1", pesq);
		});
		
	}
	
	@Test
	void associaPesquisadorJaAssociado() {
		
		controllerPesquisasTest.cadastraPesquisa("Busca pelo aumento do protagonismo feminino na área de TI", "computação, sociologia");
		controllerPesquisadorTest.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		Pesquisador pesq = controllerPesquisadorTest.getPesquisador("professor@pesquisador.com");
		controllerPesquisasTest.associaPesquisador("COM1", pesq);
		assertEquals(false, controllerPesquisasTest.associaPesquisador("COM1", pesq));
		
	}
	
	@Test
	void desassociaPesquisadorComum() {
		
		controllerPesquisasTest.cadastraPesquisa("Busca pelo aumento do protagonismo feminino na área de TI", "computação, sociologia");
		controllerPesquisadorTest.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		Pesquisador pesq = controllerPesquisadorTest.getPesquisador("professor@pesquisador.com");
		controllerPesquisasTest.associaPesquisador("COM1", pesq);
		assertEquals(true, (controllerPesquisasTest.desassociaPesquisador("COM1", "professor@pesquisador.com")));
	
	}
	
	@Test
	void desassociaPesquisadorIDPesquisaNull() {
		
		controllerPesquisasTest.cadastraPesquisa("Busca pelo aumento do protagonismo feminino na área de TI", "computação, sociologia");
		controllerPesquisadorTest.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		Pesquisador pesq = controllerPesquisadorTest.getPesquisador("professor@pesquisador.com");
		controllerPesquisasTest.associaPesquisador("COM1", pesq);
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisasTest.desassociaPesquisador(null, "professor@pesquisador.com");
		});
		
	}
	
	@Test
	void desassociaPesquisadorIDPesquisaVazio() {
		
		controllerPesquisasTest.cadastraPesquisa("Busca pelo aumento do protagonismo feminino na área de TI", "computação, sociologia");
		controllerPesquisadorTest.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		Pesquisador pesq = controllerPesquisadorTest.getPesquisador("professor@pesquisador.com");
		controllerPesquisasTest.associaPesquisador("COM1", pesq);
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisasTest.desassociaPesquisador("", "professor@pesquisador.com");
		});
		
	}
	
	@Test
	void desassociaPesquisadorEmailNull() {
		
		controllerPesquisasTest.cadastraPesquisa("Busca pelo aumento do protagonismo feminino na área de TI", "computação, sociologia");
		controllerPesquisadorTest.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		Pesquisador pesq = controllerPesquisadorTest.getPesquisador("professor@pesquisador.com");
		controllerPesquisasTest.associaPesquisador("COM1", pesq);
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisasTest.desassociaPesquisador("COM1", null);
		});
		
	}
	
	@Test
	void desassociaPesquisadorEmailVazio() {
		
		controllerPesquisasTest.cadastraPesquisa("Busca pelo aumento do protagonismo feminino na área de TI", "computação, sociologia");
		controllerPesquisadorTest.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		Pesquisador pesq = controllerPesquisadorTest.getPesquisador("professor@pesquisador.com");
		controllerPesquisasTest.associaPesquisador("COM1", pesq);
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisasTest.desassociaPesquisador("COM1", "");
		});
		
	}
	
	@Test
	void desassociaPesquisadorInexistente() {
		
		controllerPesquisasTest.cadastraPesquisa("Busca pelo aumento do protagonismo feminino na área de TI", "computação, sociologia");
		controllerPesquisadorTest.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		Pesquisador pesq = controllerPesquisadorTest.getPesquisador("professor@pesquisador.com");
		controllerPesquisasTest.associaPesquisador("COM1", pesq);
		assertEquals(false, controllerPesquisasTest.desassociaPesquisador("COM1", "pesquisador@professor.com"));
		
	}	

	@Test
	void desassociaPesquisadorJaDesassociado() {
		
		controllerPesquisasTest.cadastraPesquisa("Busca pelo aumento do protagonismo feminino na área de TI", "computação, sociologia");
		controllerPesquisadorTest.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		Pesquisador pesq = controllerPesquisadorTest.getPesquisador("professor@pesquisador.com");
		controllerPesquisasTest.associaPesquisador("COM1", pesq);
		controllerPesquisasTest.desassociaPesquisador("COM1", "professor@pesquisador.com");
		assertEquals(false, controllerPesquisasTest.desassociaPesquisador("COM1", "professor@pesquisador.com"));
		
	}

	
	@Test
	void associaProblemaValido() {
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		Problema problema = new Problema("A interferência americana nos países da América Latina", 4, "P1");
		assertEquals("true", controllerPesquisasTest.associaProblema("HUM1", "P1", problema));
		controllerPesquisasTest.associaProblema("HUM1", "P1", problema);
		assertEquals("false", controllerPesquisasTest.associaProblema("HUM1", "P1", problema));
	}
	
	@Test
	void associaProblemaIdPesquisaNull() {
		Problema problema = new Problema("A interferência americana nos países da América Latina", 4, "P1");
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisasTest.associaProblema(null, "P1", problema);
		});
	}
	
	@Test
	void associaProblemaIdPesquisaVazio() {
		Problema problema = new Problema("A interferência americana nos países da América Latina", 4, "P1");
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisasTest.associaProblema("", "P1", problema);
		});
	}
	
	@Test
	void associaProblemaIdProblemaNull() {
		Problema problema = new Problema("A interferência americana nos países da América Latina", 4, "P1");
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisasTest.associaProblema("HUM1", null, problema);
		});
	}
	
	@Test
	void associaProblemaIdProblemaVazio() {
		Problema problema = new Problema("A interferência americana nos países da América Latina", 4, "P1");
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisasTest.associaProblema("HUM1", "", problema);
		});
	}
	
	@Test
	void associaProblemaPesquisaJaAssociada() {
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		Problema problema = new Problema("A interferência americana nos países da América Latina", 4, "P1");
		controllerPesquisasTest.associaProblema("HUM1", "P1", problema);
		Problema problema2 = new Problema("Presença de tropas americanas no Oriente Médio", 4, "P2");
		assertThrows(AssociationException.class, () -> {
			controllerPesquisasTest.associaProblema("HUM1", "P2", problema2);
		});
	}
	
	@Test
	void desassociaProblemaValido() {
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		Problema problema = new Problema("A interferência americana nos países da América Latina", 4, "P1");
		controllerPesquisasTest.associaProblema("HUM1", "P1", problema);
		assertEquals("true", controllerPesquisasTest.desassociaProblema("HUM1"));
		controllerPesquisasTest.desassociaProblema("HUM1");
		assertEquals("false", controllerPesquisasTest.desassociaProblema("HUM1"));
	}
	
	@Test
	void desassociaProblemaIdPesquisaNull() {
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		Problema problema = new Problema("A interferência americana nos países da América Latina", 4, "P1");
		controllerPesquisasTest.associaProblema("HUM1", "P1", problema);
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisasTest.desassociaProblema(null);
		});
	}
	
	@Test
	void desassociaProblemaIdPesquisaVazio() {
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		Problema problema = new Problema("A interferência americana nos países da América Latina", 4, "P1");
		controllerPesquisasTest.associaProblema("HUM1", "P1", problema);
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisasTest.desassociaProblema("");
		});
	}
	
	@Test
    void desassociaProblemaPesquisaInexistente() {
    	assertThrows(NullPointerException.class, () -> {
    		controllerPesquisasTest.desassociaProblema("HUM1");
        });
    }
	
	@Test
	void desassociaProblemaPesquisaNaoEncontrada() {
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		Problema problema = new Problema("A interferência americana nos países da América Latina", 4, "P1");
		controllerPesquisasTest.associaProblema("HUM1", "P1", problema);
		assertThrows(NullPointerException.class, () -> {
    		controllerPesquisasTest.desassociaProblema("HUM2");
        });
	}
	
	@Test
    void desassociaProblemaPesquisaDesativada() {
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		Problema problema = new Problema("A interferência americana nos países da América Latina", 4, "P1");
		controllerPesquisasTest.associaProblema("HUM1", "P1", problema);
		controllerPesquisasTest.encerraPesquisa("HUM1", "Pesquisa censurada pelos Estados Unidos");
		assertThrows(ActivationException.class, () -> {
    		controllerPesquisasTest.desassociaProblema("HUM1");
        });
	}
	
	@Test
	void associaObjetivoValido() {
		Objetivo objetivo = new Objetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2, "O1");
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		assertEquals("true", controllerPesquisasTest.associaObjetivo("HUM1", "O1", objetivo));
		controllerPesquisasTest.associaObjetivo("HUM1", "O1", objetivo);
		assertEquals("false", controllerPesquisasTest.associaObjetivo("HUM1", "O1", objetivo));
	}
	
	@Test
	void associaObjetivoJaAssociado() {
		Objetivo objetivo = new Objetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2, "O1");
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		controllerPesquisasTest.cadastraPesquisa("Consequências do colonialismo no século XXI", "historia");
		controllerPesquisasTest.associaObjetivo("HUM1", "O1", objetivo);
		assertThrows(AssociationException.class, () -> {
    		controllerPesquisasTest.associaObjetivo("HIS1", "O1", objetivo);
        });
	}
	
	@Test
	void associaObjetivoIdPesquisaNull() {
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		Objetivo objetivo = new Objetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2, "O1");
		assertThrows(NullPointerException.class, () -> {
    		controllerPesquisasTest.associaObjetivo(null, "O1", objetivo);
        });
	}
	
	@Test
	void associaObjetivoIdPesquisaVazio() {
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		Objetivo objetivo = new Objetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2, "O1");
		assertThrows(IllegalArgumentException.class, () -> {
    		controllerPesquisasTest.associaObjetivo("", "O1", objetivo);
        });
	}
	
	@Test
	void associaObjetivoIdObjetivoNull() {
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		Objetivo objetivo = new Objetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2, "O1");
		assertThrows(NullPointerException.class, () -> {
    		controllerPesquisasTest.associaObjetivo("HUM1", null, objetivo);
        });
	}
	
	@Test
	void associaObjetivoIdObjetivoVazio() {
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		Objetivo objetivo = new Objetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2, "O1");
		assertThrows(IllegalArgumentException.class, () -> {
    		controllerPesquisasTest.associaObjetivo("HUM1", "", objetivo);
        });
	}
	
	@Test
	void associaObjetivoPesquisaNaoEncontrada() {
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		Objetivo objetivo = new Objetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2, "O1");
		assertThrows(NullPointerException.class, () -> {
    		controllerPesquisasTest.associaObjetivo("HUM2", "O1", objetivo);
        });
	}
	
	@Test
	void associaObjetivoPesquisaInexistente() {
		Objetivo objetivo = new Objetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2, "O1");
		assertThrows(NullPointerException.class, () -> {
    		controllerPesquisasTest.associaObjetivo("HUM1", "O1", objetivo);
        });
	}
	
	@Test
	void associaObjetivoPesquisaDesativada() {
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		Objetivo objetivo = new Objetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2, "O1");
		controllerPesquisasTest.encerraPesquisa("HUM1", "Pesquisa censurada pelos imperialistas");
		assertThrows(ActivationException.class, () -> {
    		controllerPesquisasTest.associaObjetivo("HUM1", "O1", objetivo);
        });
	}
	
	@Test
	void desassociaObjetivoValido() {
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		Objetivo objetivo = new Objetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2, "O1");
		controllerPesquisasTest.associaObjetivo("HUM1", "O1", objetivo);
		assertEquals("true", controllerPesquisasTest.desassociaObjetivo("HUM1", "O1"));
		controllerPesquisasTest.desassociaObjetivo("HUM1", "O1");
		assertEquals("false", controllerPesquisasTest.desassociaObjetivo("HUM1", "O1"));
	}
	
	@Test
	void desassociaObjetivoIdPesquisaNull() {
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		Objetivo objetivo = new Objetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2, "O1");
		controllerPesquisasTest.associaObjetivo("HUM1", "O1", objetivo);
		assertThrows(NullPointerException.class, () -> {
    		controllerPesquisasTest.desassociaObjetivo(null, "O1");
        });
	}
	
	@Test
	void desassociaObjetivoIdPesquisaVazio() {
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		Objetivo objetivo = new Objetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2, "O1");
		controllerPesquisasTest.associaObjetivo("HUM1", "O1", objetivo);
		assertThrows(IllegalArgumentException.class, () -> {
    		controllerPesquisasTest.desassociaObjetivo("", "O1");
        });
	}
	
	@Test
	void desassociaObjetivoIdObjetivoNull() {
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		Objetivo objetivo = new Objetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2, "O1");
		controllerPesquisasTest.associaObjetivo("HUM1", "O1", objetivo);
		assertThrows(NullPointerException.class, () -> {
    		controllerPesquisasTest.desassociaObjetivo("HUM1", null);
        });
	}
	
	@Test
	void desassociaObjetivoIdObjetivoVazio() {
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		Objetivo objetivo = new Objetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2, "O1");
		controllerPesquisasTest.associaObjetivo("HUM1", "O1", objetivo);
		assertThrows(IllegalArgumentException.class, () -> {
    		controllerPesquisasTest.desassociaObjetivo("HUM1", "");
        });
	}
	
	@Test
    void desassociaObjetivoPesquisaNaoEncontrada() {
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		Objetivo objetivo = new Objetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2, "O1");
		controllerPesquisasTest.associaObjetivo("HUM1", "O1", objetivo);
		assertThrows(NullPointerException.class, () -> {
    		controllerPesquisasTest.desassociaObjetivo("HUM2", "O1");
        });
	}
	
	@Test
	void desassociaObjetivoPesquisaInexistente() {
		assertThrows(NullPointerException.class, () -> {
    		controllerPesquisasTest.desassociaObjetivo("HUM1", "O1");
        });
	}
	
	@Test
	void desassociaObjetivoPesquisaDesativada() {
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		Objetivo objetivo = new Objetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2, "O1");
		controllerPesquisasTest.associaObjetivo("HUM1", "O1", objetivo);
		controllerPesquisasTest.encerraPesquisa("HUM1", "Pesquisa censurada pelos imperialistas");
		assertThrows(ActivationException.class, () -> {
    		controllerPesquisasTest.desassociaObjetivo("HUM1", "O1");
        });
	}
	
	@Test
	void listarObjetivos() {
		controllerPesquisasTest.cadastraPesquisa("Imperialismo americano no século XXI", "humanas, ciencias sociais");
		Objetivo objetivoImperialismo = new Objetivo("GERAL", "Alertar para os perigos das intervenções americanas em outros países", 4, 2, "O1");
		Objetivo consequenciasAmericaLatina = new Objetivo("ESPECIFICO", "Buscar as consequências do imperialismo americano na América Latina", 4, 4, "O2");
		Objetivo consequenciasOrienteMedio = new Objetivo("ESPECIFICO", "Buscar consequências da Guerra do Golfo", 4, 3, "O3");
		Objetivo consequenciasAsia = new Objetivo("ESPECIFICO", "Encontrar relação entre o desenvolvimento dos Tigres Asiáticos e o imperialismo", 4, 4, "04");
		controllerPesquisasTest.cadastraPesquisa("Imperialismo na America Latina", "humanas");
		controllerPesquisasTest.cadastraPesquisa("O desenvolvimento da mídia e a Guerra do Golfo", "humanas, midia");
		controllerPesquisasTest.cadastraPesquisa("Uma pesquisa bem legal", "Real Madrid");
		controllerPesquisasTest.associaObjetivo("HUM1", "O1", objetivoImperialismo);
		controllerPesquisasTest.associaObjetivo("HUM1", "O2", consequenciasAmericaLatina);
		controllerPesquisasTest.associaObjetivo("HUM1", "O3", consequenciasOrienteMedio);
		controllerPesquisasTest.associaObjetivo("HUM1", "O4", consequenciasAsia);
		controllerPesquisasTest.associaObjetivo("HUM2", "O2", consequenciasAmericaLatina);
		assertEquals("HUM1 - Imperialismo americano no século XXI - humanas, ciencias sociais | HUM2 - Imperialismo na America Latina - humanas | REA1 - Uma pesquisa bem legal - Real Madrid | HUM3 - O desenvolvimento da mídia e a Guerra do Golfo - humanas, midia", controllerPesquisasTest.listar("OBJETIVOS"));
	}
	
	@Test
	void listarProblemas() {
		
	}
}
