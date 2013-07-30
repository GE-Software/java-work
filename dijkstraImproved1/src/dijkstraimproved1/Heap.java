/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
// this is implementation of min Heap , with deleteByValue added to be used in 
//dijkstra algorithm.
package dijkstraimproved1;

import java.util.Hashtable;

public class Heap {

    private Node[] heap;
    private int lastindex = -1;
    Hashtable<Integer,Integer> hash; 
    final int inf=1000000;

    Heap(int size) {
        heap = new Node[size];
        hash=new Hashtable<Integer,Integer>();
    }

    public int getSize() {
        return lastindex + 1;
    }

    public void insert(Node element) {
        int parentindex, tempIndex;
        Node temp;
        lastindex += 1;
        heap[lastindex] = element;
        
        hash.put(heap[lastindex].getId(), lastindex);
        
        tempIndex = lastindex;
        while (tempIndex != 0) {
            parentindex = (tempIndex - 1) / 2;
            if (heap[tempIndex].getScore() < heap[parentindex].getScore()) {
                
                hash.remove(heap[tempIndex].getId());
                hash.put(heap[tempIndex].getId(), parentindex);
                
                hash.remove(heap[parentindex].getId());
                hash.put(heap[parentindex].getId(), tempIndex);
                
                temp = new Node();
                temp = temp.copy(heap[parentindex]);
                heap[parentindex] = Node.copy(heap[tempIndex]);
                heap[tempIndex] = Node.copy(temp);
                
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

    public Node deletemin() {
        Node value = null;
        int tempindex;
        Node temp;
        if (!isempty()) {

            value = heap[0];
            tempindex = 0;
            
            hash.remove(value.getId());
            hash.put(value.getId(),-inf);
            hash.remove(heap[lastindex].getId());
            hash.put(heap[lastindex].getId(), 0);
            
            heap[0] = heap[lastindex];
            lastindex -= 1;
            boolean complete = false;

            while (complete == false && (2 * tempindex + 1 <= lastindex)) {
                if (2 * tempindex + 2 > lastindex) {
                    if (heap[2 * tempindex + 1].getScore() <= heap[tempindex].getScore()) {
                        temp = heap[tempindex];
                        
                        hash.remove(temp.getId());
                        hash.put(temp.getId(),2*tempindex+1);
                        
                        hash.remove(heap[2*tempindex+1]);
                        hash.put(heap[2*tempindex+1].getId(),tempindex);
                        
                        heap[tempindex] = heap[2 * tempindex + 1];
                        heap[2 * tempindex + 1] = temp;
                        tempindex = 2 * tempindex + 1;
                    } else {
                        complete = true;
                    }
                } else if (heap[2 * tempindex + 2].getScore() <= heap[2 * tempindex + 1].getScore()) {
                    if (heap[2 * tempindex + 2].getScore() <= heap[tempindex].getScore()) {
                        temp = heap[tempindex];
                        hash.remove(temp.getId());
                        hash.put(temp.getId(),2*tempindex+2);
                        
                        hash.remove(heap[2*tempindex+2]);
                        hash.put(heap[2*tempindex+2].getId(),tempindex);
                        
                        heap[tempindex] = heap[2 * tempindex + 2];
                        heap[2 * tempindex + 2] = temp;
                        tempindex = 2 * tempindex + 2;
                    } else {
                        complete = true;
                    }
                } else if (heap[2 * tempindex + 1].getScore() <= heap[tempindex].getScore()) {
                    temp = heap[tempindex];
                        
                        hash.remove(temp.getId());
                        hash.put(temp.getId(),2*tempindex+1);
                        
                        hash.remove(heap[2*tempindex+1]);
                        hash.put(heap[2*tempindex+1].getId(),tempindex);
                    
                    
                    
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

    // delete by ID
    public void deleteByValue(int idValue) {

        int tempindex = 0;
        Node temp = new Node();

        tempindex=hash.get(idValue);
        hash.remove(idValue);
        hash.put(idValue,-inf);

        if (!isempty()) {

            heap[tempindex] = Node.copy(heap[lastindex]);
            lastindex -= 1;

            boolean complete = false;

            while (complete == false && (2 * tempindex + 1 <= lastindex)) {
                if (2 * tempindex + 2 > lastindex) {
                    if (heap[2 * tempindex + 1].getScore() == heap[tempindex].getScore()) {
                        temp = Node.copy(heap[tempindex]);
                        
                        hash.remove(temp.getId());
                        hash.put(temp.getId(),2*tempindex+1);
                        
                        hash.remove(heap[2*tempindex+1]);
                        hash.put(heap[2*tempindex+1].getId(),tempindex);
                        
                        
                        
                        heap[tempindex] = Node.copy(heap[2 * tempindex + 1]);
                        heap[2 * tempindex + 1] = Node.copy(temp);
                        
                        tempindex = 2 * tempindex + 1;
                    } else {
                        complete = true;
                    }
                } else if (heap[2 * tempindex + 2].getScore() <= heap[2 * tempindex + 1].getScore()) {
                    if (heap[2 * tempindex + 2].getScore() <= heap[tempindex].getScore()) {
                        temp = Node.copy(heap[tempindex]);
                        
                        hash.remove(temp.getId());
                        hash.put(temp.getId(),2*tempindex+2);
                        
                        hash.remove(heap[2*tempindex+2]);
                        hash.put(heap[2*tempindex+2].getId(),tempindex);
                        
                        heap[tempindex] = Node.copy(heap[2 * tempindex + 2]);
                        heap[2 * tempindex + 2] = Node.copy(temp);
                        tempindex = 2 * tempindex + 2;
                    } else {
                        complete = true;
                    }
                } else if (heap[2 * tempindex + 1].getScore() <= heap[tempindex].getScore()) {
                    temp = Node.copy(heap[tempindex]);
                    
                        hash.remove(temp.getId());
                        hash.put(temp.getId(),2*tempindex+1);
                        
                        hash.remove(heap[2*tempindex+1]);
                        hash.put(heap[2*tempindex+1].getId(),tempindex);
                        heap[tempindex] = Node.copy(heap[2 * tempindex + 1]);
                        heap[2 * tempindex + 1] = Node.copy(temp);
                    
                    tempindex = 2 * tempindex + 1;

                } else {
                    complete = true;
                }


            }

        }
    }

    public void heapify(Node[] a) {
        int heapsize = a.length;
        heap = a;
        for (int i=0;i<heapsize;i++)
        {
            hash.put(a[i].getId(),i);
        }
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
        if (l <= lastindex && heap[l].getScore() < heap[index].getScore()) {
            smallest = l;
        } else {
            smallest = index;
        }
        if (r <= lastindex && heap[r].getScore() < heap[smallest].getScore()) {
            smallest = r;
        }
        if (smallest != index) {
            
            hash.remove(heap[smallest].getId());
            hash.put(heap[smallest].getId(), index);
            
            hash.remove(heap[index].getId());
            hash.put(heap[index].getId(), smallest);
            
            Node temp = Node.copy(heap[smallest]);
            heap[smallest] = Node.copy(heap[index]);
            heap[index] = Node.copy(temp);
            heapArrange(smallest);
            
        
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Heap test = new Heap(20);
        /*test.insert(5);
         test.insert(4);
         test.insert(3);
         test.insert(6);
         test.insert(2);
         test.insert(15);
         test.insert(-1);
         //int temp=test.deletemin();
         //temp=test.deletemin();
         test.deleteByValue(3);*/
    }
}
