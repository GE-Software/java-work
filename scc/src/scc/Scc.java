/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Lenovo-BM
 */
public class Scc {

    /**
     *
     * @author Lenovo-BM
     */
    AdjacencyList ad;
    boolean[] explored;
    StringBuilder output;
    int nNodes;
    AdjacencyList revad;
    AdjacencyList relabeled;
    int[] leader;
    int s; // for leaders in 2nd pass
    int t; // for finishing times in 1st pass
    int[] f;
    int[] fsorted;
    boolean initialized=false;

    public Scc(LinkedList<Node> n, LinkedList<Edge>[] m) {
        explored = new boolean[n.size()];
        ad = new AdjacencyList(n, m);
        for (int i = 0; i < n.size(); i++) {
            explored[i] = false;
        }
        output = new StringBuilder();
        nNodes = n.size();

        leader = new int[nNodes];
        f = new int[nNodes];


       /* LinkedList<Edge>[] revedge = new LinkedList[nNodes];
        for (int i = 0; i < nNodes; i++) {
            revedge[i] = new LinkedList();
        }

        for (int i = 0; i < nNodes; i++) {

            for (int j = 0; j < m[i].size(); j++) {
                Edge tempedge = new Edge();
                tempedge.setHead(m[i].get(j).getTail());
                tempedge.setTail(m[i].get(j).getHead());
                tempedge.setDirected(m[i].get(j).getDirected());
                tempedge.setWeight(m[i].get(j).getWeight());

                revedge[m[i].get(j).getHead().getId()].add(tempedge);
            }
        }

        revad = new AdjacencyList(n, revedge);
*/
    }

    public Scc(LinkedList<Node> n, LinkedList<Edge> m,int index) {
        if (initialized==false)
        {
            explored = new boolean[n.size()];
            for (int i = 0; i < n.size(); i++) 
                explored[i] = false;
            output = new StringBuilder();
            nNodes = n.size();
            leader = new int[nNodes];
            f = new int[nNodes];
            initialized=true;

        }
            ad= new AdjacencyList(n, m,index);
    }      
        

        
            
    
    
    
    
    
    
    
    public void DFSCalculate2(AdjacencyList ad, int startIndex) {
        explored[startIndex] = true;
        leader[startIndex] = s;
        LinkedList<Edge> edge = new LinkedList<>();
        edge = ad.getEdgeList(fsorted[startIndex]);
        for (int i = 0; i < edge.size(); i++) {
            if (explored[edge.get(i).getHead().getId()] == false) {
                explored[edge.get(i).getHead().getId()] = true;
                DFSCalculate2(relabeled, edge.get(i).getHead().getId());
            }
        }

        //f[startIndex]=t;
        // t++;
        // output.append(startIndex);
    }

    public void DFSCalculate(AdjacencyList ad, int startIndex) {
        explored[startIndex] = true;
        leader[startIndex] = s;
        LinkedList<Edge> edge = new LinkedList<>();
        edge = ad.getEdgeList(startIndex);
        for (int i = 0; i < edge.size(); i++) {
            if (explored[edge.get(i).getHead().getId()] == false) {
                DFSCalculate(ad, edge.get(i).getHead().getId());
            }
        }

        f[startIndex] = t;
        t++;
        output.append(startIndex);
    }

