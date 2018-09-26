/* File and Class Headers
 * Name: Nigel Doering
 * Email: nfdoerin@ucsd.edu
 * PID: A14445032
 * cs8b account: cs8bwaat
 * This file is for psa6 which creates a GUI for the previous psa3. This is for
 * the class CSE8B.
 *
 * This class overrides the start method in order to construct a GUI that reads
 * user input in order to play the game 2048. It uses various instance variables
 * to keep track of the objects being created within the GUI.
 */
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.util.*;

import java.io.*;
public class Gui2048 extends Application
{
   private String outputBoard; // The filename for where to save the Board
   private Board board; // The 2048 Game Board

   private GridPane pane; // pane to put the rectangles and text in
   private Rectangle[][] rectGrid; // to keep track of the rectangles
   private Text[][] textGrid; // keep track of the text in the pane
   private Text scoreTxt; //Keep track of the score
   private Scene scene; //The scene that holds the pane

   /** Add your own Instance Variables here */

   /**
    * This method overrides start in order to play the GUI
    * @param primaryStage is the stage to put the scene and pane in
    */
   @Override
      public void start(Stage primaryStage)
      {
         // Process Arguments and Initialize the Game Board
         processArgs(getParameters().getRaw().toArray(new String[0]));
         //
         rectGrid = new Rectangle[board.GRID_SIZE][board.GRID_SIZE];
         textGrid = new Text[board.GRID_SIZE][board.GRID_SIZE];

         // Create the pane that will hold all of the visual objects
         pane = new GridPane();
         pane.setAlignment(Pos.CENTER);
         pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
         pane.setStyle("-fx-background-color: rgb(187, 173, 160)");
         // Set the spacing between the Tiles
         pane.setHgap(15); 
         pane.setVgap(15);

         //Creating a scene that will hold the rectangles
         scene = new Scene(pane);
         primaryStage.setTitle("Gui2048");
         primaryStage.setScene(scene); 
         scene.setOnKeyPressed(new KeyHandler());
         // Adding the name of the the game to the top left
         Text title = new Text();
         title.setText("2048");
         title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 
                  FontPosture.ITALIC, 30));
         title.setFill(Color.BLACK);
         //Converting score to string
         String scoreTemp = new String("Score: " + Integer.toString(board.getScore()));
         //Adding the score to the top right
         scoreTxt = new Text();
         scoreTxt.setText(scoreTemp);
         scoreTxt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
         scoreTxt.setFill(Color.BLACK);
         // Creating string to hold the score
         // Adding the title and score to the pane
         pane.add(title, 0, 0, 2, 1);
         pane.add(scoreTxt, board.getGrid().length/2, 0, board.getGrid().length/2, 1);
         //Setting the alignment
         pane.setHalignment(title, HPos.CENTER);
         pane.setHalignment(scoreTxt, HPos.CENTER);
         //initializing the board
         this.initializeBoard();
         //Showing the stage
         primaryStage.show();
      }

   /** Add your own Instance Methods Here */

   /**
    * Helper method to choose the color of the rectangle
    * @return color
    * @param value the value of the element
    */
   public static Color updateTile(int value) {
      //if value is 0 then color is COLOR_2
      if (value == 0) {
         return Constants2048.COLOR_EMPTY;
      }
      //if value is 2 return the 2 color
      else if (value == 2) {
         return Constants2048.COLOR_2;
      }
      //repeating process up to 2048
      else if (value == 4) {
         return Constants2048.COLOR_4;
      }
      else if (value == 8) {
         return Constants2048.COLOR_8;
      }
      else if (value == 16) {
         return Constants2048.COLOR_16;
      }
      else if (value == 32) {
         return Constants2048.COLOR_32;
      }
      else if (value == 64) {
         return Constants2048.COLOR_64;
      }
      else if (value == 128) {
         return Constants2048.COLOR_128;
      }
      else if (value == 256) {
         return Constants2048.COLOR_256;
      }
      else if (value == 512) {
         return Constants2048.COLOR_512;
      }
      else if (value == 1024) {
         return Constants2048.COLOR_1024;
      }
      //else the value is greater or equal to 2048 will be same color
      else {
         return Constants2048.COLOR_2048;
      }
   }

   /**
    * Helper method to update the text size 
    * @param value is the value of the tile
    * @return int is the size of text 
    */
   public static int updateText(int value) {
      //if the tile is 64 or under then text size is 55
      if (value <= 64) {
         return Constants2048.TEXT_SIZE_LOW;
      }
      //else if the tile is under 512 then return 45
      else if (value <= 512) {
         return Constants2048.TEXT_SIZE_MID;
      }
      //else return 35 for numbers bigger
      else {
         return Constants2048.TEXT_SIZE_HIGH;
      }
   }

   /**
    * Helper method to initialize the board 
    * @param pane is the GridPane to be updated
    */
   public void initializeBoard() {
      //Initializing the board grids
      int[][] grid = board.getGrid();
      //Looping through the board's grid 
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            //Creating new rectangle for each grid element
            Rectangle rect = new Rectangle();
            rect.setWidth(Constants2048.TILE_WIDTH);
            rect.setHeight(Constants2048.TILE_WIDTH);
            // Color tileColor = new Color(187, 173, 160);
            rect.setFill(updateTile(grid[i][j]));
            //Creating a string to hold the value of each grid element
            String temp = new String(Integer.toString(grid[i][j]));
            //if grid element is zero initialize to null
            if (grid[i][j] == 0) {
               temp = null;
            }
            //Creating new text element for each grid element
            Text text = new Text();
            text.setText(temp);
            text.setFont(Font.font("Times New Roman", FontWeight.BOLD,
                     updateText(grid[i][j])));
            text.setFill(Color.WHITE);
            //Adding text and rectangle to pane
            pane.add(rect, j,i+1);
            pane.add(text, j,i+1);
            //Setting the Halignment
            pane.setHalignment(text, HPos.CENTER);
            pane.setHalignment(rect, HPos.CENTER);
            //Adding text and rectangle objects to instance variables
            rectGrid[i][j] = rect;
            textGrid[i][j] = text;

         }
      }
   }

   private class KeyHandler implements EventHandler<KeyEvent> {
      /**
       * Method to override the handle method to dicate what to do with user
       * input
       * @param e is the key event to handle
       */
      @Override 
         public void handle(KeyEvent e) {
            //if s is input save game
            if (e.getCode() == KeyCode.S) {
               try {
                  board.saveBoard(outputBoard);
                  //Printing out board is saving
                  System.out.println("Saving Board to " + outputBoard);
               }
               catch (IOException c) {
                  System.out.println("Saving board threw an error");
               }
            }

            //Checking what key was pressed
            else if (e.getCode() == KeyCode.UP) {
               //Calling method to check if the board can move
               this.checkAndMove(Direction.UP);
               this.updateGUI();
               this.gameOverPrint();
            }
            //else if key is DOWN, repeating same steps of if UP
            else if (e.getCode() == KeyCode.DOWN) {
               this.checkAndMove(Direction.DOWN);
               this.updateGUI();
               this.gameOverPrint();
            }
            else if (e.getCode() == KeyCode.LEFT) {
               this.checkAndMove(Direction.LEFT);
               this.updateGUI();
               this.gameOverPrint();
            }
            else if (e.getCode() == KeyCode.RIGHT) {
               this.checkAndMove(Direction.RIGHT);
               this.updateGUI();
               this.gameOverPrint();
            }
            else {
               return;
            }
         }

      /**
       * Helper method to check if board can move, then move
       * @param direction is the direction to check
       */
      public void checkAndMove(Direction direction) {
         //Checking if the board can move that direction
         if (board.canMove(direction)) {
            //Moving the board
            board.move(direction);
            board.addRandomTile();
            //Printing out board is moving
            System.out.println("Board is moving " + direction);
         }
         else {
            return;
         }
      }

      /**
       * Helper method for upadating the board on the GUI
       */
      public void updateGUI() {
         //Initializing the board grids
         int[][] grid = board.getGrid();
         //Looping through the board's grid 
         for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
               //updating the rectangle color
               rectGrid[i][j].setFill(updateTile(grid[i][j]));
               //creating string to hold the value of the grid
               String temp = new String(Integer.toString(grid[i][j]));
               //if the board value is 0 set to null 
               if (grid[i][j] == 0) {
                  temp = null;
               }
               //Updating the text
               textGrid[i][j].setText(temp);
               //updating the font of the text
               textGrid[i][j].setFont(Font.font("Times New Roman", FontWeight.BOLD
                        , updateText(grid[i][j])));
            }
         }
         //Updating the score
         String scoreTemp = new String("Score: " + Integer.toString(board.getScore()));
         scoreTxt.setText(scoreTemp);
      }

      /**
       * Helper method to check if the game is over or not and if it is then
       * display game over screen 
       */
      public void gameOverPrint() {
         //Checking if the board is over
         if (board.isGameOver()) {
            //Creating game over rect
            Rectangle rect = new Rectangle();
            rect.setWidth(scene.getWidth());
            rect.setHeight(scene.getHeight());
            rect.setFill(Constants2048.COLOR_GAME_OVER);
            //Creating the text for game over
            Text gameOver = new Text();
            gameOver.setText("Game Over!");
            gameOver.setFont(Font.font("Times New Roman", FontWeight.BOLD, 
                     70));
            //Adding the rect and text to the pane
            pane.add(rect, 0, 0, board.getGrid().length, 
                  board.getGrid().length+ 1);
            pane.add(gameOver, 0, board.getGrid().length/2, 
                  board.getGrid().length, 1);
            pane.setHalignment(rect, HPos.CENTER);
            pane.setValignment(rect, VPos.CENTER);
            pane.setHalignment(gameOver, HPos.CENTER);
            //Stopping the program if game over
            scene.setOnKeyPressed(null);
         }
         else return;
      }



   }

   /** DO NOT EDIT BELOW */

   // The method used to process the command line arguments
   private void processArgs(String[] args)
   {
      String inputBoard = null;   // The filename for where to load the Board
      int boardSize = 0;          // The Size of the Board

      // Arguments must come in pairs
      if((args.length % 2) != 0)
      {
         printUsage();
         System.exit(-1);
      }

      // Process all the arguments 
      for(int i = 0; i < args.length; i += 2)
      {
         if(args[i].equals("-i"))
         {   // We are processing the argument that specifies
            // the input file to be used to set the board
            inputBoard = args[i + 1];
         }
         else if(args[i].equals("-o"))
         {   // We are processing the argument that specifies
            // the output file to be used to save the board
            outputBoard = args[i + 1];
         }
         else if(args[i].equals("-s"))
         {   // We are processing the argument that specifies
            // the size of the Board
            boardSize = Integer.parseInt(args[i + 1]);
         }
         else
         {   // Incorrect Argument 
            printUsage();
            System.exit(-1);
         }
      }

      // Set the default output file if none specified
      if(outputBoard == null)
         outputBoard = "2048.board";
      // Set the default Board size if none specified or less than 2
      if(boardSize < 2)
         boardSize = 4;

      // Initialize the Game Board
      try{
         if(inputBoard != null)
            board = new Board(new Random(), inputBoard);
         else
            board = new Board(new Random(), boardSize);
      }
      catch (Exception e)
      {
         System.out.println(e.getClass().getName() + 
               " was thrown while creating a " +
               "Board from file " + inputBoard);
         System.out.println("Either your Board(String, Random) " +
               "Constructor is broken or the file isn't " +
               "formated correctly");
         System.exit(-1);
      }
   }

   // Print the Usage Message 
   private static void printUsage()
   {
      System.out.println("Gui2048");
      System.out.println("Usage:  Gui2048 [-i|o file ...]");
      System.out.println();
      System.out.println("  Command line arguments come in pairs of the "+ 
            "form: <command> <argument>");
      System.out.println();
      System.out.println("  -i [file]  -> Specifies a 2048 board that " + 
            "should be loaded");
      System.out.println();
      System.out.println("  -o [file]  -> Specifies a file that should be " + 
            "used to save the 2048 board");
      System.out.println("                If none specified then the " + 
            "default \"2048.board\" file will be used");  
      System.out.println("  -s [size]  -> Specifies the size of the 2048" + 
            "board if an input file hasn't been"); 
      System.out.println("                specified.  If both -s and -i" + 
            "are used, then the size of the board"); 
      System.out.println("                will be determined by the input" +
            " file. The default size is 4.");
   }
}
