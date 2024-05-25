// Name: Tianming Guo
// USC NetID: guotianm
// CS 455 PA4
// Spring 2024

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
   A Rack of Scrabble tiles
 */

public class Rack {

   private String word;
   private static final int ZERO = 0;
   /**
    a constructor for the Rack.
    @param word a user input word

    */
   public Rack(String word){
      this.word = word;
   }
   /**
    It will call the private allSubsets method to find all subsets of the giving string.
    It will process the str to get the unique chars of str and their corresponding frequency.
    @param str a string that needs to be processed
    @retrun return an ArrayList of all subsets

    */
   public static ArrayList<String> getSubsets(String str){

      HashMap<Character,Integer> charMap = new HashMap<>();
      int[] multi;
      ArrayList<Integer> multiArrayList = new ArrayList<>();
      String unique = "";

      for(int i=0;i<str.length();i++){
         charMap.put(str.charAt(i),charMap.getOrDefault(str.charAt(i),0)+1); //record frequency for each char
      }
      for(Map.Entry<Character,Integer> pairs : charMap.entrySet()){
         unique = unique + pairs.getKey(); //get all the unique char i.e. keys
         multiArrayList.add(pairs.getValue());
      }
      multi = new int[multiArrayList.size()];
      for(int i=0;i<multiArrayList.size();i++){
         multi[i] = multiArrayList.get(i); //add frequency to multi array.
      }

      return allSubsets(unique,multi,ZERO);
   }

   public ArrayList<String> getSubsets(){
      return getSubsets(this.word);
   }

   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
      each subset is represented as a String that can have repeated characters in it.
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }

   
}
