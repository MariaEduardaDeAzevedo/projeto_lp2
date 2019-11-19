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

		ControllerAtividades cAtividades = new ControllerAtividades();
		ControllerBuscas cBuscas = new ControllerBuscas();
		ControllerObjetivos cObjetivos = new ControllerObjetivos();
		ControllerPesquisador cPesquisadores = new ControllerPesquisador();
		ControllerPesquisas cPesquisas = new ControllerPesquisas();
		ControllerProblemas cProblemas = new ControllerProblemas();
		
		cAtividades.cadastrarAtividade("Monitoramento por ninjas de computacao na guerra ninja.", "BAIXO", "Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		cObjetivos.cadastraObjetivo("GERAL", "Diminuir a dor no coracao dos estudantes de computacao depois de ver rock lee vs gaara ao som de linkin park.", 4, 2);
		cPesquisadores.cadastraPesquisador("Gaara", "estudante", "Interessado na computacao com areia e em lutar com o rock lee ao som de linkin park.", "gaaraxrocklee@12LinkinPark", "https://areiaMassa");
		cPesquisas.cadastraPesquisa("pesquisa animais", "gosto de animais");
		cProblemas.cadastraProblema("O problema causado pela areia na segunda guerra mundial ninja de computacao.", 4);
		
		
	}
	
	@Test
	void buscaTermoComum() {
		
		assertEquals("COM2: computacao, poo | COM1: Homofobia em mensagens online de alunos de computacao do primeiro periodo. | COM1: computacao, homofobia | gaaraxrocklee@12LinkinPark: Interessado na computacao com areia e em lutar com o rock lee ao som de linkin park. | P4: O problema causado pela areia na segunda guerra mundial ninja de computacao. | P1: O problema do discurso homofobico em chats online de alunos de computacao de primeiro periodo | O6: Diminuir a dor no coracao dos estudantes de computacao depois de ver rock lee vs gaara ao som de linkin park. | O1: Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao. | A5: Monitoramento por ninjas de computacao na guerra ninja. | A1: Monitoramento de chats dos alunos de computacao do primeiro periodo.", cBuscas.busca("computacao", cPesquisas.getPesquisas(), cPesquisadores.getPesquisadores(), cProblemas.getProblemas(), cObjetivos.getObjetivos(), cAtividades.getAtividades()));
		
	
	}
	
	@Test
	void testBuscaTermoNull() {
		assertThrows(NullPointerException.class, () -> {
            cBuscas.busca(null, cPesquisas.getPesquisas(), cPesquisadores.getPesquisadores(), cProblemas.getProblemas(), cObjetivos.getObjetivos(), cAtividades.getAtividades());
        });

	}
	
	@Test
	void testBuscaTermoVazio() {
		assertThrows(NullPointerException.class, () -> {
            cBuscas.busca("", cPesquisas.getPesquisas(), cPesquisadores.getPesquisadores(), cProblemas.getProblemas(), cObjetivos.getObjetivos(), cAtividades.getAtividades());
        });

	}
	
	@Test
	void testBuscaResultadoComum() {
		assertEquals("COM1: computacao, homofobia", cBuscas.buscaResultado("computacao",  cPesquisas.getPesquisas(), cPesquisadores.getPesquisadores(), cProblemas.getProblemas(), cObjetivos.getObjetivos(), cAtividades.getAtividades(), 3));
	}
	
	@Test
	void testBuscaResultadoTermoNull() {
		assertThrows(NullPointerException.class, () -> {
            cBuscas.buscaResultado(null, cPesquisas.getPesquisas(), cPesquisadores.getPesquisadores(), cProblemas.getProblemas(), cObjetivos.getObjetivos(), cAtividades.getAtividades(), 1);
        });

	}
	
	@Test
	void testBuscaResultadoTermoVazio() {
		assertThrows(NullPointerException.class, () -> {
            cBuscas.buscaResultado("", cPesquisas.getPesquisas(), cPesquisadores.getPesquisadores(), cProblemas.getProblemas(), cObjetivos.getObjetivos(), cAtividades.getAtividades(), 1);
        });

	}
	
	@Test
	void testBuscaResultadoNumeroNull() {
		assertThrows(NullPointerException.class, () -> {
            cBuscas.buscaResultado("computacao", cPesquisas.getPesquisas(), cPesquisadores.getPesquisadores(), cProblemas.getProblemas(), cObjetivos.getObjetivos(), cAtividades.getAtividades(), 0);
        });

	}
}