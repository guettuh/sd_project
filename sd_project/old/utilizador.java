import java.net.*;
import java.io.*;
import java.util.*;

//criei o utilizador, acho que esta a mais.
//guarda um idRegisto e um nickName, ambos do tipo string
//guarda numa Hashtable a lista de utilizadores

public class utilizador {

    private int idRegisto;
    private String nickName;
    private static ArrayList<utilizador> listaUtilizadores = new ArrayList<utilizador>();

    public utilizador() {
    }

    public utilizador(String nickName) {
        this.idRegisto = listaUtilizadores.size() + 1;
        this.nickName = nickName;
    }

    public int getIdRegisto() {
        return idRegisto;
    }

    public String getNickName() {
        return nickName;
    }

    // por incremental
    public void setIdRegisto(int idRegisto) {
        this.idRegisto = idRegisto;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public static void setListaUtilizadores(ArrayList<utilizador> listaUtilizadores) {
        utilizador.listaUtilizadores = listaUtilizadores;
    }

    public static ArrayList<utilizador> getListaUtilizadores() {
        return listaUtilizadores;
    }

}