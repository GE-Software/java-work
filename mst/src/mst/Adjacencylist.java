/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package mst;

/**
 *
 * @author Lenovo-BM
 */
public class Adjacencylist {

    Node Root;
    
    Adjacencylist()
        {
         //Root=new Node();
         //Root=null;
        }
    public Node findvertex(int id)
    {
        Node Vertexpointer=new Node();
        Vertexpointer=Root;
        boolean found=false;
        if (Vertexpointer.id==id)
            {
                return Vertexpointer;
            }
        else
            {
                while (Vertexpointer.Down!=null && found!=true)
                {
                    Vertexpointer=Vertexpointer.Down;
                    if (Vertexpointer.id==id)
                    {
                        found=true;
                    }    
                }
                if (found==true)
                    {
                        return Vertexpointer;
                    }
        
                else
                    {
                        return null;
                    }
        }
    }
    public Node FindEdge(int vid, int id)
    {
        Node temp=new Node();
        temp=findvertex(vid);
        boolean found=false;
        
        while (temp!=null && temp.Next!=null && found !=true)
        {
            temp=temp.Next;
            if (temp.id==id)
            {
                found=true;
                return temp;
            }
                
        }
        return null;
      }
   
    
    
    public void addedge(int id,Node a)
    {
       Node b=new Node();
        Node c=new Node();
        Node temp=new Node();
       
        b=findvertex(id);
        if (b!=null)
        {
            c.id=a.id;
            c.Down=a.Down;
            c.weight=a.weight;
            c.Next=a.Next;
            
            
            temp=b.Next;
            b.Next=c;
            c.Next=temp;
            //c.weight=w;
            c. Down=null;
        }   
        // else  it is possible to throw an exception to show that the vertex is not find
    
    }
    /** add vertex to the beginning of the vertices
    **/
    public void addvertex(Node a)
    {
        Node c=new Node();
        Node temp=new Node();
        
        c.id=a.id;
        c.weight=a.weight;
        c.Down=a.Down;
        c.Next=a.Next;
        
        if (Root==null)
        
           Root=c;
        
        else
        {
            temp=Root;
            Root=c;
            Root.Down=temp;
           // Root.Next=null; // not sure if this is necessary for testing
        }
    }
    
    public void deletevertex(int id)
    {
        Node b=new Node();
        Node temp=new Node();
        boolean found=false;
        b=Root;
        if (b.id==id)
        {
            Root=b.Down;
        }
        else
            while (b.Down!=null && found !=true)
            {
                temp=b;
                b=b.Down;
                if (b.id==id)
                {
                    found=true;
                    temp.Down=b.Down;
                }
                
            }
    
    }
    
  
    public void deleteedge(int vertexid,int edgeid)
    {
        Node b=new Node();
        boolean found=false;
        Node previous=new Node();
        Node current=new Node();
        
        b=findvertex(vertexid);
        previous=b.Next;
        current=b.Next;
        if (current.id==edgeid)
        {
            b.Next=current.Next;
        }
        else
        {
            while (current.Next!=null && found!=true)
            {
                previous=current;
                current=current.Next;
                if (current.id==edgeid)
                {
                    found=true;
                    previous.Next=current.Next;
                }
            }
        }
    }
    public int[] outputvertices(int nvertices)
    {
        int [] a=new int [nvertices];
        int i=0;
        Node current=Root;
        if (current==null) 
            return a;
        else
        {
            a[i]=current.id;
            i=i+1;
            while (current.Down!=null && i<nvertices)
            {   
                current=current.Down;
                a[i]=current.id;
                i=i+1;
            }
            return a;
        }
    }
    
    public int[] outputverticesnodes(int nelements) // module for testing pusposes
    {
        int[] a=new int[nelements];
        int i=0;
        Node currentvertex=new Node();
        Node currentedge=new Node();
        
        currentvertex=Root;
        if (currentvertex==null)
            return a;
        else
        {   
         while (currentvertex!=null)
            {
                currentedge=currentvertex.Next;
                a[i]=currentvertex.id;
                i=i+1;
                while (currentedge!=null)
                {
                    a[i]=currentedge.id;
                    i=i+1;
                    currentedge=currentedge.Next;
                }
                currentvertex=currentvertex.Down;
            }
        
            return a;
            }
    }
    
  
}
