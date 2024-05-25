//Name: Tianming Guo
//USC NetId: guotianm
//CSCI 455 PA1
//Spring 2024

import javax.swing.*;
import java.awt.*;

/**
 This Spiralcomponent class draws spiral segments. it extends JComponent class.

 */

public class SpiralComponent  extends JComponent {
   //
   public static final int TWO = 2;
   private int initialSegment;
   private int numberOfSegment;
   private Point startPosition;

   /**
    Constructs a car with a given top left corner.
    @param initialSegment the united length of the initial segment
    @param numberOfSegments the number of segments to draw
    */
   public SpiralComponent(int initialSegment, int numberOfSegments) {
      this.initialSegment = initialSegment;
      this.numberOfSegment = numberOfSegments;

   }
   /**
    Draw the segments. This class will always detect width and height of the frame.
    And set the instance variable startPosition to middle of the frame.
    @param g is a Graphics instance

    */
   public void paintComponent(Graphics g){

      Graphics2D g2 = (Graphics2D) g;
      // set the startPosition in the middle of a JFrame by finding the half width and half height of the JFrame
      this.startPosition = new Point( getWidth()/TWO, getHeight()/TWO);
      SpiralGenerator newSegment = new SpiralGenerator(startPosition, initialSegment);

      // Generate the 'numberOfSegment' number of segments. And draw them using Graphics2D method.
      for (int i = 0; i < numberOfSegment; i++) {
         g2.draw(newSegment.nextSegment());
      }

   }

}
