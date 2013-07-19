/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mincut;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Lenovo-BM
 */
public class AdjacencyList {
 
    List<Node> vertex=new LinkedList<Node>();
    List<Edge>[] edge;
    int n; // number of vertices
    int m; // number of edges
    
    AdjacencyList(int n , int m)
    {
        this.n=n;
        this.m=m;
        edge = new LinkedList[m];
    }
    public void readVertex(Node[]a )
    {
        for (int i=0; i<a.length;i++)
        {
            Node tempNode=new Node();
            tempNode.setId(a[i].getId());
            tempNode.setNext(a[i].getNext());
            vertex.add(tempNode);
        }
    }
    
    public void readEdges(Edge[] a)
    {
        int temp;
        if (a[0].getDirected()==false)
        {
            for (int i=0; i<a.length;i++)
            {
                temp=vertex.indexOf(a[i].getTail());
                edge[temp].add(a[i]);
                temp=vertex.indexOf(a[i].getHead());
                edge[temp].add(a[i]);
            }
        }
        else
        {
            for (int i=0; i<a.length;i++)
            {
                temp=vertex.indexOf(a[i].getTail());
                edge[temp].add(a[i]);
            }
        }
    }

    public void build()
    {
        int t;
        t=vertex.size();
        for (int i=0; i<t;i++)
        {
            vertex.get(i).setNext(edge[i].get(0));
        }
    }
    
    
}
