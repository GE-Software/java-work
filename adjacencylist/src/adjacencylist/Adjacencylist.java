/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adjacencylist;

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

    public void addedge(int id,Node a,int w)
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
            c.weight=w;
            c.Down=null;
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
    /**
    public Node deletevertex(Node previous,Node current,Node a)
    {
        Node temp=new Node();
        if (current==null)
                return null;
        if (current.id==a.id)
        {
            if (current!=Root)
            {
                
                temp=current.Down;
                previous.Down=temp;
                return current;
            }
            else
            {
                Root=Root.Down;
                temp=current.Down;
                previous.Down=temp;
                return current;
                
            }
        }
        else
        {
            return deletevertex(previous.Down,current.Down,a);
        }
    }
    **/
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
    
   /** 
    public Node deletedge(Node current,Node a)
    {
        Node vertex=new Node();
        Node first=new Node();
        vertex=findvertex(current.id);
        if (vertex==null)
            return null;
        else
            first=vertex.Next;
        
        return deletefromlist(first,first,a);
        
    }   
        
    public Node deletefromlist(Node previous, Node current, Node a)
    {
            Node temp = new Node();
            if (current==null)
                    return null;
            if (current.id==a.id)
                {
                    temp=current.Next;
                    previous.Next=temp;
                    current.Next=null;
                    return current;
                    
                }
        
        else
        {
            return deletefromlist(previous.Down,current.Down,a);
        }
    }
    **/
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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    Node[] g=new Node[5] ;
    Adjacencylist list=new Adjacencylist(); 
    for (int i=0;i<5;i++)
    {
        g[i]=new Node();
        g[i].id=i;
        g[i].Next=null;
        g[i].Down=null;
        g[i].weight=0;
        list.addvertex(g[i]);
    }
    Node t=g[4];
    int[] output=new int[5];
    
    output=list.outputvertices(5);
    // up to hear is correct
    list.addedge(0,g[1],1);
    list.addedge(0,g[3],4);
    list.addedge(0,g[4],5);
    list.addedge(1,g[0],1);
    list.addedge(1,g[2],2);
    list.addedge(2,g[1],2);
    list.addedge(2,g[3],3);
    list.addedge(2,g[4],6);
    list.addedge(3,g[0],4);
    list.addedge(3,g[2],3);
    list.addedge(4,g[0],5);
    list.addedge(4,g[2],6);
    
    int[] output1=new int[17];
    
    //output1=list.outputvertices(5);
    output1=list.outputverticesnodes(17);
    //list.deletevertex(4);
    //int[] output2=new int[4];
    
    //output2=list.outputvertices(4);
   // list.deleteedge(2,3);
    //int[] output2=new int[16];
    //utput2=list.outputverticesnodes(16);
    }
}
