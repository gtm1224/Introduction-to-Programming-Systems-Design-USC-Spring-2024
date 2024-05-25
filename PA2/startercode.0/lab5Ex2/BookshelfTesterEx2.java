import java.util.ArrayList;
import java.util.Arrays;

public class BookshelfTesterEx2 {
   public static void main(String[] args) {
      //creating an empty Bookshelf object
      Bookshelf bookshelfTestOne = new Bookshelf();
      System.out.println("should print out []");
      System.out.println(bookshelfTestOne);

      //createing a Bookshelf object with an ArrayList
      ArrayList<Integer> pileOfBooks = new ArrayList<>(Arrays.asList(25, 37, 16, 10, 1, 2, 3));
      Bookshelf bookshelfTestTwo = new Bookshelf(pileOfBooks);
      System.out.println("should print out: [25, 37, 16, 10, 1, 2, 3]");
      System.out.println(bookshelfTestTwo);

   }
}
