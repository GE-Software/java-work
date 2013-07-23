/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scc;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Lenovo-BM
 */
public class AdjacencyList {
 
    LinkedList<Node> vertex=new LinkedList<>();
    
    
    
    AdjacencyList(LinkedList<Node> n , LinkedList<Edge>[] m)
    {
        
        
        
        for (int i=0; i<n.size();i++)
        {
           Node tnode=new Node();
           tnode.setId(n.get(i).getId());
           tnode.setNext(m[i]);
            vertex.add(tnode);
            //vertex.get(i).setNext(m[i]);
         
        }
      
    }
    public Node getVertex(int index)
    {
        return vertex.get(index);
    }
    
    public LinkedList<Node> getVertexList()
    {
        return vertex;
    }
    
    public void setVertex(Node[]a )
    {
        for (int i=0; i<a.length;i++)
        {
            Node tempNode=new Node();
            tempNode.setId(a[i].getId());
            tempNode.setNext(a[i].getNext());
            vertex.add(tempNode);
        }
    }
    
    public Edge getEdge(int vertexIndex, int edgeIndex)
    {
        return vertex.get(vertexIndex).getNext().get(edgeIndex);
    }
    public LinkedList<Edge> getEdgeList(int vertexIndex)
    {
        return vertex.get(vertexIndex).getNext();
    }
    
    
    
    
    public void setEdges(LinkedList<Edge>[] a)
    {
           for (int i=0; i<a.length;i++)
            {
                vertex.get(i).setNext(a[i]);
            }
    }

    
    
    
    
}
