/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sccimproved4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Lenovo-BM
 */
public class SccImproved4 {

  
    ArrayList<Integer>[] adalHead;
    boolean[] explored;
    int nNodes;
    ArrayList<Integer>[] revalHead;
    int[] leader;
    int s; // for leaders in 2nd pass
    int t; // for finishing times in 1st pass
    int[] f;
    int[] fsorted;

    public SccImproved4(int size) {

        
        adalHead = new ArrayList[size];
        explored = new boolean[size];
        for (int i = 0; i < size; i++) {
            explored[i] = false;
        }

        nNodes = size;
        leader = new int[nNodes];
        f = new int[nNodes];
        for (int i=0; i<nNodes;i++)
        {
            adalHead[i]=new ArrayList();
                               
        }
}
    
    public void DFSCalculate2(int startIndex) {
        explored[startIndex] = true;
        leader[startIndex] = s;

        int t;
        for (int i = 0; i < adalHead[fsorted[startIndex]].size(); i++) {
            t = adalHead[fsorted[startIndex]].get(i);
            if (explored[t] == false) {

                DFSCalculate2(t);
            }
        }

    }

    public void DFSCalculate(int startIndex) {
        explored[startIndex] = true;
        leader[startIndex] = s;
        int temp;
        for (int i = 0; i < revalHead[startIndex].size(); i++) {
            temp=revalHead[startIndex].get(i);
            if (explored[temp] == false) {

                DFSCalculate(temp);
            }
        }

        f[startIndex] = t;
        
        
        
        
        t++;
    }
private void reversead()
{
         
        revalHead = new ArrayList[nNodes];
        for (int i=0; i<nNodes;i++)
         revalHead[i]=new ArrayList();
         
         
               
        for (int i = 0; i < nNodes; i++) {

            for (int j = 0; j < adalHead[i].size(); j++) {

                //m[revalHead[i][j]].add(revalHead[i][j]);
                revalHead[adalHead[i].get(j)].add(i);

            }
        }
        for (int i=0;i<nNodes;i++)
            revalHead[i].trimToSize();
        
     adalHead=null;   
     
}
    
private void reverserev()
{
        adalHead = new ArrayList[nNodes];
        for (int i=0; i<nNodes;i++)
         adalHead[i]=new ArrayList();
         
         
               
        for (int i = 0; i < nNodes; i++) {

            for (int j = 0; j < revalHead[i].size(); j++) {

                
                adalHead[revalHead[i].get(j)].add(i);

            }
        }
    for (int i=0;i<nNodes;i++)
        adalHead[i].trimToSize();
        
        revalHead=null;
        // for head due to memory limitation
}
    public void DFSCalculateLoop() {
        t = 0;
        s = 0;
        reversead();
        
        
        
        for (int i = nNodes - 1; i >= 0; i--) {
            if (explored[i] == false) {
                s = i;
                DFSCalculate(i);
            }
        }

        reverserev();

       
        revalHead = null;
        


        renameLabels();
       arrangef();

        for (int i = 0; i < nNodes; i++) {
            explored[i] = false;
        }


        for (int i = nNodes - 1; i >= 0; i--) {
            if (explored[i] == false) {
                s = i;
                DFSCalculate2(i);
            }
        }



    }

    public void arrangef() {
        fsorted = new int[f.length];
        for (int i = 0; i < f.length; i++) {
            fsorted[f[i]] = i;
        }
    }

    public void renameLabels() {

        for (int i = 0; i < nNodes; i++) {
            //  tedge[i] = new ArrayList();

            for (int j = 0; j < adalHead[i].size(); j++) {

                
                adalHead[i].set(j,f[adalHead[i].get(j)]);
            }
        }
    }

    public void readfile() throws FileNotFoundException, IOException
    {
        String line = new String();
        line = "";

        File file = new File("c:\\SCC.txt");
        Scanner s;
        BufferedReader bf = new BufferedReader(new FileReader(file));
        s = new Scanner(bf);
        
        int previousTailId = 0;
        int currentTailId = 0;
        int currentHeadId = 0;
       
        while (s.hasNextLine()) {

            line = s.nextLine();
            String[] parts = line.split(" ");
            currentTailId = Integer.parseInt(parts[0]) - 1;
            currentHeadId = Integer.parseInt(parts[1]) - 1;

            if (currentTailId == previousTailId) {
                //System.out.println(currentTailId + "   " + currentHeadId);
               
                adalHead[previousTailId].add(currentHeadId);

                previousTailId = currentTailId;

            } else {
                if (currentTailId - previousTailId > 1) {
                    for (int i = 1; i < currentTailId - previousTailId; i++) {
                        //adalTail[previousTailId + i]=new ArrayList();
                        adalHead[previousTailId + i] = new ArrayList();

                    }
                }

                
                //System.out.println(currentTailId + "   " + currentHeadId);
                
                previousTailId=currentTailId;
                adalHead[previousTailId].add(currentHeadId);
                //adalTail[previousTailId].add(currentTailId);
            }
        }
       for (int i=0; i<nNodes;i++)
            adalHead[i].trimToSize();
        bf.close();
        s.close();



    }
    
    public static int[] convertIntegers(ArrayList<Integer> integers)
{
     int[] ints = new int[integers.size()];
    int i = 0;
    for (Integer n : integers) {
        ints[i++] = n;
    }
    return ints;
}
    
    
    
    
    
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {

              
        SccImproved4 t = new SccImproved4(875714);

        t.readfile();
       
        t.DFSCalculateLoop();

        int[] count = new int[875714];
        for (int i = 0; i < 875714; i++) {
            count[t.leader[i]] += 1;
        }


        Mergesort merge = new Mergesort(count);
        int a[] = merge.mergesort(count, 0, count.length - 1);







    }
}
