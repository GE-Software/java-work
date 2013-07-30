/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstraimproved1;

import java.util.ArrayList;





/**
 *
 * @author Lenovo-BM
 */
public class Node {
    
       private int id;
       private ArrayList<Edge>next=new ArrayList<Edge>();
       private int score;
        
    public Node()
            {
              id=-1;
               next=null;
            }
    public Node(Node s)
    {
        id=s.getId();
        next=s.getNext();
        score=s.getScore();
    }

    public void setId(int t)
    {
        id=t;
    }
    public void setNext(ArrayList<Edge> t)
    {
      next=t;
      
    }
    public int getId()
    {
        return id;
    }
    public ArrayList<Edge> getNext()
    {
        
        return next;
    }
    
    
public void setScore(int score)
{
    this.score=score;
}

public int getScore()
{
    return score;
}

static public Node copy(Node s)
{
    Node temp=new Node();
    temp.id=s.id;
    temp.score=s.score;
    temp.next=s.next;
    return temp;
}

}