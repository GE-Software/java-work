/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mst;

/**
 *
 * @author Lenovo-BM
 */
public class Mstoutputlist {
    Mstnode Root=new Mstnode();
   
    
    public void addnode(Mstnode child)
   {
       Mstnode temp=new Mstnode();
       Mstnode Pointer=Root;
       if (Root==null)
           Root=child;
       else
       {    
       while (Pointer.next!=null)
           Pointer=Pointer.next;
       
       temp.id=child.id;
       temp.parent=child.parent;
       temp.next=null;
       temp.weight=child.weight;
       Pointer.next=temp;
        }
   }
}
