//Name: Tianming Guo
//USC NetId: guotianm
//CSCI 455 PA1
//Spring 2024

import javax.sound.sampled.Line;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 class SpiralGeneratorTester

 The class tests SpiralGenerator class including connection between to segments,
 perpendicularity between two segments. And check the segment is horizontal or vertical.

 The class has a main and several static testing methods to make automated testing.
 Users only need to use SpiralGenerator class to generate a test instance, then pass
 the testing instance and the number of segments needed to AutomatedTesting
 method. The AutomatedTesting will print out the testing results.

 */

public class SpiralGeneratorTester {

   /**
    main method will do automated testing for SpiralGenerator.
    Users only need to use SpiralGenerator class to generate a test instance, then pass
    the testing instance and the number of segments needed to AutomatedTesting
    method. The AutomatedTesting will print out the testing results.
    */
   public static void main(String[] args) {
      // Start test 1
      System.out.println("Making a spiral starting from java.awt.Point[x=200,y=300]");
      System.out.println("with a unit length of 5, and made up of 10 segments:");
      // Generate a SpiralGenerator with starting position = [200,300] and unit length = 5
      SpiralGenerator testOne = new SpiralGenerator(new Point(200,300),5);
      // Pass the SpiralGenerator and the number of segments to automatedTesting method
      automatedTesting(testOne,10);

      // Start test 2
      System.out.println();
      System.out.println("Making a spiral starting from java.awt.Point[x=300,y=400]");
      System.out.println("with a unit length of 15, and made up of 200 segments:");
      // Generate a SpiralGenerator with starting position = [300,400] and unit length = 15
      SpiralGenerator testTwo = new SpiralGenerator(new Point(300,400),15);
      // Pass the SpiralGenerator and the number of segments to automatedTesting method
      automatedTesting(testTwo,200);

      // Start test 3
      System.out.println();
      System.out.println("Making a spiral starting from java.awt.Point[x=0,y=0]");
      System.out.println("with a unit length of 100, and made up of 20 segments:");
      // Generate a SpiralGenerator with starting position = [0,0] and unit length = 100
      SpiralGenerator testThree = new SpiralGenerator(new Point(0,0),100);
      // Pass the SpiralGenerator and the number of segments to automatedTesting method
      automatedTesting(testThree,20);


   }
   /**
    prints out the segment information. It starts from a Point2D.Double [x1,y1] to another Point2D.Double
    [x2,y2]
    @param currentSegment a Line2D variable
    */
   public static void printPoint2DInfo(Line2D currentSegment){
      Point2D segmentOneFrom = new Point2D.Double(currentSegment.getX1(),currentSegment.getY1());
      Point2D segmentOneTo = new Point2D.Double(currentSegment.getX2(),currentSegment.getY2());
      System.out.print(segmentOneFrom + " " + segmentOneTo);
   }
   /**
    check the segment that passed to the method is horizontal or not.
    @param segment a Line2D variable
    */
   public static boolean checkHorizontal(Line2D segment){

      return (segment.getY1() == segment.getY2());
   }

   /**
    check the segment that passed to the method is vertical or not.
    @param segment a Line2D variable
    */
   public static boolean checkVertical(Line2D segment){

      return (segment.getX1() == segment.getX2());
   }

   /**
    check the two segments l1 and l2 are connected or not.
    @param l1 a Line2D variable
    @param l2 a Line2D variable
    */
   public static boolean checkConnectivity(Line2D l1, Line2D l2){
      double l1X2 = l1.getX2();
      double l1Y2 = l1.getY2();
      double l2X1 = l2.getX1();
      double l2Y1 = l2.getY1();

      return (l1X2 == l2X1 && l1Y2 == l2Y1);
   }
   /**
    check the two segments l1 and l2 are perpendicular or not using vector multiplication.
    @param l1 a Line2D variable
    @param l2 a Line2D variable
    */
   public static boolean checkPerpendicular(Line2D l1, Line2D l2){
      double vectorOneX = l1.getX2() - l1.getX1();
      double vectorOneY = l1.getY2() - l1.getY1();
      double vectorTwoX = l2.getX2() - l2.getX1();
      double vectorTwoY = l2.getY2() - l2.getY1();

      return (vectorOneX * vectorTwoX + vectorOneY * vectorTwoY ) == 0.0;
   }
   /**
    automatedTesting combines all the tests method defined before to test a SpiralGenerator variable
    and its segments.
    @param testingObject a SpiralGenerator variable
    @param numOfSegments the number of segments that the SpiralGenerator variable need to generate.
    */
   public static void automatedTesting (SpiralGenerator testingObject, int numOfSegments){
      // Initialize the previous segment to null.
      Line2D previousSegment = null;
      int counter= 0;
      // Make failedHorizontalOrVertical to false

      while (counter < numOfSegments) {
         Line2D currentSegment = testingObject.nextSegment();
         System.out.print("Segment #"+(counter+1)+": ");
         printPoint2DInfo(currentSegment);
         System.out.println();
         // when counter is even, i.e. 0th,second,fourth...segment (start with 0th)
         // we need to check if the segment is horizontal
         if(counter % 2 == 0 ){
            if(!checkHorizontal(currentSegment)){

               System.out.println("FAILED: segment is not horizontal or vertical.  Skipping pair tests.");

            }
            // when counter is odd, i.e first,3rd,5th..segment (starts with 0th)
            // we need to check if it is vertical.
         }else{
            if(!checkVertical(currentSegment)){

               System.out.println("FAILED: segment is not horizontal or vertical.  Skipping pair tests.");
            }
         }
         // Skip the 0th segment for connectivity check. We starts the check from the segment.
         if(counter >= 1){

            if (!checkConnectivity(previousSegment,currentSegment)){

               System.out.println("FAILED: last two segments are not connected");

            }

            if (!checkPerpendicular(previousSegment,currentSegment)){

               System.out.println("FAILED: last two segments are not perpendicular");
            }

         }
         counter+=1;
         //set previous segment equals to current segment
         previousSegment = new Line2D.Double(currentSegment.getX1(),currentSegment.getY1(),currentSegment.getX2(),currentSegment.getY2());

      }

   }

}
