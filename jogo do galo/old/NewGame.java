package jogo_do_galo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class NewGame extends UnicastRemoteObject implements NewGameInterface {

    public NewGame() throws RemoteException {
        super();
    };

    public void setNewGame(String nickname, String IPAddress) throws RemoteException {
        System.out.println(nickname + " está neste endereço " + IPAddress);
    }
}
