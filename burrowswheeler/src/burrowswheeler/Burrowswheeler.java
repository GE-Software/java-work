/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burrowswheeler;

/**
 *
 * @author Lenovo-BM
 */
public class Burrowswheeler {

    /**
     * @param args the command line arguments
     */
    StringBuilder Input;
    StringBuilder[] originalsuffixes;
    StringBuilder[] sortedsuffixes;
    StringBuilder BWstring;
    int[] next;
    Movetofrontencoder mtfencoder;
    
    Burrowswheeler( StringBuilder I)
    {
        int length=I.length();
        Input=I;
        next=new int[length];
        originalsuffixes=new StringBuilder[length];
        sortedsuffixes=new StringBuilder[length];
        BWstring=new StringBuilder();
        for (int i=0; i<length;i++)
        {
            originalsuffixes[i]=new StringBuilder();
            sortedsuffixes[i]=new StringBuilder();
        }
        originalsuffixes[0].append(I.toString());
        mtfencoder=new Movetofrontencoder();
     }
    
    private void createoriginal()
    {
        for (int i=1; i<Input.length();i++)
            shift(originalsuffixes[i-1],i);
        
       
    }
    
    private StringBuilder shift(StringBuilder t,int i)
    {
        char temp=t.charAt(0);
        originalsuffixes[i].append(t);
        originalsuffixes[i].deleteCharAt(0);
        return originalsuffixes[i].append(temp);
        
    }
    private void sort()
    {
        Mergesort t= new Mergesort();
        sortedsuffixes=t.mergesort(originalsuffixes,0,Input.length()-1);
    }
    private void createBWstring()
    {
        for (int i=0;i<Input.length();i++)
            BWstring.append(sortedsuffixes[i].charAt(Input.length()-1));
    }
    private void calculatenext()
    {
        StringBuilder temp;
        String build;
        char temp1;
        for (int i=0;i<Input.length();i++)
        {
            build=sortedsuffixes[i].toString();
            temp=new StringBuilder();
            temp.append(build);
            temp1=temp.charAt(0);
            temp.deleteCharAt(0);
            temp.append(temp1);
            for (int j=0; j<Input.length();j++)
                if (i!=j)
                    if (temp.toString().equals(sortedsuffixes[j].toString()))
                        next[i]=j;
        }
    }
    
    public StringBuilder simulate()
    {
        createoriginal();
        sort();
        createBWstring();
        calculatenext();
        
        //move to front
        for (int i=0; i<Input.length();i++)
            mtfencoder.Move(BWstring.charAt(i));
        
        StringBuilder temp=mtfencoder.returnsequence();
        
        
        // huffman encoding
        
        
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    StringBuilder input=new StringBuilder();
    input.append("abracadabra");
    Burrowswheeler bw=new Burrowswheeler(input);
    StringBuilder output=bw.simulate();
    }
}
