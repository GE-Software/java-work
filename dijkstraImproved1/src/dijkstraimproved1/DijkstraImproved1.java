
/*fast implementation of the Dijkstra alogrithm with heap that allows log(n) deletion from any place 
 * extremely suitable for use with Dijkstra
 */
package dijkstraimproved1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DijkstraImproved1 {

    AdjacencyList al;
    Heap heap;
    int start;
    boolean[] explored;
    int nNodes;
    static final int inf = 1000000;

    public DijkstraImproved1(int nNodes, int s) {

        al = new AdjacencyList();
        heap = new Heap(nNodes);
        explored = new boolean[nNodes];

        for (int i = 0; i < nNodes; i++) {
            explored[i] = false;

        }
        this.nNodes = nNodes;
        try {
            readfile("DijkstraData.txt");
        } catch (Exception e) {
        };
        // at this point al is ready for use.
        start = s;

        Heap.a[s] = 0;
        buildHeap();
       int min= heap.deletemin();// to delete start element 
        calculateScore(start);
       

        explored[start] = true;

    }

    private void calculateScore(int index) {
        int tempScore;

        int headId ;
        for (int i = 0; i < al.getNextSize(index); i++) {
             headId = al.getHeadId(index, i);
            if (explored[headId] == false) {
                tempScore = Heap.a[index] + al.getEdgeWeight(index, i);
               

                if (tempScore < heap.a[headId]) {
                    heap.deleteByValue(headId);
                    Heap.a[headId] = tempScore;
                    heap.insert(headId);
                }
            }
        }
    }

    public void calculateDijkstra() {
        while (heap.getSize() > 0) {
            int s = heap.deletemin();
           // this code is only for testing that the min is actually deleted from the heap
            
            
            explored[s] = true;
            calculateScore(s);
        }
    }

    private void buildHeap() {

        int[] temp = new int[nNodes];
        temp = al.getVertexIndexArray();
        for (int i=0; i<al.getVertexIndexArray().length;i++)
            heap.insert(temp[i]);
    }

    private void readfile(String filename) throws FileNotFoundException, IOException {
        // initialization of the arraylist to be used for the initialization of the Adjacency List

        ArrayList<Node> nodes = new ArrayList();
        ArrayList<Edge>[] edges = new ArrayList[nNodes];
        for (int i = 0; i < nNodes; i++) {
            edges[i] = new ArrayList();
        }

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
            line = line.replace(",", " ");
            line = line.replace("\t", " ");
            line = line.replace("  ", " ");
            line = line.replace("   ", " ");
            line = line.replace("    ", " ");
            line = line.replace("     ", " ");
            String[] parts = line.split(" ");

            int currentWeight;
            Node tnode = new Node();
            Node tnodeHead;
            Edge tedge = new Edge();
            tnode.setId(Integer.parseInt(parts[0]) - 1);

            if (tnode.getId() - previousTailId > 1) {
                for (int i = 1; i < tnode.getId() - previousTailId; i++) {
                    Node temp = new Node();
                    temp.setId(previousTailId + i);
                    temp.setNext(null);
                    nodes.add(temp);
                }
            }
            nodes.add(tnode);
            currentTailId = Integer.parseInt(parts[0]) - 1;
            for (int i = 1; i < parts.length; i++) {
                currentHeadId = Integer.parseInt(parts[i]) - 1;
                currentWeight = Integer.parseInt(parts[i + 1]);
                tedge = new Edge();
                tedge.setTail(tnode);
                tedge.setWeight(currentWeight);
                tnodeHead = new Node();
                tnodeHead.setId(currentHeadId);
                tnodeHead.setNext(null);
                Heap.a[currentHeadId] = inf;
                tedge.setHead(tnodeHead);
                edges[currentTailId].add(tedge);
                i = i + 1;
            }
            previousTailId = currentTailId;

        }



        al = new AdjacencyList(nodes, edges);

    }

    public static void main(String[] args) {
        // TODO code application logic here


        Edge tedge = new Edge();
        ArrayList edgeArray = new ArrayList();
        ArrayList[] edges = new ArrayList[200];
        int start;

        for (int i = 0; i < 200; i++) {
            edges[i] = new ArrayList();
        }


        start = 0;
        DijkstraImproved1 d;

        d = new DijkstraImproved1(200, start);

        d.calculateDijkstra();

        System.out.println(Heap.a[6]);
        System.out.println(Heap.a[36]);
        System.out.println(Heap.a[58]);
        System.out.println(Heap.a[81]);
        System.out.println(Heap.a[98]);
        System.out.println(Heap.a[114]);
        System.out.println(Heap.a[132]);
        System.out.println(Heap.a[164]);
        System.out.println(Heap.a[187]);
        System.out.println(Heap.a[196]);

    }
}
