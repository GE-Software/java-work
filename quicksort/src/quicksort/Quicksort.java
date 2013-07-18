/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quicksort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Lenovo-BM
 */
public class Quicksort {

    int[] A;
    int cc;
    Quicksort(int size)
    {
        A=new int[size];
        cc=0;
    }
    
    private int decomposition(int lowerindex,int upperindex)
    {
        int i,temp;
        i=lowerindex+1;
        for (int index=lowerindex+1 ;index<=upperindex;index++)
            if (A[index]<A[lowerindex])
            {
                
                temp=A[i];
                A[i]=A[index];
                A[index]=temp;
                i=i+1;
            }
        temp=A[i-1];
        A[i-1]=A[lowerindex];
        A[lowerindex]=temp;
        return i-1;
            
    }
    private void choosepivot(int lowerindex, int upperindex)
    {
         Random randomGenerator = new Random();
         int pivotindex=lowerindex+randomGenerator.nextInt(upperindex-lowerindex);
         int temp=A[pivotindex];
         A[pivotindex]=A[lowerindex];
         A[lowerindex]=temp;
    }
    private void choosepivotfinal(int lowerindex, int upperindex)
    {
         //Random randomGenerator = new Random();
         //int pivotindex=lowerindex+randomGenerator.nextInt(upperindex-lowerindex);
        int pivotindex=upperindex; 
        int temp=A[pivotindex];
         A[pivotindex]=A[lowerindex];
         A[lowerindex]=temp;
    }
    
    private void choosepivotmedian(int lowerindex, int upperindex)
    {
         
       int temp,middleindex,pivotindex=0;
       int[] B=new int[3];
          
        if ((upperindex-lowerindex+1)%2==0)
        {
           // b[1]=A[(upperindex-lowerindex+1)/2];
            middleindex=(upperindex-lowerindex)/2+lowerindex;
        }
        else{
            //b[1]=(upperindex-lowerindex+1)%2+1;
            middleindex=(upperindex-lowerindex)/2+lowerindex;
        }
        B[0]=A[lowerindex];
        B[1]=A[middleindex];
        B[2]=A[upperindex];
        for (int i=0; i<2; i++)
            for (int j=1;j<3-i;j++)
                if (B[j-1]>B[j])
                {
                    temp=B[j-1];
                    B[j-1]=B[j];
                    B[j]=temp;
                }
        
      
        if (B[1]==A[lowerindex])
            pivotindex=lowerindex;
        else if (B[1]==A[middleindex])
            pivotindex=middleindex;
        else
            pivotindex=upperindex;
        
        /**if (A[lowerindex]>A[middleindex]&& A[lowerindex]<A[upperindex])
            pivotindex=lowerindex;
        else if (A[lowerindex]>A[middleindex]&& A[lowerindex]>A[upperindex]&& A[middleindex]>A[upperindex])
            pivotindex=middleindex;
        else if (A[lowerindex]>A[middleindex]&& A[lowerindex]>A[upperindex]&& A[middleindex]<A[upperindex])
            pivotindex=upperindex;
        else if (A[lowerindex]<A[middleindex]&& A[lowerindex]<A[upperindex]&& A[middleindex]<A[upperindex])
            pivotindex=middleindex;
        else if (A[lowerindex]<A[middleindex]&& A[lowerindex]<A[upperindex]&& A[middleindex]>A[upperindex])
            pivotindex=upperindex;
       */
            
       /**if (A[pivotindex]<A[lowerindex]&& A[pivotindex]<A[upperindex]&& A[lowerindex]<A[upperindex])
           pivotindex=lowerindex;
       else if (A[pivotindex]<A[lowerindex]&& A[pivotindex]<A[upperindex]&& A[lowerindex]>A[upperindex])
           pivotindex=upperindex;
       else if (A[pivotindex]<A[lowerindex]&&A[pivotindex]>A[upperindex])
           pivotindex=pivotindex;
       else if (A[pivotindex]>A[lowerindex]&& A[pivotindex]>A[upperindex]&& A[lowerindex]>A[upperindex])
           pivotindex=lowerindex;
       else if (A[pivotindex]>A[lowerindex]&& A[pivotindex]>A[upperindex]&& A[lowerindex]<A[upperindex])
           pivotindex=upperindex;
       else if(A[pivotindex]>A[lowerindex]&& A[pivotindex]<A[upperindex])
           pivotindex=pivotindex;
*/
         temp=A[pivotindex];
         A[pivotindex]=A[lowerindex];
         A[lowerindex]=temp;
    }
    
    public void quicksort(int lowerindex, int upperindex)
    {
        int pivotindex ;
        int t1=0,t2=0;
        // need to throw exception if either the indices are out of bound
        if ((lowerindex<0) || (upperindex>A.length-1))
            throw new IndexOutOfBoundsException("indices are out of bound");
        
        if ((upperindex-lowerindex)>0)
        {
            //choosepivot(lowerindex,upperindex);
            //choosepivotfinal(lowerindex,upperindex);
            choosepivotmedian(lowerindex,upperindex);
            pivotindex=decomposition(lowerindex,upperindex);
            if (upperindex>lowerindex)
                cc=cc+upperindex-lowerindex;
            quicksort(lowerindex,pivotindex-1);
            
            quicksort(pivotindex+1,upperindex);
            
            
         
        }
    
    }
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
   Quicksort t=new Quicksort(10000);
   File file=new File("c:\\QuickSort.txt");
   BufferedReader bf=new BufferedReader(new FileReader(file));
        for (int i=0; i<10000;i++) {
            t.A[i]=Integer.parseInt(bf.readLine());
        }
               
    t.quicksort(0, 10000-1);
    System.out.println(t.cc);
    }
}
