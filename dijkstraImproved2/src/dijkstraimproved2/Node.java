/*
 * To change this template, choo
 * and open the template in the editor.
 */
package dijkstraimproved2;

import java.util.ArrayList;

/**
 *
 * @author Lenovo-BM
 */
public class Node {
    
       private int id;
       private ArrayList<Edge>next=new ArrayList<Edge>();
       public int score;
       final int inf=1000000; 
    public Node()
            {
              id=-1;
               next=null;
               score=inf;
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
    
    
static public Node copy(Node s)
{
    Node temp=new Node();
    temp.id=s.id;
    temp.next=s.next;
    temp.score=s.score;
    return temp;
    
}
 public int getScore()
{
    return score;
}
public void setScore(int s)
{
    score=s;
}

}