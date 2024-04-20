/**
 * This is the tester for the classes inside the core package.
 * Tests all methods inside Connect4logic and Connect4TextConsole
 * using junit as a unit tester.
 *
 * @author Chris Crum
 * @version 04.20.2024
 */

package test;


import core.Connect4ComputerPlayer;
import core.Connect4Logic;
import org.junit.Test;

import static org.junit.Assert.*;

public class connectTester {

    /**
     * Test case for the initBoard method of the Connect4Logic class.
     * Initializes the board and compares it with an expected empty board.
     */
    @Test
    public void testInitBoard() {
        try {
            Connect4Logic gameLogic = new Connect4Logic();

            // Manually initialize the board to all 'X's
            char[][] expectedBoard = new char[gameLogic.ROWS][gameLogic.COL];
            for (int i = 0; i < gameLogic.ROWS; i++) {
                for (int j = 0; j < gameLogic.COL; j++) {
                    expectedBoard[i][j] = ' ';
                }
            }
            // Call the initBoard method
            gameLogic.initBoard();
            // Retrieve the actual board
            char[][] actualBoard = gameLogic.getBoard();
            // Compare each cell of the actual board with the expected board
            for (int i = 0; i < gameLogic.ROWS; i++) {
                for (int j = 0; j < gameLogic.COL; j++) {
                    assertEquals(expectedBoard[i][j], actualBoard[i][j]);
                }
            }
        } catch (Exception e) {
            System.out.println("Error in testInitBoard, in Junit Test: " + e.getMessage());
        }
    }

    /**
     * Test case for the playerMove method of the Connect4Logic class.
     * Tests various scenarios of valid and invalid player moves.
     */
    @Test
    public void testPlayerMove() {
        try {
            Connect4Logic gameLogic = new Connect4Logic();
            //These should all be valid moves
            assertTrue(gameLogic.playerMove(1));
            assertTrue(gameLogic.playerMove(2));
            assertTrue(gameLogic.playerMove(3));
            assertTrue(gameLogic.playerMove(4));
            assertTrue(gameLogic.playerMove(5));
            assertTrue(gameLogic.playerMove(6));
            assertTrue(gameLogic.playerMove(7));

            //These should all be invalid moves
            assertFalse(gameLogic.playerMove(0));
            assertFalse(gameLogic.playerMove(10));
            assertFalse(gameLogic.playerMove(9999));
            assertFalse(gameLogic.playerMove(-0));
            assertFalse(gameLogic.playerMove(-10));
            assertFalse(gameLogic.playerMove(-1));
            assertFalse(gameLogic.playerMove(77));
        } catch (Exception e) {
            System.out.println("Error in testPlayerMove(), in Junit Test: " + e.getMessage());
        }
    }

    /**
     * Test case for the winChecker method of the Connect4Logic class.
     * Tests if a winning condition is correctly detected.
     */
    @Test
    public void testWinChecker() {
        try {
            Connect4Logic gameLogic = new Connect4Logic();
            assertFalse(gameLogic.winChecker()); // No winner at the start

            // Player X should win this

            // Player's Move
            gameLogic.playerMove(1);
            assertFalse(gameLogic.winChecker());

            // Bot's move
            gameLogic.playerMove(1);
            assertFalse(gameLogic.winChecker());
            // Player's Move
            gameLogic.playerMove(2);
            assertFalse(gameLogic.winChecker());
            // Bot's move
            gameLogic.playerMove(2);
            assertFalse(gameLogic.winChecker());
            // Player's Move
            gameLogic.playerMove(3);
            assertFalse(gameLogic.winChecker());
            // Bot's Move
            gameLogic.playerMove(3);
            assertFalse(gameLogic.winChecker());

            // Player's winning move
            gameLogic.playerMove(4);

            // checks to see if winner
            assertTrue(gameLogic.winChecker());

        } catch (Exception e) {
            System.out.println("Error in testWinChecker, in Junit Test: " + e.getMessage());
        }
    }

