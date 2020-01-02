package client;

import java.rmi.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.*;
import server.JGboard;
import client.ClientInterface;
import server.ServiceInterface;

//frame que ilustra o acesso e atualização do jogo

public class JogoGalo extends JFrame implements ActionListener, ClientInterface {
   protected JButton[][] squares;
   protected JLabel statusLabel = new JLabel();
   protected JButton reset;
   protected DefaultListModel<String> listaUsers = new DefaultListModel<>();
   public List all;
int meuId;
JPanel squareFill;
JPanel box;
   
   protected ServiceInterface remoteJGBoard;
   String SERVICE_NAME = "/PlayerRemote";

   // Alterar a porta
   // public final static int ServicePort = 10001;

   // Alterar o HOST, para o HOST da máquina que executa o Server
   // String HOST = "localhost";

   // public void JogoGalos() {}

   public JogoGalo(String PLAYERSERVER, String HOST, int SERVICE_PORT) throws RemoteException, InterruptedException {

      super("Jogo do Galo");
      setSize(650, 400); // larger than needed; resizing done below
      // método para o serviço remoto
      try {
         System.out.println("Connecting...");
         String serverObjectName = "rmi://" + HOST + "/" + PLAYERSERVER;
         System.out.println(serverObjectName);
         remoteJGBoard = (ServiceInterface) Naming.lookup(serverObjectName);

         // serve para atualização do tabuleiros nos dois peers
         UnicastRemoteObject.exportObject(this, SERVICE_PORT);
         remoteJGBoard.register(this);
      } catch (Exception e) {
         System.out.println("Exception: " + e.getMessage());
         e.printStackTrace();
         return;
      }

      
      meuId=remoteJGBoard.printAll().size();
      System.out.println(meuId);
      
      
      
      JFrame frame = new JFrame("JOptionPane showMessageDialog");

      if (remoteJGBoard.printAll().size() > 2) {
         JOptionPane.showMessageDialog(frame, "Já existem dois jogadores na partida, aguarde e tente novamente");
         System.exit(0);
      } else if (remoteJGBoard.printAll().size() == 2) {
         JOptionPane.showMessageDialog(frame, "Encontrado Jogador. Boa Sorte!");
      } else {
         JOptionPane.showMessageDialog(frame, "Boa sorte!");
      }
      
      while(remoteJGBoard.printAll().size()!=2){
          System.out.println("...");
	  Thread.sleep(500);
      }
      
      
      // interface grafica do jogo, com eventListener

      // para fechar a janela
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent event) {
            System.exit(0);
         }
      });
      
      
      
      
      // main box; tudo colocado nesta box
      box = new JPanel();
      box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
      getContentPane().add(box);

      
      // Jlabel a informar quem é a jogar e no fim informa o vencedor
      box.add(statusLabel, BorderLayout.NORTH);

      // Implementação dos butões de jogo
      squareFill = new JPanel();
      squareFill.setLayout(new GridLayout(3, 3, 5, 5));

      // listagem de todas as conexões
      JPanel btnReset = new JPanel();
      btnReset.setLayout(new GridLayout(9, 9, 9, 9));

      // adiciona à box as componentes
      box.add(squareFill);
      box.add(btnReset);

      
      
      squares = new JButton[3][3];
      
      for (int row = 0; row < 3; ++row) {
         for (int col = 0; col < 3; ++col) {
            squares[row][col] = new JButton();
            squareFill.add(squares[row][col]);
            squares[row][col].addActionListener(this);
            
         }
      }
      
      

      reset = new JButton();
      reset.setText("Reset Jogo");
      btnReset.add(reset);
      reset.addActionListener(this);

      try {
         for (int i = 0; i < remoteJGBoard.printAll().size(); ++i) {
            String user = remoteJGBoard.printAll().get(i);
            listaUsers.add(i, user);
         }
      } catch (RemoteException ex) {

      }

      JList<String> list = new JList<>(listaUsers);

      box.add(list);

      // resize frame
      pack();
      setSize(300, 600);

   }

   // limpa Tabuleiro
   protected void resetBoard() {
      for (int row = 0; row < 3; ++row)
         for (int col = 0; col < 3; ++col)
            squares[row][col].setText(" ");
      try {
         remoteJGBoard.reset();
      } catch (RemoteException e) {
         System.out.println("Exception: " + e.getMessage());
         e.printStackTrace();
         System.exit(1);
      }
      setStatus(getBoard());
   }

   // avisa vez do jogador, e caso de vencedor informa.
   protected void setStatus(JGboard bd) {
      char w = bd.winner();
      if (w == ' ')
         statusLabel.setText("Click on " + bd.turn() + " square");
      else
         statusLabel.setText("Winner: " + w);
   }

   // retorna o tabuleio no estado mais atual
   protected JGboard getBoard() {
      JGboard res = null;
      try {
         res = remoteJGBoard.getState();
      } catch (RemoteException e) {
         System.out.println("Exception: " + e.getMessage());
         e.printStackTrace();
         System.exit(1);
      }
      return res;
   }

   // escolhe posição
   protected void pickSquare(int col, int row) {
      try {
         remoteJGBoard.pick(col, row);
      } catch (RemoteException e) {
         System.out.println("Exception: " + e.getMessage());
         e.printStackTrace();
         System.exit(1);
      }
   }

   // method "called" by remote object to update the state of the game
   public void updateBoard(JGboard new_board) throws RemoteException {
      for (int row = 0; row < 3; ++row)
         for (int col = 0; col < 3; ++col)
            squares[row][col].setText(new_board.ownerStr(col, row));
      setStatus(new_board);
   }

   // eventListener
   public void actionPerformed(ActionEvent event) {
      JGboard bd = getBoard();

      
      for (int row = 0; row < 3; ++row)
         for (int col = 0; col < 3; ++col)
            if (event.getSource() == squares[row][col]) {
               if (bd.isOpen(col, row)) {
                  pickSquare(col, row);
                  bd = getBoard();
                  squares[row][col].setText(bd.ownerStr(col, row));
                  setStatus(bd);

               }
               return;
            }
      if (event.getSource() == reset) {
         resetBoard();
      }
      if(event.getSource() == squares && meuId==1){
          box.setEnabled(false);
      }else if(event.getSource() == squares && meuId==2){
          box.setEnabled(false);
      }else{
          box.setEnabled(true);
      }
      
   }

}
