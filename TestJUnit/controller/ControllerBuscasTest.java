package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import base.Atividade;
import base.Objetivo;
import base.Pesquisa;
import base.Pesquisador;
import base.Problema;

class ControllerBuscasTest {

	private ControllerAtividades cAtividades;
	private ControllerBuscas cBuscas;
	private ControllerObjetivos cObjetivos;
	private ControllerPesquisador cPesquisadores;
	private ControllerPesquisas cPesquisas;
	private ControllerProblemas cProblemas;

	@BeforeEach
	void criaControllers() {

		this.cAtividades = new ControllerAtividades();
		this.cBuscas = new ControllerBuscas();
		this.cObjetivos = new ControllerObjetivos();
		this.cPesquisadores = new ControllerPesquisador();
		this.cPesquisas = new ControllerPesquisas();
		this.cProblemas = new ControllerProblemas();

		cAtividades.cadastrarAtividade("Monitoramento por ninjas de computacao na guerra ninja.", "BAIXO",
				"Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		cObjetivos.cadastraObjetivo("GERAL",
				"Diminuir a dor no coracao dos estudantes de computacao depois de ver rock lee vs gaara ao som de linkin park.",
				4, 2);
		cPesquisadores.cadastraPesquisador("Gaara", "estudante",
				"Interessado na computacao com areia e em lutar com o rock lee ao som de linkin park.",
				"gaaraxrocklee@12LinkinPark", "https://areiaMassa");
		cPesquisas.cadastraPesquisa("pesquisa animais", "gosto de animais");
		cProblemas.cadastraProblema("O problema causado pela areia na segunda guerra mundial ninja de computacao.", 4);

	}

	@Test
	void buscaTermoComum() {

		cBuscas.busca("computacao", cPesquisas.getPesquisas(), cPesquisadores.getPesquisadores(),
				cProblemas.getProblemas(), cObjetivos.getObjetivos(), cAtividades.getAtividades());

	}

	@Test
	void testBuscaTermoNull() {
		assertThrows(NullPointerException.class, () -> {
			cBuscas.busca(null, cPesquisas.getPesquisas(), cPesquisadores.getPesquisadores(), cProblemas.getProblemas(),
					cObjetivos.getObjetivos(), cAtividades.getAtividades());
		});

	}

	@Test
	void testBuscaTermoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			cBuscas.busca("", cPesquisas.getPesquisas(), cPesquisadores.getPesquisadores(), cProblemas.getProblemas(),
					cObjetivos.getObjetivos(), cAtividades.getAtividades());
		});

	}

	@Test
	void testBuscaResultadoComum() {
		
		cBuscas.buscaResultado("computacao", cPesquisas.getPesquisas(), cPesquisadores.getPesquisadores(),
						cProblemas.getProblemas(), cObjetivos.getObjetivos(), cAtividades.getAtividades(), 3);
	}

	@Test
	void testBuscaResultadoTermoNull() {
		assertThrows(NullPointerException.class, () -> {
			cBuscas.buscaResultado(null, cPesquisas.getPesquisas(), cPesquisadores.getPesquisadores(),
					cProblemas.getProblemas(), cObjetivos.getObjetivos(), cAtividades.getAtividades(), 1);
		});

	}

	@Test
	void testBuscaResultadoTermoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			cBuscas.buscaResultado("", cPesquisas.getPesquisas(), cPesquisadores.getPesquisadores(),
					cProblemas.getProblemas(), cObjetivos.getObjetivos(), cAtividades.getAtividades(), 1);
		});

	}

	@Test
	void testBuscaResultadoNumeroNull() {
		assertThrows(IndexOutOfBoundsException.class, () -> {
			cBuscas.buscaResultado("computacao", cPesquisas.getPesquisas(), cPesquisadores.getPesquisadores(),
					cProblemas.getProblemas(), cObjetivos.getObjetivos(), cAtividades.getAtividades(), 0);
		});

	}
	
	
	
	
}