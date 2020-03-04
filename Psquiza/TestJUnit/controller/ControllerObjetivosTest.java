package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.ControllerObjetivos;

class ControllerObjetivosTest {

	private ControllerObjetivos controller;

	@BeforeEach
	void init() {
		
		this.controller = new ControllerObjetivos();
		
	}
	
	@Test
	void testCadastraObjetivoComum() {
		
		this.controller.cadastraObjetivo("ESPECIFICO", "Buscar levar ciencia e tecnologia para fora dos muros da universidade", 5, 4);
		
	}
	
	@Test
	void testCadastraObjetivoTipoVazio() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.cadastraObjetivo("", "Buscar levar ciencia e tecnologia para fora dos muros da universidade", 5, 4);
		});
		
	}
	
	@Test
	void testCadastraObjetivoTipoNull() {
		
		assertThrows(NullPointerException.class, () -> {
			this.controller.cadastraObjetivo(null, "Buscar levar ciencia e tecnologia para fora dos muros da universidade", 5, 4);
		});
		
	}
	
	@Test
	void testCadastraObjetivoTipoInexistente() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.cadastraObjetivo("MEDIANO", "Buscar levar ciencia e tecnologia para fora dos muros da universidade", 5, 4);
		});
		
	}
	
	@Test
	void testCadastraObjetivoDescricaoVazia() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.cadastraObjetivo("ESPECIFICO", "", 5, 4);
		});
		
	}
	
	@Test
	void testCadastraObjetivoDescricaoNull() {
		
		assertThrows(NullPointerException.class, () -> {
			this.controller.cadastraObjetivo("ESPECIFICO", null, 5, 4);
		});
		
	}
	
	@Test
	void testCadastraObjetivoAderenciaInexistenteMenor() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.cadastraObjetivo("ESPECIFICO", "Buscar levar ciencia e tecnologia para fora dos muros da universidade", 0, 4);
		});
		
	}
	
	@Test
	void testCadastraObjetivoAderenciaInexistenteMaior() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.cadastraObjetivo("ESPECIFICO", "Buscar levar ciencia e tecnologia para fora dos muros da universidade", 10, 4);
		});
		
	}
	
	@Test
	void testCadastraObjetivoViabilidadeInexistenteMenor() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.cadastraObjetivo("ESPECIFICO", "Buscar levar ciencia e tecnologia para fora dos muros da universidade", 4, -2);
		});
		
	}
	
	@Test
	void testCadastraObjetivoViabiliadeInexistenteMaior() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.cadastraObjetivo("ESPECIFICO", "Buscar levar ciencia e tecnologia para fora dos muros da universidade", 5, 20);
		});
		
	}
	
	@Test
	void testExibeObjetivoComum() throws Exception {
		
		this.controller.cadastraObjetivo("ESPECIFICO", "Buscar levar ciencia e tecnologia para fora dos muros da universidade", 5, 4);
		assertEquals(this.controller.exibeObjetivo("O1"), "O1 - ESPECIFICO - Buscar levar ciencia e tecnologia para fora dos muros da universidade - 9");
		
	}
	
	@Test
	void testExibeObjetivoInexistente() {
		
		this.controller.cadastraObjetivo("ESPECIFICO", "Buscar levar ciencia e tecnologia para fora dos muros da universidade", 5, 4);
		assertThrows(Exception.class, () -> {
			this.controller.exibeObjetivo("O2");
		});
		
	}
	
	@Test
	void testApagaObjetivoComum() throws Exception {
		
		this.controller.cadastraObjetivo("ESPECIFICO", "Buscar levar ciencia e tecnologia para fora dos muros da universidade", 5, 4);
		this.controller.apagarObjetivo("O1");
		assertThrows(Exception.class, () -> {
			this.controller.exibeObjetivo("O1");
		});
		
	}
	
	@Test
	void testApagaObjetivoInexistente() {
		
		assertThrows(Exception.class, () -> {
			this.controller.apagarObjetivo("O1");
		});
		
	}
}
