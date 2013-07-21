/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Random;

/**
 *
 * @author Lenovo-BM
 */
public class Quicksort {

    int[] A;
    Quicksort(int size)
    {
        A=new int[size];
    }
    
    private int decomposition(int lowerindex,int upperindex)
    {
        int i,temp;
        i=lowerindex;
        for (int index=lowerindex+1 ;index<=upperindex;index++)
            if (A[index]<A[lowerindex])
            {
                i=i+1;
                temp=A[i];
                A[i]=A[index];
                A[index]=temp;
            }
        temp=A[i];
        A[i]=A[lowerindex];
        A[lowerindex]=temp;
        return i;
            
    }
    private void choosepivot(int lowerindex, int upperindex)
    {
         Random randomGenerator = new Random();
         int pivotindex=lowerindex+randomGenerator.nextInt(upperindex-lowerindex);
         int temp=A[pivotindex];
         A[pivotindex]=A[lowerindex];
         A[lowerindex]=temp;
    }
    public void quicksort(int lowerindex, int upperindex)
    {
        int pivotindex;
        // need to throw exception if either the indices are out of bound
        if ((lowerindex<0) || (upperindex>A.length-1))
            throw new IndexOutOfBoundsException("indices are out of bound");
        
        if ((upperindex-lowerindex)>0)
        {
            choosepivot(lowerindex,upperindex);
            pivotindex=decomposition(lowerindex,upperindex);
            quicksort(lowerindex,pivotindex-1);
            quicksort(pivotindex+1,upperindex);
        }
    }
    
    
    
    
    /**
     * @param args the command line arguments
     */
   
}
