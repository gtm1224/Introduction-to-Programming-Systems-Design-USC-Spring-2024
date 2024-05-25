// Name: Tianming Guo
// USC NetID: guotianm
// CSCI455 PA2
// Spring 2024

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Class BookshelfKeeperProg
 * BookshelfKeeperProg contains the main method that allows the user to perform a series
 * of pickPos and putHeight operations on a bookshelf in an interactive mode with user
 * commands called pick and put.
 * It can also be run in a batch mode by using input and output redirection.
 * It will exit when it detects the errors in invalide user input.
 */
public class BookshelfKeeperProg {

   /**
    * the main method that allows the user to perform a series
    * of pickPos and putHeight operations on a bookshelf in an interactive mode with user
    * commands called pick and put.
    * It can also be run in a batch mode by using input and output redirection.
    * It will exit when it detects the errors in invalide user input.
    */
   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      System.out.println("Please enter initial arrangement of books followed by newline:");
      Bookshelf bookshelf = new Bookshelf();
      String rawInput = in.nextLine();
      if(!rawInput.isEmpty()){
         String input = rawInput.trim();
         if(!parseInput(input,bookshelf)){
            System.out.println("Exiting Program.");
            return;
         }
      }
      BookshelfKeeper test = new BookshelfKeeper(bookshelf);
      System.out.println(test);
      System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");
      while(in.hasNextLine()){
         String rawLine = in.nextLine();
         String curLine = rawLine.trim();
         if(!parseCommand(curLine, test)){
            System.out.println("Exiting Program.");
            return;
         }
         System.out.println(test);
      }

   }

   /**
    * The method extracts the user command from String input.
    * It Modifies a valid Bookshelf object by adding height to it.
    * The method call will return a boolean. If the input is valid i.e. all heights
    * are positive and they are all sorted, the method will return true, otherwise false
    * @param input, a string of user input in one line that is trimmed
    * @param bookshelf, an empty Bookshelf object that will be adding heights
    * PRE: input is a trimed String i.e. no white spaces in front and the back.
    */
   public static boolean parseInput(String input, Bookshelf bookshelf){

      String[] parseResult = input.split("\\s+");
      for(String num : parseResult){
         if(Integer.parseInt(num)<0){
            System.out.println("ERROR: Height of a book must be positive.");
            return false;
         }
         bookshelf.addLast(Integer.parseInt(num));
      }
      if(!bookshelf.isSorted()){
         System.out.println("ERROR: Heights must be specified in non-decreasing order.");
         return false;
      }

      return true;
   }

   /**
    * The method extracts the user commands from String input.
    * It Modified a valid BookshelfKeeper object by using pick and put command.
    * The method call will return a boolean. If user pick and put commands are executed succefully,
    * i.e. either a successful pick or put the method will return true.
    * For invalid commands or input and "end" will return false.
    * @param curLine, a string of user input in one line that is trimmed.
    * @param test, the object of BookshelfKeeper that will be modified by applying its method.
    * PRE: input is a trimed String i.e. no white spaces in front and the back.
    */

   public static boolean parseCommand(String curLine, BookshelfKeeper test){
      String[] parseResult = curLine.split("\\s+");
      String command = parseResult[0];
      if (command.equals("end")){
         return false ;
      }else if(command.equals("put")){
         int height = Integer.parseInt(parseResult[1]);
         if(height <= 0){
            System.out.println("ERROR: Height of a book must be positive.");
            return false;
         }
         test.putHeight(height);
      }else if(command.equals("pick")){
         int pos = Integer.parseInt(parseResult[1]);
         if(pos<0 || pos>=test.getNumBooks()){
            System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
            return false;
         }
         test.pickPos(Integer.parseInt(parseResult[1]));
      }else{
         System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
         return false;
      }
      return true;
   }


}
