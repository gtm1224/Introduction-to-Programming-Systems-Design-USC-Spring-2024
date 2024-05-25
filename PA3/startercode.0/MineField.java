// Name: Tianming Guo
// USC NetID: 9863196514
// CS 455 PA3
// Spring 2024

/** 
   MineField
      Class with locations of mines for a minesweeper game.
      This class is mutable, because we sometimes need to change it once it's created.
      Mutators: populateMineField, resetEmpty
      Includes convenience method to tell the number of mines adjacent to a location.
 */
public class MineField {
   /**
    Representation invariant:
    numRows>=1, numCols>=1, 0 <= numMines < (1/3 * numRows * numCols).
    minesField has dimension number of rows same as numRows, and dimension number of columns same as numCols.
    minesField elements can only be false or true.
    */
   // private instance variables:
   private int numRows;
   private int numCols;
   private int numMines;
   private boolean[][] minesField;
   /**
      Create a minefield with same dimensions as the given array, and populate it with the mines in
      the array such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice
      versa.  numMines() for this minefield will corresponds to the number of 'true' values in 
      mineData.
      @param mineData  the data for the mines; must have at least one row and one col,
                       and must be rectangular (i.e., every row is the same length)
    */
   public MineField(boolean[][] mineData) {
      assert mineData.length>=1 && mineData[0].length >= 1;
      this.numRows = mineData.length;
      this.numCols = mineData[0].length;
      this.numMines = 0;
      this.minesField = new boolean[numRows][numCols];
      for (int i = 0; i <numRows ; i++) {
         for (int j = 0; j <numCols ; j++) {
            minesField[i][j] = mineData[i][j];
            if(mineData[i][j] == true){
               numMines+=1;
            }
         }

      }


   }
   
   
   /**
      Create an empty minefield (i.e. no mines anywhere), that may later have numMines mines (once 
      populateMineField is called on this object).  Until populateMineField is called on such a 
      MineField, numMines() will not correspond to the number of mines currently in the MineField.
      @param numRows  number of rows this minefield will have, must be positive
      @param numCols  number of columns this minefield will have, must be positive
      @param numMines   number of mines this minefield will have,  once we populate it.
      PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations). 
    */
   public MineField(int numRows, int numCols, int numMines) {
      this.numRows = numRows;
      this.numCols = numCols;
      this.numMines = numMines;
      minesField = new boolean[numRows][numCols];
   }
   

   /**
      Removes any current mines on the minefield, and puts numMines() mines in random locations on
      the minefield, ensuring that no mine is placed at (row, col).
      @param row the row of the location to avoid placing a mine
      @param col the column of the location to avoid placing a mine
      PRE: inRange(row, col) and numMines() < (1/3 * numRows() * numCols())
    */
   public void populateMineField(int row, int col) {
      resetEmpty();
      int curMine = 0;
      while(curMine<numMines){
         int randRow = randomNum(this.numRows);
         int randCol = randomNum(this.numCols);
         if((randRow == row && randCol == col) || minesField[randRow][randCol] == true){
            continue;
         } else{
            minesField[randRow][randCol] = true;
            curMine += 1;
         }

      }

   }
   
   
   /**
      Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or
      numCols().  Thus, after this call, the actual number of mines in the minefield does not match
      numMines().  
      Note: This is the state a minefield created with the three-arg constructor is in at the 
      beginning of a game.
    */
   public void resetEmpty() {
      minesField = new boolean[numRows][numCols];

   }

   
  /**
     Returns the number of mines adjacent to the specified location (not counting a possible 
     mine at (row, col) itself).
     Diagonals are also considered adjacent, so the return value will be in the range [0,8]
     @param row  row of the location to check
     @param col  column of the location to check
     @return  the number of mines adjacent to the square at (row, col)
     PRE: inRange(row, col)
   */
   public int numAdjacentMines(int row, int col) {

      int numAdjacent = 0;
      numAdjacent += checkTopBottomLeftRight(row,col);
      numAdjacent += checkDiagonals(row,col);

      return numAdjacent;
   }
   
   
   /**
      Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
      start from 0.
      @param row  row of the location to consider
      @param col  column of the location to consider
      @return whether (row, col) is a valid field location
   */
   public boolean inRange(int row, int col) {
      if(row<0 || row>= numRows || col<0 || col>= numCols){
         return false;
      }
      return true;
   }
   
   
   /**
      Returns the number of rows in the field.
      @return number of rows in the field
   */  
   public int numRows() {
      return this.numRows;       // DUMMY CODE so skeleton compiles
   }
   
   
   /**
      Returns the number of columns in the field.
      @return number of columns in the field
   */    
   public int numCols() {
      return this.numCols;       // DUMMY CODE so skeleton compiles
   }
   
   
   /**
      Returns whether there is a mine in this square
      @param row  row of the location to check
      @param col  column of the location to check
      @return whether there is a mine in this square
      PRE: inRange(row, col)   
   */    
   public boolean hasMine(int row, int col) {
      return minesField[row][col];
   }
   
   
   /**
      Returns the number of mines you can have in this minefield.  For mines created with the 3-arg
      constructor, some of the time this value does not match the actual number of mines currently
      on the field.  See doc for that constructor, resetEmpty, and populateMineField for more
      details.
      @return number of mines
    */
   public int numMines() {

      return numMines;

   }

   
   // <put private methods here>


   /**
    Returns a random integer number in range [0,range)
    @param the range here is either numRows or numCols
    @return a random integer number in range [0,range)
    PRE: range can only be numRows or numCols i.e. range = numRows or range = numCols
    */
   private int randomNum(int range){

      return (int) (Math.random()*range);

   }

   /**
    Returns the total number of adjacent mines that are from four directions: top bottom left and right
    @param row row of the location to check
    @param col column of the location to check
    @return Returns the total number of adjacent in top, bootom, left and right
    PRE: inRange(row, col)
    */
   private int checkTopBottomLeftRight(int row, int col){
      int numAdjacent = 0;
//      check top square
      if (inRange(row-1,col)){
         if (minesField[row-1][col]==true){
            numAdjacent+=1;
         }
      }
//      check bottom square
      if (inRange(row+1,col)){
         if (minesField[row+1][col]==true){
            numAdjacent+=1;
         }
      }
//      check left square
      if (inRange(row,col-1)){
         if (minesField[row][col-1]==true){
            numAdjacent+=1;
         }
      }
//      check right square
      if (inRange(row,col+1)){
         if (minesField[row][col+1]) {
            numAdjacent+=1;
         }
      }

      return numAdjacent;
   }
   /**
    Returns the total number of adjacent mines that are in the two diagonals. i.e. the four corner: top-left, top-right
    bottom-right, bottom-left.
    @param row row of the location to check
    @param col column of the location to check
    @return Returns the total number of adjacent mines that are in the two diagonals.
    PRE: inRange(row, col)
    */
   private int checkDiagonals(int row, int col){
      int numAdjacent = 0;
//      check top-left
      if (inRange(row-1,col-1)){
         if (minesField[row-1][col-1]) {
            numAdjacent+=1;
         }
      }
//      check top-right
      if (inRange(row-1,col+1)){
         if (minesField[row-1][col+1]) {
            numAdjacent+=1;
         }
      }
//      check bottom-right
      if (inRange(row+1,col+1)){
         if (minesField[row+1][col+1]) {
            numAdjacent+=1;
         }
      }
//      check bottom-left
      if (inRange(row+1,col-1)){
         if (minesField[row+1][col-1]) {
            numAdjacent+=1;
         }
      }
      return numAdjacent;
   }
}

