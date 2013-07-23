/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scc;

import java.util.LinkedList;

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
    boolean[]explored;
    StringBuilder output;
    int nNodes;
    AdjacencyList revad;
    AdjacencyList relabeled;
    int[] leader;
    int s; // for leaders in 2nd pass
    int t; // for finishing times in 1st pass
    int[] f;
    int [] fsorted;
    public Scc(LinkedList<Node> n, LinkedList<Edge>[] m)
    {
        explored=new boolean[n.size()];
        ad=new AdjacencyList(n,m);
       for (int i=0; i<n.size();i++)
           explored[i]=false;
       output=new StringBuilder();
       nNodes=n.size();
       
       leader=new int[nNodes];
       f=new int[nNodes];
       
       
       LinkedList<Edge>[] revedge=new LinkedList[nNodes];
       for (int i=0; i<nNodes;i++) {
            revedge[i]=new LinkedList();
        }
       
       for (int i=0; i<nNodes;i++)
       {
          
           for (int j=0; j<m[i].size();j++)
           {
                Edge tempedge=new Edge();
                tempedge.setHead(m[i].get(j).getTail());
                tempedge.setTail(m[i].get(j).getHead());
                tempedge.setDirected(m[i].get(j).getDirected());
                tempedge.setWeight(m[i].get(j).getWeight());
                
                revedge[m[i].get(j).getHead().getId()].add(tempedge);
           }
       }
              
       revad=new AdjacencyList(n,revedge);
    
  }
 
    public void DFSCalculate2(AdjacencyList ad,int startIndex)
{
    explored[startIndex]=true;
    leader[startIndex]=s;
    LinkedList<Edge> edge=new LinkedList<>();
    edge=ad.getEdgeList(fsorted[startIndex]);
    for (int i=0; i<edge.size();i++)
    {
        if (explored[edge.get(i).getHead().getId()]==false) {
            explored[edge.get(i).getHead().getId()]=true;
            DFSCalculate2(ad,edge.get(i).getHead().getId());
        }
    }
    
    //f[startIndex]=t;
   // t++;
   // output.append(startIndex);
}
    
    
    
    public void DFSCalculate(AdjacencyList ad,int startIndex)
{
    explored[startIndex]=true;
    leader[startIndex]=s;
    LinkedList<Edge> edge=new LinkedList<>();
    edge=ad.getEdgeList(startIndex);
    for (int i=0; i<edge.size();i++)
    {
        if (explored[edge.get(i).getHead().getId()]==false) {
            DFSCalculate(ad,edge.get(i).getHead().getId());
        }
    }
    
    f[startIndex]=t;
    t++;
    output.append(startIndex);
}
    
 public void DFSCalculateLoop()   
 {
     t=0;
     s=0;
     for (int i=nNodes-1;i>=0;i--)
         if(explored[i]==false)
         {
             s=i;
             DFSCalculate(revad,i);
         }
     
     renameLabels();
     arrangef();
     
     for (int i=0; i<nNodes;i++)
         explored[i]=false;
     
     
     
     for (int i=nNodes-1;i>=0;i--)
         if(explored[i]==false)
         {
             s=i;
             DFSCalculate2(relabeled,i);
         }
     
     
     
 }
 
 public void arrangef()
 {
     fsorted=new int[f.length];
     for (int i=0;i<f.length;i++)
         fsorted[f[i]]=i;
 }
 
 
     public void renameLabels()
     {
         
         LinkedList<Edge>[] tedge=new LinkedList[nNodes];
         for (int i=0; i<nNodes;i++)
         {
             tedge[i]=new LinkedList();
             //tedge[i]=ad.getEdgeList(i);
         
             
             for (int j=0; j<ad.getEdgeList(i).size();j++)
             {   Node temp=new Node();
                 Edge edge=new Edge();
                 
                 temp.setId(f[ad.getEdgeList(i).get(j).getTail().getId()]);
                 //temp.setNext(null);
                 edge.setTail(temp);
                 temp=new Node();
                 temp.setId(f[ad.getEdgeList(i).get(j).getHead().getId()]);
                 //temp.setNext(null);
                 edge.setHead(temp);
                 edge.setDirected(ad.getEdgeList(i).get(j).getDirected());
                 edge.setWeight(ad.getEdgeList(i).get(j).getWeight());
                 tedge[i].add(edge);
                 
             }
         }
         LinkedList<Node> tnode=new LinkedList<>();
         tnode=ad.getVertexList();
         
         for (int i=0; i<tnode.size();i++)
         {
             tnode.get(i).setId(f[i]);
         }
        
        
         
         
      relabeled=new AdjacencyList(tnode,tedge);
         
         
     }
 
    /**
     * @param args the command line arguments
     */
 public static void main(String[] args) {
        // TODO code application logic here
    

     LinkedList<Node> n=new LinkedList<>();
     LinkedList<Edge>[] m=new LinkedList[9];
     Node tnode=new Node();
     
     for (int i=0; i<9;i++)
         m[i]=new LinkedList<Edge>();
     
     tnode.setId(0);
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
     
     
     
     Scc t=new Scc(n,m);
     t.DFSCalculateLoop();
}






}

    /**
     * @param args the command line arguments
     */
   
    

