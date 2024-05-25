// Name: Tianming Guo
// USC NetID: guotianm
// CSCI455 PA2
// Spring 2024


import java.util.ArrayList;

/**
 * Class BookshelfKeeper 
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in 
 * non-decreasing order by height, with the restriction that single books can only be added 
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put 
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
public class BookshelfKeeper {

   /**
      Representation invariant:

      elements in bookShelfKeeper represent the heights of the books
      each height must be > 0;
      bookShelfKeeper should always be sorted after any public function call.
      totalOperations represents the number of operations has done so far,
      it should be greater or equal to 0; i.e. totalOperations>=0;
      lastOperation represents the number of operations has done after pickPos or putHeight.
      it also needs to be greater or equal to 0; i.e. lastOperation>=0;
   */
   
   // <add instance variables here>
   private Bookshelf bookShelfKeeper;
   // record the number of operations since the BookshelfKeeper has been created.
   private int totalOperations;
   // record the number of operations has been done after pickPos or putHeight.
   private int lastOperation;

   private static final int TWO = 2;


   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {
      this.bookShelfKeeper = new Bookshelf();
      this.totalOperations = 0;
      this.lastOperation = 0;
   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    *@param sortedBookshelf, a Bookshelf object that contains the heights of books. The heights are all greater than 0,
    *       and the heights are in non-decreasing order.
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {
      this.bookShelfKeeper = sortedBookshelf;
      this.totalOperations = 0;
      this.lastOperation = 0;
      assert isValidBookshelfKeeper(); // add assert to make sure the object is valid after the operations
   }

   /**
    * Removes a book from the specified position in the bookshelf and keeps bookshelf sorted 
    * after picking up the book.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * @param position, the position of a book in bookShelfKeeper.
    * PRE: 0 <= position < getNumBooks()
    */
   public int pickPos(int position) {
      lastOperation = 0;
      //initialize an ArrayList to store the books that have been removed.
      ArrayList<Integer> tempStorage = new ArrayList<>();
      boolean removeFromLast = false;
      if (position>=bookShelfKeeper.size()/TWO){
         // if the position is closer to the right end, then remove books from the right end.
         lastOperation+=removeFromLastUntilPos(position,tempStorage);
         removeFromLast = true;
      }else{
         // if the position is closer to the left end, then remove books from the left end.
         lastOperation+=removeFromFrontUntilPos(position,tempStorage);
      }
      // if the books were removed from the right end, then use addLast
      if(removeFromLast){
         for (int i = tempStorage.size()-1; i >=0 ; i--) {
            bookShelfKeeper.addLast(tempStorage.get(i));
         }
      // if the books were removed from the left end, then use addFront
      }else{
         for (int i = tempStorage.size()-1; i >=0 ; i--) {
            bookShelfKeeper.addFront(tempStorage.get(i));
         }
      }
      lastOperation+=tempStorage.size();
      totalOperations += lastOperation;
      assert isValidBookshelfKeeper(); // add assert to make sure the object is valid after the operations
      return lastOperation;
   }



   /**
    * Inserts book with specified height into the shelf.  Keeps the contained bookshelf sorted 
    * after the insertion.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * @param height, the height of a book. height > 0.
    * PRE: height > 0
    */
   public int putHeight(int height) {
      // if bookShelfKeeper is empty then add book to last
      if (bookShelfKeeper.size()==0){
         bookShelfKeeper.addLast(height);
         lastOperation=1;
         totalOperations+=lastOperation;
         return lastOperation;
      }
      // Comparing for the height from left to right, find the position to insert.
      int firstSeen = firstSeenPosition(height);
      // Comparing for the height from right to left, find the position to insert.
      int lastSeen = lastSeenPosition(height);
      // if the position is closer to the left end, then remove books from the left end.
      // Otherwise, remove from right end.
      if(firstSeen+1<bookShelfKeeper.size()-lastSeen){
         //initialize an ArrayList to store the books that have been removed.
         ArrayList<Integer> tempStorage = new ArrayList<>();
         lastOperation = removeFromFrontUntilPosInclude(firstSeen,tempStorage);
         bookShelfKeeper.addFront(height);
         for (int i = tempStorage.size()-1; i >=0 ; i--) {
            bookShelfKeeper.addFront(tempStorage.get(i));
         }
         lastOperation += tempStorage.size()+1;
      }else{
         //initialize an ArrayList to store the books that have been removed.
         ArrayList<Integer> tempStorage = new ArrayList<>();
         lastOperation = removeFromLastUntilPosInclude(lastSeen,tempStorage);
         bookShelfKeeper.addLast(height);
         for (int i = tempStorage.size()-1; i >=0 ; i--) {
            bookShelfKeeper.addLast(tempStorage.get(i));
         }
         lastOperation += tempStorage.size()+1;
      }
      totalOperations+=lastOperation;
      assert isValidBookshelfKeeper(); // add assert to make sure the object is valid after the operations
      return lastOperation;
   }




   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getTotalOperations() {
      
       return totalOperations;
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {

       return bookShelfKeeper.size();
   }

   /**
    * Returns string representation of this BookshelfKeeper. Returns a String containing height
    * of all books present in the bookshelf in the order they are on the bookshelf, followed 
    * by the number of bookshelf mutator calls made to perform the last pick or put operation, 
    * followed by the total number of such calls made since we created this BookshelfKeeper.
    * 
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
    * 
    */
   public String toString() {
      
      return bookShelfKeeper.toString()+" "+lastOperation+" "+totalOperations;   // dummy code to get stub to compile
   }

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state.
    * (See representation invariant comment for details.)
    */
   private boolean isValidBookshelfKeeper() {

      for (int i = 0; i < bookShelfKeeper.size(); i++) {
         if(bookShelfKeeper.getHeight(i)<=0){
            return false;
         }
      }
      return bookShelfKeeper.isSorted();

   }

   // add any other private methods here
   /**
    * returns the number of operations have been done by removing the books from right end until
    * the position, include the book at the position, but the storage ArrayList will not record the book
    * at the position.
    * @param position, the position of a book in bookShelfKeeper.
    * @param storage, an empty ArrayList that will record the heights of books that have been removed.
    * This method modifies the bookShelfKeeper instance variable.
    */
   private int removeFromFrontUntilPos(int position, ArrayList<Integer> storage){
      for (int i = 0; i <=position ; i++) {
         if(i!=position){
            storage.add(bookShelfKeeper.removeFront());
         }else{
            bookShelfKeeper.removeFront();
         }
      }
      return position+1;
   }

   /**
    * returns the number of operations have been done by removing the books from left(front) end until
    * the position, include the book at the position, but the storage ArrayList will not record the book
    * at the position.
    * @param position, the position of a book in bookShelfKeeper.
    * @param storage, an empty ArrayList that will record the heights of books that have been removed.
    * This method modifies the bookShelfKeeper instance variable.
    */

   private int removeFromLastUntilPos(int position, ArrayList<Integer> storage){
      int temp = bookShelfKeeper.size()-position;
      for (int i = bookShelfKeeper.size()-1; i >=position; i--) {
         if(i!=position){
            storage.add(bookShelfKeeper.removeLast());
         }else{
            bookShelfKeeper.removeLast();
         }
      }
      return temp;
   }

   /**
    * returns the number of operations have been done by removing the books from left(front) end until
    * the position, include the book at the position. The storage ArrayList [will] record the book
    * at the position.
    * @param position, the position of a book in bookShelfKeeper.
    * @param storage, an empty ArrayList that will record the heights of books that have been removed.
    * This method modifies the bookShelfKeeper instance variable.
    */
   private int removeFromFrontUntilPosInclude(int position, ArrayList<Integer> storage){
      for (int i = 0; i <=position ; i++) {
         storage.add(bookShelfKeeper.removeFront());
      }
      return position+1;
   }
   /**
    * returns the number of operations have been done by removing the books from the right(back) end until
    * the position, include the book at the position. The storage ArrayList [will] record the book
    * at the position.
    * @param position, the position of a book in bookShelfKeeper.
    * @param storage, an empty ArrayList that will record the heights of books that have been removed.
    * This method modifies the bookShelfKeeper instance variable.
    */
   private int removeFromLastUntilPosInclude(int position, ArrayList<Integer> storage){
      int temp = bookShelfKeeper.size()-position;
      for (int i = bookShelfKeeper.size()-1; i >= position; i--) {
         storage.add(bookShelfKeeper.removeLast());
      }
      return temp;
   }
   /**
    * looping from left to right to find the position.
    * The books from 0 to the position (include the position) need to be removed
    * return the position
    * @param height, the height of a book. height > 0.
    */
   private int firstSeenPosition(int height){
      int firstSeen = bookShelfKeeper.size();
      for (int i = 0; i < bookShelfKeeper.size(); i++) {
         if(bookShelfKeeper.getHeight(i)>=height){
            firstSeen = i-1;
            break;
         }
      }
      return firstSeen;
   }

   /**
    * looping from right to left to find the position.
    * The books from bookShelfKeeper.size()-1 to the position (include the position) need to be removed
    * return the position
    * @param height, the height of a book. height > 0.
    */
   private int lastSeenPosition(int height){
      int lastSeen = -1;
      for (int i = bookShelfKeeper.size()-1; i >= 0 ; i--) {
         if(bookShelfKeeper.getHeight(i)<=height){
            lastSeen =i+1;
            break;
         }
      }
      return lastSeen;
   }

}
