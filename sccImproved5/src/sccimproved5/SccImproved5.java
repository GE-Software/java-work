/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sccimproved5;

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
public class SccImproved5 {

    //ArrayList<Integer>[] adalTail;
    //int[] countTail;
    int[][] adalHead;
    boolean[] explored;
    int nNodes;
    //ArrayList<Integer>[] revalTail;
    //int[][] revalHead;
    int[] leader;
    int s; // for leaders in 2nd pass
    int t; // for finishing times in 1st pass
    int[] f;
    int[] fsorted;

    public SccImproved5(int size) {

        //adalTail = new ArrayList[size];
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
        for (int i = 0; i < adalHead[fsorted[startIndex]].length; i++) {
            t = adalHead[fsorted[startIndex]][i];
            if (explored[t] == false) {

                DFSCalculate2(adalHead[fsorted[startIndex]][i]);
            }
        }

    }

    public void DFSCalculate(int startIndex) {
        explored[startIndex] = true;
        leader[startIndex] = s;

        for (int i = 0; i < adalHead[startIndex].length; i++) {
            if (explored[adalHead[startIndex][i]] == false) {

                DFSCalculate(adalHead[startIndex][i]);
            }
        }

        f[startIndex] = t;
        t++;
    }

    private int[][] reversead() {

        int[][] revalHead = new int[nNodes][];
        ArrayList<Integer>[] temp = new ArrayList[nNodes];

        for (int i = 0; i < nNodes; i++) {
            temp[i] = new ArrayList();
        }
        for (int i = 0; i < nNodes; i++) {

            for (int j = 0; j < adalHead[i].length; j++) {
                temp[adalHead[i][j]].add(i);

            }
        }

        for (int i = 0; i < nNodes; i++) {
            revalHead[i] = convertIntegers(temp[i]);
        }

        return revalHead;
    }

    private int[][] reverserev() {

        int[][] revalHead = new int[nNodes][];
        ArrayList<Integer>[] temp = new ArrayList[nNodes];

        for (int i = 0; i < nNodes; i++) {
            temp[i] = new ArrayList();
        }


        for (int i = 0; i < nNodes; i++) {
            for (int j = 0; j < adalHead[i].length; j++) {
                temp[adalHead[i][j]].add(i);

            }
        }
        for (int i = 0; i < nNodes; i++) {
            revalHead[i] = convertIntegers(temp[i]);
        }


        return revalHead;
        // for head due to memory limitation
    }

    public void DFSCalculateLoop() {
        t = 0;
        s = 0;
        adalHead = reversead();



        for (int i = nNodes - 1; i >= 0; i--) {
            if (explored[i] == false) {
                s = i;
                DFSCalculate(i);
            }
        }

        adalHead = reverserev();


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

            for (int j = 0; j < adalHead[i].length; j++) {


                adalHead[i][j] = f[adalHead[i][j]];
            }
        }
    }

    public void readfile() throws FileNotFoundException, IOException {
        String line = new String();
        line = "";

        File file = new File("c:\\SCC.txt");
        Scanner s;
        BufferedReader bf = new BufferedReader(new FileReader(file));
        s = new Scanner(bf);

        int previousTailId = 0;
        int currentTailId = 0;
        int currentHeadId = 0;
        ArrayList temp = new ArrayList();


        while (s.hasNextLine()) {

            line = s.nextLine();
            String[] parts = line.split(" ");
            currentTailId = Integer.parseInt(parts[0]) - 1;
            currentHeadId = Integer.parseInt(parts[1]) - 1;

            if (currentTailId == previousTailId) {
                System.out.println(currentTailId + "   " + currentHeadId);

                temp.add(currentHeadId);

                previousTailId = currentTailId;

            } else {
                if (currentTailId - previousTailId > 1) {
                    for (int i = 1; i < currentTailId - previousTailId; i++) {

                        adalHead[previousTailId + i] = new int[0];

                    }
                }
                adalHead[previousTailId] = convertIntegers(temp);
                temp = new ArrayList();
                System.out.println(currentTailId + "   " + currentHeadId);

                previousTailId = currentTailId;
                temp.add(currentHeadId);

            }
        }
        adalHead[previousTailId] = convertIntegers(temp);

        bf.close();
        s.close();



    }

    public static int[] convertIntegers(ArrayList<Integer> integers) {
        int[] ints = new int[integers.size()];
        int i = 0;
        for (Integer n : integers) {
            ints[i++] = n;
        }
        return ints;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        int size = 875714;
        SccImproved5 t = new SccImproved5(size);

        t.readfile();


        /*
         int[]temp={3};
        
         t.adalHead[0]=temp;
        
         //t.adalTail[1].add(1);
         int[] temp1={7};
         t.adalHead[1]=temp1;
        
         //t.adalTail[2].add(2);
         int[] temp2={5};
         t.adalHead[2]=temp2;
        
         //t.adalTail[3].add(3);
         int[] temp3={6};
         t.adalHead[3]=temp3;
        
         //t.adalTail[4].add(4);
         int[] temp4={1};
         t.adalHead[4]=temp4;
        
         //t.adalTail[5].add(5);
         int[] temp5={8};
         t.adalHead[5]=temp5;
        
         //t.adalTail[6].add(6);
         int[] temp6={0};
         t.adalHead[6]=temp6;
        
         //t.adalTail[7].add(7);
         int[] temp7={4,5};
         t.adalHead[7]=temp7;
         //t.adalTail[7].add(7);
         //t.adalHead[7].add(5);
        
         //t.adalTail[8].add(8);
         int[] temp8={2,6};
         t.adalHead[8]=temp8;
         //t.adalTail[8].add(8);
         //t.adalHead[8].add(6);
         */
        t.DFSCalculateLoop();

        int[] count = new int[size];
        for (int i = 0; i < size; i++) {
            count[t.leader[i]] += 1;
        }


        Mergesort merge = new Mergesort(count);
        int a[] = merge.mergesort(count, 0, count.length - 1);
    }
}
