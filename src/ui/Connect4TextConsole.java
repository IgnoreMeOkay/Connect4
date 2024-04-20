/**
 * Connect4TextConsole class provides a text-based console interface for playing Connect4 game.
 * Basically it is the ui for the game. Requires Connect4Logic.
 * This class handles game initialization, user inputs, and game display.
 *
 * @author 
 * @version 03.24.2024
 */

package ui;

import core.Connect4ComputerPlayer;
import core.Connect4Logic;

import java.util.Scanner;


/**
 * The Connect4TextConsole class provides a text-based console interface for playing Connect4 game.
 * It manages game initialization, user inputs, and game display.
 */
public class Connect4TextConsole {
    private final Connect4Logic myLogic;  //Instance of Connect4Logic to manage game logic
    public Scanner scanner; //// Scanner object to read user input


    /**
     * Constructor for Connect4TextConsole initializes the game logic and scanner.
     */
    public Connect4TextConsole() {
        myLogic = new Connect4Logic();
        scanner = new Scanner(System.in);
    }


    /**
     * Starts the Connect 4 game by displaying the
     * start menu and initiating game play.
     *
     */
    public void startGame() {
        try {
            System.out.println("This is a Connect 4 Start Menu");
            System.out.println();

            System.out.println("Start the game? Type yes or No");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("yes") || input.equals("no")) {
                if (input.equals("yes")) {
                    System.out.println("Do you want to play against a bot or another player?");
                    System.out.println("Type 'player' for a human opponent and 'bot' for a computer.");
                    String inputTwo = scanner.nextLine().trim().toLowerCase();

                    if (inputTwo.equals("player")) {
                        System.out.println("Okay starting game, Have fun!");
                        playAgainstHuman();
                    } else if (inputTwo.equals("bot")) {
                        System.out.println("Start the game against a bot");
                        playAgainstComputer();
                    } else {
                        System.out.println("This is not a valid input. You are supposed to type 'player' or 'bot'");
                        startGame();
                    }
                } else {
                    System.out.println("okay, maybe next time");
                    System.out.println();
                    startGame();
                }
            } else {
                System.out.println(input + " is not a valid input. try again");
                startGame();
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Initiates the game play loop, allowing players to take turns until a
     * win or draw occurs.
     * */
    private void playAgainstHuman() {
        try {
            while (true) {
                display();
                int column = getPlayerMove();

                if (!myLogic.playerMove(column)) {
                    System.out.println("Column is full, please choose another column.");
                    continue;
                }
                if (myLogic.winChecker()) {
                    display();
                    System.out.println("Player " + myLogic.getSelectedPlayer() + " wins!");
                    break;
                }
                if (myLogic.boardChecker()) {
                    display();
                    System.out.println("It's a draw!");
                    break;
                }
                myLogic.playerSwitcher();
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }


    private void playAgainstComputer() {
        try {
            Connect4ComputerPlayer computerPlayer = new Connect4ComputerPlayer('O');

            while (true) {
                display();

                if (myLogic.getSelectedPlayer() == 'X') {
                    int column = getPlayerMove();
                    if (!myLogic.playerMove(column)) {
                        System.out.println("Column is full, please choose another column.");
                        continue;
                    }
                } else {
                    int column = computerPlayer.comMove(myLogic.getBoard());
                    myLogic.playerMove(column);
                    System.out.println("Computer chose column " + column);
                }

                if (myLogic.winChecker()) {
                    display();
                    if (myLogic.getSelectedPlayer() == 'X') {
                        System.out.println("Player X wins!");
                    } else {
                        System.out.println("Computer wins!");
                    }
                    break;
                }

                if (myLogic.boardChecker()) {
                    display();
                    System.out.println("It's a draw!");
                    break;
                }

                myLogic.playerSwitcher();
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }


    /**
     * Displays the current state of the game board.
     */
    private void display() {
        try {
            char[][] board = myLogic.getBoard();
            System.out.println("  1   2   3   4   5   6   7");

            for (char[] chars : board) {
                for (int j = 0; j < chars.length; j++) {
                    if (chars[j] == ' ') {
                        System.out.print("|   ");
                    } else {
                        System.out.print("| " + chars[j] + " ");
                    }
                }
                System.out.println("|");
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Gets the column number from the player for their move.
     * @return The column number chosen by the player.
     */
    private int getPlayerMove() {
        int column;
        while (true) {
            System.out.println("Player " + myLogic.getSelectedPlayer() + ", enter column number (1-7):");
            try {
                column = Integer.parseInt(scanner.nextLine());
                if (column < 1 || column > 7) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 7.");
            }
        }
        return column;
    }


    /**
     * Main method to start the Connect 4 game.
     * @param args Command-line arguments. args is not used.
     *
     */
    public static void main(String[] args) {
        Connect4TextConsole myGame = new Connect4TextConsole();
        myGame.startGame();
    }


    //Testing code

    // Getter method for the Scanner object
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
