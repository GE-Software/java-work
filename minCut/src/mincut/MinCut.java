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
           
            removeVertex(edgeList.get(index).getTail(),edgeList.get(index).getHead());
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
       for (int i=j-1; i>=0;i--)
                    edgeList.remove(temp[i]);
    
    }
    
    
    private void removeVertex(Node t,Node h)
    {
        //if (vertexList.contains(t)) {
        //    vertexList.remove(t);
        //}
        
        if (vertexList.contains(t)) {
            vertexList.remove(t);
           
        }
        else
            if (vertexList.contains(h)){
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
    
    Node[] temp=new Node[8];
    
    for (int i=0; i<8;i++)
    {
        temp[i]=new Node();
        temp[i].setId(i);
        temp[i].setNext(null);
        v.add(temp[i]);
    }
    //
    
    Edge[] t=new Edge[14];
    
        for (int i=0; i<14; i++)
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
        t[3].setTail(temp[0]);
        t[3].setHead(temp[3]);// changed from 6 for testing
        
        //t[2].setTail(temp[1]);
        //t[2].setHead(temp[0]);
        t[4].setTail(temp[1]);
        t[4].setHead(temp[2]);
        t[5].setTail(temp[1]);
        t[5].setHead(temp[3]);
        
        t[6].setTail(temp[2]);
        t[6].setHead(temp[3]);
        //t[4].setTail(temp[2]);
        //t[4].setHead(temp[0]);
        //t[5].setTail(temp[2]);
        //t[5].setHead(temp[1]);
        t[7].setTail(temp[3]);
        t[7].setHead(temp[4]);
        t[8].setTail(temp[4]);
        t[8].setHead(temp[5]);
        t[9].setTail(temp[4]);
        t[9].setHead(temp[6]);
        t[10].setTail(temp[5]);
        t[10].setHead(temp[6]);
        t[11].setTail(temp[5]);
        t[11].setHead(temp[7]);
        t[12].setTail(temp[6]);
        t[12].setHead(temp[7]);
        t[13].setTail(temp[4]);
        t[13].setHead(temp[7]);
        
        
        
    for (int i=0; i<14; i++)
        {
           e.add(t[i]);
            
        }
        
         
        
        MinCut mc=new MinCut(8,14,v,e);

        int result=mc.minCut();
        System.out.println("# of edges"+result);
        
        System.out.println(mc.vertexList.get(0).getId());
        System.out.println(mc.vertexList.get(1).getId());
        
        for (int i=0; i<mc.edgeList.size();i++)
        {
            System.out.print(mc.edgeList.get(i).getTail().getId());
            System.out.print("     ");
            System.out.print(mc.edgeList.get(i).getHead().getId());
            System.out.println();
        }
     }
    }

