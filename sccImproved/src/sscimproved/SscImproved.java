/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sscimproved;

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
public class SscImproved {
    AdjacencyList ad;
    boolean[] explored;
    int nNodes;
    AdjacencyList revad;
   
    int[] leader;
    int s; // for leaders in 2nd pass
    int t; // for finishing times in 1st pass
    int[] f;
    int[] fsorted;
  

    
    public SscImproved(int size) {
        
            ad=new AdjacencyList(size);
            explored = new boolean[size];
            for (int i = 0; i <size; i++) 
                explored[i] = false;
            
            nNodes = size;
            leader = new int[nNodes];
            f = new int[nNodes];
        //    initialized=true;

        
            
    }    
    public void build (ArrayList<Edge> m, int index)
    {
        ad.build(m,index);
    }
        

    public void DFSCalculate2(int startIndex) {
        explored[startIndex] = true;
        leader[startIndex] = s;
        
        int t;
        for (int i = 0; i < ad.al[fsorted[startIndex]].size(); i++) {
            t=ad.al[fsorted[startIndex]].get(i).getHead();
            if (explored[t] == false) {
                
                DFSCalculate2(ad.al[fsorted[startIndex]].get(i).getHead());
            }
        }

    
    }

    public void DFSCalculate(int startIndex) {
        explored[startIndex] = true;
        leader[startIndex] = s;
        
        for (int i = 0; i < revad.al[startIndex].size(); i++) {
            if (explored[revad.al[startIndex].get(i).getHead()] == false) {
                
                DFSCalculate(revad.al[startIndex].get(i).getHead());
            }
        }

        f[startIndex] = t;
        t++;
        
    }

    public void DFSCalculateLoop() {
        t = 0;
        s = 0;
        
        // reverse the list
        revad=new AdjacencyList(nNodes);
        
        for (int i = 0; i < nNodes; i++) {

            for (int j = 0; j < ad.al[i].size(); j++) {
                Edge tempedge = new Edge();
                tempedge.setEdge(ad.al[i].get(j).getHead(),ad.al[i].get(j).getTail(),
                        ad.al[i].get(j).getDirected(),ad.al[i].get(j).getWeight());
               

                revad.al[ad.al[i].get(j).getHead()].add(tempedge);
            }
        }
        ad=null;
        //System.gc();
        
        for (int i = nNodes - 1; i >= 0; i--) {
            if (explored[i] == false) {
                s = i;
                DFSCalculate(i);
            }
        }

        
        
        ad=new AdjacencyList(nNodes);
        
        

        for (int i = 0; i < nNodes; i++) {

            for (int j = 0; j < revad.al[i].size(); j++) {
                Edge tempedge = new Edge();
                tempedge.setEdge(ad.al[i].get(j).getHead(),ad.al[i].get(j).getTail(),
                        ad.al[i].get(j).getDirected(),ad.al[i].get(j).getWeight());
               

                ad.al[revad.al[i].get(j).getHead()].add(tempedge);
            }
        }
        
        
        
        
        // the following two lines are for freeing some memory
        revad=null;
        System.gc();
        
        
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

        ArrayList<Edge>[] tedge = new ArrayList[nNodes];
        for (int i = 0; i < nNodes; i++) {
            tedge[i] = new ArrayList();

            for (int j = 0; j < ad.al[i].size(); j++) {
             
                ad.al[i].get(j).setTail(f[ad.al[i].get(j).getTail()]);
                ad.al[i].get(j).setHead(f[ad.al[i].get(j).getHead()]);
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
        SscImproved t=new SscImproved(875714);
           
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
                temp1.setEdge(currentTailId,currentHeadId,true,0);
                
                System.out.println(currentTailId+"   "+currentHeadId);
                m.add(temp1);
                
                previousTailId=currentTailId;
            }
            else
                
            {t.build(m,previousTailId);
                if (currentTailId-previousTailId>1)
                {
                    for (int i=1; i<currentTailId-previousTailId;i++)
                    {
                        m=new ArrayList<>();
                        t.build(m, previousTailId+i);
                    }
                }
                
                m=new ArrayList<>();
                temp1.setEdge(currentTailId,currentHeadId,true,0);
               
                System.out.println(currentTailId+"   "+currentHeadId);
                
                m.add(temp1);
                
                previousTailId=currentTailId;
            }
        }
        bf.close();
        s.close();
        
        
        t.build(m,875714-1);
        
        
        
        
        t.DFSCalculateLoop();

        int[] count = new int[875714];
        for (int i = 0; i < 875714; i++) {
            count[t.leader[i]] += 1;
        }


        Mergesort merge = new Mergesort(count);
        int a[]=merge.mergesort(count, 0, count.length-1);
        
       





    }
}
/**
 * @param args the command line arguments
 */

   ///////////////////////  
     
