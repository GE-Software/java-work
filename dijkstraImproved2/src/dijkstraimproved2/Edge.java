/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstraimproved2;


/**
 *
 * @author Lenovo-BM
 */

public class Edge {

    private  Node tail;
    private Node head;
    private boolean directed;
    private int weight;
    final int inf=1000000;
    //private int score; 
    public Edge()
    {
        tail=new Node();
        head=new Node();
       
        directed=false;
        weight=inf;
        //score=inf;
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
   /* public void setScore(int s)
    {
        score=s;
    }
    public int getScore()
    {
        return score;
    }*/
    public static Edge copy(Edge s)
    {
        Edge temp=new Edge();
        temp.head=s.head;
        temp.tail=s.tail;
        //temp.score=s.score;
        temp.weight=s.weight;
        return temp;
    }
}
