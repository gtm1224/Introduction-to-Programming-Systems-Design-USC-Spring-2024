// Name: Tianming Guo
// USC NetID: guotianm
// CS 455 PA4
// Spring 2024

/*
 tester class for Rack
*
**/


import java.util.ArrayList;

public class RackTester {
   public static void main(String[] args) {
      Rack myRack = new Rack("abccdd");
      ArrayList<String> subsets = myRack.getSubsets();
      System.out.println(subsets);
      Rack myRack1 = new Rack("cmal");
      ArrayList<String> subsets1 = myRack1.getSubsets();
      System.out.println(subsets1);


//      ArrayList<String> test = myRack.allSubsets("abc",new int[]{1,1,1},0);
//      System.out.println(test);
   }
}
