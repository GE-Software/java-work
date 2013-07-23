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
public class DFS {
    AdjacencyList ad;
    boolean[]explored;
    StringBuilder output;
    
    public DFS(LinkedList<Node> n, LinkedList<Edge>[] m)
    {
       explored=new boolean[n.size()];
        ad=new AdjacencyList(n,m);
       for (int i=0; i<n.size();i++)
           explored[i]=false;
       output=new StringBuilder();
    }
public void DFSCalculate(int startIndex)
{
    explored[startIndex]=true;
    LinkedList<Edge> edge=new LinkedList<>();
    edge=ad.getEdgeList(startIndex);
    for (int i=0; i<edge.size();i++)
    {
        if (explored[edge.get(i).getHead().getId()]==false)
        DFSCalculate(edge.get(i).getHead().getId());
    }
    output.append(startIndex);
}
public StringBuilder getOutput()
{
    return output;
}

 /*public static void main(String[] args) {
     
     LinkedList<Node> n=new LinkedList<>();
     LinkedList<Edge>[] m=new LinkedList[4];
     Node tnode=new Node();
     Edge tedge=new Edge();
     for (int i=0; i<4;i++)
         m[i]=new LinkedList<>();
     
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
      
     tedge.setTail(n.get(0));
     tedge.setHead(n.get(1));
     tedge.setDirected(true);
     m[0].add(tedge);
     
     tedge=new Edge();
     tedge.setTail(n.get(0));
     tedge.setHead(n.get(2));
     tedge.setDirected(true);
     m[0].add(tedge);
     
     tedge=new Edge();
     tedge.setTail(n.get(1));
     tedge.setHead(n.get(3));
     tedge.setDirected(true);
     m[1].add(tedge);
     
     tedge=new Edge();
     tedge.setTail(n.get(2));
     tedge.setHead(n.get(3));
     tedge.setDirected(true);
     m[2].add(tedge);
      
     
     DFS dfs=new DFS(n,m);
     dfs.DFSCalculate(0);      
     
     StringBuilder t=new StringBuilder();
     t=dfs.getOutput();
     // TODO code application logic here
    }

 */



}
