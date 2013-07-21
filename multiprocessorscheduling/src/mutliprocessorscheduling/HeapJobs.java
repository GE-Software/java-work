/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Lenovo-BM
 */
package mutliprocessorscheduling;

public class HeapJobs {

    private Job[] heap;
    private int lastindex=-1;
    
    HeapJobs(int size)
    {
         heap=new Job[size];
     }
     
    public void insert(Job element)
     {
         int parentindex, tempindex;
         Job temp;
         lastindex+=1;
         heap[lastindex]=element;
         tempindex=lastindex;
         while (tempindex !=0)
         {
             parentindex=(tempindex-1)/2;
             if (heap[tempindex].Score>=heap[parentindex].Score)
             {
                 temp=heap[parentindex];
                 heap[parentindex]=heap[tempindex];
                 heap[tempindex]=temp;
                 tempindex=parentindex;
              }
             else
             {
                tempindex=0;
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
    public Job deletemax()
    {
        Job value=new Job(-1,0.0,0.0); 
        int tempindex;
        Job temp = new Job(-1,0.0,0.0);
        if (!isempty())
        {
            
            value=heap[0];
            tempindex=0;
            heap[0]=heap[lastindex];
            heap[lastindex]=null;
            lastindex-=1;
            
            boolean complete=false;
            
            while (complete==false && (2*tempindex+1<=lastindex))
            {        
                        if (2*tempindex+2>lastindex) 
                            
                           if (heap[2*tempindex+1].Score>=heap[tempindex].Score)
                                {
                                    temp=heap[tempindex];
                                    heap[tempindex]=heap[2*tempindex+1];
                                    heap[2*tempindex+1]=temp;
                                    tempindex=2*tempindex+1;
                                }
                                    else
                                        complete=true;
                            
                        
                        else
                            if (heap[2*tempindex+2].Score>=heap[2*tempindex+1].Score)
                            
                                if (heap[2*tempindex+2].Score>=heap[tempindex].Score)
                                {
                                    temp=heap[tempindex];
                                    heap[tempindex]=heap[2*tempindex+2];
                                    heap[2*tempindex+2]=temp;
                                    tempindex=2*tempindex+2;
                                }
                                else
                                    complete=true;
                        
                            else if (heap[2*tempindex+1].Score>=heap[tempindex].Score)
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
} 
    

