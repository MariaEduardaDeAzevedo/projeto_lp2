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
		ControllerAtividades controllerAtividadesTest = new ControllerAtividades();
		ControllerBuscas controllerBuscasTest = new ControllerBuscas();
		ControllerObjetivos controllerObjetivoTest = new ControllerObjetivos();
		ControllerPesquisador controllerPesquisadorTest = new ControllerPesquisador();
		ControllerPesquisas controllerPesquisasTest = new ControllerPesquisas();
		ControllerProblemas controllerProblemasTest = new ControllerProblemas();

		controllerAtividadesTest.cadastrarAtividade("Cultivo de espécies de plantas por hidroponia", "BAIXO", "Não há risco de haver nenhum tipo de prejuízo com este experimento");
		controllerObjetivoTest.cadastraObjetivo("ESPECIFICO", "Buscar levar ciencia e tecnologia para fora dos muros da universidade", 5, 4);
		controllerPesquisadorTest.cadastraPesquisador("Arthur", "estudante", "estudante de Computação", "arthur@example.com", "https://formation");
		controllerPesquisasTest.cadastraPesquisa("pesquisa animais", "gosto de animais");
		controllerProblemasTest.cadastraProblema("Problema do acesso democratico a internet no Brasil", 4);
	}
	@Test
	void test() {
		controllerBuscasTest.busca("acesso", controllerPesquisasTest.getPesquisas(), controllerPesquisadorTest.getPesquisadores(),
				controllerProblemasTest.getProblemas(), controllerObjetivoTest.getObjetivos(), controllerAtividadesTest.getAtividades());;
	}

}
