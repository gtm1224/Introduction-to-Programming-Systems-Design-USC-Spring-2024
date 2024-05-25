// Name: Tianming Guo
// USC NetID: 9863196514
// CS 455 PA3
// Spring 2024

/**
  VisibleField class
  This is the data that's being displayed at any one point in the game (i.e., visible field, because
  it's what the user can see about the minefield). Client can call getStatus(row, col) for any 
  square.  It actually has data about the whole current state of the game, including the underlying
  mine field (getMineField()).  Other accessors related to game status: numMinesLeft(), 
  isGameOver().  It also has mutators related to actions the player could do (resetGameDisplay(),
  cycleGuess(), uncover()), and changes the game state accordingly.
  
  It, along with the MineField (accessible in mineField instance variable), forms the Model for the
  game application, whereas GameBoardPanel is the View and Controller in the MVC design pattern.  It
  contains the MineField that it's partially displaying.  That MineField can be accessed
  (or modified) from outside this class via the getMineField accessor.  
 */
public class VisibleField {
   // ----------------------------------------------------------   
   // The following public constants (plus values [0,8] mentioned in comments below) are the
   // possible states of one location (a "square") in the visible field (all are values that can be
   // returned by public method getStatus(row, col)).
   /**
    Representation invariant:
    0 <= numofGuesses <= mineField.numMines()
    0<= numofUncovered <= mineField.numRows()*mineField.numCols()-mineField.numMines()
    */
   // The following are the covered states (all negative values):
   public static final int COVERED = -1;   // initial value of all squares
   public static final int MINE_GUESS = -2;
   public static final int QUESTION = -3;

   // The following are the uncovered states (all non-negative values):
   
   // values in the range [0,8] corresponds to number of mines adjacent to this opened square
   
   public static final int MINE = 9;      // this loc is a mine that hasn't been guessed already
                                          // (end of losing game)
   public static final int INCORRECT_GUESS = 10;  // is displayed a specific way at the end of
                                                  // losing game
   public static final int EXPLODED_MINE = 11;   // the one you uncovered by mistake (that caused
                                                 // you to lose)
   // ----------------------------------------------------------
   public static final  int Zero = 0;
   // <put instance variables here>

   // A cover for the mineField, each element indicates a states of a square in mineField
   private int[][] mineFieldCover;
   private MineField mineField;
   private int numofGuesses;
   private boolean gameOver;
   // increment when the user uncover a square
   private int numofUncovered;




