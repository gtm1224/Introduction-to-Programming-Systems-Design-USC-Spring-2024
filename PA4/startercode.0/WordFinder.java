// Name: Tianming Guo
// USC NetID: guotianm
// CS 455 PA4
// Spring 2024
import java.util.*;
/*
   This class contains the main method.
   This class will have a main that's responsible for processing the command-line argument,
   and handling any error processing. It also has the main command loop.It will initialize the AnagramDictionary from args or default dictionary.
   It will prompt user to input and read the user input or read from a file using Scanner class.
   It will also initialize a Rack from the input, and get the subsets and corresponding words in dictionary and also calculate
   the corresponding scores for each valid subset.

**/
public class WordFinder {
   //DOT will casue the program to stop and exit
   public static final String DOT = ".";
   // define the default Anagram Dictionary string i.e. the file name
   private static final String DEFAULTDICTIONARY = "sowpods.txt";
   // define the AnagramDictionary as static and global, so it can be accessed in the main
   private static AnagramDictionary dictionary;

   /*
   The main method.
   It will initialize the AnagramDictionary from args or default dictionary.
   It will prompt user to input and read the user input or read from a file using Scanner class.
   it will call userPrompt function to finish Rack initialization and print out the results.
   @param args it might contain a path for a customized dictionary, it can also be nothing.

**/

   public static void main(String[] args) {
      String dictionaryFile = DEFAULTDICTIONARY;

      boolean flag = false;
      if(args.length>0){
         dictionaryFile = args[0];
      }
      try{
         dictionary = new AnagramDictionary(dictionaryFile); //initialize the AnagramDictionary using the dictionaryFile
      }catch(Exception e){
         System.out.println("ERROR: " + e.getMessage());  // check for exceptions, then make flag true.
         System.out.println("Exiting program.");
         flag = true;
      }
      if(!flag){
         System.out.println("Type . to quit."); //if no error found during AnagramDictionary initialization, then call userPrompt
         userPrompt(dictionary);
      }


   }
   /*
  it will initialize a ScoreTable. It will read input in loop, processing the input using checkDictionary and call printScoresAndWords to print out the corresponding anagram dictionary words
  and scores.
  @param dictionary an AnagramDictionary object which has been successfully initialized.

**/
   private static void userPrompt(AnagramDictionary dictionary){
      Scanner in = new Scanner(System.in);
      ScoreTable scoreTable = new ScoreTable();

      while(in.hasNext()){
         System.out.print("Rack? "); //start of each Rack
         String word = in.next();
         if(word.equals(DOT) ){
            return;
         }
         TreeMap<Integer,ArrayList<String>> scoreAndWords = new TreeMap<>(Collections.reverseOrder()); //use TreeMap to set key in decreasing order
         int numWords = checkDictionary(word, dictionary, scoreAndWords, scoreTable); //we put the score and corresponding words in the TreeMap and also get the total number of words
         System.out.println("We can make " + numWords + " words from " + "\""+ word +"\"");
         if(numWords>0){
            System.out.println("All of the words with their scores (sorted by score):");
            printScoresAndWords(scoreAndWords);
         }
      }
   }

   /*
   it will initialize a Rack for each read in string. Then it will generate subsets of the input string. Then look for corresponding valid
   words from dictionary.put score and corresponding words into a TreeMap.
   @param word the input string
   @param dictionary the AnagramDictionary object that can check subsets for valid words
   @param scoreAndWords the TreeMap for scores and corresponding words
   @param scoreTable a ScoreTable that can calculate the score for each subset.
   @return return the total number of valid words

**/
   public static int checkDictionary(String word, AnagramDictionary dictionary, TreeMap<Integer,ArrayList<String>> scoreAndWords, ScoreTable scoreTable){
      int numWords = 0; //record for total number of words for each rack
      Rack rack = new Rack(word); // initialize a Rack for the current input
      ArrayList<String> subsets = rack.getSubsets(); //get all the subsets of the input

      for(String subset : subsets){
         ArrayList<String> subsetAnagram  = dictionary.getAnagramsOf(subset); //it is a deep copy of the anagram ArrayList
         if(subsetAnagram.size()>0){  //only add words if we can fina valid words from dictionary
            numWords += subsetAnagram.size();
            int score = scoreTable.getScoreOfStr(subset); //calculate the score of a subset
            if(scoreAndWords.containsKey(score)){ //if the score already in the TreeMap, get its value i.e. the corresponding ArrayList
               ArrayList<String> existingWords = scoreAndWords.get(score);
               for(String anagramWord : subsetAnagram){ //append the anagram word to the list.
                  existingWords.add(anagramWord);
               }
            }else{
               scoreAndWords.put(score,subsetAnagram); //if the score has no corresponding ArrayList, just add the subsetAnagram
            }
         }

      }
      return numWords;

   }
   /*
   The method will print out the score: word in the required order. i.e. sort the score in decreasing order and the corresponding
   ArrayList with words in alphabetical order.
   @param scoreAndWords a TreeMap that contains key as the score and value as the ArrayList with valid dictionary words that have
   the score as the key.

**/
   public static void printScoresAndWords(TreeMap<Integer,ArrayList<String>> scoreAndWords){

      Comparator<String> sortString = new Comparator<String>() {
         @Override
         public int compare(String o1, String o2) {
            return o1.compareTo(o2);
         } //override the compare function to sort the string in alphabetical order.
      };

      for(Map.Entry<Integer,ArrayList<String>> pair : scoreAndWords.entrySet()){
         int score = pair.getKey();
         ArrayList<String> scoreWords = pair.getValue();
         Collections.sort(scoreWords);// sort the ArrayList of words in alphabetical order.
         for(String word : scoreWords){
            System.out.println(score + ": " + word);
         }
      }
   }


}
