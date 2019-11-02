package projeto_lp2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import base.Problema;

class ProblemaTest {

	private Problema problemaComum;

	@Test
	void testProblemaComum() {

		Problema problema = new Problema("A problematica de casos de homofobia em servicos de motorista por aplicativo",
				5, "P1");
		assertEquals("A problematica de casos de homofobia em servicos de motorista por aplicativo - 5",
				problema.toString());

	}

	@Test
	void testProblemaDescricaoVazia() {

		assertThrows(IllegalArgumentException.class, () -> {
			Problema problema = new Problema("", 5, "P1");
		});

	}
	
	
	void testProblemaDescricaoNull() {

		assertThrows(NullPointerException.class, () -> {
			Problema problema = new Problema(null, 5, "P1");
		});

	}

	
	@Test
	void testProblemaViabilidadeInvalidaMenor() {

		assertThrows(IllegalArgumentException.class, () -> {
			Problema problema = new Problema("A problematica da supremacia quantica", 0, "P1");
		});

	}

	@Test
	void testProblemaViabilidadeInvalidaMaior() {

		assertThrows(IllegalArgumentException.class, () -> {
			Problema problema = new Problema("A problematica da supremacia quantica", 6, "P1");
		});

	}
	
	@Test
	void testToString() {
		
		Problema problema = new Problema("A problematica de discursos de odio destinado a minorias no Twitter", 5, "P1");
		assertEquals("A problematica de discursos de odio destinado a minorias no Twitter - 5", problema.toString());
		
	}

}
