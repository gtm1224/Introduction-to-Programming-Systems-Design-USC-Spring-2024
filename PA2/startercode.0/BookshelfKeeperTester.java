// Name: Tianming Guo
// USC NetID: guotianm
// CSCI455 PA2
// Spring 2024
import java.util.ArrayList;
import java.util.Arrays;

public class BookshelfKeeperTester {
   public static void main(String[] args) {
      ArrayList<Integer> pileOfBooks = new ArrayList<>(Arrays.asList(3, 7, 7, 7, 12, 12, 12, 12, 15, 18));
      Bookshelf bookshelfOne = new Bookshelf(pileOfBooks);
      BookshelfKeeper bookshelfKeeperTestOne = new BookshelfKeeper(bookshelfOne);
      System.out.println(bookshelfKeeperTestOne.toString());
      bookshelfKeeperTestOne.pickPos(3);
      System.out.println(bookshelfKeeperTestOne.toString());
      System.out.println(bookshelfKeeperTestOne.putHeight(18));
      System.out.println(bookshelfKeeperTestOne.toString());
      System.out.println(bookshelfKeeperTestOne.putHeight(14));
      System.out.println(bookshelfKeeperTestOne.toString());
      System.out.println(bookshelfKeeperTestOne.putHeight(12));
      System.out.println(bookshelfKeeperTestOne.toString());
      System.out.println(bookshelfKeeperTestOne.putHeight(14));
      System.out.println(bookshelfKeeperTestOne.toString());
      System.out.println(bookshelfKeeperTestOne.putHeight(14));
      System.out.println(bookshelfKeeperTestOne.toString());


      pileOfBooks = new ArrayList<>(Arrays.asList(3, 3, 3, 3));
      Bookshelf bookshelfTwo = new Bookshelf(pileOfBooks);
      BookshelfKeeper bookshelfKeeperTestTwo = new BookshelfKeeper(bookshelfTwo);
      System.out.println(bookshelfKeeperTestTwo.toString());
      bookshelfKeeperTestTwo.pickPos(3);
      System.out.println(bookshelfKeeperTestTwo.toString());


   }
}
