/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package strongConnectedComponents;


import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Lenovo-BM
 */
public class Node {
    
       private int id;
       private Edge next=new Edge();
        
        
    Node()
            {
              id=-1;

              next=null;
              
            }

    
    public void setId(int t)
    {
        id=t;
    }
    public void setNext(Edge t)
    {
        next=t;
    }
    public int getId()
    {
        return id;
    }
    public Edge getNext()
    {
        return next;
    }
    
    }

