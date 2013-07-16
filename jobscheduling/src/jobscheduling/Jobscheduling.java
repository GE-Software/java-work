/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jobscheduling;

/**
 *
 * @author Lenovo-BM
 */
public class Jobscheduling {

    jobs[] S;
    
    Jobscheduling(jobs[] A)
    {
        S=new jobs[A.length];
        S=A;
    }
    
    public float Calculation()
    {   Mergesort sort=new Mergesort();
        S=sort.mergesort(S,0,S.length-1);
        S[0].completiontime=S[0].length;
        for (int i=1;i<S.length;i++)
        {
            S[i].completiontime=S[i-1].completiontime+S[i].length;
        }
         return S[S.length-1].completiontime;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    jobs[] A=new jobs[5];
    A[0]=new jobs();
    A[1]=new jobs();
    A[2]=new jobs();
    A[3]=new jobs();
    A[4]=new jobs();
    A[0].weight=1;
    A[1].weight=1;
    A[2].weight=1;
    A[3].weight=1;
    A[4].weight=1;
    A[0].id=0;
    A[1].id=1;
    A[2].id=2;
    A[3].id=3;
    A[4].id=4;
    A[0].length=1;
    A[1].length=2;
    A[2].length=3;
    A[3].length=4;
    A[4].length=5;
    for (int i=0;i<5;i++)
        A[i].score=A[i].weight/A[i].length;
    Jobscheduling j=new Jobscheduling(A);
     float result=j.Calculation();
    
    }
}
