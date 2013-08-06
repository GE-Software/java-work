/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package median;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author Lenovo-BM
 */
public class Median {

    int[] numbers;
    MinHeap minHeap;
    MaxHeap maxHeap;
    int mediansum=0;
    public Median(int size) throws FileNotFoundException
    {
        numbers=new int[size];
        readfile();
        minHeap=new MinHeap(size);
        maxHeap=new MaxHeap(size);
        
        
        maxHeap.insert(numbers[0]);
        mediansum=numbers[0];
        int item;
        for (int i=1; i<size;i++)
        {
            if (numbers[i]>maxHeap.getmax())
            {
                minHeap.insert(numbers[i]);
            }
            else
            {
                maxHeap.insert(numbers[i]);
            }    
        
           if (Math.abs(maxHeap.getSize()-minHeap.getSize())>1)
               if (maxHeap.getSize()>minHeap.getSize())
               {
                   item=maxHeap.deletemax();
                   minHeap.insert(item);
               }
               else
               {
                   item=minHeap.deletemin();
                   maxHeap.insert(item);
               }
                   
           if ((maxHeap.getSize()+minHeap.getSize())%2==1)
               if (minHeap.getSize()>maxHeap.getSize())
                   mediansum+=minHeap.getmin();
               else
                   mediansum+=maxHeap.getmax();
           
        
            
            else
               mediansum+=maxHeap.getmax();
        }
                
    }
    
    public int returnValue()
    {
        return mediansum%10000;
    }
    
     public void readfile() throws FileNotFoundException {
        String filename = "Median.txt";
        //String filename="1.txt";
        File file = new File(filename);
        Scanner s;
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String line = new String();
        line = "";
        s = new Scanner(bf);
        int i = 0;
        int t;

        while (s.hasNextLine()) {

            line = s.nextLine();
            numbers[i] = Integer.parseInt(line);
            i+=1;
        }
        s.close();
    }
    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
    Median t=new Median(10000);
    int r=t.returnValue();
   
   }
}
