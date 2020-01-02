package server;

public class Board {

    // novo tabuleiro e sem vencedores
    private char[][] board = new char[3][3];
    public boolean winner = false;

    public void reset() {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col)
                board[row][col] = ' ';
        }
    }

    // Método para exibir o tabuleiro sempre que executar uma nova jogada.
    // impresso no server!!
    public void getBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 'X')
                    System.out.print(" X ");

                if (board[row][col] == 'O')
                    System.out.print(" O ");

                if (board[row][col] == ' ')
                    System.out.print("   ");

                if (col == 0 || col == 1)
                    System.out.print("/");
            }
            System.out.println();

        }
    }

    // método para verificar o tabuleiro
    public char checkBoard() {
        winner = true;
        // verifica colunas
        for (int col = 0; col < 3; ++col) {
            if (board[0][col] != ' ' && board[0][col] == board[1][col] && board[1][col] == board[2][col])
                return board[0][col];
        }
        // verifica linhas
        for (int row = 0; row < 3; ++row) {
            if (board[row][0] != ' ' && board[row][0] == board[row][1] && board[row][1] == board[row][2])
                return board[row][0];
        }
        // verifica diagonais
        if (board[1][1] != ' ') {
            if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
                return board[1][1];
            if (board[2][0] == board[1][1] && board[1][1] == board[0][2])
                return board[1][1];
        }

        // retorna negativo após passar toda a verificação
        winner = false;
        return ' ';
    }

    // Método para verificar se o tabuleiro foi totalmente preenchido
    public boolean boardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    // Método para retornar a posição
    public int getPosition(int row, int col) {
        return board[row - 1][col - 1];
    }

    //
    public void pick(int jogador, int row, int col) {
        if (jogador == 1) {
            board[row - 1][col - 1] = 'X';
        } else {
            board[row - 1][col - 1] = 'O';
        }
        getBoard();
    }

    // Método para imprimir na consola
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 'X') {
                    sb.append(" X ");
                }
                if (board[row][col] == 'O') {
                    sb.append(" O ");
                }
                if (board[row][col] == ' ') {
                    sb.append("   ");
                }

                if (col == 0 || col == 1) {
                    sb.append("|");
                }
                
                
                
            }
            if(row == 0 || row == 1){
                    sb.append("-----------\n");
            }
        }
        return sb.toString();
    }
}
