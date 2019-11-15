package controller;

import java.io.*;

public class Serealizador {

    public Serealizador(){}

    public  void salvarArquivos(ControllerPesquisador cPesquisador, ControllerPesquisas cPesquisa, ControllerProblemas cProblema,
                                ControllerAtividades cAtividade, ControllerObjetivos cObjetivo) {
        try {
            FileOutputStream filePequisadores = new FileOutputStream("ControllerPesquisador");
            ObjectOutputStream objectPesquisadores = new ObjectOutputStream(filePequisadores);
            objectPesquisadores.writeObject(cPesquisador);
            objectPesquisadores.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream filePequisas = new FileOutputStream("ControllerPesquisas");
            ObjectOutputStream objectPesquisas = new ObjectOutputStream(filePequisas);
            objectPesquisas.writeObject(cPesquisa);
            objectPesquisas.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream fileProblemas = new FileOutputStream("ControllerProblemas");
            ObjectOutputStream objectProblemas = new ObjectOutputStream(fileProblemas);
            objectProblemas.writeObject(cProblema);
            objectProblemas.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream fileAtividades = new FileOutputStream("ControllerAtividades");
            ObjectOutputStream objectAtividades = new ObjectOutputStream(fileAtividades);
            objectAtividades.writeObject(cAtividade);
            objectAtividades.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream fileObjetivos = new FileOutputStream("ControllerObjetivos");
            ObjectOutputStream objectObjetivos = new ObjectOutputStream(fileObjetivos);
            objectObjetivos.writeObject(cObjetivo);
            objectObjetivos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
            /*FileOutputStream fileGeral = new FileOutputStream("ControllerGeral");
            ObjectOutputStream objectGeral = new ObjectOutputStream(fileGeral);
            objectGeral.writeObject(cGeral);
            objectGeral.close();*/

    }

    public void carregarArquivos(ControllerPesquisador cPesquisador, ControllerPesquisas cPesquisa, ControllerProblemas cProblema,
                                 ControllerAtividades cAtividade, ControllerObjetivos cObjetivo) {
        FileInputStream filePequisadores = null;
        try {
            filePequisadores = new FileInputStream("ControllerPesquisador");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream objectPesquisadores = null;
        try {
            objectPesquisadores = new ObjectInputStream(filePequisadores);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            cPesquisador = (ControllerPesquisador) objectPesquisadores.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        FileInputStream filePequisas = null;
        try {
            filePequisas = new FileInputStream("ControllerPesquisas");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream objectPesquisas = null;
        try {
            objectPesquisas = new ObjectInputStream(filePequisas);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            cPesquisa = (ControllerPesquisas) objectPesquisas.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        FileInputStream fileProblemas = null;
        try {
            fileProblemas = new FileInputStream("ControllerProblemas");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream objectProblemas = null;
        try {
            objectProblemas = new ObjectInputStream(fileProblemas);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            cProblema = (ControllerProblemas) objectProblemas.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        FileInputStream fileAtividades = null;
        try {
            fileAtividades = new FileInputStream("ControllerAtividades");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream objectAtividades = null;
        try {
            objectAtividades = new ObjectInputStream(fileAtividades);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            cAtividade = (ControllerAtividades) objectAtividades.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        FileInputStream fileObjetivos = null;
        try {
            fileObjetivos = new FileInputStream("ControllerObjetivos");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream objectObjetivos = null;
        try {
            objectObjetivos = new ObjectInputStream(fileObjetivos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            cObjetivo = (ControllerObjetivos) objectObjetivos.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
