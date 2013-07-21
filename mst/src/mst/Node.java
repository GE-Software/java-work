/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mst;

/**
 *
 * @author Lenovo-BM
 */
public class Node {
    
        int id;
        Node Next;
        Node Down;
        int weight;
        
    Node()
            {
              id=-1;
              
              
              weight=0;
              Next=null;
              Down=null;
            }
    }

