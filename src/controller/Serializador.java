package controller;

import base.Pesquisa;

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

    public Map<String, Pesquisa> carregarArquivos(String nomeDoArquivo) throws Exception {
        FileInputStream arquivo = new FileInputStream(nomeDoArquivo);
        ObjectInputStream objeto = new ObjectInputStream(arquivo);
        return (Map<String, Pesquisa>) objeto.readObject();
    }

    public int carregarArquivoInt(String nomeDoArquivo) throws Exception {
        FileInputStream arquivo = new FileInputStream(nomeDoArquivo);
        ObjectInputStream objeto = new ObjectInputStream(arquivo);
        return (int) objeto.readObject();
    }
}
