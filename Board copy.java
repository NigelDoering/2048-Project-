// Name: Nigel Doering 
// Email: nfdoerin@gmail.com
// PID: A14445032
// CS8B account: cs8bwaat
// This file creates a board for tbe game 2048. The board file includes 
// methods allowing for the game to check if a move is possible and also
// executes the actual move command in the game. Also in the file are 
// helper methods for the main methods and a method to save the board to a
// file. The class is focused on creating a game board and implementing all
// the main capabilities of the board in the game 2048. The instance variables 
//

/**
 * Sample Board
 * <p/>
 * 0   1   2   3
 * 0   -   -   -   -
 * 1   -   -   -   -
 * 2   -   -   -   -
 * 3   -   -   -   -
 * <p/>
 * The sample board shows the index values for the columns and rows
 * Remember that you access a 2D array by first specifying the row
 * and then the column: grid[row][column]
 */

import java.util.*;
import java.io.*;

public class Board {
   public final int NUM_START_TILES = 2; 
   public final int TWO_PROBABILITY = 90;
   public final int GRID_SIZE;


   private final Random random; // a reference to the Random object, passed in 
   // as a parameter in Boards constructors
   private int[][] grid;  // a 2D int array, its size being boardSize*boardSize
   private int score;     // the current score, incremented as tiles merge 


   // TODO PSA3
   // Constructs a fresh board with random tiles
   public Board(Random random, int boardSize) {
      //Initializing grid size to passed parameter
      GRID_SIZE = boardSize;
      //Initializing grid to a double int array
      this.grid = new int[boardSize][boardSize];
      this.random = random;
      //Adding to random tiles to beginning of game
      this.addRandomTile();
      this.addRandomTile();
      //Initializing score to 0
      this.score = 0;



   }

   // TODO PSA3
   // Construct a board based off of an input file
   // assume board is valid
   public Board(Random random, String inputBoard) throws IOException {
      //Initializing random object
      this.random = random;
      //Creating a file to pass to scanner
      File gameFile = new File(inputBoard);
      //Creating scanner to read file
      Scanner input = new Scanner(gameFile);
      //Storing grid size as string
      String tempGridSize = input.next();
      //Initializing GRID_SIZE
      GRID_SIZE = Integer.parseInt(tempGridSize);
      //Storing score as string
      String tempScore = input.next();
      //Initializing the score
      this.score = Integer.parseInt(tempScore);
      //Creating double int array to hold the grid
      int [][] tempGrid = new int[GRID_SIZE][GRID_SIZE];
      //Looping through the file to copy values to toBeGrid
      for (int i = 0; i < GRID_SIZE; i++) {
         for (int j = 0; j < GRID_SIZE; j++) {
            //Holding the int value of the grid
            String tempGridValue = input.next();
            //Putting the value into the temp grid
            tempGrid[i][j] = Integer.parseInt(tempGridValue);
         }
      }
      //Transferring toBeGrid to actual grid
      this.grid = tempGrid;
   }

   // Saves the current board to a file
   // @param takes the name of the file to be saved to
   // @return returns nothing, void
   public void saveBoard(String outputBoard) throws IOException {
      //Creating a PrintWriter to save the game to a file
      PrintWriter myPrinter = new PrintWriter(outputBoard);
      //Saving grid size and score to variable
      int gridSize = this.GRID_SIZE;
      int score = this.score;
      //Printing grid size and score to separate lines
      myPrinter.println(gridSize);
      myPrinter.println(score);
      //Creating a temporary grid to hold values of grid
      int[][] temp = this.grid;
      //Looping through the rows of grid
      for (int i = 0; i < gridSize; i++) {
         //Creating empty string to fill with grid values
         String gridLine = "";
         //Looping through columns of the grid
         for (int j = 0; j < gridSize; j++) {
            //Saving the value of a grid location
            int value = temp[i][j];
            //Converting value to string with space at end to save to file
            String valueToString = Integer.toString(value) + " ";
            //Creating grid line
            gridLine = gridLine + valueToString;
         }
         //printing the whole grid line to file
         myPrinter.println(gridLine);
      }
      //Closing printer to ensure everything is saved to file
      myPrinter.close();
   }

