package server;

import java.rmi.*;
import java.util.ArrayList;
import client.ClientInterface;

//interface para server do jogo

public interface ServiceInterface extends Remote {

    public JGboard getState() throws RemoteException;

    public void pick(int col, int row) throws RemoteException;

    public void reset() throws RemoteException;

    // added so client can register self with server for callbacks
    public void register(ClientInterface newClient) throws RemoteException;

    public ArrayList<String> printAll() throws RemoteException;
    
    //public void turnPlayer() throws RemoteException;
    public boolean turn() throws RemoteException;
            
}
