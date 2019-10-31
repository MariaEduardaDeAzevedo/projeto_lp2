package projeto_lp2;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AtividadeTest {
	private Atividade atividadeBase1;
	private Atividade atividadeBase2;
	private Atividade atividadeBase3;
	
	@BeforeEach
	void criaAtividades() {
		atividadeBase1 = new Atividade("Cultivo de espécies de plantas por hidroponia", "BAIXO", "Não há risco de haver nenhum tipo de prejuízo com este experimento", "A1");
		atividadeBase2 = new Atividade("Busca de mensagens que espalham notícias falsas nos chats dos alunos de computação", "MEDIO", "Acusar uma notícia falsa pode constranger o remetente desta.", "A2");
		atividadeBase3 = new Atividade("Busca de uma forma de unificar a teoria da relatividade geral ao princípio da incerteza como forma de buscar as condições iniciais do universo através da utilização de um acelerador de partículas", "ALTO", "Qualquer erro matemático nos cálculos pode ocasionar uma falha no acelerador de partículas, o que pode causar danos irreversíveis ao local do experimento", "A3");
		atividadeBase2.cadastrarItem("Monitoramento no Slack das disciplinas de programação do primeiro e do segundo período de computação");
		atividadeBase3.cadastrarItem("Verificar se a condição sem contorno do universo é verdadeira");
		atividadeBase3.cadastrarItem("Calcular a energia necessária para simular as condiçoes iniciais do universo num acelerador de partículas");
	}
	
	@Test
	void testAtividadeValido() {
		assertEquals(0, atividadeBase1.contaItensPendentes());
		assertEquals(0, atividadeBase1.contaItensRealizados());
	}
	
	@Test
	void testAtividadeDescricaoNull() {
        assertThrows(NullPointerException.class, () -> {
            Atividade novaAtividade = new Atividade(null, "BAIXO", "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo", "A4");
        });
    }
	
	@Test
	void testAtividadeDescricaoVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
            Atividade novaAtividade = new Atividade("", "BAIXO", "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo", "A4");
        });
	}
	
	@Test
	void testAtividadeRiscoNull() {
		assertThrows(NullPointerException.class, () -> {
            Atividade novaAtividade = new Atividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", null, "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo", "A4");
        });
	}
	
	@Test
	void testAtividadeRiscoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
            Atividade novaAtividade = new Atividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", "", "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo", "A4");
        });
	}
	
	@Test
	void testAtividadeRiscoInvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			Atividade novaAtividade = new Atividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", "MUITO BAIXO", "Como é apenas uma contagem, e ainda assim esta é feita nos chats públicos, o risco é baixo", "A4");
        });
	}
	
	@Test
	void testAtividadeDescricaoRiscoNull() {
		assertThrows(NullPointerException.class, () -> {
			Atividade novaAtividade = new Atividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", "BAIXO", null, "A4");
        });
	}
	
	@Test
	void testAtividadeDescricaoRiscoVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			Atividade novaAtividade = new Atividade("Contagem de mensagens pejorativas nos chats públicos dos alunos de computação", "BAIXO", "", "A4");
        });
	}

	@Test
	void testCadastrarItemValido() {
		assertEquals(0, atividadeBase1.contaItensPendentes());
		atividadeBase1.cadastrarItem("Estudar a interação da hidroponia com o período de crescimento das plantas");
		assertEquals(1, atividadeBase1.contaItensPendentes());
		assertEquals(1, atividadeBase2.contaItensPendentes());
		atividadeBase2.cadastrarItem("Estudar semelhanças entre notícias falsas");
		assertEquals(2, atividadeBase2.contaItensPendentes());
		assertEquals(2, atividadeBase3.contaItensPendentes());
		atividadeBase3.cadastrarItem("Procurar variáveis semelhantes nas equações da relatividade geral e do princípio da incerteza");
		assertEquals(3, atividadeBase3.contaItensPendentes());
	}
	
	@Test
	void testCadastrarItemNull() {
		assertThrows(NullPointerException.class, () -> {
			atividadeBase1.cadastrarItem(null);
			atividadeBase2.cadastrarItem(null);
			atividadeBase3.cadastrarItem(null);
		});
	}
	
	@Test
	void testCadastrarItemVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeBase1.cadastrarItem("");
			atividadeBase2.cadastrarItem("");
			atividadeBase3.cadastrarItem("");
		});
	}

	@Test
	void testToString() {
		assertEquals("Cultivo de espécies de plantas por hidroponia (BAIXO - Não há risco de haver nenhum tipo de prejuízo com este experimento)", atividadeBase1.toString());
		assertEquals("Busca de mensagens que espalham notícias falsas nos chats dos alunos de computação (MEDIO - Acusar uma notícia falsa pode constranger o remetente desta.) | PENDENTE - Monitoramento no Slack das disciplinas de programação do primeiro e do segundo período de computação", atividadeBase2.toString());
		assertEquals("Busca de uma forma de unificar a teoria da relatividade geral ao princípio da incerteza como forma de buscar as condições iniciais do universo através da utilização de um acelerador de partículas (ALTO - Qualquer erro matemático nos cálculos pode ocasionar uma falha no acelerador de partículas, o que pode causar danos irreversíveis ao local do experimento) | PENDENTE - Verificar se a condição sem contorno do universo é verdadeira | PENDENTE - Calcular a energia necessária para simular as condiçoes iniciais do universo num acelerador de partículas", atividadeBase3.toString());
	}

	@Test
	void testContaItensPendentes() {
		fail("Not yet implemented");
	}

	@Test
	void testContaItensRealizados() {
		fail("Not yet implemented");
	}
	
	@Test
	void testEqualsObject() {
		fail("Not yet implemented");
	}
	
	@Test
	void testHashCode() {
		fail("Not yet implemented");
	}

}
