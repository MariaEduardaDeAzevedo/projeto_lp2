package projeto_lp2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import base.Pesquisa;
import excecoes.ActivationException;

class PesquisaTest {

	Pesquisa pesquisaTeste;
	Pesquisa pesquisaTesteIgual;
	Pesquisa pesquisaTesteDiferente;

	@BeforeEach
	void criaPesquisa() {
		pesquisaTeste = new Pesquisa("animal", "pesquisa animais", "vida animal");
		pesquisaTesteIgual = new Pesquisa("animal", "pesquisa animais", "vida animal");
		pesquisaTesteDiferente = new Pesquisa("politica", "pesquisa sobre politica", "corrupcao");

	}

	@Test
	void criaPesquisaCodigoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			Pesquisa pesquisa1 = new Pesquisa("", "pesquisa teclados", "gosto de teclados");
		});
	}

	@Test
	void criaPesquisaCodigoNull() {
		assertThrows(NullPointerException.class, () -> {
			Pesquisa pesquisa1 = new Pesquisa(null, "pesquisa teclados", "gosto de teclados");
		});
	}

	@Test
	void criaPesquisaDescricaoVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			Pesquisa pesquisa1 = new Pesquisa("teclado", "", "gosto de teclados");
		});
	}

	@Test
	void criaPesquisaDescricaoNull() {
		assertThrows(NullPointerException.class, () -> {
			Pesquisa pesquisa1 = new Pesquisa("teclado", null, "gosto de teclados");
		});
	}

	@Test
	void criaPesquisaCampoInteresseVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			Pesquisa pesquisa1 = new Pesquisa("teclado", "gosto de teclados", "");
		});
	}

	@Test
	void criaPesquisaCampoInteresseNull() {
		assertThrows(NullPointerException.class, () -> {
			Pesquisa pesquisa1 = new Pesquisa("teclado", "pesquisa teclados", null);
		});
	}

	@Test
	void criaPesquisaTudoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			Pesquisa pesquisa1 = new Pesquisa("", "", "");
		});
	}

	@Test
	void criaPesquisaTudoNull() {
		assertThrows(NullPointerException.class, () -> {
			Pesquisa pesquisa1 = new Pesquisa(null, null, null);
		});
	}

	@Test
	void toStringPesquisa() {
		assertEquals("animal - pesquisa animais - vida animal", pesquisaTeste.toString());

	}

	@Test
	void ativaPesquisaAtivada() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaTeste.ativaPesquisa();
		});
	}

	@Test
	void ativaPesquisaDesativada() {
		pesquisaTeste.encerraPesquisa("Verba cortada");
		pesquisaTeste.ativaPesquisa();
		assertTrue(pesquisaTeste.isAtivada());
	}

	@Test
	void inativaPesquisaAtivada() {
		pesquisaTeste.encerraPesquisa("Concluída");
		assertFalse(pesquisaTeste.isAtivada());
	}

	@Test
	void inativaPesquisaDesativada() {
		pesquisaTeste.encerraPesquisa("Corte de verbas");
		assertThrows(ActivationException.class, () -> {
			pesquisaTeste.encerraPesquisa("Corte de verbas");
		});
	}

	@Test
	void alteraPesquisaConteudoASerAlteradoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaTeste.alteraPesquisa("", "gosto de mouse");
	    });
	}
	
	@Test
	void alteraPesquisaConteudoASerAlteradoNull() {
		assertThrows(NullPointerException.class, () -> {
			pesquisaTeste.alteraPesquisa(null, "gosto de mouse");
	    });
	}
	
	@Test
	void alteraPesquisaNovoConteudoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaTeste.alteraPesquisa("DESCRICAO", "");
	    });
	}
	
	@Test
	void alteraPesquisaNovoConteudoNull() {
		assertThrows(NullPointerException.class, () -> {
			pesquisaTeste.alteraPesquisa("DESCRICAO", null);
	    });
	}
	
	@Test
	void alteraPesquisaTudoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaTeste.alteraPesquisa("", "");
	    });
	}
	
	@Test
	void alteraPesquisaTudoNull() {
		assertThrows(NullPointerException.class, () -> {
			pesquisaTeste.alteraPesquisa(null, null);
	    });
	}
	
	@Test
    void hashCodePesquisa(){
        assertEquals(pesquisaTeste.hashCode(), pesquisaTesteIgual.hashCode());
    }
	
	@Test
    void equalsTrue(){
        assertTrue(pesquisaTeste.equals(pesquisaTesteIgual));
    }

    @Test
    void equalsTrueMesmoObjeto(){
        assertTrue(pesquisaTeste.equals(pesquisaTeste));
    }

    @Test
    void equalsFalse(){
        assertFalse(pesquisaTeste.equals(pesquisaTesteDiferente));
    }

    @Test
    void equalsFalseNull(){
        assertFalse(pesquisaTeste.equals(null));
    }


}
