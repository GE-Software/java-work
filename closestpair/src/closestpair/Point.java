/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package closestpair;


/**
 *
 * @author Lenovo-BM
 */
public class Point {
private double x;
private double y;    

Point(double x, double y)
{
    this.x=x;
    this.y=y;
}

Point ()
{
}


public void set(double x , double y)
{
    this.x=x;
    this.y=y;
}
public void set(Point p)
{
    this.x=p.returnx();
    this.y=p.returny();
}

public double returnx()
{
    return x;
}
public double returny()
{
    return y;
}

public Point returnxy()
{
    Point temp=new Point();
    temp.x=x;
    temp.y=y;
    return temp;
}

}
