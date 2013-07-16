/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mutliprocessorscheduling;

/**
 *
 * @author Lenovo-BM
 */
public class HeapProcessor {

    private Processor[] heap;
    private int lastindex=-1;
    
    HeapProcessor(int size)
    {
         heap=new Processor[size];
     }
     
    public void insert(Processor element)
     {
         int parentindex, tempindex;
         Processor temp;
         lastindex+=1;
         heap[lastindex]=element;
         tempindex=lastindex;
         while (tempindex !=0)
         {
             parentindex=(tempindex-1)/2;
             if (heap[tempindex].Completiontime<heap[parentindex].Completiontime)
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
    public Processor deletemin()
    {
        Processor value= new Processor(-1);
                
        int tempindex;
        Processor temp=new Processor (-1);
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
                            
                           if (heap[2*tempindex+1].Completiontime<=heap[tempindex].Completiontime)
                                {
                                    temp=heap[tempindex];
                                    heap[tempindex]=heap[2*tempindex+1];
                                    heap[2*tempindex+1]=temp;
                                    tempindex=2*tempindex+1;
                                }
                                    else
                                        complete=true;
                            
                        
                        else
                            if (heap[2*tempindex+2].Completiontime<=heap[2*tempindex+1].Completiontime)
                            
                                if (heap[2*tempindex+2].Completiontime<=heap[tempindex].Completiontime)
                                {
                                    temp=heap[tempindex];
                                    heap[tempindex]=heap[2*tempindex+2];
                                    heap[2*tempindex+2]=temp;
                                    tempindex=2*tempindex+2;
                                }
                                else
                                    complete=true;
                        
                            else if (heap[2*tempindex+1].Completiontime<=heap[tempindex].Completiontime)
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
