/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package median;

import java.util.Hashtable;

/**
 *Max heap implementation
 * @author Lenovo-BM
 */
public class MaxHeap {
    private int[] heap;///////return tp private
    private int lastindex = -1;
    //static int[] a;
    Hashtable<Integer, Integer> hash;
    final int inf = 1000000;

    MaxHeap(int size) {
        heap = new int[size];
        hash = new Hashtable();
      //  a = new int[size];
      //  for (int i = 0; i < size; i++) {
      //      a[i] = -inf;
       // }
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
            if (heap[tempIndex] > heap[parentindex]) {
                temp = heap[parentindex];
                hash.remove(heap[tempIndex]);
                hash.put(heap[tempIndex], parentindex);
                hash.remove(temp);
                hash.put(temp, tempIndex);
                heap[parentindex] = heap[tempIndex];
                heap[tempIndex] = temp;
                tempIndex = parentindex;
            } 
            else {
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

    public int deletemax() {
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
                int largest;
                int l = 2 * tempindex + 1;
                int r = 2 * tempindex + 2;
                if ((heap[l] > heap[tempindex])) {
                    largest = l;
                } else {
                    largest = tempindex;
                }
                if (r <= lastindex && (heap[r] > heap[largest])) {
                    largest = r;
                }

                if (largest != tempindex) {
                    temp = heap[largest];
                    hash.remove(heap[tempindex]);
                    hash.put(heap[tempindex], largest);
                    hash.remove(temp);
                    hash.put(temp, tempindex);
                    heap[largest] = heap[tempindex];
                    heap[tempindex] = temp;

                }
                if (largest == l) {
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
            if (heap[tempindex] > heap[(tempindex - 1) / 2]) {
                while (tempindex != 0) {
                    parentindex = (tempindex - 1) / 2;
                    if (heap[tempindex] < heap[parentindex]) {
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
                    int largest;
                    int l = 2 * tempindex + 1;
                    int r = 2 * tempindex + 2;
                    if ((l <= lastindex) && (heap[l] > heap[tempindex])) {
                        largest = l;
                    } else {
                        largest = tempindex;
                    }
                    if (r <= lastindex && heap[r] > heap[largest]) {
                        largest = r;
                    }

                    if (largest != tempindex) {
                        temp = heap[largest];
                        hash.remove(heap[tempindex]);
                        hash.put(heap[tempindex], largest);
                        hash.remove(temp);
                        hash.put(temp, tempindex);
                        heap[largest] = heap[tempindex];
                        heap[tempindex] = temp;
                    }
                    if (largest == l) {
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
        int largest;
        l = 2 * index + 1;
        r = 2 * index + 2;
        if (l <= lastindex && heap[l] > heap[index]) {
            largest = l;
        } 
        else {
            largest = index;
        }
        if (r <= lastindex && heap[r] > heap[largest]) {
            largest = r;
        }
        if (largest != index) {
            int temp = heap[largest];
            hash.remove(heap[index]);
            hash.put(heap[index], largest);
            hash.remove(temp);
            hash.put(temp, index);
            heap[largest] = heap[index];
            heap[index] = temp;

            heapArrange(largest);
        }

    }
    
    public int getmax()
{
    return heap[0];
}
    /**
     * @param args the command line arguments
     */
    
     /*public static void main(String[] args) {
         MaxHeap t=new MaxHeap(10);
         
         for (int i=0;i<10;i++)
         {
             t.a[i]=i;
             t.insert(i);
         }
         int temp=t.deletemax();
         temp=t.deletemax();
         t.deleteByValue(5);
         
     }*/


}