   /**
      Create a visible field that has the given underlying mineField.
      The initial state will have all the locations covered, no mines guessed, and the game not
      over.
      @param mineField  the minefield to use for for this VisibleField
    */
   public VisibleField(MineField mineField) {
      this.mineField = mineField;
      this.mineFieldCover = new int[mineField.numRows()][mineField.numCols()];
      resetGameDisplay();
   }
   
   
   /**
      Reset the object to its initial state (see constructor comments), using the same underlying
      MineField. 
   */     
   public void resetGameDisplay() {
      for (int i = 0; i <mineField.numRows() ; i++) {
         for (int j = 0; j <mineField.numCols() ; j++) {
            mineFieldCover[i][j] = COVERED;
         }
      }
      numofGuesses = 0;
      numofUncovered=0;
      gameOver = false;
   }
  
   
   /**
      Returns a reference to the mineField that this VisibleField "covers"
      @return the minefield
    */
   public MineField getMineField() {
      return this.mineField;       // DUMMY CODE so skeleton compiles
   }
   
   
   /**
      Returns the visible status of the square indicated.
      @param row  row of the square
      @param col  col of the square
      @return the status of the square at location (row, col).  See the public constants at the
            beginning of the class for the possible values that may be returned, and their meanings.
      PRE: getMineField().inRange(row, col)
    */
   public int getStatus(int row, int col) {
      return mineFieldCover[row][col];       // DUMMY CODE so skeleton compiles
   }

   
   /**
      Returns the the number of mines left to guess.  This has nothing to do with whether the mines
      guessed are correct or not.  Just gives the user an indication of how many more mines the user
      might want to guess.  This value will be negative if they have guessed more than the number of
      mines in the minefield.     
      @return the number of mines left to guess.
    */
   public int numMinesLeft() {
      return mineField.numMines() - numofGuesses;       // DUMMY CODE so skeleton compiles

   }
 
   
   /**
      Cycles through covered states for a square, updating number of guesses as necessary.  Call on
      a COVERED square changes its status to MINE_GUESS; call on a MINE_GUESS square changes it to
      QUESTION;  call on a QUESTION square changes it to COVERED again; call on an uncovered square
      has no effect.  
      @param row  row of the square
      @param col  col of the square
      PRE: getMineField().inRange(row, col)
    */
   public void cycleGuess(int row, int col) {
      int curState = getStatus(row,col);
      if(curState == COVERED){
         mineFieldCover[row][col] = MINE_GUESS;
         numofGuesses += 1;
      }else if(curState == MINE_GUESS){
         mineFieldCover[row][col] = QUESTION;
         numofGuesses -= 1;
      }else if(curState == QUESTION){
         mineFieldCover[row][col] = COVERED;
      }
   }

   
   /**
      Uncovers this square and returns false iff you uncover a mine here.
      If the square wasn't a mine or adjacent to a mine it also uncovers all the squares in the
      neighboring area that are also not next to any mines, possibly uncovering a large region.
      Any mine-adjacent squares you reach will also be uncovered, and form (possibly along with
      parts of the edge of the whole field) the boundary of this region.
      Does not uncover, or keep searching through, squares that have the status MINE_GUESS. 
      Note: this action may cause the game to end: either in a win (opened all the non-mine squares)
      or a loss (opened a mine).
      @param row  of the square
      @param col  of the square
      @return false   iff you uncover a mine at (row, col)
      PRE: getMineField().inRange(row, col)
    */
   public boolean uncover(int row, int col) {
      if(mineField.hasMine(row,col)){
         mineFieldCover[row][col] = EXPLODED_MINE;
         gameOver = true;
         numofUncovered += 1;
         finalDisplayLose();
         return false;
      }
      DFS(row,col);
      if(checkWin()){
         finalDisplayWin();
      }
      return true;
   }
 
   
   /**
      Returns whether the game is over.
      (Note: This is not a mutator.)
      @return whether game has ended, i.e. gameOver variable
    */
   public boolean isGameOver() {

      return gameOver;

   }
 
   
   /**
      Returns whether this square has been uncovered.  (i.e., is in any one of the uncovered states, 
      vs. any one of the covered states).
      @param row of the square
      @param col of the square
      @return whether the square is uncovered
      PRE: getMineField().inRange(row, col)
    */
   public boolean isUncovered(int row, int col) {
      if(mineFieldCover[row][col] == COVERED || mineFieldCover[row][col] == MINE_GUESS || mineFieldCover[row][col] ==QUESTION){
         return false;
      }
      return true;
   }
   
 
   // <put private methods here>
   /**
    Apply  DFS in a recursive way. Try to uncover a squre in current (row,col) then propagate to top,right,down,left
    and four diagonal squares. It will stop when a square in not inRange, or uncovered already, or is a mine, or marked
    guess mine. It will display the number of adjacent mines when it hits boundary and return.
    @param row  of the square
    @param col  of the square

    PRE: getMineField().inRange(row, col)
    */
   private void DFS(int row, int col){
      // return when a squre is not in range, is a mine, is Uncovered, a guessed mine.
      if(!mineField.inRange(row,col)|| mineField.hasMine(row,col) || isUncovered(row,col) || getStatus(row,col)==MINE_GUESS){
         return;
      }
      // return when a squre has the number of adjacent mines greater than 0. update the number of uncovered mines.
      if(mineField.numAdjacentMines(row,col)!=Zero){
         mineFieldCover[row][col]=mineField.numAdjacentMines(row,col);
         numofUncovered += 1;
         return;
      }
      // update the number of uncovered mines
      mineFieldCover[row][col]=mineField.numAdjacentMines(row,col);
      numofUncovered += 1;
      // apply DFS to squares around it. i.e. the top,right,down,left and four dignoal squares i.e.
      // top-left, top-right, bottom-left and bottom-right.
      DFS(row-1,col);
      DFS(row,col+1);
      DFS(row+1,col);
      DFS(row,col-1);
      DFS(row-1,col-1);
      DFS(row-1,col+1);
      DFS(row+1,col-1);
      DFS(row+1,col+1);

   }

   /**
    check if user win the game or not. First calculate if the number of uncovered squares are equal to the
    total number of non-mine squares, then check each square to see if the user made wrong click for mines.
    if user clicked the mine, the gameOver will be set true and the method returns false. If all the squares
    passed the check, returns true.
    @return returns true if the user win the game, return false if the user lose the game.
    */
   private boolean checkWin(){
      if (numofUncovered == mineField.numRows()*mineField.numCols()-mineField.numMines()){
         for (int i = 0; i <mineField.numRows() ; i++) {
            for (int j = 0; j <mineField.numCols() ; j++){
               if(mineField.hasMine(i,j)){
                  if(isUncovered(i,j)){
                     gameOver = true;
                     return false;
                  }
               }
            }
         }
         gameOver = true;
         return true;

      }
      return false;
   }
   /**
    when user win the game, make the final display.
    */
   private void finalDisplayWin(){
      for (int i = 0; i <mineField.numRows() ; i++) {
         for (int j = 0; j <mineField.numCols() ; j++) {
            //change all the mine to MINE_GUESS
            if(mineField.hasMine(i,j)){
               mineFieldCover[i][j] = MINE_GUESS;
            }
         }
      }
   }
   /**
    when user lose the game, make the final display.
    */
   private void finalDisplayLose(){
      for (int i = 0; i <mineField.numRows() ; i++) {
         for (int j = 0; j <mineField.numCols() ; j++) {
            if(mineField.hasMine(i,j)){
               //if user did not guess the mine, change it to MINE
               if(getStatus(i,j)==COVERED || getStatus(i,j)==QUESTION){
                  mineFieldCover[i][j] = MINE;
               }
               //if user guessed the mine wrong, change it to INCORRECT_GUESS
            }else if (getStatus(i,j) == MINE_GUESS){
               mineFieldCover[i][j] = INCORRECT_GUESS;
            }
         }
      }
   }
}
