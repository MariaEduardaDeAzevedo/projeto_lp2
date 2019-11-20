package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.ControllerAtividades;
import excecoes.ActivationException;

class ControllerAtividadesTest {
	
	private ControllerAtividades controller1;
	private ControllerAtividades controller2;
	private ControllerAtividades controller3;
	
	@BeforeEach
	void criaControllers() {
		controller1 = new ControllerAtividades();
		controller2 = new ControllerAtividades();
		controller3 = new ControllerAtividades();
		controller2.cadastrarAtividade("Cultivo de espécies de plantas por hidroponia", "BAIXO", "Não há risco de haver nenhum tipo de prejuízo com este experimento");
		controller3.cadastrarAtividade("Cultivo de espécies de plantas por hidroponia", "BAIXO", "Não há risco de haver nenhum tipo de prejuízo com este experimento");
		controller3.cadastrarAtividade("Busca de mensagens que espalham notícias falsas nos chats dos alunos de computação", "MEDIO", "Acusar uma notícia falsa pode constranger o remetente desta.");
		controller3.cadastrarAtividade("Busca de uma forma de unificar a teoria da relatividade geral ao princípio da incerteza como forma de buscar as condições iniciais do universo através da utilização de um acelerador de partículas", "ALTO", "Qualquer erro matemático nos cálculos pode ocasionar uma falha no acelerador de partículas, o que pode causar danos irreversíveis ao local do experimento");
		controller3.cadastrarItem("A2", "Monitoramento no Slack das disciplinas de programação do primeiro e do segundo período de computação");
		controller3.cadastrarItem("A3", "Verificar se a condição sem contorno do universo é verdadeira");
		controller3.cadastrarItem("A3", "Calcular a energia necessária para simular as condiçoes iniciais do universo num acelerador de partículas");
	}

	@Test
	void testControllerAtividades() {
		assertThrows(NullPointerException.class, () -> {
            controller1.exibirAtividade("A1");
        });
	}

	@Test
	void testCadastrarAtividadeValido() {
		assertEquals("A1", controller1.cadastrarAtividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", "BAIXO", "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo"));
		assertEquals("A2", controller2.cadastrarAtividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", "BAIXO", "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo"));
		assertEquals("A4", controller3.cadastrarAtividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", "BAIXO", "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo"));
	}
	
	@Test
	void testCadastrarAtividadeDescricaoNull() {
		assertThrows(NullPointerException.class, () -> {
			controller1.cadastrarAtividade(null, "BAIXO", "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo");
			controller2.cadastrarAtividade(null, "BAIXO", "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo");
			controller3.cadastrarAtividade(null, "BAIXO", "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo");
        });
	}
	
	@Test
	void testCadastrarAtividadeDescricaoVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller1.cadastrarAtividade("", "BAIXO", "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo");
			controller2.cadastrarAtividade("", "BAIXO", "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo");
			controller3.cadastrarAtividade("", "BAIXO", "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo");
        });
	}
	
	@Test
	void testCadastrarAtividadeRiscoNull() {
		assertThrows(NullPointerException.class, () -> {
			controller1.cadastrarAtividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", null, "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo");
			controller2.cadastrarAtividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", null, "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo");
			controller3.cadastrarAtividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", null, "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo");
        });
	}
	
