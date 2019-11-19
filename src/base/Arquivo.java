package base;

import java.io.*;
import java.util.Map;

/**
 * Classe responsavel por salvar e carregar arquivos.
 */
public class Arquivo {

    /**
     * Constroi o objeto Arquivo sem nenhum parametro nem atributo.
     */
    public Arquivo() {
    }

    /**
     * Metodo utilizado para criar um arquivo fisico na pasta Dados no formato binario de uma
     * collection.
     *
     * @param entidades     collection das entidades que se deseja salvar.
     * @param nomeDoArquivo nome do arquivo que sera salvo.
     */
    public void salvarArquivos(Map entidades, String nomeDoArquivo) {
        FileOutputStream arquivo = null;
        try {
            arquivo = new FileOutputStream("Dados" + File.separator + nomeDoArquivo);
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo nao encontrado");
            e.printStackTrace();
        }
        ObjectOutputStream objeto = null;
        try {
            objeto = new ObjectOutputStream(arquivo);
        } catch (IOException e) {
            System.err.println("Erro na leitura do arquivo");
            e.printStackTrace();
        }
        try {
            objeto.writeObject(entidades);
        } catch (IOException e) {
            System.err.println("Erro na escrita do arquivo");
            e.printStackTrace();
        }
        try {
            objeto.close();
        } catch (IOException e) {
            System.err.println("Erro ao fechar o arquivo");
            e.printStackTrace();
        }
    }

    /**
     * Metodo utilizado para criar um arquivo fisico na pasta Dados no formato binario de um
     * numero inteiro.
     *
     * @param numero        Numero que se deseja salvar.
     * @param nomeDoArquivo nome do arquivo que sera salvo.
     */
    public void salvarArquivoInt(int numero, String nomeDoArquivo) {
        FileOutputStream arquivo = null;
        try {
            arquivo = new FileOutputStream("Dados" + File.separator + nomeDoArquivo);
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo nao encontrado");
            e.printStackTrace();
        }
        ObjectOutputStream objeto = null;
        try {
            objeto = new ObjectOutputStream(arquivo);
        } catch (IOException e) {
            System.err.println("Erro na leitura do arquivo");
            e.printStackTrace();
        }
        try {
            objeto.writeObject(numero);
        } catch (IOException e) {
            System.err.println("Erro na escrita do arquivo");
            e.printStackTrace();
        }
        try {
            objeto.close();
        } catch (IOException e) {
            System.err.println("Erro ao fechar o arquivo");
            e.printStackTrace();
        }
    }

    /**
     * Metodo utilizado para carregar um arquivo fisico da pasta Dados que esta no formato binario
     * e retornar uma collection.
     *
     * @param nomeDoArquivo nome do arquivo que sera carregado.
     * @return collection que estava contido no arquivo.
     */
    public Map carregarArquivos(String nomeDoArquivo) {
        FileInputStream arquivo = null;
        try {
            arquivo = new FileInputStream("Dados" + File.separator + nomeDoArquivo);
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo nao encontrado");
            e.printStackTrace();
        }
        ObjectInputStream objeto = null;
        try {
            objeto = new ObjectInputStream(arquivo);
        } catch (IOException e) {
            System.err.println("Erro ao abrir arquivo");
            e.printStackTrace();
        }
        Map listaDeEntidades = null;
        try {
            listaDeEntidades = (Map) objeto.readObject();
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Classe de objeto desconhecida");
            e.printStackTrace();
        }
        return listaDeEntidades;
    }


    /**
     * Metodo utilizado para carregar um arquivo fisico da pasta Dados que esta no formato binario
     * e retornar um numero.
     *
     * @param nomeDoArquivo nome do arquivo que sera carregado.
     * @return numero que estava contido no arquivo.
     */
    public int carregarArquivoInt(String nomeDoArquivo) {
        FileInputStream arquivo = null;
        try {
            arquivo = new FileInputStream("Dados" + File.separator + nomeDoArquivo);
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo nao encontrado");
            e.printStackTrace();
        }
        ObjectInputStream objeto = null;
        try {
            objeto = new ObjectInputStream(arquivo);
        } catch (IOException e) {
            System.err.println("Erro ao abrir arquivo");
            e.printStackTrace();
        }
        Object arquivoInt = null;
        try {
            arquivoInt = objeto.readObject();
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Classe de objeto desconhecida");
            e.printStackTrace();
        }

        return (int) arquivoInt;
    }
}
