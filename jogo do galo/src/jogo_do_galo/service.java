package jogo_do_galo;

import java.rmi.*;
import java.util.ArrayList;

//interface para server do jogo

public interface service extends Remote {

    public JGboard getState() throws RemoteException;

    public void pick(int col, int row) throws RemoteException;

    public void reset() throws RemoteException;

    // added so client can register self with server for callbacks
    public void register(ClientRemote newClient) throws RemoteException;

    public ArrayList<String> printAll() throws RemoteException;

    public boolean isPronto() throws RemoteException;

    public boolean isMinhaVez(int i) throws RemoteException;

    public boolean isPartidaEncerrada() throws RemoteException;

    public boolean ultrapassouTempo() throws RemoteException;
    
    public void turnPlayer() throws RemoteException;
    public boolean turn() throws RemoteException;
            
}
