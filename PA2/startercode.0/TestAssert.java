import java.util.ArrayList;
import java.util.Arrays;

public class TestAssert {
   public static void main(String[] args) {
      Bookshelf assertTestOne = new Bookshelf();
      ArrayList<Integer> pileOfBooks = new ArrayList<>(Arrays.asList(1,2,10,11,-10,13,14));

      Bookshelf assertTestTwo = new Bookshelf(pileOfBooks);
//      System.out.println(assertTestTwo.toString());
   }
}
