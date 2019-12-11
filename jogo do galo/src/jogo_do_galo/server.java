package jogo_do_galo;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class server {
    
    String SERVICE_NAME="/JogoRemote";

	private void bindRMI(Game jogo) throws RemoteException {
		
		System.getProperties().put( "java.security.policy", "./server.policy");

		if( System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}

		try { 
			LocateRegistry.createRegistry(1099);
		} catch( RemoteException e) {
			
		}
		try {
		  LocateRegistry.getRegistry("127.0.0.1",1099).rebind(SERVICE_NAME, jogo);
		  } catch( RemoteException e) {
		  	System.out.println("Registry not found");
		  }
	}
        
        public server() {
		super();
	}
	
	public void pedidoJogo() {
		
		Game pedido = null;
		try {
			pedido = new Game();
		} catch (RemoteException e1) {
			System.err.println("unexpected error...");
			e1.printStackTrace();
		}
		
		try {
			bindRMI(pedido);
		} catch (RemoteException e1) {
			System.err.println("erro ao registar o stub...");
			e1.printStackTrace();
		}
		
	}
    
}
