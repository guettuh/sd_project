package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Game extends UnicastRemoteObject implements GameInterface {
	private Board board;
	private int turn = 1;
	private int playersInLine = 0;
	private final long LIMITED_TIME = 45 * 1000;
	private long start = System.currentTimeMillis();

	public Game() throws RemoteException {
		board = new Board();
	}

        @Override
	public int joinGame() throws RemoteException {
		if (playersInLine < 2){
			return ++playersInLine;
                } else {
			return -1;
                }
	}

        @Override
	public boolean turn(int i) throws RemoteException {
		return i == turn;
	}

        @Override
	public boolean pickValid(int row, int col) throws RemoteException {

		return board.getPosition(row, col) == ' ';
	}

        @Override
	public String pick(int playerID, int row, int col) throws RemoteException {

		board.pick(playerID, row, col);
		turn = (turn % 2) + 1;

		start = System.currentTimeMillis();
		System.out.print("Counting: ");
		System.out.println(start);
		return board.toString();
	}

        @Override
	public boolean finish() throws RemoteException {
		return board.checkBoard() != ' ' || board.boardFull() || countDown();
	}

        @Override
	public int result() throws RemoteException {
		int winnerPlayer = 0;
		char winner;
		if (board.winner) {
			winner = board.checkBoard();
			if (winner == 'X') {
				winnerPlayer = 1;
			} else if (winner == 'O') {
				winnerPlayer = 2;
			} else {
				winnerPlayer = 0;
			}
		} else if (countDown()) {
			return (turn % 2) + 1;
		}
		return winnerPlayer;
	}

        @Override
	public boolean countDown() throws RemoteException {
		return (System.currentTimeMillis() - start) >= LIMITED_TIME;
	}

        @Override
	public boolean ready() throws RemoteException {
		return playersInLine == 2;
	}

        @Override
	public String getBoardStr() throws RemoteException {
		return board.toString();
	}

        @Override
	public void newGame() throws RemoteException {
		if (playersInLine != 1) {
			playersInLine = 0;
			board.reset();
			turn = 1;
			start = System.currentTimeMillis();
		}
	}
}
