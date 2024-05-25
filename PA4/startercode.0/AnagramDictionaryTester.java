// Name: Tianming Guo
// USC NetID: guotianm
// CS 455 PA4
// Spring 2024

/*
 tester class for AnagramDictionary
*
**/
import java.util.ArrayList;

public class AnagramDictionaryTester {
   public static void main(String[] args) {
      try{
         AnagramDictionary myAnagram = new AnagramDictionary("./testFiles/duplicate.txt");
         ArrayList<String> anagram = myAnagram.getAnagramsOf("abc");
         System.out.println(anagram);
      }catch(Exception e){
         System.out.println(e.getMessage());
         System.out.println("Exiting program.");
//         e.printStackTrace();
      }

   }
}
