/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package movetofrontdecoder;

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
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String test="ab      c    ";
        String output="";
        Movetofrontdecoder t=new Movetofrontdecoder();
        for (int i=0;i<test.length();i++)
        {
            int c=(int)test.charAt(i);
            output+= t.Retrieve(c)+" ";
            t.move(c);
            
        }
    }
}
