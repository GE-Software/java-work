/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burrowswheeler;

/**
 *
 * @author Lenovo-BM
 */
public class Movetofrontdecoder {

   public static  StringBuilder List;
   
   Movetofrontdecoder()
   {
       List=new StringBuilder (256);
       for (int i=0; i<256 ; i++)
       List.append((char)i);
    }
    
   public char Retrieve(int index)
   {
       return List.charAt(index);
   }
   public void move(int index)
   {
       char c=Retrieve(index);
       List.deleteCharAt(index);
       List.insert(0, c);
   }
    
    
    
}
