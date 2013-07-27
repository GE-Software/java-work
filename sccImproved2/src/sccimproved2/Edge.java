/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sccimproved2;

import java.util.LinkedList;

/**
 *
 * @author Lenovo-BM
 */

public class Edge {

    private  int tail;
    private int head;
    //private boolean directed;
    //private int weight;

    
    public Edge()
    {
    }
    public int getTail()
    {
               
        return tail;
    }
    public int getHead()
    {
                 
        return head;
    }
    /*public boolean getDirected()
    {
        return directed;
    }*/
    /*public int getWeight()
    {
        return weight;
    }*/
public void setTail(int t)
    {
        
        tail=t;
    }
    public void setHead(int t)
    {
        head=t;
        
    }
    /*public void setDirected(boolean t)
    {
        directed=t;
    }*/
    /*public void setWeight(int t)
    {
        weight=t;
    }*/

public void setEdge(int tail,int head, boolean directed,int weight)
{
    this.tail=tail;
    this.head=head;
    //this.directed=directed;
    //this.weight=weight;
}
public void setEdge(int tail,int head)
{
    this.tail=tail;
    this.head=head;
    //this.directed=directed;
    //this.weight=weight;
}
}
