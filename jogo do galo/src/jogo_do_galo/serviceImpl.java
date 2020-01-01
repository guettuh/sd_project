package jogo_do_galo;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

//implementação da interface service

class serviceImpl extends UnicastRemoteObject implements service {

   // the object being served up
   protected JGboard board = new JGboard();
   private long horarioInicio = System.currentTimeMillis();
   private long DURACAO_MAXIMA = 45 * 1000;
   private int jogadorDaVez = 1;
   protected JogoGalo remoteBoard;

   public boolean isPronto() throws RemoteException {
      return clients.size() == 2;
   }

   public boolean isPartidaEncerrada() throws RemoteException {
      return board.winner() == 'X' || board.winner() == 'O' || ultrapassouTempo();
   }

   public boolean isMinhaVez(int i) throws RemoteException {
      return i == jogadorDaVez;
   }

   public boolean ultrapassouTempo() throws RemoteException {
      System.out.print("O tempo passado em milissegundos � ");
      System.out.println(System.currentTimeMillis() - horarioInicio);
      return (System.currentTimeMillis() - horarioInicio) >= DURACAO_MAXIMA;
   }

   public serviceImpl() throws RemoteException {
      super();
   }

   // implementations of methods in CounterService
   public JGboard getState() throws RemoteException {
      return board;
   }

   public void pick(int col, int row) throws RemoteException {
      board.pick(col, row);
      updateClients();
   }

   public void reset() throws RemoteException {
      board.reset();
      updateClients();
   }
   
   public boolean turn() throws RemoteException{
       return board.turn()=='X';
   }

   // Permite o server identificar quem são os clientes
   // A lista é sincrona para evitar problemas da concorrencia

   public List clients = Collections.synchronizedList(new LinkedList());

   public void register(ClientRemote newClient) throws RemoteException {
      clients.add(newClient);
      System.out.println("Isto é uma impressão de novo cliente " + newClient);
   }

   public ArrayList<String> printAll() throws RemoteException {
      ArrayList<String> printAll = new ArrayList<>();
      for (int i = 0; i < clients.size(); ++i) {
         int indexInicial = clients.get(i).toString().indexOf("endpoint:");
         int indexFinal = clients.get(i).toString().indexOf("(remote)");
         printAll.add("Jogador " + (i + 1) + " " + clients.get(i).toString().substring(indexInicial + 9, indexFinal));
      }
      return printAll;
   }

   public void turnPlayer(){
        if(clients.contains(1)){
          remoteBoard.setEnabled(true);
        }else if(clients.contains(1)){
          remoteBoard.setEnabled(true);
      }else{
         remoteBoard.setEnabled(false);}
         }


   public void updateClients() {
      Iterator it = clients.iterator();
      while (it.hasNext()) {
         ClientRemote client = (ClientRemote) it.next();
         try {
            client.updateBoard(board);
         } catch (Exception e) {
            System.out.println("Não foi possivel atualizar o cliente " + client.toString());
         }
      }

   }
}