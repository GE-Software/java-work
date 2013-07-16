/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package movetofrontencoder;

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
   
   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    String Teststring="abbbaabbbbaccabbaaabc";
    String output="";
    int length=Teststring.length();
    Movetofrontencoder t= new Movetofrontencoder();
    for(int i=0;i<length;i++)
    {
        char c=Teststring.charAt(i);
        output+=(char)t.Retrieve(c);
        t.Move(c);
    }
        
    
    StringBuilder temp=t.returnsequence();
    
    }
}
