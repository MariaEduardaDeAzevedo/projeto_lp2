package base;
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
		assertEquals(0, atividadeBase1.contaItensPendentes());
		assertEquals(1, atividadeBase2.contaItensPendentes());
		assertEquals(2, atividadeBase3.contaItensPendentes());
	}

	@Test
	void testContaItensRealizados() {
		assertEquals(0, atividadeBase1.contaItensRealizados());
		assertEquals(0, atividadeBase2.contaItensRealizados());
		assertEquals(0, atividadeBase3.contaItensRealizados());
	}
	
	@Test
	void testEqualsObject() {
		assertFalse(atividadeBase1.equals(atividadeBase2));
		assertFalse(atividadeBase1.equals(atividadeBase3));
		assertFalse(atividadeBase3.equals(atividadeBase2));
		assertTrue(atividadeBase1.equals(atividadeBase1));
		assertTrue(atividadeBase2.equals(atividadeBase2));
		assertTrue(atividadeBase3.equals(atividadeBase3));
		Atividade atividadeBase1clone = new Atividade("Cultivo de espécies de plantas por hidroponia", "BAIXO", "Não há risco de haver nenhum tipo de prejuízo com este experimento", "A1");
		Atividade mesmoId1 = new Atividade("Buscar uma resposta do por quê Joy Division tem o melhor disco do post punk", "ALTO", "Pessoas podem se ofender com o propósito da pesquisa", "A1");
		Atividade atividadeBase2clone = new Atividade("Busca de mensagens que espalham notícias falsas nos chats dos alunos de computação", "MEDIO", "Acusar uma notícia falsa pode constranger o remetente desta.", "A2");
		Atividade mesmoId2 = new Atividade("Pesquisar o significado da letra da primeira música do disco Kid A", "MEDIO", "Pode ser difícil de entender o que significa cada palavra", "A2");
		Atividade atividadeBase3clone = new Atividade("Busca de uma forma de unificar a teoria da relatividade geral ao princípio da incerteza como forma de buscar as condições iniciais do universo através da utilização de um acelerador de partículas", "ALTO", "Qualquer erro matemático nos cálculos pode ocasionar uma falha no acelerador de partículas, o que pode causar danos irreversíveis ao local do experimento", "A3");
		Atividade mesmoId3 = new Atividade("Pesquisar o percentual de pessoas que viram 2001 e entenderam o final", "BAIXO", "Muitas pessoas não entenderam o final", "A3");
		assertTrue(atividadeBase1.equals(atividadeBase1clone));
		assertTrue(atividadeBase1.equals(mesmoId1));
		assertTrue(atividadeBase2.equals(atividadeBase2clone));
		assertTrue(atividadeBase2.equals(mesmoId2));
		assertTrue(atividadeBase3.equals(atividadeBase3clone));
		assertTrue(atividadeBase3.equals(mesmoId3));
	}
	
	@Test
	void testHashCode() {
		assertEquals(atividadeBase1.hashCode(), atividadeBase1.hashCode());
		assertEquals(atividadeBase2.hashCode(), atividadeBase2.hashCode());
		assertEquals(atividadeBase3.hashCode(), atividadeBase3.hashCode());
		Atividade atividadeBase1clone = new Atividade("Cultivo de espécies de plantas por hidroponia", "BAIXO", "Não há risco de haver nenhum tipo de prejuízo com este experimento", "A1");
		Atividade mesmoId1 = new Atividade("Buscar uma resposta do por quê Joy Division tem o melhor disco do post punk", "ALTO", "Pessoas podem se ofender com o propósito da pesquisa", "A1");
		Atividade atividadeBase2clone = new Atividade("Busca de mensagens que espalham notícias falsas nos chats dos alunos de computação", "MEDIO", "Acusar uma notícia falsa pode constranger o remetente desta.", "A2");
		Atividade mesmoId2 = new Atividade("Pesquisar o significado da letra da primeira música do disco Kid A", "MEDIO", "Pode ser difícil de entender o que significa cada palavra", "A2");
		Atividade atividadeBase3clone = new Atividade("Busca de uma forma de unificar a teoria da relatividade geral ao princípio da incerteza como forma de buscar as condições iniciais do universo através da utilização de um acelerador de partículas", "ALTO", "Qualquer erro matemático nos cálculos pode ocasionar uma falha no acelerador de partículas, o que pode causar danos irreversíveis ao local do experimento", "A3");
		Atividade mesmoId3 = new Atividade("Pesquisar o percentual de pessoas que viram 2001 e entenderam o final", "BAIXO", "Muitas pessoas não entenderam o final", "A3");
		assertEquals(atividadeBase1.hashCode(), atividadeBase1clone.hashCode());
		assertEquals(atividadeBase1.hashCode(), mesmoId1.hashCode());
		assertEquals(atividadeBase2.hashCode(), atividadeBase2clone.hashCode());
		assertEquals(atividadeBase2.hashCode(), mesmoId2.hashCode());
		assertEquals(atividadeBase3.hashCode(), atividadeBase3clone.hashCode());
		assertEquals(atividadeBase3.hashCode(), mesmoId3.hashCode());
	}

}
