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
public class BFS {
    AdjacencyList ad;
    boolean[]explored;
    StringBuilder output; 
    LinkedList<Integer> queue;
    
public BFS(LinkedList<Node> n, LinkedList<Edge>[] m)
{
        explored=new boolean[n.size()];
        ad=new AdjacencyList(n,m);
        for (int i=0; i<n.size();i++)
                explored[i]=false;
        output=new StringBuilder();
        queue=new LinkedList<>();
}
public void BFSCalculate (int startIndex)
{
    explored[startIndex]=true;
    queue.add(startIndex);
    output.append(startIndex);
    while (queue.size()!=0)
    {
        int t=queue.poll();
        LinkedList<Edge> edge=new LinkedList<>();
        edge=ad.getEdgeList(t);
        for (int i=0; i<edge.size();i++)
           if (explored[edge.get(i).getHead().getId()]==false)
           {
               queue.add(edge.get(i).getHead().getId());
               explored[edge.get(i).getHead().getId()]=true;
               output.append(edge.get(i).getHead().getId());
           }
    }
}
public StringBuilder getOutput()
{
    return output;
}

}