    public void DFSCalculateLoop() {
        t = 0;
        s = 0;
        
        LinkedList<Edge>[] revedge = new LinkedList[nNodes];
        for (int i = 0; i < nNodes; i++) 
            revedge[i] = new LinkedList();
        

        for (int i = 0; i < nNodes; i++) {

            for (int j = 0; j < ad.getVertex(i).getNext().size(); j++) {
                Edge tempedge = new Edge();
                tempedge.setHead(ad.getVertex(i).getNext().get(j).getTail());
                tempedge.setTail(ad.getVertex(i).getNext().get(j).getHead());
                tempedge.setDirected(ad.getVertex(i).getNext().get(j).getDirected());
                tempedge.setWeight(ad.getVertex(i).getNext().get(j).getWeight());

                revedge[ad.getVertex(i).getNext().get(j).getHead().getId()].add(tempedge);
            }
        }


        
        
        
        
        
        
        
        
        
        
        
        for (int i = nNodes - 1; i >= 0; i--) {
            if (explored[i] == false) {
                s = i;
                DFSCalculate(revad, i);
            }
        }

        renameLabels();
        arrangef();

        for (int i = 0; i < nNodes; i++) {
            explored[i] = false;
        }



        for (int i = nNodes - 1; i >= 0; i--) {
            if (explored[i] == false) {
                s = i;
                DFSCalculate2(relabeled, i);
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

        LinkedList<Edge>[] tedge = new LinkedList[nNodes];
        for (int i = 0; i < nNodes; i++) {
            tedge[i] = new LinkedList();

            for (int j = 0; j < ad.getEdgeList(i).size(); j++) {
                Node temp = new Node();
                Edge edge = new Edge();
                temp.setId(f[ad.getEdgeList(i).get(j).getTail().getId()]);
                edge.setTail(temp);
                temp = new Node();
                temp.setId(f[ad.getEdgeList(i).get(j).getHead().getId()]);
                edge.setHead(temp);
                edge.setDirected(ad.getEdgeList(i).get(j).getDirected());
                edge.setWeight(ad.getEdgeList(i).get(j).getWeight());
                tedge[i].add(edge);

            }
        }
        LinkedList<Node> tnode = new LinkedList<>();
        tnode = ad.getVertexList();

        for (int i = 0; i < tnode.size(); i++) {
            tnode.get(i).setId(f[i]);
        }




        ad = new AdjacencyList(tnode, tedge);


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here


        LinkedList<Node> n = new LinkedList<>();
       // LinkedList<Edge>[] m = new LinkedList[5105043];
        LinkedList<Edge> m=new LinkedList<>();
        Node tnode;
        String line = new String();
        line="";
        //Scanner lineScanner;
        File file = new File("c:\\SCC.txt");
        Scanner s;
       BufferedReader bf=new BufferedReader(new FileReader(file));
        s = new Scanner(bf); 
        
        
  
        for (int i = 0;i<875714;i++)
     {
            tnode = new Node();
            tnode.setId(i);
            n.add(tnode);
        }

        
        
        int previousTailId=0;
        int currentTailId=0;
        int currentHeadId=0;
        Scc t;
        Edge temp1;
        Node tTail;
        Node tHead;
        while (s.hasNextLine())
        {
            temp1=new Edge();
            tTail=new Node();
            tHead=new Node();
            line=s.nextLine();
            String[] parts=line.split(" ");
            currentTailId=Integer.parseInt(parts[0]);
            currentHeadId=Integer.parseInt(parts[1]);
           
            if (currentTailId==previousTailId)
            {
                tTail.setId(currentTailId);
                tHead.setId(currentHeadId);
                System.out.println(currentTailId+"   "+currentHeadId);
                temp1.setTail(tTail);
                temp1.setHead(tHead);
                temp1.setDirected(true);
                m.add(temp1);
                
                previousTailId=currentTailId;
            }
            else
            {
                t=new Scc(n,m,currentTailId);
                m=new LinkedList<>();
                tTail.setId(currentTailId);
                tHead.setId(currentHeadId);
                temp1.setTail(tTail);
                temp1.setHead(tHead);
                temp1.setDirected(true);
                m.add(temp1);
                //lScanner.close();
                previousTailId=currentTailId;
            }
        }
        s.close();
        
        
        t=new Scc(n,m,n.size()-1);
        
        //s.close();

        //Scc t = new Scc(n, m);
        t.DFSCalculateLoop();

        int[] count = new int[875714];
        for (int i = 0; i < 875714; i++) {
            count[t.leader[i]] += 1;
        }


        Mergesort merge = new Mergesort(count);
        merge.mergesort(count, 0, count.length-1);
        count=merge.returnsorted();
       





    }
}
/**
 * @param args the command line arguments
 */
/*tnode.setId(0);
 n.add(tnode);
 tnode=new Node();
 tnode.setId(1);
 n.add(tnode);
      
 tnode=new Node();
 tnode.setId(2);
 n.add(tnode);
      
 tnode=new Node();
 tnode.setId(3);
 n.add(tnode);
      
 tnode=new Node();
 tnode.setId(4);
 n.add(tnode);
 tnode=new Node();
 tnode.setId(5);
 n.add(tnode);
 tnode=new Node();
 tnode.setId(6);
 n.add(tnode);
 tnode=new Node();
 tnode.setId(7);
 n.add(tnode);
 tnode=new Node();
 tnode.setId(8);
 n.add(tnode);
     
 Edge tedge=new Edge();
 tedge.setTail(n.get(0));
 tedge.setHead(n.get(3));
 tedge.setDirected(true);
 m[0].add(tedge);
     
 tedge=new Edge();
 tedge.setTail(n.get(1));
 tedge.setHead(n.get(7));
 tedge.setDirected(true);
 m[1].add(tedge);
     
 tedge=new Edge();
 tedge.setTail(n.get(2));
 tedge.setHead(n.get(5));
 tedge.setDirected(true);
 m[2].add(tedge);
     
 tedge=new Edge();
 tedge.setTail(n.get(3));
 tedge.setHead(n.get(6));
 tedge.setDirected(true);
 m[3].add(tedge);
     
 tedge=new Edge();
 tedge.setTail(n.get(4));
 tedge.setHead(n.get(1));
 tedge.setDirected(true);
 m[4].add(tedge);
     
 tedge=new Edge();
 tedge.setTail(n.get(5));
 tedge.setHead(n.get(8));
 tedge.setDirected(true);
 m[5].add(tedge);
     
 tedge=new Edge();
 tedge.setTail(n.get(6));
 tedge.setHead(n.get(0));
 tedge.setDirected(true);
 m[6].add(tedge);
     
 tedge=new Edge();
 tedge.setTail(n.get(7));
 tedge.setHead(n.get(4));
 tedge.setDirected(true);
 m[7].add(tedge);
     
 tedge=new Edge();
 tedge.setTail(n.get(7));
 tedge.setHead(n.get(5));
 tedge.setDirected(true);
 m[7].add(tedge);
     
 tedge=new Edge();
 tedge.setTail(n.get(8));
 tedge.setHead(n.get(6));
 tedge.setDirected(true);
 m[8].add(tedge);
     
 tedge=new Edge();
 tedge.setTail(n.get(8));
 tedge.setHead(n.get(2));
 tedge.setDirected(true);
 m[8].add(tedge);
     
     
     
     
     
     
     
     
     
 /* Edge tedge=new Edge();
 tedge.setTail(n.get(0));
 tedge.setHead(n.get(3));
 tedge.setDirected(true);
 m[0].add(tedge);
     
 tedge=new Edge();
 tedge.setTail(n.get(1));
 tedge.setHead(n.get(7));
 tedge.setDirected(true);
 m[1].add(tedge);
     
 tedge=new Edge();
 tedge.setTail(n.get(2));
 tedge.setHead(n.get(5));
 tedge.setDirected(true);
 m[2].add(tedge);
     
 tedge=new Edge();
 tedge.setTail(n.get(3));
 tedge.setHead(n.get(6));
 tedge.setDirected(true);
 m[3].add(tedge);
     
     
 tedge=new Edge();
 tedge.setTail(n.get(4));
 tedge.setHead(n.get(1));
 tedge.setDirected(true);
 m[4].add(tedge);
     
 tedge=new Edge();
 tedge.setTail(n.get(5));
 tedge.setHead(n.get(8));
 tedge.setDirected(true);
 m[5].add(tedge);
     
          
 tedge=new Edge();
 tedge.setTail(n.get(6));
 tedge.setHead(n.get(0));
 tedge.setDirected(true);
 m[6].add(tedge);
     
 tedge=new Edge();
 tedge.setTail(n.get(7));
 tedge.setHead(n.get(5));
 tedge.setDirected(true);
 m[7].add(tedge);
     
     
 tedge=new Edge();
 tedge.setTail(n.get(7));
 tedge.setHead(n.get(4));
 tedge.setDirected(true);
 m[7].add(tedge);
     
     
 tedge=new Edge();
 tedge.setTail(n.get(8));
 tedge.setHead(n.get(6));
 tedge.setDirected(true);
 m[8].add(tedge);
      
 tedge=new Edge();
 tedge.setTail(n.get(8));
 tedge.setHead(n.get(2));
 tedge.setDirected(true);
 m[8].add(tedge);
 */
     
     
     

