package jogo_do_galo;

import java.io.*;
import java.rmi.RemoteException;

//objeto board, mantem o tracking do estado do jogo

public class JGboard implements Serializable
{
   
   private char[][] owner;
   private char turn;
   private boolean winner = false;

   public JGboard()
   {
      owner = new char[3][3];
      reset();
   }

   //limpa tabuleiro, limpa as posições a vazios,
   //começa vencedor com X, always!
   public void reset()
   {
      for(int row = 0; row < 3; ++row)
      {
         for(int col = 0; col < 3; ++col)
            owner[row][col] = ' ';
      }
      turn = 'X';
      winner = false;
   }

   
   
   
   public char owner(int col, int row)
   {
      return owner[row][col];
   }

   //trata os Valores '0' e 'X'
   public String ownerStr(int col, int row)
   {
      char o = owner(col, row);
      if ( o == 'O' )
         return "O";
      else if ( o == 'X' )
         return "X";
      else
         return " ";
   }

   //Verifica se a posição está vazia
   public boolean isOpen(int col, int row)
   {
      return !winner && owner(col, row) == ' ';
   }

   //retorna o turno atual
   public char turn() { return turn; }

   //troca o turno após seleção da posição 
   public void pick(int col, int row)
   {
      assert !winner && owner[row][col] == ' ';
      owner[row][col] = turn;
      if ( turn == 'X' )
         turn = 'O';
      else
         turn = 'X';
   }

   //verifica vencedor
   public char winner()
   {
      winner = true;
      //verifica colunas
      for(int col = 0; col < 3; ++col)
      {
         if (    owner[0][col] != ' '
              && owner[0][col] == owner[1][col]
              && owner[1][col] == owner[2][col] )
            return owner[0][col];
      }
      //verifica linhas
      for(int row = 0; row < 3; ++row)
      {
         if (    owner[row][0] != ' '
              && owner[row][0] == owner[row][1]
              && owner[row][1] == owner[row][2] )
            return owner[row][0];
      }
      //verifica diagonais
      if ( owner[1][1] != ' ' )
      {
         if ( owner[0][0] == owner[1][1] && owner[1][1] == owner[2][2] )
            return owner[1][1];
         if ( owner[2][0] == owner[1][1] && owner[1][1] == owner[0][2] )
            return owner[1][1];
      }
      
      //retorna negativo após passar toda a verificação
      winner = false;
      return ' ';
   }
}

