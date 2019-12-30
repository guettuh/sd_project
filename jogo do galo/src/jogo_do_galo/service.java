package jogo_do_galo;

import java.rmi.*;

//interface para server do jogo

public interface service extends Remote {
    
    public JGboard getState() throws RemoteException;
    public void pick(int col, int row) throws RemoteException;
    public void reset()  throws RemoteException;
    // added so client can register self with server for callbacks
    public void register(ClientRemote newClient) throws RemoteException;
}
