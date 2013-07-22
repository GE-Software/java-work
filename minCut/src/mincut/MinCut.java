/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mincut;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Lenovo-BM
 */
public class MinCut {

    /**
     * @param args the command line arguments
     */
    List<Edge> edgeList = new LinkedList<>();
    List<Node> vertexList = new LinkedList<>();
    UF uf;
    Random randomGenerator = new Random();

    public MinCut(int n, int m, List v, List e) // n # of vertices , m # of edges
    {
        uf = new UF(n);
        edgeList = e;
        vertexList = v;
        // remove the duplicate links on the list
        int[] temp = new int[edgeList.size() / 2];
        int counter = 0;
        for (int i = 0; i < edgeList.size(); i++) {

            int tailindex = edgeList.get(i).getTail().getId();
            int headindex = edgeList.get(i).getHead().getId();
            int j = i + 1;
            boolean found = false;
            while (found == false && j < edgeList.size()) {
                if ((tailindex == edgeList.get(j).getHead().getId()) && (headindex == edgeList.get(j).getTail().getId())) {
                    temp[counter] = j;
                    counter = counter + 1;
                    found = true;
                    edgeList.remove(j);

                } else {
                    j = j + 1;
                }
            }
        }

    }

    public int minCut() {
        int index;
        int tailId, headId;

        while (vertexList.size() > 2 && !edgeList.isEmpty()) {
            if (edgeList.size() > 0) {
                index = getRandom(edgeList.size());
                tailId = edgeList.get(index).getTail().getId();
                headId = edgeList.get(index).getHead().getId();
                uf.union(tailId, headId);
                edgeList.remove(index);
                removeVertex(tailId, headId);
                AdjustEdges(tailId, headId);
                removeSelfLoops();
            }
        }
        return edgeList.size();
    }

    private void AdjustEdges(int tailId, int headId) {
        int tochange = 0;
        int changeto = 0;
        if (tailId >= headId) {
            tochange = tailId;
            changeto = headId;
        } else {
            tochange = headId;
            changeto = tailId;
        }
        Node temp;
        for (int i = 0; i < edgeList.size(); i++) {
            if (edgeList.get(i).getTail().getId() == tochange) {
                temp = new Node();
                temp.setId(changeto);
                edgeList.get(i).setTail(temp);
            } else {
                if (edgeList.get(i).getHead().getId() == tochange) {
                    temp = new Node();
                    temp.setId(changeto);
                    edgeList.get(i).setHead(temp);
                }
            }
        }

    }

    private void removeSelfLoops() {
        int[] temp = new int[edgeList.size()];
        int count = 0;
        int t, h;
        for (int i = 0; i < edgeList.size(); i++) {
            t = edgeList.get(i).getTail().getId();
            h = edgeList.get(i).getHead().getId();

            if (uf.connected(t, h)) {
                temp[count] = i;
                count += 1;
            }


        }
        for (int i = count - 1; i >= 0; i--) {
            edgeList.remove(temp[i]);
        }
    }

    private void removeVertex(int t, int h) {

        for (int i = vertexList.size() - 1; i >= 0; i--) {
            if (t == vertexList.get(i).getId()) {
                vertexList.remove(i);
                break;
            } else {
                if (h == vertexList.get(i).getId()) {
                    vertexList.remove(i);
                    break;
                }
            }
        }
    }

    private int getRandom(int m) // m is the # of remaining Edges in Edges list
    {

        return randomGenerator.nextInt(m);
    }

    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here


        List<Node> v = new LinkedList<>();
        List<Edge> e = new LinkedList<>();

        Node[] temp = new Node[200];
        for (int iteration = 0; iteration < 500; iteration++) {
            v = new LinkedList<>();
            e = new LinkedList<>();

            int counter = 0;
            File file = new File("c:\\kargerMinCut.txt");
            Scanner s;
            BufferedReader bf = new BufferedReader(new FileReader(file));
            Node edgetemp;
            Edge[] t = new Edge[5034];

            counter = 0;

            String line = "";
            Scanner lineScanner;
            s = new Scanner(file);
            for (int i = 0; i < 200; i++) {
                line = s.nextLine();
                lineScanner = new Scanner(line);
                temp[i] = new Node();
                temp[i].setId(lineScanner.nextInt() - 1);
                temp[i].setNext(null);
                v.add(temp[i]);
                while (lineScanner.hasNextInt()) {
                    t[counter] = new Edge();
                    t[counter].setDirected(false);
                    edgetemp = new Node();
                    edgetemp.setId(temp[i].getId());
                    t[counter].setTail(edgetemp);
                    edgetemp = new Node();
                    edgetemp.setId(lineScanner.nextInt() - 1);
                    edgetemp.setNext(null);
                    t[counter].setHead(edgetemp);
                    counter += 1;
                }
                lineScanner.close();

            }



            for (int i = 0; i < counter; i++) {
                e.add(t[i]);

            }



            MinCut mc = new MinCut(200, counter, v, e);

            int result = mc.minCut();
            System.out.println("# of edges  " + result);

            //System.out.println(mc.vertexList.get(0).getId());
            //System.out.println(mc.vertexList.get(1).getId());

            //for (int i=0; i<mc.edgeList.size();i++)
            // {
            //   System.out.print(mc.edgeList.get(i).getTail().getId());
            //   System.out.print("     ");
            //   System.out.print(mc.edgeList.get(i).getHead().getId());
            //   System.out.println();
            s.close();
        }
    }
}
