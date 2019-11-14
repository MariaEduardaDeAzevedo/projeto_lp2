package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ControllerBuscasTest {

	private ControllerAtividades controllerAtividadesTest;
	private ControllerBuscas controllerBuscasTest;
	private ControllerObjetivos controllerObjetivoTest;
	private ControllerPesquisador controllerPesquisadorTest;
	private ControllerPesquisas controllerPesquisasTest;
	private ControllerProblemas controllerProblemasTest;
	
	@BeforeEach
	void criaControllers() {
		ControllerAtividades cAtividade = new ControllerAtividades();
		ControllerBuscas cBuscas = new ControllerBuscas();
		ControllerObjetivos cObjetivos = new ControllerObjetivos();
		ControllerPesquisador cPesquisador = new ControllerPesquisador();
		ControllerPesquisas cPesquisas = new ControllerPesquisas();
		ControllerProblemas cProblemas = new ControllerProblemas();
		
		cAtividade.cadastrarAtividade("Cultivo de espécies de plantas por hidroponia", "BAIXO", "Não há risco de haver nenhum tipo de prejuízo com este experimento");
		cObjetivos.cadastraObjetivo("ESPECIFICO", "Buscar levar ciencia e tecnologia para fora dos muros da universidade", 5, 4);
		cPesquisador.cadastraPesquisador("Arthur", "estudante", "estudante de Computação", "arthur@example.com", "https://formation");
		cPesquisas.cadastraPesquisa("pesquisa animais", "gosto de animais");
		cProblemas.cadastraProblema("Problema do acesso democratico a internet no Brasil", 4);
		
		
	}
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
