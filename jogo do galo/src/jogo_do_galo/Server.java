package jogo_do_galo;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class Server {
    
    //Esta classe cria uma instância da classe JOGOGALO e 
//regista-a no servidor de nomes Java RMI (rmiregistry) com o nome “/PlayerRemote”
   public final static String SERVICE_NAME = "/PlayerRemote";

// modify the following port number to 1099
//não sei se a porta é fixa e se devemos manter o 1099!!
   public final static int ServicePort = 10000;

    public static void main(String args[]) throws RemoteException, MalformedURLException  
   {
      System.out.println("Iniciando Jogo...");
     
      try
      {
          LocateRegistry.createRegistry(1099);
          service cserv = new serviceImpl();
          
         String serverObjectName = "rmi://localhost" + SERVICE_NAME;
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
