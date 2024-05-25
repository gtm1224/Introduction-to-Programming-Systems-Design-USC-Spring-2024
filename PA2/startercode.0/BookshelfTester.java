import java.util.ArrayList;
import java.util.Arrays;

public class BookshelfTester {
   public static void main(String[] args) {

      System.out.println("---------EX2---------");
      //creating an empty Bookshelf object

      Bookshelf bookshelfTestOne = new Bookshelf();
      System.out.println(" testing empty constructor: ");
      System.out.println("print out the whole bookshelf, should print out []");
      System.out.println(bookshelfTestOne);

      //createing a Bookshelf object with an ArrayList
      System.out.println(" testing the constructor pass in an ArrayList: ");
      ArrayList<Integer> pileOfBooks = new ArrayList<>(Arrays.asList(25,37,16,10,1,2,3));
      Bookshelf bookshelfTestTwo = new Bookshelf(pileOfBooks);
      System.out.println("print out the whole bookshelf, should print out [25, 37, 16, 10, 1, 2, 3]");
      System.out.println(bookshelfTestTwo);
      System.out.println("-------end---of--EX2---------");
      System.out.println();
      System.out.println();

      System.out.println("---------EX3---------");
      System.out.println("print out the bookshelf");
      System.out.println(bookshelfTestTwo.toString());
      System.out.println(" testing isSorted method: ");
      System.out.println("should print out false");
      System.out.println(bookshelfTestTwo.isSorted());
      System.out.println();
      System.out.println();

      System.out.println(" testing addFront and isSorted method: ");
      System.out.println("print out the bookshelf");

      pileOfBooks = new ArrayList<>(Arrays.asList(1,2,10,11,12,13,14));
      Bookshelf bookshelfTestThree = new Bookshelf(pileOfBooks);
      System.out.println(bookshelfTestThree.toString());
      System.out.println("should print out true");
      System.out.println(bookshelfTestThree.isSorted());
      System.out.println();
      System.out.println();


      System.out.println("print out the bookshelf");
      System.out.println(bookshelfTestThree.toString());
      System.out.println(" testing addFront: ");
      bookshelfTestThree.addFront(10);
      System.out.println("print out the whole bookshelf, should print out [10, 1, 2, 10, 11, 12, 13, 14]");
      System.out.println(bookshelfTestThree.toString());
      System.out.println("should print out false");
      System.out.println(bookshelfTestThree.isSorted());
      System.out.println();
      System.out.println();
      System.out.println("print out the bookshelf");
      System.out.println(bookshelfTestThree.toString());
      System.out.println(" testing addLast: ");
      bookshelfTestThree.addLast(10000);
      System.out.println("print out the whole bookshelf, should print out [10, 1, 2, 10, 11, 12, 13, 14, 10000]");
      System.out.println(bookshelfTestThree.toString());

      //testing boundary case with size()==0
      System.out.println("print out the bookshelf");
      System.out.println(bookshelfTestOne.toString());
      bookshelfTestOne.addLast(10000);
      System.out.println("print out the whole bookshelf, should print out [10000]");
      System.out.println(bookshelfTestOne.toString());
      System.out.println();
      System.out.println();

      System.out.println("print out the bookshelf");
      System.out.println(bookshelfTestThree.toString());
      System.out.println(" testing removeFront: ");
      int frontTestThree=bookshelfTestThree.removeFront();
      System.out.println("should print Out front equals to 10");
      System.out.println(frontTestThree);
      System.out.println("print out the whole bookshelf, should print out [1, 2, 10, 11, 12, 13, 14, 10000]");
      System.out.println(bookshelfTestThree.toString());
      System.out.println();
      System.out.println();
      System.out.println("-------end---of--EX3---------");
      System.out.println();
      System.out.println();

      System.out.println("---------EX4---------");
      System.out.println("print out the bookshelf");
      System.out.println(bookshelfTestThree.toString());
      System.out.println(" testing removeLast: ");
      int lastTestThree=bookshelfTestThree.removeLast();
      System.out.println("should print out last equals to 10000");
      System.out.println(lastTestThree);
      System.out.println("print out the whole bookshelf, should print out [1, 2, 10, 11, 12, 13, 14]");
      System.out.println(bookshelfTestThree.toString());

      System.out.println("print out the bookshelf");
      System.out.println(bookshelfTestOne.toString());
      int frontTestOne=bookshelfTestOne.removeFront();
      System.out.println("should print out front equals to 10000");
      System.out.println(frontTestOne);
      System.out.println("print out the whole bookshelf, should print out []");
      System.out.println(bookshelfTestOne.toString());
      System.out.println();
      System.out.println();

      System.out.println(" testing size(): ");
      System.out.println("print out size, should print out 0");
      System.out.println(bookshelfTestOne.size());
      pileOfBooks = new ArrayList<>(Arrays.asList(1,2,3,4,5));
      Bookshelf bookshelfTestFour = new Bookshelf(pileOfBooks);
      System.out.println("print out size, should print out 5");
      System.out.println(bookshelfTestFour.size());


      System.out.println(" testing getHeight(): ");
      System.out.println("print out height at position 0, should print out 1");
      System.out.println(bookshelfTestFour.getHeight(0));
      System.out.println("print out height at position 3, should print out 4");
      System.out.println(bookshelfTestFour.getHeight(3));
      System.out.println("-------end---of--EX4---------");

   }
}
