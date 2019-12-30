package jogo_do_galo;

import java.rmi.RemoteException;
import java.rmi.server.RemoteRef;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class Game extends UnicastRemoteObject implements GameInterface {

    private Hashtable<String, NewGame> presentIPs = new Hashtable<String, NewGame>();

    public Game() throws RemoteException {
        super();
    }

    Vector[] tabuleiro;
    String Jogador1;
    String Jogador2;

    public Game(String Jogador1, String Jogador2) {
        this.Jogador1 = Jogador1;
        this.Jogador2 = Jogador2;
    }

    public Vector[] getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Vector[] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public Hashtable<String, NewGame> getPresentIPs() {
        return presentIPs;
    }

    public void setPresentIPs(Hashtable<String, NewGame> presentIPs) {
        this.presentIPs = presentIPs;
    }

    public String getJogador1() {
        return Jogador1;
    }

    public void setJogador1(String Jogador1) {
        this.Jogador1 = Jogador1;
    }

    public String getJogador2() {
        return Jogador2;
    }

    public void setJogador2(String Jogador2) {
        this.Jogador2 = Jogador2;
    }

    public RemoteRef getRef() {
        return ref;
    }

    public void setRef(RemoteRef ref) {
        this.ref = ref;
    }

}