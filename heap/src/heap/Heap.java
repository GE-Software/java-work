/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
// this is implementation of min Heap , with deleteByValue added to be used in 
//dijkstra algorithm. 
package heap;

/**
 *
 * @author Lenovo-BM
 */
public class Heap {

    private int[] heap;
    private int lastindex = -1;

    Heap(int size) {
        heap = new int[size];
    }

    public void insert(int element) {
        int parentindex, tempIndex;
        int temp;
        lastindex += 1;
        heap[lastindex] = element;
        tempIndex = lastindex;
        while (tempIndex != 0) {
            parentindex = (tempIndex - 1) / 2;
            if (heap[tempIndex] < heap[parentindex]) {
                temp = heap[parentindex];
                heap[parentindex] = heap[tempIndex];
                heap[tempIndex] = temp;
                tempIndex = parentindex;
            } else {
                tempIndex = 0;
            }
        }
    }

    // heapify and heaparrange are implemented in order to allow linear time initialization of a heap.
    public void heapify(int[] a) {
        int heapsize = a.length;
        heap = a;
        lastindex = heapsize - 1;
        for (int i = heapsize / 2; i >= 0; i--) {
            heapArrange(i);
        }

    }

    public void heapArrange(int index) {
        int l, r;
        int smallest;
        l = 2 * index + 1;
        r = 2 * index + 2;
        if (l <= lastindex && heap[l] < heap[index]) {
            smallest = l;
        } else {
            smallest = index;
        }
        if (r <= lastindex && heap[r] < heap[smallest]) {
            smallest = r;
        }
        if (smallest != index) {
            int temp = heap[smallest];
            heap[smallest] = heap[index];
            heap[index] = temp;
            heapArrange(smallest);
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
        int value = -1, tempindex, temp;
        if (!isempty()) {

            value = heap[0];
            tempindex = 0;
            heap[0] = heap[lastindex];
            lastindex -= 1;
            boolean complete = false;

            while (complete == false && (2 * tempindex + 1 <= lastindex)) {
                if (2 * tempindex + 2 > lastindex) {
                    if (heap[2 * tempindex + 1] <= heap[tempindex]) {
                        temp = heap[tempindex];
                        heap[tempindex] = heap[2 * tempindex + 1];
                        heap[2 * tempindex + 1] = temp;
                        tempindex = 2 * tempindex + 1;
                    } else {
                        complete = true;
                    }
                } else if (heap[2 * tempindex + 2] <= heap[2 * tempindex + 1]) {
                    if (heap[2 * tempindex + 2] <= heap[tempindex]) {
                        temp = heap[tempindex];
                        heap[tempindex] = heap[2 * tempindex + 2];
                        heap[2 * tempindex + 2] = temp;
                        tempindex = 2 * tempindex + 2;
                    } else {
                        complete = true;
                    }
                } else if (heap[2 * tempindex + 1] <= heap[tempindex]) {
                    temp = heap[tempindex];
                    heap[tempindex] = heap[2 * tempindex + 1];
                    heap[2 * tempindex + 1] = temp;
                    tempindex = 2 * tempindex + 1;

                } else {
                    complete = true;
                }
            }
        }




        return value;
    }

    public void deleteByValue(int value) {
        int tvalue = -1, tempindex = 0, temp = 0;

        for (int i = 0; i < heap.length; i++) {
            if (heap[i] == value) {
                tempindex = i;
                break;
            }
        }


        if (!isempty()) {

            heap[tempindex] = heap[lastindex];
            lastindex -= 1;

            boolean complete = false;

            while (complete == false && (2 * tempindex + 1 <= lastindex)) {
                if (2 * tempindex + 2 > lastindex) {
                    if (heap[2 * tempindex + 1] <= heap[tempindex]) {
                        temp = heap[tempindex];
                        heap[tempindex] = heap[2 * tempindex + 1];
                        heap[2 * tempindex + 1] = temp;
                        tempindex = 2 * tempindex + 1;
                    } else {
                        complete = true;
                    }
                } else if (heap[2 * tempindex + 2] <= heap[2 * tempindex + 1]) {
                    if (heap[2 * tempindex + 2] <= heap[tempindex]) {
                        temp = heap[tempindex];
                        heap[tempindex] = heap[2 * tempindex + 2];
                        heap[2 * tempindex + 2] = temp;
                        tempindex = 2 * tempindex + 2;
                    } else {
                        complete = true;
                    }
                } else if (heap[2 * tempindex + 1] <= heap[tempindex]) {
                    temp = heap[tempindex];
                    heap[tempindex] = heap[2 * tempindex + 1];
                    heap[2 * tempindex + 1] = temp;
                    tempindex = 2 * tempindex + 1;

                } else {
                    complete = true;
                }


            }

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Heap test = new Heap(20);
        int[] a = {5, 4, 3, 6, 2, 15, -1};

        test.heapify(a);
        test.deleteByValue(2);
    }
}