   // Adds a random tile (of value 2 or 4) to a
   // random empty space on the board
   // @param none 
   // @return returns nothing
   public void addRandomTile() {
      //Calling helper method get empty spaces to get count of 0 spaces
      int count = this.getEmptySpaces();
      //Creating a random number location representing where to put tile
      int location = random.nextInt(count);
      //Creating random number to compare to TWO_PROBABILTY
      int value = random.nextInt(100);
      //putting count back to -1 in order to count number of 0 spaces
      count = -1;
      //Looping through the board
      for (int m = 0; m < this.GRID_SIZE; m++) {
         for (int n = 0; n < this.GRID_SIZE; n++) {
            //Checking if the board has a zero and if so increment count
            if (this.grid[m][n] == 0) {
               count++;
            }
            //Initializing values to represent m and n
            int x = m;
            int y = n;
            //Checking if count = location and if so adding tile
            if (count == location) {
               this.addingTile(x, y, value);
               return;
            }
         }
      }
   }
   //Helper method for AddRandomTile to actually add the tile
   //@param passes in the location on the board along with value
   //to decide if to add a two or four
   //@returns is void
   public void addingTile(int m, int n, int value) {
      //Checking if value is greater than probability and adding 2 if so
      if (value < TWO_PROBABILITY) {
         grid[m][n] = 2;
      }
      //Else adding a 4 to the board
      else { 
         grid[m][n] = 4;
      }
   }

   //Helper method to get count for the addrandomtile method
   //Returns an int of the amount of empty spaces
   public int getEmptySpaces() {
      //Creating a count variable to hold the number of empty spaces
      int count = 0;
      //Looping through the grid of the calling object
      for (int i = 0; i < this.GRID_SIZE; i++) {
         for (int j = 0; j < this.GRID_SIZE; j++) {
            //Checking if board element is empty
            if (this.grid[i][j] == 0) {
               //Increasing count
               count++;
            }
         }
      }
      //Returning the count
      return count;
   }

   // determins whether the board can move in a certain direction
   // return true if such a move is possible
   // @param takes a direction to determine if move is possible
   public boolean canMove(Direction direction){
      //Creatin varibale to hold boolean return value
      boolean temp = false;
      //Checking if the direction is left
      if (direction == Direction.LEFT) {
         //Holding return value of canMoveLeft
         temp = this.canMoveLeft();
         //Returning boolean value 
         return temp;
      }
      //Now checking right
      else if (direction == Direction.RIGHT) {
         //Holding return value and returning it
         temp = this.canMoveRight();
         return temp; 
      }
      //Checking up direction
      else if (direction == Direction.UP) {
         //Varibale to hold return value and to be returned
         temp = this.canMoveUp();
         return temp;
      }
      //Checking downward direction
      else if (direction == Direction.DOWN) {
         //Varibale to hold return value and to return
         temp = this.canMoveDown();
         return temp;
      }
      //If all else fails return false
      else { 
         return false;
      }
   }

   //Helper method for canMove. Checks if tiles can move right.
   //Takes no parameters and returns boolean
   private boolean canMoveRight() {
      //Looping through the grid
      for (int i =0; i < this.GRID_SIZE; i++) {
         for (int j = GRID_SIZE-1; j > 0; j--) {
            //checking tile and adjacent tile to the right
            if ((this.grid[i][j] == this.grid[i][j-1] && 
                     this.grid[i][j] != 0)||
                  (this.grid[i][j] == 0 && this.grid[i][j-1] != 0)) {
               return true;
            }
         }
      }
      return false;
   }

   //Helper method for canMove. Checks if tiles can move left.
   //takes no parameters and returns boolean
   private boolean canMoveLeft() {
      //Creating variable to hold grid size
      int gridSize = this.GRID_SIZE;
      //Looping through the grid
      for (int i = 0; i < gridSize; i++) {
         for (int j = 0; j < gridSize-1; j++) {
            //Checking if value and value to right are same 
            if ((this.grid[i][j] == this.grid[i][j+1] &&
                     this.grid[i][j] != 0)||
                  (this.grid[i][j] == 0 && this.grid[i][j+1] != 0)) {
               return true;
            }
         }
      }
      return false;
   }

   //Helper method for canMove. Checks if tiles can move down
   //no parameters and returns boolean
   private boolean canMoveDown() {
      //variable to hold grid size
      int gridSize = this.GRID_SIZE;
      //looping through the grid
      for (int i = gridSize-1; i > 0; i--) {
         for (int j = 0; j < gridSize; j++) {
            //Checking if value and value below are equal
            if ((this.grid[i][j] == this.grid[i-1][j] &&
                     this.grid[i][j] != 0)||
                  (this.grid[i][j] == 0 && this.grid[i-1][j] != 0)) {
               return true;
            }
         }
      }
      return false;
   }
   //This is a helper method for canMove and checks if tiles can move
   //up. Takes no parameters and returns boolean 
   private boolean canMoveUp() {
      //Varible to hold grid size
      int gridSize = this.GRID_SIZE;
      //Looping through the grid
      for (int i = 0; i < gridSize-1; i++) {
         for (int j = 0; j < gridSize; j++) {
            //Checking if value and value above are equal
            if ((this.grid[i][j] == this.grid[i+1][j] && 
                     this.grid[i][j]!=0)||
                  (this.grid[i][j] == 0 && this.grid[i+1][j] != 0)) {
               return true;
            }
         }
      }
      return false;
   }

