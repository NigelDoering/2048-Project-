// Name: Nigel Doering
// Email: nfdoerin@gmail.com
// cs8b account: cs8bwaat 
// PID: A14445032
// This class is meant to be the middle man between the Game2048 method and
// the Board class. The GameManager class is basically just one important method
// being the play method which controls the actual game.The instance variable
// board is to load a board from the Board class. Then outputBoard is the string
// that is going to be used to create a file to save the board to.

import java.util.*;
import java.io.*;

public class GameManager {
   // Instance variables
   private Board board; // The actual 2048 board
   private String outputBoard; // File to save the board to when exiting

   /*ec*/
   private String outputRecord; // file to save the record file, format: [size] wasdwasdwasdawsd
   StringBuilder history = new StringBuilder(); // a string of commands history
   /*ce*/


   // GameManager Constructor
   // Generate new game
   public GameManager(String outputBoard, int boardSize, Random random) {
      //Inititializing output board to value passed
      this.outputBoard = outputBoard;
      //Initializing board to a new game board
      this.board = new Board(random, boardSize);

   }

   // GameManager Constructor
   // Load a saved game
   public GameManager(String inputBoard, String outputBoard, Random random
         ) throws IOException {
      //Loading a saved board
      this.board = new Board(random, inputBoard);
      //Initializing outputBoard
      this.outputBoard = outputBoard;

   }

   // Main play loop
   // Takes in input from the user to specify moves to execute
   // valid moves are:
   //      w - Move up
   //      s - Move Down
   //      a - Move Left
   //      d - Move Right
   //      q - Quit and Save Board
   //
   //  If an invalid command is received then print the controls
   //  to remind the user of the valid moves.
   //
   //  Once the player decides to quit or the game is over,
   //  save the game board to a file based on the outputBoard
   //  string that was set in the constructor and then return
   //
   //  If the game is over print "Game Over!" to the terminal
   public void play() throws Exception {
      //Initializing a scanner to read user input
      Scanner userInput = new Scanner(System.in);
      //Creating empty string to hold user input
      String input = "";
      //Creating direction var to hold direction to be moved
      Direction direc = null;
      //While game is not over i keep playing
      while (this.board.isGameOver() == false) {
         //Printing controls
         this.printControls();
         //Printing current board
         System.out.println(this.board);
         //Reading user input
         input = userInput.next();
         //Checking if user input is a s w d
         if (input.equals("w") || input.equals("s") || input.equals("d")
               || input.equals("a")) {
            //Converting input to direction
            direc = getDirection(input);
            //Moving board
            this.board.move(direc);
            this.board.addRandomTile();
         }
         //If user inputs q then quit game and save board
         else if (input.equals("q")) {
            this.board.saveBoard(this.outputBoard);
            return;
         }
         //If usr enters any other input reprint controls
         else {
            this.printControls();
         }
      }
      //End of while loop means game is over
      this.board.saveBoard(this.outputBoard);
      System.out.println("Game Over");
   }

   // Print the Controls for the Game
   private void printControls() {
      System.out.println("  Controls:");
      System.out.println("    w - Move Up");
      System.out.println("    s - Move Down");
      System.out.println("    a - Move Left");
      System.out.println("    d - Move Right");
      System.out.println("    q - Quit and Save Board");
      System.out.println();
   }

   //Helper method for the play method. Converts string input to 
   //a direction 
   //@return direction is returned
   //@param takes a string to be converted to a direction
   private static Direction getDirection(String input) {
      // if input is w then return up
      if (input.equals("w")) {
         return Direction.UP;
      }
      // if input is s then return down
      if (input.equals("s")) {
         return Direction.DOWN;
      }
      // if input is d then return right
      if (input.equals("d")) {
         return Direction.RIGHT;
      }
      // if input is a then return left 
      if (input.equals("a")) {
         return Direction.LEFT;
      }
      else {
         return null;
      }
   }
}
