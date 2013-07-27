/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sccimproved3;

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
public class SccImproved3 {

    int[][] adalTail;
    int[][] adalHead;
    boolean[] explored;
    int nNodes;
    int[][] revalTail;
    int[][] revalHead;
    int[] leader;
    int s; // for leaders in 2nd pass
    int t; // for finishing times in 1st pass
    int[] f;
    int[] fsorted;

    public SccImproved3(int size) {

        adalTail = new int[size][];
        adalHead = new int[size][];
        explored = new boolean[size];
        for (int i = 0; i < size; i++) {
            explored[i] = false;
        }

        nNodes = size;
        leader = new int[nNodes];
        f = new int[nNodes];
       
}
    
    public void DFSCalculate2(int startIndex) {
        explored[startIndex] = true;
        leader[startIndex] = s;

        int t;
        for (int i = 0; i < adalTail[fsorted[startIndex]].length; i++) {
            t = adalHead[fsorted[startIndex]][i];
            if (explored[t] == false) {

                DFSCalculate2(adalHead[fsorted[startIndex]][i]);
            }
        }

    }

    public void DFSCalculate(int startIndex) {
        explored[startIndex] = true;
        leader[startIndex] = s;

        for (int i = 0; i < revalTail[startIndex].length; i++) {
            if (explored[revalHead[startIndex][i]] == false) {

                DFSCalculate(revalHead[startIndex][i]);
            }
        }

        f[startIndex] = t;
        t++;
    }
private void reversead()
{
        revalTail = new int[nNodes][];
        revalHead = new int[nNodes][];

        ArrayList<Integer>[] m = new ArrayList[875714];
        for (int i = 0; i < nNodes; i++) {
            m[i] = new ArrayList();
            
        }

        for (int i = 0; i < nNodes; i++) {

            for (int j = 0; j < adalTail[i].length; j++) {

                m[adalHead[i][j]].add(adalHead[i][j]);
                //mHead[revalTail[i][j]].add(revalTail[i][j]);

            }
        }

        int[] a;
        
        for (int i = 0; i < nNodes; i++) {
            a = new int[m[i].size()];
           
            for (int j = 0; j < m[i].size(); j++) {
                a[j] = m[i].get(j);
              }
            revalTail[i] = a;
         }
        
        // for head due to memory limitation
        
        
        m = new ArrayList[875714];
        for (int i = 0; i < nNodes; i++) {
            m[i] = new ArrayList();
            
        }

        for (int i = 0; i < nNodes; i++) {

            for (int j = 0; j < adalTail[i].length; j++) {

                //m[revalHead[i][j]].add(revalHead[i][j]);
                m[adalTail[i][j]].add(adalTail[i][j]);

            }
        }

        for (int i = 0; i < nNodes; i++) {
            a = new int[m[i].size()];
           
            for (int j = 0; j < m[i].size(); j++) {
                a[j] = m[i].get(j);
              }
            revalHead[i] = a;
         }

}
    
private void reverserev()
{
        adalTail = new int[nNodes][];
        adalHead = new int[nNodes][];

        ArrayList<Integer>[] m = new ArrayList[875714];
        for (int i = 0; i < nNodes; i++) {
            m[i] = new ArrayList();
            
        }

        for (int i = 0; i < nNodes; i++) {

            for (int j = 0; j < revalTail[i].length; j++) {

                m[revalHead[i][j]].add(revalHead[i][j]);
                //mHead[revalTail[i][j]].add(revalTail[i][j]);

            }
        }

        int[] a;
        
        for (int i = 0; i < nNodes; i++) {
            a = new int[m[i].size()];
           
            for (int j = 0; j < m[i].size(); j++) {
                a[j] = m[i].get(j);
              }
            adalTail[i] = a;
         }
        
        // for head due to memory limitation
        
        
        m = new ArrayList[875714];
        for (int i = 0; i < nNodes; i++) {
            m[i] = new ArrayList();
            
        }

        for (int i = 0; i < nNodes; i++) {

            for (int j = 0; j < revalTail[i].length; j++) {

                //m[revalHead[i][j]].add(revalHead[i][j]);
                m[revalTail[i][j]].add(revalTail[i][j]);

            }
        }

        for (int i = 0; i < nNodes; i++) {
            a = new int[m[i].size()];
           
            for (int j = 0; j < m[i].size(); j++) {
                a[j] = m[i].get(j);
              }
            adalHead[i] = a;
         }
        
}
    public void DFSCalculateLoop() {
        t = 0;
        s = 0;
        reversead();
        
        adalTail = null;
        adalHead = null;
        
        for (int i = nNodes - 1; i >= 0; i--) {
            if (explored[i] == false) {
                s = i;
                DFSCalculate(i);
            }
        }

        reverserev();

        // the following two lines are for freeing some memory
        revalTail = null;
        revalHead = null;
        //System.gc();


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

            for (int j = 0; j < adalTail[i].length; j++) {

                adalTail[i][j] = (f[adalTail[i][j]]);
                adalHead[i][j] = (f[adalHead[i][j]]);
            }

        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        ArrayList<Integer> mTail = new ArrayList<>();
        ArrayList<Integer> mHead = new ArrayList<>();

        String line = new String();
        line = "";

        File file = new File("c:\\SCC.txt");
        Scanner s;
        BufferedReader bf = new BufferedReader(new FileReader(file));
        s = new Scanner(bf);
        SccImproved3 t = new SccImproved3(875714);

        int previousTailId = 0;
        int currentTailId = 0;
        int currentHeadId = 0;
        while (s.hasNextLine()) {

            line = s.nextLine();
            String[] parts = line.split(" ");
            currentTailId = Integer.parseInt(parts[0]) - 1;
            currentHeadId = Integer.parseInt(parts[1]) - 1;

            if (currentTailId == previousTailId) {
                System.out.println(currentTailId + "   " + currentHeadId);
                mTail.add(currentTailId);
                mHead.add(currentHeadId);

                previousTailId = currentTailId;

            } else {
                if (currentTailId - previousTailId > 1) {
                    for (int i = 1; i < currentTailId - previousTailId; i++) {
                        t.adalTail[previousTailId + i] = new int[0];
                        t.adalHead[previousTailId + i] = new int[0];

                    }
                }

                int[] tTail = new int[mTail.size()];
                int[] tHead = new int[mTail.size()];
                for (int i = 0; i < mTail.size(); i++) {
                    tTail[i] = mTail.get(i);
                    tHead[i] = mHead.get(i);
                }
                t.adalTail[previousTailId] = tTail;
                t.adalHead[previousTailId] = tHead;
                mTail=null;
                mHead=null;
                mTail = new ArrayList<>();
                mHead = new ArrayList<>();
                System.out.println(currentTailId + "   " + currentHeadId);
                mTail.add(currentTailId);
                mHead.add(currentHeadId);
                previousTailId = currentTailId;
            }
        }
        bf.close();
        s.close();


        int[] tTail = new int[mTail.size()];
        int[] tHead = new int[mTail.size()];
        for (int i = 0; i < mTail.size(); i++) {
            tTail[i] = mTail.get(i);
            tHead[i] = mHead.get(i);
        }
        t.adalTail[previousTailId] = tTail;
        t.adalHead[previousTailId] = tHead;




        t.DFSCalculateLoop();

        int[] count = new int[875714];
        for (int i = 0; i < 875714; i++) {
            count[t.leader[i]] += 1;
        }


        Mergesort merge = new Mergesort(count);
        int a[] = merge.mergesort(count, 0, count.length - 1);







    }
}
