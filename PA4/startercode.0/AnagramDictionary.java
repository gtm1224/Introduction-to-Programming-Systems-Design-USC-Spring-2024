// Name: Tianming Guo
// USC NetID: guotianm
// CS 455 PA4
// Spring 2024

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;


/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {

   //using a HashMap to store a sorted word and its corresponding valid anagrams
   private HashMap<String,ArrayList<String>> anagramDict;
   //Using a HashSet to store all the words in dictionary, checking for the duplicate.
   private HashSet<String> wordsSeen;
   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
      @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException,
                                                    IllegalDictionaryException {

      anagramDict = new HashMap<>();
      wordsSeen = new HashSet<>();
      File dictionary = new File(fileName);
      Scanner in = new Scanner(dictionary);
      while(in.hasNext()){
         String word = in.next();
         if(wordsSeen.contains(word)){
            in.close();
            throw new IllegalDictionaryException("Illegal dictionary: dictionary file has a duplicate word: " + word);
         }
         wordsSeen.add(word);
         String sortedWord = sortString(word);
         if(anagramDict.containsKey(sortedWord)){
            anagramDict.get(sortedWord).add(word);
         }else{
            ArrayList<String> anagrams = new ArrayList<>();
            anagrams.add(word);
            anagramDict.put(sortedWord,anagrams);
         }
      }
      in.close();

   }
   

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param str string to process
      @return a list of the anagrams of str (deep copy)
    */
   public ArrayList<String> getAnagramsOf(String str) {
      String sortedStr = sortString(str);
      if(anagramDict.containsKey(sortedStr)){
         return deepCopyArrayList(anagramDict.get(sortedStr));
      }
      return new ArrayList<>();
   }

   /**
       sort the string in alphabetical order.
       @param str string to process
       @return a sorted string from str
    */
   public String sortString(String str){
      char[] charArray = str.toCharArray();
      Arrays.sort(charArray);

      return new String(charArray);
   }

   /**
       sort the string in alphabetical order.
       @param original the original ArrayList that need to be copied (deep copy)
       @return a deep copy of the original ArrayList
    */

   private ArrayList<String> deepCopyArrayList(ArrayList<String> original){
      ArrayList<String> deepCopyArray = new ArrayList<>();
      for(String word : original){
         String addWord = word;
         deepCopyArray.add(addWord);
      }
      return deepCopyArray;
   }
   
}
