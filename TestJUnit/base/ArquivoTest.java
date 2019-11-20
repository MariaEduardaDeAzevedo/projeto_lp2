package base;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArquivoTest {

	private Arquivo arquivo;
	
	@BeforeEach
	void inicia() { 
		this.arquivo = new Arquivo();
	}
	
	@Test
	void testSalvarArquivosComum() {
		Map mapa = new HashMap<String, String>();
		mapa.put("key", "value");
		File dir = new File("teste");
		dir.mkdir();
		this.arquivo.salvarArquivos(mapa, "teste", "teste");
		boolean retorno = false;
		
		for (File f : dir.listFiles()) {
			
			if (f.getName().equals("teste")) {
				retorno = true;
				f.delete();
				break;			
			}	
		}
		dir.delete();
		assertEquals(true, retorno);
	}
	
	@Test
	void testSalvarArquivoIntComum() {
		File dir = new File("teste");
		dir.mkdir();
		this.arquivo.salvarArquivoInt(2, "teste", "teste");
		boolean retorno = false;
		
		for (File f : dir.listFiles()) {
			
			if (f.getName().equals("teste")) {
				retorno = true;
				f.delete();
				break;			
			}	
		}
		dir.delete();
		assertEquals(true, retorno);
	}
	
	@Test
	void testCarregarArquivosComum() {
		Map mapa = new HashMap<String, String>();
		mapa.put("key", "value");
		File dir = new File("teste");
		dir.mkdir();
		this.arquivo.salvarArquivos(mapa, "teste", "teste");
		
		Map retorno = this.arquivo.carregarArquivos("teste", "teste");
		
		dir.listFiles()[0].delete();
		dir.delete();
		assertEquals(retorno, mapa);
	}
	
	@Test
	void testCarregarArquivoIntComum() {
		File dir = new File("teste");
		dir.mkdir();
		this.arquivo.salvarArquivoInt(2, "teste", "teste");
		
		int retorno = this.arquivo.carregarArquivoInt("teste", "teste");
		
		dir.listFiles()[0].delete();
		dir.delete();
		assertEquals(retorno, 2);
	}
}
