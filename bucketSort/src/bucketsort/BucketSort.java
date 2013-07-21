/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bucketsort;

/**
 *
 * @author Lenovo-BM
 */
import java.util.Random;
public class BucketSort {
    
    int[] a;
    int[] bucket;
    /**
     * @param args the command line arguments
     */
    
    public int[] bucketSort(int[] a,int max)
    {
        this.a=new int[a.length];
        this.a=a;
        this.bucket=new int[max];
       // for (int i=0; i<max;i++)
       //   this.bucket[i]=0;
        for (int i=0; i<a.length;i++)
            bucket[a[i]]=bucket[a[i]]+1;
        int k=0;
        for (int i=0; i<max;i++)
            for (int j=0; j<bucket[i];j++)
            {
                this.a[k]=i;
                k+=1;
            }
        return this.a;
    }
    public static void main(String[] args) {
        // TODO code application logic here
    int[] a=new int[10];
    Random  randomGenerator = new Random();
    for (int i=0; i<10;i++)
        a[i]=randomGenerator.nextInt(5);
    BucketSort t= new BucketSort();
    a=t.bucketSort(a, 5);
    }
    
}
