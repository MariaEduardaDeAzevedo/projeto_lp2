package projeto_lp2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.ControllerProblemas;

class ControllerProblemasTest {

	private ControllerProblemas controller;

	@BeforeEach
	void init() {
		
		this.controller = new ControllerProblemas();
		
	}
	
	@Test
	void testCadastraProblemaComum() throws Exception {
		
		this.controller.cadastraProblema("Problema do acesso democratico a internet no Brasil", 4);
		assertEquals("P1 - Problema do acesso democratico a internet no Brasil - 4", this.controller.exibeProblema("P1"));
		
	}
	
	@Test
	void testCadastraProblemaVazio() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.cadastraProblema("", 3);;
		});
		
	}
	
	@Test
	void testCadastraProblemaNull() {
		
		assertThrows(NullPointerException.class, () -> {
			this.controller.cadastraProblema(null, 2);;
		});
		
	}
	
	@Test
	void testCadastraProblemaNumeroInvalidoMenor() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.cadastraProblema("A problematica da democratizacao da tecnologia no Brasil", -1);;
		});
		
	}
	
	@Test
	void testCadastraProblemaNumeroInvalidoMaior() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.cadastraProblema("A problematica da democratizacao da tecnologia no Brasil", 9);;
		});
		
	}
	
	@Test
	void testExibeProblemaComum() throws Exception {
		
		this.controller.cadastraProblema("Problema do acesso democratico a internet no Brasil", 4);
		assertEquals("P1 - Problema do acesso democratico a internet no Brasil - 4", this.controller.exibeProblema("P1"));
		
	}
	
	@Test
	void testExibeProblemaInexistente() {
		
		assertThrows(Exception.class, () -> {
			this.controller.exibeProblema("P9");
		});
		
	}
	
	@Test
	void testApagaProblemaComum() throws Exception {
		
		this.controller.cadastraProblema("O problema da democratizacao da ciencia e tecnologia no Brasil", 4);
		this.controller.apagarProblema("P1");
		assertThrows(Exception.class, () -> {
			this.controller.exibeProblema("P1");
		});
		
	}
	
	@Test
	void testApagarProblemaInexistente() {
		
		this.controller.cadastraProblema("O problema da democratizacao da ciencia e tecnologia no Brasil", 4);
		assertThrows(Exception.class, () -> {
			this.controller.apagarProblema("P10");;
		});
		
	}
}
