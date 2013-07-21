/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mst;

/**
 *
 * @author Lenovo-BM
 */
public class Mstnode {
  
   int id;
   Mstnode parent;
   Mstnode next;
   int weight;
   
   Mstnode()
   {
       
       id=0;
       parent=null;
       next=null;
       weight=0;
   }
        
}
