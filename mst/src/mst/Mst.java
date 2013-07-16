/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mst;
import java.util.Random;


/**
 *
 * @author Lenovo-BM
 */
public class Mst {

    Heap H;
    Uniquerandom Ur;
    Unionfind Uf;
    Adjacencylist Al;
    Uniquerandom Urn; // for nodes
    Uniquerandom Urw;// for weight
    Mstoutputlist Mo;
    Mst(int Graphsize)
    {
        Random randomgenerator = new Random();
        Uf=new Unionfind(Graphsize);
        Ur=new Uniquerandom();
        Al=new Adjacencylist();
        Mo=new Mstoutputlist();
        H=new Heap(3);
        InitializeNodes(Graphsize);
        InitializeEdges(Graphsize);
        InitializeUnion(Graphsize);
        
    }
    private void InitializeNodes(int Graphsize)
    {
        for (int i=0;i<Graphsize;i++)
        {
            Node a=new Node();
            a.id=i;
            Al.addvertex(a);
        }
    }
    private void InitializeEdges(int Graphsize) // need revision for
    {
        int a1=0,a2=0,a3=0;
        Random randomgenerator=new Random();
        Urw=new Uniquerandom();
        int[]weight=new int[3*Graphsize];
        weight=Urw.Generate(3*Graphsize,0,6*Graphsize);
        for (int i=0; i<Graphsize;i++)
        {
            while (a1==a2 || a2==a3||a1==a3)
                {
                    a1=randomgenerator.nextInt(Graphsize);
                    a2=randomgenerator.nextInt(Graphsize);
                    a3=randomgenerator.nextInt(Graphsize);
                }
            Node a1node=new Node();
            Node a2node=new Node();
            Node a3node=new Node();
            a1node.id=a1;
            a2node.id=a2;
            a3node.id=a3;
            a1node.weight=weight[3*i];
            a2node.weight=weight[3*i+1];
            a3node.weight=weight[3*i+2];
            Al.addedge(i,a1node);
            Al.addedge(i,a2node);
            Al.addedge(i, a3node);
            
          }
    }
    private void InitializeUnion(int Graphsize)
    {
        Uf.union(0, 0);
        
        for (int i=1;i<Graphsize;i++)
            Uf.union(i,Graphsize);
    }
    public void CalculateMST(int Graphsize)
    {
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
