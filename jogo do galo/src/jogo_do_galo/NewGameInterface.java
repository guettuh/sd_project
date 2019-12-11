package jogo_do_galo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NewGameInterface extends Remote {

    public void setNewGame(String nickname, String IPAddress) throws RemoteException;

    
}
