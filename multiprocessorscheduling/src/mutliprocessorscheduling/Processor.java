/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mutliprocessorscheduling;

/**
 *
 * @author Lenovo-BM
 */
public class Processor {
    public int ID;
    public Double Completiontime;
    StringBuilder Jobssequence;
    
    Processor(int ID)
    {
        this.ID=ID;
        Completiontime=0.0;
        Jobssequence=new StringBuilder();
    }
}
