/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mincut;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Lenovo-BM
 */
public class MinCut {

    /**
     * @param args the command line arguments
     */

    List<Edge> edgeList=new LinkedList<>();
    List<Node> vertexList=new LinkedList<>();
    UF uf;
    Random randomGenerator=new Random();
    
    public MinCut(int n, int m,List v, List e ) // n # of vertices , m # of edges
    {
        uf=new UF(n);
        edgeList=e;
        vertexList=v;
    }
    public int minCut()
    {
        int index;
        int tailId, headId;
        
        while (vertexList.size()>2)
        {
           if (edgeList.size()>0){
            index=getRandom(edgeList.size());
            tailId=edgeList.get(index).getTail().getId();
            headId=edgeList.get(index).getHead().getId();
            uf.union(tailId, headId);
           /** Node vremove=new Node();
            vremove.setId(edgeList.get(index).getHead().getId());
            vremove.setNext(edgeList.get(index).getHead().getNext());
            */
            removeVertex(edgeList.get(index).getHead());
            removeSelfLoops();
            
        }
        }
        return edgeList.size();
    }
    private void removeSelfLoops()
    {
        int t, h;
        int[] temp=new int[edgeList.size()];
        int j=0;
        
        for (int i=0; i<edgeList.size();i++)
        {
            t=edgeList.get(i).getTail().getId();
            h=edgeList.get(i).getHead().getId();
            if (uf.connected(t, h)) {
                temp[j]=i;
                j=j+1;          
            }
        }
       for (int i=0; i<j;i++)
                    edgeList.remove(temp[i]);
    
    }
    
    
    private void removeVertex(Node h)
    {
        //if (vertexList.contains(t)) {
        //    vertexList.remove(t);
        //}
        
        if (vertexList.contains(h)) {
            vertexList.remove(h);
        }
     }
    
    
    
    private int getRandom(int m) // m is the # of remaining Edges in Edges list
    {
            
            return randomGenerator.nextInt(m);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
  
        
    List<Node> v=new LinkedList<>();
    List<Edge> e=new LinkedList<>();
    
    Node[] temp=new Node[4];
    
    for (int i=0; i<4;i++)
    {
        temp[i]=new Node();
        temp[i].setId(i);
        temp[i].setNext(null);
        v.add(temp[i]);
    }
    //
    
    Edge[] t=new Edge[6];
    
        for (int i=0; i<6; i++)
        {
            t[i]=new Edge();
            t[i].setDirected(false);
            
        }
        t[0].setTail(temp[0]);
        t[0].setHead(temp[1]);
        t[1].setTail(temp[0]);
        t[1].setHead(temp[2]);
        t[2].setTail(temp[0]);
        t[2].setHead(temp[3]);
        //t[2].setTail(temp[1]);
        //t[2].setHead(temp[0]);
        t[3].setTail(temp[1]);
        t[3].setHead(temp[2]);
        t[4].setTail(temp[1]);
        t[4].setHead(temp[3]);
        
        t[5].setTail(temp[2]);
        t[5].setHead(temp[3]);
        //t[4].setTail(temp[2]);
        //t[4].setHead(temp[0]);
        //t[5].setTail(temp[2]);
        //t[5].setHead(temp[1]);
    
    for (int i=0; i<6; i++)
        {
           e.add(t[i]);
            
        }
        
         
        
        MinCut mc=new MinCut(4,6,v,e);

        int result=mc.minCut();
        System.out.println(result);
        for (int i=0; i<mc.edgeList.size();i++)
        {
            System.out.print(mc.edgeList.get(i).getTail().getId());
            System.out.print("     ");
            System.out.print(mc.edgeList.get(i).getHead().getId());
            System.out.println();
        }
     }
    }