   // move the board in a certain direction
   // return true if such a move is successful
   // @param takes diretion to execute the move in that direction
   public boolean move(Direction direction) {
      //Checking if the move is possible if not return false
      if (this.canMove(direction) == false) {
         return false;
      }
      //Performing operations if directions is right
      if (direction == Direction.RIGHT) {
         //Calling method to move right
         this.moveRight();
         //this.addRandomTile();
         return true;
      }
      //Performing operations if the direction is left
      else if (direction == Direction.LEFT) {
         //Calling method to moves tiles left
         this.moveLeft();
         // this.addRandomTile();
         return true;
      }
      //Performing operations if the direction is up
      else if (direction == Direction.UP) {
         //Moving tiles up
         this.moveUp();
         // this.addRandomTile();
         return true;
      }
      //performing operations if the direction is down
      else if (direction == Direction.DOWN) {
         //moving tiles down
         this.moveDown(); 
         //this.addRandomTile();
         return true;
      }
      else {
         return false; 
      }
   }
   //helper method for move method
   //@param no parameters
   //@return void
   private void moveRight() {
      this.rotate();
      this.rotate();
      //Shifting grid to begin adding varinables together
      this.moveLeft();
      this.rotate();
      this.rotate();
   }

   //Helper method for move. Moves tiles left
   //@param no parameters
   //@return void
   private void moveLeft() {
      //Score to increment score
      int score = this.score;
      //Assigning grid to shifted left grid
      grid = this.shiftLeft();
      //looping through the grid
      for(int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid.length-1; j++) {
            //checking if adjacent tiles are equal 
            if (grid[i][j] == grid[i][j+1] && grid[i][j] != 0) {
               //new tile value saved
               int newTile = grid[i][j] * 2;
               //Updating new tile and old
               grid[i][j] = newTile;
               grid[i][j+1] = 0;
               //Incrementing score
               score = score + newTile;
            }
         }
      }
      //shifting grid again to complete shift
      grid = this.shiftLeft();
      //updating score
      this.score = score;
   }

   //helper method to move. Shifts tiles down
   //@param none
   //@return void
   private void moveDown() {
      this.rotate();
      this.rotate();
      this.rotate();
      this.moveLeft();
      this.rotate();
   }


   //Helper method for move. Moves tiles up
   //@param none
   //@return void
   private void moveUp() {
      this.rotate();
      this.moveLeft();
      this.rotate();
      this.rotate();
      this.rotate();
   }
   
   //Helper method for move. Shifts the actual board
   //@return int[][] returnd a shifted board
   //@param none
   private int[][] shiftLeft() {
      //int[][] to hold array to return
      int[][] arrayAfter = new int[grid.length][grid.length];
      int index = 0;
      //Looping thrpugh the grid
      for (int i = 0; i < grid.length; i++) {
         index = 0;
         for (int j = 0; j < grid.length; j++) {
            if (grid[i][j] != 0) {
               //copying elements if they are not zero
               arrayAfter[i][index] = grid[i][j];
               //incrementing index
               index++;
            }
         }
      }
      //returning the finsished array
      return arrayAfter;
   }
   
   //this method rotates the board in order to allow it to shift using
   //only shift left
   //@param none
   //@return none
   private void rotate() {
      //Creating new board
      int[][] temp = new int[grid.length][grid.length];
      //Looping through the array
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid.length; j++) {
            //rotating the old board to the new board
            temp[grid.length-j-1][i] = grid[i][j];
         }
      }
      //upgrading the grid
      grid = temp;
   }



   // No need to change this for PSA3
   // Check to see if we have a game over
   // @returns true if the game is over 
   // @param none
   public boolean isGameOver() {
      //If statement checking if this can move in any direction
      if ((this.canMove(Direction.LEFT) == false) &&
            (this.canMove(Direction.RIGHT) == false) &&
            (this.canMove(Direction.UP) == false) &&
            (this.canMove(Direction.DOWN) == false)) {
         //Returning false meaning game is over
         return true;
      }
      else {
         return false;
      }
   }

   // Return the reference to the 2048 Grid
   public int[][] getGrid() {
      return grid;
   }

   // Return the score
   public int getScore() {
      return score;
   }

   @Override
      public String toString() {
         StringBuilder outputString = new StringBuilder();
         outputString.append(String.format("Score: %d\n", score));
         for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++)
               outputString.append(grid[row][column] == 0 ? "    -" :
                     String.format("%5d", grid[row][column]));

            outputString.append("\n");
         }
         return outputString.toString();
      }
   public static void main(String[] args) {
      Random r = new Random();
      Board b = new Board(r, 2);
      b.grid[0][0] = 2;
      b.grid[1][0] = 2;
      b.grid[0][1] = 4;
      b.grid[1][1] = 4;
      b.rotate(); 
      System.out.println(b);

   }
}
