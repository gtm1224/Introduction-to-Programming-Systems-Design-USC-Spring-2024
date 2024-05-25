//Name: Tianming Guo
//USC NetId: guotianm
//CSCI 455 PA1
//Spring 2024

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 The class has a main method which paint the spiral segments to a JFrame.

 */
public class SpiralViewer {
   // Define the width of JFrame equals to 500;
   public static final int WIDTH = 500;
   // Define the height of JFrame equals to 800;
   public static final int HEIGHT = 800;

   public static void main(String[] args)
   {

      // prompts the input for the length of initial segment。
      Scanner in = new Scanner(System.in);
      System.out.print("Enter length of initial segment: ");
      int initialSegment = in.nextInt();

      // Check the length of initial segment should be greater than 0,
      // Otherwise, the program will keep asking user for a correct value.
      while ( initialSegment <= SpiralGenerator.ZERO ){
         System.out.println("ERROR: value must be > 0");
         System.out.print("Enter length of initial segment: ");
         initialSegment = in.nextInt();
      }

      // prompts the input for the number of segments
      System.out.print("Enter number of segments: ");
      int numberOfSegments = in.nextInt();

      // Check the number of segments should be greater than 0,
      // Otherwise, the program will keep asking user for a correct value.
      while ( numberOfSegments <= SpiralGenerator.ZERO ){
         System.out.println("ERROR: value must be > 0");
         System.out.print("Enter number of segments: ");
         numberOfSegments = in.nextInt();
      }

      // Creating a JFrame for the segments. Set width, height and title for the JFrame.
      JFrame frame = new JFrame();
      frame.setSize(WIDTH, HEIGHT);
      frame.setTitle("Spiral Segments");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Draw the segments using SpiralComponent
      SpiralComponent component = new SpiralComponent(initialSegment, numberOfSegments);
      frame.add(component);
      frame.setVisible(true);

   }
}
