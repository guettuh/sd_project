package server;

import java.rmi.Remote;
import java.rmi.RemoteException;



public interface GameInterface extends Remote {
	
	public int joinGame () throws RemoteException;
	public boolean turn (int i) throws RemoteException;
	public boolean pickValid (int row, int col) throws RemoteException;
	public String pick(int i, int row, int col) throws RemoteException;
	public boolean finish() throws RemoteException;
        public int result() throws RemoteException;
        public boolean ready() throws RemoteException;
        public String getBoardStr() throws RemoteException;
        public void newGame() throws RemoteException;
        public boolean countDown() throws RemoteException;
}
