/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twosum;

import java.util.Hashtable;
import java.util.Random;

/**
 *
 * @author Lenovo-BM
 */
public class Twosum {

    int [] A;
    Hashtable<Integer,Integer> numbers;
    
    Twosum(int nelements)
    {
        Random randomGenerator = new Random();
        A=new int[nelements];
        Mergesort sorted=new Mergesort();
        numbers=new Hashtable(3*nelements);
        for (int i=0;i<nelements;i++)
            
            A[i]=randomGenerator.nextInt(2*nelements);
           
           A=sorted.mergesort(A,0,nelements-1);
           for (int i=0;i<nelements;i++)
               numbers.put(i,A[i]);
           
    }
    public int calculate(int sum)
    {
        int counter=0;
        int remaining;
        for (int i=0;i<A.length;i++)
        {
            remaining=sum-A[i];
            if(numbers.contains(remaining)==true)
                counter=counter+1;
            numbers.remove(i);
        }
    return counter;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    
   long starttime=System.nanoTime();
    Twosum t=new Twosum(50000);
    int result=t.calculate(100000);
    long endtime=System.nanoTime();
    double Difference=(endtime-starttime)/1e9;
    }
}
