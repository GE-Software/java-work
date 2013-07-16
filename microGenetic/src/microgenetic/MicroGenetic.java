/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package microgenetic;
import java.util.Random;
/**
 *
 * @author Lenovo-BM
 */
public class MicroGenetic extends Thread {

    double[][]a;
    static final double ebselon=0.09;
    static final int nchromosomes=1000;
    double bestfitness=Double.POSITIVE_INFINITY;
    int iterations=50000;
    double[] fitness;
    double a3,a2,a1,a0;
    
    MicroGenetic(double a3, double a2,double a1,double a0)
    {
        Random randomGenerator=new Random();
        a=new double[nchromosomes][3];
        fitness=new double[nchromosomes];
        for (int i=0; i<nchromosomes; i++)
            for (int j=0;j<3;j++)
            {
                a[i][j]=randomGenerator.nextDouble()*Math.abs(a0);
                fitness[i]=Double.POSITIVE_INFINITY;
                
            }
        this.a3=a3;
        this.a2=a2;
        this.a1=a1;
        this.a0=a0;
     }
    
    public double[] calculate()
    {
        Random randomGenerator=new Random();
        fitness[0]=calculateFitness(0);
        bestfitness=fitness[0];
        double t;
        for (int i=0; i<iterations; i++)
        {
            for (int j=0; j<nchromosomes-1; j++)
            {
                crossover(j,j+1,randomGenerator.nextInt(3));
                mutation(j+1,randomGenerator.nextInt(3),randomGenerator.nextInt(10000)/1000);
                calculateFitness(j+1);
                if (fitness[j+1]<bestfitness)
                {
                    bestfitness=fitness[j+1];
                   
                    exchange(0,j+1);
                }
            
            }
         fill();       
                
        }
    return returnBest(); 
    }
    
    private double[] returnBest()
    {
        return a[0];
    }
    private void crossover(int first,int second,int index)
    {
        double temp=a[first][index];
        a[first][index]=a[second][index];
        a[second][index]=temp;
    }
    private void mutation(int index, int subindex, double value)
    {
        if (value<5)
            a[index][subindex]=a[index][subindex]-value;
        else
            a[index][subindex]=a[index][subindex]+value;
    }
    private void fill()
    {
        Random randomGenerator=new Random();
        
        for (int i=1; i<nchromosomes;i++)
        {
            a[i][0]=randomGenerator.nextDouble()*Math.abs(a0);
        
            a[i][1]=randomGenerator.nextDouble()*Math.abs(a0);
            a[i][2]=randomGenerator.nextDouble()*Math.abs(a0);
        }
    }
    private double calculateFitness(int index)
    {
        double temp=0;
        double x1=a[index][0];
        double x2=a[index][1];
        double x3=a[index][2];
        
            //temp=temp+Math.abs(a3*Math.pow(x1, 3.0)+a2*Math.pow(x1,2)+a1*Math.pow(x1,1)+a0)
              //      +Math.abs(a3*Math.pow(x2, 3.0)+a2*Math.pow(x2,2)+a1*Math.pow(x2,1)+a0)
              //      +Math.abs(a3*Math.pow(x3, 3.0)+a2*Math.pow(x3,2)+a1*Math.pow(x3,1)+a0);
        temp=temp+Math.abs(x1+x2+x3-a2)+Math.abs(x1*x2+x1*x3+x2*x3-a1)+Math.abs(x1*x2*x3-a0);    
        if (Math.abs(x1-x2)<ebselon)
                //temp=temp+Math.abs(x1-x2)*100000;
                temp=temp+10;
            if (Math.abs(x1-x3)<ebselon)
                //temp=temp+Math.abs(x1-x3)*100000;
                temp=temp+10;
                
            if (Math.abs(x2-x3)<ebselon)
                //temp=temp+Math.abs(x2-x3)*100000;
                temp=temp+10;
    fitness[index]=temp;
    return temp;
    }   
    private void exchange(int index , int index1)
    {  double[] temp=new double[3];
        for (int i=0; i<3; i++)
        {
            temp[i]=a[index][i];
            a[index][i]=a[index1][i];
            a[index1][i]=temp[i];
        }
        temp[0]=fitness[index];
        fitness[index]=fitness[index1];
        fitness[index1]=temp[0];
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    MicroGenetic micro=new MicroGenetic(1,6,8,6);
    micro.calculate();
    }
}
