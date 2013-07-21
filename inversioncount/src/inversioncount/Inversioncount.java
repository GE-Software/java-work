/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inversioncount;
import java.util.Scanner;
import java.io.File;
/**
 *
 * @author Lenovo-BM
 */
import java.util.Random;

public class Inversioncount {

    
    public long Countinversionsort(int[]a, int startindex, int endindex)
    {
        int [] left;
        int [] right;
        int middle;
        Mergesort m=new Mergesort();
        if (a.length==1)
            return 0;
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
        long leftcount , rightcount;
        leftcount=Countinversionsort(left,0,left.length-1);
        rightcount=Countinversionsort(right,0,right.length-1);
        left=m.mergesort(left,0,left.length-1);
        right=m.mergesort(right,0,right.length-1);
        Inversioncountmatrix split= new Inversioncountmatrix(left.length+right.length);
        split=countmergesort(left,right);
        return leftcount+rightcount+split.Count;
        
           
        }
    }
    
    public Inversioncountmatrix countmergesort(int[]a, int[]b)
    {
        int leftlength=a.length;
        int rightlength=b.length;
        
        Inversioncountmatrix S=new Inversioncountmatrix(leftlength+rightlength);
        int resultindex=0, leftindex=0, rightindex=0;
    
         while((leftindex<leftlength) || (rightindex<rightlength))
        {
            if(leftindex<leftlength && rightindex<rightlength)
            {
                if(a[leftindex]<=b[rightindex])
                {
                    S.IC[resultindex]=a[leftindex];
                    resultindex+=1;
                    leftindex+=1;
                }
                else
                    {
                    S.IC[resultindex]=b[rightindex];
                    S.Count=S.Count+leftlength-leftindex;
                    resultindex+=1;
                    rightindex+=1;
                    }
            }
            else if (leftindex<leftlength)
                {
                  int leftremaining=leftlength-leftindex;
                  for (int i=0;i<leftremaining;i++)
                  {
                      S.IC[resultindex]=a[leftindex+i];
                      resultindex+=1;
                  }
                  leftindex=leftlength;
                }
            else
            {
                int rightremaining=rightlength-rightindex;
                for (int i=0;i<rightremaining;i++)
                  {
                      S.IC[resultindex]=b[rightindex+i];
                      resultindex+=1;
                  }
                  rightindex=rightlength;
            }
        }
    
       return S;
   
    
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    Random randomgenerator=new Random();
    Inversioncount s=new Inversioncount();
    int[] a=new int[100000];

    try
    {Scanner scanner = new Scanner(new File("c:\\inversion.txt"));
        int i = 0;
        while(scanner.hasNextInt()){
            a[i] = scanner.nextInt();
            i=i+1;
    }
    }
    catch(Exception e)
    {
    
    }
    
    
    
    
    
    long temp=s.Countinversionsort(a, 0,100000-1);
   
    
    }
}
