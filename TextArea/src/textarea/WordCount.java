/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package textarea;
import java.util.Hashtable;
/**
 *
 * @author Lenovo-BM
 */
public class WordCount {

    Hashtable<Integer, Integer> delimiters= new Hashtable<Integer, Integer>();    
    
    Hashtable<String,Integer> words=new Hashtable<String,Integer>();
    String original=new String();
    
    public WordCount(String input)
    {
        delimiters.put((int)' ', 0);
        delimiters.put((int)'.', 0);
        delimiters.put((int)'!', 0);
        delimiters.put((int)';', 0);
        delimiters.put((int)',', 0);
        delimiters.put((int)'/', 0);
        delimiters.put((int)'~', 0);
        delimiters.put((int)'@', 0);
        delimiters.put((int)'#', 0);
        delimiters.put((int)'$', 0);
        delimiters.put((int)'%', 0);
        delimiters.put((int)'^', 0);
        delimiters.put((int)'*', 0);
        delimiters.put((int)'(', 0);
        delimiters.put((int)')', 0);
        delimiters.put((int)'-', 0);
        delimiters.put((int)'_', 0);
        delimiters.put((int)'=', 0);
        delimiters.put((int)'<', 0);
        delimiters.put((int)'>', 0);
        delimiters.put((int)'{', 0);
        delimiters.put((int)'}', 0);
        delimiters.put((int)'1', 0);
        delimiters.put((int)'2', 0);
        delimiters.put((int)'3', 0);
        delimiters.put((int)'4', 0);
        delimiters.put((int)'5', 0);
        delimiters.put((int)'6', 0);
        delimiters.put((int)'7', 0);
        delimiters.put((int)'8', 0);
        delimiters.put((int)'9', 0);
        delimiters.put((int)'0', 0);
        delimiters.put((int)'`', 0);
        delimiters.put((int)'\\', 0);
        delimiters.put((int)':', 0);
        delimiters.put((int)'/', 0);
        delimiters.put((int)'|', 0);
        delimiters.put((int)']', 0);
        delimiters.put((int)'[', 0);
        delimiters.put((int)'&',0);
        
        original=input;
    }
    public void countWords()
    {
        String word=new String();
        String lowercase=new String();
        
        int count=0;
        lowercase=original;
        lowercase.toLowerCase();
        
        
        word="";
        for (int i=0; i<lowercase.length();i++)
        {
           
            
            while(delimiters.containsKey((int)lowercase.charAt(i))!=true )
            {
                word=word+lowercase.charAt(i);
                if (i+1<lowercase.length())
                        i=i+1;
                else break;
            }
            if (word!="")
                if (words.containsKey(word.toString())==false)
                {
                    words.put(word.toString(), 1);
                    word="";
                }
                else
                {
                    count=words.get(word.toString());
                    count+=1;
                    words.remove(word.toString());
                    words.put(word.toString(),count);
                    word="";
                }
            else{}
                
        }
    
    }
    
    public int returnWords()
    {
        return words.size();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    
    String in=new String();
    in="this is a rather! long text to see if it is working correctly!@#$%^this is rubbish but useful (*&^%%% for testing";
    WordCount wc=new WordCount(in);
    
      

    
    wc.countWords();
    
    
    }
}
