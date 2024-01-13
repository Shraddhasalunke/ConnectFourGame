import java.util.Scanner;

public class ConnectFourGame {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final char EMPTY = ' ';
    private static final char PLAYER1 = 'X';
    private static final char PLAYER2 = 'O';

    private char[][] board;

    public ConnectFourGame() {
        board = new char[ROWS][COLUMNS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    public void printBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                System.out.print("| " + board[row][col] + " ");1
            }
            System.out.println("|");
        }
        System.out.println("  1   2   3   4   5   6   7");
    }

    public boolean dropPiece(int column, char player) {
        if (column < 0 || column >= COLUMNS || board[0][column] != EMPTY) {
            return false;
        }

        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][column] == EMPTY) {
                board[row][column] = player;
                return true;
            }
        }

        return false;
    }

    public boolean checkWin(char player) {
        
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col] == player && board[row][col + 1] == player
                        && board[row][col + 2] == player && board[row][col + 3] == player) {
                    return true;
                }
            }
        }

        
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (board[row][col] == player && board[row + 1][col] == player
                        && board[row + 2][col] == player && board[row + 3][col] == player) {
                    return true;
                }
            }
        }

        
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col] == player && board[row + 1][col + 1] == player
                        && board[row + 2][col + 2] == player && board[row + 3][col + 3] == player) {
                    return true;
                }
            }
        }

        
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 3; col < COLUMNS; col++) {
                if (board[row][col] == player && board[row + 1][col - 1] == player
                        && board[row + 2][col - 2] == player && board[row + 3][col - 3] == player) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isFull() {
        for (int col = 0; col < COLUMNS; col++) {
            if (board[0][col] == EMPTY) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ConnectFourGame game = new ConnectFourGame();
        Scanner scanner = new Scanner(System.in);
        boolean player1Turn = true;

        while (true) {
            game.printBoard();

            char currentPlayer = player1Turn ? PLAYER1 : PLAYER2;
            System.out.println("Player " + currentPlayer + ", enter your column (1-7): ");

            int column = scanner.nextInt() - 1;
            if (game.dropPiece(column, currentPlayer)) {
                if (game.checkWin(currentPlayer)) {
                    game.printBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                } else if (game.isFull()) {
                    game.printBoard();
                    System.out.println("It's a draw!");
                    break;
                }

                player1Turn = !player1Turn;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }
}

