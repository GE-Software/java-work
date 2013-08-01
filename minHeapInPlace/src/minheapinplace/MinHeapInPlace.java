/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minheapinplace;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

// this is implementation of min Heap , with deleteByValue added to be used in 
//dijkstra algorithm.

/**
 *
 * @author Lenovo-BM
 */
public class MinHeapInPlace {

    private int[] heap;
    private int[] next; 
    private int[] previous;
    private int lastindex=-1;
    private int first,last;
    final int inf=1000000;
    MinHeapInPlace(int size)
    {
         heap=new int[size];
         next=new int[size];
         previous=new int[size];
         first=-1;
         last=-1;
     }
     
    public void insert(int[]  element)
     {
         int parentindex, tempIndex;
         int temp;
         for (int i=0;i<element.length;i++)
         {
            lastindex+=1;
            heap[lastindex]=element[0];
         }
         tempIndex=lastindex;
         while (tempIndex !=0)
         {
             parentindex=(tempIndex-1)/2;
             if (heap[tempIndex]<heap[parentindex])
             {
                 temp=heap[parentindex];
                 heap[parentindex]=heap[tempIndex];
                 heap[tempIndex]=temp;
                 tempIndex=parentindex;
              }
             else
             {
                tempIndex=0;
            }
         }
     }
    
    public boolean isempty()
    {
        if(lastindex==-1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public int deletemin()
    {
        int value=-1, tempindex,temp;
        if (!isempty())
        {
            
            value=heap[0];
            tempindex=0;
            heap[0]=heap[lastindex];
            lastindex-=1;
            boolean complete=false;
            
            while (complete==false && (2*tempindex+1<=lastindex))
            {        
                        if (2*tempindex+2>lastindex) 
                            
                           if (heap[2*tempindex+1]<=heap[tempindex])
                                {
                                    temp=heap[tempindex];
                                    heap[tempindex]=heap[2*tempindex+1];
                                    heap[2*tempindex+1]=temp;
                                    tempindex=2*tempindex+1;
                                }
                                    else
                                        complete=true;
                        else
                            if (heap[2*tempindex+2]<=heap[2*tempindex+1])
                            
                                if (heap[2*tempindex+2]<=heap[tempindex])
                                {
                                    temp=heap[tempindex];
                                    heap[tempindex]=heap[2*tempindex+2];
                                    heap[2*tempindex+2]=temp;
                                    tempindex=2*tempindex+2;
                                }
                                else
                                    complete=true;
                        
                            else if (heap[2*tempindex+1]<=heap[tempindex])
                                    {
                                        temp=heap[tempindex];
                                        heap[tempindex]=heap[2*tempindex+1];
                                        heap[2*tempindex+1]=temp;
                                        tempindex=2*tempindex+1;
                               
                                    }
                                else
                                        complete=true;
                    }
        }

            
            
            
            return value;
    }
    
    public void deleteByValue(int value)
    {
        int tvalue=-1, tempindex=0,temp=0;
        
        for (int i=0; i<=lastindex;i++)
        
            if (heap[i]==value)
                    {
                    tempindex=i;
                    break;
                    }
        
        if (!isempty())
        {
            
            heap[tempindex]=heap[lastindex];
            lastindex-=1;
            
            boolean complete=false;
            
            while (complete==false && (2*tempindex+1<=lastindex))
            {        
                        if (2*tempindex+2>lastindex) 
                            
                           if (heap[2*tempindex+1]<=heap[tempindex])
                                {
                                    temp=heap[tempindex];
                                    heap[tempindex]=heap[2*tempindex+1];
                                    heap[2*tempindex+1]=temp;
                                    tempindex=2*tempindex+1;
                                }
                                    else
                                        complete=true;
                        else
                            if (heap[2*tempindex+2]<=heap[2*tempindex+1])
                            
                                if (heap[2*tempindex+2]<=heap[tempindex])
                                {
                                    temp=heap[tempindex];
                                    heap[tempindex]=heap[2*tempindex+2];
                                    heap[2*tempindex+2]=temp;
                                    tempindex=2*tempindex+2;
                                }
                                else
                                    complete=true;
                        
                            else if (heap[2*tempindex+1]<=heap[tempindex])
                                    {
                                        temp=heap[tempindex];
                                        heap[tempindex]=heap[2*tempindex+1];
                                        heap[2*tempindex+1]=temp;
                                        tempindex=2*tempindex+1;
                               
                                    }
                                else
                                        complete=true;           
                                    
                                    
                    }
        
        }
    }
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
    MinHeapInPlace test=new MinHeapInPlace(20);
    int a[] ={5,4,3,6,2,15,1};
    
   
    }
}
