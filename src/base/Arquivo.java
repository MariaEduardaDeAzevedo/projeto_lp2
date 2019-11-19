package base;

import java.io.*;
import java.util.Map;

public class Arquivo {

    public Arquivo() {
    }

    public void salvarArquivos(Map entidades, String nomeDoArquivo) {
        FileOutputStream arquivo = null;
        try {
            arquivo = new FileOutputStream("Arquivo" + File.separator + nomeDoArquivo);
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

    public void salvarArquivoInt(int numero, String nomeDoArquivo) {
        FileOutputStream arquivo = null;
        try {
            arquivo = new FileOutputStream("Arquivo" + File.separator + nomeDoArquivo);
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

    public Map carregarArquivos(String nomeDoArquivo) {
        FileInputStream arquivo = null;
        try {
            arquivo = new FileInputStream("Arquivo" + File.separator + nomeDoArquivo);
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

    public int carregarArquivoInt(String nomeDoArquivo) {
        FileInputStream arquivo = null;
        try {
            arquivo = new FileInputStream("Arquivo" + File.separator + nomeDoArquivo);
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
