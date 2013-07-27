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
public class Node {
    
       private int id;
       private LinkedList<Edge>next=new LinkedList<Edge>();
        
        
    Node()
            {
              id=-1;

              next=null;
              
            }

    
    public void setId(int t)
    {
        id=t;
    }
    public void setNext(LinkedList<Edge> t)
    {
      next=t;
        /* LinkedList<Edge> temp=new LinkedList();
       Edge tedge;
        next=new LinkedList<Edge>();
       
        for (int i=0;i<t.size();i++)
       {
       tedge=new Edge();
       tedge.setHead(t.get(i).getHead());
       tedge.setTail(t.get(i).getTail());
       tedge.setWeight(t.get(i).getWeight());
       tedge.setDirected(t.get(i).getDirected());
       temp.add(tedge);               
       }
       next=temp;*/
    }
    public int getId()
    {
        return id;
    }
    public LinkedList<Edge> getNext()
    {
        
        return next;
    }
    
    }

