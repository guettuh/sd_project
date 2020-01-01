package jogo_do_galo;

import java.rmi.*;

//class que permite a troca de jogador, sempre que o tabuleiro muda de estado

public interface ClientRemote extends Remote {

   void updateBoard(JGboard new_board) throws RemoteException;
}
