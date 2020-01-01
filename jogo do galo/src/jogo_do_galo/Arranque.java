package jogo_do_galo;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;
import java.rmi.RemoteException;

import jogo_do_galo.JogoGalo;
import jogo_do_galo.serviceImpl;


public class Arranque extends JFrame {
    
    String PLAYERSERVER;
    public String HOST;
    protected String SERVICE_PORT;
    
    public Arranque() throws RemoteException, InterruptedException {
        super("Jogo do Galo - Escolha o server!");
      setSize(650, 400);

        
        addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent event) {
            System.exit(0);
         }
      });
        
        
      JPanel box = new JPanel();
      box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
      getContentPane().add(box);
      PLAYERSERVER = JOptionPane.showInputDialog("A começar um novo jogo, insira o nome do adversário: ");
      HOST = JOptionPane.showInputDialog("Insira o Endereço IP do Server: ");
      SERVICE_PORT = JOptionPane.showInputDialog("Insira a sua Porta: ");
      
      
      
      JogoGalo newJogo = new JogoGalo(PLAYERSERVER, HOST, Integer.parseInt(SERVICE_PORT));
      newJogo.setVisible(true);
      
   }
    
    public static void main(String[] args) throws RemoteException, InterruptedException {
      Arranque ar = new Arranque();
      ar.setVisible(true);
      ar.setVisible(false);
   }
}
