package controller;

import excecoes.ActivationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.ControllerPesquisador;

import static org.junit.jupiter.api.Assertions.*;

class ControllerPesquisadorTest {

    ControllerPesquisador controllerPesquisadorTeste;

    @BeforeEach
    void constroiController(){
        controllerPesquisadorTeste = new ControllerPesquisador();
    }

    @Test
    void cadastraPesquisadorValido() {
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        assertEquals("Arthur (estudante) - estudante de Computação - arthur@example.com - https://formation",
                controllerPesquisadorTeste.exibePesquisador("arthur@example.com"));
    }

    @Test
    void cadastraPesquisadorNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            controllerPesquisadorTeste.cadastraPesquisador("", "estudante",
                    "estudante de Computação", "arthur@example.com", "https://formation");
        });
    }

    @Test
    void cadastraPesquisadorNomeNull(){
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.cadastraPesquisador(null, "estudante",
                    "estudante de Computação", "arthur@example.com", "https://formation");
        });
    }

    @Test
    void cadastraPesquisadorFuncaoVazia(){
        assertThrows(IllegalArgumentException.class, () -> {
            controllerPesquisadorTeste.cadastraPesquisador("Arthur", "", "estudante de Computação", "arthur@example.com", "https://formation");
        });
    }

    @Test
    void cadastraPesquisadorFuncaoNull(){
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.cadastraPesquisador("Arthur", null, "estudante de Computação", "arthur@example.com", "https://formation");
        });
    }

    @Test
    void cadastraPesquisadorBiografiaVazia(){
        assertThrows(IllegalArgumentException.class, () -> {
            controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante", "", "arthur@example.com", "https://formation");
        });
    }

    @Test
    void cadastraPesquisadorBiografiaNull(){
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante", null, "arthur@example.com", "https://formation");
        });
    }

    @Test
    void cadastraPesquisadorEmailVazio(){
        assertThrows(IllegalArgumentException.class, () -> {
            controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante", "estudante de Computação", "", "https://formation");
        });
    }

    @Test
    void cadastraPesquisadorEmailNull(){
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante", "estudante de Computação", null, "https://formation");
        });
    }

    @Test
    void cadastraPesquisadorFotoVazia(){
        assertThrows(IllegalArgumentException.class, () -> {
            controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante", "estudante de Computação", "arthur@example.com", "");
        });
    }

    @Test
    void cadastraPesquisadorFotoNull(){
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante", "estudante de Computação", "arthur@example.com", null);
        });
    }

    @Test
    void alteraPesquisadorEmailValido(){
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        controllerPesquisadorTeste.alteraPesquisador("arthur@example.com", "EMAIL", "pedro@example.com");
        assertEquals("Arthur (estudante) - estudante de Computação - pedro@example.com - https://formation",
                controllerPesquisadorTeste.exibePesquisador("pedro@example.com"));
    }
    
    @Test
    void alteraPesquisadorEmailInvalido(){
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.alteraPesquisador("arthur@example.com", "NOME", "java");
        });
    }

    @Test
    void alteraPesquisadorAtributoVazio(){
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerPesquisadorTeste.alteraPesquisador("arthur@example.com", "", "java");
        });
    }

    @Test
    void alteraPesquisadorAtributoNull(){
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.alteraPesquisador("arthur@example.com", null, "java");
        });
    }

    @Test
    void alteraPesquisadorAtributoNovoValorVazio(){
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerPesquisadorTeste.alteraPesquisador("arthur@example.com", "NOME", "");
        });
    }

    @Test
    void alteraPesquisadorAtributoNovoValorNull(){
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.alteraPesquisador("arthur@example.com", "NOME", null);
        });
    }

    @Test
    void ativaPesquisadorEmailInvalido(){
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.ativaPesquisador("arthur@xample.com");
        });
    }

    @Test
    void ativaPesquisadorAtivado(){
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        assertThrows(ActivationException.class, () -> {
            controllerPesquisadorTeste.ativaPesquisador("arthur@example.com");
        });
    }

    @Test
    void ativaPesquisadorDesativado(){
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        controllerPesquisadorTeste.desativaPesquisador("arthur@example.com");
        controllerPesquisadorTeste.ativaPesquisador("arthur@example.com");
        assertTrue(controllerPesquisadorTeste.pesquisadorEhAtivo("arthur@example.com"));
    }

    @Test
    void desativaPesquisadorAtivado(){
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        controllerPesquisadorTeste.desativaPesquisador("arthur@example.com");
        assertFalse(controllerPesquisadorTeste.pesquisadorEhAtivo("arthur@example.com"));
    }

    @Test
    void desativaPesquisadorInativado() {
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        controllerPesquisadorTeste.desativaPesquisador("arthur@example.com");
        assertThrows(ActivationException.class, () -> {
            controllerPesquisadorTeste.desativaPesquisador("arthur@example.com");
        });
    }

    @Test
    void exibePesquisadorEmailInvalido() {
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.exibePesquisador("arthur@example.com");
        });
    }

    @Test
    void exibePesquisadorValido() {
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        assertEquals("Arthur (estudante) - estudante de Computação - arthur@example.com - https://formation",
                controllerPesquisadorTeste.exibePesquisador("arthur@example.com"));
    }

    @Test
    void pesquisadorEhAtivoEmailInvalido() {
        assertThrows(NullPointerException.class, () -> {
            controllerPesquisadorTeste.pesquisadorEhAtivo("arthur@example.com");
        });
    }

    @Test
    void pesquisadorEhAtivoTrue(){
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        assertTrue(controllerPesquisadorTeste.pesquisadorEhAtivo("arthur@example.com"));
    }

    @Test
    void pesquisadorEhAtivoFalse(){
        controllerPesquisadorTeste.cadastraPesquisador("Arthur", "estudante",
                "estudante de Computação", "arthur@example.com", "https://formation");
        controllerPesquisadorTeste.desativaPesquisador("arthur@example.com");
        assertFalse(controllerPesquisadorTeste.pesquisadorEhAtivo("arthur@example.com"));
    }
    
	@Test
	void cadastraEspecialidadeProfessorComum() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		controllerPesquisadorTeste.cadastraEspecialidadeProfessor("professor@pesquisador.com", "Bacharel", "CC", "11/11/2015");
		
	}
	
	@Test
	void cadastraEspecialidadeProfessorEmailNull() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisadorTeste.cadastraEspecialidadeProfessor(null, "Bacharel", "CC", "11/11/2015");
		});
		
	}
	
	@Test
	void cadastraEspecialidadeProfessorEmailVazio() {
	
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisadorTeste.cadastraEspecialidadeProfessor("", "Bacharel", "CC", "11/11/2015");
		});
		
	}
	
	@Test
	void cadastraEspecialidadeProfessorFormacaoNull() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisadorTeste.cadastraEspecialidadeProfessor("professor@pesquisador.com", null, "CC", "11/11/2015");
		});
		
	}
	
	@Test
	void cadastraEspecialidadeProfessorFormacaoVazio() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisadorTeste.cadastraEspecialidadeProfessor("professor@pesquisador.com", "", "CC", "11/11/2015");
		});
		
	}
	
	@Test
	void cadastraEspecialidadeProfessorUnidadeNull() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisadorTeste.cadastraEspecialidadeProfessor("professor@pesquisador.com", "Bacharel", null, "11/11/2015");
		});
		
	}
	
	@Test
	void cadastraEspecialidadeProfessorUnidadeVazio() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisadorTeste.cadastraEspecialidadeProfessor("professor@pesquisador.com", "Bacharel", "", "11/11/2015");
		});
		
	}
	
	@Test
	void cadastraEspecialidadeProfessorDataNull() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisadorTeste.cadastraEspecialidadeProfessor("professor@pesquisador.com", "Bacharel", "CC", null);
		});
		
	}
	
	@Test
	void cadastraEspecialidadeProfessorDataVazio() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "professor", "professor pesquisador", "professor@pesquisador.com", "https://pic_pesq_prof");
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisadorTeste.cadastraEspecialidadeProfessor("professor@pesquisador.com", "Bacharel", "CC", "");
		});
		
	}
	
	@Test
	void cadastraEspecialidadeAlunoComum() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "alune pesquisador", "alune@pesquisador.com", "https://pic_pesq_alune");
		controllerPesquisadorTeste.cadastraEspecialidadeAluno("alune@pesquisador.com", 2, 7.56);
		
	}
	
	@Test
	void cadastraEspecialidadeAlunoEmailNull() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "alune pesquisador", "alune@pesquisador.com", "https://pic_pesq_alune");
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisadorTeste.cadastraEspecialidadeAluno(null, 2, 7.56);
		});
		
	}
	
	@Test
	void cadastraEspecialidadeAlunoEmailVazio() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "alune pesquisador", "alune@pesquisador.com", "https://pic_pesq_alune");
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisadorTeste.cadastraEspecialidadeAluno("", 2, 7.56);
		});
		
	}
	
	@Test
	void cadastraEspecialidadeAlunoPeriodoInvalido() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "alune pesquisador", "alune@pesquisador.com", "https://pic_pesq_alune");
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisadorTeste.cadastraEspecialidadeAluno("alune@pesquisador.com", -2, 7.56);
		});
		
	}
	
	@Test
	void cadastraEspecialidadeAlunoIEAInvalido() {
	
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "alune pesquisador", "alune@pesquisador.com", "https://pic_pesq_alune");
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisadorTeste.cadastraEspecialidadeAluno("alune@pesquisador.com", 2, -7.56);
		});
		
	}
	
	@Test
	void exibePesquisadorComum() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "alune pesquisador", "alune@pesquisador.com", "https://pic_pesq_alune");
		assertEquals(controllerPesquisadorTeste.exibePesquisador("alune@pesquisador.com"), "pesquisador (estudante) - alune pesquisador - alune@pesquisador.com - https://pic_pesq_alune");
		
	}
	
	@Test
	void exibePesquisadorEmailNull() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "alune pesquisador", "alune@pesquisador.com", "https://pic_pesq_alune");
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisadorTeste.exibePesquisador(null);
		});
		
	}
	
	@Test
	void exibePesquisadorEmailVazio() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "alune pesquisador", "alune@pesquisador.com", "https://pic_pesq_alune");
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisadorTeste.exibePesquisador("");
		});
		
	}
	
	@Test
	void exibePesquisadorInexistente() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "alune pesquisador", "alune@pesquisador.com", "https://pic_pesq_alune");
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisadorTeste.exibePesquisador("aluna");
		});
		
	}
	
	@Test
	void listaPesquisadoresComum() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "alune pesquisador", "alune@pesquisador.com", "https://pic_pesq_alune");
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "aluna pesquisador", "aluna@pesquisador.com", "https://pic_pesq_aluna");
		assertEquals(controllerPesquisadorTeste.listaPesquisadores("ALUNA"), "pesquisador (estudante) - alune pesquisador - alune@pesquisador.com - https://pic_pesq_alune | pesquisador (estudante) - aluna pesquisador - aluna@pesquisador.com - https://pic_pesq_aluna");
	}
	
	@Test
	void listaPesquisadoresTipoNull() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "alune pesquisador", "alune@pesquisador.com", "https://pic_pesq_alune");
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "aluna pesquisador", "aluna@pesquisador.com", "https://pic_pesq_aluna");
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisadorTeste.exibePesquisador(null);
		});
	}
	
	@Test
	void listaPesquisadoresTipoVazio() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "alune pesquisador", "alune@pesquisador.com", "https://pic_pesq_alune");
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "aluna pesquisador", "aluna@pesquisador.com", "https://pic_pesq_aluna");
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisadorTeste.exibePesquisador("");
		});
	}
	
	@Test
	void listaPesquisadoresTipoInexistente() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "alune pesquisador", "alune@pesquisador.com", "https://pic_pesq_alune");
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "aluna pesquisador", "aluna@pesquisador.com", "https://pic_pesq_aluna");
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisadorTeste.exibePesquisador("orientador");
		});
	}
	
	@Test
	void alteraPesquisadorAlunoSemestreComum() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "alune pesquisador", "alune@pesquisador.com", "https://pic_pesq_alune");
		controllerPesquisadorTeste.cadastraEspecialidadeAluno("alune@pesquisador.com", 4, 7.56);
		controllerPesquisadorTeste.alteraPesquisador("alune@pesquisador.com", "SEMESTRE", "5");
		
	}
	
	@Test
	void alteraPesquisadorAlunoIEAComum() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "alune pesquisador", "alune@pesquisador.com", "https://pic_pesq_alune");
		controllerPesquisadorTeste.cadastraEspecialidadeAluno("alune@pesquisador.com", 4, 7.56);
		controllerPesquisadorTeste.alteraPesquisador("alune@pesquisador.com", "IEA", "5.93");
		
	}
	
	@Test
	void alteraPesquisadorProfessorFormacaoComum() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "professor", "professora pesquisadora", "professora@pesquisadora.com", "https://pic_pesq_prof");
		controllerPesquisadorTeste.cadastraEspecialidadeProfessor("professora@pesquisadora.com", "Bacharel", "DSC", "21/09/2015");
		controllerPesquisadorTeste.alteraPesquisador("professora@pesquisadora.com", "FORMACAO", "Doutorado");
		
	}
	
	@Test
	void alteraPesquisadorProfessorUnidadeComum() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "professor", "professora pesquisadora", "professora@pesquisadora.com", "https://pic_pesq_prof");
		controllerPesquisadorTeste.cadastraEspecialidadeProfessor("professora@pesquisadora.com", "Bacharel", "DSC", "21/09/2015");
		controllerPesquisadorTeste.alteraPesquisador("professora@pesquisadora.com", "UNIDADE", "SPLAB");
		
	}
	
	@Test
	void alteraPesquisadorProfessorDataComum() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "professor", "professora pesquisadora", "professora@pesquisadora.com", "https://pic_pesq_prof");
		controllerPesquisadorTeste.cadastraEspecialidadeProfessor("professora@pesquisadora.com", "Bacharel", "DSC", "21/09/2015");
		controllerPesquisadorTeste.alteraPesquisador("professora@pesquisadora.com", "DATA", "11/11/2019");
		
	}
	
	@Test
	void alteraPesquisadorAlunoSemestreNull() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "alune pesquisador", "alune@pesquisador.com", "https://pic_pesq_alune");
		controllerPesquisadorTeste.cadastraEspecialidadeAluno("alune@pesquisador.com", 4, 7.56);
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisadorTeste.alteraPesquisador("alune@pesquisador.com", "SEMESTRE", null);
		});
		
		
	}
	
	@Test
	void alteraPesquisadorAlunoIEANull() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "alune pesquisador", "alune@pesquisador.com", "https://pic_pesq_alune");
		controllerPesquisadorTeste.cadastraEspecialidadeAluno("alune@pesquisador.com", 4, 7.56);
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisadorTeste.alteraPesquisador("alune@pesquisador.com", "IEA", null);
		});
		
		
	}
	
	@Test
	void alteraPesquisadorProfessorFormacaoNull() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "professor", "professora pesquisadora", "professora@pesquisadora.com", "https://pic_pesq_prof");
		controllerPesquisadorTeste.cadastraEspecialidadeProfessor("professora@pesquisadora.com", "Bacharel", "DSC", "21/09/2015");
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisadorTeste.alteraPesquisador("professora@pesquisadora.com", "FORMACAO", null);
		});
		
		
	}
	
	@Test
	void alteraPesquisadorProfessorUnidadeNull() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "professor", "professora pesquisadora", "professora@pesquisadora.com", "https://pic_pesq_prof");
		controllerPesquisadorTeste.cadastraEspecialidadeProfessor("professora@pesquisadora.com", "Bacharel", "DSC", "21/09/2015");
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisadorTeste.alteraPesquisador("professora@pesquisadora.com", "UNIDADE", null);
		});
		
		
	}
	
	@Test
	void alteraPesquisadorProfessorDataNull() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "professor", "professora pesquisadora", "professora@pesquisadora.com", "https://pic_pesq_prof");
		controllerPesquisadorTeste.cadastraEspecialidadeProfessor("professora@pesquisadora.com", "Bacharel", "DSC", "21/09/2015");
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisadorTeste.alteraPesquisador("professora@pesquisadora.com", "DATA", null);
		});
	}
	
	@Test
	void alteraPesquisadorAlunoSemestreVazio() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "alune pesquisador", "alune@pesquisador.com", "https://pic_pesq_alune");
		controllerPesquisadorTeste.cadastraEspecialidadeAluno("alune@pesquisador.com", 4, 7.56);
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisadorTeste.alteraPesquisador("alune@pesquisador.com", "SEMESTRE", "");
		});
		
		
	}
	
	@Test
	void alteraPesquisadorAlunoIEAVazio() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "alune pesquisador", "alune@pesquisador.com", "https://pic_pesq_alune");
		controllerPesquisadorTeste.cadastraEspecialidadeAluno("alune@pesquisador.com", 4, 7.56);
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisadorTeste.alteraPesquisador("alune@pesquisador.com", "IEA", "");
		});
		
		
	}
	
	@Test
	void alteraPesquisadorProfessorFormacaoVazio() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "professor", "professora pesquisadora", "professora@pesquisadora.com", "https://pic_pesq_prof");
		controllerPesquisadorTeste.cadastraEspecialidadeProfessor("professora@pesquisadora.com", "Bacharel", "DSC", "21/09/2015");
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisadorTeste.alteraPesquisador("professora@pesquisadora.com", "FORMACAO", "");
		});
		
		
	}
	
	@Test
	void alteraPesquisadorProfessorUnidadeVazio() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "professor", "professora pesquisadora", "professora@pesquisadora.com", "https://pic_pesq_prof");
		controllerPesquisadorTeste.cadastraEspecialidadeProfessor("professora@pesquisadora.com", "Bacharel", "DSC", "21/09/2015");
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisadorTeste.alteraPesquisador("professora@pesquisadora.com", "UNIDADE", "");
		});
		
		
	}
	
	@Test
	void alteraPesquisadorProfessorDataVazio() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "professor", "professora pesquisadora", "professora@pesquisadora.com", "https://pic_pesq_prof");
		controllerPesquisadorTeste.cadastraEspecialidadeProfessor("professora@pesquisadora.com", "Bacharel", "DSC", "21/09/2015");
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisadorTeste.alteraPesquisador("professora@pesquisadora.com", "DATA", "");
		});
	}
	
	@Test
	void alteraPesquisadorAlunoSemestreInvalido() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "alune pesquisador", "alune@pesquisador.com", "https://pic_pesq_alune");
		controllerPesquisadorTeste.cadastraEspecialidadeAluno("alune@pesquisador.com", 4, 7.56);
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisadorTeste.alteraPesquisador("alune@pesquisador.com", "SEMESTRE", "0");
		});
		
		
	}
	
	@Test
	void alteraPesquisadorAlunoIEAInvalido() {
		
		controllerPesquisadorTeste.cadastraPesquisador("pesquisador", "estudante", "alune pesquisador", "alune@pesquisador.com", "https://pic_pesq_alune");
		controllerPesquisadorTeste.cadastraEspecialidadeAluno("alune@pesquisador.com", 4, 7.56);
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisadorTeste.alteraPesquisador("alune@pesquisador.com", "IEA", "-3.8");
		});
		
		
	}

}