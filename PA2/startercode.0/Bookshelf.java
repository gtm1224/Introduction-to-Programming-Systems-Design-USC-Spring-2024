// Name: Tianming Guo
// USC NetID: guotianm
// CSCI455 PA2
// Spring 2024


import java.util.ArrayList;

/**
 * Class Bookshelf
 * Implements idea of arranging books into a bookshelf.
 * Books on a bookshelf can only be accessed in a specific way so books don’t fall down;
 * You can add or remove a book only when it’s on one of the ends of the shelf.   
 * However, you can look at any book on a shelf by giving its location (starting at 0).
 * Books are identified only by their height; two books of the same height can be
 * thought of as two copies of the same book.
*/

public class Bookshelf {

   /**
      Representation invariant:
         elements in pileOfBooks represent the heights of the books
         each height must be > 0;
   */
   private ArrayList<Integer> pileOfBooks;

   private static final int ZERO= 0;

   /**
    * Creates an empty Bookshelf object i.e. with no books
    */
   public Bookshelf() {
      this.pileOfBooks = new ArrayList<>();
      assert isValidBookshelf();  // assert statement to check the Bookshelf is valid.
   }

   /**
    * Creates a Bookshelf with the arrangement specified in pileOfBooks. Example
    * values: [20, 1, 9].
    * 
    * PRE: pileOfBooks contains an array list of 0 or more positive numbers
    * representing the height of each book.
    * @param pileOfBooks an ArrayList stores the height of books, the heights are all greater than 0.
    */
   public Bookshelf(ArrayList<Integer> pileOfBooks) {
      for(int book:pileOfBooks){
         assert book>ZERO;
      }
      this.pileOfBooks = new ArrayList<>(pileOfBooks);
      assert isValidBookshelf();

   }

   /**
    * Inserts book with specified height at the start of the Bookshelf, i.e., it
    * will end up at position 0.
    * @param height, the height of a book. height > 0.
    * PRE: height > 0 (height of book is always positive)
    */
   public void addFront(int height) {
      assert height > ZERO;
      this.pileOfBooks.add(0,height);
      assert isValidBookshelf();
   }

   /**
    * Inserts book with specified height at the end of the Bookshelf.
    * @param height, the height of a book. height > 0.
    * PRE: height > 0 (height of book is always positive)
    */
   public void addLast(int height) {
      assert height > ZERO;
      this.pileOfBooks.add(this.pileOfBooks.size(),height);
      assert isValidBookshelf();
   }

   /**
    * Removes book at the start of the Bookshelf and returns the height of the
    * removed book.
    * 
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeFront() {
      assert pileOfBooks.size()>ZERO;
      int firstBook = pileOfBooks.get(ZERO);
      pileOfBooks.remove(ZERO);
      assert isValidBookshelf();
      return firstBook;
      
   }

   /**
    * Removes book at the end of the Bookshelf and returns the height of the
    * removed book.
    * 
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeLast() {
      assert pileOfBooks.size()>0;
      int lastBook = pileOfBooks.get(pileOfBooks.size()-1);
      pileOfBooks.remove(pileOfBooks.size()-1);
      assert isValidBookshelf();
      return lastBook;
   }

   /*
    * Gets the height of the book at the given position.
    * @param position, the position of the book inside the pileOfBooks.
    * PRE: 0 <= position < this.size()
    */
   public int getHeight(int position) {

      int temp = pileOfBooks.get(position);
      assert isValidBookshelf();
      assert (position>=0 && position<pileOfBooks.size());
      return temp;
      
   }

   /**
    * Returns number of books on the this Bookshelf.
    */
   public int size() {

      int temp =pileOfBooks.size();
      assert isValidBookshelf();
      return temp;

   }

   /**
    * Returns string representation of this Bookshelf. Returns a string with the height of all
    * books on the bookshelf, in the order they are in on the bookshelf, using the format shown
    * by example here:  “[7, 33, 5, 4, 3]”
    */
   public String toString() {
      assert isValidBookshelf();
      return pileOfBooks.toString();

   }

   /**
    * Returns true iff the books on this Bookshelf are in non-decreasing order.
    * (Note: this is an accessor; it does not change the bookshelf.)
    */
   public boolean isSorted() {
      assert isValidBookshelf();
      int previousHeight = ZERO;
      for(int height : pileOfBooks){
         if(height<previousHeight){
            return false;
         }
         previousHeight = height;
      }
      return true;
   }

   /**
    * Returns true iff the Bookshelf data is in a valid state.
    * (See representation invariant comment for more details.)
    */
   private boolean isValidBookshelf() {
      for (int bookHeight : this.pileOfBooks) {
         if (bookHeight <= 0) {
            return false;
         }
      }
      return true;
   }

}
