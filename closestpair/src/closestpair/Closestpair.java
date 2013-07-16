/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package closestpair;

/**
 *
 * @author Lenovo-BM
 */
public class Closestpair {

    Point[] inputx;
    Point[] inputy;
    
    
    Closestpair(Point[] in)
    {
        inputx=new Point[in.length];
        inputy=new Point[in.length];
        Mergesort m=new Mergesort();
        for (int i=0; i<inputx.length;i++)
        {
            inputx[i]=new Point();
            inputy[i]=new Point();
            inputx[i].set(in[i].returnxy());
            
        }
        
        inputx=m.mergesort(inputx, 0, inputx.length-1, false);
        inputy=m.mergesort(inputx,0,inputx.length-1,true);
        closestpaircalculation(inputx,inputy);
        
    }

    
    private double distance(Point p , Point q)
    {
        double px=p.returnx();
        double py=p.returny();
        double qx=q.returnx();
        double qy=q.returny();
        return Math.sqrt((px-qx)*(px-qx)+(py-qy)*(py-qy));
    }
    public Point[] closestpaircalculation(Point[] px, Point[] py)
    {
        Point[] leftx;
        Point[] rightx;
        Point[] lefty;
        Point[] righty;
        Point[] best=new Point[2];
        best[0]=new Point();
        best[1]=new Point();
        Double bestdistance=Double.POSITIVE_INFINITY;
        
        if (px.length<=3)
        {
            for (int i=0; i<px.length-1;i++)
            {
                double temp=distance(px[i],px[i+1]);
                if (temp<bestdistance)
                {
                    bestdistance=temp;
                    
                    best[0].set(px[i]);
                    best[1].set(px[i+1]);
                }
            return best;
            }
        }
        else
        {
            int middle=px.length/2;
            double middlevalue=px[middle-1].returnx();
            leftx=new Point[middle];
            rightx=new Point[px.length-middle];
            lefty=new Point[middle];
            righty=new Point[px.length-middle];
            
            for (int i=0; i<middle;i++)
            {
                leftx[i]=new Point();
                lefty[i]=new Point();
                leftx[i].set(px[i]);
            }
            for (int i=middle;i<px.length;i++)
            {
                rightx[i-middle]=new Point();
                righty[i-middle]=new Point();
                rightx[i-middle].set(px[i]);
            }
            int leftindex=0;
            int rightindex=0;
            for (int i=0; i<px.length;i++)
            {
                if (py[i].returnx()<=middlevalue)
                {
                    lefty[leftindex]=new Point();
                    lefty[leftindex].set(py[i]);
                    leftindex+=1;
                }
                else
                {
                righty[rightindex]=new Point();
                righty[rightindex].set(py[i]);
                rightindex+=1;
                }
            }
        Point[] bestleft=new Point[2];
        Point[] bestright=new Point[2];
        Point[] bestsplit=new Point[2];
        
        bestleft=closestpaircalculation(leftx,lefty);
        bestright=closestpaircalculation(rightx,righty);
        double leftdistance=distance(bestleft[0].returnxy(),bestleft[1].returnxy());
        double rightdistance=distance(bestright[0].returnxy(), bestright[1].returnxy());
        double delta=Math.min(leftdistance,rightdistance);
        
        bestsplit=splitcalculation(leftx,lefty,rightx,righty,delta);
        double splitdistance=distance(bestsplit[0].returnxy(),bestsplit[1].returnxy());
        if (leftdistance<=rightdistance && leftdistance<=splitdistance)
            best= bestleft;
        else
            if (rightdistance<leftdistance && rightdistance<=splitdistance)
                best=bestright;
            else
                best=bestsplit;
        
        }
    return best;
    }
    
    private Point[] splitcalculation(Point[] leftx, Point[] lefty, Point[] rightx, Point[] righty,double delta)
    {
        Point xbar=new Point();
        Point[] sy;
        xbar.set(leftx[leftx.length-1].returnxy());
        double best=delta;
        Point[] bestpair=new Point[2];
        
        for (int i=0; i<2; i++)
        {
                bestpair[i]=new Point();
                bestpair[i].set(100000.0*i,100000.0*i);
        }
        sy=new Point[leftx.length+rightx.length];
       int  index=0;
       double lowerbound=xbar.returnx()-delta;
       double upperbound=xbar.returnx()+delta;
        for (int i=0;i<leftx.length+rightx.length;i++)
        {
            if (i<leftx.length)
            {
                if (lefty[i].returnx()>=lowerbound)
                {
                    sy[index]=new Point();
                    sy[index].set(lefty[i]);
                    index+=1;
                }
            
                else{}
            }
            else
            {
                if (righty[i-lefty.length].returnx()<=upperbound)
                {
                    sy[index]=new Point();
                    sy[index].set(righty[i-lefty.length]);
                    index+=1;
                }
           }
              
        }
        double t;
        for (int i=1; i<index-1;i++)
            for (int j=1; j<Math.min(7,index-i);i++)
            {
                t=distance(sy[i],sy[i+j]);
                if (t<best)
                {
                    best=t;
                    bestpair[0].set(sy[i]);
                    bestpair[1].set(sy[i+j]);
                }
            }
    return bestpair;
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    Point[] in=new Point[7];
    for (int i=0; i<7; i++)
            in[i]=new Point();
    in[0].set(0.0,0.0);
    in[1].set(1.0,1.0);
    in[2].set(1.3,1.3);
    in[3].set(3,3);
    in[4].set(5.0,5.0);
    in[5].set(8.0,1);
    in[6].set(0.5,0.6);
    
    Closestpair t=new Closestpair(in);
    }
}
