/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sscimproved;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Lenovo-BM
 */
public class AdjacencyList {
 
    ArrayList<Edge>[] al;
    int size;
    
    public AdjacencyList(int size)
    {
        
        this.size=size;
        al=new ArrayList[size];
        for (int i=0; i<size;i++)
            al[i]=new ArrayList<>();
        
    }
    
    
   public void build(ArrayList<Edge> m,int index)
   {
        al[index]=m; 
   }
}
    
    
    
    
    
    
    
    
    
    