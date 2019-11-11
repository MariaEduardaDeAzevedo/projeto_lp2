package controller;

import base.Atividade;
import base.Pesquisador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.ControllerPesquisas;

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

}
