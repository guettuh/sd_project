package client;

import java.rmi.*;
import server.JGboard;

//class que permite a troca de jogador, sempre que o tabuleiro muda de estado

public interface ClientInterface extends Remote {

   void updateBoard(JGboard new_board) throws RemoteException;
}
