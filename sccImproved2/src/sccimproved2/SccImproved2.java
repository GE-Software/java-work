/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sccimproved2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Lenovo-BM
 */
public class SccImproved2 {
AdjacencyList ad;
    boolean[] explored;
    int nNodes;
    AdjacencyList revad;
   
    int[] leader;
    int s; // for leaders in 2nd pass
    int t; // for finishing times in 1st pass
    int[] f;
    int[] fsorted;
  

    
    public SccImproved2(int size) {
        
            ad=new AdjacencyList(size);
            explored = new boolean[size];
            for (int i = 0; i <size; i++) 
                explored[i] = false;
            
            nNodes = size;
            leader = new int[nNodes];
            f = new int[nNodes];
        //    initialized=true;

        
            
    }    
    public void build (Edge[] m, int index)
    {
        ad.build(m,index);
    }
        

    public void DFSCalculate2(int startIndex) {
        explored[startIndex] = true;
        leader[startIndex] = s;
        
        int t;
        for (int i = 0; i < ad.al[fsorted[startIndex]].length; i++) {
            t=ad.al[fsorted[startIndex]][i].getHead();
            if (explored[t] == false) {
                
                DFSCalculate2(ad.al[fsorted[startIndex]][i].getHead());
            }
        }

    
    }

    public void DFSCalculate(int startIndex) {
        explored[startIndex] = true;
        leader[startIndex] = s;
        
        for (int i = 0; i < revad.al[startIndex].length; i++) {
            if (explored[revad.al[startIndex][i].getHead()] == false) {
                
                DFSCalculate(revad.al[startIndex][i].getHead());
            }
        }

        f[startIndex] = t;
        t++;
        
    }

    public void DFSCalculateLoop() {
        t = 0;
        s = 0;
        
        revad=new AdjacencyList(nNodes);
        
        
        ArrayList<Edge>[] m=new ArrayList[875714];
        
        for (int i = 0; i < nNodes; i++) 
            m[i]=new ArrayList();
        for (int i = 0; i < nNodes; i++){
            for (int j = 0; j < ad.al[i].length; j++) {
                Edge tempedge = new Edge();
                tempedge.setEdge(ad.al[i][j].getHead(),ad.al[i][j].getTail());
                
                
                m[ad.al[i][j].getHead()].add(tempedge);
            }
        }
        Edge[] a;
        
        // the loop was reversed for testing purposes
        for (int i = nNodes-1; i >= 0; i--)
        {
            a=new Edge[m[i].size()];
        
            for (int j=0;j<m[i].size();j++){
                a[j]=new Edge();
                a[j].setEdge(m[i].get(j).getTail(),m[i].get(j).getHead());
            }
            revad.build(a, i);
        
        }
            
        
        m=null;
        ad=null;
        //System.gc();
        
        for (int i = nNodes - 1; i >= 0; i--) {
            if (explored[i] == false) {
                s = i;
                DFSCalculate(i);
            }
        }

        
        
        ad=new AdjacencyList(nNodes);
        
        m=new ArrayList[875714];
        
        for (int i = 0; i < nNodes; i++) 
            m[i]=new ArrayList();

        for (int i = 0; i < nNodes; i++) {

            for (int j = 0; j < revad.al[i].length; j++) {
                Edge tempedge = new Edge();
                tempedge.setEdge(ad.al[i][j].getHead(),ad.al[i][j].getTail());
               
                
                m[revad.al[i][j].getHead()].add(tempedge);
            }
        }
         Edge[] b;
        for (int i = 0; i < nNodes; i++)
        {
            b=new Edge[m[i].size()];
        
            for (int j=0;j<m[i].size();j++){
                b[j]=new Edge();
            
                b[j].setEdge(m[i].get(j).getTail(),m[i].get(j).getHead());
            }
            revad.build(b, i);
        }  
        
        
        
        
        
        
        // the following two lines are for freeing some memory
        revad=null;
       // System.gc();
        
        
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

        //ArrayList<Edge>[] tedge = new ArrayList[nNodes];
        for (int i = 0; i < nNodes; i++) {
          //  tedge[i] = new ArrayList();

            for (int j = 0; j < ad.al[i].length; j++) {
             
                ad.al[i][j].setTail(f[ad.al[i][j].getTail()]);
                ad.al[i][j].setHead(f[ad.al[i][j].getHead()]);
            }


    }
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
       
        ArrayList<Edge> m=new ArrayList<>();
        
        String line = new String();
        line="";
        
        File file = new File("c:\\SCC.txt");
        Scanner s;
        BufferedReader bf=new BufferedReader(new FileReader(file));
        s = new Scanner(bf); 
        SccImproved2 t=new SccImproved2(875714);
           
        Edge temp1;
        
       
        int previousTailId=0;
        int currentTailId=0;
        int currentHeadId=0;
        while (s.hasNextLine())
        {
            temp1=new Edge();
            
            line=s.nextLine();
            String[] parts=line.split(" ");
            currentTailId=Integer.parseInt(parts[0])-1;
            currentHeadId=Integer.parseInt(parts[1])-1;
           
            if (currentTailId==previousTailId)
            {
                temp1.setEdge(currentTailId,currentHeadId);
                
                System.out.println(currentTailId+"   "+currentHeadId);
                m.add(temp1);
                
                previousTailId=currentTailId;
                temp1=null;
            }
            else
            {
                if (currentTailId-previousTailId>1)
                {
                    for (int i=1; i<currentTailId-previousTailId;i++)
                    {
                        Edge[]tedge=new Edge[0];
                        t.build(tedge, previousTailId+i);
                    }
                }
                Edge[] tedge=new Edge[m.size()];
                for (int i=0; i<m.size();i++){
                    tedge[i]=new Edge();
                
                    tedge[i].setEdge(m.get(i).getTail(), m.get(i).getHead());
                }
                t.build(tedge,previousTailId);
                
                m=new ArrayList<>();
                temp1.setEdge(currentTailId,currentHeadId);
               
                System.out.println(currentTailId+"   "+currentHeadId);
                
                m.add(temp1);
                
                previousTailId=currentTailId;
            }
        }
        bf.close();
        s.close();
        
        Edge[] tedge=new Edge[m.size()];
                for (int i=0; i<m.size();i++){
                    tedge[i]=new Edge();
                
                    tedge[i].setEdge(m.get(i).getTail(), m.get(i).getHead());
                }
        t.build(tedge,875714-1);
        
        
        
        
        t.DFSCalculateLoop();

        int[] count = new int[875714];
        for (int i = 0; i < 875714; i++) {
            count[t.leader[i]] += 1;
        }


        Mergesort merge = new Mergesort(count);
        int a[]=merge.mergesort(count, 0, count.length-1);
        
       





    }
}

