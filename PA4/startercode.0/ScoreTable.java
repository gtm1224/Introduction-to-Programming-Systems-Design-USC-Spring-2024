// Name: Tianming Guo
// USC NetID: guotianm
// CS 455 PA4
// Spring 2024

import java.util.HashMap;
/**
 This class has information about Scrabble scores for scrabble letters and words.
 In scrabble not every letter has the same value.
 Letters that occur more often in the English language are worth less (e.g., 'e' and 's' are each worth 1 point),
 and letters that occur less often are worth more (e.g., 'q' and 'z' are worth 10 points each). Here are all the letter values:
 (1 point)-A, E, I, O, U, L, N, S, T, R
 (2 points)-D, G
 (3 points)-B, C, M, P
 (4 points)-F, H, V, W, Y
 (5 points)-K
 (8 points)- J, X
 (10 points)-Q, Z
 This class should work for both upper and lower case versions of the letters, e.g., 'a' and 'A' will have the same score.
 */
public class ScoreTable {

   private char[] onePoint;
   private char[] twoPoint;
   private char[] threePoint;
   private char[] fourPoint;
   private char[] fivePoint;
   private char[] eightPoint;
   private char[] tenPoint;
   private HashMap<Character,Integer> scoreTable;
   private static final int ONE  = 1;
   private static final int TWO  = 2;
   private static final int THREE  = 3;
   private static final int FOUR  = 4;
   private static final int FIVE  = 5;
   private static final int EIGHT  = 8;
   private static final int TEN  = 10;
/**
 the constructor for ScoreTable. It will add all upper letters and lower letters to the table.
 * */
   public ScoreTable(){
      onePoint = new char[]{'A','E','I','O','U','L', 'N','S','T','R'};
      twoPoint = new char[]{'D','G'};
      threePoint = new char[]{'B','C','M','P'};
      fourPoint = new char[]{'F','H','V','W','Y'};
      fivePoint = new char[]{'K'};
      eightPoint = new char[]{'J','X'};
      tenPoint = new char[]{'Q','Z'};
      scoreTable = new HashMap<>();

      addScore(onePoint,ONE);
      addScore(twoPoint,TWO);
      addScore(threePoint,THREE);
      addScore(fourPoint, FOUR);
      addScore(fivePoint, FIVE);
      addScore(eightPoint, EIGHT);
      addScore(tenPoint, TEN);



   }
   /**
    It will add all upper letters and lower letters to the table.
    @param chars the letters need to be added
    @param score the corresponding score of the letters
    * */
   private void addScore(char[] chars, int score){
      for(char c : chars){
         scoreTable.put(c,score);
         scoreTable.put(Character.toLowerCase(c),score);
      }
   }
   /**
    It will calculate the total score of a string
    @param str a string need to be processed.
    @return the total score of the string.
     * */
   public int getScoreOfStr(String str){
      int totalScore = 0;

      for (int i = 0; i < str.length(); i++) {
         char curChar = str.charAt(i);
         if(scoreTable.containsKey(curChar)){
            totalScore += scoreTable.get(curChar);
         }

      }
      return totalScore;


   }
   
}
