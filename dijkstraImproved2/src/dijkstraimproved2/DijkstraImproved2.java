/*
 * Basic implementation of Dijkstra algorithm which is not fast specially for the selection of min item
 * 
 */
package dijkstraimproved2;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class DijkstraImproved2 {

    AdjacencyList al;
    //Heap heap;
    int start;
    boolean[] explored;
    int nNodes;
    int a[];
    final int inf = 1000000;

    public DijkstraImproved2(int nNodes, int s) {
        al = new AdjacencyList();
        explored = new boolean[nNodes];
        a = new int[nNodes];
        for (int i = 0; i < nNodes; i++) {
            explored[i] = false;
            a[i]=inf;
        }
        
        this.nNodes = nNodes;
        try {
            readfile("DijkstraData.txt");
        } catch (Exception e) {
        };
        // at this point al is ready for use.
        //buildHeap();
        start = s;
        a[start]=0;
        explored[start] = true;
        calculateScore(s);
    }

    private void calculateScore(int s) {
        int tempScore = inf;
        int headId;
        for (int i = 0; i < al.vertex.get(s).getNext().size(); i++) {
            if (explored[al.vertex.get(s).getNext().get(i).getHead().getId()] == false) {
                tempScore = a[s] + al.vertex.get(s).getNext().get(i).getWeight();
                headId = al.vertex.get(s).getNext().get(i).getHead().getId();
                if (tempScore < a[headId]) 
                    a[headId]=tempScore;
            }
                
          }
      }
    

    public void calculateDijkstra() {
        int min = inf;
        int minId = -inf;
        
        for (int j=0;j<nNodes-1;j++){
            for (int i = 0; i < nNodes; i++) {
                if (explored[i] == false) {
                    if (a[i] < min) {
                        min = a[i];
                        minId = i;
                        
                    }
                    
                }
            }
            
            explored[minId] = true;
            calculateScore(minId);
            min=inf;
            minId=-inf;
            
         }
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
                    temp.setScore(inf);
                    nodes.add(temp);
                }
            }



            tnode.setScore(inf);
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
                tnodeHead.setScore(inf);
                tedge.setHead(tnodeHead);
                edges[currentTailId].add(tedge);
                i = i + 1;
            }

            previousTailId = currentTailId;

        }
        s.close();
      al = new AdjacencyList(nodes, edges);
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here


        Edge tedge = new Edge();
        ArrayList edgeArray = new ArrayList();
        ArrayList[] edges = new ArrayList[200];
        //Node start=new Node();

        for (int i = 0; i < 200; i++) {
            edges[i] = new ArrayList();
        }



        DijkstraImproved2 d;

        d = new DijkstraImproved2(200, 0);

        d.calculateDijkstra();

        System.out.println(d.a[6]);
        System.out.println(d.a[36]);
        System.out.println(d.a[58]);
        System.out.println(d.a[81]);
        System.out.println(d.a[98]);
        System.out.println(d.a[114]);
        System.out.println(d.a[132]);
        System.out.println(d.a[164]);
        System.out.println(d.a[187]);
        System.out.println(d.a[196]);







    }
}