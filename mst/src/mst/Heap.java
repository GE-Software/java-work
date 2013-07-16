/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


// implementation of Boruvka algorithm for minimum spanning tree

// incomplete

package mst;

/**
 *
 * @author Lenovo-BM
 */
public class Heap {

    private Node[] heap;
    private int lastindex=-1;
    
    Heap(int size)
    {
         heap=new Node[size];
         for (int i=0; i<size;i++)
             heap[i]=new Node();
     }
     
    public void insert(Node element)
     {
         int parentindex, tempindex;
         Node temp;
         lastindex+=1;
         heap[lastindex]=element;
         tempindex=lastindex;
         while (tempindex !=0)
         {
             parentindex=(tempindex-1)/2;
             if (heap[tempindex].weight<heap[parentindex].weight)
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
    public Node deletemin()
    {
        Node value=new Node();
        int tempindex;
        Node temp;
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
                            
                           if (heap[2*tempindex+1].weight>=heap[tempindex].weight)
                                {
                                    temp=heap[tempindex];
                                    heap[tempindex]=heap[2*tempindex+1];
                                    heap[2*tempindex+1]=temp;
                                    tempindex=2*tempindex+1;
                                }
                                    else
                                        complete=true;
                            
                        
                        else
                            if (heap[2*tempindex+2].weight>=heap[2*tempindex+1].weight)
                            
                                if (heap[2*tempindex+2].weight>=heap[tempindex].weight)
                                {
                                    temp=heap[tempindex];
                                    heap[tempindex]=heap[2*tempindex+2];
                                    heap[2*tempindex+2]=temp;
                                    tempindex=2*tempindex+2;
                                }
                                else
                                    complete=true;
                        
                            else if (heap[2*tempindex+1].weight>=heap[tempindex].weight)
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
