/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstraimproved1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Lenovo-BM
 */
public class DijkstraImproved1 {

    AdjacencyList al;
    Heap heap;
    Node start;
    boolean[] explored;
    int nNodes;
    //int a[];
    final int inf = 1000000;

    public DijkstraImproved1(int nNodes, Node s,AdjacencyList alInput) throws FileNotFoundException, IOException {
        al=new AdjacencyList();
        al=alInput;
        heap = new Heap(nNodes);
        explored = new boolean[nNodes];
        start = new Node(s);
        for (int i = 0; i < nNodes; i++) {
            explored[i] = false;
            al.vertex.get(i).setScore(inf);
       
        }
        al.vertex.get(start.getId()).setScore(0);
        this.nNodes = nNodes;
        readfile("DijkstraData.txt");
        // at this point al is ready for use.
        buildHeap();
        calculateScore(start);
        explored[start.getId()] = true;

    }

    private void calculateScore(Node s) {
        int tempScore;
        int index = s.getId();

        for (int i = 0; i < al.vertex.get(index).getNext().size(); i++) {
            if (explored[al.vertex.get(index).getNext().get(i).getHead().getId()] == false) {
                tempScore = al.vertex.get(index).getScore() + al.getEdge(index, i).getWeight();
                int headId=al.vertex.get(index).getNext().get(i).getHead().getId();
                if (tempScore < al.vertex.get(headId).getScore()) 
                {
                    heap.deleteByValue(headId);
                    al.vertex.get(headId).setScore(tempScore);
                    heap.insert(al.vertex.get(headId));

                }
            }
        }

    }

    public void calculateDijkstra() {
        while (heap.getSize() > 0) {
            Node s = heap.deletemin();
            explored[s.getId()] = true;
            calculateScore(s);
        }
    }

    private void buildHeap() {

        Node[] temp = new Node[nNodes];
        temp = al.getVertexArray();
        heap.heapify(temp);
        heap.deleteByValue(start.getId());
    }

    // need modifications in order to accomodate the file structure
    private void readfile(String filename) throws FileNotFoundException, IOException {
        // initialization of the arraylist to be used for the initialization of the Adjacency List

        ArrayList<Node> nodes = new ArrayList();
        ArrayList<Edge>[] edges = new ArrayList[nNodes];
        for (int i = 0; i < nNodes; i++) {
            edges[i] = new ArrayList();
        }
        /////////////

        String line = new String();
        line = "";

        File file = new File(filename);
        Scanner s;
        BufferedReader bf = new BufferedReader(new FileReader(file));
        
        s = new Scanner(bf);

        int previousTailId = 0;
        int currentTailId = 0;
        int currentHeadId = 0;

        while (s.hasNextLine()) {

            line = s.nextLine();
            line=line.replace(","," ");
            line=line.replace("\t"," ");
            String[] parts = line.split(" ");
            
            
            currentTailId = Integer.parseInt(parts[0]) - 1;
             
            

            if (currentTailId == previousTailId) {
                //System.out.println(currentTailId + "   " + currentHeadId);

                //adalHead[previousTailId].add(currentHeadId);

                previousTailId = currentTailId;

            } else {
                if (currentTailId - previousTailId > 1) {
                    for (int i = 1; i < currentTailId - previousTailId; i++) {
                        //adalTail[previousTailId + i]=new ArrayList();
                        //      adalHead[previousTailId + i] = new ArrayList();
                    }
                }


                //System.out.println(currentTailId + "   " + currentHeadId);

                previousTailId = currentTailId;
                //adalHead[previousTailId].add(currentHeadId);
                //adalTail[previousTailId].add(currentTailId);
            }
        }
        for (int i = 0; i < nNodes; i++) //            adalHead[i].trimToSize();
        {
            bf.close();
        }
        s.close();

        al = new AdjacencyList(nodes, edges);

    }

    /**
     * @param args the command line arguments
     */
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
   
    
    Edge tedge=new Edge();
    ArrayList edgeArray=new ArrayList();
    ArrayList[] edges=new ArrayList[5];
    Node[] temp=new Node[4];
    
    for (int i=0; i<4;i++)
    {
        temp[i]=new Node();
        
    }
    for (int i=0; i<5;i++)
        edges[i]=new ArrayList();
    
    temp[0].setId(0);
    temp[1].setId(1);
    temp[2].setId(2);
    temp[3].setId(3);
    
    
    tedge.setTail(temp[0]);
    tedge.setHead(temp[1]);
    tedge.setDirected(true);
    tedge.setWeight(1);
    edgeArray.add(tedge);
    
    
    
    tedge=new Edge();
    tedge.setTail(temp[0]);
    tedge.setHead(temp[2]);
    tedge.setDirected(true);
    tedge.setWeight(4);
    edgeArray.add(tedge);
    temp[0].setNext(edgeArray);
    
    
    
    
    edgeArray=new ArrayList();
    tedge=new Edge();
    tedge.setTail(temp[1]);
    tedge.setHead(temp[3]);
    tedge.setDirected(true);
    tedge.setWeight(6);
    edgeArray.add(tedge);
    
    
    tedge=new Edge();
    tedge.setTail(temp[1]);
    tedge.setHead(temp[2]);
    tedge.setDirected(true);
    tedge.setWeight(2);
    edgeArray.add(tedge);
    temp[1].setNext(edgeArray);
    
    edgeArray=new ArrayList();
    tedge=new Edge();
    tedge.setTail(temp[2]);
    tedge.setHead(temp[3]);
    tedge.setDirected(true);
    tedge.setWeight(3);
    edgeArray.add(tedge);
    temp[2].setNext(edgeArray);
    
    
    temp[3].setNext(new ArrayList());
    
    AdjacencyList al=new AdjacencyList();
    al.setVertex(temp);
    
    DijkstraImproved1 d;
   try{
        d=new DijkstraImproved1(4,temp[0],al);
       
        d.calculateDijkstra();
   }
   catch(Exception e)
   {
       
   }
    
    
    
    
}
}