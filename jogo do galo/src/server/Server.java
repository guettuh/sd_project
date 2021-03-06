package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import javax.swing.JOptionPane;

public class Server {

   // Esta classe cria uma instância da classe JOGOGALO e
   // regista-a no servidor de nomes Java RMI (rmiregistry) com o nome
   // “PLAYERSERVER” solicitado ao utilizador

   public final static String SERVICE_NAME = "/PlayerRemote";
   private static String PLAYERSERVER;
   public final static int ServicePort = 10000;

   public static void main(String args[]) throws RemoteException, MalformedURLException {
      System.out.println("Iniciando Jogo...");

      PLAYERSERVER = JOptionPane.showInputDialog("Insira o seu nome:");

      try {
         LocateRegistry.createRegistry(1099);
         ServiceInterface servico = new Service();

         String serverObjectName = "rmi://localhost" + "/" + PLAYERSERVER;
         System.out.println(serverObjectName);
         Naming.rebind(serverObjectName, servico);
         System.out.println("Service running.");
      } catch (Exception e) {
         System.out.println("Exception: " + e.getMessage());
         e.printStackTrace();
      }
   }
}
