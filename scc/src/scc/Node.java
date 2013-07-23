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
       //LinkedList<Edge> temp=new LinkedList();
       //Edge tedge;
        next=new LinkedList<Edge>();
       for (int i=0;i<t.size();i++)
       {
       next.add(t.get(i));               
       }
       
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

