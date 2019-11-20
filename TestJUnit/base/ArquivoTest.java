package base;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArquivoTest {

	private Arquivo arquivo;
	
	@BeforeEach
	void inicia() { 
		this.arquivo = new Arquivo();
	}
}
