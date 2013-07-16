/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearchtree;

/**
 *
 * @author Lenovo-BM
 */
public class Binarysearchtree {

    public class Node{
        private int value;
        private Node left;
        private Node right;
        private Node parent;
    
        Node()
            {
                value=0;
                left=null;
                right=null;
                parent=null;
            }
        Node(int a)
            {
                
                value=a;
                left=null;
                right=null;
                parent=null;
            }
        Node(int a , Node b, Node c , Node d)
            {
                value=a;
                left=b;
                right=c;
                parent=d;
            }
        public void setvalue(int a)
        {
            value=a;
        }
        public void setleft(Node a)
        {
            left=a;
        }
        public void setright(Node a)
        {
            right=a;
        }
        public void setparent(Node a)
        {
            parent=a;
        }
        public int getvalue()
        {
            return value;
        }
        public Node getleft()
        {
            return left;
            
        }
        public Node getright()
        {
            return right;
        }
        public Node getparent()
        {
            return parent;
        }
    }
    
    Node root;
    
    Binarysearchtree()
    {
        root=new Node();
        root=null;
    }
    
    public void Insert (Node a)
    {
        Node b= new Node();
        Node c=new Node();
        b=null;
        c=root;
        while (c!=null)
        {
            b=c;
            if (a.getvalue()<c.getvalue())
                    c=c.getleft();
            else
                    c=c.getright();
        }
        a.setparent(b);
        
        if (b==null)
            root=a;
        else
            if (a.getvalue()<b.getvalue())
                b.setleft(a);
            else
                b.setright(a);
                
        }
    public Node delete(Node a)
    {
        Node b=new Node();
        Node c=new Node();
        
        if (a.getleft()==null || a.getright()==null)
            b=a;
        else
            b=successor(a);
        
        if (b.getleft()!=null)
            c=b.getleft();
        else
            c=b.getright();
        
        if (c!=null)
            c.setparent(b.getparent());
        
        if (b.getparent()==null)
            root=c;
        else
            if (b==(b.getparent()).getleft())
                (b.getparent()).setleft(c);
            else
                (b.getparent()).setright(c);
        
        if (b!=a)
            a.setvalue(b.getvalue());
        
        return b;
    }
    
    public Node successor(Node a)
    {
        Node b=new Node();
        if (a.getright()!=null)
            return minimum(a.getright());
        
        b=a.getparent();
        
        while (b!=null && a==b.getright())
        {
            a=b;
            b=b.getparent();
        }
        return b;
     }
    public Node predecessor(Node a)
    {
        Node b=new Node();
        if (a.getleft()!=null)
            return maximum(a.getleft());
        
        b=a.getparent();
        
        while (b!=null && a==b.getleft())
        {
            a=b;
            b=b.getparent();
        }
        return b;
     }  
    
    
    
    
    
    public Node minimum (Node a)
      {
          while (a.getleft()!=null)
              a=a.getleft();
          return a;
      
      }
     public Node maximum (Node a)
      {
          while (a.getright()!=null)
              a=a.getright();
          return a;
      
      }
    public Node search(Node b,int a)
    {
        if ( (b==null) || (a==b.getvalue()))
            return b;
        else
            if (a<b.getvalue())
                return search(b.getleft(),a);
            else
                return search(b.getright(),a);
     }
    
  
    
    
    public void simulate()
    {
        Node test=new Node(5,null,null,null);
        Insert(test);
        Node test1=new Node(1,null,null,null);
        Insert(test1);
        Node test2=new Node(10,null,null,null);
        Insert(test2);
        Node test3=new Node(2,null,null,null);
        Insert(test3);
        Node a=search(root,10);
        Node b=successor(test1);
        Node c=predecessor(test1);
        Node d=maximum(root);
    }
    public void Inorder(Node a)
    {
        if (a!=null)
                Inorder(a.getleft());
                //printf(a.getvalue());
                Inorder(a.getright());
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
   // 0Node test=new Node();
    Binarysearchtree tree= new Binarysearchtree();
    
    tree.simulate();
    
    
    }
}