/*temp1.setTail(0);
        temp1.setHead(3);
        temp1.setDirected(true);
        m.add(temp1);
        t.build(m,0);
        
        m=new LinkedList<>();
        temp1=new Edge();
        temp1.setTail(1);
        temp1.setHead(7);
        temp1.setDirected(true);
        m.add(temp1);
        t.build(m,1);
        
        m=new LinkedList<>();
        temp1=new Edge();
        temp1.setTail(2);
        temp1.setHead(5);
        temp1.setDirected(true);
        m.add(temp1);
        t.build(m,2);
        
        m=new LinkedList<>();
        temp1=new Edge();
        temp1.setTail(3);
        temp1.setHead(6);
        temp1.setDirected(true);
        m.add(temp1);
        t.build(m,3);
        
        m=new LinkedList<>();
        temp1=new Edge();
        temp1.setTail(4);
        temp1.setHead(1);
        temp1.setDirected(true);
        m.add(temp1);
        t.build(m,4);

        m=new LinkedList<>();
        temp1=new Edge();
        temp1.setTail(5);
        temp1.setHead(8);
        temp1.setDirected(true);
        m.add(temp1);
        t.build(m,5);

        m=new LinkedList<>();
        temp1=new Edge();
        temp1.setTail(6);
        temp1.setHead(0);
        temp1.setDirected(true);
        m.add(temp1);
        t.build(m,6);

        m=new LinkedList<>();
        temp1=new Edge();
        temp1.setTail(7);
        temp1.setHead(4);
        temp1.setDirected(true);
        m.add(temp1);

        
        temp1=new Edge();
        temp1.setTail(7);
        temp1.setHead(5);
        temp1.setDirected(true);
        m.add(temp1);
        t.build(m,7);

        m=new LinkedList<>();
        temp1=new Edge();
        temp1.setTail(8);
        temp1.setHead(6);
        temp1.setDirected(true);
        m.add(temp1);
        
        
        temp1=new Edge();
        temp1.setTail(8);
        temp1.setHead(2);
        temp1.setDirected(true);
        m.add(temp1);
        t.build(m,8);

        
        
        
        
        
       

        
        
        
        
        
        //s.close();

        //Scc t = new Scc(n, m);

 //////////////////////////
 *        
 *////////////////////////////////
/* output 322211
temp1=new Edge();
        temp1.setTail(0);
        temp1.setHead(3);
        m.add(temp1);
        t.build(m,0);
    
        m=new LinkedList<>();
        temp1=new Edge();
        temp1.setTail(1);
        temp1.setHead(4);
        m.add(temp1);
       t.build(m,1);
        
        m=new LinkedList<>();
        temp1=new Edge();
        temp1.setTail(2);
        temp1.setHead(0);
        m.add(temp1);
        t.build(m,2);
       
        
        m=new LinkedList<>();
        temp1=new Edge();
        temp1.setTail(3);
        temp1.setHead(2);
        m.add(temp1);
        temp1=new Edge();
        temp1.setTail(3);
        temp1.setHead(5);
        m.add(temp1);
        t.build(m,3); 
        
        m=new LinkedList<>();
        temp1=new Edge();
        temp1.setTail(4);
        temp1.setHead(1);
        m.add(temp1);
        temp1=new Edge();
        temp1.setTail(4);
        temp1.setHead(6);
        m.add(temp1);
        temp1=new Edge();
        temp1.setTail(4);
        temp1.setHead(7);
        m.add(temp1);
        t.build(m,4);
        
        m=new LinkedList<>();
        temp1=new Edge();
        temp1.setTail(5);
        temp1.setHead(6);
        m.add(temp1);
        
        temp1=new Edge();
        temp1.setTail(5);
        temp1.setHead(9);
        m.add(temp1);
        t.build(m,5);
 
        
        m=new LinkedList<>();
        temp1=new Edge();
        temp1.setTail(6);
        temp1.setHead(5);
        m.add(temp1);
        temp1=new Edge();
        temp1.setTail(6);
        temp1.setHead(10);
        m.add(temp1);
        t.build(m,6);
        
        m=new LinkedList<>();
        temp1=new Edge();
        temp1.setTail(7);
        temp1.setHead(8);
        m.add(temp1);
        t.build(m,7);
        
        m=new LinkedList<>();
        temp1=new Edge();
        
        m.add(temp1);
        t.build(m,8);
        
        
        m=new LinkedList<>();
        temp1=new Edge();
        temp1.setTail(9);
        temp1.setHead(10);
        m.add(temp1);
        t.build(m,9);
        
        m=new LinkedList<>();
        temp1=new Edge();
        temp1.setTail(10);
        temp1.setHead(9);
        m.add(temp1);
        t.build(m,10);
        
  */      