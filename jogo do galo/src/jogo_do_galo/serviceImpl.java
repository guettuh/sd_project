package jogo_do_galo;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.rmi.registry.LocateRegistry;

//implementação da interface service

class serviceImpl extends UnicastRemoteObject implements service{
    
//Esta classe cria uma instância da classe JOGOGALO e 
//regista-a no servidor de nomes Java RMI (rmiregistry) com o nome “/PlayerRemote”
   public final static String SERVICE_NAME = "/PlayerRemote";

// modify the following port number to 1099
//não sei se a porta é fixa e se devemos manter o 1099!!
   public final static int ServicePort = 10000;

// the object being served up
   protected JGboard board = new JGboard();


   public serviceImpl() throws RemoteException {
      super();
   }

// implementations of methods in CounterService
   public JGboard getState() throws RemoteException
   {
      return board;
   }

   public void pick(int col, int row) throws RemoteException
   {
      board.pick(col, row);
      updateClients();
   }

   public void reset() throws RemoteException
   {
      board.reset();
      updateClients();
   }

//Permite o server identificar quem são os clientes
//A lista é sincrona para evitar problemas da concorrencia
   
   List clients = Collections.synchronizedList(new LinkedList());
   public void register(ClientRemote newClient) throws RemoteException
   {
      clients.add(newClient);
   }

   public void updateClients()
   {
      Iterator it = clients.iterator();
      while ( it.hasNext() )
      {
         ClientRemote client = (ClientRemote)it.next();
         try
         {
            client.updateBoard(board);
         }
         catch ( Exception e )
         {
            System.out.println("Não foi possivel atualizar o cliente " + client.toString());
         }
      }
          
   }

   //solução da ficha2, para fazer a comunicação, a de cima também funciona,
   //ligeiramente diferente à de baixo
   
   /*
   private void bindRMI(serviceImpl servico) throws RemoteException {
		
		System.getProperties().put( "java.security.policy", "./server.policy");

		if( System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}

		try { 
			LocateRegistry.createRegistry(1099);
		} catch( RemoteException e) {
			
		}
		try {
		  LocateRegistry.getRegistry("127.0.0.1",1099).rebind(SERVICE_NAME, servico);
		  } catch( RemoteException e) {
		  	System.out.println("Registry not found");
		  }
	}
   
   public void createServico() {
		
		serviceImpl servico = null;
		try {
			servico = new serviceImpl();
		} catch (RemoteException e1) {
			System.err.println("unexpected error...");
			e1.printStackTrace();
		}
		
		try {
			bindRMI(servico);
		} catch (RemoteException e1) {
			System.err.println("erro ao registar o stub...");
			e1.printStackTrace();
		}
		
	}*/
   
   public static void main(String args[]) 
   {
      System.out.println("Iniciando Jogo...");
      
      /*serviceImpl ps = null;
       try {
           ps = new serviceImpl();
           System.out.println("Service running.");
       } catch (RemoteException ex) {
           System.out.println("Exception: " + ex.getMessage());
       }
		ps.createServico();*/
      try
      {
         service cserv = new serviceImpl();
         String serverObjectName = "Localhost:" + ServicePort
            + "/" + SERVICE_NAME;
         Naming.rebind(serverObjectName, cserv);
         System.out.println("Service running.");
      }
      catch (Exception e)
      {
         System.out.println("Exception: " + e.getMessage());
         e.printStackTrace();
      }
   }
   
   
}


