/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
import java.util.Random;
public final class Smath {

  public static final int IntRandom(int range)
  {
      
   
    Random randomGenerator = new Random();
   
    int randomInt = randomGenerator.nextInt(range);
    return randomInt;   
        
  }
 

  public static final int RangeRandom(int lower , int higher) throws IllegalArgumentException
  {
       
    if ( lower > higher ) {
      throw new IllegalArgumentException("Start cannot exceed End.");
    }
    //get the range, casting to long to avoid overflow problems
    long range = (long)higher - (long)lower + 1;
    // compute a fraction of the range, 0 <= frac < range
    Random randomGenerator = new Random();
    long fraction = (long)(range * randomGenerator.nextDouble());
    int randomNumber =  (int)(fraction + lower);    
   return randomNumber;
  }
  
  
}  

