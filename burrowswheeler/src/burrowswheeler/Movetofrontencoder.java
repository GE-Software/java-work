/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burrowswheeler;

/**
 *
 * @author Lenovo-BM
 */
public  class Movetofrontencoder {

   public static  StringBuilder List;
   
   Movetofrontencoder()
   {
       List=new StringBuilder (256);
       for (int i=0; i<256 ; i++)
       List.append((char)i);
    }
    
   public int Retrieve(char c)
   {
        
      return  List.indexOf(Character.toString(c));
   }
   
   public void Move(char c)
   {
       int d = Retrieve(c);
       List=List.deleteCharAt(d);
       List=List.insert(0, c);
       
   }
   public StringBuilder returnsequence()    
   {
       return List;
   }
   
   
    
}
