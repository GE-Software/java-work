/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquerandom;

import java.util.Random;
import java.util.Hashtable;

/**
 *
 * @author Lenovo-BM
 */
public class Uniquerandom {

    int[] A;
    Hashtable<Integer,Integer> h=new Hashtable <Integer, Integer>();;
    
    public int[] Generate(Integer num,Integer min, Integer max)
    {
        
        A= new int[num];
        
        Random randomGenerator = new Random();
        if (max>=num)
        {    
        for (int i=0;i<num;i++)
        {
        Integer n=min+randomGenerator.nextInt(max);
        while (h.contains(n)==true)
            n= min+randomGenerator.nextInt(max);
        
        A[i]=n;
        h.put(i,n);
        }
        
        }
        return A;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
   Uniquerandom r=new Uniquerandom();
   r.Generate(50000,0,75000);
    Quicksort q =new Quicksort(50000);  
    q.A=r.A;
    q.quicksort(0, 50000-1);
   
    }
}
