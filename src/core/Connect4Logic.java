/**
 * Connect4Logic class provides the core logic for the Connect 4 game.
 * The Class provides logic for the game board, player moves,
 * and win/draw/loss conditions.
 *
 * @author 
 * @version 03.24.2024
 */
package core;


/**
 * Connect4Logic class provides the core logic for the Connect 4 game.
 * It manages the game board, player moves, and win/draw conditions.
 */
public class Connect4Logic {
    public final int COL = 7;
    public final int ROWS = 6;
    private char selectedPlayer;
    private char[][] board;

    /**
     * Constructor for Connect4Logic initializes the game board and sets the initial player.
     */
    public Connect4Logic() {
        board = new char[ROWS][COL];
        selectedPlayer = 'X';
        initBoard();
    }

    /**
     * Initializes the game board with empty spaces.
     */
    public void initBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = ' ';
            }
        }
    }


    /**
     * This checks if a player's move is valid and updates the game board based off that.
     *
     * @param column The column number where the player wants to move their symbol.
     * @return True if the move is correct, false if the selected column is already full.
     */
    public boolean playerMove(int column) {
        if (column < 1 || column > COL) {
            return false;
        }
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][column - 1] == ' ') {
                board[i][column - 1] = selectedPlayer;
                return true;
            }
        }
        return false; // Column is full
    }

    /**
     * Checks if the current player has won the game by forming a vertical, horizontal, or diagonal line of 4.
     *
     * @return True if the current player has won, false otherwise.
     */
    public boolean winChecker() {
        for (int col = 0; col < COL; col++) {
            for (int row = 0; row < ROWS; row++) {
                if (downChecker(row, col) || leftAndRightChecker(row, col) || diagonalChecker(row, col)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This checks if there are 4 consecutive player symbols downwards from the given position.
     * Downward only because we are checking every move, so a piece cannot be on top of it.
     *
     * @param row The row index to start the check from.
     * @param col The column index to check.
     * @return True if there are four consecutive player symbols downwards, false otherwise.
     */
    public boolean downChecker(int row, int col) {
        try {
            char playerSymbol = board[row][col];
            if (playerSymbol == ' ') {
                return false;
            }
            int count = 0;
            for (int i = row; i < ROWS; i++) {
                if (board[i][col] == playerSymbol) {
                    count++;
                } else {
                    break;
                }
            }
            return count >= 4;
        } catch (Exception e) {
            // Throw a NullPointerException with a custom message
            throw new NullPointerException("Error in downChecker: " + e.getMessage());
        }
    }

    /**
     * This checks if there are 4 consecutive player's symbols horizontally or diagonally from the move.
     *
     * @param row The row index to check.
     * @param col The column index to check.
     * @return True if there are four consecutive player symbols horizontally or diagonally, false otherwise.
     */
    public boolean leftAndRightChecker(int row, int col) {
        try {
            char playerSymbol = board[row][col];
            if (playerSymbol == ' ') {
                return false;
            }

            int countLeft = 0;
            for (int i = col; i >= 0; i--) {
                if (board[row][i] == playerSymbol) {
                    countLeft++;
                } else {
                    break;
                }
            }

            int countRight = 0;
            for (int i = col; i < COL; i++) {
                if (board[row][i] == playerSymbol) {
                    countRight++;
                } else {
                    break;
                }
            }
            return countLeft + countRight - 1 >= 4; // Subtract 1 to account for the current position counted twice
        } catch (Exception e) {
            throw new NullPointerException("Error in leftAndRightChecker: " + e.getMessage());
        }
    }


    /**
     * This checks if there are four consecutive player symbols diagonally from the given position.
     *
     * @param row The row index to check.
     * @param col The column index to check.
     * @return True if there are four consecutive player symbols diagonally, false otherwise.
     */
    public boolean diagonalChecker(int row, int col) {
        try {
            char playerSymbol = board[row][col];
            if (playerSymbol == ' ') {
                return false;
            }
            // Check diagonal from top-left to bottom-right
            int countDiagonal1 = 0;
            for (int i = 0; row + i < ROWS && col + i < COL; i++) {
                if (board[row + i][col + i] == playerSymbol) {
                    countDiagonal1++;
                } else {
                    break;
                }
            }
            // Check diagonal from top-right to bottom-left
            int countDiagonal2 = 0;
            for (int i = 0; row + i < ROWS && col - i >= 0; i++) {
                if (board[row + i][col - i] == playerSymbol) {
                    countDiagonal2++;
                } else {
                    break;
                }
            }
            return countDiagonal1 >= 4 || countDiagonal2 >= 4;
        } catch (Exception e) {
            throw new NullPointerException("Error in diagonalChecker: " + e.getMessage());
        }
    }


    /**
     * Checks if the game board is full, resulting in a draw.
     *
     * @return True if the board is full, false otherwise.
     */
    public boolean boardChecker() {
        try {
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COL; j++) {
                    if (board[i][j] == ' ') {
                        return false; // Board is not full
                    }
                }
            }
            return true; // Board is full
        } catch (Exception e) {
            throw new NullPointerException("Error in boardChecker: " + e.getMessage());
        }
    }


    /**
     * Switches the current player.
     */
    public void playerSwitcher() {
        if (selectedPlayer == 'X') {
            selectedPlayer = 'O';
        } else {
            selectedPlayer = 'X';
        }
    }


    /**
     * Gets the symbol representing the current player.
     *
     * @return The symbol representing the current player ('X' or 'O').
     */
    public char getSelectedPlayer() {
        return selectedPlayer;
    }

    /**
     * Gets the current state of the game board.
     *
     * @return The current game board.
     */
    public char[][] getBoard() {
        return board;
    }

    /**
     * The purpose of this method is to test the gameLogic.
     * It is utilized in the Junit tester class,
     *
     * @param customBoard sets the custom array game board.
     */
    public void setBoard(char[][] customBoard) {
        this.board = customBoard;
    }
}



