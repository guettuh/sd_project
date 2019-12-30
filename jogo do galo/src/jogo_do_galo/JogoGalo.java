package jogo_do_galo;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.awt.*;
import java.awt.event.*;
import java.rmi.registry.LocateRegistry;
import javax.swing.*;

//frame que ilustra o acesso e atualização do jogo

public class JogoGalo extends JFrame implements ActionListener, ClientRemote
{ 
   protected JButton[][] squares;
   protected JLabel statusLabel = new JLabel();

   protected service remoteJGBoard;
    String SERVICE_NAME="/PlayerRemote";
// modify the following port number to 1099
   public final static int ServicePort = 10000;
   public final static String Server = "127.0.0.1";

   public JogoGalo()
   {
       
      super("Jogo do Galo");
      setSize(650, 400); // larger than needed; resizing done below

      
      
      // set up the remote service
      //rever este metodo
      try
      {
         System.out.println("Connecting...");
         /*String serverObjectName = "rmi"
            + serviceImpl.ServicePort + "/"
            + serviceImpl.SERVICE_NAME;
         remoteJGBoard = (service)Naming.lookup(serverObjectName);*/
         remoteJGBoard= (service) LocateRegistry.getRegistry(Server, ServicePort).lookup(SERVICE_NAME);
         //
         // the next two lines allow the server to call updateBoard:
         //
         UnicastRemoteObject.exportObject(this);
         remoteJGBoard.register(this);
      }
      catch ( Exception e )
      {
         System.out.println("Exception: " + e.getMessage());
         e.printStackTrace();
         return;
      }
      

      // a bit of magic to halt the application if the user closes the window
      // interface grafica do jogo, com eventListener
      addWindowListener(
         new WindowAdapter() {
         public void windowClosing(WindowEvent event) { System.exit(0); }
         });

      // main box; everything placed in this
      JPanel box = new JPanel();
      box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
      getContentPane().add(box);

      box.add(statusLabel, BorderLayout.NORTH);

      // set up the x's and o's
      JPanel xs_and_os = new JPanel();
      xs_and_os.setLayout(new GridLayout(3, 3, 5, 5));
      box.add(xs_and_os);

      squares = new JButton[3][3];
      for(int row = 0; row < 3; ++row)
      {
         for(int col = 0; col < 3; ++col)
         {
            squares[row][col] = new JButton();
            xs_and_os.add(squares[row][col]);
            squares[row][col].addActionListener(this);
         }
      }

      resetBoard();
      
      // resize frame
      pack();
      setSize(300, 300);
   }

   //limpa Tabuleiro
   protected void resetBoard()
   {
      for(int row = 0; row < 3; ++row)
         for(int col = 0; col < 3; ++col)
            squares[row][col].setText(" ");
      try
      {
         remoteJGBoard.reset();
      }
      catch ( RemoteException e )
      {
         System.out.println("Exception: " + e.getMessage());
         e.printStackTrace();
         System.exit(1);
      }
      setStatus(getBoard());
   }

   //verifica vencedor
   protected void setStatus(JGboard bd)
   {
      char w = bd.winner();
      if ( w == ' ' )
         statusLabel.setText("Click on " + bd.turn() + " square");
      else
         statusLabel.setText("Winner: " + w);
   }

   //retorna o tabuleio no estado mais atual
   protected JGboard getBoard()
   {
      JGboard res = null;
      try
      {
         res = remoteJGBoard.getState();
      }
      catch ( RemoteException e )
      {
         System.out.println("Exception: " + e.getMessage());
         e.printStackTrace();
         System.exit(1);
      }
      return res;
   }

   //escolhe posição
   protected void pickSquare(int col, int row)
   {
      try
      {
         remoteJGBoard.pick(col, row);
      }
      catch ( RemoteException e )
      {
         System.out.println("Exception: " + e.getMessage());
         e.printStackTrace();
         System.exit(1);
      }
   }

   // method "called" by remote object to update the state of the game
   public void updateBoard(JGboard new_board) throws RemoteException
   {
      for(int row = 0; row < 3; ++row)
         for(int col = 0; col < 3; ++col)
            squares[row][col].setText(new_board.ownerStr(col, row));
      setStatus(new_board);
   }

   //eventListener
   public void actionPerformed(ActionEvent event)
   {
      JGboard bd = getBoard();

      for(int row = 0; row < 3; ++row)
         for(int col = 0; col < 3; ++col)
            if ( event.getSource() == squares[row][col] )
            {
               if ( bd.isOpen(col, row) )
               {
                  pickSquare(col, row);
                  bd = getBoard();
                  squares[row][col].setText(bd.ownerStr(col, row));
                  setStatus(bd);
               }
               return;
            }
   }
   
   public static void main(String[] args)
   {
      JogoGalo bd = new JogoGalo();
      bd.setVisible(true);
   }
}

