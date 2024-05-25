// Name: Tianming Guo
// USC NetID: guotianm
// CS 455 PA4
// Spring 2024
import java.io.IOException;

/**
   This class reports problems with the dictionary file.
 */
public class IllegalDictionaryException extends IOException {
   public IllegalDictionaryException() {}
   public IllegalDictionaryException(String message)
   {
      super(message);
   }
}
