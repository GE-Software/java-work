/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixmultiply;

/**
 *
 * @author Lenovo-BM
 */
public class Matrixmultiply {
    int [][]A;
    int arows,acolumns;
    
    int[][]B;
    int brows, bcolumns;
    int [][]C;

    Matrixmultiply(int[][]a,int[][]b)
    {
        arows=a.length;
        brows=a[0].length;
        brows=b.length;
        bcolumns=b[0].length;
        A=new int[arows][acolumns];
        B=new int[arows][bcolumns];
        C=new int[arows][bcolumns];
                A=a;
                B=b;
    }
    public int[][]grademultiply()
    {
        for(int i=0; i<arows;i++)
            for (int j=0;j<bcolumns;j++)
            {
                int sum=0;
            
                for (int k=0;k<brows;k++)
                    sum=sum+A[i][k]*B[k][j];
                C[i][j]=sum;
            }
    return C;
    }
    public int[][] strassenmultiply()
    {
        
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    int[][] a=new int [2][2];
    int [][] b=new int[2][2];
    int[][]c=new int[2][2];
    a[0][0]=1;
    a[0][1]=2;
    a[1][0]=3;
    a[1][1]=4;
    b[0][0]=1;
    b[0][1]=2;
    b[1][0]=3;
    b[1][1]=4;
    Matrixmultiply t=new Matrixmultiply(a,b);
    c=t.grademultiply();
    }
}
