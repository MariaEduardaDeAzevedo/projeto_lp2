package projeto_lp2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import base.Objetivo;

class ObjetivoTest {

	@Test
	void testObjetivoComum() {

		Objetivo objetivo = new Objetivo("GERAL", "Estimular mulheres a seguir em profissoes na area de TI", 4, 4,
				"O1");
		assertEquals(objetivo.toString(), "GERAL - Estimular mulheres a seguir em profissoes na area de TI - 8");

	}

	@Test
	void testObjetivoTipoVazio() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			Objetivo objetivo = new Objetivo("", "Estimular mulheres a seguir em profissoes na area de TI", 4, 4, "O1");
		});
		
	}
	
	@Test
	void testObjetivoTipoNull() {
		
		assertThrows(NullPointerException.class, () -> {
			Objetivo objetivo = new Objetivo(null, "Estimular mulheres a seguir em profissoes na area de TI", 4, 4, "O1");
		});
		
	}
	
	@Test
	void testObjetivoTipoInexistente() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			Objetivo objetivo = new Objetivo("MEDIANO", "Estimular mulheres a seguir em profissoes na area de TI", 4, 4, "O1");
		});
		
	}
	
	@Test
	void testObjetivoDescricaoVazia() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			Objetivo objetivo = new Objetivo("GERAL", "", 4, 4, "O1");
		});
		
	}
	
	@Test
	void testObjetivoDescricaoNull() {
		
		assertThrows(NullPointerException.class, () -> {
			Objetivo objetivo = new Objetivo("GERAL", null, 4, 4, "O1");
		});
		
	}
	
	@Test
	void testObjetivoIDVazio() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			Objetivo objetivo = new Objetivo("GERAL", "Estimular mulheres a seguir em profissoes na area de TI", 4, 4, "");
		});
		
	}
	
	@Test
	void testObjetivoIDNull() {
		
		assertThrows(NullPointerException.class, () -> {
			Objetivo objetivo = new Objetivo("GERAL", "Estimular mulheres a seguir em profissoes na area de TI", 4, 4, null);
		});
		
	}
	
	@Test
	void testHashCode() {
		
		Objetivo o1 = new Objetivo("GERAL", "Estimular mulheres a seguir em profissoes na area de TI", 4, 4, "O1");
		Objetivo o2 = new Objetivo("GERAL", "Estimular mulheres a seguir em profissoes na area de TI", 4, 4, "O1");

		assert o1.hashCode() == o2.hashCode();
		
	}
	
	@Test
	void testEqualsObjetosIguais() {
		
		Objetivo o1 = new Objetivo("GERAL", "Estimular mulheres a seguir em profissoes na area de TI", 4, 4, "O1");
		Objetivo o2 = new Objetivo("GERAL", "Estimular mulheres a seguir em profissoes na area de TI", 4, 4, "O1");

		assertEquals(o1.equals(o2), true);
		
	}
	
	@Test
	void testEqualsObjetosDiferentes() {
		

		Objetivo o1 = new Objetivo("GERAL", "Estimular mulheres a seguir em profissoes na area de TI", 4, 4, "O1");
		Objetivo o2 = new Objetivo("GERAL", "Estimular mulheres a seguir em profissoes na area de TI", 4, 4, "O2");

		assertEquals(o1.equals(o2), false);
		
	}
	
	@Test
	void testEqualsMesmoObjeto() {
		
		Objetivo o1 = new Objetivo("GERAL", "Estimular mulheres a seguir em profissoes na area de TI", 4, 4, "O1");
		
		assertEquals(o1.equals(o1), true);
		
	}
	
	@Test
	void testEqualsObjetoENull() {
		
		Objetivo o1 = new Objetivo("GERAL", "Estimular mulheres a seguir em profissoes na area de TI", 4, 4, "O1");
		
		assertEquals(o1.equals(null), false);
		
	}

	@Test
	void testEqualsOutraClasse() {
		
		Objetivo o1 = new Objetivo("GERAL", "Estimular mulheres a seguir em profissoes na area de TI", 4, 4, "O1");
		
		assertEquals(o1.equals("Isto e uma String :)"), false);
		
	}
	
	@Test
	void testToString() {
		
		Objetivo objetivo = new Objetivo("GERAL", "Estimular mulheres a seguir em profissoes na area de TI", 4, 4,
				"O1");
		assertEquals(objetivo.toString(), "GERAL - Estimular mulheres a seguir em profissoes na area de TI - 8");
		
	}
	
}