    /**
     * Test case for the boardChecker method of the Connect4Logic class.
     * Tests if the board full condition is correctly detected.
     */
    @Test
    public void testBoardChecker() {
        try {
            Connect4Logic gameLogic = new Connect4Logic();
            assertFalse(gameLogic.boardChecker()); // Board is not full at the start
            // Fill up the board
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    gameLogic.playerMove(j + 1);
                    if (j < 6) {
                        assertFalse(gameLogic.boardChecker());
                    }
                }
            }
            assertTrue(gameLogic.boardChecker()); // Board is full
        } catch (Exception e) {
            System.out.println("Error in testBoardChecker, in Junit Test: " + e.getMessage());
        }
    }

    /**
     * Test case for the playerSwitcher method of the Connect4Logic class.
     * Tests if player switching functionality works as expected.
     */
    @Test
    public void testPlayerSwitcher() {
        try {
            Connect4Logic gameLogic = new Connect4Logic();
            assertEquals('X', gameLogic.getSelectedPlayer()); // Initial player is 'X'
            assertNotEquals('O', gameLogic.getSelectedPlayer());

            gameLogic.playerSwitcher();
            assertEquals('O', gameLogic.getSelectedPlayer()); // Player switched to 'O'
            assertNotEquals('X', gameLogic.getSelectedPlayer());

            gameLogic.playerSwitcher();
            assertEquals('X', gameLogic.getSelectedPlayer()); // Player switched back to 'X'
            assertNotEquals('O', gameLogic.getSelectedPlayer());

        } catch (Exception e) {
            System.out.println("Error in testPlayerSwitcher, in Junit Test: " + e.getMessage());
        }
    }

    /**
     * Test case for the diagonalChecker method of the Connect4Logic class.
     * Tests diagonal winning condition for both player 'X' and 'O'.
     */
    @Test
    public void testDiagonalCheckerX() {
        try {
            Connect4Logic gameLogic = new Connect4Logic();
            char[][] board = {
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', 'X', ' '},
                    {' ', ' ', ' ', ' ', 'X', '0', ' '},
                    {' ', ' ', ' ', 'X', '0', '0', ' '},
                    {' ', ' ', 'X', 'O', 'X', '0', ' '},
            };
            gameLogic.setBoard(board);

            assertFalse(gameLogic.diagonalChecker(0, 6));
            assertFalse(gameLogic.diagonalChecker(1, 6));
            assertFalse(gameLogic.diagonalChecker(2, 6));
            assertFalse(gameLogic.diagonalChecker(3, 6));
            assertFalse(gameLogic.diagonalChecker(4, 6));
            assertFalse(gameLogic.diagonalChecker(5, 6));


            assertFalse(gameLogic.diagonalChecker(0, 5));
            assertFalse(gameLogic.diagonalChecker(1, 5));
            assertFalse(gameLogic.diagonalChecker(3, 5));
            assertFalse(gameLogic.diagonalChecker(4, 5));
            assertFalse(gameLogic.diagonalChecker(5, 5));


            assertTrue(gameLogic.diagonalChecker(2, 5));


        } catch (Exception e) {
            System.out.println("Error in testDiagonalCheckerX, in Junit Test: " + e.getMessage());
        }
    }

    /**
     * Test case for the diagonalChecker method of the Connect4Logic class.
     * Tests diagonal winning condition for both player 'X' and 'O'.
     */
    @Test
    public void testDiagonalCheckerO() {
        try {
            Connect4Logic gameLogic = new Connect4Logic();
            char[][] board = {
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', 'O', ' '},
                    {' ', ' ', ' ', ' ', 'O', 'X', ' '},
                    {' ', ' ', ' ', 'O', '0', 'X', ' '},
                    {' ', ' ', 'O', 'X', 'O', 'X', ' '},
            };
            gameLogic.setBoard(board);


            assertFalse(gameLogic.diagonalChecker(0, 6));
            assertFalse(gameLogic.diagonalChecker(1, 6));
            assertFalse(gameLogic.diagonalChecker(2, 6));
            assertFalse(gameLogic.diagonalChecker(3, 6));
            assertFalse(gameLogic.diagonalChecker(4, 6));
            assertFalse(gameLogic.diagonalChecker(5, 6));


            assertFalse(gameLogic.diagonalChecker(0, 5));
            assertFalse(gameLogic.diagonalChecker(1, 5));
            assertFalse(gameLogic.diagonalChecker(3, 5));
            assertFalse(gameLogic.diagonalChecker(4, 5));
            assertFalse(gameLogic.diagonalChecker(5, 5));


            assertTrue(gameLogic.diagonalChecker(2, 5));

        } catch (Exception e) {
            System.out.println("Error in testDiagonalCheckerO, in Junit Test: " + e.getMessage());
        }
    }


    /**
     * Test case for the downChecker method of the Connect4Logic class.
     * Tests vertical winning condition.
     */
    @Test
    public void downChecker() {
        try {
            Connect4Logic gameLogic = new Connect4Logic();
            char[][] board = {
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', 'O', ' '},
                    {' ', ' ', ' ', ' ', 'X', 'O', ' '},
                    {' ', ' ', ' ', 'X', 'X', 'O', ' '},
                    {' ', ' ', 'O', 'O', 'X', 'O', ' '},
            };
            gameLogic.setBoard(board);
            assertTrue(gameLogic.downChecker(2, 5));
            assertFalse(gameLogic.downChecker(3, 4));

            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', 'X', ' '},
                    {' ', ' ', ' ', ' ', 'O', 'X', ' '},
                    {' ', ' ', ' ', 'X', 'O', 'X', ' '},
                    {' ', ' ', 'O', 'O', 'O', 'X', ' '},
            };
            gameLogic.setBoard(board);

            assertTrue(gameLogic.downChecker(2, 5));
            assertFalse(gameLogic.downChecker(3, 4));
        } catch (Exception e) {
            System.out.println("Error in downChecker, in Junit Test: " + e.getMessage());
        }
    }

    /**
     * Test case for the leftAndRightChecker method of the Connect4Logic class.
     * Tests horizontal winning condition.
     */
    @Test
    public void leftAndRightChecker() {
        try {
            Connect4Logic gameLogic = new Connect4Logic();
            char[][] board = {
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', 'X', ' '},
                    {' ', ' ', ' ', ' ', 'X', 'X', ' '},
                    {' ', ' ', ' ', 'X', 'X', 'X', ' '},
                    {' ', ' ', 'O', 'O', 'O', 'O', ' '},
            };
            gameLogic.setBoard(board);

            assertTrue(gameLogic.leftAndRightChecker(5, 2));
            assertTrue(gameLogic.leftAndRightChecker(5, 3));
            assertTrue(gameLogic.leftAndRightChecker(5, 4));
            assertTrue(gameLogic.leftAndRightChecker(5, 5));

            assertFalse(gameLogic.leftAndRightChecker(4, 2));
            assertFalse(gameLogic.leftAndRightChecker(4, 3));
            assertFalse(gameLogic.leftAndRightChecker(4, 4));
            assertFalse(gameLogic.leftAndRightChecker(4, 5));

        } catch (Exception e) {
            System.out.println("Error in leftAndRightChecker, in Junit Test: " + e.getMessage());
        }
    }


    //Tests on connect4ComputerPlayer class


    /**
     * Test case for the getRandomMove method of the Connect4ComputerPlayer class.
     * Tests if a random valid move is generated.
     */
    @Test
    public void testGetRandomMove() {
        try {
            Connect4ComputerPlayer computerPlayer = new Connect4ComputerPlayer('X');
            char[][] board = {
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '}
            };
            int move = computerPlayer.getRandomMove(board);
            assertTrue(move >= 1 && move <= 7);
        } catch (Exception e) {
            System.out.println("Error in testGetRandomMove, in Junit Test: " + e.getMessage());
        }
    }

    /**
     * Test case for the comMove method of the Connect4ComputerPlayer class.
     * Tests various scenarios of computer moves for blocking and winning conditions.
     */
    @Test
    public void testComMove() {
        try {
            Connect4ComputerPlayer computerPlayer = new Connect4ComputerPlayer('O');

            //Easy Clean Board should be random
            char[][] board = {
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', 'X'}
            };
            int move = computerPlayer.comMove(board);
            assertTrue(move >= 1 && move < 8);


            //This should block the player on the left
            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', 'X', 'X', 'X', 'O'}
            };
            assertEquals(3, computerPlayer.comMove(board));

            //Block on the right
            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', 'O', 'X', 'X', 'X', ' '}
            };
            move = computerPlayer.comMove(board);

            assertNotEquals(1, move);
            assertNotEquals(2, move);
            assertNotEquals(3, move);
            assertNotEquals(4, move);
            assertNotEquals(5, move);
            assertNotEquals(6, move);
            assertEquals(7, move);


            //block vertical
            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', 'X', ' ', ' ', ' '},
                    {' ', ' ', ' ', 'X', ' ', ' ', ' '},
                    {' ', ' ', ' ', 'X', 'O', 'X', 'O'}
            };
            assertEquals(4, computerPlayer.comMove(board));

            //Block Diagonal
            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', 'O', ' ', ' '},
                    {' ', ' ', ' ', ' ', 'X', 'O', ' '},
                    {' ', ' ', ' ', 'X', 'X', 'O', 'O'},
                    {' ', 'O', 'X', 'O', 'X', 'X', 'O'}
            };
            assertEquals(6, computerPlayer.comMove(board));

            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', 'O', 'X', 'X', ' ', ' ', ' '},
                    {' ', 'X', 'O', 'O', ' ', ' ', ' '},
                    {'X', 'X', 'O', 'O', ' ', ' ', ' '}
            };
            assertEquals(4, computerPlayer.comMove(board));


            //winning move on left
            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', 'X', 'X', ' ', ' ', ' ', ' '},
                    {' ', 'X', 'X', ' ', 'O', 'O', 'O'}
            };
            assertEquals(4, computerPlayer.findWinningMove(board));

            //winning move on right
            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', 'X', 'X', ' ', ' ', ' ', ' '},
                    {' ', 'X', 'X', 'O', 'O', 'O', ' '}
            };
            assertEquals(7, computerPlayer.findWinningMove(board));

            //winning vertical move
            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', 'O', ' ', ' ', ' '},
                    {' ', 'X', 'X', 'O', ' ', ' ', ' '},
                    {' ', 'X', 'X', 'O', ' ', ' ', ' '}
            };

            assertEquals(4, computerPlayer.findWinningMove(board));


            //winning diagonally
            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', 'X', 'O', 'X', ' ', ' ', ' '},
                    {' ', 'O', 'X', 'O', ' ', ' ', ' '},
                    {'O', 'X', 'O', 'X', ' ', ' ', ' '}
            };
            assertEquals(4, computerPlayer.comMove(board));


            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', 'O', 'O', ' ', ' '},
                    {' ', ' ', ' ', 'X', 'X', 'O', ' '},
                    {' ', ' ', 'O', 'X', 'O', 'X', 'O'}
            };
            assertEquals(4, computerPlayer.comMove(board));

        } catch (Exception e) {
            System.out.println("Error in testComMove, in Junit Test: " + e.getMessage());
        }
    }

    /**
     * Test case for the isValidMove method of the Connect4ComputerPlayer class.
     * Tests if a move is valid on the current board state.
     */
    @Test
    public void testIsValidMove() {
        try {
            Connect4ComputerPlayer computerPlayer = new Connect4ComputerPlayer('O');
            char[][] board = {
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '}
            };
            assertTrue(computerPlayer.isValidMove(board, 0));
            assertTrue(computerPlayer.isValidMove(board, 1));
            assertTrue(computerPlayer.isValidMove(board, 6));
            assertTrue(computerPlayer.isValidMove(board, 3));


            assertFalse(computerPlayer.isValidMove(board, -1));
            assertFalse(computerPlayer.isValidMove(board, 7));
            assertFalse(computerPlayer.isValidMove(board, 100));


            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', 'O'},
                    {' ', ' ', ' ', ' ', ' ', ' ', 'O'},
                    {' ', ' ', ' ', ' ', ' ', ' ', 'O'},
                    {' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                    {' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                    {' ', ' ', ' ', ' ', ' ', ' ', 'X'}
            };

            assertFalse(computerPlayer.isValidMove(board, 6));
            assertFalse(computerPlayer.isValidMove(board, 7));
            assertTrue(computerPlayer.isValidMove(board, 1));
            assertTrue(computerPlayer.isValidMove(board, 3));

        } catch (Exception e) {
            System.out.println("Error in testIsValidMove, in Junit Test: " + e.getMessage());
        }
    }


    /**
     * Test case for the findWinningMove method of the Connect4ComputerPlayer class.
     * Tests if the computer can find a winning move.
     */
    @Test
    public void testFindWinningMoveLeftRight() {
        try {
            Connect4ComputerPlayer computerPlayer = new Connect4ComputerPlayer('O');

            //Left and right checker Horizontal
            char[][] board = {
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', 'X', 'O', 'O', 'O', ' ', ' '}
            };
            assertEquals(6, computerPlayer.findWinningMove(board));
            assertNotEquals(-1, computerPlayer.findWinningMove(board));


            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', 'O', 'O', 'O', 'X', ' '}
            };
            assertEquals(2, computerPlayer.findWinningMove(board));
            assertNotEquals(-1, computerPlayer.findWinningMove(board));


            //vertical checker for winning
            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', 'O', ' ', ' ', ' ', ' '},
                    {' ', ' ', 'O', 'X', ' ', ' ', ' '},
                    {' ', 'X', 'O', 'O', 'X', ' ', ' '}
            };
            assertEquals(3, computerPlayer.findWinningMove(board));
            assertNotEquals(-1, computerPlayer.findWinningMove(board));

            //Diagonal Check
            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', 'O', ' ', ' '},
                    {' ', ' ', ' ', 'O', 'X', ' ', ' '},
                    {' ', 'X', 'O', 'O', 'X', ' ', ' '}
            };
            assertEquals(-1, computerPlayer.findWinningMove(board));
            assertNotEquals(6, computerPlayer.findWinningMove(board));


            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', 'O', 'O', ' '},
                    {' ', ' ', ' ', 'O', 'X', 'O', ' '},
                    {' ', 'X', 'O', 'O', 'X', 'X', ' '}
            };
            assertEquals(6, computerPlayer.findWinningMove(board));


            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', 'O', ' ', ' ', ' ', ' '},
                    {' ', ' ', 'X', 'O', 'X', ' ', ' '},
                    {' ', ' ', 'X', 'O', 'O', 'X', ' '}
            };
            assertEquals(-1, computerPlayer.findWinningMove(board));
            assertNotEquals(2, computerPlayer.findWinningMove(board));

            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', 'O', 'O', ' ', ' ', ' ', ' '},
                    {' ', 'X', 'X', 'O', 'X', ' ', ' '},
                    {' ', 'O', 'X', 'O', 'O', 'X', ' '}
            };
            assertEquals(2, computerPlayer.findWinningMove(board));
        } catch (Exception e) {
            System.out.println("Error in testFindWinningMoveLeftRight, in Junit Test: " + e.getMessage());
        }
    }

    /**
     * Test case for the findBlockingMoves method of the Connect4ComputerPlayer class.
     * Tests if the computer can find a blocking move.
     */
    @Test
    public void testFindBlockingMoves() {
        try {
            Connect4ComputerPlayer computerPlayer = new Connect4ComputerPlayer('O');

            //blank
            char[][] board = {
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '}
            };
            assertEquals(-6, computerPlayer.findBlockingMove(board));


            //left right
            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', 'X', 'X', 'X', ' ', 'O'}
            };
            assertEquals(5, computerPlayer.findBlockingMove(board));
            assertNotEquals(-6, computerPlayer.findBlockingMove(board));


            //left right
            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', 'X', 'X', 'X', 'O'}
            };
            assertEquals(2, computerPlayer.findBlockingMove(board));
            assertNotEquals(3, computerPlayer.findBlockingMove(board));


            //Could give an error
            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {'X', 'X', 'X', 'O', 'O', 'O', 'X'}
            };
            assertEquals(-6, computerPlayer.findBlockingMove(board));
            assertNotEquals(0, computerPlayer.findBlockingMove(board));


            //Vertical
            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', 'X', ' ', ' '},
                    {' ', ' ', ' ', ' ', 'X', ' ', ' '},
                    {' ', 'O', 'X', 'X', 'X', 'O', 'O'}
            };
            assertEquals(4, computerPlayer.findBlockingMove(board));
            assertNotEquals(-1, computerPlayer.findBlockingMove(board));
            assertNotEquals(3, computerPlayer.findBlockingMove(board));


            //Diagonal
            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', 'O', ' ', ' '},
                    {' ', ' ', ' ', ' ', 'X', 'O', ' '},
                    {' ', ' ', ' ', 'X', 'X', 'O', 'O'},
                    {' ', 'O', 'X', 'O', 'X', 'X', 'O'}
            };
            assertEquals(5, computerPlayer.findBlockingMove(board));
            assertNotEquals(3, computerPlayer.findBlockingMove(board));
            assertNotEquals(-1, computerPlayer.findBlockingMove(board));


            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', 'O', ' ', ' ', ' ', 'O', ' '},
                    {' ', 'O', 'X', 'O', 'X', 'O', ' '},
                    {' ', 'X', 'O', 'X', 'O', 'X', 'O'},
                    {'X', 'O', 'X', 'X', 'X', 'O', 'O'}
            };
            assertEquals(3, computerPlayer.findBlockingMove(board));
            assertNotEquals(-1, computerPlayer.findBlockingMove(board));

            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', 'O', 'X', ' ', ' ', ' ', ' '},
                    {' ', 'X', 'O', ' ', ' ', ' ', ' '},
                    {'X', 'X', 'O', ' ', ' ', ' ', ' '}
            };
            assertEquals(-6, computerPlayer.findBlockingMove(board));


            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', 'O', 'X', ' ', ' ', ' ', ' '},
                    {' ', 'X', 'O', 'O', ' ', ' ', ' '},
                    {'X', 'X', 'O', 'O', ' ', ' ', ' '}
            };
            assertEquals(-6, computerPlayer.findBlockingMove(board));

            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', 'O', 'X', 'X', ' ', ' ', ' '},
                    {' ', 'X', 'O', 'O', ' ', ' ', ' '},
                    {'X', 'X', 'O', 'O', ' ', ' ', ' '}
            };
            assertEquals(3, computerPlayer.findBlockingMove(board));


            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', 'X', 'O', 'X', 'X', ' ', ' '},
                    {' ', 'O', 'O', 'O', 'X', 'X', 'O'},
                    {'X', 'X', 'O', 'O', 'O', 'O', 'X'}
            };
            assertEquals(3, computerPlayer.findBlockingMove(board));

            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', 'X', ' ', ' ', ' ', ' '},
                    {' ', 'O', 'O', 'X', ' ', ' ', ' '},
                    {' ', 'X', 'O', 'O', 'X', 'X', 'O'}
            };
            assertEquals(-6, computerPlayer.findBlockingMove(board));

            board = new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', 'X', 'X', ' ', ' ', ' ', ' '},
                    {' ', 'O', 'O', 'X', ' ', ' ', ' '},
                    {' ', 'X', 'O', 'O', 'X', 'X', 'O'}
            };
            assertEquals(1, computerPlayer.findBlockingMove(board));

        } catch (Exception e) {
            System.out.println("Error in testFindBlockingMoves, in Junit Test: " + e.getMessage());
        }
    }

    /**
     * Test case for the getSymbol method of the Connect4ComputerPlayer class.
     * Tests if the correct symbol is returned.
     */
    @Test
    public void testGetSymbol() {
        try {
            Connect4ComputerPlayer computerPlayer = new Connect4ComputerPlayer('X');
            assertEquals('X', computerPlayer.getSymbol());
            assertNotEquals('O', computerPlayer.getSymbol());

            Connect4ComputerPlayer computerPlayer2 = new Connect4ComputerPlayer('O');
            assertEquals('O', computerPlayer2.getSymbol());
            assertNotEquals('X', computerPlayer2.getSymbol());

        } catch (Exception e) {
            System.out.println("Error in testGetSymbol, in Junit Test: " + e.getMessage());
        }
    }


    /**
     * Test case for exception handling in the comMove method of the Connect4ComputerPlayer class.
     * Tests if an IllegalArgumentException is thrown when the board is null.
     */
    @Test
    public void tryCatchTestingForBot() {
        try {
            Connect4ComputerPlayer computerPlayer = new Connect4ComputerPlayer('O');

            // comMove Method
            Exception comMoveExc = assertThrows(IllegalArgumentException.class, () -> {
                computerPlayer.comMove(null);
            });
            // Verify the exception message
            assertEquals("Board cannot be null", comMoveExc.getMessage());


            //GetRandomMove checker
            IllegalArgumentException getRandomexc = assertThrows(IllegalArgumentException.class, () -> {
                computerPlayer.getRandomMove(null);
            });

            assertEquals("Board cannot be null", getRandomexc.getMessage());


            //isValid throws
            assertThrows(IllegalArgumentException.class, () -> {
                computerPlayer.isValidMove(null, 0);
            });


        } catch (Exception e) {
            System.out.println("Error in tryCatchTestingForBot, in Junit Test: " + e.getMessage());
        }
    }


    /**
     * Tests exception handling for various methods in the Connect4Logic class.
     */
    @Test
    public void exceptionTestingGameLogic() {
        try {
            Connect4Logic gameLogic = new Connect4Logic();
            //Checks downChecker Catch
            gameLogic.setBoard(null);
            NullPointerException exception = assertThrows(NullPointerException.class, () -> {
                // Call the method with valid row and column indices
                gameLogic.downChecker(0, 0); // Assuming valid indices
            });

            //Checks leftAndRightChecker Catch
            NullPointerException exception2 = assertThrows(NullPointerException.class, () -> {
                // Call the method with valid row and column indices
                gameLogic.leftAndRightChecker(0, 0); // Assuming valid indices
            });

            NullPointerException exception3 = assertThrows(NullPointerException.class, () -> {
                // Call the method with valid row and column indices
                gameLogic.diagonalChecker(0, 0); // Assuming valid indices
            });

            // Call the method with valid row and column indices
            // Assuming valid indices
            NullPointerException exception4 = assertThrows(NullPointerException.class, gameLogic::boardChecker);

        } catch (Exception e) {
            System.out.println("Error in exceptionTestingGameLogic, in Junit Test: " + e.getMessage());
        }
    }


    // Tests beyond this point is to hit really weird cases. It kinda helped, but really not needed.

    /**
     * Tests the getRandomMove method.
     * Ensures that a valid random move is returned.
     */
    @Test
    public void testGetRandomMove2() {
        // Create an instance of the Connect4ComputerPlayer
        Connect4ComputerPlayer player = new Connect4ComputerPlayer('O');

        // Define a sample board with at least one valid move
        char[][] board = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X', 'O', 'X'}
                // At least one empty space
        };

        // Call the getRandomMove method
        int column = player.getRandomMove(board);

        // Check if the returned column is valid
        assertTrue(column >= 1 && column <= 7);// Column should be between 1 and 7
        assertTrue(player.isValidMove(board, column - 1)); // Check if the move is valid on the board
    }


    /**
     * Tests the getRandomMove method when there are no valid moves.
     * Ensures that -1 is returned when there are no valid moves.
     */

    @Test
    public void testGetRandomMove3() {
        // Create an instance of the Connect4ComputerPlayer
        Connect4ComputerPlayer player = new Connect4ComputerPlayer('O');

        // Define a sample board with no valid moves
        char[][] board = {
                {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X', 'O', 'X'}
        };

        // Call the getRandomMove method
        int column = player.getRandomMove(board);
        assertEquals(-1, column);
    }

    /**
     * Tests the isValidMove method.
     * Covers different scenarios including out of bounds, filled columns, and empty columns.
     */
    @Test
    public void testIsValidMove2() {
        // Create an instance of the Connect4ComputerPlayer
        Connect4ComputerPlayer player = new Connect4ComputerPlayer('O');

        // Define a sample board
        char[][] board = {
                {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X', 'O', 'X'}
        };

        // Test when the column is out of bounds
        assertFalse(player.isValidMove(board, -1)); // Column too small
        assertFalse(player.isValidMove(board, 7)); // Column too large

        // Test when the column is filled
        assertFalse(player.isValidMove(board, 0)); // First column is completely filled

        // Test when the column has at least one empty slot
        assertFalse(player.isValidMove(board, 6)); // Last column has at least one empty slot
    }

    /**
     * Tests the findBlockingMove method for 'X' symbol.
     * Ensures that the correct blocking move is returned for 'X'.
     */
    @Test
    public void testFindBlockingMoveX() {
        // Create an instance of the Connect4ComputerPlayer with 'X' symbol
        Connect4ComputerPlayer player = new Connect4ComputerPlayer('X');

        char[][] board = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'O', ' ', ' ', ' ', ' ', ' '},
                {'O', 'O', 'O', ' ', ' ', ' ', ' '}
        };

        // Test when there is a potential winning move for the opponent
        assertEquals(3, player.findBlockingMove(board)); // Expected blocking move at column 3
    }

    /**
     * Tests the findWinningMove method for 'X' symbol.
     * Ensures that the correct winning move is returned for 'X'.
     */
    @Test
    public void testFindWinningMoveX() {
        Connect4ComputerPlayer player = new Connect4ComputerPlayer('X');
        char[][] board = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'O', ' ', ' ', ' ', ' ', ' '},
                {'O', 'O', 'O', ' ', 'X', 'X', 'X'}
        };
        assertEquals(3, player.findBlockingMove(board));
    }

    /**
     * Tests the findBlockingMove method.
     * Ensures that the correct blocking move is returned.
     */
    @Test
    public void testFindBlockingMove() {
        // Create an instance of the Connect4ComputerPlayer with 'X' symbol
        Connect4ComputerPlayer player = new Connect4ComputerPlayer('X');

        // Define a sample board where the condition inside the nested loops is satisfied
        char[][] board = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'O', ' ', ' ', ' ', ' ', ' '},
                {' ', 'O', ' ', ' ', ' ', ' ', ' '},
                {'O', 'O', 'X', ' ', ' ', ' ', ' '}
        };

        // Test when the condition inside the nested loops is satisfied
        assertEquals(1, player.findBlockingMove(board)); // Expected blocking move at column 3
    }


}




