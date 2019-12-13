package jogo_do_galo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface GameInterface extends Remote {

    public Vector<String> getJogo(String IPAddress, NewGameInterface ng) throws RemoteException;

}