	@Test
	void testCadastrarAtividadeRiscoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller1.cadastrarAtividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", "", "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo");
			controller2.cadastrarAtividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", "", "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo");
			controller3.cadastrarAtividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", "", "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo");
        });
	}
	
	@Test
	void testCadastraAtividadeRiscoInvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller1.cadastrarAtividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", "MUITO BAIXO", "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo");
			controller2.cadastrarAtividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", "BAIXÍSSIMO", "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo");
			controller3.cadastrarAtividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", "BEM BAIXO", "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo");
        });
	}
	
	@Test
	void testCadastrarAtividadeDescricaoRiscoNull() {
		assertThrows(NullPointerException.class, () -> {
			controller1.cadastrarAtividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", "BAIXO", null);
			controller2.cadastrarAtividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", "BAIXO", null);
			controller3.cadastrarAtividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", "BAIXO", null);
        });
	}
	
	@Test
	void testCadastrarAtividadeDescricaoRiscoVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller1.cadastrarAtividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", "BAIXO", "");
			controller2.cadastrarAtividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", "BAIXO", "");
			controller3.cadastrarAtividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", "BAIXO", "");
        });
	}

	@Test
	void testExibirAtividadeValido() {
		assertEquals("Cultivo de espécies de plantas por hidroponia (BAIXO - Não há risco de haver nenhum tipo de prejuízo com este experimento)", controller2.exibirAtividade("A1"));
		assertEquals("Cultivo de espécies de plantas por hidroponia (BAIXO - Não há risco de haver nenhum tipo de prejuízo com este experimento)", controller3.exibirAtividade("A1"));
		assertEquals("Busca de mensagens que espalham notícias falsas nos chats dos alunos de computação (MEDIO - Acusar uma notícia falsa pode constranger o remetente desta.) | PENDENTE - Monitoramento no Slack das disciplinas de programação do primeiro e do segundo período de computação", controller3.exibirAtividade("A2"));
		assertEquals("Busca de uma forma de unificar a teoria da relatividade geral ao princípio da incerteza como forma de buscar as condições iniciais do universo através da utilização de um acelerador de partículas (ALTO - Qualquer erro matemático nos cálculos pode ocasionar uma falha no acelerador de partículas, o que pode causar danos irreversíveis ao local do experimento) | PENDENTE - Verificar se a condição sem contorno do universo é verdadeira | PENDENTE - Calcular a energia necessária para simular as condiçoes iniciais do universo num acelerador de partículas", controller3.exibirAtividade("A3"));
	}
	
	@Test
	void testExibirAtividadeIdNull() {
		assertThrows(NullPointerException.class, () -> {
			controller1.exibirAtividade(null);
			controller2.exibirAtividade(null);
			controller3.exibirAtividade(null);
        });
	}
	
	@Test
	void testExibirAtividadeIdVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller1.exibirAtividade("");
			controller2.exibirAtividade("");
			controller3.exibirAtividade("");
        });
	}
	
	@Test
	void testExibirAtividadeIdInvalido() {
		assertThrows(NullPointerException.class, () -> {
			controller1.exibirAtividade("A1");
			controller2.exibirAtividade("A97");
			controller3.exibirAtividade("A25");
        });
	}

	@Test
	void testCadastrarItemValido() {
		assertEquals(0, controller2.contaItensPendentes("A1"));
		assertEquals(0, controller2.contaItensRealizados("A1"));
		controller2.cadastrarItem("A1", "Pesquisar a salinidade adequada da água para o cultivo por hidroponia");
		assertEquals(1, controller2.contaItensPendentes("A1"));
		assertEquals(0, controller2.contaItensRealizados("A1"));
		
		assertEquals(0, controller3.contaItensPendentes("A1"));
		assertEquals(0, controller3.contaItensRealizados("A1"));
		controller3.cadastrarItem("A1", "Pesquisar a salinidade adequada da água para o cultivo por hidroponia");
		assertEquals(1, controller3.contaItensPendentes("A1"));
		assertEquals(0, controller3.contaItensRealizados("A1"));
		
		assertEquals(1, controller3.contaItensPendentes("A2"));
		assertEquals(0, controller3.contaItensRealizados("A2"));
		controller3.cadastrarItem("A2", "Buscar semelhanças entre as mensagens que espalhavam notícias falsas");
		assertEquals(2, controller3.contaItensPendentes("A2"));
		assertEquals(0, controller3.contaItensRealizados("A2"));
		
		assertEquals(2, controller3.contaItensPendentes("A3"));
		assertEquals(0, controller3.contaItensRealizados("A3"));
		controller3.cadastrarItem("A3", "Pesquisar se a condição de singularidade no início do universo é demonstrável com a energia disponível");
		assertEquals(3, controller3.contaItensPendentes("A3"));
		assertEquals(0, controller3.contaItensRealizados("A3"));
	}
	
	@Test
	void testCadastrarItemIdNull() {
		assertThrows(NullPointerException.class, () -> {
			controller1.cadastrarItem(null, "Busca dos motivos do fracasso dos planos de Gorbachev");
			controller2.cadastrarItem(null, "Pesquisar a salinidade adequada da água para o cultivo por hidroponia");
			controller3.cadastrarItem(null, "Buscar semelhanças entre as mensagens que espalhavam notícias falsas");
        });
	}
	
	@Test
	void testCadastrarItemIdVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller1.cadastrarItem("", "Busca dos motivos do fracasso dos planos de Gorbachev");
			controller2.cadastrarItem("", "Pesquisar a salinidade adequada da água para o cultivo por hidroponia");
			controller3.cadastrarItem("", "Buscar semelhanças entre as mensagens que espalhavam notícias falsas");
        });
	}
	
	@Test
	void testCadastrarItemAtividadeInexistente() {
		assertThrows(NullPointerException.class, () -> {
			controller1.cadastrarItem("A1", "Busca dos motivos do fracasso dos planos de Gorbachev");
			controller2.cadastrarItem("A56", "Pesquisar a salinidade adequada da água para o cultivo por hidroponia");
			controller3.cadastrarItem("A7", "Buscar semelhanças entre as mensagens que espalhavam notícias falsas");
        });
	}

	@Test
	void testApagaAtividadeValido() {
		controller2.apagaAtividade("A1");
		controller3.apagaAtividade("A1");
		controller3.apagaAtividade("A2");
		controller3.apagaAtividade("A3");
		assertThrows(NullPointerException.class, () -> {
			controller2.exibirAtividade("A1");
			controller3.exibirAtividade("A1");
			controller3.exibirAtividade("A2");
			controller3.exibirAtividade("A3");
        });
	}
	
	@Test
	void testApagaAtividadeIdNull() {
		assertThrows(NullPointerException.class, () -> {
			controller1.apagaAtividade(null);
			controller2.apagaAtividade(null);
			controller3.apagaAtividade(null);
        });
	}
	
	@Test
	void testApagaAtividadeIdVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller1.apagaAtividade("");
			controller2.apagaAtividade("");
			controller3.apagaAtividade("");
        });
	}
	
	@Test
	void testApagaAtividadeInexistente() {
		assertThrows(NullPointerException.class, () -> {
			controller1.apagaAtividade("A1");
			controller2.apagaAtividade("A2");
			controller3.apagaAtividade("A4");
			controller3.apagaAtividade("A13");
        });
	}

	@Test
	void testContaItensPendentesValido() {
		assertEquals(0, controller2.contaItensPendentes("A1"));
		assertEquals(0, controller3.contaItensPendentes("A1"));
		assertEquals(1, controller3.contaItensPendentes("A2"));
		assertEquals(2, controller3.contaItensPendentes("A3"));
	}
	
	@Test
	void testContaItensPendentesIdNull() {
		assertThrows(NullPointerException.class, () -> {
			controller1.contaItensPendentes(null);
			controller2.contaItensPendentes(null);
			controller3.contaItensPendentes(null);
        });
	}
	
	@Test
	void testContaItensPendentesIdVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller1.contaItensPendentes("");
			controller2.contaItensPendentes("");
			controller3.contaItensPendentes("");
        });
	}
	
	@Test
	void testContaItensPendentesAtividadeInexistente() {
		assertThrows(NullPointerException.class, () -> {
			controller1.contaItensPendentes("A1");
			controller2.contaItensPendentes("A2");
			controller3.contaItensPendentes("A4");
        });
	}

	@Test
	void testContaItensRealizadosValido() {
		assertEquals(0, controller2.contaItensRealizados("A1"));
		assertEquals(0, controller3.contaItensRealizados("A1"));
		assertEquals(0, controller3.contaItensRealizados("A2"));
		assertEquals(0, controller3.contaItensRealizados("A3"));
	}
	
	@Test
	void testContaItensRealizadosIdNull() {
		assertThrows(NullPointerException.class, () -> {
			controller1.contaItensRealizados(null);
			controller2.contaItensRealizados(null);
			controller3.contaItensRealizados(null);
        });
	}
	
	@Test
	void testContaItensRealizadosIdVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller1.contaItensRealizados("");
			controller2.contaItensRealizados("");
			controller3.contaItensRealizados("");
        });
	}
	
	@Test
	void testContaItensRealizadosAtividadeInexistente() {
		assertThrows(NullPointerException.class, () -> {
			controller1.contaItensRealizados("A1");
			controller2.contaItensRealizados("A2");
			controller3.contaItensRealizados("A4");
			controller3.contaItensRealizados("A13");
        });
	}

	@Test
	void executaAtividadeValida() {
		controller3.executaAtividade("A3", 1, 50);
		assertEquals(1, controller3.getAtividade("A3").contaItensRealizados());
	}

	@Test
	void executaAtividadeRepetida() {
		controller3.executaAtividade("A3", 1, 50);
		assertThrows(ActivationException.class, () -> {
			controller3.executaAtividade("A3", 1, 50);
		});
	}

	@Test
	void executaAtividadeInvalida() {
		assertThrows(NullPointerException.class, () -> {
			controller3.executaAtividade("A8", 1, 50);
		});
	}

	@Test
	void executaAtividadeCodigoAtividadeVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller3.executaAtividade("", 1, 50);
		});
	}

	@Test
	void executaAtividadeCodigoAtividadeNull() {
		assertThrows(NullPointerException.class, () -> {
			controller3.executaAtividade(null, 1, 50);
		});
	}

	@Test
	void executaAtividadeItemNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller3.executaAtividade("A3", 0, 50);
		});
	}

	@Test
	void executaAtividadeItemNegativo() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller3.executaAtividade("A3", -1, 50);
		});
	}

	@Test
	void executaAtividadeItemInvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller3.executaAtividade("A3", 5, 50);
		});
	}

	@Test
	void executaAtividadeDuracaoNula() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller3.executaAtividade("A3", 1, 0);
		});
	}

	@Test
	void executaAtividadeDuracaoNegativa() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller3.executaAtividade("A3", 1, -1);
		});
	}

    @Test
    void cadastraResultadoValido() {
		assertEquals(1, controller3.cadastraResultado("A2", "Pesquisa concluída com sucesso"));
    }

	@Test
	void cadastraResultadoCodigoAtividadeVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller3.cadastraResultado("", "Pesquisa concluída com sucesso");
		});
	}

	@Test
	void cadastraResultadoCodigoAtividadeNull() {
		assertThrows(NullPointerException.class, () -> {
			controller3.cadastraResultado(null, "Pesquisa concluída com sucesso");
		});
	}

	@Test
	void cadastraResultadoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller3.cadastraResultado("A3", "");
		});
	}

	@Test
	void cadastraResultadoNull() {
		assertThrows(NullPointerException.class, () -> {
			controller3.cadastraResultado("A3", null);
		});
	}

	@Test
	void removeResultadoValido() {
		controller3.cadastraResultado("A3", "Pesquisa concluída com sucesso");
		assertTrue(controller3.removeResultado("A3", 1));
	}

	@Test
	void removeResultadoAtividadeInvalida() {
		assertThrows(NullPointerException.class, () -> {
			controller3.removeResultado("A8", 1);
		});
	}

	@Test
	void removeResultadoCodigoAtividadeVazio() {
		controller3.cadastraResultado("A2", "Pesquisa concluída com sucesso");
		assertThrows(IllegalArgumentException.class, () -> {
			controller3.removeResultado("", 1);
		});
	}

	@Test
	void removeResultadoCodigoAtividadeNull() {
		controller3.cadastraResultado("A2", "Pesquisa concluída com sucesso");
		assertThrows(NullPointerException.class, () -> {
			controller3.removeResultado(null, 1);
		});
	}

	@Test
	void removeResultadoNumeroResultadoNulo() {
		controller3.cadastraResultado("A2", "Pesquisa concluída com sucesso");
		assertThrows(IllegalArgumentException.class, () -> {
			controller3.removeResultado("A2", 0);
		});
	}

	@Test
	void removeResultadoNumeroResultadoNegativo() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller3.removeResultado("A2", -1);
		});
	}
	@Test
	void removeResultadoNumeroResultadoInvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller3.removeResultado("A2", 50);
		});
	}

    @Test
    void listaResultadosValido() {
		controller3.cadastraResultado("A2", "Pesquisa concluída com sucesso");
		assertEquals("Pesquisa concluída com sucesso", controller3.listaResultados("A2"));
	}

	@Test
	void listaResultadosCodigoAtividadeVazio() {
		controller3.cadastraResultado("A2", "Pesquisa concluída com sucesso");
		assertThrows(IllegalArgumentException.class, () -> {
			controller3.listaResultados("");
		});
	}

	@Test
	void listaResultadosCodigoAtividadeNull() {
		controller3.cadastraResultado("A2", "Pesquisa concluída com sucesso");
		assertThrows(NullPointerException.class, () -> {
			controller3.listaResultados(null);
		});
	}

	@Test
	void listaResultadosAtividadeInvalida() {
		controller3.cadastraResultado("A2", "Pesquisa concluída com sucesso");
		assertThrows(NullPointerException.class, () -> {
			controller3.listaResultados("A8");
		});
	}

    @Test
    void getDuracaoValida() {
		controller3.cadastrarItem("A2", "Pesquisa");
		controller3.executaAtividade("A2", 1, 20);
		assertEquals(20, controller3.getDuracao("A2"));
    }

	@Test
	void getDuracaoCodigoAtividadeVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller3.getDuracao("");
		});
	}

	@Test
	void getDuracaoCodigoAtividadeNull() {
		assertThrows(NullPointerException.class, () -> {
			controller3.getDuracao(null);
		});
	}

	@Test
	void getDuracaoAtividadeInvalida() {
		assertThrows(NullPointerException.class, () -> {
			controller3.getDuracao("A9");
		});
	}
	
	@Test
	void defineProximaAtividade() {
		controller3.defineProximaAtividade("A2", "A3");
	}
	
	@Test
	void defineProximaAtvdIdPrecedenteVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller3.defineProximaAtividade("", "A3");
		});
	}
	
	@Test
	void defineProximaAtvdIdSubsequenteVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller3.defineProximaAtividade("A2", "");
		});
	}
	
	@Test
	void defineProximaAtvdIdsVazios() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller3.defineProximaAtividade("", "");
		});
	}
	
	@Test
	void defineProximaAtvdIdPrecedenteNull() {
		assertThrows(NullPointerException.class, () -> {
			controller3.defineProximaAtividade(null, "A3");
		});
	}
	
	@Test
	void defineProximaAtvdIdSubsequenteNull() {
		assertThrows(NullPointerException.class, () -> {
			controller3.defineProximaAtividade("A3", null);
		});
	}
	
	@Test
	void defineProximaAtvdIdsNulos() {
		assertThrows(NullPointerException.class, () -> {
			controller3.defineProximaAtividade(null, null);
		});
	}
	
	@Test
	void tiraProximaAtividade() {
		controller3.tiraProximaAtividade("A2");
	}
	
	@Test
	void tiraProximaAtividadeIdPrecedenteNulo() {
		assertThrows(NullPointerException.class, () -> {
			controller3.tiraProximaAtividade(null);
		});
	}
	
	@Test
	void tiraProximaAtividadeIdPrecedenteVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller3.tiraProximaAtividade("");
		});
	}
	
	@Test
	void contaProximos() {
		controller3.contaProximos("A2");
	}
	
	@Test
	void contaProximosIdPrecedenteVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller3.tiraProximaAtividade("");
		});
	}
	
	@Test
	void contaProximosIdPrecedenteNulo() {
		assertThrows(NullPointerException.class, () -> {
			controller3.tiraProximaAtividade(null);
		});
	}
	
	@Test
	void pegaProximo() {
		controller3.pegaProximo("A1", 3);
	}
	
	@Test
	void pegaProximoIdAtividadeNull() {
		assertThrows(NullPointerException.class, () -> {
			controller3.pegaProximo(null, 1);
		});
	}
	
	@Test
	void pegaProximoIdAtividadeVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller3.pegaProximo("", 1);
		});
	}
	
	@Test
	void pegaProximoEnesimaNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller3.pegaProximo("A2", 0);
		});
	}
	
	@Test
	void pegaMaiorRiscoAtividade() {
		assertThrows(NullPointerException.class, () -> {
			controller3.pegaMaiorRiscoAtividades("A1");
		});
	}
	
	@Test
	void pegaMaiorRiscoAtividadeNull() {
		assertThrows(NullPointerException.class, () -> {
			controller3.pegaMaiorRiscoAtividades(null);
		});
	}
	
	
}
