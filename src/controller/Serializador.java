package controller;

import java.io.*;
import java.util.Map;

public class Serializador {

    public Serializador() {
    }

    public void salvarArquivos(Map entidades, String nomeDoArquivo) {
        FileOutputStream arquivo = null;
        try {
            arquivo = new FileOutputStream(nomeDoArquivo);
            ObjectOutputStream objeto = new ObjectOutputStream(arquivo);
            objeto.writeObject(entidades);
            objeto.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvarArquivoInt(int numero, String nomeDoArquivo) {
        FileOutputStream arquivo = null;
        try {
            arquivo = new FileOutputStream(nomeDoArquivo);
            ObjectOutputStream objeto = new ObjectOutputStream(arquivo);
            objeto.writeObject(numero);
            objeto.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map carregarArquivos(String nomeDoArquivo) throws Exception {
        FileInputStream arquivo = new FileInputStream(nomeDoArquivo);
        ObjectInputStream objeto = new ObjectInputStream(arquivo);
        Map listaDeEntidades = (Map) objeto.readObject();
        return listaDeEntidades;
    }

    public int carregarArquivoInt(String nomeDoArquivo) throws Exception {
        FileInputStream arquivo = new FileInputStream(nomeDoArquivo);
        ObjectInputStream objeto = new ObjectInputStream(arquivo);
        return (int) objeto.readObject();
    }
}
