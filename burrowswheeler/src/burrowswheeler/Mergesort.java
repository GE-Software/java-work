/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burrowswheeler;

import sun.security.util.Length;
import java.util.Random;
/**
 * This is an implementation of the merge sort algorithm for array of integer 
 * inputs. it takes into account the case of even and odd number of inputs.
 * the merge sort function takes three arguments : the array to be sorted , 
 * lower index and upper index of the array (nelements-1).
 * @author Wassim Bedwani
 */
public class Mergesort {

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
    
    Mergesort ()
    {
      
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
     
    public StringBuilder[] mergesort(StringBuilder[] a,int startindex, int endindex)
    {
        StringBuilder[] left;
        StringBuilder[] right;
        int middle;
        
        if ((endindex-startindex+1)<=1)
                return a ;
                
        else
        {
           if(((endindex-startindex+1) % 2) ==0) // to take into account the array with odd number of elements.
           {
            middle=(endindex-startindex+1)/2;
            left=new StringBuilder[middle];
            right=new StringBuilder[middle];
            
            for (int i=0;i<middle;i++)
            {
                left[i]=a[startindex+i];
                right[i]=a[startindex+middle+i];

            }
           }
           else
           {
                middle=(endindex-startindex+1)/2;
               left=new StringBuilder[middle];
               right=new StringBuilder[middle+1];
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
    private StringBuilder[] merge(StringBuilder[]a,StringBuilder[]b)
    {
        int leftlength=a.length;
        int rightlength=b.length;
        StringBuilder[] result=new StringBuilder[leftlength+rightlength];
        int resultindex=0, leftindex=0,rightindex=0;
        
        while((leftindex<leftlength) || (rightindex<rightlength))
        {
            if(leftindex<leftlength && rightindex<rightlength)
            {
                if(a[leftindex].toString().compareTo(b[rightindex].toString())<0)
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
    
}
