//Name: Tianming Guo
//USC NetId: guotianm
//CSCI 455 PA1
//Spring 2024


import java.awt.geom.Line2D;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
   class SpiralGenerator
   
   Generates a "rectangular" spiral, using Java display coordinate system, moving 
   outward from the center of the spiral in a counter-clockwise direction.
   That is, it will head rightward on screen, then, upward, then left, then down, etc.
  
   Length of initial line is unitLength.
   Padding between "layers" of the spiral is also unitLength.
   
   NOTE: we have provided the public interface for this class.  Do not change
   the public interface.  You can add private instance variables, constants, 
   and private methods to the class.  You will also be completing the 
   implementation of the methods given. 
   
 */
public class SpiralGenerator {
   // There are four directions: right, up, left, down.
   public static final int NUMBER_OF_DIRECTIONS = 4;
   // The length of segment will increase by unit length in every two segments.
   public static final int EVERY_TWO_TIMES= 2;
   // Define the four directions. For example, (1.0,0.0) vector represents a segment's direction
   // pointing to the left.
   public static final Point2D[] FOUR_DIRECTIONS ={
           new Point2D.Double(1.0, 0.0),
           new Point2D.Double(0.0, -1.0),
           new Point2D.Double(-1.0, 0.0),
           new Point2D.Double(0.0, 1.0)
   };
   // For each segment, make the INCREMENT = 1 for currentDirection. currentDirection refers to FOUR_DIRECTIONS.
   public static final int INCREMENT = 1;

   public static final int ZERO = 0;
   private Point2D startPosition;
   private int unitLength;
   private int currentDirection;
   private int counts;
   private int currentLength;

   /**
      Creates a SpiralGenerator starting at startPosition, with length of first segnment and 
      distance between "layers" both given as unitLength.  Both values are assuming the Java 
      graphics coordinate system.
      @param startPosition starting point of the first segment in the spiral
      @param unitLength in pixels, must be > 0
   */

   /**
    SpiralGenerator constructor which takes two variables
    @param startPosition a Point variable (x,y)
    @param unitLength the unit length which is also the length of the first segment,
    the latter segments will be an integer multiple of the unit length.
    */
   public SpiralGenerator(Point startPosition, int unitLength) {

      this.startPosition = new Point2D.Double((double) startPosition.getX(), (double) startPosition.getY());
      this.unitLength = unitLength;
      //currentDirection works like a pointer to the FOUR_DIRECTIONS, it is initialized to 0.
      this.currentDirection = ZERO;
      // Here the currentLength is our first segment which is the unitLength.
      this.currentLength = unitLength;
      // The currentLength will increase by one unitLength in every two segments,
      // Starts at -2 to take care of the initial case. So, we do not need to increment
      // currentLength at the beginning.
      this.counts = -EVERY_TWO_TIMES;
   }

   /**
      Return the coordinates of the next line segment in the spiral.
      
    */
   public Line2D nextSegment() {
      // calculate the next x coordinate of the next segment' endPosition
      double nextX = startPosition.getX() + FOUR_DIRECTIONS[currentDirection].getX() * currentLength;

      // calculate the next y coordinate of the next segment
      double nextY = startPosition.getY() + FOUR_DIRECTIONS[currentDirection].getY() * currentLength;

      // generate a Point2D as our segment's endPosition.
      Point2D endPosition = new Point2D.Double(nextX, nextY);
      Line2D nextLine = new Line2D.Double(startPosition ,endPosition);

      // For the next segment after this segment, the startPosition is current endPosition.
      startPosition = endPosition;

      // Prepare the direction in FOUR_DIRECTIONS for the next segment.
      currentDirection = (currentDirection + INCREMENT) % NUMBER_OF_DIRECTIONS;

      // Do a count for every two segments pair.
      counts  = (counts + INCREMENT) % EVERY_TWO_TIMES;

      // If counts equals to 0, i.e. the start of a new pair, increment the currentLength by a unitLength
      if (counts == ZERO){
         currentLength += unitLength;
      }
      return nextLine;

   }

}
