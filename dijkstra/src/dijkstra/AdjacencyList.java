/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

import java.util.ArrayList;

/**
 *
 * @author Lenovo-BM
 */
public class AdjacencyList {
 
   ArrayList<Node> vertex;
    
    
    
    AdjacencyList(ArrayList<Node> n , ArrayList<Edge>[] m)
    {
        
        vertex=new ArrayList<>();
        
        for (int i=0; i<n.size();i++)
        {
           Node tnode=new Node();
           tnode.setId(n.get(i).getId());
           tnode.setNext(m[i]);
            vertex.add(tnode);
            //vertex.get(i).setNext(m[i]);
         
        }
      
    }
   AdjacencyList(ArrayList<Node> n , ArrayList<Edge> m,int index)
   {
        if (vertex==null)
            vertex=new ArrayList<>();
           Node tnode=new Node();
           tnode.setId(index);
           tnode.setNext(m);
            vertex.add(tnode);
            //vertex.get(i).setNext(m[i]);
         
     }
   
   public AdjacencyList()
   {
   vertex=new ArrayList();
   }
    public Node getVertex(int index)
    {
        return vertex.get(index);
    }
    
    public ArrayList<Node> getVertexList()
    {
        return vertex;
    }
    public Node[] getVertexArray()
    {
         Node[] temp=new Node[vertex.size()];
         
        for (int i=0;i<vertex.size();i++)
          temp[i]=vertex.get(i);
    return temp;
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
    public ArrayList<Edge> getEdgeList(int vertexIndex)
    {
        return vertex.get(vertexIndex).getNext();
    }
    
    public void setEdges(ArrayList<Edge>[] a)
    {
           for (int i=0; i<a.length;i++)
            {
                vertex.get(i).setNext(a[i]);
            }
    }

    
    
    
    
}
