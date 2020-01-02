package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import javax.swing.JOptionPane;

import server.GameInterface;

public class ClientGame {

	public static void start(GameInterface game, Scanner sc) throws RemoteException, NotBoundException, MalformedURLException, InterruptedException {
		int playerID;
		int pick;
		playerID = game.joinGame();
                
		System.out.println("Aguardando novo jogador...");
		while (!game.ready()) {
			System.out.println("...");
			Thread.sleep(500);
		}
		System.out.println("Jogador encontrado!");

		while (!game.finish()) {
			if (game.turn(playerID)) {
				System.out.println(game.getBoardStr());
				System.out.println("Faça sua jogada, indique a posição de 1 a 9");
				pick = sc.nextInt();
				int row = 0;
				int col = 0;
				switch (pick) {
				case 1:
					row = 1;
					col = 1;
					break;
				case 2:
					row = 1;
					col = 2;
					break;
				case 3:
					row = 1;
					col = 3;
					break;
				case 4:
					row = 2;
					col = 1;
					break;
				case 5:
					row = 2;
					col = 2;
					break;
				case 6:
					row = 2;
					col = 3;
					break;
				case 7:
					row = 3;
					col = 1;
					break;
				case 8:
					row = 3;
					col = 2;
					break;
				case 9:
					row = 3;
					col = 3;
					break;
				}

				if (game.countDown())
					break;

				if (game.pickValid(row, col)) {
					game.pick(playerID, row, col);
					System.out.println("Jogador " + playerID + " jogou na linha: " 
                                                + row + " e na coluna: " + col + "\n" + game.getBoardStr());
					if (!game.finish())
						System.out.println("Aguardando Jogador...");
				} else {
					System.out.println("Jogada inválida! Tente novamente!");
				}
			}
			Thread.sleep(100);
		}

		if (game.result() == playerID) {
			System.out.println("Parabéns jogador" + playerID + "você ganhou um Jogo do Galo");
		} 
                else if (game.result() == 0) {
			System.out.println("Empate!");
		} 
                else {
                    if (game.countDown())
			System.out.println("Terminou o tempo disponivel paa jogada! Perdeu a partida! Tente novamente.");
                    else
			System.out.println("Perdeu a partida jogador " + playerID + "! Tente novamente.");
		}

		System.out.println("Começar uma nova partida escreva 1");
		if (sc.nextInt() == 1) {
			game.newGame();
			start(game, sc);
		}
	}

	// main com ligação ao servico rmi.
	// JOption com inputDialog para questionar o peer pelo nome e ip da máquina
	public static void main(String[] args)
			throws RemoteException, NotBoundException, MalformedURLException, InterruptedException {

		String PLAYERSERVER = JOptionPane.showInputDialog("A começar um novo jogo, insira o nome do peer remoto: ");
		String HOST = JOptionPane.showInputDialog("Agora insira o Endereço IP: ");

		try {
			GameInterface jogo = (GameInterface) Naming.lookup("rmi://" + HOST + "/" + PLAYERSERVER);

			Scanner sc = new Scanner(System.in);
			System.out.println("Bem vindo ao Jogo do Galo!");

			System.out.println("Para começar uma partida nova digite 1");

			int newGame = sc.nextInt();

			if (newGame == 1) {
				start(jogo, sc);
				sc.close();
			}
		} catch (Exception e) {

			System.err.println("Servidor não iniciado...");
		}
	}

}
