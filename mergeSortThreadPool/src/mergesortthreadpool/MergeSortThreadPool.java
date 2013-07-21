/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortthreadpool;
import java.util.concurrent.ConcurrentLinkedQueue;
/**
 *
 * @author Lenovo-BM
 */


//----------------------------------------




    //------------------------------------------
public class MergeSortThreadPool {

private WorkerThread[] workers;
private ConcurrentLinkedQueue<Runnable> taskQueue;
private volatile int threadCompleted;
private volatile boolean running;
private int[] unsorted;
private int[]sorted;

MergeSortThreadPool(int[] a)
{
    int length=a.length;
    unsorted=new int[length];
    sorted=new int[length];
    unsorted=a;
}
public void start()
{
    taskQueue=new ConcurrentLinkedQueue<Runnable>();
    MergeSort[] tasks;
    tasks=new MergeSort[4];
    
       
        tasks[0]=new MergeSort(unsorted,0,unsorted.length/4-1);
        tasks[1]=new MergeSort(unsorted,unsorted.length/4,unsorted.length/2-1);
        tasks[2]=new MergeSort(unsorted,unsorted.length/2,unsorted.length*3/4-1);
        tasks[3]=new MergeSort(unsorted,unsorted.length*3/4,unsorted.length-1);
        
        for (int i=0; i<4;i++)
            taskQueue.add(tasks[i]);
        
        
        int threadCount=4;
        workers= new WorkerThread[threadCount];
        running=true;
        threadCompleted=0;
        for (int i = 0; i < threadCount; i++) {
         workers[i] = new WorkerThread();
         try {
            workers[i].setPriority( Thread.currentThread().getPriority() - 1 );
         }
         catch (Exception e) {
         }
         workers[i].start();
                 
      }
        for (int i=0; i<4 ; i++)
            try{
            workers[i].join();
            }
            catch(InterruptedException e){}
            MergeSort temp=new MergeSort(unsorted,0,unsorted.length-1);
            sorted=temp.merge(temp.merge(tasks[0].returnsorted(),tasks[1].returnsorted()),
                    temp.merge(tasks[2].returnsorted(),tasks[3].returnsorted()));
}

private void stop() {
      running = false;
   }
     synchronized private void threadFinished() {
      threadCompleted++;
     }    
  public int[] returnsorted()
  {
      return sorted;
  }
//---------------------------------------
 private class MergeSort implements Runnable
{
    int[]A;
    int[] B;
    
    MergeSort (int[] a, int start, int end)
    {
      A=new int[end-start+1];
      for (int i=0; i<(end-start+1);i++)
      A[i]=a[start+i];
      
      B=new int[end-start+1];
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
}
 //------------------------------
 private class WorkerThread extends Thread
 {
     public void run()
     {
         try{
             while(running)
             {
                 Runnable task=taskQueue.poll();
                 if (task==null)
                     break;
                 task.run();
             }
         }
         finally {
             threadFinished();
             }
         }
     }
 
//----------------------------------------------------
/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int [] a=new int [100];
        for (int i=0; i<100;i++)
            a[i]=100-i;
        
        MergeSortThreadPool t=new MergeSortThreadPool(a);
        t.start();
        a=t.returnsorted();
    }
}
