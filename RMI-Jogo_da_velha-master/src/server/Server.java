package serverPeer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import javax.swing.JOptionPane;

import server.Game;

public class Server {
    
    private static String PLAYERSERVER;
    
	public static void main(String[] args) throws RemoteException, MalformedURLException {
	
            PLAYERSERVER = JOptionPane.showInputDialog("Insira o seu nome:");  
                
		LocateRegistry.createRegistry(1099);
		Game jogo =  new Game();
                String serverObjectName = "rmi://localhost" + "/" + PLAYERSERVER;
		Naming.rebind(serverObjectName, jogo);
		System.out.println("Service running! Launch ClientGame!");
	}

}
