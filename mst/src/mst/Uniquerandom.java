/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mst;

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
   
}
