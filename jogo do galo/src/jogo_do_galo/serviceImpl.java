package jogo_do_galo;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.rmi.registry.LocateRegistry;

//implementação da interface service

class serviceImpl extends UnicastRemoteObject implements service{
    
    
    
// the object being served up
   protected JGboard board = new JGboard();
   String convite = null;

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
   
   public List clients = Collections.synchronizedList(new LinkedList());
   
   public void register(ClientRemote newClient) throws RemoteException
   {
      clients.add(newClient);
      System.out.println("Isto é uma impressão" + newClient);
   }

   
   /*print clients
   public void printClients() throws RemoteException {
       for(int i=0;i<clients.size();++i){
           clients.iterator();
       }
   }*/
   
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
}