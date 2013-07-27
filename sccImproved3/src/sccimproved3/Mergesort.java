/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sccimproved3;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import sun.security.util.Length;
import java.util.Random;
/**
 * This is an implementation of the merge sort algorithm for array of integer 
 * inputs. it takes into account the case of even and odd number of inputs.
 * the merge sort function takes three arguments : the array to be sorted , 
 * lower index and upper index of the array (nelements-1).
 * @author Wassim Bedwani
 */
public class Mergesort extends Thread{

    /**
     * @param args the command line arguments
     * no variables are needed for the implementation of the merge sort as
     * sort function is made public.
     */
   
    /**
     * the constructor is empty as there is no variables to be initialized
     * in the class
     * @param a 
     **/
    int[]A;
    int[] B;
    
    Mergesort (int[] a)
    {
      A=new int[a.length];
      A=a;
      B=new int[a.length];
    }
    
    /** 
     * the subroutine takes three variables : the integer array of elements to
     * be sorted , the start index of the items to be sorted and the end index
     * which is normally equal number of elements-1 and the function returns
     * an array of integers.
     * @param a the array to be sorted
     * @param startindex the start index of the items that need 
     * to be sorted in the array
     * @param endindex the index of the last item in the array that 
     * need to be sorted
     * @return returns an integer array of the size of the input.
     **/
     
    public void run()
    {
        
        B=mergesort(A,0,A.length-1);
    }
    public int[] mergesort(int[] a,int startindex, int endindex)
    {
        int[] left;
        int[] right;
        int middle;
        
        if ((endindex-startindex+1)<=1)
                return a ;
                
        else
        {
           if(((endindex-startindex+1) % 2) ==0) // to take into account the array with odd number of elements.
           {
            middle=(endindex-startindex+1)/2;
            left=new int[middle];
            right=new int[middle];
            
            for (int i=0;i<middle;i++)
            {
                left[i]=a[startindex+i];
                right[i]=a[startindex+middle+i];

            }
           }
           else
           {
                middle=(endindex-startindex+1)/2;
               left=new int[middle];
               right=new int[middle+1];
               for (int i=0;i<middle;i++)
                   left[i]=a[startindex+i];
               for (int i=0;i<middle+1;i++)
               right[i]=a[startindex+middle+i];
           }
        left=mergesort(left,0,left.length-1);
        right=mergesort(right,0,right.length-1);
        return(merge(left,right));
    }
    }
    
    /**
     * order O(n) of the merge taking two parameters: the array a and the
     * array b that need to be merged. the number of elements of the arrays are calculated by the routine.
     * @param a first input array to be merged
     * @param b second input array to be merged
     * @return 
     **/
    private int[] merge(int[]a,int[]b)
    {
        int leftlength=a.length;
        int rightlength=b.length;
        int[] result=new int[leftlength+rightlength];
        int resultindex=0, leftindex=0,rightindex=0;
        
        while((leftindex<leftlength) || (rightindex<rightlength))
        {
            if(leftindex<leftlength && rightindex<rightlength)
            {
                if(a[leftindex]<=b[rightindex])
                {
                    result[resultindex]=a[leftindex];
                    resultindex+=1;
                    leftindex+=1;
                }
                else
                    {
                    result[resultindex]=b[rightindex];
                    resultindex+=1;
                    rightindex+=1;
                    }
            }
            else if (leftindex<leftlength)
                {
                  int leftremaining=leftlength-leftindex;
                  for (int i=0;i<leftremaining;i++)
                  {
                      result[resultindex]=a[leftindex+i];
                      resultindex+=1;
                  }
                  leftindex=leftlength;
                }
            else
            {
                int rightremaining=rightlength-rightindex;
                for (int i=0;i<rightremaining;i++)
                  {
                      result[resultindex]=b[rightindex+i];
                      resultindex+=1;
                  }
                  rightindex=rightlength;
            }
            }
    
       return result;
   
    }
    public int[] returnsorted()
    {
        return B;
    }
    
    /**
     * this is the main area where the testing of the merge sort is being done.
     * @param args 
     */
    public static void main(String[] args) {
        // TODO code application logic here
       long sum=0,avg=0;
       
        
       int[] a;
       int []c;
       int nelements=200000;
       a=new int[nelements];
       c=new int[nelements];
       Random randomGenerator = new Random();
       for (int i=0;i<nelements;i++)
       {
            a[i]=randomGenerator.nextInt(nelements);
            c[i]=randomGenerator.nextInt(nelements);
       }
       Mergesort test=new Mergesort(a);
       Mergesort test2=new Mergesort(c);
       long start = System.currentTimeMillis();
       test.start();
       test2.start();
       int [] b=new int[nelements];
       int []d=new int[nelements];
       b=test.mergesort(a,0,a.length-1);
       d=test2.mergesort(c,0,c.length-1);
       //b=test.returnsorted();
       //d=test2.returnsorted();
       //b=test.mergesort(a,0,nelements-1);
       //d=test.mergesort(c,0,nelements-1);
       try{
       test.join();
       test2.join();
       }
       catch(InterruptedException e){}
       long end=System.currentTimeMillis();
       long time=end-start;
    }
}
