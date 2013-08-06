/*
 * 
 * complicated heap implementation
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
// this is implementation of min Heap , with deleteByValue added to be used in 
//dijkstra algorithm. a fast one with heapify to initialize in linear time , hash table to allow for log(n)
// deletion from the heap.
package dijkstraimproved1;

import java.util.Hashtable;

public class Heap {

    private int[] heap;///////return tp private
    private int lastindex = -1;
    static int[] a;
    Hashtable<Integer, Integer> hash;
    final int inf = 1000000;

    Heap(int size) {
        heap = new int[size];
        hash = new Hashtable();
        a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = inf;
        }
    }

    public int getSize() {
        return lastindex + 1;
    }

    public void insert(int element) {
        int parentindex, tempIndex;
        int temp;
        lastindex += 1;
        heap[lastindex] = element;


        hash.put(element, lastindex);

        tempIndex = lastindex;

        while (tempIndex != 0) {
            parentindex = (tempIndex - 1) / 2;
            if (a[heap[tempIndex]] < a[heap[parentindex]]) {
                temp = heap[parentindex];
                hash.remove(heap[tempIndex]);
                hash.put(heap[tempIndex], parentindex);
                hash.remove(temp);
                hash.put(temp, tempIndex);
                heap[parentindex] = heap[tempIndex];
                heap[tempIndex] = temp;
                tempIndex = parentindex;
            } else {
                tempIndex = 0;
            }
        }
    }

    public boolean isempty() {
        if (lastindex == -1) {
            return true;
        } else {
            return false;
        }
    }

    public int deletemin() {
        int value = 0;
        int tempindex;
        int temp;
        if (lastindex != -1) {

            value = heap[0];
            hash.remove(value);
            hash.put(value, -inf);
            tempindex = 0;
            heap[0] = heap[lastindex];
            hash.remove(heap[lastindex]);
            if (lastindex != 0) {
                hash.put(heap[lastindex], 0);
            } else {
                hash.put(heap[lastindex], -inf);
            }

            lastindex -= 1;

            while (2 * tempindex + 1 <= lastindex) {
                int smallest;
                int l = 2 * tempindex + 1;
                int r = 2 * tempindex + 2;
                if ((a[heap[l]] < a[heap[tempindex]])) {
                    smallest = l;
                } else {
                    smallest = tempindex;
                }
                if (r <= lastindex && (a[heap[r]] < a[heap[smallest]])) {
                    smallest = r;
                }

                if (smallest != tempindex) {
                    temp = heap[smallest];
                    hash.remove(heap[tempindex]);
                    hash.put(heap[tempindex], smallest);
                    hash.remove(temp);
                    hash.put(temp, tempindex);
                    heap[smallest] = heap[tempindex];
                    heap[tempindex] = temp;

                }
                if (smallest == l) {
                    tempindex = 2 * tempindex + 1;
                } else {
                    tempindex = 2 * tempindex + 2;
                }
            }
        }
        return value;
    }

    // delete by ID
    public void deleteByValue(int idValue) {

        int tempindex = 0;
        int temp = 0;
        tempindex = hash.get(idValue);

        if (tempindex != -inf) {
            hash.remove(idValue);
            hash.put(idValue, -inf);
            int parentindex;
            heap[tempindex] = heap[lastindex];
            hash.remove(heap[lastindex]);
            hash.put(heap[lastindex], tempindex);
            lastindex -= 1;
//bubble up
            if (a[heap[tempindex]] < a[heap[(tempindex - 1) / 2]]) {
                while (tempindex != 0) {
                    parentindex = (tempindex - 1) / 2;
                    if (a[heap[tempindex]] < a[heap[parentindex]]) {
                        temp = heap[parentindex];
                        hash.remove(heap[tempindex]);
                        hash.put(heap[tempindex], parentindex);
                        hash.remove(temp);
                        hash.put(temp, tempindex);
                        heap[parentindex] = heap[tempindex];
                        heap[tempindex] = temp;
                        tempindex = parentindex;
                    } else {
                        tempindex = 0;
                    }

                }
            } else {
                while (2 * tempindex + 1 <= lastindex) {
                    int smallest;
                    int l = 2 * tempindex + 1;
                    int r = 2 * tempindex + 2;
                    if ((l <= lastindex) && (a[heap[l]] < a[heap[tempindex]])) {
                        smallest = l;
                    } else {
                        smallest = tempindex;
                    }
                    if (r <= lastindex && a[heap[r]] < a[heap[smallest]]) {
                        smallest = r;
                    }

                    if (smallest != tempindex) {
                        temp = heap[smallest];
                        hash.remove(heap[tempindex]);
                        hash.put(heap[tempindex], smallest);
                        hash.remove(temp);
                        hash.put(temp, tempindex);
                        heap[smallest] = heap[tempindex];
                        heap[tempindex] = temp;
                    }
                    if (smallest == l) {
                        tempindex = 2 * tempindex + 1;
                    } else {
                        tempindex = 2 * tempindex + 2;
                    }
                }
            }
        }
    }

    public void heapify(int[] a) {
        int heapsize = a.length;
        heap = a;
        for (int i = 0; i < heapsize; i++) {
            hash.put(a[i], i);
        }
        lastindex = heapsize - 1;
        for (int i = (heapsize / 2); i >= 0; i--) {
            heapArrange(i);
        }

    }

    public void heapArrange(int index) {
        int l, r;
        int smallest;
        l = 2 * index + 1;
        r = 2 * index + 2;
        if (l <= lastindex && a[heap[l]] < a[heap[index]]) {
            smallest = l;
        } else {
            smallest = index;
        }
        if (r <= lastindex && a[heap[r]] < a[heap[smallest]]) {
            smallest = r;
        }
        if (smallest != index) {
            int temp = heap[smallest];
            hash.remove(heap[index]);
            hash.put(heap[index], smallest);
            hash.remove(temp);
            hash.put(temp, index);
            heap[smallest] = heap[index];
            heap[index] = temp;

            heapArrange(smallest);
        }

    }
    /**
     * @param args the command line arguments
     */
    /*
     public static void main(String[] args) {
     //TODO code application logic here
     Heap test=new Heap(200);
     int[] a=new int[200];
     test.a[0]=0;
     for (int i=0; i<200;i++)
     {
     a[i]=i;
     // test.insert(a[i]);
     test.a[i]=1000000;
     }
     
     for (int i=0; i<200; i++)
     test.deletemin();
     
        
     test.heapify(a);
     int t=test.deletemin();
     test.a[50]=5;
     test.a[60]=6;
     test.deleteByValue(50);
     test.deleteByValue(60);
     test.insert(50);
     test.insert(60);
     test.deleteByValue(60);
     t=test.deletemin();
     test.deleteByValue(5);
     }*/
}
/*   Heap test = new Heap(7);
        int[] a = new int [7];
        int[] score=new int[7];
        a[0]=4;
        a[1]=3;
        a[2]=2;
        a[3]=5;
        a[4]=1;
        a[5]=6;
        a[6]=0;
        //score=a;
       test.a[0]=0;
       test.a[1]=1;
       test.a[2]=2;
       test.a[3]=3;
       test.a[4]=4;
       test.a[5]=5;
       test.a[6]=6;
       test.heapify(a);
       test.deleteByValue(1);
       int temp=test.deletemin();
       test.insert(0);
       test.deleteByValue(3);
       test.insert(1);*/