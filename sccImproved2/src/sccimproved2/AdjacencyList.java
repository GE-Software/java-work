/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sccimproved2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Lenovo-BM
 */
public class AdjacencyList {
 
    Edge[][] al;
    int size;
    
    public AdjacencyList(int size)
    {
        
        this.size=size;
        al=new Edge[size][];
        
        
    }
    
    
   public void build(Edge[] m,int index)
   {
        al[index]=m; 
   }
}
    
    
    
    
    
    
    
    
    
    