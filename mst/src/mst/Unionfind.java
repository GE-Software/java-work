/****************************************************************************
 *  Weighted union find with path compression
 *
 ****************************************************************************/


/**
 *  The <tt>UF</tt> class represents a union-find data data structure.
 *  It supports the <em>union</em> and <em>find</em>
 *  operations, along with a method for determining the number of
 *  disjoint sets.
 *  <p>
 *  This implementation uses weighted quick union with path compression.
 *  Creating a data structure with N objects takes linear time.
 *  Afterwards, all operations are logarithmic worst-case time.
 *  <p>
 *  
 */

package mst;
/**
 *
 * @author Lenovo-BM
 */
public class Unionfind {
    private int[] id;    // id[i] = parent of i
    private int[] sz;    // sz[i] = number of objects in subtree rooted at i
    private int count;   // number of components

   /**
     * Create an empty union find data structure with N isolated sets.
     * @throws java.lang.IllegalArgumentException if N < 0
     */
    public Unionfind(int N) {
        if (N < 0) throw new IllegalArgumentException();
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

   /**
     * Return the id of component corresponding to object p.
     * @throws java.lang.IndexOutOfBoundsException unless 0 <= p < N
     */
    public int find(int p) {
        if (p < 0 || p >= id.length) throw new IndexOutOfBoundsException();
        while (p != id[p])
        {
            id[p]=id[id[p]];
            p = id[p];
            
        }
        return p;
    }

   /**
     * Return the number of disjoint sets.
     */
    public int count() {
        return count;
    }

  
   /**
     * Are objects p and q in the same set?
     * @throws java.lang.IndexOutOfBoundsException unless both 0 <= p < N and 0 <= q < N
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

  
   /**
     * Replace sets containing p and q with their union.
     * @throws java.lang.IndexOutOfBoundsException unless both 0 <= p < N and 0 <= q < N
     */
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;

        // make smaller root point to larger one
        if   (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
        else                 { id[j] = i; sz[i] += sz[j]; }
        count--;
    }


   
}


