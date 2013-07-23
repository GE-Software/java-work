/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package strongConnectedComponents;

/**
 *
 * @author Lenovo-BM
 */

public class Edge {

    private  Node tail;
    private Node head;
    private boolean directed;
    private int weight;

    
    public Edge()
    {
        tail=null;
        head=null;
        directed=false;
        weight=0;
    }
    public Node getTail()
    {
        return tail;
    }
    public Node getHead()
    {
        return head;
    }
    public boolean getDirected()
    {
        return directed;
    }
    public int getWeight()
    {
        return weight;
    }
public void setTail(Node t)
    {
        tail=t;
    }
    public void setHead(Node t)
    {
        head=t;
    }
    public void setDirected(boolean t)
    {
        directed=t;
    }
    public void setWeight(int t)
    {
        weight=t;
    }



}
