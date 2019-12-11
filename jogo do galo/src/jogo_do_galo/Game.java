package jogo_do_galo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;


public class Game extends UnicastRemoteObject implements GameInterface{

    private Hashtable<String, Jogo_n> presentIPs = new Hashtable<String, Jogo_n>();
	
    public Game() throws RemoteException {
        super();
    }
    
    
    class Jogo_n{
    
        Vector[] tabela;
        int Jogador1;
        int Jogador2;
        
        public Jogo_n(int Jogador1, int Jogador2){
            this.Jogador1=Jogador1;
            this.Jogador2=Jogador2;
        }
        
        
        
    }
    
    
    
}
