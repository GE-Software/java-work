/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heap;

/**
 *
 * @author Lenovo-BM
 */
public class Heap {

    private int[] heap;
    private int lastindex=-1;
    
    Heap(int size)
    {
         heap=new int[size];
     }
     
    public void insert(int element)
     {
         int parentindex, tempindex;
         int temp;
         lastindex+=1;
         heap[lastindex]=element;
         tempindex=lastindex;
         while (tempindex !=0)
         {
             parentindex=(tempindex-1)/2;
             if (heap[tempindex]<heap[parentindex])
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
                            
                           if (heap[2*tempindex+1]>=heap[tempindex])
                                {
                                    temp=heap[tempindex];
                                    heap[tempindex]=heap[2*tempindex+1];
                                    heap[2*tempindex+1]=temp;
                                    tempindex=2*tempindex+1;
                                }
                                    else
                                        complete=true;
                            
                        
                        else
                            if (heap[2*tempindex+2]>=heap[2*tempindex+1])
                            
                                if (heap[2*tempindex+2]>=heap[tempindex])
                                {
                                    temp=heap[tempindex];
                                    heap[tempindex]=heap[2*tempindex+2];
                                    heap[2*tempindex+2]=temp;
                                    tempindex=2*tempindex+2;
                                }
                                else
                                    complete=true;
                        
                            else if (heap[2*tempindex+1]>=heap[tempindex])
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
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
    Heap test=new Heap(20);
    test.insert(5);
    test.insert(4);
    test.insert(3);
    test.insert(6);
    test.insert(2);
    test.insert(15);
    test.insert(-1);
    int temp=test.deletemin();
    }
}
