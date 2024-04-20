/**
 * Connect4ComputerPlayer class provides the core logic for the Connect 4 game.
 * The Class provides logic for the bot to play against the player,
 * checks for winning moves and blocking moves, or moves randomly.
 * Not extensive, but pretty good.
 *
 * @author Chris Crum
 * @version 03.31.2024
 */


package core;

import java.util.Random;


/**
 * playAgainstComputer()
 * Represents a computer player for the Connect4 game.
 */
public class Connect4ComputerPlayer {

    private final char symbol; // Symbol representing the computer player on the board

    /**
     * Constructs a Connect4ComputerPlayer with the specified symbol.
     *
     * @param symbol The symbol representing the computer player.
     */
    public Connect4ComputerPlayer(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Makes a move by the computer player.
     *
     * @param board The current state of the game board.
     * @return The column where the computer player makes its move.
     */
    public int comMove(char[][] board) {
        if (board == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }
        // Check if there is a winning move available
        int winningMove = findWinningMove(board);
        if (winningMove != -1) {
            return winningMove; // If there's a winning move, play it
        }
        // Check if there is a winning move available for the opponent (human player)
        int blockingMove = findBlockingMove(board);
        if (blockingMove != -6) {
            return blockingMove + 1; // If there's a blocking move, play it
        }
        return getRandomMove(board);
    }

    /**
     * Generates a random valid move for the computer player.
     *
     * @param board The current state of the game board.
     * @return The column where the random valid move is to be made, or -1 if an error occurs.
     */
    public int getRandomMove(char[][] board) {
        if (board == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }
        Random random = new Random();
        int maxAttempts = 100; // Limit the maximum number of attempts
        int attempts = 0;
        while (attempts < maxAttempts) {
            int column = random.nextInt(board[0].length) + 1; // Generate a random number between 1 and 7
            if (isValidMove(board, column - 1)) {
                return column;
            }
            attempts++;
        }
        // If no valid move found within the maximum attempts, return -1
        return -1;
    }


    /**
     * Finds a winning move for the computer player.
     * Checks Horizontally, Vertically, and diagonally.
     * Checks for three not for 2 then 1, that is ok. The game would be too hard to win.
     * Diagonal wins must be top bottom. Middle is not checked because it would be difficult.
     *
     * @param board The current state of the game board.
     * @return The column where the winning move should be made, or -1 if no winning move is found.
     */
    public int findWinningMove(char[][] board) {
        int columns = board[0].length;
        int rows = board.length;
        // Check for potential winning moves horizontally
        for (int row = 0; row < rows; row++) {
            // Check from left to right
            for (int col = 0; col < columns - 3; col++) {
                if (board[row][col] == symbol &&
                        board[row][col + 1] == symbol &&
                        board[row][col + 2] == symbol &&
                        board[row][col + 3] == ' ') {
                    return col + 4; // Return the column to complete the sequence
                }
            }
            // Check from right to left
            for (int col = columns - 1; col >= 3; col--) {
                if (board[row][col] == symbol &&
                        board[row][col - 1] == symbol &&
                        board[row][col - 2] == symbol &&
                        board[row][col - 3] == ' ') {
                    return col - 2; // Return the column to complete the sequence
                }
            }
        }
        // Check for winning move vertically
        for (int col = 0; col < columns; col++) {
            for (int row = rows - 1; row >= 3; row--) {
                if (board[row][col] == symbol &&
                        board[row - 1][col] == symbol &&
                        board[row - 2][col] == symbol &&
                        board[row - 3][col] == ' ') {
                    if (row == rows - 1 || board[row + 1][col] != ' ') {
                        return col + 1; // Return the column to complete the sequence
                    }
                }
            }
        }
        // Check for potential winning moves diagonally (from top-left to bottom-right) for the opponent
        for (int row = rows - 1; row >= 3; row--) {
            for (int col = 0; col < columns - 3; col++) {
                if (board[row][col] == symbol &&
                        board[row - 1][col + 1] == symbol &&
                        board[row - 2][col + 2] == symbol &&
                        board[row - 3][col + 3] == ' ') {
                    if (row == rows - 1 || board[row + 1][col + 1] != ' ') {
                        if (board[row - 2][col + 3] != ' ') {
                            return col + 4; // Return the column to block the opponent's winning move
                        }
                    }
                }
            }
        }

        // Check for potential winning moves diagonally (from top-right to bottom-left) for the opponent
        for (int row = rows - 1; row >= 3; row--) {
            for (int col = columns - 1; col >= 3; col--) {
                if (board[row][col] == symbol &&
                        board[row - 1][col - 1] == symbol &&
                        board[row - 2][col - 2] == symbol &&
                        board[row - 3][col - 3] == ' ') {
                    if (row == rows - 1 || board[row + 1][col - 1] != ' ') {
                        if (board[row - 2][col - 3] != ' ') {
                            return col - 2; // Return the column to block the opponent's winning move
                        }
                    }
                }
            }
        }
        return -1; // No winning move found
    }

    /**
     * Finds a blocking move to prevent the opponent from winning.
     * Similar to winning moves. Imperfect on purpose because it would be way
     * too difficult to win.
     *
     * @param board The current state of the game board.
     * @return The column where the blocking move should be made, or -1 if no blocking move is found.
     */
    public int findBlockingMove(char[][] board) {
        char opponentSymbol = (symbol == 'X') ? 'O' : 'X'; // Determine opponent's symbol
        int columns = board[0].length;
        int rows = board.length;
        // Check for potential winning moves horizontally for the opponent
        for (int row = 0; row < rows; row++) {
            // Check from left to right
            for (int col = 0; col < columns - 3; col++) {
                if (board[row][col] == opponentSymbol &&
                        board[row][col + 1] == opponentSymbol &&
                        board[row][col + 2] == opponentSymbol &&
                        board[row][col + 3] == ' ') {
                    return col + 3; // Return the column to block the opponent's winning move
                } else if (board[row][col] == opponentSymbol &&
                        board[row][col + 1] == opponentSymbol &&
                        board[row][col + 2] == opponentSymbol &&
                        col != 0 && board[row][col - 1] == ' ') {
                    return col - 1;
                }
            }
        }

        for (int col = 0; col < columns; col++) { // Iterate over each column
            for (int row = rows - 1; row >= 3; row--) { // Iterate over each row (from the bottom up to the 4th row)
                // Check if the current position and the previous three positions in the same column contain the opponent's symbol
                if (board[row][col] == opponentSymbol &&
                        board[row - 1][col] == opponentSymbol &&
                        board[row - 2][col] == opponentSymbol &&
                        board[row - 3][col] == ' ') { // Check if the position above the sequence is empty
                    // Check if the current row is the bottom row or if there's a token above the sequence
                    if (row == rows - 1 || board[row + 1][col] != ' ') {
                        return col; // Return the column to block the opponent's winning move
                    }
                }
            }
        }
        // Check for potential winning moves diagonally (from top-left to bottom-right) for the opponent
        for (int row = rows - 1; row >= 3; row--) {
            for (int col = 0; col < columns - 3; col++) {
                if (board[row][col] == opponentSymbol &&
                        board[row - 1][col + 1] == opponentSymbol &&
                        board[row - 2][col + 2] == opponentSymbol &&
                        board[row - 3][col + 3] == ' ') {
                    if (row == rows - 1 || board[row + 1][col + 1] != ' ') {
                        if (board[row - 2][col + 3] != ' ') {
                            return col + 3; // Return the column to block the opponent's winning move
                        }

                    }
                }
            }
        }
        // Check for potential winning moves diagonally (from top-right to bottom-left) for the opponent
        for (int row = rows - 1; row >= 3; row--) {
            for (int col = columns - 1; col >= 3; col--) {
                if (board[row][col] == opponentSymbol &&
                        board[row - 1][col - 1] == opponentSymbol &&
                        board[row - 2][col - 2] == opponentSymbol &&
                        board[row - 3][col - 3] == ' ') {
                    if (row == rows - 1 || board[row + 1][col - 1] != ' ') {

                        if (board[row - 2][col - 3] != ' ') {
                            return col - 3; // Return the column to block the opponent's winning move
                        }
                    }
                }
            }
        }
        return -6; // No blocking move found
    }


    /**
     * Checks if a move is valid in the given column.
     *
     * @param board  The current state of the game board.
     * @param column The column where the move is to be made.
     * @return True if the move is valid, false otherwise.
     */
    public boolean isValidMove(char[][] board, int column) {
        // Check if the board is null
        if (board == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }
        // Check if the column is within the bounds of the board
        if (column < 0 || column >= board[0].length) {
            return false;
        }
        // Check if the top row of the column is empty
        if (board[0][column] != ' ') {
            return false; // Column is already filled
        }
        // Check if there's at least one empty slot in the column
        for (int i = 0; i < board.length; i++) {
            if (board[i][column] == ' ') {
                return true;
            }
        }
        return false; // Column is completely filled and somehow got past the other statements
    }

    /**
     * Gets the symbol representing the computer player.
     *
     * @return The symbol representing the computer player.
     */
    public char getSymbol() {
        return symbol;
    }


}
