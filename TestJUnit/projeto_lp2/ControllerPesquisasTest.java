package projeto_lp2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.ControllerPesquisas;

import static org.junit.jupiter.api.Assertions.*;

class ControllerPesquisasTest {

	ControllerPesquisas controllerPesquisasTest;
	
	@BeforeEach
	void constroiController() {
		controllerPesquisasTest = new ControllerPesquisas();
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
	
	
	
	


}
